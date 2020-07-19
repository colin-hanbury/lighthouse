package screens.settings.notifications;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ToggleButton;
import android.widget.Toolbar;

import hanbury.colin.lighthouse.R;
import screens.common.toolbar.ToolbarView;
import screens.common.view.BaseObservableView;
import screens.common.viewfactory.LightHouseViewFactory;
import screens.settings.account.IAccountView;

public class NotificationsView extends BaseObservableView<INotificationsView.Listener>
        implements IAccountView{

    private final ToggleButton mNearbyPoliceToggle;
    private final Toolbar mToolbar;
    private final ToolbarView mToolbarView;
    private final ToggleButton mPaymentDueToggle;

    public NotificationsView(LayoutInflater inflater, ViewGroup parent,
                             LightHouseViewFactory lightHouseViewFactory){
        setRootView(inflater.inflate(R.layout.fragment_notifications, parent, false));
        mNearbyPoliceToggle = findViewById(R.id.nearby_policy_toggle);
        mPaymentDueToggle = findViewById(R.id.payment_due_toggle);
        mToolbar = findViewById(R.id.toolbar_widget);
        mToolbarView = lightHouseViewFactory.getToolbarView(parent);
    }


    private void initToolbar() {
        mToolbar.addView(mToolbarView.getRootView());

        mToolbarView.setTitle(getString(R.string.title_account));

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
}
