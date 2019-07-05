package snow.app.ideelee.vehical_module.vehicle;

import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import snow.app.ideelee.R;
import snow.app.ideelee.api_request_retrofit.ApiService;
import snow.app.ideelee.api_request_retrofit.retrofit_client.ApiClient;
import snow.app.ideelee.coupons.SelectCouponCat;
import snow.app.ideelee.extrafiles.BaseActivity;
import snow.app.ideelee.responses.couponcatres.CouponCatRes;
import snow.app.ideelee.responses.ondemandservicessubcatres.OnDemandSubCatRes;
import snow.app.ideelee.responses.ondemandservicessubcatres.ServicesDetail;
import snow.app.ideelee.vehical_module.vehicle.adapters.VehicleCategoriesAdapter;

public class VehicleCategories extends BaseActivity {
    @BindView(R.id.rv_vehicle_cat)
    RecyclerView recyclerView;
    ArrayList<ServicesDetail> servicesDetailArrayList;
    @BindView(R.id.title_bookingappointement)
    TextView tv;
    String userid,token,servicetype,cat_id;
    HashMap<String, String> map;
    ApiService apiService;
    @BindView(R.id.backbutton1)
    ImageView backbutton1;
    @BindView(R.id.cat_img)
    ImageView cat_img;
    VehicleCategoriesAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_categories);
        ButterKnife.bind(this);



        servicesDetailArrayList=new ArrayList<>();
        servicetype=getIntent().getStringExtra("servicetype");
        cat_id=getIntent().getStringExtra("cat");
        apiService = ApiClient.getClient(VehicleCategories.this)
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
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false));



          adapter = new VehicleCategoriesAdapter(VehicleCategories.this, servicesDetailArrayList);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);
        handleHomeSceenData();
    }




    public void getCouponCatData(HashMap<String, String> map) {
        createProgressDialog();

        Observer<OnDemandSubCatRes> observer = apiService.getOnDemandSubCat(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<OnDemandSubCatRes>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(OnDemandSubCatRes res) {
                        if (res.getStatus()) {
                            Toast.makeText( VehicleCategories.this, res.getMessage(), Toast.LENGTH_SHORT).show();

                            //  for (int i = 0; i < res.getHomescreendata().getParentCatArray().size(); i++) {

                            Picasso.with(VehicleCategories.this).
                                    load(res.getServicesdata().getParentdetail().getBannerImage()).into(cat_img);
tv.setText(res.getServicesdata().getParentdetail().getName());
                              servicesDetailArrayList.addAll(res.getServicesdata().getServicesDetail());

                            adapter.notifyDataSetChanged();

                            //add more on  the last the position








                        } else {
                            Toast.makeText( VehicleCategories.this, res.getMessage(), Toast.LENGTH_SHORT).show();
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
        map.put("category_id", cat_id);


        getCouponCatData(map);
    }

}
