package networking.fetch.recordings;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

import common.BaseObservable;
import data.user.User;

public class FetchRecentRecording extends BaseObservable<FetchRecentRecording.Listener> {

    public interface Listener{
        void onFetchRecentRecordingSuccess(File recording);
        void onFetchRecentRecordingFailure(String error);
    }

    private String mUsername;

private final String TAG = "FetchRecentRecording";
    public void tryFetchRecentRecordingAndNotify(String title){
        mUsername = User.getUserInstance().getUsername();
        try{
            File tempFile = File.createTempFile("VideoRecordings", "mp4");
            StorageReference storageRef = FirebaseStorage.getInstance().getReference()
                    .child("users").child(mUsername).child("recentrecordings").child(title)
                    .child(title + ".mp4");
            Log.i(TAG, "fetching recording for: " + mUsername);
            storageRef.getFile(tempFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    notifySuccess(tempFile);
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

    private void notifySuccess(File recording){

    }

    private void notifyFailure(String error){

    }
}
