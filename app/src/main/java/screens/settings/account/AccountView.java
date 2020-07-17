package screens.settings.account;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.Toolbar;

import hanbury.colin.lighthouse.R;
import screens.common.toolbar.ToolbarView;
import screens.common.view.BaseObservableView;
import screens.common.viewfactory.LightHouseViewFactory;

public class AccountView extends BaseObservableView<IAccountView.Listener>
        implements IAccountView{

    private final ToolbarView mToolbarView;
    private final Toolbar mToolbar;

    public AccountView(LayoutInflater inflater, ViewGroup parent,
                       LightHouseViewFactory lightHouseViewFactory){
        setRootView(inflater.inflate(R.layout.fragment_account, parent, false));

        mToolbar = findViewById(R.id.account_toolbar);
        mToolbarView = lightHouseViewFactory.getToolbarView(parent);
    }
}
