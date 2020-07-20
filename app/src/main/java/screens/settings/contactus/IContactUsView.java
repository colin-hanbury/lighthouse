package screens.settings.contactus;

import screens.common.view.IBaseObservableView;

public interface IContactUsView extends IBaseObservableView<IContactUsView.Listener> {

    interface Listener{
        void onBackClicked();
        void onLogoutClicked();
    }

    void showProgressIndication();

    void hideProgressIndication();
}
