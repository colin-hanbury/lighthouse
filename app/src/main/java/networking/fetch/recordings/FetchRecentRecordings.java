package networking.fetch.recordings;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import common.BaseObservable;
import data.user.User;

public class FetchRecentRecordings extends BaseObservable<FetchRecentRecordings.Listener> {

    public interface Listener{
        void onFetchRecentRecordingsSuccess();
        void onFetchRecentRecordingsFailure();
    }

    private static String TAG = "Fetch Recent Recordings";
    private String mUsername;
    private List<String> mRecordingsTitles;


    public void tryFetchRecentRecordingsAndNotify(){
        mUsername = User.getUserInstance().getUsername();
        mRecordingsTitles = getRecordingsTitles();
        getRecordings();

    }



    private List<String> getRecordingsTitles() {
        List<String> titles = new ArrayList<>();
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        database.collection("users").document(mUsername)
                .collection("recentrecordings")
                .addSnapshotListener(new Snap
        return titles;
    }


    private void getRecordings() {
        for (String title: mRecordingsTitles){

            try{
                File tempFile = File.createTempFile("VideoRecordings", "mp4");
                StorageReference storageRef = FirebaseStorage.getInstance().getReference()
                        .child("users").child(mUsername).child("recentrecordings").child(title)
                        .child(title + ".mp4");
                Log.i(TAG, "fetching recording for: " + mUsername);
                storageRef.getFile(tempFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        notifyFailure(e.getMessage());
                    }
                });
            }
            catch (IOException e){
                notifyFailure(e.getMessage());
            }
        }

    }



    private void notifySuccess(){

    }

    private void notifyFailure(String message) {
    }

}

