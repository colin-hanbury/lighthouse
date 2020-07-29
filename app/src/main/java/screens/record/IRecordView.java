package screens.record;

import android.graphics.SurfaceTexture;
import android.view.TextureView;
import android.widget.VideoView;

import screens.common.view.IBaseObservableView;

import static screens.record.IRecordView.*;

public interface IRecordView extends IBaseObservableView<Listener> {


    interface Listener {
        void onBackClicked();
        void onLogoutClicked();
        void onRecordButtonClicked();
        void notifySurfaceAvailable(int width, int height);
        void notifySurfaceSizeChanged(int width, int height);
    }

    TextureView getTextureView();

    VideoView getVideoView();

    void showCameraPreviewView();

    void showCameraRecordingView();

    void showProgressIndication();

    void hideProgressIndication();

    void showStartRecordingButton();

    void showStopRecordingButton();

    void showToast(String message);
}
