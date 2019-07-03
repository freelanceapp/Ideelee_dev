package snow.app.ideelee.HomeScreen;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import snow.app.ideelee.HomeScreen.Adapters.BottomGridAdapter;
import snow.app.ideelee.HomeScreen.Adapters.GridAdapter;
import snow.app.ideelee.HomeScreen.Adapters.MainCategory;
import snow.app.ideelee.HomeScreen.Adapters.ServiceProviderCategoryAdapter;
import snow.app.ideelee.HomeScreen.Adapters.ViewPagerHome;
import snow.app.ideelee.R;
import snow.app.ideelee.api_request_retrofit.ApiService;
import snow.app.ideelee.api_request_retrofit.retrofit_client.ApiClient;
import snow.app.ideelee.responses.homescreenres.Banner;
import snow.app.ideelee.responses.homescreenres.HomeScreenRes;
import snow.app.ideelee.responses.homescreenres.ParentCatArray;
import snow.app.ideelee.responses.homescreenres.PopularProfile;

import static android.content.Context.MODE_PRIVATE;
import static com.facebook.FacebookSdk.getApplicationContext;

public class HomeFragment extends Fragment {
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    @BindView
            (R.id.grid_view_image_text)
    GridView androidGridView;
    MainCategory adapterViewAndroid;
    BottomGridAdapter bottomGridAdapter;
    ViewPager viewpager;
    PagerAdapter adapter;
    ViewPagerHome sliderPagerAdapter;
    List<Banner> slider_image_list;
    int page_position = 0;
    ViewPager viewPager;
    LinearLayout sliderDotspanel;
    String userid, token;
    ApiService apiService;
    HashMap<String, String> map;
    List<ParentCatArray> parentCatArrayArrayList;
    List<PopularProfile> productList;
    //the recyclerview
    @BindView(R.id.rc_toprated)
    RecyclerView rc_toprated;
    @BindView(R.id.recyclerView_grid)
    RecyclerView recyclerView_grid;
    GridAdapter customAdapter;
    ServiceProviderCategoryAdapter adapter_toprated;
    private ViewPager vp_slider;
    private LinearLayout ll_dots;
    private TextView[] dots;
    private int dotscount;
    private Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, v);


        SharedPreferences prefs = getActivity().getSharedPreferences("Login", MODE_PRIVATE);
        userid = prefs.getString("userid", "0");
        token = prefs.getString("token", "0");
        parentCatArrayArrayList = new ArrayList<>();
        apiService = ApiClient.getClient(getActivity())
                .create(ApiService.class);
        handleHomeSceenData();
        productList = new ArrayList<>();
        // init();
        final String[] gridViewString = {
                getString(R.string.buycoupons), getString(R.string.vehicle), getString(R.string.vehiclewash),
                getString(R.string.rentals), getString(R.string.gardening), getString(R.string.eventservices),
                getString(R.string.shoppingdelivery),
                getString(R.string.handymanservices), getString(R.string.moving), getString(R.string.teaching),
                getString(R.string.camping), getString(R.string.more)

        };
        int[] gridViewImageId = {
                R.drawable.coupon_24, R.drawable.car_24, R.drawable.car_wash, R.drawable.rent_24,
                R.drawable.baby_sitting, R.drawable.towing,
                R.drawable.carpanter,
                R.drawable.carpanter, R.drawable.painting, R.drawable.painting, R.drawable.electrician,
                R.drawable.more

        };
        slider_image_list = new ArrayList<>();
        vp_slider = (ViewPager) v.findViewById(R.id.vp_slider);
        sliderPagerAdapter = new ViewPagerHome(getActivity(), slider_image_list);
        vp_slider.setAdapter(sliderPagerAdapter);
        ll_dots = (LinearLayout) v.findViewById(R.id.ll_dots);
        //categories gridview
        adapterViewAndroid = new MainCategory(getActivity(), gridViewString, gridViewImageId);
        androidGridView.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
        androidGridView.setAdapter(adapterViewAndroid);
        // get the reference of RecyclerView

// set a GridLayoutManager with default vertical orientation and 3 number of columns
        recyclerView_grid.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 4, LinearLayoutManager.VERTICAL, false);
        recyclerView_grid.setLayoutManager(gridLayoutManager);
        customAdapter = new GridAdapter(getActivity(), parentCatArrayArrayList);
        recyclerView_grid.setAdapter(customAdapter);
//
//        bottomGridAdapter = new BottomGridAdapter(getActivity(), gridViewString, gridViewImageId);
//        recyclerView.setHasFixedSize(true);
//
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        //initializing the productlist

        // viewpage code
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

//top rated adapter

        rc_toprated.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        adapter_toprated = new ServiceProviderCategoryAdapter(getActivity(), productList, width);
        rc_toprated.setAdapter(adapter_toprated);
        setDynamicHeight(androidGridView);


