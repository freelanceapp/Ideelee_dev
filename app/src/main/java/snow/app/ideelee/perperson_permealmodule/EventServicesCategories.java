package snow.app.ideelee.perperson_permealmodule;

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
import snow.app.ideelee.perday_fixedpricemodule.adapters.RentalCatAdapter;
import snow.app.ideelee.perperson_permealmodule.adapters.EventCatAdapter;

public class EventServicesCategories extends AppCompatActivity {
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
        setContentView(R.layout.activity_event_categories);
        ButterKnife.bind(this);
        vehicle_cat = new ArrayList<>();
        tv.setText("Event Services");
        backbutton1.setOnClickListener(new View.OnClickListener() {
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
