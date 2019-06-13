package snow.app.ideelee.fooddelivery.restaurantsmod;

import android.content.Context;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import snow.app.ideelee.HomeScreen.ServiceActivity;
import snow.app.ideelee.R;
import snow.app.ideelee.fooddelivery.restaurantsmod.adapter.RestaurantsAdapter;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class RestaurantsList extends AppCompatActivity {
   @BindView
  (R.id.backbutton1) ImageView backbutton1;
   @BindView(R.id.title_bookingappointement) TextView title_bookingappointement;
   @BindView(R.id.relevance) TextView relevance;
   @BindView(R.id.rv_list) RecyclerView rv_list;
    RestaurantsAdapter restaurantsAdapter;

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
        title_bookingappointement.setText("Restaurants");
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        rv_list.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<String> data = new ArrayList<>();
        restaurantsAdapter = new RestaurantsAdapter(data, this, width);
        rv_list.setAdapter(restaurantsAdapter);
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

}
