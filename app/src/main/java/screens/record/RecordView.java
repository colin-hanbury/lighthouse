package screens.record;

import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ProgressBar;

import androidx.appcompat.widget.Toolbar;

import hanbury.colin.networking.R;
import screens.common.toolbar.ToolbarView;
import screens.common.view.BaseObservableView;
import screens.common.viewfactory.LightHouseViewFactory;

public class RecordView extends BaseObservableView<IRecordView.Listener> implements IRecordView {

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

}
