package screens.common.navigation.screennavigation;


import android.util.Log;

import data.settings.SettingsItem;
import screens.common.navigation.fragmenthelpers.FragmentFrameHelper;
import screens.login.ui.login.LoginFragment;
import screens.map.MapsFragment;
import screens.record.RecordFragment;
import screens.settings.SettingsFragment;
import screens.settings.account.AccountFragment;
import screens.settings.contactus.ContactUsFragment;
import screens.settings.notifications.NotificationsFragment;
import screens.settings.privacypolicy.PrivacyPolicyFragment;
import screens.settings.termsofuse.TermsOfUseFragment;

public class ScreensNavigator {

    private FragmentFrameHelper mFragmentFrameHelper;
    private static  String TAG = "ScreenNavigation";

    public ScreensNavigator(FragmentFrameHelper fragmentFrameHelper) {
        mFragmentFrameHelper = fragmentFrameHelper;
    }

    public void toMapsScreen() {
        Log.i(TAG, "navigating to Map screen");
        mFragmentFrameHelper.replaceFragmentAndClearBackstack(MapsFragment.newInstance());
    }

    public void toRecordScreen() {
        Log.i(TAG, "navigating to Record screen");
        mFragmentFrameHelper.replaceFragmentAndClearBackstack(RecordFragment.newInstance());
    }

    public void toSettingsScreen() {
        Log.i(TAG, "navigating to Settings screen");
        mFragmentFrameHelper.replaceFragmentAndClearBackstack(SettingsFragment.newInstance());
    }

    public void toAccountScreen() {
        Log.i(TAG, "navigating to Account screen");
        mFragmentFrameHelper.replaceFragment(AccountFragment.newInstance());
    }

    public void toRecentRecordingsScreen() {
        Log.i(TAG, "navigating to Recent Recordings screen");
        mFragmentFrameHelper.replaceFragment(RecentRecordingsFragment.newInstance());
    }


    public void toSavedRecordingsScreen() {
        Log.i(TAG, "navigating to Saved Recordings screen");
        mFragmentFrameHelper.replaceFragment(SavedRecordingsFragment.newInstance());
    }

    private void toNotificationsScreen() {
        Log.i(TAG, "navigating to Notifications screen");
        mFragmentFrameHelper.replaceFragment(NotificationsFragment.newInstance());
    }

    private void toContactUsScreen(){
        Log.i(TAG, "navigating to Contact Us screen");
        mFragmentFrameHelper.replaceFragment(ContactUsFragment.newInstance());
    }

    private void toTermsOfUseScreen() {
        Log.i(TAG, "navigating to Terms of Use screen");
        mFragmentFrameHelper.replaceFragment(TermsOfUseFragment.newInstance());
    }

    private void toPrivacyPolicyScreen() {
        Log.i(TAG, "navigating to Privacy Policy screen");
        mFragmentFrameHelper.replaceFragment(PrivacyPolicyFragment.newInstance());
    }

    public void toLoginScreen() {
        mFragmentFrameHelper.replaceFragment(LoginFragment.newInstance());
    }

    public void navigateBack() {
        mFragmentFrameHelper.navigateUp();
    }

    public void toSettingsItemScreen(SettingsItem settingsItem) {
        switch (settingsItem.getTitle()){
            case "Account":
                toAccountScreen();
                break;
            case "Recent Recordings":
                toRecentRecordingsScreen();
                break;
            case "Saved Recordings":
                toSavedRecordingsScreen();
                break;
            case "Notifications":
                toNotificationsScreen();
                break;
            case "Contact Us":
                toContactUsScreen();
                break;
            case "Terms of Use":
                toTermsOfUseScreen();
                break;
            case "Privacy Policy":
                toPrivacyPolicyScreen();
                break;
        }
    }

}