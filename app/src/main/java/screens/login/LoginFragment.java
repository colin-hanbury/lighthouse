package screens.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import data.login.LoginDetails;
import networking.login.FetchLogin;
import networking.login.FetchRegistration;
import screens.common.controllers.BaseFragment;
import screens.common.navigation.screennavigation.ScreensNavigator;


public class LoginFragment extends BaseFragment implements ILoginView.Listener,
        FetchLogin.Listener, FetchRegistration.Listener {

    private ScreensNavigator mScreensNavigator;
    private FetchLogin mFetchLogin;
    private FetchRegistration mFetchRegistration;

    public static LoginFragment newInstance(){
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    private ILoginView mILoginView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mILoginView = getCompositionRoot().getLightHouseViewFactory().getLoginView(container);
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
        mILoginView.hideProgressIndication();
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
    public void onRegistrationFailure() {
        mILoginView.hideProgressIndication();
        //display error
    }
}
