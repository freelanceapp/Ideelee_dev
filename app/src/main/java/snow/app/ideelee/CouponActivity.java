package snow.app.ideelee;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
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
import me.gujun.android.taggroup.TagGroup;
import snow.app.ideelee.HomeScreen.Adapters.CouponAdapter;
import snow.app.ideelee.HomeScreen.Adapters.ServiceJobAdapter;
import snow.app.ideelee.api_request_retrofit.ApiService;
import snow.app.ideelee.responses.homescreenres.HomeScreenRes;
import snow.app.ideelee.responses.homescreenres.ParentCatArray;

public class CouponActivity extends AppCompatActivity {
@BindView(R.id.rv_coupons) RecyclerView rv_coupons;
    List<String> serviceproviderlist;
    ApiService apiService;
    HashMap<String, String> map;

    CouponAdapter adapter;
    @BindView(R.id.backbutton1) ImageView backbutton1;
   @BindView(R.id.title_bookingappointement) TextView title_bookingappointement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon);
        ButterKnife.bind(this);
        int[] gridViewImageId = {
                R.drawable.c1, R.drawable.c2, R.drawable.c3, R.drawable.c1, R.drawable.c2, R.drawable.c3
        };
        rv_coupons.setLayoutManager(new LinearLayoutManager(CouponActivity.this,
                LinearLayoutManager.VERTICAL, false));
       rv_coupons.setFocusableInTouchMode(true);
          adapter = new CouponAdapter(CouponActivity.this, gridViewImageId);
        rv_coupons.setAdapter(adapter);
        backbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        title_bookingappointement.setText("Coupons");
    }




}
