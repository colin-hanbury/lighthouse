package hanbury.colin.lighthouse.screens.common;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import hanbury.colin.lighthouse.screens.common.toolbar.ToolbarView;
import hanbury.colin.lighthouse.screens.settings.ISettingsView;
import hanbury.colin.lighthouse.screens.settings.SettingsView;
import hanbury.colin.lighthouse.screens.map.IMapView;
import hanbury.colin.lighthouse.screens.map.MapView;
import hanbury.colin.lighthouse.screens.record.IRecordView;
import hanbury.colin.lighthouse.screens.record.RecordView;


public class LightHouseViewFactory {

    private final LayoutInflater layoutInflater;

    public LightHouseViewFactory(LayoutInflater layoutInflater) {
        this.layoutInflater = layoutInflater;
    }

    public IRecordView getRecordView(@Nullable ViewGroup parent) {
        return new RecordView(layoutInflater, parent, this);//mNavDrawerHelper, this);
    }
    public IMapView getMapView(@Nullable ViewGroup parent) {
        return new MapView(layoutInflater, parent, this);//mNavDrawerHelper, this);
    }
    public ISettingsView getSettingsView(@Nullable ViewGroup parent) {
        return new SettingsView(layoutInflater, parent, this);
    }

//    public IMatchAttributesView getMatchAttributesView(ViewGroup parent) {
//        return new MatchAttributesView(layoutInflater, parent, this);
//    }
//
    public ToolbarView getToolbarView(@Nullable ViewGroup parent) {
        return new ToolbarView(layoutInflater, parent);
    }
//
//    public IBottomNavigationBar getBottomNavBar(@Nullable ViewGroup parent) {
//        return new BottomNavigationBar(layoutInflater, parent);
//    }

}