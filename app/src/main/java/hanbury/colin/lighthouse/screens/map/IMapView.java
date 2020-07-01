package hanbury.colin.lighthouse.screens.map;

import hanbury.colin.lighthouse.screens.common.view.IBaseObservableView;

public interface IMapView extends IBaseObservableView<IMapView> {

    interface Listener {
        void onNavigateUpClicked();
    }
}
