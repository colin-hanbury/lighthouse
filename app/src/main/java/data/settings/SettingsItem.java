package data.settings;

import android.graphics.drawable.Icon;

public class SettingsItem {
    private final Icon mIcon;
    private final String mTitle;

    public SettingsItem(Icon icon, String title){
        this.mIcon = icon;
        this.mTitle = title;
    }

    public Icon getIcon() {
        return mIcon;
    }

    public String getTitle() {
        return mTitle;
    }
}