package networking.post.recordings;

import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import common.BaseObservable;
import data.user.User;

public class PostToRecentRecordings extends BaseObservable<PostToRecentRecordings.Listener> {

    public interface Listener{
        void onPostToRecentRecordingsSuccess();
        void onPostToRecentRecordingsFailure(String error);
    }

    private static String TAG = "PostToRecentRecordings";
    private FirebaseFirestore mDatabase;
    private StorageReference mStorageRef;
    private String mUsername;

    public void tryPostToRecentRecordingsAndNotify(File file){
        mUsername = User.getUserInstance().getUsername();
        Log.i(TAG, mUsername);
        String fileName = file.getName();
        Uri fileUri = Uri.fromFile(file);
        String date = new SimpleDateFormat("yyyy/MM/dd - HH:mm",
                Locale.getDefault()).format(new Date());
        Log.i(TAG, "starting storage post");
         mStorageRef = FirebaseStorage.getInstance().getReference()
                 .child("users").child(mUsername).child("recentrecordings")
                 .child(fileName);
         mStorageRef.putFile(fileUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
             @Override
             public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                 Uri uri = taskSnapshot.getUploadSessionUri();
                 Log.i(TAG, "post storage success");
                 notifySuccess();
                 saveToDB(uri, fileName, date);
             }
         }).addOnFailureListener(new OnFailureListener() {
             @Override
             public void onFailure(@NonNull Exception e) {
                 Log.i(TAG, "post storage failure\n " + e.getMessage());
                 notifyFailure(e.getMessage());
             }
         });
    }

    private void saveToDB(Uri uri, String title, String date) {
        mDatabase = FirebaseFirestore.getInstance();
        Map<String, String> recordindDetailsMap = new HashMap<>();
        recordindDetailsMap.put("title", title);
        recordindDetailsMap.put("date", date);
        recordindDetailsMap.put("videouri", uri.toString());
        Log.i(TAG, "starting db post");
        mDatabase.collection("users").document(mUsername)
                .collection("recentrecordings").document(title).set(recordindDetailsMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.i(TAG, "post db success");
                        notifySuccess();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i(TAG, "post db failure\n " + e.getMessage());
                        notifyFailure(e.getMessage());
                    }
        });
    }

    private void notifySuccess(){
        for (Listener listener: getListeners()){
            listener.onPostToRecentRecordingsSuccess();
        }
    }

    private void notifyFailure(String error){
        for (Listener listener: getListeners()){
            listener.onPostToRecentRecordingsFailure(error);
        }
    }

}
