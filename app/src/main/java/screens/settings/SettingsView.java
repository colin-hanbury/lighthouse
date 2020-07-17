package screens.settings;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import data.settings.SettingsItem;
import hanbury.colin.lighthouse.R;
import screens.common.toolbar.ToolbarView;
import screens.common.view.BaseObservableView;
import screens.common.viewfactory.LightHouseViewFactory;

    public class SettingsView extends BaseObservableView<ISettingsView.Listener>
            implements ISettingsView, SettingsRecyclerAdapter.Listener {

    private final ProgressBar mProgressBar;
    private final Toolbar mToolbar;
    private final ToolbarView mToolbarView;
    private final RecyclerView mRecyclerView;
    private final SettingsRecyclerAdapter mAdapter;

    public SettingsView(LayoutInflater inflater, ViewGroup parent, LightHouseViewFactory lightHouseViewFactory) {

        setRootView(inflater.inflate(R.layout.fragment_settings, parent, false));
        mProgressBar = findViewById(R.id.settings_progress);
        mRecyclerView = findViewById(R.id.settings_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new SettingsRecyclerAdapter(this,lightHouseViewFactory);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        mToolbar = findViewById(R.id.toolbar_widget);
        mToolbarView = lightHouseViewFactory.getToolbarView(mToolbar);

        initToolbar();
    }

    private void initToolbar() {
        mToolbar.addView(mToolbarView.getRootView());

        mToolbarView.setTitle(getString(R.string.title_settings));

        mToolbarView.enableBackButtonAndListen(new ToolbarView.BackClickListener() {
            @Override
            public void onBackClicked() {
                for (Listener listener : getListeners()) {
                    listener.onBackClicked();
                }
            }
        });
        mToolbarView.enableLogoutButtonAndListen(new ToolbarView.LogoutClickListener() {
            @Override
            public void onLogoutClicked() {
                for (Listener listener : getListeners()) {
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
    public void onItemClicked(SettingsItem item) {
        for(Listener listener: getListeners()){
            listener.onSettingItemClicked(item);
        }
    }
}
