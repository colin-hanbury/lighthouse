package screens.settings.recordings.saved;

import screens.common.view.IBaseObservableView;

public interface ISavedRecordingsView extends IBaseObservableView<ISavedRecordingsView.Listener> {

    interface Listener {
        void onBackClicked();
        void onLogoutClicked();
    }

    void showProgressIndication();

    void hideProgressIndication();
}
