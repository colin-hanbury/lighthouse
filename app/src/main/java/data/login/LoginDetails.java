package data.login;

public class LoginDetails {

    private String mEmail;
    private String mPassword;
    public LoginDetails(String email, String password){
        mEmail = email;
        mPassword = password;
    }

    public String getEmail() {
        return mEmail;
    }
    public String getPassword(){
        return mPassword;
    }
}
