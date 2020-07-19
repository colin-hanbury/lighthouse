package screens.settings.account;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import screens.common.controllers.BaseFragment;
import screens.common.navigation.screennavigation.ScreensNavigator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountFragment extends BaseFragment implements IAccountView.Listener {


    private IAccountView mIAccountView;
    private ScreensNavigator mScreensNavigator;

    public static AccountFragment newInstance() {
        AccountFragment fragment = new AccountFragment();
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        mIAccountView.registerListener(this);
    }

    @Override
    public void onStop() {
        mIAccountView.unregisterListener(this);
        super.onStop();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mIAccountView = getCompositionRoot().getLightHouseViewFactory().getAccountView(container);
        mScreensNavigator = getCompositionRoot().getScreensNavigator();
        return mIAccountView.getRootView();
    }

    @Override
    public void onBackClicked() {
        mScreensNavigator.navigateBack();
    }

    @Override
    public void onLogoutClicked() {
//        call logout
    }
}