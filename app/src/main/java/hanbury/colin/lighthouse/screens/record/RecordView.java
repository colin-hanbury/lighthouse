package hanbury.colin.lighthouse.screens.record;

import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ProgressBar;
import android.widget.Toolbar;

import hanbury.colin.lighthouse.R;
import hanbury.colin.lighthouse.screens.common.LightHouseViewFactory;
import hanbury.colin.lighthouse.screens.common.toolbar.ToolbarView;
import hanbury.colin.lighthouse.screens.common.view.BaseObservableView;

public class RecordView extends BaseObservableView<IRecordView> implements IRecordView {

    private final ProgressBar mProgressBar;
    private final SurfaceView mSurfaceView;
    private final Toolbar mToolbar;
    private final ToolbarView mToolbarView;

    public RecordView(LayoutInflater inflater, ViewGroup parent, LightHouseViewFactory lightHouseViewFactory) {

        setRootView(inflater.inflate(R.layout.fragment_record, parent, false));
        mProgressBar = findViewById(R.id.recordProgressBar);
        mSurfaceView = findViewById(R.id.recordSurfaceView);

        mToolbar = findViewById(R.id.toolbar_widget);

        mToolbarView = lightHouseViewFactory.getToolbarView(mToolbar);

        initToolbar();
    }


    private void initToolbar() {
        mToolbar.addView(mToolbarView.getRootView());

        mToolbarView.setTitle(getString(R.string.title_record));

        mToolbarView.enableBackButtonAndListen(new ToolbarView.BackClickListener() {
            @Override
            public void onBackClicked() {
                for (Listener listener : getListeners()) {
                    listener.onNavigateUpClicked();
                }
            }
        });
        mToolbarView.enableLogoutButtonAndListen(new ToolbarView.LogoutClickListener() {
            @Override
            public void onLogoutClicked() {
                for (Listener listener : getListeners()) {
                    listener.onNavigateUpClicked();
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

}
