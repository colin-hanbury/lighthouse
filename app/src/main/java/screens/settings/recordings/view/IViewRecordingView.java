package screens.settings.recordings.view;

import screens.common.view.IBaseObservableView;

public interface IViewRecordingView extends IBaseObservableView<IViewRecordingView.Listener> {

    interface Listener{
        void onBackClicked();
        void onLogoutClicked();
        void onPlayPauseClicked();
    }

    void play();

    void pause();

    void showProgressIndication();

    void hideProgressIndication();

    void showToast(String message);

    void setRecordingPath(String path);
}
