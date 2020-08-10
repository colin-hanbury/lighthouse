package screens.settings.recordings.recordingpreview;

import data.recordings.Recording;
import screens.common.view.IBaseObservableView;

public interface IRecordingPreviewView extends IBaseObservableView<IRecordingPreviewView.Listener> {
    interface Listener {
        void onRecordingClicked(Recording recording);
    }

    void bindRecording(Recording recording);
}


