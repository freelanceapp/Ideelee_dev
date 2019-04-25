package snow.app.ideelee;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import snow.app.ideelee.HomeScreen.Adapters.CategoriesAdapter;
import snow.app.ideelee.HomeScreen.Adapters.MainCategory;
import snow.app.ideelee.HomeScreen.ServiceActivity;
import snow.app.ideelee.fooddelivery.restaurantsmod.RestaurantsList;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Categories extends AppCompatActivity {
    CategoriesAdapter adapterViewAndroid;
    GridView gridView_category;
    ImageView backbutton1;
    TextView title_bookingappointement;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        backbutton1=findViewById(R.id.back);
        title_bookingappointement=(TextView) findViewById(R.id.title_bookingappointement);
        gridView_category = (GridView) findViewById(R.id.grid_view_categories);
        final String[] gridViewString = {
                getString(R.string.electrician), getString(R.string.maids), getString(R.string.carwash),
                getString(R.string.massage), getString(R.string.babysitting), getString(R.string.towing),
                getString(R.string.carpentar), getString(R.string.homepainting),
                getString(R.string.electrician) ,getString(R.string.maids), getString(R.string.carwash),
                getString(R.string.massage), getString(R.string.babysitting), getString(R.string.towing),
                getString(R.string.carpentar), getString(R.string.homepainting),
                getString(R.string.electrician), getString(R.string.maids), getString(R.string.carwash),
                getString(R.string.massage), getString(R.string.babysitting), getString(R.string.towing),
                getString(R.string.carpentar), getString(R.string.homepainting),
                getString(R.string.electrician) ,getString(R.string.maids), getString(R.string.carwash),
                getString(R.string.massage), getString(R.string.babysitting), getString(R.string.towing),
                getString(R.string.carpentar), getString(R.string.homepainting),

        };
        int[] gridViewImageId = {
                R.drawable.electrician, R.drawable.maids, R.drawable.car_wash, R.drawable.massage,
                R.drawable.baby_sitting, R.drawable.towing,
                R.drawable.carpanter, R.drawable.painting,R.drawable.electrician ,
                R.drawable.maids, R.drawable.car_wash, R.drawable.massage,
                R.drawable.baby_sitting, R.drawable.towing,
                R.drawable.carpanter, R.drawable.painting,
                R.drawable.electrician, R.drawable.maids, R.drawable.car_wash, R.drawable.massage,
                R.drawable.baby_sitting, R.drawable.towing,
                R.drawable.carpanter, R.drawable.painting,R.drawable.electrician ,
                R.drawable.maids, R.drawable.car_wash, R.drawable.massage,
                R.drawable.baby_sitting, R.drawable.towing,
                R.drawable.carpanter, R.drawable.painting,


        };
        adapterViewAndroid = new CategoriesAdapter(Categories.this, gridViewString, gridViewImageId);

        gridView_category.setAdapter(adapterViewAndroid);


        gridView_category.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int i, long id) {

                    startActivity(new Intent(Categories.this, RestaurantsList.class));

                    Toast.makeText(Categories.this, "GridView Item: " + gridViewString[+i],
                            Toast.LENGTH_LONG).show();

                }
        });
        backbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
