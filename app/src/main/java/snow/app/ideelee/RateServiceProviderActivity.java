package snow.app.ideelee;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import snow.app.ideelee.HomeScreen.HomeNavigation;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class RateServiceProviderActivity extends AppCompatActivity {
@BindView(R.id.ratingbar_rate) RatingBar ratingBar;
@BindView(R.id.ux_btn_submit) Button btn_submit;
   @BindView(R.id.backbutton1) ImageView backbutton1;
    @BindView(R.id.title_bookingappointement) TextView title_bookingappointement;
    @BindView(R.id.linearlayout_profile) LinearLayout linearLayout_profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_service_provider);
        ButterKnife.bind(this);
        ratingBar.setRating(4);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RateServiceProviderActivity.this, HomeNavigation.class);
                intent.putExtra("key","wallet");
                startActivity(intent);
            }
        });
        backbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        title_bookingappointement.setText("");
        linearLayout_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RateServiceProviderActivity.this, ServiceProfile.class);

                startActivity(intent);
            }
        });
    }

}
