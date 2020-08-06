package screens.settings.recordings.view;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import data.recordings.Recording;
import hanbury.colin.lighthouse.R;
import screens.common.controllers.BaseFragment;


public class ViewRecordingFragment extends BaseFragment {


    private final Recording mRecording;

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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_recording, container, false);
    }
}