package snow.app.ideelee;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import snow.app.ideelee.HomeScreen.HomeFragment;
import snow.app.ideelee.HomeScreen.HomeNavigation;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class PaymentSuccessfulActivity extends Activity {
   @BindView
  (R.id.ux_btn_rate) Button btn_rate;
   @BindView(R.id.backbutton1) ImageView backbutton1;
  @BindView(R.id.title_bookingappointement)  TextView title_bookingappointement;
  @BindView(R.id.txt_back)  TextView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_successful);
        ButterKnife.bind(this);

        if (getIntent().hasExtra("key")) {
            if (getIntent().getStringExtra("key").equals("Done")) {
                btn_rate.setText("Done");
                back.setVisibility(View.GONE);
            } else {
                btn_rate.setText("Rate Harry");
                back.setVisibility(View.VISIBLE);
            }
        } else {
            btn_rate.setText("Rate Harry");
            btn_rate.setVisibility(View.VISIBLE);

        }

            btn_rate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (btn_rate.getText().equals("Rate Harry")) {
                        Intent intent = new Intent(PaymentSuccessfulActivity.this, RateServiceProviderActivity.class);
                        startActivity(intent);
                    }else {
                        Intent intent = new Intent(PaymentSuccessfulActivity.this, HomeNavigation.class);
                        startActivity(intent);
                    }
                }
            });



        backbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        title_bookingappointement.setText("Payment");
    }

}
