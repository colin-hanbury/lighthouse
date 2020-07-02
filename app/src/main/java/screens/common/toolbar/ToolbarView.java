package screens.common.toolbar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import hanbury.colin.networking.R;
import screens.common.view.BaseView;

public class ToolbarView extends BaseView {

    public interface BackClickListener {
        void onBackClicked();
    }

    public interface LogoutClickListener {
        void onLogoutClicked();
    }

    private final TextView mTxtTitle;
    private final ImageButton mBackButton;
    private final Button mLogoutButton;

    private BackClickListener mBackClickListener;
    private LogoutClickListener mLogoutClickListener;

    public ToolbarView(LayoutInflater inflater, ViewGroup parent) {
        setRootView(inflater.inflate(R.layout.toolbar, parent, false));
        mTxtTitle = findViewById(R.id.txt_toolbar_title);
        mLogoutButton = findViewById(R.id.logout_button);
        mBackButton = findViewById(R.id.back_button);
        mLogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLogoutClickListener.onLogoutClicked();
            }
        });
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBackClickListener.onBackClicked();
            }
        });
    }
    public void enableBackButtonAndListen(BackClickListener backClickListener) {
        this.mBackClickListener = backClickListener;
        mBackButton.setVisibility(View.VISIBLE);
    }

    public void enableLogoutButtonAndListen(LogoutClickListener logoutClickListener) {
        this.mLogoutClickListener = logoutClickListener;
        mLogoutButton.setVisibility(View.VISIBLE);
    }

    public void setTitle(String title) {
        mTxtTitle.setText(title);
    }
}