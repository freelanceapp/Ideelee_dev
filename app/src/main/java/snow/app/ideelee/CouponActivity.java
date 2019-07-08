package snow.app.ideelee;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import snow.app.ideelee.HomeScreen.Adapters.CouponAdapter;
import snow.app.ideelee.api_request_retrofit.ApiService;
import snow.app.ideelee.api_request_retrofit.retrofit_client.ApiClient;
import snow.app.ideelee.extrafiles.BaseActivity;
import snow.app.ideelee.extrafiles.SessionManager;
import snow.app.ideelee.responses.getcouponsres.Coupondatum;
import snow.app.ideelee.responses.getcouponsres.GetCouponsRes;

public class CouponActivity extends BaseActivity {
    @BindView(R.id.rv_coupons)
    RecyclerView rv_coupons;
    List<Coupondatum> serviceproviderlist;
    ApiService apiService;
    HashMap<String, String> map;

    CouponAdapter adapter;
    @BindView(R.id.backbutton1)
    ImageView backbutton1;
    @BindView(R.id.title_bookingappointement)
    TextView title_bookingappointement;
    SessionManager sessionManager;
    String coupcatid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon);
        ButterKnife.bind(this);
        serviceproviderlist = new ArrayList<>();
        rv_coupons.setLayoutManager(new LinearLayoutManager(CouponActivity.this,
                LinearLayoutManager.VERTICAL, false));
        rv_coupons.setFocusableInTouchMode(true);
        adapter = new CouponAdapter(CouponActivity.this, serviceproviderlist);
        rv_coupons.setAdapter(adapter);
        apiService = ApiClient.getClient(CouponActivity.this)
                .create(ApiService.class);
        sessionManager = new SessionManager(this);
        backbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        title_bookingappointement.setText("Coupons");

        if (getIntent().hasExtra("catid")) {
            coupcatid = getIntent().getStringExtra("catid");
        }


        handlegetCouponList();
    }


    public void getCouponList(HashMap<String, String> map) {
        createProgressDialog();

        Observer<GetCouponsRes> observer = apiService.getCoupons(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<GetCouponsRes>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GetCouponsRes res) {
                        if (res.getStatus()) {


                            //  for (int i = 0; i < res.getHomescreendata().getParentCatArray().size(); i++) {

                            serviceproviderlist.addAll(res.getCoupondata());

                            adapter.notifyDataSetChanged();

                            //add more on  the last the position


                        } else {
                            Toast.makeText(CouponActivity.this, res.getMessage(), Toast.LENGTH_SHORT).show();
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


    private void handlegetCouponList() {
        map = new HashMap<>();


        map.put("userid", sessionManager.getKeyId());
        map.put("token", sessionManager.getKeyToken());
        map.put("catid", coupcatid);


        getCouponList(map);
    }


}
