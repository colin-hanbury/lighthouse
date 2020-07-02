package screens.record;

import screens.common.view.IBaseObservableView;

import static screens.record.IRecordView.*;

public interface IRecordView extends IBaseObservableView<Listener> {

    interface Listener {
        void onBackClicked();
        void onLogoutClicked();
    }
    void showProgressIndication();

    void hideProgressIndication();
}
