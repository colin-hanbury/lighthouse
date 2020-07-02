package screens.common.viewfactory;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import screens.common.toolbar.ToolbarView;
import screens.map.IMapView;
import screens.map.MapView;
import screens.record.IRecordView;
import screens.record.RecordView;
import screens.settings.ISettingsView;
import screens.settings.SettingsView;


public class LightHouseViewFactory {

    private final LayoutInflater layoutInflater;

    public LightHouseViewFactory(LayoutInflater layoutInflater) {
        this.layoutInflater = layoutInflater;
    }

    public IRecordView getRecordView(@Nullable ViewGroup parent) {
        return new RecordView(layoutInflater, parent, this);
    }
    public IMapView getMapView(@Nullable ViewGroup parent) {
        return new MapView(layoutInflater, parent, this);
    }
    public ISettingsView getSettingsView(@Nullable ViewGroup parent) {
        return new SettingsView(layoutInflater, parent, this);
    }

    public ToolbarView getToolbarView(@Nullable ViewGroup parent) {
        return new ToolbarView(layoutInflater, parent);
    }


}
