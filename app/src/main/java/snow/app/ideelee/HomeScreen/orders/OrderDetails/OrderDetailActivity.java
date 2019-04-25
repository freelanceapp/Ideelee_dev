package snow.app.ideelee.HomeScreen.orders.OrderDetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import snow.app.ideelee.AddAddress;
import snow.app.ideelee.LiveTracking;
import snow.app.ideelee.R;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class OrderDetailActivity extends AppCompatActivity {
    ImageView img;
    TextView title_bookingappointement;
    ImageView backbutton1;
    TextView status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        img = (ImageView) findViewById(R.id.img);
        backbutton1 = (ImageView) findViewById(R.id.backbutton1);
        title_bookingappointement = (TextView) findViewById(R.id.title_bookingappointement);
        status = (TextView) findViewById(R.id.status);
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        title_bookingappointement.setText("Order detail");
        Picasso.with(this)
                .load("https://stmedia.stimg.co/KING6.JPG")
                .resize(width / 6, width / 6)
/*
                .transform(new CircleTransform())
*/
                .centerCrop()
                .into(img);

          backbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrderDetailActivity.this, LiveTracking.class));
            }
        });
    }

}