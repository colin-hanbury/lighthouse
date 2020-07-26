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
        void onRegistrationFailure();
    }

    private String TAG = "FetchRegistration";
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private User mUser;


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
                    notifyFailure();
                }
            }
        });
    }

    private void notifySuccess() {
        Log.i(TAG, "notifying registration success");
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mUser = new User(mFirebaseUser.getEmail(), mFirebaseUser.getDisplayName());
        for (Listener listener : getListeners()) {
            listener.onRegistrationSuccess();
        }
    }

    private void notifyFailure() {
        Log.i(TAG, "notifying registration failure");
        for (Listener listener : getListeners()) {
            listener.onRegistrationFailure();
        }
    }
}
