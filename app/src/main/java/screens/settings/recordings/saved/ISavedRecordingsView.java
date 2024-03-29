package screens.settings.recordings.saved;

import data.recordings.Recording;
import screens.common.view.IBaseObservableView;

public interface ISavedRecordingsView extends IBaseObservableView<ISavedRecordingsView.Listener> {


    interface Listener {
        void onRecordingFileClicked(Recording recording);
        void onBackClicked();
        void onLogoutClicked();
    }

    void showProgressIndication();

    void hideProgressIndication();

    void showToast(String message);
}
