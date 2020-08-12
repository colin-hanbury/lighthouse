package screens.settings.recordings.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import data.recordings.Recording;
import networking.logout.PostLogout;
import screens.common.controllers.BaseFragment;
import screens.common.navigation.screennavigation.ScreensNavigator;


public class ViewRecordingFragment extends BaseFragment implements IViewRecordingView.Listener,
        PostLogout.Listener {

    private final Recording mRecording;
    private IViewRecordingView mIViewRecordingView;
    private ScreensNavigator mScreenNavigator;
    private PostLogout mPostLogout;
    private boolean isPlaying = false;

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
        return mIViewRecordingView.getRootView();
    }

    @Override
    public void onStart() {
        super.onStart();
        mIViewRecordingView.registerListener(this);
        mPostLogout.registerListener(this);
    }

    @Override
    public void onStop() {
        mIViewRecordingView.unregisterListener(this);
        mPostLogout.unregisterListener(this);
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
            mIViewRecordingView.showProgressIndication();
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
}