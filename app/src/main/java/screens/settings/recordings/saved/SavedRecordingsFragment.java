package screens.settings.recordings.saved;

import android.os.Bundle;

import androidx.annotation.NonNull;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import data.recordings.Recording;
import networking.logout.PostLogout;
import screens.common.controllers.BaseFragment;
import screens.common.navigation.screennavigation.ScreensNavigator;


public class SavedRecordingsFragment extends BaseFragment
        implements ISavedRecordingsView.Listener, PostLogout.Listener {


    private ISavedRecordingsView mISavedRecordings;
    private ScreensNavigator mScreensNavigator;
    private PostLogout mPostLogout;

    public static SavedRecordingsFragment newInstance() {
        SavedRecordingsFragment fragment = new SavedRecordingsFragment();
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        mISavedRecordings.registerListener(this);
        mPostLogout.registerListener(this);
    }

    @Override
    public void onStop() {
        mISavedRecordings.unregisterListener(this);
        mPostLogout.unregisterListener(this);
        super.onStop();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container,
                             @NonNull Bundle savedInstanceState) {
        mISavedRecordings = getCompositionRoot().getLightHouseViewFactory()
                .getSavedRecordingsView(container);
        mPostLogout = getCompositionRoot().getPostLogout();
        mScreensNavigator = getCompositionRoot().getScreensNavigator();
        return mISavedRecordings.getRootView();
    }

    @Override
    public void onRecordingFileClicked(Recording recording) {
        mScreensNavigator.toViewRecordingScreen(recording);
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
        mISavedRecordings.showToast(error);
    }
}