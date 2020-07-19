package screens.settings.notifications;

import screens.common.view.IBaseObservableView;

public interface INotificationsView extends IBaseObservableView<INotificationsView.Listener> {
    interface Listener {
        void onBackClicked();
        void onLogoutClicked();
    }

    void showProgressIndication();

    void hideProgressIndication();
}