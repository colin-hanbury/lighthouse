package screens.settings;

import data.settings.SettingsItem;
import screens.common.view.IBaseObservableView;

import static screens.settings.ISettingsView.*;

public interface ISettingsView extends IBaseObservableView<Listener> {


    interface Listener {
        void onSettingItemClicked(SettingsItem settingsItem);
        void onBackClicked();
        void onLogoutClicked();
    }

    void showProgressIndication();

    void hideProgressIndication();

    void showToast(String message);
}
