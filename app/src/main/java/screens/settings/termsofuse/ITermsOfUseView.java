package screens.settings.termsofuse;

import screens.common.view.IBaseObservableView;

public interface ITermsOfUseView extends IBaseObservableView<ITermsOfUseView.Listener> {

    interface Listener{
        void onBackClicked();
        void onLogoutClicked();
    }

    void showProgressIndication();

    void hideProgressIndication();
}
