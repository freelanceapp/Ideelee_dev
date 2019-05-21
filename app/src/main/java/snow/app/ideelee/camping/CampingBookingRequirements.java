package snow.app.ideelee.camping;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import snow.app.ideelee.R;
import snow.app.ideelee.carriers.CarrierBookingActivity;

public class CampingBookingRequirements extends AppCompatActivity {
Button ux_btn_continue_loginPage;
    ImageView backbutton1;
    ImageView notification;
    TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camping_bookingreq);
        backbutton1=(ImageView)findViewById(R.id.backbutton1);
        title=(TextView) findViewById(R.id.title_bookingappointement);
        title.setText("Booking");
        backbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        ux_btn_continue_loginPage =findViewById(R.id.ux_btn_continue_loginPage);
        ux_btn_continue_loginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CampingBookingRequirements.this,CampingBookingActivity.class));
            }
        });
    }
}
