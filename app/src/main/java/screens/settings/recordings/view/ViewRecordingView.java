package screens.settings.recordings.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import data.recordings.Recording;
import hanbury.colin.lighthouse.R;
import screens.common.toolbar.ToolbarView;
import screens.common.view.BaseObservableView;
import screens.common.viewfactory.LightHouseViewFactory;

public class ViewRecordingView extends BaseObservableView<IViewRecordingView.Listener>
        implements IViewRecordingView {

    private final Toolbar mToolbar;
    private final ToolbarView mToolbarView;
    private final Recording mRecording;
    private final ProgressBar mProgressBar;

    public ViewRecordingView(LayoutInflater inflater, ViewGroup parent,
                             LightHouseViewFactory lightHouseViewFactory, Recording recording){
        setRootView(inflater.inflate(R.layout.fragment_view_recording, parent, false));
        mRecording = recording;
        mProgressBar = findViewById(R.id.recent_recordings_progress);
        mToolbar = findViewById(R.id.toolbar_widget);
        mToolbarView = lightHouseViewFactory.getToolbarView(parent);
        initToolbar();
    }

    private void initToolbar() {
        mToolbar.addView(mToolbarView.getRootView());

        mToolbarView.setTitle(mRecording.getTitle());
        mToolbarView.setTitleTextSize(15);
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
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressIndication() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG);
    }
}
