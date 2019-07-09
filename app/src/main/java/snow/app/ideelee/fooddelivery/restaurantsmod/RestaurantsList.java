package snow.app.ideelee.fooddelivery.restaurantsmod;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import snow.app.ideelee.AddAddress;
import snow.app.ideelee.R;
import snow.app.ideelee.api_request_retrofit.ApiService;
import snow.app.ideelee.api_request_retrofit.retrofit_client.ApiClient;
import snow.app.ideelee.extrafiles.BaseActivity;
import snow.app.ideelee.extrafiles.SessionManager;
import snow.app.ideelee.fooddelivery.restaurantsmod.adapter.RestaurantsAdapter;
import snow.app.ideelee.responses.deliverysubcatres.DeliverySubCatRes;
import snow.app.ideelee.responses.deliverysubcatres.StoresDetail;

public class RestaurantsList extends BaseActivity implements AdapterView.OnItemSelectedListener {
    @BindView
            (R.id.backbutton1)
    ImageView backbutton1;
    @BindView(R.id.title_bookingappointement)
    TextView title_bookingappointement;

    @BindView(R.id.filter)
    TextView filter;

    @BindView(R.id.relevance)
    TextView relevance;
    @BindView(R.id.rv_list)
    RecyclerView rv_list;
    RestaurantsAdapter restaurantsAdapter;
    ApiService apiService;
    String userid, token, servicetype, cat_id, parentid, subparentid, minprice = "", maxprice = "",
            rating = "", sorting = "",
            userlatitude = "", userlongitude = "", item = "",location="";
    HashMap<String, String> map;
    List<StoresDetail> couponcategorydatumList;


