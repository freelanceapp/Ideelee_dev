package snow.app.ideelee.fooddelivery.restdetails;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import snow.app.ideelee.AppUtils.RoundedTransformation;
import snow.app.ideelee.R;
import snow.app.ideelee.fooddelivery.cart.CartActivity;
import snow.app.ideelee.fooddelivery.restdetails.adapters.FoodItemAdapter;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class RestDetailsActivity extends AppCompatActivity {

    @BindView
            (R.id.breakfast_rv)
    RecyclerView breakfast_rv;
    @BindView(R.id.lunch_rv)
    RecyclerView lunch_rv;
    FoodItemAdapter foodItemAdapter;
    FoodItemAdapter foodItemAdapter1;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_details);
        ButterKnife.bind(this);
        lunch_rv.setLayoutManager(new LinearLayoutManager(this));
        breakfast_rv.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<String> data = new ArrayList<>();
        foodItemAdapter = new FoodItemAdapter(data, this, 0);
        foodItemAdapter1 = new FoodItemAdapter(data, this, 0);
        breakfast_rv.setAdapter(foodItemAdapter);
        lunch_rv.setAdapter(foodItemAdapter1);
        /*breakfast_rv.setNestedScrollingEnabled(false);
        lunch_rv.setNestedScrollingEnabled(false);*/
        title_.setText("The Pepper Mockingbird");
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        Picasso.with(this)
                .load("https://cdn.pixabay.com/photo/2015/04/08/13/13/food-712665_960_720.jpg")
                .resize(width, width / 2)
                .transform(new RoundedTransformation(15, 15))

                .into(img);
        backbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
    public void onBackPressed() {

        super.onBackPressed();
    }

}
