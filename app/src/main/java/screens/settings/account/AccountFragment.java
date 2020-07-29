package screens.settings.account;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import networking.logout.PostLogout;
import screens.common.controllers.BaseFragment;
import screens.common.navigation.screennavigation.ScreensNavigator;


public class AccountFragment extends BaseFragment implements IAccountView.Listener,
        PostLogout.Listener {


    private IAccountView mIAccountView;
    private ScreensNavigator mScreensNavigator;
    private PostLogout mPostLogout;

    public static AccountFragment newInstance() {
        AccountFragment fragment = new AccountFragment();
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        mIAccountView.registerListener(this);
        mPostLogout.registerListener(this);
    }

    @Override
    public void onStop() {
        mIAccountView.unregisterListener(this);
        mPostLogout.unregisterListener(this);
        super.onStop();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mIAccountView = getCompositionRoot().getLightHouseViewFactory().getAccountView(container);
        mPostLogout = getCompositionRoot().getPostLogout();
        mScreensNavigator = getCompositionRoot().getScreensNavigator();
        return mIAccountView.getRootView();
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
        mIAccountView.showToast(error);
    }
}