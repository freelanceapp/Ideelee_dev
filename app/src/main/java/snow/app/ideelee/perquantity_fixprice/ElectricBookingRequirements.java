package snow.app.ideelee.perquantity_fixprice;

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
import snow.app.ideelee.metre_square_module.HandymanBookingActivity;

public class ElectricBookingRequirements extends AppCompatActivity {
    @BindView(R.id.ux_btn_continue_loginPage)
    Button ux_btn_continue_loginPage;
    @BindView(R.id.backbutton1)
    ImageView backbutton1;
    ImageView notification;
    @BindView(R.id.title_bookingappointement)
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electric_bookingreq);
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
                startActivity(new Intent(ElectricBookingRequirements.this, ElectricBookingActivity.class));
            }
        });
    }
}
