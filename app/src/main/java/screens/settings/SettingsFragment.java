package screens.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import data.settings.SettingsItem;
import screens.common.controllers.BaseFragment;
import screens.common.navigation.screennavigation.ScreensNavigator;

public class SettingsFragment extends BaseFragment implements ISettingsView.Listener{

    private ScreensNavigator mScreensNavigator;

    public static SettingsFragment newInstance(){
        SettingsFragment fragment = new SettingsFragment();
        return fragment;
    }

    private ISettingsView mISettingsView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
//            screenState = (ScreenState) savedInstanceState.getSerializable(SAVED_STATE_SCREEN_STATE);
        }
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mISettingsView = getCompositionRoot().getLightHouseViewFactory().getSettingsView(container);
        mScreensNavigator = getCompositionRoot().getScreensNavigator();
        return mISettingsView.getRootView();
    }

    @Override
    public void onStart() {
        super.onStart();
        mISettingsView.registerListener(this);
    }

    @Override
    public void onStop() {
        mISettingsView.unregisterListener(this);
        super.onStop();
    }


    @Override
    public void onSettingItemClicked(SettingsItem settingsItem) {
        mISettingsView.showProgressIndication();
        mScreensNavigator.toSettingsItemScreen(settingsItem);
        mISettingsView.hideProgressIndication();

    }

    @Override
    public void onBackClicked() {
        mScreensNavigator.navigateBack();
    }

    @Override
    public void onLogoutClicked() {
//        logout
    }
}