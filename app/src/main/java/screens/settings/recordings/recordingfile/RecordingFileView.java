package screens.settings.recordings.recordingfile;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import data.recordings.RecordingFile;
import hanbury.colin.lighthouse.R;
import screens.common.view.BaseObservableView;

public class RecordingFileView extends BaseObservableView<IRecordingFileView.Listener> implements IRecordingFileView{

    private RecordingFile mRecordingFile;
    private TextView mTitle;
    private TextView mDate;

    public RecordingFileView(LayoutInflater inflater, @Nullable ViewGroup parent) {
        setRootView(inflater.inflate(R.layout.recording_file, parent, false));
        mTitle = findViewById(R.id.recording_file_title);
        mDate = findViewById(R.id.recording_file_date);
        getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (Listener listener : getListeners()) {
                    listener.onRecordingFileClicked(mRecordingFile);
                }
            }
        });
    }


    @Override
    public void bindRecordingFile(RecordingFile recordingFile) {
        mRecordingFile = recordingFile;
        mTitle.setText(mRecordingFile.getTitle());
        mDate.setText(mRecordingFile.getDate());
    }
}
