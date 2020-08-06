package screens.settings.recordings.recent;

import android.os.Bundle;

import androidx.annotation.NonNull;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import data.recordings.Recording;
import networking.fetch.recordings.FetchRecentRecordingTitles;
import networking.logout.PostLogout;
import screens.common.controllers.BaseFragment;
import screens.common.navigation.screennavigation.ScreensNavigator;

public class RecentRecordingsFragment extends BaseFragment
        implements IRecentRecordingsView.Listener, PostLogout.Listener,
        FetchRecentRecordingTitles.Listener {

    private IRecentRecordingsView mIRecentRecordingsView;
    private ScreensNavigator mScreensNavigator;
    private PostLogout mPostLogout;
    private FetchRecentRecordingTitles mFetchTitles;

    public static RecentRecordingsFragment newInstance() {
        RecentRecordingsFragment fragment = new RecentRecordingsFragment();
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        mIRecentRecordingsView.registerListener(this);
        mPostLogout.registerListener(this);
        mFetchTitles.registerListener(this);
        fetchTitles();
    }

    private void fetchTitles() {
        mIRecentRecordingsView.showProgressIndication();
        mFetchTitles.tryFetchTitlesAndNotify();
    }

    @Override
    public void onStop() {
        mIRecentRecordingsView.unregisterListener(this);
        mPostLogout.unregisterListener(this);
        mFetchTitles.unregisterListener(this);
        super.onStop();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container,
                             @NonNull Bundle savedInstanceState) {
        mIRecentRecordingsView = getCompositionRoot().getLightHouseViewFactory()
                .getRecentRecordingsView(container);
        mPostLogout = getCompositionRoot().getPostLogout();
        mFetchTitles = getCompositionRoot().getFetchRecentRecordingTitles();
        mScreensNavigator = getCompositionRoot().getScreensNavigator();
        return mIRecentRecordingsView.getRootView();
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
    public void onRecentRecordingPreviewClicked(Recording recording) {
        mScreensNavigator.toViewRecordingScreen(recording);
    }

    @Override
    public void onLogoutSuccess() {
        mScreensNavigator.toLoginScreen();
    }

    @Override
    public void onLogoutFailure(String error) {
        mIRecentRecordingsView.showToast(error);
    }

    @Override
    public void onFetchRecentRecordingsSuccess(List<Recording> recordings) {
        mIRecentRecordingsView.hideProgressIndication();
        mIRecentRecordingsView.bindRecordings(recordings);
    }

    @Override
    public void onFetchRecentRecordingsFailure(String error) {
        mIRecentRecordingsView.showToast(error);
    }
}