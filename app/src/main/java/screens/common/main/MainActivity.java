package screens.common.main;

import android.os.Bundle;
import android.view.Window;
import android.widget.FrameLayout;


import androidx.fragment.app.FragmentActivity;

import hanbury.colin.networking.R;
import screens.common.controllers.BaseActivity;
import screens.common.navigation.fragmenthelpers.FragmentFrameWrapper;
import screens.common.navigation.screennavigation.ScreensNavigator;
import screens.common.navigation.bottomnavigation.IBottomNavigationBar;

public class MainActivity extends BaseActivity implements IBottomNavigationBar.Listener,
        FragmentFrameWrapper {


    private ScreensNavigator mScreensNavigator;
    private IBottomNavigationBar mIBottomNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mScreensNavigator = getCompositionRoot().getScreensNavigator();
        mIBottomNavigationBar = getCompositionRoot().getLightHouseViewFactory()
                .getBottomNavBar(null);
        mIBottomNavigationBar.setViewPagerAdapter(this);
        setContentView(mIBottomNavigationBar.getRootView());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mIBottomNavigationBar.registerListener(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
//        createBottomNavView();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mIBottomNavigationBar.unregisterListener(this);
    }

    @Override
    public void onMapItemClicked() {
        mScreensNavigator.toMapsScreen();
    }

    @Override
    public void onRecordItemClicked() {
        mScreensNavigator.toRecordScreen();
    }

    @Override
    public void onSettingsItemClicked() {
        mScreensNavigator.toSettingsScreen();
    }

    @Override
    public FrameLayout getFragmentFrame() {
        return mIBottomNavigationBar.getFragmentFrame();
    }
}