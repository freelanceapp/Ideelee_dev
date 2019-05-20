package snow.app.ideelee.metre_square_module;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import snow.app.ideelee.R;
import snow.app.ideelee.metre_square_module.adapters.HandymanCatAdapter;
import snow.app.ideelee.perday_fixedpricemodule.adapters.RentalCatAdapter;

public class HandymanCategories extends AppCompatActivity {
RecyclerView recyclerView;
ArrayList<String> vehicle_cat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rental_cat);
        recyclerView = (RecyclerView) findViewById(R.id.rv_vehicle_cat);
        vehicle_cat=new ArrayList<>();
        TextView tv=findViewById(R.id.title_bookingappointement);
        tv.setText("Handyman Services");
        findViewById(R.id.backbutton1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false));

        vehicle_cat.add("Painting");
        vehicle_cat.add("Carpentry");

        HandymanCatAdapter adapter = new HandymanCatAdapter(HandymanCategories.this, vehicle_cat);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);

    }
}
