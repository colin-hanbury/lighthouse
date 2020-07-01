package hanbury.colin.lighthouse.screens.map;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toolbar;

import hanbury.colin.lighthouse.R;
import hanbury.colin.lighthouse.screens.common.LightHouseViewFactory;
import hanbury.colin.lighthouse.screens.common.toolbar.ToolbarView;
import hanbury.colin.lighthouse.screens.common.view.BaseObservableView;

public class MapView extends BaseObservableView<IMapView> implements IMapView {

    private final ProgressBar mProgressBar;
    private final Toolbar mToolbar;
    private final ToolbarView mToolbarView;
//    private final Map mMapView;

    public MapView(LayoutInflater inflater, ViewGroup parent, LightHouseViewFactory lightHouseViewFactory) {

        setRootView(inflater.inflate(R.layout.fragment_map, parent, false));
        mProgressBar = findViewById(R.id.mapProgress);
        mMapView - findViewById(R.id.map);

        mToolbar = findViewById(R.id.toolbar_element);

        mToolbarView = lightHouseViewFactory.getToolbarView(mToolbar);

        initToolbar();
    }

    private void initToolbar() {
        mToolbar.addView(mToolbarView.getRootView());

        mToolbarView.setTitle(getString(R.string.title_map));

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
