package screens.map;

import com.google.android.gms.maps.MapView;

import screens.common.view.IBaseObservableView;

import static screens.map.IMapsView.*;

public interface IMapsView extends IBaseObservableView<Listener> {

    interface Listener {
        void onBackClicked();
        void onLogoutClicked();
    }
    void showProgressIndication();

    void hideProgressIndication();

    MapView getMapView();
}