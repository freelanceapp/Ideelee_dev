package snow.app.ideelee;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;

import com.chaos.view.PinView;


import butterknife.BindView;
import butterknife.ButterKnife;
import snow.app.ideelee.HomeScreen.HomeNavigation;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class OTP extends Activity {
   @BindView(R.id.ux_btn_continue_otpPage) Button btn_continue_otpPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        ButterKnife.bind(this);
        btn_continue_otpPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_continue=new Intent(OTP.this, HomeNavigation.class);
                startActivity(intent_continue);
            }
        });
      //  final PinView pinView = findViewById(R.id.firstPinView);
//        pinView.setTextColor(
//                ResourcesCompat.getColor(getResources(), R.color.black, getTheme()));
//        pinView.setTextColor(
//                ResourcesCompat.getColorStateList(getResources(), R.color.black, getTheme()));
//        pinView.setLineColor(
//                ResourcesCompat.getColor(getResources(), R.color.colorPrimary, getTheme()));
//        pinView.setLineColor(
//                ResourcesCompat.getColorStateList(getResources(), R.color.colorPrimary, getTheme()));
//        pinView.setItemCount(4);
//        pinView.setItemHeight(getResources().getDimensionPixelSize(R.dimen.pv_pin_view_item_size));
//        pinView.setItemWidth(getResources().getDimensionPixelSize(R.dimen.pv_pin_view_item_size));
//        pinView.setItemRadius(getResources().getDimensionPixelSize(R.dimen.pv_pin_view_item_radius));
//        pinView.setItemSpacing(getResources().getDimensionPixelSize(R.dimen.pv_pin_view_item_spacing));
//        pinView.setLineWidth(getResources().getDimensionPixelSize(R.dimen.pv_pin_view_item_line_width));
//        pinView.setAnimationEnable(true);// start animation when adding text
//        pinView.setCursorVisible(false);
//        pinView.setCursorColor(
//                ResourcesCompat.getColor(getResources(), R.color.colorPrimary, getTheme()));
//        pinView.setCursorWidth(getResources().getDimensionPixelSize(R.dimen.pv_pin_view_cursor_width));
//        pinView.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }});
//        pinView.setItemBackgroundColor(Color.TRANSPARENT);
////        pinView.setItemBackground(getResources().getDrawable(R.drawable.item_background));
////        pinView.setItemBackgroundResources(R.drawable.item_background);
//        pinView.setHideLineWhenFilled(false);
    }

}
