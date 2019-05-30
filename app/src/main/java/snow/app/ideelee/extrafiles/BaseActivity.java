package snow.app.ideelee.extrafiles;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.squareup.timessquare.CalendarPickerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import snow.app.ideelee.R;

public class BaseActivity extends Activity {
    CalendarPickerView  calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
          calendar=(CalendarPickerView)getLayoutInflater().inflate(R.layout.cal_layout, null);
    }

    public void addCalanderToView(LinearLayout parentView){
        Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);
        Date today = new Date();
        calendar.init(today, nextYear.getTime())
                .withSelectedDate(today)
                .inMode(CalendarPickerView.SelectionMode.RANGE);
        calendar.highlightDates(getHolidays());
        parentView.addView(calendar);

    }

    public List<Date> getSelectedDate(){
       return calendar.getSelectedDates();
    }





    public ArrayList<Date> getHolidays(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
        String dateInString = "28-05-2019";
        Date date = Calendar.getInstance().getTime();
//        try {
//            date = sdf.parse(dateInString);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        ArrayList<Date> holidays = new ArrayList<>();
        holidays.add(date);
        return holidays;
    }
}
