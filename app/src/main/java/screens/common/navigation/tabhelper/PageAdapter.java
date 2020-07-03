package screens.common.navigation.tabhelper;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;


import screens.map.MapsFragment;
import screens.record.RecordFragment;
import screens.settings.SettingsFragment;

public class PageAdapter extends FragmentStateAdapter {

    private static final int NUM_TABS = 3;

    public PageAdapter(FragmentActivity fa) {
        super(fa);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new MapsFragment();
            case 1:
                return new RecordFragment();
            case 2:
                return new SettingsFragment();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return NUM_TABS;
    }
}
