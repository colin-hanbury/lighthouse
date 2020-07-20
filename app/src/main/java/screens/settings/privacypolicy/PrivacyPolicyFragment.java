package screens.settings.privacypolicy;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import screens.common.controllers.BaseFragment;
import screens.common.navigation.screennavigation.ScreensNavigator;


public class PrivacyPolicyFragment extends BaseFragment implements IPrivacyPolicyView.Listener {

    private IPrivacyPolicyView mIPrivacyPolicyView;
    private ScreensNavigator mScreensNavigator;

    public static PrivacyPolicyFragment newInstance() {
        PrivacyPolicyFragment fragment = new PrivacyPolicyFragment();
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        mIPrivacyPolicyView.registerListener(this);
    }

    @Override
    public void onStop() {
        mIPrivacyPolicyView.unregisterListener(this);
        super.onStop();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mIPrivacyPolicyView = getCompositionRoot().getLightHouseViewFactory()
                .getPrivacyPolicyView(container);
        mScreensNavigator = getCompositionRoot().getScreensNavigator();
        return mIPrivacyPolicyView.getRootView();
    }

    @Override
    public void onBackClicked() {
        mScreensNavigator.navigateBack();
    }

    @Override
    public void onLogoutClicked() {

    }
}