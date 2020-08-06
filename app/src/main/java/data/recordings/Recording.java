package data.recordings;

public class Recording {
    private final String mTitle;
    private final String mDate;

    public Recording(String title, String date){
        this.mTitle = title;
        this.mDate = date;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDate() {
        return mDate;
    }
}
