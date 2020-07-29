package screens.map;

import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;

import screens.common.view.IBaseObservableView;

import static screens.map.IMapsView.*;

public interface IMapsView extends IBaseObservableView<Listener> {


    interface Listener {
        void onBackClicked();
        void onLogoutClicked();
    }

    void goToCurrentLocation(LatLng location);

    void showProgressIndication();

    void hideProgressIndication();

    void showToast(String message);

    MapView getMapView();
}