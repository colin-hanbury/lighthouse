package screens.settings.recordings.recent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import data.recordings.Recording;
import hanbury.colin.lighthouse.R;
import screens.common.toolbar.ToolbarView;
import screens.common.view.BaseObservableView;
import screens.common.viewfactory.LightHouseViewFactory;
import screens.settings.SettingsRecyclerAdapter;
import screens.settings.recordings.RecordingsRecyclerAdapter;

public class RecentRecordingsView extends BaseObservableView<IRecentRecordingsView.Listener>
        implements IRecentRecordingsView, RecordingsRecyclerAdapter.Listener {

    private final Toolbar mToolbar;
    private final ToolbarView mToolbarView;
    private final RecordingsRecyclerAdapter mAdapter;
    private final ProgressBar mProgressBar;
    private final RecyclerView mRecyclerView;

    public RecentRecordingsView(LayoutInflater inflater, ViewGroup parent,
                                LightHouseViewFactory lightHouseViewFactory){
        setRootView(inflater.inflate(R.layout.fragment_recent_recordings, parent, false));
        mProgressBar = findViewById(R.id.recent_recordings_progress);
        mRecyclerView = findViewById(R.id.recent_recordings_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new RecordingsRecyclerAdapter(this, lightHouseViewFactory);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        mToolbar = findViewById(R.id.toolbar_widget);
        mToolbarView = lightHouseViewFactory.getToolbarView(parent);
        initToolbar();
    }

    private void initToolbar() {
        mToolbar.addView(mToolbarView.getRootView());

        mToolbarView.setTitle(getString(R.string.title_recent_recordings));

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
    public void bindRecentRecordings(List<Recording> recordings) {
        mAdapter.bindRecordings(recordings);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG);
    }

    @Override
    public void onRecordingFileClicked(Recording recording) {
        for (Listener listener : getListeners()){
            listener.onRecentRecordingPreviewClicked(recording);
        }
    }
}
