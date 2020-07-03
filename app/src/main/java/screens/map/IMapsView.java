package screens.map;

import screens.common.view.IBaseObservableView;

import static screens.map.IMapsView.*;

public interface IMapsView extends IBaseObservableView<Listener> {

    interface Listener {
        void onBackClicked();
        void onLogoutClicked();
    }
    void showProgressIndication();

    void hideProgressIndication();
}