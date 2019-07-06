package snow.app.ideelee.coupons;

import android.app.Activity;
import android.content.SharedPreferences;
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
import snow.app.ideelee.CouponActivity;
import snow.app.ideelee.HomeScreen.Adapters.CouponAdapter;
import snow.app.ideelee.R;
import snow.app.ideelee.api_request_retrofit.ApiService;
import snow.app.ideelee.api_request_retrofit.retrofit_client.ApiClient;
import snow.app.ideelee.coupons.adapters.CouponCatAdapter;
import snow.app.ideelee.extrafiles.BaseActivity;
import snow.app.ideelee.responses.couponcatres.CouponCatRes;
import snow.app.ideelee.responses.couponcatres.Couponcategorydatum;


public class SelectCouponCat extends BaseActivity {
    @BindView
            (R.id.recyclerview)
    RecyclerView recyclerView;
    ArrayList<String> serviceproviderlist;
    @BindView(R.id.backbutton1)
    ImageView backbutton1;
    ApiService apiService;
    String userid,token,servicetype;
    HashMap<String, String> map;
    List<Couponcategorydatum> couponcategorydatumList;
    CouponCatAdapter adapter;
    @BindView(R.id.title_bookingappointement)
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_coupon_cat);
        ButterKnife.bind(this);
        title.setText("Select Coupon Category");
        couponcategorydatumList=new ArrayList<>();
        servicetype=getIntent().getStringExtra("servicetype");
        apiService = ApiClient.getClient(SelectCouponCat.this)
                .create(ApiService.class);
        SharedPreferences prefs =  getSharedPreferences("Login", MODE_PRIVATE);
        userid = prefs.getString("userid", "0");
        token = prefs.getString("token", "0");
        backbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(SelectCouponCat.this, LinearLayoutManager.VERTICAL, false));


          adapter = new CouponCatAdapter(SelectCouponCat.this, couponcategorydatumList);
        recyclerView.setAdapter(adapter);
        handleHomeSceenData();
    }




    public void getCouponCatData(HashMap<String, String> map) {
        createProgressDialog();

        Observer<CouponCatRes> observer = apiService.getCouponsCat(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<CouponCatRes>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CouponCatRes res) {
                        if (res.getStatus()) {
                            Toast.makeText( SelectCouponCat.this, res.getMessage(), Toast.LENGTH_SHORT).show();

                            //  for (int i = 0; i < res.getHomescreendata().getParentCatArray().size(); i++) {

                            couponcategorydatumList.addAll(res.getCouponcategorydata());

                            adapter.notifyDataSetChanged();

                            //add more on  the last the position








                        } else {
                            Toast.makeText( SelectCouponCat.this, res.getMessage(), Toast.LENGTH_SHORT).show();
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


    private void handleHomeSceenData() {
        map = new HashMap<>();


        map.put("userid", userid);
        map.put("token", token);
        map.put("servicetype", servicetype);
        map.put("category_id", "");


        getCouponCatData(map);
    }
}
