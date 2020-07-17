package screens.common.navigation.screennavigation;


import android.accounts.Account;
import android.util.Log;

import data.settings.SettingsItem;
import screens.common.navigation.fragmenthelpers.FragmentFrameHelper;
import screens.login.ui.login.LoginFragment;
import screens.map.MapsFragment;
import screens.record.RecordFragment;
import screens.settings.SettingsFragment;
import screens.settings.privacypolicy.PrivacyPolicyFragment;

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
        mFragmentFrameHelper.replaceFragmentAndClearBackstack(SettingsFragment.newInstance());
    }


    public void toLoginScreen() {
        mFragmentFrameHelper.replaceFragmentAndClearBackstack(LoginFragment.newInstance());
    }

    public void navigateUp() {
        mFragmentFrameHelper.navigateUp();
    }

    public void toSettingsItemScreen(SettingsItem settingsItem) {
        switch (settingsItem.getTitle()){
            case "Account":
                Log.i(TAG, "navigating to Map screen");
                mFragmentFrameHelper.replaceFragment(MapsFragment.newInstance());
                break;
            case "Recent Recordings":
                mFragmentFrameHelper.replaceFragment(SettingsFragment.newInstance());
                break;
            case "Saved Recordings":
                mFragmentFrameHelper.replaceFragment(SettingsFragment.newInstance());
                break;
            case "Notifications":
                mFragmentFrameHelper.replaceFragment(SettingsFragment.newInstance());
                break;
            case "Contact Us":
                mFragmentFrameHelper.replaceFragment(SettingsFragment.newInstance());
                break;
            case "Terms of Use":
                mFragmentFrameHelper.replaceFragment(SettingsFragment.newInstance());
                break;
            case "Privacy Policy":
                mFragmentFrameHelper.replaceFragment(PrivacyPolicyFragment.newInstance());
                break;
        }
    }
}