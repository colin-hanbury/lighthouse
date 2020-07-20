package screens.settings.recordings.recent;

import screens.common.view.IBaseObservableView;

public interface IRecentRecordingsView extends IBaseObservableView<IRecentRecordingsView.Listener> {

    interface Listener{
        void onBackClicked();
        void onLogoutClicked();
    }

    void showProgressIndication();

    void hideProgressIndication();
}
