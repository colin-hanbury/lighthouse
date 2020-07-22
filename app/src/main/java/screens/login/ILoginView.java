package screens.login;

import screens.common.view.IBaseObservableView;

public interface ILoginView extends IBaseObservableView<ILoginView.Listener> {

    interface Listener{
        void onBackClicked();
        void onLoginClicked();
        void onRegisterClicked();
    }

    void showProgressIndication();

    void hideProgressIndication();
}
