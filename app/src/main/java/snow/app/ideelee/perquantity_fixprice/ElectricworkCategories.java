package snow.app.ideelee.perquantity_fixprice;

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
import snow.app.ideelee.perperson_permealmodule.adapters.EventCatAdapter;
import snow.app.ideelee.perquantity_fixprice.adapter.ElectricworkCatAdapter;

public class ElectricworkCategories extends AppCompatActivity {
@BindView(R.id.rv_vehicle_cat) RecyclerView recyclerView;
@BindView(R.id.title_bookingappointement) TextView tv;
@BindView(R.id.backbutton1)
    ImageView backbutton1;
ArrayList<String> vehicle_cat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_categories);
        ButterKnife.bind(this);
        vehicle_cat=new ArrayList<>();
        tv.setText("Handyman Services");
     backbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false));

        vehicle_cat.add("Parking lot lights");
        vehicle_cat.add("Neon Lightning");
        vehicle_cat.add("Security Lightning");
        vehicle_cat.add("Rewire lamps/lights");
        vehicle_cat.add("Rewire wall switches");
        vehicle_cat.add("Rewire chandeliers");
        vehicle_cat.add("Emergency Electric Services");


        ElectricworkCatAdapter adapter = new ElectricworkCatAdapter(ElectricworkCategories.this, vehicle_cat);
        recyclerView.setAdapter(adapter);

    }
}
