package screens.map;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;


import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import hanbury.colin.lighthouse.R;
import screens.common.toolbar.ToolbarView;
import screens.common.view.BaseObservableView;
import screens.common.viewfactory.LightHouseViewFactory;

public class MapsView extends BaseObservableView<IMapsView.Listener> implements IMapsView,
        OnMapReadyCallback {

    private final ProgressBar mProgressBar;
    private final Toolbar mToolbar;
    private final ToolbarView mToolbarView;
    private final MapView mMapView;
    private GoogleMap mGoogleMap;
    private final String TAG = "MapsView";


    public MapsView(LayoutInflater inflater, ViewGroup parent, LightHouseViewFactory lightHouseViewFactory) {

        setRootView(inflater.inflate(R.layout.fragment_map, parent, false));
        mProgressBar = findViewById(R.id.map_progress);
        mMapView = findViewById(R.id.map_view);
        if (mMapView != null) {
            mMapView.getMapAsync(this);
        }
        mToolbar = findViewById(R.id.toolbar_widget);
        mToolbarView = lightHouseViewFactory.getToolbarView(mToolbar);
        initToolbar();
    }

    private void initToolbar() {
        mToolbar.addView(mToolbarView.getRootView());

        mToolbarView.setTitle(getString(R.string.title_map));

        mToolbarView.enableBackButtonAndListen(new ToolbarView.BackClickListener() {
            @Override
            public void onBackClicked() {
                for (Listener listener : getListeners()) {
                    listener.onBackClicked();
                }
            }
        });
        mToolbarView.enableLogoutButtonAndListen(new ToolbarView.LogoutClickListener() {
            @Override
            public void onLogoutClicked() {
                for (Listener listener : getListeners()) {
                    listener.onLogoutClicked();
                }
            }
        });
    }

    @Override
    public void goToCurrentLocation(LatLng location) {
        if(mGoogleMap !=null){
            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(location));
            CameraUpdate zoom = CameraUpdateFactory.zoomTo(10);
            mGoogleMap.animateCamera(zoom);
        }
    }

    @Override
    public void showProgressIndication() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressIndication() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG);
    }

    @Override
    public MapView getMapView() {
        return mMapView;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.i(TAG, "map ready");
        mGoogleMap = googleMap;
        LatLng galway = new LatLng(53.272315, -9.053389);
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(galway));
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(galway, 13);
        mGoogleMap.animateCamera(cameraUpdate);
    }
}
