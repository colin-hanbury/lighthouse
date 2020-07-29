package screens.settings.termsofuse;

import android.os.Bundle;

import androidx.annotation.NonNull;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import networking.logout.PostLogout;
import screens.common.controllers.BaseFragment;
import screens.common.navigation.screennavigation.ScreensNavigator;


public class TermsOfUseFragment extends BaseFragment
        implements ITermsOfUseView.Listener, PostLogout.Listener {

    private ScreensNavigator mScreensNavigator;
    private ITermsOfUseView mITermsOfUseView;
    private PostLogout mPostLogout;

    public static TermsOfUseFragment newInstance() {
        TermsOfUseFragment fragment = new TermsOfUseFragment();
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        mITermsOfUseView.registerListener(this);
        mPostLogout.registerListener(this);
    }

    @Override
    public void onStop() {
        mITermsOfUseView.unregisterListener(this);
        mPostLogout.registerListener(this);
        super.onStop();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container,
                             @NonNull Bundle savedInstanceState) {
        mITermsOfUseView = getCompositionRoot().getLightHouseViewFactory()
                .getTermsOfUseView(container);
        mPostLogout = getCompositionRoot().getPostLogout();
        mScreensNavigator = getCompositionRoot().getScreensNavigator();
        return mITermsOfUseView.getRootView();
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
        mITermsOfUseView.showToast(error);
    }
}