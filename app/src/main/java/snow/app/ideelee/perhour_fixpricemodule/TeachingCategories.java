package snow.app.ideelee.perhour_fixpricemodule;

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
import snow.app.ideelee.perhour_fixpricemodule.adapters.TeachingCatAdapter;

public class TeachingCategories extends AppCompatActivity {
@BindView(R.id.rv_vehicle_cat) RecyclerView recyclerView;
ArrayList<String> vehicle_cat;
@BindView(R.id.backbutton1) ImageView backbutton1;
@BindView(R.id.title_bookingappointement) TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teaching_categories);
        ButterKnife.bind(this);
        vehicle_cat=new ArrayList<>();
        tv.setText("Teaching and Courses Services");
        backbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false));

        vehicle_cat.add("Languages Teacher");
        vehicle_cat.add("Homework Assistant");
        vehicle_cat.add("Exams Assistant");
        vehicle_cat.add("Music");
        vehicle_cat.add("Dancing");
        vehicle_cat.add("Art and Creativity");
        vehicle_cat.add("Courses in car and driving");
        vehicle_cat.add("Technician Course");
        vehicle_cat.add("Management Course");
        vehicle_cat.add("Office software");
        vehicle_cat.add("Fashion and styling");
        vehicle_cat.add("Insurance, international trade, pension advice");
        vehicle_cat.add("Cooking, baking, bartenders and wine");
        vehicle_cat.add("Construction, Maintenance, Milling and Welding");
        vehicle_cat.add("Animals");
        vehicle_cat.add("Communications, advertising and events,cinema");
        vehicle_cat.add("Guidance, counseling and coaching");
        vehicle_cat.add("Education and training");
        vehicle_cat.add("Environmental studies, gardening and landscape");
        vehicle_cat.add("Parenting and Couplehood Studies");
        vehicle_cat.add("Electrical Studies");
        vehicle_cat.add("Real Estate Studies");
        vehicle_cat.add("Cosmetic, beauty and beauty studies");
        vehicle_cat.add("Capital Market Studies and Investments");
        vehicle_cat.add("Tourism and hiking");
        vehicle_cat.add("Mobile and Internet");
        vehicle_cat.add("Secretarial, Office and Accounting");
        vehicle_cat.add("Therapeutic professions and medicine");
        vehicle_cat.add("Sports, gymnastics and movement");
        vehicle_cat.add("Electronic commerce course");
        vehicle_cat.add("Sales and Marketing");
        vehicle_cat.add("Security & Surveillance Services");

        TeachingCatAdapter adapter = new TeachingCatAdapter(TeachingCategories.this, vehicle_cat);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);

    }
}
