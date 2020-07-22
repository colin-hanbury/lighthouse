package screens.settings.recordings.recent;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.appcompat.widget.Toolbar;

import hanbury.colin.lighthouse.R;
import screens.common.toolbar.ToolbarView;
import screens.common.view.BaseObservableView;
import screens.common.viewfactory.LightHouseViewFactory;

public class RecentRecordingsView extends BaseObservableView<IRecentRecordingsView.Listener>
        implements IRecentRecordingsView {

    private final Toolbar mToolbar;
    private final ToolbarView mToolbarView;

    public RecentRecordingsView(LayoutInflater inflater, ViewGroup parent,
                                LightHouseViewFactory lightHouseViewFactory){
        setRootView(inflater.inflate(R.layout.fragment_recent_recordings, parent, false));
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

    }

    @Override
    public void hideProgressIndication() {

    }
}
