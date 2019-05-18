package snow.app.ideelee.perperson_permealmodule;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import snow.app.ideelee.R;
import snow.app.ideelee.perday_fixedpricemodule.adapters.RentalCatAdapter;
import snow.app.ideelee.perperson_permealmodule.adapters.EventCatAdapter;

public class EventServicesCategories extends AppCompatActivity {
RecyclerView recyclerView;
ArrayList<String> vehicle_cat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_categories);
        recyclerView = (RecyclerView) findViewById(R.id.rv_vehicle_cat);
        vehicle_cat=new ArrayList<>();
        TextView tv=findViewById(R.id.title_bookingappointement);
        tv.setText("Event Services");
        findViewById(R.id.backbutton1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false));

        vehicle_cat.add("Home Cooks & Chefs");
        vehicle_cat.add("Special Events Chefs & Cooks");
        vehicle_cat.add("Catering");


        EventCatAdapter adapter = new EventCatAdapter(EventServicesCategories.this, vehicle_cat);
        recyclerView.setAdapter(adapter);

    }
}
