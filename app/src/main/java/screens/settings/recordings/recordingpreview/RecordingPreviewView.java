package screens.settings.recordings.recordingpreview;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import data.recordings.Recording;
import hanbury.colin.lighthouse.R;
import screens.common.view.BaseObservableView;

public class RecordingPreviewView extends BaseObservableView<IRecordingPreviewView.Listener>
        implements IRecordingPreviewView{

    private Recording mRecording;
    private TextView mTitle;
    private TextView mDate;

    public RecordingPreviewView(LayoutInflater inflater, @Nullable ViewGroup parent) {
        setRootView(inflater.inflate(R.layout.recording_file, parent, false));
        mTitle = findViewById(R.id.recording_file_title);
        mDate = findViewById(R.id.recording_file_date);
        getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (Listener listener : getListeners()) {
                    listener.onRecordingFileClicked(mRecording);
                }
            }
        });
    }


    @Override
    public void bindRecordingFile(Recording recording) {
        mRecording = recording;
        mTitle.setText(mRecording.getTitle());
        mDate.setText(mRecording.getDate());
    }
}
