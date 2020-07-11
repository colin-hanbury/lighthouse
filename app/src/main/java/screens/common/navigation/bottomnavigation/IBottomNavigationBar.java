package screens.common.navigation.bottomnavigation;

import android.widget.FrameLayout;


import screens.common.view.IBaseObservableView;

public interface IBottomNavigationBar extends IBaseObservableView<IBottomNavigationBar.Listener> {

    interface Listener {

        void onMapItemClicked();

        void onRecordItemClicked();

        void onSettingsItemClicked();

    }

    FrameLayout getFragmentFrame();

}