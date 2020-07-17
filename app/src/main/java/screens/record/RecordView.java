package screens.record;

import android.graphics.SurfaceTexture;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.VideoView;

import androidx.appcompat.widget.Toolbar;

import hanbury.colin.lighthouse.R;
import screens.common.toolbar.ToolbarView;
import screens.common.view.BaseObservableView;
import screens.common.viewfactory.LightHouseViewFactory;

import static hanbury.colin.lighthouse.R.drawable.ic_start_recording_icon;
import static hanbury.colin.lighthouse.R.drawable.ic_stop_recording_icon;


public class RecordView extends BaseObservableView<IRecordView.Listener> implements IRecordView,
        TextureView.SurfaceTextureListener{

    private static String TAG = "RecordView";
    private final ProgressBar mProgressBar;
    private final VideoView mVideoView;
    private TextureView mTextureView;
    private ImageButton mRecordButton;
    private final Toolbar mToolbar;
    private final ToolbarView mToolbarView;

    public RecordView(LayoutInflater inflater, ViewGroup parent,
                      LightHouseViewFactory lightHouseViewFactory)  {
        setRootView(inflater.inflate(R.layout.fragment_record, parent, false));
        mProgressBar = findViewById(R.id.record_progressBar);
        mTextureView = findViewById(R.id.record_texture_view);
        mVideoView = findViewById(R.id.record_video_view);
        mRecordButton = findViewById(R.id.record_button);
        mToolbar = findViewById(R.id.toolbar_widget);
        mToolbarView = lightHouseViewFactory.getToolbarView(mToolbar);
        initToolbar();
        mTextureView.setSurfaceTextureListener(this);
        mRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "record button clicked");
                for (Listener listener : getListeners()) {
                    Log.i(TAG, "onRecordButtonClicked() called");
                    listener.onRecordButtonClicked();
                }

            }
        });
    }

    private void initToolbar() {
        mToolbar.addView(mToolbarView.getRootView());

        mToolbarView.setTitle(getString(R.string.title_record));

        mToolbarView.enableBackButtonAndListen(new ToolbarView.BackClickListener() {
            @Override
            public void onBackClicked() {
                for (Listener listener : getListeners()) {
                    listener.onBackClicked();
                }
            }
        });
        mToolbarView.enableLogoutButtonAndListen(new ToolbarView.LogoutClickListener() {
            @Override
            public void onLogoutClicked() {
                for (Listener listener : getListeners()) {
                    listener.onLogoutClicked();
                }
            }
        });
    }

    @Override
    public TextureView getTextureView() {
        return mTextureView;
    }


    @Override
    public VideoView getVideoView() {
        return mVideoView;
    }

    @Override
    public void showCameraPreviewView() {
        mVideoView.setVisibility(View.GONE);
        mTextureView.setVisibility(View.VISIBLE);
    }
    @Override
    public void showCameraRecordingView() {
        mTextureView.setVisibility(View.GONE);
        mVideoView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgressIndication() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressIndication() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showStartRecordingButton() {
        mRecordButton.setImageResource(ic_start_recording_icon);
    }

    @Override
    public void showStopRecordingButton() {
        mRecordButton.setImageResource(ic_stop_recording_icon);
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        for (Listener listener : getListeners()) {
            listener.notifySurfaceAvailable(width, height);
        }
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
        for (Listener listener : getListeners()) {
            listener.notifySurfaceSizeChanged(width, height);
        }
    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        return true;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {
    }
}
