package snow.app.ideelee.vehical_module.vehicle;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

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
import snow.app.ideelee.R;
import snow.app.ideelee.api_request_retrofit.ApiService;
import snow.app.ideelee.api_request_retrofit.retrofit_client.ApiClient;
import snow.app.ideelee.extrafiles.BaseActivity;
import snow.app.ideelee.filter.SubSubCatFilterationAdapter;
import snow.app.ideelee.fooddelivery.restaurantsmod.RestaurantsList;
import snow.app.ideelee.responses.ondemandserviceproviderlistres.GetOnDemandProvidersListRes;
import snow.app.ideelee.responses.ondemandserviceproviderlistres.Servicelist;
import snow.app.ideelee.responses.subsubcatfileration.SubSubCatFilterationRes;
import snow.app.ideelee.responses.subsubcatfileration.Subcatdatum;
import snow.app.ideelee.vehical_module.vehicle.adapters.VehicalListAdapter;


public class VehicalListing extends BaseActivity {

    List<Servicelist> serviceproviderlist;
    List<Subcatdatum> subcatdatumList;
    TagGroup mTagGroup;
    @BindView
            (R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.backbutton1)
    ImageView imageView;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.relevance)
    TextView relevance;
    @BindView(R.id.filter)
    TextView filter;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    VehicalListAdapter adapter;
    String userid, token, parentid, subparentid;
    HashMap<String, String> map;
    ApiService apiService;
    ApiService apiService1;
    TextView textView;
    public VehicalListing() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_fragment);
        ButterKnife.bind(this);

//        setSupportActionBar(toolbar);
          textView = (TextView) toolbar.findViewById(R.id.title_bookingappointement);

        apiService = ApiClient.getClient(VehicalListing.this)
                .create(ApiService.class);

        apiService1 = ApiClient.getClient(VehicalListing.this)
                .create(ApiService.class);
        SharedPreferences prefs = getSharedPreferences("Login", MODE_PRIVATE);
        userid = prefs.getString("userid", "0");
        token = prefs.getString("token", "0");

        parentid = getIntent().getStringExtra("parentid");
        subparentid = getIntent().getStringExtra("subparentid");


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        serviceproviderlist = new ArrayList<>();
        subcatdatumList = new ArrayList<>();

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

//        Picasso.with(this).load("https://www.training.com.au/wp-content/uploads/plumbing-courses.png").resize(width,width/2).into(img);

        adapter = new VehicalListAdapter(this, serviceproviderlist);
        recyclerView.setAdapter(adapter);
        relevance.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
/*                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(VehicalListing.this, relevance);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.relevance_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(VehicalListing.this, "You Clicked : " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });

                popup.show();//showing popup menu*/
            }
        });

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initiatePopupwindow(v);
            }
        });
        handleOnDemandServiceProviderList();

    }

    @Override
    public void onBackPressed() {
        finish();

    }

    public void initiatePopupwindow(View v) {

        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.filter_menu, (ViewGroup) v.findViewById(R.id.linearlayout_filter));
        final PopupWindow pw = new PopupWindow(layout, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        pw.showAtLocation(v, Gravity.RIGHT, 0, 0);
        View container = (View) pw.getContentView().getRootView();
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams p = (WindowManager.LayoutParams) container.getLayoutParams();
        p.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        p.dimAmount = 0.6f;
        wm.updateViewLayout(container, p);
        ImageView cancel = layout.findViewById(R.id.cancel);

       RecyclerView recyclerView_filter;
        recyclerView_filter=layout.findViewById(R.id.rv_filteration);
        recyclerView_filter.setHasFixedSize(true);
        recyclerView_filter.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        SubSubCatFilterationAdapter adapter = new SubSubCatFilterationAdapter(VehicalListing.this, subcatdatumList);
        recyclerView_filter.setAdapter(adapter);

        handleGetSubSubCatFilterationList(adapter);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pw.dismiss();
            }
        });

    }


    public void getOnDemandserviceproviderList(HashMap<String, String> map) {
        createProgressDialog();

        Observer<GetOnDemandProvidersListRes> observer = apiService.getOnDemandServiceProviderList(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<GetOnDemandProvidersListRes>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GetOnDemandProvidersListRes res) {
                        if (res.getStatus()) {
                            Toast.makeText(VehicalListing.this, res.getMessage(), Toast.LENGTH_SHORT).show();

                            //  for (int i = 0; i < res.getHomescreendata().getParentCatArray().size(); i++) {

                            Picasso.with(VehicalListing.this).
                                    load(res.getServicedata().getHeaderdata().getBannerImage()).into(img);
                             textView.setText(res.getServicedata().getHeaderdata().getTitle());
                            serviceproviderlist.addAll(res.getServicedata().getServicelist());

                            adapter.notifyDataSetChanged();

                            //add more on  the last the position


                        } else {
                            Toast.makeText(VehicalListing.this, res.getMessage(), Toast.LENGTH_SHORT).show();
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


    private void handleOnDemandServiceProviderList() {
        map = new HashMap<>();


        map.put("userid", userid);
        map.put("token", token);
        map.put("parentid", parentid);
        map.put("subparentid", subparentid);


        getOnDemandserviceproviderList(map);
    }


    public void getSubSubCatFilterationList(HashMap<String, String> map, SubSubCatFilterationAdapter adapter_filter) {
        createProgressDialog();

        Observer<SubSubCatFilterationRes> observer = apiService1.getSubSubCatFilterationList(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<SubSubCatFilterationRes>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SubSubCatFilterationRes res) {
                        if (res.getStatus()) {
                            Toast.makeText(VehicalListing.this, res.getMessage(), Toast.LENGTH_SHORT).show();

                            //  for (int i = 0; i < res.getHomescreendata().getParentCatArray().size(); i++) {


                            subcatdatumList.addAll(res.getSubcatdata());

                            adapter_filter.notifyDataSetChanged();

                            //add more on  the last the position


                        } else {
                            Toast.makeText(VehicalListing.this, res.getMessage(), Toast.LENGTH_SHORT).show();
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


    private void handleGetSubSubCatFilterationList(SubSubCatFilterationAdapter adapter_filter) {
        map = new HashMap<>();

        map.put("userid", userid);
        map.put("token", token);
        map.put("parentid", parentid);
        map.put("subparentid", subparentid);


        getSubSubCatFilterationList(map,adapter_filter);
    }
}
