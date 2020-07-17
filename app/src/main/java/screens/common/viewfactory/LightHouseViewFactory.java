package screens.common.viewfactory;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import screens.common.navigation.bottomnavigation.BottomNavigationBar;
import screens.common.navigation.bottomnavigation.IBottomNavigationBar;
import screens.common.toolbar.ToolbarView;
import screens.map.IMapsView;
import screens.map.MapsView;
import screens.record.IRecordView;
import screens.record.RecordView;
import screens.settings.ISettingsView;
import screens.settings.SettingsView;
import screens.settings.account.AccountView;
import screens.settings.account.IAccountView;
import screens.settings.settingsitem.ISettingsItemView;
import screens.settings.settingsitem.SettingsItemView;


public class LightHouseViewFactory {

    private final LayoutInflater layoutInflater;

    public LightHouseViewFactory(LayoutInflater layoutInflater) {
        this.layoutInflater = layoutInflater;
    }

    public IRecordView getRecordView(@Nullable ViewGroup parent) {
        return new RecordView(layoutInflater, parent, this);
    }
    public IMapsView getMapView(@Nullable ViewGroup parent) {
        return new MapsView(layoutInflater, parent, this);
    }
    public ISettingsView getSettingsView(@Nullable ViewGroup parent) {
        return new SettingsView(layoutInflater, parent, this);
    }
    public ISettingsItemView getSettingsItemView(ViewGroup parent) {
        return new SettingsItemView(layoutInflater, parent);
    }
    public ToolbarView getToolbarView(@Nullable ViewGroup parent) {
        return new ToolbarView(layoutInflater, parent);
    }
    public IBottomNavigationBar getBottomNavBar(@Nullable ViewGroup parent) {
        return new BottomNavigationBar(layoutInflater, parent);
    }

    public IAccountView getAccountView(ViewGroup parent) {
        return new AccountView(layoutInflater, parent, this);
    }
}
