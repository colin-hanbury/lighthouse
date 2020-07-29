package screens.settings.account;

import data.settings.SettingsItem;
import screens.common.view.IBaseObservableView;

public interface IAccountView extends IBaseObservableView<IAccountView.Listener> {


    interface Listener {
        void onBackClicked();
        void onLogoutClicked();
    }

    void showProgressIndication();

    void hideProgressIndication();

    void showToast(String message);
}
