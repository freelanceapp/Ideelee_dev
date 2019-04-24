package snow.app.ideelee.fooddelivery.cart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import snow.app.ideelee.R;

public class CartActivity extends AppCompatActivity {
ImageView backbutton1;
TextView title_bookingappointement;
ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        backbutton1=(ImageView)findViewById(R.id.backbutton1);
        title_bookingappointement=(TextView) findViewById(R.id.title_bookingappointement);
        img=(ImageView)findViewById(R.id.img);
        backbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        title_bookingappointement.setText("Cart");
        Picasso.with(this)
                .load("https://stmedia.stimg.co/KING6.JPG")
                .resize(width / 6, width / 6)
/*
                .transform(new CircleTransform())
*/
                .centerCrop()
                .into(img);
    }
}
