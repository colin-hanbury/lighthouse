package screens.common.main;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Window;
import android.widget.FrameLayout;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import screens.common.controllers.BaseActivity;
import screens.common.navigation.bottomnavigation.BottomNavigationBarHelper;
import screens.common.navigation.fragmenthelpers.FragmentFrameWrapper;
import screens.common.navigation.bottomnavigation.IBottomNavigationBar;
import screens.common.navigation.screennavigation.ScreensNavigator;

public class MainActivity extends BaseActivity implements IBottomNavigationBar.Listener,
        BottomNavigationBarHelper, FragmentFrameWrapper {

    private ScreensNavigator mScreensNavigator;
    private IBottomNavigationBar mIBottomNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        requestPermission();
        mScreensNavigator = getCompositionRoot().getScreensNavigator();
        mIBottomNavigationBar = getCompositionRoot().getLightHouseViewFactory()
                .getBottomNavBar(null);
        setContentView(mIBottomNavigationBar.getRootView());
        mScreensNavigator.toLoginScreen();
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

    public void requestPermission(){
        checkPermissions();
    }


    public boolean checkPermissions(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CAMERA)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new android.app.AlertDialog.Builder(this)
                        .setTitle("App Permissions")
                        .setMessage("Please allow LightHouse permission to access camera, location, record audio, " +
                                "read from external storage and write to external storage")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(MainActivity.this,
                                        new String[]{Manifest.permission.CAMERA,
                                                Manifest.permission.ACCESS_FINE_LOCATION,
                                                Manifest.permission.ACCESS_COARSE_LOCATION,
                                                Manifest.permission.RECORD_AUDIO,
                                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                                Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                        99);
                            }
                        })
                        .create()
                        .show();
            }
            else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA,
                                Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        99);
            }
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public void hideNavBar() {
        mIBottomNavigationBar.hideNavBar();
    }

    @Override
    public void showNavBar() {
        mIBottomNavigationBar.showNavBar();
    }
}