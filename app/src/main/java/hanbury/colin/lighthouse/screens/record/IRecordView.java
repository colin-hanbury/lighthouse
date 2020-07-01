package hanbury.colin.lighthouse.screens.record;

import hanbury.colin.lighthouse.screens.common.view.IBaseObservableView;

public interface IRecordView extends IBaseObservableView<IRecordView.Listener> {

    interface Listener {
        void onNavigateUpClicked();
    }
    void showProgressIndication();

    void hideProgressIndication();
}
