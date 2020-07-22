package screens.settings.recordings.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import data.recordings.RecordingFile;
import hanbury.colin.lighthouse.R;
import screens.common.controllers.BaseFragment;


public class ViewRecordingFragment extends BaseFragment {


    private final RecordingFile mRecordingFile;

    public ViewRecordingFragment(RecordingFile recordingFile) {
        mRecordingFile = recordingFile;
    }

    public static ViewRecordingFragment newInstance(RecordingFile recordingFile) {
        ViewRecordingFragment fragment = new ViewRecordingFragment(recordingFile);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_recording, container, false);
    }
}