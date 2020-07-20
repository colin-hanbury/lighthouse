package screens.settings.notifications;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import screens.common.controllers.BaseFragment;
import screens.common.navigation.screennavigation.ScreensNavigator;


public class NotificationsFragment extends BaseFragment implements  INotificationsView.Listener {


    private INotificationsView mINotificationsView;
    private ScreensNavigator mScreensNavigator;

    public static NotificationsFragment newInstance() {
        NotificationsFragment fragment = new NotificationsFragment();
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        mINotificationsView.registerListener(this);
    }

    @Override
    public void onStop() {
        mINotificationsView.unregisterListener(this);
        super.onStop();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mINotificationsView = getCompositionRoot().getLightHouseViewFactory().getNotificationsView(container);
        mScreensNavigator = getCompositionRoot().getScreensNavigator();
        return mINotificationsView.getRootView();
    }

    @Override
    public void onBackClicked() {
        mScreensNavigator.navigateBack();
    }

    @Override
    public void onLogoutClicked() {

    }
}