package screens.settings.recordings.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;

import data.recordings.Recording;
import networking.fetch.recordings.FetchRecentRecording;
import networking.logout.PostLogout;
import screens.common.controllers.BaseFragment;
import screens.common.navigation.screennavigation.ScreensNavigator;


public class ViewRecordingFragment extends BaseFragment implements IViewRecordingView.Listener,
        PostLogout.Listener, FetchRecentRecording.Listener {

    private final Recording mRecording;
    private IViewRecordingView mIViewRecordingView;
    private ScreensNavigator mScreenNavigator;
    private PostLogout mPostLogout;
    private boolean isPlaying = false;
    private FetchRecentRecording mFetchRecentRecording;

    public ViewRecordingFragment(Recording recording) {
        mRecording = recording;
    }

    public static ViewRecordingFragment newInstance(Recording recording) {
        ViewRecordingFragment fragment = new ViewRecordingFragment(recording);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mIViewRecordingView = getCompositionRoot().getLightHouseViewFactory()
                .getViewRecordingView(container, mRecording);
        mPostLogout = getCompositionRoot().getPostLogout();
        mScreenNavigator = getCompositionRoot().getScreensNavigator();
        mFetchRecentRecording = getCompositionRoot().getFetchRecentRecording();
        return mIViewRecordingView.getRootView();
    }

    @Override
    public void onStart() {
        super.onStart();
        mIViewRecordingView.registerListener(this);
        mPostLogout.registerListener(this);
        mFetchRecentRecording.registerListener(this);
        fetchRecording();
    }

    private void fetchRecording() {
        mIViewRecordingView.showProgressIndication();
        mFetchRecentRecording.tryFetchRecentRecordingAndNotify(mRecording.getTitle(), getContext());
    }

    @Override
    public void onStop() {
        mIViewRecordingView.unregisterListener(this);
        mPostLogout.unregisterListener(this);
        mFetchRecentRecording.unregisterListener(this);
        super.onStop();
    }

    @Override
    public void onBackClicked() {
        mScreenNavigator.navigateBack();
    }

    @Override
    public void onLogoutClicked() {
        mPostLogout.tryLogoutAndNotify();
    }

    @Override
    public void onPlayPauseClicked() {
        if(isPlaying){
            mIViewRecordingView.pause();
            isPlaying = false;
        }
        else {
            mIViewRecordingView.play();
            isPlaying = true;
        }
    }

    @Override
    public void onLogoutSuccess() {
        mScreenNavigator.toLoginScreen();
    }

    @Override
    public void onLogoutFailure(String error) {
        mIViewRecordingView.showToast(error);
    }

    @Override
    public void onFetchRecentRecordingSuccess(String filePath) {
        mRecording.setFilePath(filePath);
        mIViewRecordingView.setRecordingPath(filePath);
        mIViewRecordingView.hideProgressIndication();
    }

    @Override
    public void onFetchRecentRecordingFailure(String error) {
        mIViewRecordingView.hideProgressIndication();
        mIViewRecordingView.showToast(error);
    }
}