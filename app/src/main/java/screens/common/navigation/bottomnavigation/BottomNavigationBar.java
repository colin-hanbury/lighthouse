package screens.common.navigation.bottomnavigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import hanbury.colin.lighthouse.R;
import screens.common.view.BaseObservableView;

public class BottomNavigationBar extends BaseObservableView<IBottomNavigationBar.Listener>
        implements IBottomNavigationBar {

    private final String TAG = "BottomNavigationBar";
    private final FrameLayout mContainer;
    private final BottomNavigationView bottomNavigationView;

    public BottomNavigationBar(LayoutInflater inflater, @Nullable ViewGroup parent) {
        setRootView(inflater.inflate(R.layout.nav_bar, parent, false));
        mContainer = findViewById(R.id.main_container);
        bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.navigation_map:
                        Log.i(TAG, "Map Selected");
                        for (Listener listener : getListeners()) {
                            listener.onMapItemClicked();
                        }
                        return true;

                    case R.id.navigation_record:
                        Log.i(TAG, "Record Selected");
                        for (Listener listener : getListeners()) {
                            Log.i(TAG, "listener");
                            listener.onRecordItemClicked();
                        }
                        return true;

                    case R.id.navigation_settings:
                        Log.i(TAG, "Settings Selected");
                        for (Listener listener : getListeners()) {
                            listener.onSettingsItemClicked();
                        }
                        return true;
                }
                return false;
            }
        });
    }


    @Override
    public void hideNavBar() {
        bottomNavigationView.setVisibility(View.GONE);
    }

    @Override
    public void showNavBar() {
        bottomNavigationView.setVisibility(View.VISIBLE);
    }

    @Override
    public FrameLayout getFragmentFrame() {
        return mContainer;
    }
}