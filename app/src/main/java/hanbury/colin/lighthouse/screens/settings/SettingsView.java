package hanbury.colin.lighthouse.screens.settings;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toolbar;

import hanbury.colin.lighthouse.R;
import hanbury.colin.lighthouse.screens.common.LightHouseViewFactory;
import hanbury.colin.lighthouse.screens.common.toolbar.ToolbarView;
import hanbury.colin.lighthouse.screens.common.view.BaseObservableView;

public class SettingsView extends BaseObservableView<ISettingsView> implements ISettingsView {

    private final ProgressBar mProgressBar;
    private final Toolbar mToolbar;
    private final ToolbarView mToolbarView;

    public SettingsView(LayoutInflater inflater, ViewGroup parent, LightHouseViewFactory lightHouseViewFactory) {

        setRootView(inflater.inflate(R.layout.fragment_settings, parent, false));
        mProgressBar = findViewById(R.id.settingsProgress);


        mToolbar = findViewById(R.id.toolbar_widget);

        mToolbarView = lightHouseViewFactory.getToolbarView(mToolbar);

        initToolbar();
    }

    private void initToolbar() {
        mToolbar.addView(mToolbarView.getRootView());

        mToolbarView.setTitle(getString(R.string.title_settings));

        mToolbarView.enableUpButtonAndListen(new ToolbarView.NavigateUpClickListener() {
            @Override
            public void onNavigateUpClicked() {
                for (Listener listener : getListeners()) {
                    listener.onNavigateUpClicked();
                }
            }
        });
    }
}
