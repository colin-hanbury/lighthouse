package networking.fetch.recordings;

import android.content.Context;
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
        void onFetchRecentRecordingSuccess(String filePath);
        void onFetchRecentRecordingFailure(String error);
    }

    private String mUsername;
    private String mTitle;
    private Context mContext;
    private final String TAG = "FetchRecentRecording";

    public void tryFetchRecentRecordingAndNotify(String title, Context context){
        mUsername = User.getUserInstance().getUsername();
        mTitle = title;
        mContext = context;
        File tempFile = getTempFile();
        StorageReference storageRef = FirebaseStorage.getInstance().getReference()
                .child("users").child(mUsername).child("recentrecordings").child(title)
                .child(title + ".mp4");
        Log.i(TAG, "fetching recording for: " + mUsername);
        storageRef.getFile(tempFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                notifySuccess(tempFile.getAbsolutePath());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                notifyFailure(e.getMessage());
            }
        });
    }

    private File getTempFile() {
        // External sdcard file location
        File mediaStorageDir = new File(mContext.getExternalFilesDir(null)
                , "LightHouse");
        // Create storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(TAG, "Oops! Failed create LightHouse directory");
                return null;
            }
        }
        File mediaFile = new File(mediaStorageDir.getPath() + File.separator
                + mTitle);
        return mediaFile;
    }

    private void notifySuccess(String filePath){
        for (Listener listener: getListeners()){
            listener.onFetchRecentRecordingSuccess(filePath);
        }
    }

    private void notifyFailure(String error){
        for (Listener listener: getListeners()){
            listener.onFetchRecentRecordingFailure(error);
        }
    }
}
