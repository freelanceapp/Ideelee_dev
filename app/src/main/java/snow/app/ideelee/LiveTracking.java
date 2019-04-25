package snow.app.ideelee;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import java.util.ArrayList;

import snow.app.ideelee.R;
import snow.app.ideelee.livetracking.TrackerAdapter;
import snow.app.ideelee.livetracking.TrackerM;

public class LiveTracking extends AppCompatActivity implements OnMapReadyCallback {
    SupportMapFragment mapFragment;
    TextView title_bookingappointement;
    TrackerAdapter trackerAdapter;
     RecyclerView rv_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_trackinng);
        title_bookingappointement = findViewById(R.id.title_bookingappointement);
        rv_list = findViewById(R.id.rv_list);
        findViewById(R.id.backbutton1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        title_bookingappointement.setText("Live Tracking");
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        rv_list.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<TrackerM>data= new ArrayList<>();
        data.add(new TrackerM("1","11:40 am",""));
        data.add(new TrackerM("2","11:45 am",""));
        data.add(new TrackerM("3","11:55 am",""));
        data.add(new TrackerM("4","12:00 pm",""));
        trackerAdapter= new TrackerAdapter(data,this,0);
        rv_list.setAdapter(trackerAdapter);
        rv_list.setNestedScrollingEnabled(false);




    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
