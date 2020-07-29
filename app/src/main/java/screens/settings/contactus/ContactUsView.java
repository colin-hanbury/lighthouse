package screens.settings.contactus;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import hanbury.colin.lighthouse.R;
import screens.common.toolbar.ToolbarView;
import screens.common.view.BaseObservableView;
import screens.common.viewfactory.LightHouseViewFactory;

public class ContactUsView extends BaseObservableView<IContactUsView.Listener>
        implements IContactUsView {

    private final Toolbar mToolbar;
    private final ToolbarView mToolbarView;
    private final Button mSendFeedbackButton;

    public ContactUsView(LayoutInflater inflater, ViewGroup parent,
                         LightHouseViewFactory lightHouseViewFactory){
        setRootView(inflater.inflate(R.layout.fragment_contact_us, parent, false));

        mSendFeedbackButton = findViewById(R.id.contact_feedback_button);
        mToolbar = findViewById(R.id.toolbar_widget);
        mToolbarView = lightHouseViewFactory.getToolbarView(parent);
        initToolbar();
    }

    private void initToolbar() {
        mToolbar.addView(mToolbarView.getRootView());

        mToolbarView.setTitle(getString(R.string.title_contact_us));

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

    }

    @Override
    public void hideProgressIndication() {

    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG);
    }
}
