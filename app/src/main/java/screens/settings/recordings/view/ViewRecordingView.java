package screens.settings.recordings.view;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.widget.Toolbar;

import data.recordings.Recording;
import hanbury.colin.lighthouse.R;
import screens.common.toolbar.ToolbarView;
import screens.common.view.BaseObservableView;
import screens.common.viewfactory.LightHouseViewFactory;

import static hanbury.colin.lighthouse.R.drawable.ic_play_button;
import static hanbury.colin.lighthouse.R.drawable.ic_pause_button;

public class ViewRecordingView extends BaseObservableView<IViewRecordingView.Listener>
        implements IViewRecordingView {

    private final Toolbar mToolbar;
    private final ToolbarView mToolbarView;
    private final Recording mRecording;
    private final ProgressBar mProgressBar;
    private final VideoView mVideoView;
    private final ImageButton mPlayPauseButton;

    public ViewRecordingView(LayoutInflater inflater, ViewGroup parent,
                             LightHouseViewFactory lightHouseViewFactory, Recording recording){
        setRootView(inflater.inflate(R.layout.fragment_view_recording, parent, false));
        mRecording = recording;
        mProgressBar = findViewById(R.id.view_recording_progress);
        mVideoView = findViewById(R.id.view_recording_video_view);
        Uri uri = Uri.parse(recording.getUri());
        mVideoView.setVideoURI(uri);
        mPlayPauseButton = findViewById(R.id.view_recording_play_pause_button);
        mToolbar = findViewById(R.id.toolbar_widget);
        mToolbarView = lightHouseViewFactory.getToolbarView(parent);
        initToolbar();
        mPlayPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Listener listener : getListeners()){
                    listener.onPlayPauseClicked();
                }
            }
        });
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
    public void play() {
//        MediaController mediaController = new MediaController(getContext());
//        mediaController.setAnchorView(mVideoView);
//        mVideoView.setMediaController(mediaController);
        mVideoView.start();
        mProgressBar.setVisibility(View.GONE);
        mPlayPauseButton.setImageResource(ic_pause_button);
    }

    @Override
    public void pause() {
        mVideoView.pause();
        mPlayPauseButton.setImageResource(ic_play_button);
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
