package data.user;

public class User {

    private String mEmail;
    private String mUsername;
    private String mDisplayName;
    public User(String email,String displayName){
        mEmail = email;
        mUsername = email.replace("@", "");
        mDisplayName = displayName;
    }

    public String getEmail(){
        return mEmail;
    }
    public String getUsername(){
        return mUsername;
    }
    public String getDisplayName(){
        return mDisplayName;
    }
}
