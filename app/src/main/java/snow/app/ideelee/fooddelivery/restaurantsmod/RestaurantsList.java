package snow.app.ideelee.fooddelivery.restaurantsmod;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import snow.app.ideelee.R;
import snow.app.ideelee.fooddelivery.restaurantsmod.adapter.RestaurantsAdapter;

public class RestaurantsList extends AppCompatActivity {
ImageView backbutton1;
TextView title_bookingappointement;
RecyclerView rv_list;
    RestaurantsAdapter restaurantsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants_list);
        backbutton1=(ImageView)findViewById(R.id.backbutton1);
        title_bookingappointement=(TextView)findViewById(R.id.title_bookingappointement);
        rv_list=(RecyclerView)findViewById(R.id.rv_list);

        backbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        title_bookingappointement.setText("Restaurants");
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        rv_list.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<String>data= new ArrayList<>();
        restaurantsAdapter= new RestaurantsAdapter(data,this,width);
        rv_list.setAdapter(restaurantsAdapter);
    }
}
