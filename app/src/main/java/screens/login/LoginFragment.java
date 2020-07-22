package screens.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import screens.common.controllers.BaseFragment;
import screens.common.navigation.screennavigation.ScreensNavigator;


public class LoginFragment extends BaseFragment implements ILoginView.Listener {


    private ScreensNavigator mScreensNavigator;

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
        return mILoginView.getRootView();
    }

    @Override
    public void onStart() {
        super.onStart();
        mILoginView.registerListener(this);
    }

    @Override
    public void onStop() {
        mILoginView.unregisterListener(this);
        super.onStop();
    }

    @Override
    public void onBackClicked() {
        mScreensNavigator.navigateBack();

    }

    @Override
    public void onLoginClicked() {

    }

    @Override
    public void onRegisterClicked() {

    }
}
