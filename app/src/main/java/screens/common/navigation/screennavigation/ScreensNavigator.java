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
        mFragmentFrameHelper.replaceFragmentAndClearBackstack(MapsFragment.newInstance()); //questionId));
    }

    public void toRecordScreen() {
        Log.i(TAG, "navigating to Matches screen");
        mFragmentFrameHelper.replaceFragment(RecordFragment.newInstance()); //MatchesFragment.newInstance());
    }

    public void toSettingsScreen() {
        mFragmentFrameHelper.replaceFragment(new SettingsFragment()); //ProfileFragment.newInstance());
    }


    public void toLoginScreen() {
        mFragmentFrameHelper.replaceFragmentAndClearBackstack(LoginFragment.newInstance()); //LoginFragment.newInstance());
    }

    public void navigateUp() {
        mFragmentFrameHelper.navigateUp();
    }

}