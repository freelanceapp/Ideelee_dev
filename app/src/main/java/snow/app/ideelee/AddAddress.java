package snow.app.ideelee;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import snow.app.ideelee.HomeScreen.HomeNavigation;
import snow.app.ideelee.vehical_module.vehicle.VehicalListing;


public class AddAddress extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {
    public static final String TAG = "AutoCompleteActivity";
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private static final int AUTOCOMPLETE_REQUEST_CODE = 2;
    SupportMapFragment mapFragment;
    @BindView
            (R.id.addmoredetails)
    TextView addmoredetails;


    @BindView
            (R.id.ux_btn1)
    TextView ux_btn1;
    @BindView(R.id.txt_confirmlocation)
    TextView txt_confirmlocation;
    @BindView(R.id.title_bookingappointement)
    TextView title_bookingappointement;
    @BindView(R.id.address)
    EditText address;
    List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME);
    GoogleApiClient mGoogleApiClient;
    Boolean mTimerIsRunning;
    GoogleMap mGoogleMap;
    SupportMapFragment mapFrag;
    LocationRequest mLocationRequest;
    MarkerOptions markerOptions;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    LatLng latLng;
    LatLng markerLocation;
    String intentofVL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        ButterKnife.bind(this);
        Places.initialize(getApplicationContext(), "AIzaSyCXTaGfar2xqDZpGrZRSY96l5fw6mYF4sI");

        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Autocomplete.IntentBuilder(
                        AutocompleteActivityMode.FULLSCREEN, fields)
                        .setTypeFilter(TypeFilter.ADDRESS)
                        .build(AddAddress.this);
                startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE);
            }
        });
        if (
                getIntent().hasExtra("intentofVL")) {

            intentofVL = getIntent().getStringExtra("intentofVL");

        }

        Log.e("int---", intentofVL);


        ux_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Toast.makeText(AddAddress.this, intentofVL, Toast.LENGTH_SHORT).show();
                Toast.makeText(AddAddress.this, "clicked", Toast.LENGTH_SHORT).show();
                if (intentofVL.equals("1")) {
                    Intent intent = new Intent(AddAddress.this, VehicalListing.class);
                    intent.putExtra("lat", String.valueOf(markerLocation.latitude));
                    intent.putExtra("lng", String.valueOf(markerLocation.longitude));
                    intent.putExtra("intentofVL", "1");
                 //   startActivity(intent);

                    setResult(2,intent);
                    finish();//finishing activity




                    Toast.makeText(AddAddress.this, markerLocation.latitude + "," + markerLocation.longitude, Toast.LENGTH_SHORT).show();
                }
            }
        });
//
//         autocompleteFragment = (PlaceAutocompleteFragment)
//                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
//
//        /*AutocompleteFilter filter = new AutocompleteFilter.Builder()
//                .setCountry("IN")
//                .setTypeFilter(AutocompleteFilter.TYPE_FILTER_CITIES)
//                .build();
//        autocompleteFragment.setFilter(filter);*/
//        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
//            @Override
//            public void onPlaceSelected(Place place) {
//                txtVw.setText(place.getName());
//            }
//            @Override
//            public void onError(Status status) {
//                txtVw.setText(status.toString());
//            }
//        });
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
        });
       /* findViewById(R.id.ux_btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Intent(AddAddress.this, HomeNavigation.class);
            }
        });*/
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
        mGoogleMap = googleMap;
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);


        //Initialize Google Play Services
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                //Location Permission already granted
                buildGoogleApiClient();
                mGoogleMap.setMyLocationEnabled(true);

            } else {
                //Request Location Permission
                checkLocationPermission();
            }
        } else {
            buildGoogleApiClient();
            mGoogleMap.setMyLocationEnabled(true);
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = Autocomplete.getPlaceFromIntent(data);
                Log.i(TAG, "Place: " + place.getName() + ", " + place.getId());
                address.setText(place.getName());
            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                Status status = Autocomplete.getStatusFromIntent(data);
                Log.i(TAG, status.getStatusMessage());
            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
    }






    @Override
    protected void onPause() {
        super.onPause();
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
    }

    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }

        //Place current location marker
        latLng = new LatLng(location.getLatitude(), location.getLongitude());
        markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        //  markerOptions.position(latLng);
        // markerOptions.title();
        // markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
        //  mCurrLocationMarker = mGoogleMap.addMarker(markerOptions);

        //move map camera
        //   mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 11));
///////////////////////


