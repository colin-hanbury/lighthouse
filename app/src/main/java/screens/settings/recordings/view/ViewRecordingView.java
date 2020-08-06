package screens.settings.recordings.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.appcompat.widget.Toolbar;

import hanbury.colin.lighthouse.R;
import screens.common.toolbar.ToolbarView;
import screens.common.view.BaseObservableView;
import screens.common.viewfactory.LightHouseViewFactory;

public class ViewRecordingView extends BaseObservableView<IViewRecordingView.Listener>
        implements IViewRecordingView {

    private final Toolbar mToolbar;
    private final ToolbarView mToolbarView;

    public ViewRecordingView(LayoutInflater inflater, ViewGroup parent,
                             LightHouseViewFactory lightHouseViewFactory){
        setRootView(inflater.inflate(R.layout.fragment_view_recording, parent, false));
        mToolbar = findViewById(R.id.toolbar_widget);
        mToolbarView = lightHouseViewFactory.getToolbarView(parent);
        initToolbar();
    }

    private void initToolbar() {

    }

    @Override
    public void showProgressIndication() {

    }

    @Override
    public void hideProgressIndication() {

    }

    @Override
    public void showToast(String message) {

    }
}
