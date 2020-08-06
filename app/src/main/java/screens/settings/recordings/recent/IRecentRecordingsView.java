package screens.settings.recordings.recent;

import java.util.List;

import data.recordings.Recording;
import screens.common.view.IBaseObservableView;

public interface IRecentRecordingsView extends IBaseObservableView<IRecentRecordingsView.Listener> {



    interface Listener{
        void onBackClicked();
        void onLogoutClicked();
        void onRecentRecordingPreviewClicked(String recordingTitle);
    }

    void showProgressIndication();

    void hideProgressIndication();

    void bindRecordings(List<String> recordingTitles);

    void showToast(String message);
}
