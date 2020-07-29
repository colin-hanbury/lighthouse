package screens.settings.privacypolicy;

import screens.common.view.IBaseObservableView;

public interface IPrivacyPolicyView extends IBaseObservableView<IPrivacyPolicyView.Listener> {


    interface Listener {
        void onBackClicked();
        void onLogoutClicked();
    }

    void showProgressIndication();

    void  hideProgressIndication();

    void showToast(String message);
}
