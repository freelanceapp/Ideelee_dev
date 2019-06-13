package snow.app.ideelee.fixedpricemodule;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.gujun.android.taggroup.TagGroup;
import snow.app.ideelee.BookingActivity;
import snow.app.ideelee.R;

public class VehicleWashBookingAppointment extends Activity {

    @BindView(R.id.ux_btn_makecustomslot)
    Button makecustomslot;
    @BindView(R.id.title_bookingappointement)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vehiclewashbookingappointment);
        ButterKnife.bind(this);
        makecustomslot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initiatePopupwindow(v);
            }
        });
//        setSupportActionBar(toolbar);
        textView.setText("Jack Harry Vehicle Service Station");

//        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        mTagGroup = (TagGroup)findViewById(R.id.tag_group_bookingapp);
//        mTagGroup.setTags(new String[]{"12-01 AM", "12-01 AM", "12-01 AM",
//                "01-02 AM","01-02 AM","01-02 AM",
//                "02-03 AM","02-03 AM","02-03 AM"});
//
//    mTagGroup.setOnTagClickListener(new TagGroup.OnTagClickListener() {
//        @Override
//        public void onTagClick(String tag) {
//            mTagGroup.
//        }
//    });
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
                Intent intent = new Intent(VehicleWashBookingAppointment.this, VehicleWashBookingActivity.class);

                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onNavigateUp() {
        return super.onNavigateUp();
    }

}
