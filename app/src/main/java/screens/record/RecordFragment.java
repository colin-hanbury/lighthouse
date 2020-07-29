package screens.record;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraMetadata;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.util.Size;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Semaphore;

import data.user.User;
import networking.logout.PostLogout;
import networking.post.recordings.PostToRecentRecordings;
import screens.common.controllers.BaseFragment;
import screens.common.navigation.screennavigation.ScreensNavigator;


public class RecordFragment extends BaseFragment implements IRecordView.Listener,
        PostToRecentRecordings.Listener, PostLogout.Listener {

    private PostLogout mPostLogout;
    private PostToRecentRecordings mPostToRecents;
    private ScreensNavigator mScreensNavigator;
    private HandlerThread mBackgroundThread;
    private Semaphore mCameraOpenCloseLock = new Semaphore(1);
    private Handler mBackgroundHandler;
    private static String TAG = "Record Fragment";
    private IRecordView mIRecordView;
    private CameraDevice mCameraDevice;
    private CaptureRequest.Builder mPreviewBuilder;
    private CameraCaptureSession mPreviewSession;
    private Size mVideoSize;
    private Size mPreviewSize;
    private AutoFitTextureView mTextureView;
    private MediaRecorder mMediaRecorder;
    private File mCurrentFile;
    private Integer mSensorOrientation;
    private boolean mIsRecordingVideo = false;
    private static final String VIDEO_DIRECTORY_NAME = "LightHouse";
    private static final int SENSOR_ORIENTATION_INVERSE_DEGREES = 270;
    private static final int SENSOR_ORIENTATION_DEFAULT_DEGREES = 90;
    private static final SparseIntArray INVERSE_ORIENTATIONS = new SparseIntArray();
    private static final SparseIntArray DEFAULT_ORIENTATIONS = new SparseIntArray();
    static {
        INVERSE_ORIENTATIONS.append(Surface.ROTATION_270, 0);
        INVERSE_ORIENTATIONS.append(Surface.ROTATION_180, 90);
        INVERSE_ORIENTATIONS.append(Surface.ROTATION_90, 180);
        INVERSE_ORIENTATIONS.append(Surface.ROTATION_0, 270);
    }
    static {
        DEFAULT_ORIENTATIONS.append(Surface.ROTATION_90, 0);
        DEFAULT_ORIENTATIONS.append(Surface.ROTATION_0, 90);
        DEFAULT_ORIENTATIONS.append(Surface.ROTATION_270, 180);
        DEFAULT_ORIENTATIONS.append(Surface.ROTATION_180, 270);
    }




    public static RecordFragment newInstance(){
        Log.i(TAG, "email: " + User.getUserInstance().getEmail());
        Log.i(TAG, "name: " + User.getUserInstance().getDisplayName());
        RecordFragment fragment = new RecordFragment();
        return fragment;
    }

    private CameraDevice.StateCallback mStateCallback = new CameraDevice.StateCallback() {
        @Override
        public void onOpened(@NonNull CameraDevice cameraDevice) {
            mCameraDevice = cameraDevice;
            startPreview();
            mCameraOpenCloseLock.release();
            if (null != mTextureView) {
                configureTransform(mIRecordView.getTextureView().getWidth(),
                        mIRecordView.getTextureView().getHeight());
            }
        }
        @Override
        public void onDisconnected(@NonNull CameraDevice cameraDevice) {
            mCameraOpenCloseLock.release();
            cameraDevice.close();
            mCameraDevice = null;
        }
        @Override
        public void onError(@NonNull CameraDevice cameraDevice, int error) {
            mCameraOpenCloseLock.release();
            cameraDevice.close();
            mCameraDevice = null;
            Activity activity = getActivity();
            if (null != activity) {
                activity.finish();
            }
        }
    };


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "email: " + User.getUserInstance().getEmail());
        mIRecordView = getCompositionRoot().getLightHouseViewFactory().getRecordView(container);
        mPostLogout = getCompositionRoot().getPostLogout();
        mScreensNavigator = getCompositionRoot().getScreensNavigator();
        mPostToRecents = getCompositionRoot().getPostToRecentRecordings();
        return mIRecordView.getRootView();
    }

    @Override
    public void onStart() {
        super.onStart();
        mIRecordView.registerListener(this);
        mPostLogout.registerListener(this);
        mPostToRecents.registerListener(this);
        mIRecordView.showProgressIndication();
        startBackgroundThread();
        mTextureView = (AutoFitTextureView) mIRecordView.getTextureView();
        if (mTextureView != null) {
            try {
                openCamera(mTextureView.getWidth(), mTextureView.getHeight());
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        } else {
            Log.i(TAG, "texture view not available, set listener");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mIRecordView.registerListener(this);
        mIRecordView.showProgressIndication();
        startBackgroundThread();
    }
    @Override
    public void onPause() {
        closeCamera();
        mIRecordView.unregisterListener(this);
        stopBackgroundThread();
        super.onPause();
    }

    @Override
    public void onStop() {
        mIRecordView.unregisterListener(this);
        mPostLogout.unregisterListener(this);
        mPostToRecents.unregisterListener(this);
        super.onStop();
    }

    private void startBackgroundThread() {
        mBackgroundThread = new HandlerThread("CameraBackground");
        mBackgroundThread.start();
        mBackgroundHandler = new Handler(mBackgroundThread.getLooper());
    }
    /**
     * Stops the background thread and its {@link Handler}.
     */
    private void stopBackgroundThread() {
        mBackgroundThread.quitSafely();
        try {
            mBackgroundThread.join();
            mBackgroundThread = null;
            mBackgroundHandler = null;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void openCamera(int width, int height) throws CameraAccessException {
        final Activity activity = getActivity();
        if (null == activity || activity.isFinishing()) {
            return;
        }
        CameraManager cameraManager = (CameraManager) getContext()
                .getSystemService(Context.CAMERA_SERVICE);

        String frontCameraID = cameraManager.getCameraIdList()[0];

        CameraCharacteristics cameraCharacteristics =
                cameraManager.getCameraCharacteristics(frontCameraID);
        StreamConfigurationMap streamConfigMap =
                cameraCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
        mSensorOrientation = cameraCharacteristics.get(CameraCharacteristics.SENSOR_ORIENTATION);
        mVideoSize = chooseVideoSize(streamConfigMap.getOutputSizes(MediaRecorder.class));
        mPreviewSize = chooseOptimalSize(streamConfigMap.getOutputSizes(SurfaceTexture.class),
                width, height, mVideoSize);
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            mTextureView.setAspectRatio(mPreviewSize.getWidth(), mPreviewSize.getHeight());
        } else {
            mTextureView.setAspectRatio(mPreviewSize.getHeight(), mPreviewSize.getWidth());
        }
        configureTransform(width, height);
        mMediaRecorder = new MediaRecorder();
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        cameraManager.openCamera(frontCameraID, mStateCallback, null);
        mIRecordView.hideProgressIndication();
    }

    private File getOutputMediaFile() {
        // External sdcard file location
        File mediaStorageDir = new File(getContext().getExternalFilesDir(null)
                , VIDEO_DIRECTORY_NAME);
        // Create storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(TAG, "Oops! Failed create "
                        + VIDEO_DIRECTORY_NAME + " directory");
                return null;
            }
        }
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());
        File mediaFile = new File(mediaStorageDir.getPath() + File.separator
                + "VID_" + timeStamp + ".mp4");
        return mediaFile;
    }

    private void closeCamera() {
        try {
            mCameraOpenCloseLock.acquire();
            closePreviewSession();
            if (null != mCameraDevice) {
                mCameraDevice.close();
                mCameraDevice = null;
            }
            if (null != mMediaRecorder) {
                mMediaRecorder.release();
                mMediaRecorder = null;
            }
        } catch (InterruptedException e) {
            throw new RuntimeException("Interrupted while trying to lock camera closing.");
        } finally {
            mCameraOpenCloseLock.release();
        }
    }

    private void startPreview() {
        if (null == mCameraDevice || !mTextureView.isAvailable() || null == mPreviewSize) {
            return;
        }
        try {
            closePreviewSession();
            SurfaceTexture texture = mTextureView.getSurfaceTexture();
            assert texture != null;
            texture.setDefaultBufferSize(mPreviewSize.getWidth(), mPreviewSize.getHeight());
            mPreviewBuilder = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
            Surface previewSurface = new Surface(texture);
            mPreviewBuilder.addTarget(previewSurface);
            mCameraDevice.createCaptureSession(Collections.singletonList(previewSurface),
                    new CameraCaptureSession.StateCallback() {
                        @Override
                        public void onConfigured(@NonNull CameraCaptureSession session) {
                            mPreviewSession = session;
                            updatePreview();
                        }
                        @Override
                        public void onConfigureFailed(@NonNull CameraCaptureSession session) {
                            Log.e(TAG, "onConfigureFailed: Failed ");
                            mIRecordView.hideProgressIndication();
                        }
                    }, mBackgroundHandler);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    private void updatePreview() {
        if (mCameraDevice == null) {
            return;
        }
        try {
            setUpCaptureRequestBuilder(mPreviewBuilder);
            HandlerThread thread = new HandlerThread("CameraPreview");
            thread.start();
            mPreviewSession.setRepeatingRequest(mPreviewBuilder.build(), null,
                    mBackgroundHandler);
            mIRecordView.hideProgressIndication();
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    private void setUpCaptureRequestBuilder(CaptureRequest.Builder builder) {
        builder.set(CaptureRequest.CONTROL_MODE, CameraMetadata.CONTROL_MODE_AUTO);
    }
    /**
     * Configures the necessary {@link Matrix} transformation to `mTextureView`.
     * This method should not to be called until the camera preview size is determined in
     * openCamera, or until the size of `mTextureView` is fixed.
     *
     * @param viewWidth The width of `mTextureView`
     * @param viewHeight The height of `mTextureView`
     */
    private void configureTransform(int viewWidth, int viewHeight) {
        Activity activity = getActivity();
        if (null == mTextureView || null == mPreviewSize || null == activity) {
            return;
        }
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        Matrix matrix = new Matrix();
        RectF viewRect = new RectF(0, 0, viewWidth, viewHeight);
        RectF bufferRect = new RectF(0, 0, mPreviewSize.getHeight(), mPreviewSize.getWidth());
        float centerX = viewRect.centerX();
        float centerY = viewRect.centerY();
        if (Surface.ROTATION_90 == rotation || Surface.ROTATION_270 == rotation) {
            bufferRect.offset(centerX - bufferRect.centerX(), centerY - bufferRect.centerY());
            matrix.setRectToRect(viewRect, bufferRect, Matrix.ScaleToFit.FILL);
            float scale = Math.max(
                    (float) viewHeight / mPreviewSize.getHeight(),
                    (float) viewWidth / mPreviewSize.getWidth());
            matrix.postScale(scale, scale, centerX, centerY);
            matrix.postRotate(90 * (rotation - 2), centerX, centerY);
        }
        mTextureView.setTransform(matrix);
    }

    private void setUpMediaRecorder() throws IOException {
        final Activity activity = getActivity();
        if (null == activity) {
            return;
        }
        mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mMediaRecorder.setVideoSource(MediaRecorder.VideoSource.SURFACE);
        mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        /**
         * create video output file
         */
        mCurrentFile = getOutputMediaFile();
        /**
         * set output file in media recorder
         */
        mMediaRecorder.setOutputFile(mCurrentFile.getAbsolutePath());
        CamcorderProfile profile = CamcorderProfile.get(CamcorderProfile.QUALITY_480P);
        mMediaRecorder.setVideoFrameRate(profile.videoFrameRate);
        mMediaRecorder.setVideoSize(profile.videoFrameWidth, profile.videoFrameHeight);
        mMediaRecorder.setVideoEncodingBitRate(profile.videoBitRate);
        mMediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
        mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        mMediaRecorder.setAudioEncodingBitRate(profile.audioBitRate);
        mMediaRecorder.setAudioSamplingRate(profile.audioSampleRate);
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        switch (mSensorOrientation) {
            case SENSOR_ORIENTATION_DEFAULT_DEGREES:
                mMediaRecorder.setOrientationHint(DEFAULT_ORIENTATIONS.get(rotation));
                break;
            case SENSOR_ORIENTATION_INVERSE_DEGREES:
                mMediaRecorder.setOrientationHint(INVERSE_ORIENTATIONS.get(rotation));
                break;
        }
        mMediaRecorder.prepare();
    }

    public void startRecordingVideo() {
        if (null == mCameraDevice || !mTextureView.isAvailable() || null == mPreviewSize) {
            return;
        }
        try {
            closePreviewSession();
            setUpMediaRecorder();
            SurfaceTexture texture = mTextureView.getSurfaceTexture();
            assert texture != null;
            texture.setDefaultBufferSize(mPreviewSize.getWidth(), mPreviewSize.getHeight());
            mPreviewBuilder = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_RECORD);
            List<Surface> surfaces = new ArrayList<>();
            /**
             * Surface for the camera preview set up
             */
            Surface previewSurface = new Surface(texture);
            surfaces.add(previewSurface);
            mPreviewBuilder.addTarget(previewSurface);
            //MediaRecorder setup for surface
            Surface recorderSurface = mMediaRecorder.getSurface();
            surfaces.add(recorderSurface);
            mPreviewBuilder.addTarget(recorderSurface);
            // Start a capture session
            mCameraDevice.createCaptureSession(surfaces, new CameraCaptureSession.StateCallback() {
                @Override
                public void onConfigured(@NonNull CameraCaptureSession cameraCaptureSession) {
                    mPreviewSession = cameraCaptureSession;
                    updatePreview();
                    getActivity().runOnUiThread(() -> {
                        mIsRecordingVideo = true;
                        // Start recording
                        mMediaRecorder.start();
                    });
                }
                @Override
                public void onConfigureFailed(@NonNull CameraCaptureSession cameraCaptureSession) {
                    Log.e(TAG, "onConfigureFailed: Failed");
                    mIsRecordingVideo = false;
                    mIRecordView.hideProgressIndication();
                }
            }, mBackgroundHandler);
        } catch (CameraAccessException | IOException e) {
            mIsRecordingVideo = false;
            e.printStackTrace();
        }
        mIsRecordingVideo = true;
    }
    private void closePreviewSession() {
        if (mPreviewSession != null) {
            mPreviewSession.close();
            mPreviewSession = null;
        }
    }

    public void stopRecordingVideo() {
        // UI
        try {
            mPreviewSession.stopRepeating();
            mPreviewSession.abortCaptures();
        }
        catch (CameraAccessException e) {
            mIsRecordingVideo = true;
            e.printStackTrace();
        }
        // Stop recording
        mMediaRecorder.stop();
        mMediaRecorder.reset();
        mPostToRecents.tryPostToRecentRecordingsAndNotify(mCurrentFile);
        // Start preview
        try {
            openCamera(mTextureView.getWidth(), mTextureView.getHeight());
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
        mIRecordView.hideProgressIndication();
        mIsRecordingVideo = false;
    }

    private static Size chooseVideoSize(Size[] choices) {
        for (Size size : choices) {
            if (1920 == size.getWidth() && 1080 == size.getHeight()) {
                return size;
            }
        }
        for (Size size : choices) {
            if (size.getWidth() == size.getHeight() * 4 / 3 && size.getWidth() <= 1080) {
                return size;
            }
        }
        Log.e(TAG, "Couldn't find any suitable video size");
        return choices[choices.length - 1];
    }

    private static Size chooseOptimalSize(Size[] choices, int width, int height, Size aspectRatio) {
        // Collect the supported resolutions that are at least as big as the preview Surface
        List<Size> bigEnough = new ArrayList<>();
        int w = aspectRatio.getWidth();
        int h = aspectRatio.getHeight();
        for (Size option : choices) {
            if (option.getHeight() == option.getWidth() * h / w &&
                    option.getWidth() >= width && option.getHeight() >= height) {
                bigEnough.add(option);
            }
        }
        // Pick the smallest of those, assuming we found any
        if (bigEnough.size() > 0) {
            return Collections.min(bigEnough, new CompareSizesByArea());
        } else {
            Log.e(TAG, "Couldn't find any suitable preview size");
            return choices[0];
        }
    }

    @Override
    public void onPostToRecentRecordingsSuccess() {
        Log.i(TAG, "post recent recordings success");
//        mIRecordView.showSavedMessage();
    }

    @Override
    public void onPostToRecentRecordingsFailure(String error) {
        Log.i(TAG, "post recent recordings failure");
        //        mIRecordView.showFailureMessage(error);

    }

    @Override
    public void onLogoutSuccess() {
        mScreensNavigator.toLoginScreen();
    }

    @Override
    public void onLogoutFailure(String error) {
        mIRecordView.showToast(error);
    }

    static class CompareSizesByArea implements Comparator<Size> {
        @Override
        public int compare(Size lhs, Size rhs) {
            // We cast here to ensure the multiplications won't overflow
            return Long.signum((long) lhs.getWidth() * lhs.getHeight() -
                    (long) rhs.getWidth() * rhs.getHeight());
        }
    }


    @Override
    public void onBackClicked() {
        mScreensNavigator.navigateBack();
    }

    @Override
    public void onLogoutClicked() {
        mPostLogout.tryLogoutAndNotify();
    }

    @Override
    public void onRecordButtonClicked(){
        mIRecordView.showProgressIndication();
        if (mIsRecordingVideo) {
            try {
                stopRecordingVideo();
                mIRecordView.showStartRecordingButton();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            startRecordingVideo();
            mIRecordView.showStopRecordingButton();
        }
    }

    @Override
    public void notifySurfaceAvailable(int width, int height) {
        mTextureView = (AutoFitTextureView) mIRecordView.getTextureView();
        try {
            openCamera(width, height);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void notifySurfaceSizeChanged(int width, int height) {
        configureTransform(width, height);
    }

}