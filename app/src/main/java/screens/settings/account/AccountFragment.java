package screens.settings.account;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hanbury.colin.lighthouse.R;
import screens.common.controllers.BaseFragment;
import screens.settings.ISettingsView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountFragment extends BaseFragment implements IAccountView.Listener {


    private IAccountView mIAccountView;

    public AccountFragment() {
        // Required empty public constructor
    }


    public static AccountFragment newInstance() {
        AccountFragment fragment = new AccountFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mIAccountView = getCompositionRoot().getLightHouseViewFactory().getAccountView(container);
//        mScreensNavigator = getCompositionRoot().getScreensNavigator();
        return mIAccountView.getRootView();
    }
}