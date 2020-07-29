package networking.logout;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import common.BaseObservable;
import data.user.User;


public class PostLogout extends BaseObservable<PostLogout.Listener> {

    public interface Listener {
        void onLogoutSuccess();
        void onLogoutFailure(String error);
    }

    private final String TAG = "FetchLogin";
    private FirebaseUser mFirebaseUser;
    private FirebaseAuth mFirebaseAuth;

    public void tryLogoutAndNotify(){
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        if(mFirebaseUser != null){
            try {
                mFirebaseAuth.signOut();
            }
            catch (Exception e){
                notifyFailure(e.getMessage());
            }
        }
        notifySuccess();
    }

    private void notifyFailure(String errorMessage) {
        Log.i(TAG, "notifying login failure");
        for (Listener listener : getListeners()) {
            listener.onLogoutFailure(errorMessage);
        }
    }

    private void notifySuccess() {
        Log.i(TAG, "notifying logout success");

        for (Listener listener : getListeners()) {
            listener.onLogoutSuccess();
        }
    }
}
