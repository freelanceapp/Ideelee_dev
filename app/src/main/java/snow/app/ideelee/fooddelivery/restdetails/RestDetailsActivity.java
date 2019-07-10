package snow.app.ideelee.fooddelivery.restdetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import snow.app.ideelee.AddAddress;
import snow.app.ideelee.R;
import snow.app.ideelee.api_request_retrofit.ApiService;
import snow.app.ideelee.api_request_retrofit.retrofit_client.ApiClient;
import snow.app.ideelee.extrafiles.BaseActivity;
import snow.app.ideelee.extrafiles.SessionManager;
import snow.app.ideelee.fooddelivery.cart.CartActivity;
import snow.app.ideelee.fooddelivery.restdetails.adapters.FoodItemAdapter;
import snow.app.ideelee.responses.getstoredetailsres.GetStoreDetailsRes;
import snow.app.ideelee.responses.getstoredetailsres.Product;
import snow.app.ideelee.responses.getstoredetailsres.ProductsDetail;


public class RestDetailsActivity extends BaseActivity {


    @BindView(R.id.title_bookingappointement)
    TextView title_;
    @BindView(R.id.menu)
    TextView menu;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.backbutton1)
    ImageView backbutton1;
    PopupWindow mypopupWindow;
    @BindView(R.id.view_cart)
    TextView view_cart;

    @BindView(R.id.rating_)
    TextView rating_;

    @BindView(R.id.phone)
    TextView phone;

    @BindView(R.id.address)
    TextView address;


    @BindView(R.id.recyclers)
    LinearLayout recyclers;
    @BindView(R.id.price)
    TextView price;

    ApiService apiService;
    HashMap<String, String> map;
    List<Product> productList;


    String userid, token, storeid;
    SessionManager sessionManager;
    int size, width, height;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_details);
        ButterKnife.bind(this);
        mContext = this;

        /*breakfast_rv.setNestedScrollingEnabled(false);
        lunch_rv.setNestedScrollingEnabled(false);*/
        productList = new ArrayList<>();

        apiService = ApiClient.getClient(RestDetailsActivity.this)
                .create(ApiService.class);

        sessionManager = new SessionManager(this);
        userid = sessionManager.getKeyId();
        token = sessionManager.getKeyToken();
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;
       /* Picasso.with(this)
                .load("https://cdn.pixabay.com/photo/2015/04/08/13/13/food-712665_960_720.jpg")
                .resize(width, width / 2)
                .transform(new RoundedTransformation(15, 15))

                .into(img);*/


        handlegetStoreDetails();
        backbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initiatePopupwindow(v);
            }
        });

        view_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RestDetailsActivity.this, CartActivity.class));
            }
        });


        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RestDetailsActivity.this, AddAddress.class);
                intent.putExtra("from", "rda");
                startActivityForResult(intent, 3);


            }
        });
        phone.setText(sessionManager.getKeyContactNumber());


    }

    public void initiatePopupwindow(View v) {

        LayoutInflater inflater = (LayoutInflater)
                getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.food_menu_pop, null);


        mypopupWindow = new PopupWindow(view, RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT, false);
        mypopupWindow.showAtLocation(v, Gravity.BOTTOM, 0, 300);
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        View container = (View) mypopupWindow.getContentView().getRootView();
        WindowManager.LayoutParams p = (WindowManager.LayoutParams) container.getLayoutParams();
        p.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        p.dimAmount = 0.6f;
        wm.updateViewLayout(container, p);



        /*

        LayoutInflater inflater=(LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout=inflater.inflate(R.layout.food_menu_pop,null);
            pw=new PopupWindow(layout,ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT,true);
        pw.showAtLocation(v, Gravity.BOTTOM,0,70);
        View container= (View) pw.getContentView().getRootView();
        WindowManager wm=(WindowManager)getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams p=(WindowManager.LayoutParams)container.getLayoutParams();
        p.flags=WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        p.dimAmount=0.6f;
        wm.updateViewLayout(container,p);
        pw.setOutsideTouchable(true);
        pw.setFocusable(true);*/


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == 3) {
            setAddress();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    public void getStoreDetails(HashMap<String, String> map) {
        createProgressDialog();
        Observer<GetStoreDetailsRes> observer = apiService.getStoreDetails(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<GetStoreDetailsRes>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GetStoreDetailsRes res) {
                        if (res.getStatus()) {
                            Toast.makeText(RestDetailsActivity.this, res.getMessage(), Toast.LENGTH_SHORT).show();

                            Glide.with(RestDetailsActivity.this)
                                    .load(res.getStoredata().getStoreImage())
                                    .apply(new RequestOptions().override(width / 4))
                                    .into(img);
                            title_.setText(res.getStoredata().getStoreName());
                            rating_.setText(res.getStoredata().getRating());
                            ArrayList<String> cats = new ArrayList<>();
                            for (int i = 0; i < res.getStoredata().getProducts().size(); i++) {
                                cats.add(res.getStoredata().getProducts().get(i).getCategoryName());
                                View child = getLayoutInflater().inflate(R.layout.row_recycler_store, null);
                                TextView title = (TextView) child.findViewById(R.id.title);
                                ImageView hide = (ImageView) child.findViewById(R.id.hide);
                                RecyclerView rv = (RecyclerView) child.findViewById(R.id.rv);
                                rv.setLayoutManager(new LinearLayoutManager(mContext));
                                rv.setNestedScrollingEnabled(false);
                                title.setText(res.getStoredata().getProducts().get(i).getCategoryName());
                                ArrayList<ProductsDetail> data = new ArrayList<>();
                                try{
                                    data.addAll(res.getStoredata().getProducts().get(i).getProductsDetails());
                                    FoodItemAdapter foodItemAdapter = new FoodItemAdapter(data, mContext, 0);
                                    rv.setAdapter(foodItemAdapter);
                                    rv.setAdapter(foodItemAdapter);
                                    recyclers.addView(child);
                                }catch (Exception e){

                                }



                            }


                        } else {
                            Toast.makeText(RestDetailsActivity.this, res.getMessage(), Toast.LENGTH_SHORT).show();
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


    private void handlegetStoreDetails() {


        if (getIntent().hasExtra("storeid")) {
            storeid = getIntent().getStringExtra("storeid");
        }

        map = new HashMap<>();
        map.put("userid", userid);
        map.put("token", token);
        map.put("storeid", storeid);

        getStoreDetails(map);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setAddress();
    }


    public void setAddress() {

        address.setText(sessionManager.getKeyAddress());


    }
}