    SessionManager sessionManager;
    PopupWindow pw;
    Spinner spinner;
    EditText ed_maxprice, ed_minprice, ed_location;
    int spinner_selectedindex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants_list);
        ButterKnife.bind(this);
        backbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        couponcategorydatumList = new ArrayList<>();
        servicetype = getIntent().getStringExtra("servicetype");


        cat_id = getIntent().getStringExtra("cat");
        apiService = ApiClient.getClient(RestaurantsList.this)
                .create(ApiService.class);

        sessionManager = new SessionManager(this);
        userid = sessionManager.getKeyId();
        token = sessionManager.getKeyToken();

        title_bookingappointement.setText("Restaurants");
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        rv_list.setLayoutManager(new LinearLayoutManager(this));

        restaurantsAdapter = new RestaurantsAdapter(couponcategorydatumList, this, width);
        rv_list.setAdapter(restaurantsAdapter);
        handleOnDeliveryData();
        relevance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(RestaurantsList.this, relevance);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.relevance_menu, popup.getMenu());
                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                         couponcategorydatumList.clear();

                        handleOnDeliveryData();
                        if (item.getTitle().equals("Name (a to z)")) {
                            sorting = "name";
                        } else if (item.getTitle().equals("Rating")) {
                            sorting = "rating";
                        } else if (item.getTitle().equals("Distance")) {
                            sorting = "distance";
                        } else {
                            sorting = "name";
                        }


                        return true;
                    }
                });

                popup.show();//s
            }
        });


        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initiatePopupwindow(v);
            }
        });
    }


    public void getOnDeliveryData(HashMap<String, String> map) {
        createProgressDialog();

        Observer<DeliverySubCatRes> observer = apiService.getDeliverySubCat(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<DeliverySubCatRes>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DeliverySubCatRes res) {


                        if (res.getStatus()) {
                            Toast.makeText(RestaurantsList.this, res.getMessage(), Toast.LENGTH_SHORT).show();

                            //  for (int i = 0; i < res.getHomescreendata().getParentCatArray().size(); i++) {

                            couponcategorydatumList.addAll(res.getServicesdata().getStoresDetail());

                            restaurantsAdapter.notifyDataSetChanged();

                            //add more on  the last the position


                        } else {
                            Toast.makeText(RestaurantsList.this, res.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        dismissProgressDialog();

                    }

                    @Override
                    public void onComplete() {
                        dismissProgressDialog();

                    }
                });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
   /*     if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                place = Autocomplete.getPlaceFromIntent(data);
                Log.i(TAG, "Place: " + place.getName() + ", " + place.getId());
                ed_location.setText(place.getName());
            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                Status status = Autocomplete.getStatusFromIntent(data);
                Log.i(TAG, status.getStatusMessage());
            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }else

        }*/

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 2) {
           /* String message=data.getStringExtra("MESSAGE");
            textView1.setText(message);*/

                userlongitude = data.getStringExtra("lng");
                userlatitude = data.getStringExtra("lat");

                getaddressfromlat1(Double.parseDouble(userlatitude), Double.parseDouble(userlongitude));
                Log.e("latandln", userlatitude + "--" + userlongitude);
                /*intentVL=data.getStringExtra("from");*/


                //  Log.e("latlng in vl--",userlatitude+userlongitude);

            }

        }


    }


    private void handleOnDeliveryData() {
        map = new HashMap<>();


        map.put("userid", userid);
        map.put("token", token);
        map.put("servicetype", servicetype);
        map.put("category_id", cat_id);
        map.put("minprice", minprice);
        map.put("rating", rating);
        map.put("sorting", sorting);
        map.put("userlocation", location);
        map.put("userlatitude", userlatitude);
        map.put("userlongitude", userlongitude);


        getOnDeliveryData(map);
    }

    public void initiatePopupwindow(View v) {

        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.filter_delivery_layout, (ViewGroup) v.findViewById(R.id.linearlayout_delivery_filter));
        pw = new PopupWindow(layout, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        pw.showAtLocation(v, Gravity.RIGHT, 0, 0);
        View container = (View) pw.getContentView().getRootView();
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams p = (WindowManager.LayoutParams) container.getLayoutParams();
        p.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        p.dimAmount = 0.6f;
        wm.updateViewLayout(container, p);
        ImageView cancel = layout.findViewById(R.id.cancel);
        ed_minprice = layout.findViewById(R.id.minprice);

        ed_location = layout.findViewById(R.id.location);
        spinner = layout.findViewById(R.id.spinner);

        ed_minprice.setText(minprice);

        if (userlatitude.equals("") && (userlongitude.equals(""))) {
        } else {


            getaddressfromlat1(Double.parseDouble(userlatitude), Double.parseDouble(userlongitude));
        }

        Log.e("sorting---", sorting + " k");


        ed_minprice.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                minprice = s.toString();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

            }
        });

        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        spinner.setPrompt("Select Rating");
        categories.add("1");
        categories.add("2");
        categories.add("3");
        categories.add("4");
        categories.add("5");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        spinner.setSelection(spinner_selectedindex);

     /*   for(int i = 0; i < dataAdapter.getCount(); i++)
        {
            if (dataAdapter.getItemId(i) == myID )
            {
                spinner.setSelection(i, false); //(false is optional)
                break;
            }
        }*/


        Button btn = layout.findViewById(R.id.btn_apply);
        Button clearfilter = layout.findViewById(R.id.clearfilter);


        clearfilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                couponcategorydatumList.clear();
                minprice = "";

                userlatitude = "";
                userlongitude = "";
                rating = "";
                sorting = "";
                spinner_selectedindex = 0;

                handleOnDeliveryData();
                pw.dismiss();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                couponcategorydatumList.clear();
                handleOnDeliveryData();
                pw.dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pw.dismiss();
            }
        });
        ed_location.setHint("Location");
        ed_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent = new Autocomplete.IntentBuilder(
                        AutocompleteActivityMode.FULLSCREEN, fields)
                        .setTypeFilter(TypeFilter.ADDRESS)
                        .build(VehicalListing.this);
                startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE);
*/
                Intent addressIntent = new Intent(RestaurantsList.this, AddAddress.class);
                addressIntent.putExtra("from", "vl");
                startActivityForResult(addressIntent, 2);
            }
        });
        //  ed_location.setText(place.getName());

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        rating = parent.getItemAtPosition(position).toString();
        spinner_selectedindex = position;


        Log.e("inde", String.valueOf(spinner_selectedindex));

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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

                ed_location.setText("" + subLocality + "," + state
                        + "," + country);

location=ed_location.getText().toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
