package snow.app.ideelee.HomeScreen.orders.OrderDetails;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import snow.app.ideelee.AddAddress;
import snow.app.ideelee.LiveTracking;
import snow.app.ideelee.R;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class OrderDetailActivity extends AppCompatActivity {
    @BindView
            (R.id.img)
    ImageView img;
    @BindView(R.id.title_bookingappointement)
    TextView title_bookingappointement;
    @BindView(R.id.backbutton1)
    ImageView backbutton1;
    @BindView(R.id.status)
    TextView status;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        ButterKnife.bind(this);
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        progressDialog = new ProgressDialog(OrderDetailActivity.this);
        progressDialog.getWindow()
                .setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        progressDialog.setContentView(R.layout.progressdialoglayout);


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

    @Override
    public void onBackPressed() {

        super.onBackPressed();
        //   progressDialog.dismiss();

    }
}
