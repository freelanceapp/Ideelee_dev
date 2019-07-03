package snow.app.ideelee.extrafiles;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
/*import android.widget.CalendarView;*/
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.exceptions.OutOfDateRangeException;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.applikeysolutions.cosmocalendar.dialog.CalendarDialog;
import com.applikeysolutions.cosmocalendar.dialog.OnDaysSelectionListener;
import com.applikeysolutions.cosmocalendar.model.Day;
import com.applikeysolutions.cosmocalendar.selection.RangeSelectionManager;
import com.applikeysolutions.cosmocalendar.settings.lists.DisabledDaysCriteria;
import com.applikeysolutions.cosmocalendar.settings.lists.DisabledDaysCriteriaType;
import com.applikeysolutions.cosmocalendar.utils.SelectionType;

import com.squareup.timessquare.CalendarPickerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import snow.app.ideelee.Login;
import snow.app.ideelee.R;

public class BaseActivity extends Activity {
 //  CalendarPickerView  calendar;
    CalendarView calendarView;
    ProgressDialog dialog ;
    String startDate, endDate,device_token;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialog = new ProgressDialog(BaseActivity.this);
          sessionManager=new SessionManager(BaseActivity.this);
        calendarView = (CalendarView) getLayoutInflater().inflate(R.layout.cal_layout, null);
    //    dialog.setCancelable(false);


        dialog.getWindow()
                .setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT)
               );

        dialog.setContentView(R.layout.progressdialoglayout);
    }

    public void addCalanderToView(LinearLayout parentView) {
        Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);
        Date today = new Date();

        Date date = Calendar.getInstance().getTime();
        Calendar calendar = Calendar.getInstance();


        try {
            calendarView.setDate(calendar);
        } catch (OutOfDateRangeException e) {
            e.printStackTrace();
        }

        Calendar min = Calendar.getInstance();
        min.add(Calendar.DAY_OF_MONTH,-1);

        calendarView.setMinimumDate(min);
        Resources res = this.getResources();
        calendarView.setDividerDrawable(res.getDrawable(R.drawable.divider));
//
//        calendar.init(today, nextYear.getTime())
//                .withSelectedDate(today)
//                .inMode(CalendarPickerView.SelectionMode.MULTIPLE);
//        calendar.highlightDates(getHolidays());

//        DisabledDaysCriteria criteria = new DisabledDaysCriteria(Calendar.MONDAY, Calendar.FRIDAY, DisabledDaysCriteriaType.DAYS_OF_WEEK);
//        calendarView.setDisabledDaysCriteria(criteria);
//        calendarView.setCalendarOrientation(0);
//        calendarView.setFirstDayOfWeek(Calendar.MONDAY);
//
//        calendarView.setSelectionType(SelectionType.MULTIPLE);
//
//        if (calendarView.getSelectionManager() instanceof RangeSelectionManager) {
//            RangeSelectionManager rangeSelectionManager = (RangeSelectionManager)
//                    calendarView.getSelectionManager();
//            if (rangeSelectionManager.getDays() != null) {
//                startDate = rangeSelectionManager.getDays().first.toString();
//                endDate = rangeSelectionManager.getDays().second.toString();
//            } else {
//                Toast.makeText(getApplicationContext(), "Invalid Selection", Toast.LENGTH_SHORT).show();
//            }
//        }
//       calendarView.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View v) {
//
//           }
//       });
////        new CalendarDialog(this, new OnDaysSelectionListener() {
////            @Override
////            public void onDaysSelected(List<Day> selectedDays) {
////
////            }
////        }).show();
        calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                Calendar clickedDayCalendar = eventDay.getCalendar();

                System.out.println("clicked day--"+clickedDayCalendar);
            }
        });
        calendarView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
//        List<Calendar> selectedDates = calendarView.getSelectedDates();
//        System.out.println("seleted dates---"+selectedDates);
//        Calendar selectedDate = calendarView.getFirstSelectedDate();
//        System.out.println("selectedDate---"+selectedDate);


        parentView.addView(calendarView);
//    public List<Date> getSelectedDate(){
//       return calendar.getSelectedDates();
//    }
    }

    public ArrayList<Date> getHolidays(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
      //  String dateInString = "28-05-2019";
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



    public void    createProgressDialog() {

        try {
            dialog.show();
        } catch (WindowManager.BadTokenException e) {

        }
       // dialog.setCancelable(false);
        dialog.getWindow()
                .setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.progressdialoglayout);

        dialog.setMessage("Please WAIT.....");

    }


    public void dismissProgressDialog(){


        try {
            dialog.dismiss();
        } catch (WindowManager.BadTokenException e) {

        }

        // dialog.setMessage(Message);


    }



    //getdevicetoken....

    public  String getDeviceToken(Context context){
        device_token = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        System.out.println("device toekn--" + device_token);


        return device_token;
    }

}