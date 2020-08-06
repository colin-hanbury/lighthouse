package networking.fetch.recordings;

import android.util.Log;

import androidx.annotation.Nullable;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import common.BaseObservable;
import data.recordings.Recording;
import data.user.User;

public class FetchRecentRecordingTitles extends BaseObservable<FetchRecentRecordingTitles.Listener> {

    public interface Listener{
        void onFetchRecentRecordingsSuccess(List<Recording> recordings);
        void onFetchRecentRecordingsFailure(String error);
    }

    private static String TAG = "Fetch Recent Recordings";
    private String mUsername;


    public void tryFetchTitlesAndNotify(){
        mUsername = User.getUserInstance().getUsername();
        getRecordingsTitles();
    }


    private void getRecordingsTitles() {
        Log.i(TAG, "get recording titles");
        List<Recording> recordings = new ArrayList<>();
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        database.collection("users").document(mUsername)
                .collection("recentrecordings").addSnapshotListener(
                        new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots,
                                @Nullable FirebaseFirestoreException e) {
                for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()){
                    String title = (String) snapshot.get("title");
                    Recording recording = new Recording(title);
                    recordings.add(recording);
                }
            }
        });
        if (recordings.isEmpty()){
            notifyFailure("not recordings found");
        }
        else {
            notifySuccess(recordings);
        }
    }

    private void notifySuccess(List<Recording> recordings){
        for (Listener listener : getListeners()){
            listener.onFetchRecentRecordingsSuccess(recordings);
        }
    }

    private void notifyFailure(String error) {
        for (Listener listener : getListeners()){
            listener.onFetchRecentRecordingsFailure(error);
        }
    }

}

