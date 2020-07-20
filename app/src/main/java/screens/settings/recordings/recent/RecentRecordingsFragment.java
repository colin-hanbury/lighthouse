package screens.settings.recordings.recent;

import android.os.Bundle;

import androidx.annotation.NonNull;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import screens.common.controllers.BaseFragment;
import screens.common.navigation.screennavigation.ScreensNavigator;

public class RecentRecordingsFragment extends BaseFragment implements IRecentRecordingsView.Listener {

    private IRecentRecordingsView mIRecentRecordingsView;
    private ScreensNavigator mScreensNavigator;

    public static RecentRecordingsFragment newInstance() {
        RecentRecordingsFragment fragment = new RecentRecordingsFragment();
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        mIRecentRecordingsView.registerListener(this);
    }

    @Override
    public void onStop() {
        mIRecentRecordingsView.unregisterListener(this);
        super.onStop();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container,
                             @NonNull Bundle savedInstanceState) {
        mIRecentRecordingsView = getCompositionRoot().getLightHouseViewFactory()
                .getRecentRecordingsView(container);
        mScreensNavigator = getCompositionRoot().getScreensNavigator();
        return mIRecentRecordingsView.getRootView();
    }

    @Override
    public void onBackClicked() {
        mScreensNavigator.navigateBack();
    }

    @Override
    public void onLogoutClicked() {

    }
}