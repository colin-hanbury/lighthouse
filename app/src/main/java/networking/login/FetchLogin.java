package networking.login;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import common.BaseObservable;
import data.login.LoginDetails;
import data.user.User;

public class FetchLogin extends BaseObservable<FetchLogin.Listener> {

    public interface Listener {
        void onLoginSuccess();
        void onLoginFailure(String error);
    }

    private final String TAG = "FetchLogin";
    private FirebaseUser mFirebaseUser;
    private FirebaseAuth mFirebaseAuth;

    public void tryLoginAndNotify(LoginDetails loginDetails){
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        if (mFirebaseUser != null){
            notifySuccess();
        }
        else {
            mFirebaseAuth.signInWithEmailAndPassword(
                    loginDetails.getEmail(), loginDetails.getPassword()).addOnCompleteListener(
                            new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        notifySuccess();
                    }
                    else {
                        notifyFailure(task.getException().getMessage());
                    }
                }
            });
        }
    }

    private void notifyFailure(String errorMessage) {
        Log.i(TAG, "notifying login failure");
        for (Listener listener : getListeners()) {
            listener.onLoginFailure(errorMessage);
        }
    }

    private void notifySuccess() {
        Log.i(TAG, "notifying login success");
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        User user = User.getUserInstance();
        user.setEmail(mFirebaseUser.getEmail());
        user.setDisplayName(mFirebaseUser.getEmail().split("@")[0]);
        user.setUsername(mFirebaseUser.getEmail().replace("@",""));
        for (Listener listener : getListeners()) {
            listener.onLoginSuccess();
        }
    }
}
