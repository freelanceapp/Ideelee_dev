package snow.app.ideelee.perquantityperfloor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import snow.app.ideelee.R;
import snow.app.ideelee.metre_square_module.adapters.HandymanCatAdapter;
import snow.app.ideelee.perquantityperfloor.adapters.MovingliftingCatAdapter;

public class MovingliftingCategories extends AppCompatActivity {
    @BindView(R.id.rv_vehicle_cat)
    RecyclerView recyclerView;
    ArrayList<String> vehicle_cat;
    @BindView(R.id.title_bookingappointement)
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rental_cat);
        ButterKnife.bind(this);
        vehicle_cat = new ArrayList<>();
        tv.setText("Moving & Lifting Services");
        findViewById(R.id.backbutton1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false));

        vehicle_cat.add("Moving");
        vehicle_cat.add("Heavy Lifting");
        vehicle_cat.add("Packing & unpacking");
        vehicle_cat.add("General Moving");

        MovingliftingCatAdapter adapter = new MovingliftingCatAdapter(MovingliftingCategories.this, vehicle_cat);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);

    }
}