// method for adding indicators
        addBottomDots(0);

        final Handler handler = new Handler();

        final Runnable update = new Runnable() {
            public void run() {

                try {
                    if (page_position == slider_image_list.size()) {
                        page_position = 0;
                    } else {
                        page_position = page_position + 1;
                    }
                    vp_slider.setCurrentItem(page_position, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        new Timer().schedule(new TimerTask() {

            @Override
            public void run() {
                handler.post(update);
            }
        }, 100, 5000);
        // viewpager code ends
        vp_slider.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                addBottomDots(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        // recycler view items

//
//        productList.add(
//                new ServiceProvider(
//                        getResources().getString(R.string.electrician),
//                        4.5,
//                        R.drawable.img
//                ));
//
//        productList.add(
//                new ServiceProvider(
//                        getResources().getString(R.string.plumber),
//                        5.0,
//                        R.drawable.img
//                ));
//
//        productList.add(
//                new ServiceProvider(
//                        getResources().getString(R.string.carpentar),
//                        4.6,
//                        R.drawable.img));
        //


//        ViewGroup.LayoutParams layoutParams = androidGridView.getLayoutParams();
//        layoutParams.height = width/2; //this is in pixels
//        androidGridView.setLayoutParams(layoutParams);
//        androidGridView.setColumnWidth(width/4);

        return v;
    }

    private void init() {


//Add few items to slider_image_list ,this should contain url of images which should be displayed in slider
// here i am adding few sample image links, you can add your own

//        slider_image_list.add("http://images.all-free-download.com/images/graphiclarge/mountain_bongo_animal_mammal_220289.jpg");
//        slider_image_list.add("http://images.all-free-download.com/images/graphiclarge/bird_mountain_bird_animal_226401.jpg");
//        slider_image_list.add("http://images.all-free-download.com/images/graphiclarge/mountain_bongo_animal_mammal_220289.jpg");


        vp_slider.setAdapter(sliderPagerAdapter);


    }

    private void addBottomDots(int currentPage) {
        try {
            dots = new TextView[slider_image_list.size()];
            ll_dots.removeAllViews();
            for (int i = 0; i < dots.length; i++) {
                dots[i] = new TextView(getActivity());
                dots[i].setText(Html.fromHtml("&#8226;"));
                dots[i].setTextSize(35);
                dots[i].setTextColor(getActivity().getResources().getColor(R.color.navyblue));
                ll_dots.addView(dots[i]);
            }

            if (dots.length > 0)
                dots[currentPage].setTextColor(getActivity().getResources().getColor(R.color.colorPrimary));
        } catch (Exception e) {
            Log.e("error", "==" + e);
        }
    }

    private void setDynamicHeight(GridView gridView) {
        ListAdapter gridViewAdapter = gridView.getAdapter();
        if (gridViewAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        int items = gridViewAdapter.getCount();
        int rows = 0;

        View listItem = gridViewAdapter.getView(0, null, gridView);
        listItem.measure(0, 0);
        totalHeight = listItem.getMeasuredHeight();

        float x = 1;
        if (items > 5) {
            x = items / 5;
            rows = (int) (x + 1);
            totalHeight *= rows;
        }

        ViewGroup.LayoutParams params = gridView.getLayoutParams();
        params.height = totalHeight;
        gridView.setLayoutParams(params);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }


    public void getHomeScreenData(HashMap<String, String> map) {
        // showProgress();

        Observer<HomeScreenRes> observer = apiService.getHomeScreenData(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<HomeScreenRes>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeScreenRes res) {
                        if (res.getStatus()) {
                            Toast.makeText(getActivity(), res.getMessage(), Toast.LENGTH_SHORT).show();

                            //  for (int i = 0; i < res.getHomescreendata().getParentCatArray().size(); i++) {

                            parentCatArrayArrayList.addAll(res.getHomescreendata().getParentCatArray());

                            ParentCatArray parentCatArray=new ParentCatArray();
                            parentCatArray.setName("More");


                          parentCatArrayArrayList.add(parentCatArray);
                            slider_image_list.addAll(res.getHomescreendata().getBanners());
                            productList.addAll(res.getHomescreendata().getPopularProfile());
                            customAdapter.notifyDataSetChanged();
                            sliderPagerAdapter.notifyDataSetChanged();
                            adapter_toprated.notifyDataSetChanged();

                            //add more on  the last the position








                        } else {
                            Toast.makeText(getActivity(), res.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {


                    }

                    @Override
                    public void onComplete() {


                    }
                });


    }


    private void handleHomeSceenData() {
        map = new HashMap<>();

        map.put("userid", userid);
        map.put("token", token);


        getHomeScreenData(map);
    }


}
