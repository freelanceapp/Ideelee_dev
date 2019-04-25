package snow.app.ideelee;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import snow.app.ideelee.HomeScreen.HomeNavigation;

public class RateServiceProviderActivity extends AppCompatActivity {
RatingBar ratingBar;
Button btn_submit;
    ImageView backbutton1;
    TextView title_bookingappointement;
    LinearLayout linearLayout_profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_service_provider);
        btn_submit=findViewById(R.id.ux_btn_submit);
        ratingBar=findViewById(R.id.ratingbar_rate);
        backbutton1=findViewById(R.id.backbutton1);
        linearLayout_profile=findViewById(R.id.linearlayout_profile);
        title_bookingappointement=(TextView) findViewById(R.id.title_bookingappointement);
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
