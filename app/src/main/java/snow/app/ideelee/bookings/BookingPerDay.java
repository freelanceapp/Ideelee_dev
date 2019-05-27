package snow.app.ideelee.bookings;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.timessquare.CalendarPickerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import snow.app.ideelee.BookingActivity;
import snow.app.ideelee.BookingAppointment;
import snow.app.ideelee.HomeScreen.HomeFragment;
import snow.app.ideelee.R;
import snow.app.ideelee.WalletFragment;

import static android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.CollectionInfoCompat.SELECTION_MODE_MULTIPLE;

public class BookingPerDay extends AppCompatActivity {
    Button makecustomslot;
    @BindView(R.id.book)
    Button book;
    String selecteddate;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title_bookingappointement)
    TextView textView;
    @BindView(R.id.calendarView)
    CalendarView simpleCalendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_per_day);
        ButterKnife.bind(this);

        textView.setText("Jack Vehicle Services");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/mm/dd");
        selecteddate = sdf.format(new Date(simpleCalendarView.getDate()));
        // System.out.println("seleted date ---"+selectedDate);
        simpleCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                selecteddate = (i2 + " / " + (i1 + 1) + " / " + i);
                System.out.println("sleted date---" + selecteddate);
                //Toast.makeText(getApplicationContext(), "Selected Date:\n" + "Day = " + i2 + "\n" + "Month = " + i1 + "\n" + "Year = " + i, Toast.LENGTH_LONG).show();
            }
        });

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookingPerDay.this, BookingActivity.class);
                intent.putExtra("key", selecteddate);
                startActivity(intent);
            }
        });
    }

    public void initiatePopupwindow(View v) {

        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.make_customslot, (ViewGroup) v.findViewById(R.id.linearlayout_customslot));
        final PopupWindow pw = new PopupWindow(layout, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        pw.showAtLocation(v, Gravity.CENTER, 0, 0);
        View container = (View) pw.getContentView().getRootView();
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams p = (WindowManager.LayoutParams) container.getLayoutParams();
        p.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        p.dimAmount = 0.6f;
        wm.updateViewLayout(container, p);
        Button btn_continue_loginPage = layout.findViewById(R.id.ux_btn_done);
        btn_continue_loginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmbooking();
            }
        });

    }

    public void confirmbooking() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.confirm_booking_dialog, (ViewGroup) findViewById(R.id.linearlayout_confirmbooking));
        final PopupWindow pw = new PopupWindow(layout, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        pw.showAtLocation(layout, Gravity.CENTER, 0, 0);
        View container = (View) pw.getContentView().getRootView();
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams p = (WindowManager.LayoutParams) container.getLayoutParams();
        p.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        p.dimAmount = 0.6f;
        wm.updateViewLayout(container, p);

        Button btn_continue_loginPage = layout.findViewById(R.id.ux_btn_booknow);
        btn_continue_loginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookingPerDay.this, BookingActivity.class);

                startActivity(intent);
            }
        });

    }

    private void initializeCalendar() {
        // simpleCalendarView.setSelectionMode(SELECTION_MODE_MULTIPLE); // Removes onClick functionality
        simpleCalendarView.setMaxDate(3);
//        simpleCalendarView.add(Calendar.DATE, -1);
//        calendarView.setDateSelected(cal.getTime(), true);
//        calendarView.setDateSelected(CalendarDay.today(), true);
//        calendarView.setDateSelected(CalendarDay.from(2017, 3, 19), true);
    }
}
