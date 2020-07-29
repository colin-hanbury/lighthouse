package screens.settings.termsofuse;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import hanbury.colin.lighthouse.R;
import screens.common.toolbar.ToolbarView;
import screens.common.view.BaseObservableView;
import screens.common.viewfactory.LightHouseViewFactory;

public class TermsOfUseView extends BaseObservableView<ITermsOfUseView.Listener>
        implements ITermsOfUseView {

    private final Toolbar mToolbar;
    private final ToolbarView mToolbarView;

    public TermsOfUseView(LayoutInflater inflater, ViewGroup parent,
                          LightHouseViewFactory lightHouseViewFactory){
        setRootView(inflater.inflate(R.layout.fragment_terms_of_use, parent, false));

        mToolbar = findViewById(R.id.toolbar_widget);
        mToolbarView = lightHouseViewFactory.getToolbarView(parent);
        initToolbar();
    }

    private void initToolbar() {
        mToolbar.addView(mToolbarView.getRootView());

        mToolbarView.setTitle(getString(R.string.title_terms_of_use));

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

    @Override
    public void showToast(String message) {
        Toast.makeText(getContext(),message, Toast.LENGTH_LONG);
    }
}
