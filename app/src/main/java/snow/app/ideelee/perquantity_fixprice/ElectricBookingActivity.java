package snow.app.ideelee.perquantity_fixprice;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import snow.app.ideelee.HomeScreen.HomeNavigation;
import snow.app.ideelee.R;

public class ElectricBookingActivity extends Activity {
    TextView txt_note;
    Button btn_confirmbooking;
    ImageView backbutton1;
    TextView timeslot,timeslotvalue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electric_booking);
        txt_note = findViewById(R.id.textview_note);
        backbutton1 = findViewById(R.id.backbutton1);
        btn_confirmbooking = findViewById(R.id.ux_btn_confirmbooking);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView textView = (TextView) toolbar.findViewById(R.id.title_bookingappointement);
        textView.setText("Booking");
        timeslot=findViewById(R.id.timeslot);
        timeslotvalue=findViewById(R.id.timeslotvalue);
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");
        String formattedDate = df.format(c);
        System.out.println("date in intent ---"+getIntent().getStringExtra("key"));
//        setSupportActionBar(toolbar);
        if (getIntent().hasExtra("key")){

    timeslot.setText("Day");
    timeslotvalue.setText(getIntent().getStringExtra("key"));


  }else {

        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            txt_note.setText(Html.fromHtml("<pre><span style=\"color: #ff0000;\">Note:-</span> <span style=\"color: #000000;\">You will get a notification/SMS when Service provider accept your job.</span></pre>", Html.FROM_HTML_MODE_COMPACT));
        } else {
            txt_note.setText(Html.fromHtml("<pre><span style=\"color: #ff0000;\">Note:-</span> <span style=\"color: #000000;\">You will get a notification/SMS when Service provider accept your job.</span></pre>"));
        }
        btn_confirmbooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initiatePopupwindow(v);
            }
        });


        backbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void initiatePopupwindow(View v) {

        try {
            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.booking_successful_dialog, (ViewGroup) v.findViewById(R.id.linearlayout_bookingsuccessful));
            final PopupWindow pw = new PopupWindow(layout, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
            pw.showAtLocation(v, Gravity.CENTER, 0, 0);
            View container = (View) pw.getContentView().getRootView();
            WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
            WindowManager.LayoutParams p = (WindowManager.LayoutParams) container.getLayoutParams();
            p.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            p.dimAmount = 0.6f;
            wm.updateViewLayout(container, p);
            Button btn_continue_loginPage = layout.findViewById(R.id.ux_btn_done_bs);
            btn_continue_loginPage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ElectricBookingActivity.this, HomeNavigation.class);
                    intent.putExtra("key", "wallet");
                    startActivity(intent);
                    finish();

                }
            });
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong..", Toast.LENGTH_SHORT).show();
        }


    }


}
