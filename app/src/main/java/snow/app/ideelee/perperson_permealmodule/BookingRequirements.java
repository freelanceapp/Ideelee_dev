package snow.app.ideelee.perperson_permealmodule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import snow.app.ideelee.R;

public class BookingRequirements extends AppCompatActivity {
Button ux_btn_continue_loginPage;
    ImageView backbutton1;
    ImageView notification;
    TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_requirements);
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
                startActivity(new Intent(BookingRequirements.this,EventBookingActivity.class));
            }
        });
    }
}