//Showing Current Location Marker on Map

        /**
         * moving marker
         *
         */


        mGoogleMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {

            @Override
            public void onMarkerDrag(Marker arg0) {
                // TODO Auto-generated method stub


                Log.d("Marker", "Dragging");
            }

            @Override
            public void onMarkerDragEnd(Marker arg0) {
                // TODO Auto-generated method stub
//                LatLng markerLocation = mCurrLocationMarker.getPosition();
////                mCurrLocationMarker.setTitle(markerLocation.toString());
////
////                double latitude=markerLocation.latitude
//                getaddressfromlat(markerLocation.latitude, markerLocation.longitude);
//                Toast.makeText(AddAddress.this, markerLocation.toString(), Toast.LENGTH_LONG).show();
//                Log.d("Marker", "finished");
            }

            @Override
            public void onMarkerDragStart(Marker arg0) {
                // TODO Auto-generated method stub
                Log.d("Marker", "Started");
                markerLocation = mCurrLocationMarker.getPosition();
                getaddressfromlat(markerLocation.latitude, markerLocation.longitude);
            }
        });


        LocationManager locationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);
        String provider = locationManager.getBestProvider(new Criteria(), true);
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        Location locations = locationManager.getLastKnownLocation(provider);
        List<String> providerList = locationManager.getAllProviders();
        if (null != locations && null != providerList && providerList.size() > 0) {
            double longitude = locations.getLongitude();
            double latitude = locations.getLatitude();
//            Geocoder geocoder = new Geocoder(getApplicationContext(),
//                    Locale.getDefault());
//            try {
//                List<Address> listAddresses = geocoder.getFromLocation(latitude,
//                        longitude, 1);
//                if (null != listAddresses && listAddresses.size() > 0) {
//// Here we are finding , whatever we want our marker to show when
//                    //
//                    String state = listAddresses.get(0).getAdminArea();
//                    String country = listAddresses.get(0).getCountryName();
//                    String subLocality = listAddresses.get(0).getSubLocality();
//                    markerOptions.title(""+ subLocality + "," + state
//                            + "," + country);
//                    System.out.println(subLocality + "," + state
//                            + "," + country);
//                 //   address.setText(listAddresses.get(0).getAddressLine(0));
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            getaddressfromlat1(latitude, longitude);
        }
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        mCurrLocationMarker = mGoogleMap.addMarker(markerOptions);
//move map camera
        mCurrLocationMarker.setDraggable(true);
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(11));
//this code stops location updates
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient,
                    this);


            /////////////////////////////////
        }
    }

    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(this)
                        .setTitle("Location Permission Needed")
                        .setMessage("This app needs the Location permission, please accept to use location functionality")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(AddAddress.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        mGoogleMap.setMyLocationEnabled(true);
                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    public void getaddressfromlat(double lat, double lng) {
        Geocoder geocoder = new Geocoder(getApplicationContext(),
                Locale.getDefault());
        try {

            List<Address> listAddresses = geocoder.getFromLocation(lat,
                    lng, 1);
            if (null != listAddresses && listAddresses.size() > 0) {
// Here we are finding , whatever we want our marker to show when
                //
                String state = listAddresses.get(0).getAdminArea();
                String country = listAddresses.get(0).getCountryName();
                String subLocality = listAddresses.get(0).getSubLocality();
                markerOptions.title("" + subLocality + "," + state
                        + "," + country);
//                    System.out.println(subLocality + "," + state
//                            + "," + country);

                mCurrLocationMarker.setTitle(listAddresses.get(0).getAddressLine(0));
//            mCurrLocationMarker.setTitle(""+ subLocality + "," + state
//                    + "," + country);
                address.setText(listAddresses.get(0).getAddressLine(0));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getaddressfromlat1(double lat, double lng) {
        Geocoder geocoder = new Geocoder(getApplicationContext(),
                Locale.getDefault());
        try {

            List<Address> listAddresses = geocoder.getFromLocation(lat,
                    lng, 1);
            if (null != listAddresses && listAddresses.size() > 0) {
// Here we are finding , whatever we want our marker to show when
                //
                String state = listAddresses.get(0).getAdminArea();
                String country = listAddresses.get(0).getCountryName();
                String subLocality = listAddresses.get(0).getSubLocality();
                markerOptions.title("" + subLocality + "," + state
                        + "," + country);
//                    System.out.println(subLocality + "," + state
//                            + "," + country);

                //   mCurrLocationMarker.setTitle(listAddresses.get(0).getAddressLine(0));
//            mCurrLocationMarker.setTitle(""+ subLocality + "," + state
//                    + "," + country);
                address.setText("" + subLocality + "," + state
                        + "," + country);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
