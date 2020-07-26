package networking.post.recordings;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import common.BaseObservable;
import common.LightHouseApp;

public class PostToRecentRecordings extends BaseObservable<PostToRecentRecordings.Listener> {

    public interface Listener{
        void onPostToRecentRecordingsSuccess();
        void onPostToRecentRecordingsFailure();
    }

    private FirebaseFirestore mDatabase;
    private StorageReference mStorageRef;

    public void tryPostToRecentRecordingsAndNotify(){
         mDatabase = FirebaseFirestore.getInstance();
         mStorageRef = FirebaseStorage.getInstance().getReference()
                 .child("users/user").child(user.getusername()).child("recentrecordings")
                 .child(recordingname);
         
    }

    private void notifySuccess(){
        for (Listener listener: getListeners()){
            listener.onPostToRecentRecordingsSuccess();
        }
    }
    private void notifyFailure(String error){
        for (Listener listener: getListeners()){
            listener.onPostToRecentRecordingsFailure();
        }
    }
}
