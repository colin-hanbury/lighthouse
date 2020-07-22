package screens.login;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.widget.Toolbar;

import hanbury.colin.lighthouse.R;
import screens.common.toolbar.ToolbarView;
import screens.common.view.BaseObservableView;
import screens.common.viewfactory.LightHouseViewFactory;

public class LoginView extends BaseObservableView<ILoginView.Listener> implements ILoginView {

    private final Toolbar mToolbar;
    private final ToolbarView mToolbarView;
    private final Button mLoginButton;
    private final Button mRegisterButton;
    private final EditText mEmailField;
    private final EditText mPasswordField;
    private final ProgressBar mProgressBar;

    public LoginView(LayoutInflater inflater, ViewGroup container,
                     LightHouseViewFactory lightHouseViewFactory){
        setRootView(inflater.inflate(R.layout.fragment_login, container));
        mLoginButton = findViewById(R.id.login_button);
        mRegisterButton = findViewById(R.id.register_button);
        mEmailField = findViewById(R.id.email_input);
        mPasswordField = findViewById(R.id.password_input);
        mProgressBar = findViewById(R.id.login_progress);
        mToolbar = findViewById(R.id.toolbar_widget);
        mToolbarView = lightHouseViewFactory.getToolbarView(container);
        initToolbarAndButtons();
    }

    private void initToolbarAndButtons() {
        mToolbar.addView(mToolbarView.getRootView());
        mToolbarView.setTitle(getString(R.string.title_login));
        mToolbarView.enableBackButtonAndListen(new ToolbarView.BackClickListener() {
            @Override
            public void onBackClicked() {
                for (Listener listener: getListeners()){
                    listener.onBackClicked();
                }
            }
        });
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEnteredDetails();
                for (Listener listener: getListeners()){
                    listener.onLoginClicked();
                }
            }
        });
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEnteredDetails();
                for (Listener listener: getListeners()){
                    listener.onRegisterClicked();
                }
            }
        });
    }

    private void getEnteredDetails() {
        String email = mEmailField.getText().toString();
        String password = mPasswordField.getText().toString();
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
