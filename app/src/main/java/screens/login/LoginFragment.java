package screens.login;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import data.login.LoginDetails;
import data.user.User;
import networking.login.FetchLogin;
import networking.login.FetchRegistration;
import screens.common.controllers.BaseFragment;
import screens.common.navigation.bottomnavigation.BottomNavigationBar;
import screens.common.navigation.bottomnavigation.BottomNavigationBarHelper;
import screens.common.navigation.bottomnavigation.IBottomNavigationBar;
import screens.common.navigation.screennavigation.ScreensNavigator;


public class LoginFragment extends BaseFragment implements ILoginView.Listener,
        FetchLogin.Listener, FetchRegistration.Listener {

    private final String TAG = "Login Fragment";
    private ScreensNavigator mScreensNavigator;
    private FetchLogin mFetchLogin;
    private FetchRegistration mFetchRegistration;
    private IBottomNavigationBar mIBottomNavigationBar;
    private BottomNavigationBarHelper mNavBarHelper;

    public static LoginFragment newInstance(){
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    private ILoginView mILoginView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mILoginView = getCompositionRoot().getLightHouseViewFactory().getLoginView(container);
        mNavBarHelper =getCompositionRoot().getNavBarHelper();
        mScreensNavigator = getCompositionRoot().getScreensNavigator();
        mFetchLogin = getCompositionRoot().getFetchLogin();
        mFetchRegistration = getCompositionRoot().getFetchRegistration();
        return mILoginView.getRootView();
    }

    @Override
    public void onStart() {
        super.onStart();
        mILoginView.registerListener(this);
        mFetchLogin.registerListener(this);
        mFetchRegistration.registerListener(this);
        mNavBarHelper.hideNavBar();
    }

    @Override
    public void onStop() {
        mFetchRegistration.unregisterListener(this);
        mFetchLogin.unregisterListener(this);
        mILoginView.unregisterListener(this);
        super.onStop();
    }

    @Override
    public void onBackClicked() {
        mScreensNavigator.navigateBack();
    }

    @Override
    public void onLoginClicked(LoginDetails loginDetails) {
        mFetchLogin.tryLoginAndNotify(loginDetails);
        mILoginView.showProgressIndication();
    }

    @Override
    public void onRegisterClicked(LoginDetails loginDetails) {
        mFetchRegistration.tryRegistrationAndNotify(loginDetails);
        mILoginView.showProgressIndication();
    }

    @Override
    public void onLoginSuccess() {
        Log.i(TAG, User.getUserInstance().getEmail());
        mILoginView.hideProgressIndication();
        mNavBarHelper.showNavBar();
        mScreensNavigator.toMapsScreen();
    }

    @Override
    public void onLoginFailure(String error) {
        //display error
        mILoginView.hideProgressIndication();
    }

    @Override
    public void onRegistrationSuccess() {
        mILoginView.hideProgressIndication();
        mScreensNavigator.toMapsScreen();

    }

    @Override
    public void onRegistrationFailure(String error) {
        mILoginView.hideProgressIndication();
        //display error
    }
}
