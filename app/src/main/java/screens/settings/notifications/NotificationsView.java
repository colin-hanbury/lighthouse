package screens.settings.notifications;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.widget.Toolbar;

import hanbury.colin.lighthouse.R;
import screens.common.toolbar.ToolbarView;
import screens.common.view.BaseObservableView;
import screens.common.viewfactory.LightHouseViewFactory;

public class NotificationsView extends BaseObservableView<INotificationsView.Listener>
        implements INotificationsView{

    private final TextView mNearbyPolicTextView;
    private final TextView mPaymentDueTextView;
    private final Switch mNearbyPoliceSwitch;
    private final Switch mPaymentDueSwitch;
    private final Toolbar mToolbar;
    private final ToolbarView mToolbarView;


    public NotificationsView(LayoutInflater inflater, ViewGroup parent,
                             LightHouseViewFactory lightHouseViewFactory){
        setRootView(inflater.inflate(R.layout.fragment_notifications, parent, false));
        mNearbyPolicTextView = findViewById(R.id.notify_police_nearby_text);
        mPaymentDueTextView = findViewById(R.id.notify_payment_due_text);
        mNearbyPoliceSwitch = findViewById(R.id.notify_police_nearby_switch);
        mPaymentDueSwitch = findViewById(R.id.notify_payment_due_switch);
        mToolbar = findViewById(R.id.toolbar_widget);
        mToolbarView = lightHouseViewFactory.getToolbarView(parent);
        initToolbar();
    }


    private void initToolbar() {
        mToolbar.addView(mToolbarView.getRootView());

        mToolbarView.setTitle(getString(R.string.title_notifications));

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
