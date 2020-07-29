package data.user;

public class User {

    private static String mEmail;
    private static String mUsername;
    private static String mDisplayName;

    public static volatile User userInstance;

    private User(){
        if (userInstance != null){
            throw new RuntimeException("Use getInstance() method to get the single user instance");
        }
    }

    public static User getUserInstance() {
        if (userInstance == null){
            synchronized (User.class){
                if (userInstance == null){
                    userInstance = new User();
                }
            }
        }
        return userInstance;
    }

    public void setEmail(String email){
        mEmail = email;
    }
    public String getEmail(){
        return mEmail;
    }
    public void setUsername(String username){
        mUsername = username;
    }
    public String getUsername(){
        return mUsername;
    }
    public void setDisplayName(String displayName){
        mDisplayName = displayName;
    }
    public String getDisplayName(){
        return mDisplayName;
    }
}
