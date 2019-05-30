package snow.app.ideelee.vehical_module.vehicle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import snow.app.ideelee.R;
import snow.app.ideelee.vehical_module.vehicle.adapters.VehicleCategoriesAdapter;

public class VehicleCategories extends AppCompatActivity {
    @BindView(R.id.rv_vehicle_cat)
    RecyclerView recyclerView;
    ArrayList<String> vehicle_cat;
    @BindView(R.id.title_bookingappointement)
    TextView tv;

    @BindView(R.id.backbutton1)
    ImageView backbutton1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_categories);
        ButterKnife.bind(this);
        vehicle_cat = new ArrayList<>();
        tv.setText("Vehicle Services");
        backbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false));

        vehicle_cat.add("Car Rental");
        vehicle_cat.add("Aircrafts & Flying Vehicles");
        vehicle_cat.add("Vessels Rental");
        vehicle_cat.add("Motorcycle Rental");
        vehicle_cat.add("Truck Rental");
        vehicle_cat.add("Atv");

        VehicleCategoriesAdapter adapter = new VehicleCategoriesAdapter(VehicleCategories.this, vehicle_cat);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);

    }
}
