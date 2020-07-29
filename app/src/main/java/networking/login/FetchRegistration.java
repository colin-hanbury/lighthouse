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

public class FetchRegistration extends BaseObservable<FetchRegistration.Listener> {

    public interface Listener {
        void onRegistrationSuccess();
        void onRegistrationFailure(String error);
    }

    private String TAG = "FetchRegistration";
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;

    public void tryRegistrationAndNotify(LoginDetails loginDetails){
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseAuth.createUserWithEmailAndPassword(loginDetails.getEmail(),
                loginDetails.getPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    notifySuccess();
                }
                else{
                    notifyFailure(task.getException().getMessage());
                }
            }
        });
    }

    private void notifySuccess() {
        Log.i(TAG, "notifying registration success");
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        User user = User.getUserInstance();
        user.setEmail(mFirebaseUser.getEmail());
        user.setDisplayName(mFirebaseUser.getEmail().split("@")[0]);
        user.setUsername(mFirebaseUser.getEmail().replace("@",""));
        for (Listener listener : getListeners()) {
            listener.onRegistrationSuccess();
        }
    }

    private void notifyFailure(String error) {
        Log.i(TAG, "notifying registration failure");
        for (Listener listener : getListeners()) {
            listener.onRegistrationFailure(error);
        }
    }
}
