package screens.common.navigation.screennavigation;


import android.util.Log;

import screens.common.navigation.fragmenthelpers.FragmentFrameHelper;
import screens.login.ui.login.LoginFragment;
import screens.map.MapsFragment;
import screens.record.RecordFragment;
import screens.settings.SettingsFragment;

public class ScreensNavigator {

    private FragmentFrameHelper mFragmentFrameHelper;
    private static  String TAG = "ScreenNavigation";

    public ScreensNavigator(FragmentFrameHelper fragmentFrameHelper) {
        mFragmentFrameHelper = fragmentFrameHelper;
    }

    public void toMapsScreen(){ //String questionId) {
        Log.i(TAG, "navigating to Map screen");
        mFragmentFrameHelper.replaceFragmentAndClearBackstack(MapsFragment.newInstance());
    }

    public void toRecordScreen() {
        Log.i(TAG, "navigating to Record screen");
        mFragmentFrameHelper.replaceFragment(RecordFragment.newInstance());
    }

    public void toSettingsScreen() {
        Log.i(TAG, "navigating to Settings screen");
        mFragmentFrameHelper.replaceFragment(new SettingsFragment());
    }


    public void toLoginScreen() {
        mFragmentFrameHelper.replaceFragmentAndClearBackstack(LoginFragment.newInstance());
    }

    public void navigateUp() {
        mFragmentFrameHelper.navigateUp();
    }

}