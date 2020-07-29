package screens.map;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.maps.model.LatLng;

import data.user.User;
import networking.logout.PostLogout;
import screens.common.controllers.BaseFragment;
import screens.common.navigation.screennavigation.ScreensNavigator;

public class MapsFragment extends BaseFragment implements IMapsView.Listener, PostLogout.Listener {

    private static String TAG = "MapsFragment";

    private ScreensNavigator mScreensNavigator;
    private PostLogout mPostLogout;

    public static MapsFragment newInstance(){
        Log.i(TAG, User.getUserInstance().getEmail());
        MapsFragment fragment = new MapsFragment();
        return fragment;
    }
    private IMapsView mIMapsView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mIMapsView = getCompositionRoot().getLightHouseViewFactory().getMapView(container);
        mIMapsView.getMapView().onCreate(savedInstanceState);
        mPostLogout = getCompositionRoot().getPostLogout();
        mScreensNavigator = getCompositionRoot().getScreensNavigator();
        return mIMapsView.getRootView();
    }
    private LatLng getLocation() {
        LatLng galway = new LatLng(53.282388, -9.049340);
        return galway;
    }

    @Override
    public void onStart() {
        super.onStart();
        mIMapsView.registerListener(this);
        mPostLogout.registerListener(this);
        mIMapsView.getMapView().onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mIMapsView.getMapView().onResume();
        mIMapsView.goToCurrentLocation(getLocation());
    }

    @Override
    public void onStop() {
        mIMapsView.unregisterListener(this);
        mPostLogout.unregisterListener(this);
        mIMapsView.getMapView().onStop();
        super.onStop();
    }


    @Override
    public void onPause() {
        super.onPause();
        mIMapsView.getMapView().onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mIMapsView.getMapView().onDestroy();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mIMapsView.getMapView().onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mIMapsView.getMapView().onLowMemory();
    }


    @Override
    public void onBackClicked() {
        mScreensNavigator.navigateBack();
    }

    @Override
    public void onLogoutClicked() {
        mPostLogout.tryLogoutAndNotify();
    }

    @Override
    public void onLogoutSuccess() {
        mScreensNavigator.toLoginScreen();
    }

    @Override
    public void onLogoutFailure(String error) {
        mIMapsView.showToast(error);
    }
}