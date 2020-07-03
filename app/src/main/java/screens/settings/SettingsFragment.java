package screens.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import screens.common.controllers.BaseFragment;

public class SettingsFragment extends BaseFragment implements ISettingsView.Listener{

    public static SettingsFragment newInstance(){ //String questionId) {
        //Bundle args = new Bundle();
        //args.putString(ARG_QUESTION_ID, questionId);
        SettingsFragment fragment = new SettingsFragment();
        //fragment.setArguments(args);
        return fragment;
    }

    private ISettingsView mISettingsView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
//            screenState = (ScreenState) savedInstanceState.getSerializable(SAVED_STATE_SCREEN_STATE);
        }
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mISettingsView = getCompositionRoot().getLightHouseViewFactory().getSettingsView(container);
        return mISettingsView.getRootView();
    }

    @Override
    public void onStart() {
        super.onStart();
        mISettingsView.registerListener(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        mISettingsView.unregisterListener(this);
    }


    @Override
    public void onBackClicked() {
//        mNavigator.navigateBack();
    }

    @Override
    public void onLogoutClicked() {
//        logout
    }
}