package screens.settings;

import screens.common.view.IBaseObservableView;

import static screens.settings.ISettingsView.*;

public interface ISettingsView extends IBaseObservableView<Listener> {

    interface Listener {
        void onBackClicked();
        void onLogoutClicked();
    }

    void showProgressIndication();

    void hideProgressIndication();
}
