package screens.login;

import data.login.LoginDetails;
import screens.common.view.IBaseObservableView;

public interface ILoginView extends IBaseObservableView<ILoginView.Listener> {

    interface Listener{
        void onBackClicked();
        void onLoginClicked(LoginDetails loginDetails);
        void onRegisterClicked(LoginDetails loginDetails);
    }

    void showProgressIndication();

    void hideProgressIndication();
}
