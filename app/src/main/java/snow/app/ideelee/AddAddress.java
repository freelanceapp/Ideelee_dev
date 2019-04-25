package snow.app.ideelee;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import snow.app.ideelee.HomeScreen.HomeNavigation;
import snow.app.ideelee.R;

public class AddAddress extends AppCompatActivity implements OnMapReadyCallback {
    SupportMapFragment mapFragment;
    TextView addmoredetails;
    TextView txt_confirmlocation;
    TextView title_bookingappointement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        txt_confirmlocation = findViewById(R.id.txt_confirmlocation);
        addmoredetails = findViewById(R.id.addmoredetails);
        title_bookingappointement = findViewById(R.id.title_bookingappointement);
        addmoredetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.buttonlayout).setVisibility(View.GONE);
                findViewById(R.id.fields).setVisibility(View.VISIBLE);
                findViewById(R.id.txt_confirmlocation).setVisibility(View.VISIBLE);
            }
        });
        findViewById(R.id.txt_confirmlocation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Intent(AddAddress.this, HomeNavigation.class);
            }
        }); findViewById(R.id.ux_btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Intent(AddAddress.this, HomeNavigation.class);
            }
        });
 findViewById(R.id.backbutton1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        title_bookingappointement.setText("Add Address");

        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mapFragment.getMapAsync(new OnMapReadyCallback() {
//                    @Override
//                    public void onMapReady(GoogleMap googleMap) {
//                        googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
//
//                        googleMap.addMarker(new MarkerOptions()
//                                .position(new LatLng(37.4233438, -122.0728817))
//                                .title("LinkedIn")
//                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
//
//                        googleMap.addMarker(new MarkerOptions()
//                                .position(new LatLng(37.4629101,-122.2449094))
//                                .title("Facebook")
//                                .snippet("Facebook HQ: Menlo Park"));
//
//                        googleMap.addMarker(new MarkerOptions()
//                                .position(new LatLng(37.3092293, -122.1136845))
//                                .title("Apple"));
//
//                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.4233438, -122.0728817), 10));
//                    }
//                });
//            }
//        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
