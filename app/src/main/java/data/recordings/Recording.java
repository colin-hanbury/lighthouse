package data.recordings;


public class Recording {
    private final String mTitle;
    private final String mDate;
    private final String mUri;
    private String mPath;

    public Recording(String title, String date, String uri){
        this.mTitle = title;
        this.mDate = date;
        this.mUri = uri;
    }

    public void setFilePath(String path){
        mPath = path;
    }
    public String getTitle() {
        return mTitle;
    }

    public String getDate() {
        return mDate;
    }

    public String getUri() {
        return mUri;
    }

    public String getFilePath(){
        return mPath;
    }
}
