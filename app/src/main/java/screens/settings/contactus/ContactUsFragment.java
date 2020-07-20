package screens.settings.contactus;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hanbury.colin.lighthouse.R;
import screens.common.controllers.BaseFragment;
import screens.common.navigation.screennavigation.ScreensNavigator;

public class ContactUsFragment extends BaseFragment implements IContactUsView.Listener {


    private IContactUsView mIContactUsView;
    private ScreensNavigator mScreensNavigator;

    public static ContactUsFragment newInstance() {
        ContactUsFragment fragment = new ContactUsFragment();
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        mIContactUsView.registerListener(this);
    }

    @Override
    public void onStop() {
        mIContactUsView.unregisterListener(this);
        super.onStop();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mIContactUsView = getCompositionRoot().getLightHouseViewFactory()
                .getContactUsView(container);
        mScreensNavigator = getCompositionRoot().getScreensNavigator();
        return mIContactUsView.getRootView();
    }

    @Override
    public void onBackClicked() {
        mScreensNavigator.navigateBack();
    }

    @Override
    public void onLogoutClicked() {

    }
}