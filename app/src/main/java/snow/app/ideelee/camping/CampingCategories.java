package snow.app.ideelee.camping;

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
import snow.app.ideelee.camping.adapters.CampingCatAdapter;
import snow.app.ideelee.carriers.adapters.CarriersCatAdapter;

public class CampingCategories extends AppCompatActivity {
@BindView(R.id.rv_vehicle_cat) RecyclerView recyclerView;
@BindView(R.id.title_bookingappointement) TextView tv;
@BindView(R.id.backbutton1)
    ImageView backbutton1;
ArrayList<String> vehicle_cat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rental_cat);
        ButterKnife.bind(this);
        vehicle_cat=new ArrayList<>();
        tv.setText("Camping and Equipments For Rent");
        backbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false));
        vehicle_cat.add("Rental of wooden tables and for camping");
        vehicle_cat.add("Rental of plastic chairs");
        vehicle_cat.add("Folding wooden chair for Rent");
        vehicle_cat.add("Wooden chair for Rent");
        vehicle_cat.add("Plastic chair for Rent");
        vehicle_cat.add("Decorative chair");
        CampingCatAdapter adapter = new CampingCatAdapter(CampingCategories.this, vehicle_cat);
        recyclerView.setAdapter(adapter);

    }
}
