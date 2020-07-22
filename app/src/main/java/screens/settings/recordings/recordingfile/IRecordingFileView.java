package screens.settings.recordings.recordingfile;

import data.recordings.RecordingFile;
import screens.common.view.IBaseObservableView;

public interface IRecordingFileView extends IBaseObservableView<IRecordingFileView.Listener> {
    interface Listener {
        void onRecordingFileClicked(RecordingFile recordingFile);
    }

    void bindRecordingFile(RecordingFile recordingFile);
}


