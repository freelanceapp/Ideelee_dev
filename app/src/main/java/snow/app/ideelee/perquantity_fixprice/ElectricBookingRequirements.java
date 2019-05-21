package snow.app.ideelee.perquantity_fixprice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import snow.app.ideelee.R;
import snow.app.ideelee.metre_square_module.HandymanBookingActivity;

public class ElectricBookingRequirements extends AppCompatActivity {
Button ux_btn_continue_loginPage;
    ImageView backbutton1;
    ImageView notification;
    TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electric_bookingreq);
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
                startActivity(new Intent(ElectricBookingRequirements.this,ElectricBookingActivity.class));
            }
        });
    }
}
