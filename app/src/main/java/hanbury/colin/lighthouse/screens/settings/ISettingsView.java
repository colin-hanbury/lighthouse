package hanbury.colin.lighthouse.screens.settings;

import hanbury.colin.lighthouse.screens.common.view.IBaseObservableView;

public interface ISettingsView extends IBaseObservableView<ISettingsView.Listener> {

    interface Listener {
        void onNavigateUpClicked();
    }
}
