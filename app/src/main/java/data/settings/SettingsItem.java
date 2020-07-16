package data.settings;

public class SettingsItem {
    private final int mIcon;
    private final String mTitle;

    public SettingsItem(int icon, String title){
        this.mIcon = icon;
        this.mTitle = title;
    }

    public int getIconID() {
        return mIcon;
    }

    public String getTitle() {
        return mTitle;
    }
}