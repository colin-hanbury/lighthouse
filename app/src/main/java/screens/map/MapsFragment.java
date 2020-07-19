package screens.map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import screens.common.controllers.BaseFragment;
import screens.common.navigation.screennavigation.ScreensNavigator;

public class MapsFragment extends BaseFragment implements IMapsView.Listener {

    private ScreensNavigator mScreensNavigator;

    public static MapsFragment newInstance(){

        MapsFragment fragment = new MapsFragment();
        return fragment;
    }
    private IMapsView mIMapsView;

    @Override
    public void onStart() {
        super.onStart();
        mIMapsView.registerListener(this);
        mIMapsView.getMapView().onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mIMapsView.getMapView().onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
        mIMapsView.unregisterListener(this);
        mIMapsView.getMapView().onStop();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mIMapsView = getCompositionRoot().getLightHouseViewFactory().getMapView(container);
        mIMapsView.getMapView().onCreate(savedInstanceState);
        mScreensNavigator = getCompositionRoot().getScreensNavigator();
        return mIMapsView.getRootView();
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

    }

}