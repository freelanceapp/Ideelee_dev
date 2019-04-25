package snow.app.ideelee.HomeScreen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;



import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import snow.app.ideelee.Categories;
import snow.app.ideelee.HomeScreen.Adapters.BottomGridAdapter;
import snow.app.ideelee.HomeScreen.Adapters.MainCategory;
import snow.app.ideelee.HomeScreen.Adapters.ServiceProviderCategoryAdapter;
import snow.app.ideelee.HomeScreen.Adapters.ViewPagerHome;
import snow.app.ideelee.HomeScreen.Modals.ServiceProvider;
import snow.app.ideelee.R;

public class HomeFragment extends Fragment {
    GridView androidGridView;
     MainCategory adapterViewAndroid;
    BottomGridAdapter bottomGridAdapter;
    ViewPager viewpager;
    PagerAdapter adapter;
    private ViewPager vp_slider;
    private LinearLayout ll_dots;
    ViewPagerHome sliderPagerAdapter;
    ArrayList<String> slider_image_list;
    private TextView[] dots;
    int page_position = 0;
    ViewPager viewPager;
    LinearLayout sliderDotspanel;
    private int dotscount;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;

    List<ServiceProvider> productList;






    //the recyclerview
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);



         final String[] gridViewString = {
                getString(R.string.electrician), getString(R.string.maids), getString(R.string.carwash),
                getString(R.string.massage), getString(R.string.babysitting), getString(R.string.towing),
                getString(R.string.carpentar),
                getString(R.string.carpentar), getString(R.string.homepainting), getString(R.string.homepainting),
                 getString(R.string.homepainting), getString(R.string.more)

        };
        int[] gridViewImageId = {
                R.drawable.electrician, R.drawable.maids, R.drawable.car_wash, R.drawable.massage,
                R.drawable.baby_sitting, R.drawable.towing,
                R.drawable.carpanter,
                R.drawable.carpanter, R.drawable.painting, R.drawable.painting, R.drawable.painting, R.drawable.more

        };



        //categories gridview

        vp_slider = (ViewPager) v.findViewById(R.id.vp_slider);
        ll_dots = (LinearLayout) v.findViewById(R.id.ll_dots);
        adapterViewAndroid = new MainCategory(getActivity(), gridViewString, gridViewImageId);
        androidGridView = (GridView) v.findViewById(R.id.grid_view_image_text);
        androidGridView.setAdapter(adapterViewAndroid);



        bottomGridAdapter = new BottomGridAdapter(getActivity(), gridViewString, gridViewImageId);
        androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int i, long id) {
if (i==gridViewString.length-1){
    startActivity(new Intent(getActivity(), Categories.class));
}else{
                    startActivity(new Intent(getActivity(), ServiceActivity.class));

                    Toast.makeText(getActivity(), "GridView Item: " + gridViewString[+i],
                            Toast.LENGTH_LONG).show();

            }}
        });

        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        //initializing the productlist
        productList = new ArrayList<>();

        // viewpage code

        init();

// method for adding indicators
        addBottomDots(0);

        final Handler handler = new Handler();

        final Runnable update = new Runnable() {
            public void run() {
                if (page_position == slider_image_list.size()) {
                    page_position = 0;
                } else {
                    page_position = page_position + 1;
                }
                vp_slider.setCurrentItem(page_position, true);
            }
        };

        new Timer().schedule(new TimerTask() {

            @Override
            public void run() {
                handler.post(update);
            }
        }, 100, 5000);
        // viewpager code ends


        // recycler view items


        productList.add(
                new ServiceProvider(
                        getResources().getString(R.string.electrician),
                        4.5,
                        R.drawable.img
                ));

        productList.add(
                new ServiceProvider(
                        getResources().getString(R.string.plumber),
                        5.0,
                        R.drawable.img
                ));

        productList.add(
                new ServiceProvider(
                        getResources().getString(R.string.carpentar),
                        4.6,
                        R.drawable.img));
        //
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        ServiceProviderCategoryAdapter adapter = new ServiceProviderCategoryAdapter(getActivity(), productList, width);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);

setDynamicHeight(androidGridView);
        return v;
    }

    private void init() {


        slider_image_list = new ArrayList<>();

//Add few items to slider_image_list ,this should contain url of images which should be displayed in slider
// here i am adding few sample image links, you can add your own

        slider_image_list.add("http://images.all-free-download.com/images/graphiclarge/mountain_bongo_animal_mammal_220289.jpg");
        slider_image_list.add("http://images.all-free-download.com/images/graphiclarge/bird_mountain_bird_animal_226401.jpg");
        slider_image_list.add("http://images.all-free-download.com/images/graphiclarge/mountain_bongo_animal_mammal_220289.jpg");


        sliderPagerAdapter = new ViewPagerHome(getActivity(), slider_image_list);
        vp_slider.setAdapter(sliderPagerAdapter);

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
        if( items > 5 ){
            x = items/5;
            rows = (int) (x + 1);
            totalHeight *= rows;
        }

        ViewGroup.LayoutParams params = gridView.getLayoutParams();
        params.height = totalHeight;
        gridView.setLayoutParams(params);
    }
}
