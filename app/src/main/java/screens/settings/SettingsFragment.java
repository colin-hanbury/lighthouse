package screens.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import data.settings.SettingsItem;
import networking.logout.PostLogout;
import screens.common.controllers.BaseFragment;
import screens.common.navigation.screennavigation.ScreensNavigator;

public class SettingsFragment extends BaseFragment implements ISettingsView.Listener,
                        PostLogout.Listener{

    private ScreensNavigator mScreensNavigator;
    private PostLogout mPostLogout;

    public static SettingsFragment newInstance(){
        SettingsFragment fragment = new SettingsFragment();
        return fragment;
    }

    private ISettingsView mISettingsView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mISettingsView = getCompositionRoot().getLightHouseViewFactory().getSettingsView(container);
        mPostLogout = getCompositionRoot().getPostLogout();
        mScreensNavigator = getCompositionRoot().getScreensNavigator();
        return mISettingsView.getRootView();
    }

    @Override
    public void onStart() {
        super.onStart();
        mISettingsView.registerListener(this);
        mPostLogout.registerListener(this);
    }

    @Override
    public void onStop() {
        mISettingsView.unregisterListener(this);
        mPostLogout.unregisterListener(this);
        super.onStop();
    }


    @Override
    public void onSettingItemClicked(SettingsItem settingsItem) {
        mScreensNavigator.toSettingsItemScreen(settingsItem);
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
        mISettingsView.showToast(error);
    }
}