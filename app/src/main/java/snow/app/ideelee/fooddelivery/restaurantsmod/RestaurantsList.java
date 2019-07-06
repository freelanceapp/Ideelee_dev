package snow.app.ideelee.fooddelivery.restaurantsmod;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupMenu;
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
import snow.app.ideelee.HomeScreen.ServiceActivity;
import snow.app.ideelee.R;
import snow.app.ideelee.api_request_retrofit.ApiService;
import snow.app.ideelee.api_request_retrofit.retrofit_client.ApiClient;
import snow.app.ideelee.coupons.SelectCouponCat;
import snow.app.ideelee.extrafiles.BaseActivity;
import snow.app.ideelee.fooddelivery.restaurantsmod.adapter.RestaurantsAdapter;
import snow.app.ideelee.responses.couponcatres.CouponCatRes;
import snow.app.ideelee.responses.couponcatres.Couponcategorydatum;
import snow.app.ideelee.responses.deliverysubcatres.DeliverySubCatRes;
import snow.app.ideelee.responses.deliverysubcatres.StoresDetail;
import snow.app.ideelee.responses.ondemandservicessubcatres.OnDemandSubCatRes;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class RestaurantsList extends BaseActivity {
   @BindView
  (R.id.backbutton1) ImageView backbutton1;
   @BindView(R.id.title_bookingappointement) TextView title_bookingappointement;
   @BindView(R.id.relevance) TextView relevance;
   @BindView(R.id.rv_list) RecyclerView rv_list;
    RestaurantsAdapter restaurantsAdapter;
    ApiService apiService;
    String userid,token,servicetype,cat_id;
    HashMap<String, String> map;
    List<StoresDetail> couponcategorydatumList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants_list);
        ButterKnife.bind(this);
        backbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        couponcategorydatumList=new ArrayList<>();
        servicetype=getIntent().getStringExtra("servicetype");


        cat_id=getIntent().getStringExtra("cat");
        apiService = ApiClient.getClient( RestaurantsList.this)
                .create(ApiService.class);
        SharedPreferences prefs =  getSharedPreferences("Login", MODE_PRIVATE);
        userid = prefs.getString("userid", "0");
        token = prefs.getString("token", "0");
        title_bookingappointement.setText("Restaurants");
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        rv_list.setLayoutManager(new LinearLayoutManager(this));

        restaurantsAdapter = new RestaurantsAdapter(couponcategorydatumList, this, width);
        rv_list.setAdapter(restaurantsAdapter);
        handleOnDeliveryData();
        relevance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(RestaurantsList.this, relevance);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.relevance_menu, popup.getMenu());
                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(RestaurantsList.this, "You Clicked : " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });

                popup.show();//s
            }
        });
    }


    public void getOnDeliveryData(HashMap<String, String> map) {
        createProgressDialog();

        Observer<DeliverySubCatRes> observer = apiService.getDeliverySubCat(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<DeliverySubCatRes>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DeliverySubCatRes res) {
                        if (res.getStatus()) {
                            Toast.makeText( RestaurantsList.this, res.getMessage(), Toast.LENGTH_SHORT).show();

                            //  for (int i = 0; i < res.getHomescreendata().getParentCatArray().size(); i++) {

                            couponcategorydatumList.addAll(res.getServicesdata().getStoresDetail());

                            restaurantsAdapter.notifyDataSetChanged();

                            //add more on  the last the position








                        } else {
                            Toast.makeText( RestaurantsList.this, res.getMessage(), Toast.LENGTH_SHORT).show();
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


    private void handleOnDeliveryData() {
        map = new HashMap<>();


        map.put("userid", userid);
        map.put("token", token);
        map.put("servicetype", servicetype);
        map.put("category_id", cat_id);


        getOnDeliveryData(map);
    }

}
