package screens.settings.recordings.saved;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.widget.Toolbar;

import data.recordings.RecordingFile;
import hanbury.colin.lighthouse.R;
import screens.common.toolbar.ToolbarView;
import screens.common.view.BaseObservableView;
import screens.common.viewfactory.LightHouseViewFactory;
import screens.settings.ISettingsView;
import screens.settings.recordings.RecordingsRecyclerAdapter;

public class SavedRecordingsView extends BaseObservableView<ISavedRecordingsView.Listener>
        implements ISavedRecordingsView, RecordingsRecyclerAdapter.Listener {

    private final ToolbarView mToolbarView;
    private final Toolbar mToolbar;

    public SavedRecordingsView(LayoutInflater inflater, ViewGroup parent,
                               LightHouseViewFactory lightHouseViewFactory){
        setRootView(inflater.inflate(R.layout.fragment_saved_recordings, parent, false));
        mToolbar = findViewById(R.id.toolbar_widget);
        mToolbarView = lightHouseViewFactory.getToolbarView(parent);
        initToolbar();
    }

    private void initToolbar() {
        mToolbar.addView(mToolbarView.getRootView());

        mToolbarView.setTitle(getString(R.string.title_saved_recordings));

        mToolbarView.enableBackButtonAndListen(new ToolbarView.BackClickListener() {
            @Override
            public void onBackClicked() {
                for (Listener listener: getListeners()){
                    listener.onBackClicked();
                }
            }
        });

        mToolbarView.enableLogoutButtonAndListen(new ToolbarView.LogoutClickListener() {
            @Override
            public void onLogoutClicked() {
                for (Listener listener: getListeners()){
                    listener.onLogoutClicked();
                }
            }
        });
    }

    @Override
    public void showProgressIndication() {

    }

    @Override
    public void hideProgressIndication() {

    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG);
    }

    @Override
    public void onRecordingFileClicked(RecordingFile recordingFile) {
        for(Listener listener: getListeners()){
            listener.onRecordingFileClicked(recordingFile);
        }
    }
}
