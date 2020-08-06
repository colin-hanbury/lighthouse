package data.recordings;

public class Recording {
    private final String mTitle;
    private final String mDate;

    public Recording(String title){
        this.mTitle = title;
        this.mDate = makeDateString(title);
    }

    private String makeDateString(String title) {
        return title;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDate() {
        return mDate;
    }


}
