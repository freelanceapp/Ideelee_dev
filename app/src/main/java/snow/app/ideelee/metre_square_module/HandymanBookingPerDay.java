package snow.app.ideelee.metre_square_module;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import snow.app.ideelee.R;
import snow.app.ideelee.extrafiles.BaseActivity;
import snow.app.ideelee.perday_fixedpricemodule.RentalBookingActivity;
import snow.app.ideelee.perperson_permealmodule.BookingRequirements;

public class HandymanBookingPerDay extends BaseActivity {
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
    @BindView(R.id.backbutton1)
    ImageView backbutton1;

    @BindView(R.id.parentView)
    LinearLayout parentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handyman_bookingperday);
        ButterKnife.bind(this);

        textView.setText("Handyman Services");
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
                Intent intent = new Intent(HandymanBookingPerDay.this, HandymanBookingRequirements.class);
                intent.putExtra("key", selecteddate);
                startActivity(intent);
            }
        });
        backbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        addCalanderToView(parentView);
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
                Intent intent = new Intent(HandymanBookingPerDay.this, RentalBookingActivity.class);

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
