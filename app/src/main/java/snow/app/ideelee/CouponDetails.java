package snow.app.ideelee;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import snow.app.ideelee.api_request_retrofit.ApiService;
import snow.app.ideelee.api_request_retrofit.retrofit_client.ApiClient;
import snow.app.ideelee.extrafiles.BaseActivity;
import snow.app.ideelee.extrafiles.SessionManager;
import snow.app.ideelee.responses.coupondetails.CouponDetailRes;

public class CouponDetails extends BaseActivity {
    @BindView(R.id.backbutton1)
    ImageView backbutton1;
    @BindView(R.id.img_coupon)
    ImageView img_coupon;
    @BindView(R.id.btn_buycoupon)
    Button btn_buycoupon;
    @BindView(R.id.title_bookingappointement)
    TextView title_bookingappointement;
    @BindView(R.id.coupondetails)
    TextView coupondetail;
    SessionManager sessionManager;
    ApiService apiService;
String couponid;
    HashMap<String, String> map;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon_details);

        ButterKnife.bind(this);

        backbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_buycoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CouponDetails.this, PaymentSuccessfulActivity.class);
                intent.putExtra("key", "Done");
                startActivity(intent);
            }
        });

        handlegetCouponDetails();
/*
        btn_buycoupon.setText(Html.fromHtml("<span style=\"color: #ffcc00;\">$ 10.00</span> <span style=\"color: #ffffff;\">Buy Now</span>", Html.FROM_HTML_MODE_COMPACT));
*/
        title_bookingappointement.setText("Coupon Details");
        apiService = ApiClient.getClient(CouponDetails.this)
                .create(ApiService.class);
        sessionManager = new SessionManager(this);

       /* coupondetail.setText(Html.fromHtml("<p style=\"color: #5e9ca0;\"><span style=\"color: #000000;\">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat</span></p>\n" +
                "<ul>\n" +
                "<li><span style=\"color: #999999;\">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor</span></li>\n" +
                "<li><span style=\"color: #999999;\">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor</span></li>\n" +
                "<li><span style=\"color: #999999;\">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor</span></li>\n" +
                "<li><span style=\"color: #999999;\">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor</span></li>\n" +
                "<li><span style=\"color: #999999;\">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor</span></li>\n" +
                "<li><span style=\"color: #999999;\">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor</span></li>\n" +
                "</ul>\n" +
                "<p>&nbsp;</p>", Html.FROM_HTML_MODE_COMPACT));*/
if (getIntent().hasExtra("couponid")){
      couponid=getIntent().getStringExtra("couponid");
}
    }


    public void getCouponDetails(HashMap<String, String> map) {
        createProgressDialog();

        Observer<CouponDetailRes> observer = apiService.getCouponDetails(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<CouponDetailRes>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CouponDetailRes res) {
                        if (res.getStatus()) {

                            Picasso.with(CouponDetails.this).load(res.getCoupondata().getBanner()).into(img_coupon);
                            coupondetail.setText(res.getCoupondata().getTermConditions());
                            btn_buycoupon.setText("$ " + res.getCoupondata().getPrice());


                        } else {
                            Toast.makeText(CouponDetails.this, res.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        dismissProgressDialog();

                    }

                    @Override
                    public void onComplete() {
                        dismissProgressDialog();

                    }
                });


    }


    private void handlegetCouponDetails() {
        map = new HashMap<>();


        map.put("userid", sessionManager.getKeyId());
        map.put("token", sessionManager.getKeyToken());
        map.put("couponid", couponid);


        getCouponDetails(map);
    }


}
