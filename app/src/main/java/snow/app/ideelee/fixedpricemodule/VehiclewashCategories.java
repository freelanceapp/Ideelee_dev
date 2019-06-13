package snow.app.ideelee.fixedpricemodule;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import snow.app.ideelee.R;
import snow.app.ideelee.fixedpricemodule.adapters.VehicleWashAdapter;
import snow.app.ideelee.vehical_module.vehicle.adapters.VehicleCategoriesAdapter;

public class VehiclewashCategories extends AppCompatActivity {
    @BindView(R.id.rv_vehicle_cat) RecyclerView recyclerView;
    ArrayList<String> vehicle_cat;
    @BindView(R.id.title_bookingappointement) TextView tv;
    @BindView(R.id.backbutton1) ImageView backbutton1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehiclewash_categories);
        ButterKnife.bind(this);
        vehicle_cat = new ArrayList<>();
        tv.setText("Vehicle Wash");
        backbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false));

        vehicle_cat.add("Car wash");
        vehicle_cat.add("Motorcycle wash");
        vehicle_cat.add("SUV / off-road wash");
        vehicle_cat.add("Motorcycle wash");
        vehicle_cat.add("Truck wash");

        VehicleWashAdapter adapter = new VehicleWashAdapter(VehiclewashCategories.this, vehicle_cat);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);

    }
}
