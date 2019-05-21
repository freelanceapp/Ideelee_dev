package snow.app.ideelee.perquantityperfloor;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import me.gujun.android.taggroup.TagGroup;
import snow.app.ideelee.HomeScreen.Modals.ServiceProviderList;
import snow.app.ideelee.R;

import snow.app.ideelee.perquantityperfloor.adapters.MovinglistingAdapter;


public class MovingLiftingListing extends Activity {

    List<ServiceProviderList> serviceproviderlist;
    TagGroup mTagGroup;
    RecyclerView recyclerView;

    public MovingLiftingListing() {
    }

    ImageView imageView;
    ImageView img;
    TextView relevance, filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rentals_subcat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //        setSupportActionBar(toolbar);
        TextView textView = (TextView)toolbar.findViewById(R.id.title_bookingappointement);
        textView.setText("Moving and Lifting Services");
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        relevance = findViewById(R.id.relevance);
        filter = findViewById(R.id.filter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        imageView = findViewById(R.id.backbutton1);
        img = findViewById(R.id.img);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });
        serviceproviderlist = new ArrayList<>();
        serviceproviderlist.add(
                new ServiceProviderList(
                        "Jack Harry",
                        "1 KM",
                        4.5,
                        R.drawable.img
                ));

        serviceproviderlist.add(
                new ServiceProviderList(
                        "Alex",
                        "5 KM",
                        4.5,
                        R.drawable.img
                ));

        serviceproviderlist.add(
                new ServiceProviderList(
                        "Devin",
                        "10 KM",
                        4.5,
                        R.drawable.img));
        serviceproviderlist.add(
                new ServiceProviderList(
                        "William",
                        "67km",
                        4.5,
                        R.drawable.img));
        serviceproviderlist.add(
                new ServiceProviderList(
                        "James",
                        "67km",
                        4.5,
                        R.drawable.img));
        serviceproviderlist.add(
                new ServiceProviderList(
                        "Liam",
                        "67km",
                        4.5,
                        R.drawable.img));
        serviceproviderlist.add(
                new ServiceProviderList(
                        "James",
                        "67km",
                        4.5,
                        R.drawable.img));
        serviceproviderlist.add(
                new ServiceProviderList(
                        "James",
                        "67km",
                        4.5,
                        R.drawable.img));
        serviceproviderlist.add(
                new ServiceProviderList(
                        "Devin",
                        "67km",
                        4.5,
                        R.drawable.img));

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

//        Picasso.with(this).load("https://www.training.com.au/wp-content/uploads/plumbing-courses.png").resize(width,width/2).into(img);

        MovinglistingAdapter adapter = new MovinglistingAdapter(this, serviceproviderlist);
        recyclerView.setAdapter(adapter);
        relevance.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(MovingLiftingListing.this, relevance);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.relevance_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(MovingLiftingListing.this, "You Clicked : " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });

                popup.show();//showing popup menu
            }
        });

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initiatePopupwindow(v);
            }
        });


    }

    @Override
    public void onBackPressed() {
        finish();

    }

    public void initiatePopupwindow(View v) {

        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.vehiclewashfilter, (ViewGroup) v.findViewById(R.id.linearlayout_filter));
        final PopupWindow pw = new PopupWindow(layout, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        pw.showAtLocation(v, Gravity.RIGHT, 0, 0);
        View container = (View) pw.getContentView().getRootView();
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams p = (WindowManager.LayoutParams) container.getLayoutParams();
        p.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        p.dimAmount = 0.6f;
        wm.updateViewLayout(container, p);
        ImageView cancel = layout.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pw.dismiss();
            }
        });

    }

}
