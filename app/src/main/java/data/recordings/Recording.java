package data.recordings;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Recording {
    private final String mTitle;
    private final String mDate;
    private final String mUri;

    public Recording(String title, String date, String uri){
        this.mTitle = title;
        this.mDate = date;
        this.mUri = uri;
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
}
