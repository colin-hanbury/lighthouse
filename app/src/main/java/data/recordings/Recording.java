package data.recordings;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Recording {
    private final String mTitle;
    private final String mDate;

    public Recording(String title, String date){
        this.mTitle = title;
        this.mDate = date;
    }
//
//    private String makeDateString(String title) {
//        try{
//            String tempDateArray[] = title.replace("VID_", "").split("_");
//            String tempDate = tempDateArray[0];
//            Date date = new SimpleDateFormat("yyyy/MM/dd").parse(tempDate);
//            return date.toString();
//        }
//         catch (ParseException e)  {
//            //do nothing for now
//         }
//
//    }

    public String getTitle() {
        return mTitle;
    }

    public String getDate() {
        return mDate;
    }


}
