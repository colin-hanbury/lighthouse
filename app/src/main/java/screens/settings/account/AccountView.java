package screens.settings.account;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import hanbury.colin.lighthouse.R;
import screens.common.toolbar.ToolbarView;
import screens.common.view.BaseObservableView;
import screens.common.viewfactory.LightHouseViewFactory;

public class AccountView extends BaseObservableView<IAccountView.Listener>
        implements IAccountView {

    private final ToolbarView mToolbarView;
    private final Toolbar mToolbar;
    private final TextView mAccountTypeText;
    private final EditText mCurrentPasswordInput;
    private final EditText mNewPasswordInput;
    private final EditText mConfirmNewPasswordInput;
    private final EditText mBackUpEmailInput;
    private final Button mAccountUpgradeButton;
    private final Button mChangePasswordButton;
    private final Button mTwoFactorAuthButton;
    private final Button mBackUpEmailButton;
    private final Button mDeleteAccountButton;

    public AccountView(LayoutInflater inflater, ViewGroup parent,
                       LightHouseViewFactory lightHouseViewFactory){
        setRootView(inflater.inflate(R.layout.fragment_account, parent, false));
        mAccountTypeText = findViewById(R.id.account_type_text);
        mCurrentPasswordInput = findViewById(R.id.current_password_input);
        mNewPasswordInput = findViewById(R.id.new_password_input);
        mConfirmNewPasswordInput = findViewById(R.id.confirm_new_password_input);
        mAccountUpgradeButton = findViewById(R.id.account_upgrade_button);
        mChangePasswordButton = findViewById(R.id.change_password_button);
        mTwoFactorAuthButton = findViewById(R.id.set_two_factor_auth_button);
        mBackUpEmailInput = findViewById(R.id.set_backup_email_input);
        mBackUpEmailButton = findViewById(R.id.set_backup_email_button);
        mDeleteAccountButton = findViewById(R.id.delete_account_button);
        mToolbar = findViewById(R.id.toolbar_widget);
        mToolbarView = lightHouseViewFactory.getToolbarView(parent);
        initToolbar();
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
