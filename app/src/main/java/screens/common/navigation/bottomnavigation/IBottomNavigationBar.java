package screens.common.navigation.bottomnavigation;

import android.widget.FrameLayout;

import androidx.fragment.app.FragmentActivity;

import screens.common.view.IBaseObservableView;

public interface IBottomNavigationBar extends IBaseObservableView<IBottomNavigationBar.Listener> {

    interface Listener {

        void onMapItemClicked();

        void onRecordItemClicked();

        void onSettingsItemClicked();

    }
    void setViewPagerAdapter(FragmentActivity fragmentActivity);

    FrameLayout getFragmentFrame();

}