package snow.app.ideelee.carriers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import snow.app.ideelee.R;
import snow.app.ideelee.carriers.adapters.CarriersCatAdapter;
import snow.app.ideelee.metre_square_module.adapters.HandymanCatAdapter;

public class CarriersCategories extends AppCompatActivity {
RecyclerView recyclerView;
ArrayList<String> vehicle_cat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rental_cat);
        recyclerView = (RecyclerView) findViewById(R.id.rv_vehicle_cat);
        vehicle_cat=new ArrayList<>();
        TextView tv=findViewById(R.id.title_bookingappointement);
        tv.setText("Carriers Services");
        findViewById(R.id.backbutton1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false));

        vehicle_cat.add("50 KM");
        vehicle_cat.add("100 KM");
        vehicle_cat.add("200 KM");
        vehicle_cat.add("300 KM");
        vehicle_cat.add("500 KM");
        vehicle_cat.add("600 KM");
        vehicle_cat.add("700 KM");

        CarriersCatAdapter adapter = new CarriersCatAdapter(CarriersCategories.this, vehicle_cat);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);

    }
}
