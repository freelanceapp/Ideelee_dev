package snow.app.ideelee;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class CouponDetails extends AppCompatActivity {
    ImageView backbutton1;
    Button btn_buycoupon;
    TextView title_bookingappointement,coupondetail;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon_details);
        backbutton1=findViewById(R.id.backbutton1);
        title_bookingappointement=(TextView) findViewById(R.id.title_bookingappointement);
        coupondetail=findViewById(R.id.coupondetails);
        backbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
btn_buycoupon=findViewById(R.id.btn_buycoupon);
btn_buycoupon.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(CouponDetails.this,PaymentSuccessfulActivity.class);
        intent.putExtra("key","Done");
        startActivity(intent);
    }
});
        btn_buycoupon.setText(Html.fromHtml("<span style=\"color: #ffcc00;\">$ 10.00</span> <span style=\"color: #ffffff;\">Buy Now</span>", Html.FROM_HTML_MODE_COMPACT));
        title_bookingappointement.setText("Coupon Details");


        coupondetail.setText(Html.fromHtml("<p style=\"color: #5e9ca0;\"><span style=\"color: #000000;\">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat</span></p>\n" +
                "<ul>\n" +
                "<li><span style=\"color: #999999;\">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor</span></li>\n" +
                "<li><span style=\"color: #999999;\">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor</span></li>\n" +
                "<li><span style=\"color: #999999;\">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor</span></li>\n" +
                "<li><span style=\"color: #999999;\">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor</span></li>\n" +
                "<li><span style=\"color: #999999;\">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor</span></li>\n" +
                "<li><span style=\"color: #999999;\">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor</span></li>\n" +
                "</ul>\n" +
                "<p>&nbsp;</p>", Html.FROM_HTML_MODE_COMPACT));

    }
}