package snow.app.ideelee.camping;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import snow.app.ideelee.R;
import snow.app.ideelee.carriers.CarrierBookingActivity;

public class CampingBookingRequirements extends AppCompatActivity {
@BindView(R.id.ux_btn_continue_loginPage) Button ux_btn_continue_loginPage;
    @BindView(R.id.backbutton1) ImageView backbutton1;

   @BindView(R.id.title_bookingappointement) TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camping_bookingreq);
        ButterKnife.bind(this);
        title.setText("Booking");
        backbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        ux_btn_continue_loginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CampingBookingRequirements.this,CampingBookingActivity.class));
            }
        });



    }
}

