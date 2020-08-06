package screens.settings.recordings.view;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import data.recordings.Recording;
import hanbury.colin.lighthouse.R;
import screens.common.controllers.BaseFragment;
import screens.settings.recordings.recent.RecentRecordingsFragment;


public class ViewRecordingFragment extends BaseFragment implements IViewRecordingView.Listener {

    private final Recording mRecording;
    private IViewRecordingView mIViewRecordingView;

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
                .getViewRecordingView(container);

        return mIViewRecordingView.getRootView();
    }

    @Override
    public void onBackClicked() {

    }

    @Override
    public void onLogoutClicked() {

    }

    @Override
    public void onPlayPauseClicked() {

    }
}