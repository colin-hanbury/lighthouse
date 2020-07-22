package data.recordings;

public class RecordingFile {
    private final String mTitle;
    private final String mDate;

    public RecordingFile(String title, String date){
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
