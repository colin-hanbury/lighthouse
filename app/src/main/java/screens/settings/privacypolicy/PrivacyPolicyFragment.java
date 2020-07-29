package screens.settings.privacypolicy;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import networking.logout.PostLogout;
import screens.common.controllers.BaseFragment;
import screens.common.navigation.screennavigation.ScreensNavigator;


public class PrivacyPolicyFragment extends BaseFragment implements IPrivacyPolicyView.Listener,
        PostLogout.Listener{

    private IPrivacyPolicyView mIPrivacyPolicyView;
    private ScreensNavigator mScreensNavigator;
    private PostLogout mPostLogout;

    public static PrivacyPolicyFragment newInstance() {
        PrivacyPolicyFragment fragment = new PrivacyPolicyFragment();
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        mIPrivacyPolicyView.registerListener(this);
        mPostLogout.registerListener(this);
    }

    @Override
    public void onStop() {
        mIPrivacyPolicyView.unregisterListener(this);
        mPostLogout.unregisterListener(this);
        super.onStop();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mIPrivacyPolicyView = getCompositionRoot().getLightHouseViewFactory()
                .getPrivacyPolicyView(container);
        mPostLogout = getCompositionRoot().getPostLogout();
        mScreensNavigator = getCompositionRoot().getScreensNavigator();
        return mIPrivacyPolicyView.getRootView();
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
        mIPrivacyPolicyView.showToast(error);
    }
}