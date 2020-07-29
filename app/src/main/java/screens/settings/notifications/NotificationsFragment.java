package screens.settings.notifications;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import networking.logout.PostLogout;
import screens.common.controllers.BaseFragment;
import screens.common.navigation.screennavigation.ScreensNavigator;


public class NotificationsFragment extends BaseFragment implements  INotificationsView.Listener,
        PostLogout.Listener {


    private INotificationsView mINotificationsView;
    private ScreensNavigator mScreensNavigator;
    private PostLogout mPostLogout;

    public static NotificationsFragment newInstance() {
        NotificationsFragment fragment = new NotificationsFragment();
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        mINotificationsView.registerListener(this);
        mPostLogout.registerListener(this);
    }

    @Override
    public void onStop() {
        mINotificationsView.unregisterListener(this);
        mPostLogout.unregisterListener(this);
        super.onStop();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mINotificationsView = getCompositionRoot().getLightHouseViewFactory()
                .getNotificationsView(container);
        mPostLogout = getCompositionRoot().getPostLogout();
        mScreensNavigator = getCompositionRoot().getScreensNavigator();
        return mINotificationsView.getRootView();
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
        mINotificationsView.showToast(error);
    }
}