package screens.settings.settingsitem;

import data.settings.SettingsItem;
import screens.common.view.IBaseObservableView;

public interface ISettingsItemView extends IBaseObservableView<ISettingsItemView.Listener> {

    interface Listener {
        void onSettingItemClicked(SettingsItem item);
    }

    void bindSettingItem(SettingsItem item);
}
