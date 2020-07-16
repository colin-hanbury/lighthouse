package screens.settings.settingsitem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import data.settings.SettingsItem;
import hanbury.colin.networking.R;
import screens.common.view.BaseObservableView;

public class SettingsItemView extends BaseObservableView<ISettingsItemView.Listener>
        implements ISettingsItemView {

    private final ImageView mIcon;
    private final TextView mTitle;

    private SettingsItem mSettingItem;

    public SettingsItemView(LayoutInflater inflater, @Nullable ViewGroup parent) {
        setRootView(inflater.inflate(R.layout.setting_item, parent, false));
        mIcon = findViewById(R.id.setting_item_icon);
        mTitle = findViewById(R.id.setting_item_title);
        getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (Listener listener : getListeners()) {
                    listener.onSettingItemClicked(mSettingItem);
                }
            }
        });
    }
    @Override
    public void bindSettingItem(SettingsItem item) {
        mSettingItem = item;
        mIcon.setImageResource(mSettingItem.getIconID());
        mTitle.setText(mSettingItem.getTitle());
    }
}
