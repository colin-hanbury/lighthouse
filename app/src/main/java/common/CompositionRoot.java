package common;

import android.content.Context;
import android.view.LayoutInflater;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import data.user.User;
import networking.fetch.recordings.FetchRecentRecordingTitles;
import networking.login.FetchLogin;
import networking.login.FetchRegistration;
import networking.logout.PostLogout;
import networking.post.recordings.PostToRecentRecordings;
import screens.common.navigation.bottomnavigation.BottomNavigationBarHelper;
import screens.common.navigation.fragmenthelpers.FragmentFrameHelper;
import screens.common.navigation.fragmenthelpers.FragmentFrameWrapper;
import screens.common.navigation.screennavigation.ScreensNavigator;
import screens.common.viewfactory.LightHouseViewFactory;
import screens.settings.recordings.recent.RecentRecordingsFragment;


public class CompositionRoot {

    private final FragmentActivity mActivity;

    public CompositionRoot(FragmentActivity activity) {
        mActivity = activity;
    }


    private FragmentActivity getActivity() {
        return mActivity;
    }

    private Context getContext() {
        return mActivity;
    }

    private FragmentManager getFragmentManager() {
        return getActivity().getSupportFragmentManager();
    }

    private LayoutInflater getLayoutInflater() {
        return LayoutInflater.from(getContext());
    }

    public LightHouseViewFactory getLightHouseViewFactory() {
        return new LightHouseViewFactory(getLayoutInflater() );//, getNavDrawerHelper());
    }

    public ScreensNavigator getScreensNavigator() {
        return new ScreensNavigator(getFragmentFrameHelper());
    }

    private FragmentFrameHelper getFragmentFrameHelper() {
        return new FragmentFrameHelper(getActivity(), getFragmentFrameWrapper(), getFragmentManager());
    }

    private FragmentFrameWrapper getFragmentFrameWrapper() {
        return (FragmentFrameWrapper) getActivity();
    }

    public BottomNavigationBarHelper getNavBarHelper(){
        return (BottomNavigationBarHelper) getActivity();
    }

    public FetchLogin getFetchLogin() {
        return new FetchLogin();
    }

    public FetchRegistration getFetchRegistration() {
        return new FetchRegistration();
    }

    public PostToRecentRecordings getPostToRecentRecordings(){
        return new PostToRecentRecordings();
    }

    public PostLogout getPostLogout() {
        return new PostLogout();
    }

    public FetchRecentRecordingTitles getFetchRecentRecordingTitles() {
        return new FetchRecentRecordingTitles();
    }
}