package snow.app.ideelee.vehicle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import snow.app.ideelee.HomeScreen.Adapters.ServiceProviderCategoryAdapter;
import snow.app.ideelee.R;
import snow.app.ideelee.vehicle.adapters.VehicleCategoriesAdapter;

public class VehicleCategories extends AppCompatActivity {
RecyclerView recyclerView;
ArrayList<String> vehicle_cat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_categories);
        recyclerView = (RecyclerView) findViewById(R.id.rv_vehicle_cat);
        vehicle_cat=new ArrayList<>();
        TextView tv=findViewById(R.id.title_bookingappointement);
        tv.setText("Vehicle Services");
        findViewById(R.id.backbutton1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                true));
        vehicle_cat.add("Aircrafts & Flying Vehicles");
        vehicle_cat.add("Vessels Rental");
        vehicle_cat.add("Motorcycle Rental");
        vehicle_cat.add("Truck Rental");
        vehicle_cat.add("Car Rental");



        VehicleCategoriesAdapter adapter = new VehicleCategoriesAdapter(VehicleCategories.this, vehicle_cat);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);

    }
}
