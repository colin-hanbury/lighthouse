package screens.common.navigation.bottomnavigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import hanbury.colin.networking.R;
import screens.common.view.BaseObservableView;

public class BottomNavigationBar extends BaseObservableView<IBottomNavigationBar.Listener>
        implements IBottomNavigationBar {

    private final String TAG = "BottomNavigationBar";
    private final FrameLayout frameLayout;
    private final BottomNavigationView bottomNavigationView;

    public BottomNavigationBar(LayoutInflater inflater, @Nullable ViewGroup parent) {
        setRootView(inflater.inflate(R.layout.nav_bar, parent, false));
        frameLayout = findViewById(R.id.nav_bar_layout);
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
    public FrameLayout getFragmentFrame() {
        return frameLayout;
    }
}