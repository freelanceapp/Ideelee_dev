package snow.app.ideelee.vehical_module.vehicle;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
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

import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.gujun.android.taggroup.TagGroup;
import snow.app.ideelee.AddAddress;
import snow.app.ideelee.R;
import snow.app.ideelee.api_request_retrofit.ApiService;
import snow.app.ideelee.api_request_retrofit.retrofit_client.ApiClient;
import snow.app.ideelee.extrafiles.BaseActivity;
import snow.app.ideelee.filter.SubSubCatFilterationAdapter;
import snow.app.ideelee.responses.ondemandserviceproviderlistres.GetOnDemandProvidersListRes;
import snow.app.ideelee.responses.ondemandserviceproviderlistres.Servicelist;
import snow.app.ideelee.responses.subsubcatfileration.SubSubCatFilterationRes;
import snow.app.ideelee.responses.subsubcatfileration.Subcatdatum;
import snow.app.ideelee.vehical_module.vehicle.adapters.VehicalListAdapter;


public class VehicalListing extends BaseActivity implements AdapterView.OnItemSelectedListener {

    public static final String TAG = "AutoCompleteActivity";
    private static final int AUTOCOMPLETE_REQUEST_CODE = 2;
    public static String subserviceids = "";
    public static ArrayList<String> subserviceidslist;
    List<Servicelist> serviceproviderlist;
    List<Subcatdatum> subcatdatumList;
    TagGroup mTagGroup;
    @BindView
            (R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.backbutton1)
    ImageView imageView;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.relevance)
    TextView relevance;
    @BindView(R.id.filter)
    TextView filter;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    VehicalListAdapter adapter;
    String userid, token, parentid, subparentid, minprice = "", maxprice = "", rating = "", sorting = "",
            userlatitude = "", userlongitude = "", item="";
    HashMap<String, String> map;
    ApiService apiService;
    ApiService apiService1;
    TextView textView;
    EditText ed_location, ed_minprice, ed_maxprice;
    Place place;
    Spinner spinner;

    SubSubCatFilterationAdapter adapter_filter=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_fragment);
        ButterKnife.bind(this);
        subserviceidslist = new ArrayList<>();

//        setSupportActionBar(toolbar);
        textView = (TextView) toolbar.findViewById(R.id.title_bookingappointement);

        apiService = ApiClient.getClient(VehicalListing.this)
                .create(ApiService.class);
        Places.initialize(getApplicationContext(), "AIzaSyCXTaGfar2xqDZpGrZRSY96l5fw6mYF4sI");
        apiService1 = ApiClient.getClient(VehicalListing.this)
                .create(ApiService.class);
        SharedPreferences prefs = getSharedPreferences("Login", MODE_PRIVATE);
        userid = prefs.getString("userid", "0");
        token = prefs.getString("token", "0");

        parentid = getIntent().getStringExtra("parentid");
        subparentid = getIntent().getStringExtra("subparentid");


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        /*if (getIntent().hasExtra("lat")) {
            userlatitude = getIntent().getStringExtra("lat");
            userlongitude = getIntent().getStringExtra("lng");
        } else {
            userlongitude = "";
            userlatitude = "";
        }*/
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        serviceproviderlist = new ArrayList<>();
        subcatdatumList = new ArrayList<>();

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

//        Picasso.with(this).load("https://www.training.com.au/wp-content/uploads/plumbing-courses.png").resize(width,width/2).into(img);



/*

        if (getIntent().hasExtra("intentofVL")){
            intentVL=getIntent().getStringExtra("intentofVL");
            if (intentVL.equals("1")){

            }
        }
*/


        adapter = new VehicalListAdapter(this, serviceproviderlist);
        recyclerView.setAdapter(adapter);
        relevance.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(VehicalListing.this, relevance);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.relevance_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {

                        if (item.getTitle().equals("Name (a to z)")) {
                            sorting = "name";
                        } else if (item.getTitle().equals("Rating")) {
                            sorting = "rating";
                        } else if (item.getTitle().equals("Distance")) {
                            sorting = "distance";
                        } else {
                            sorting = "name";
                        }


                        Toast.makeText(VehicalListing.this, "You Clicked : " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });

                popup.show();//showing popup menu
            }
        });

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initiatePopupwindow(v);
            }
        });
        handleOnDemandServiceProviderList();
        handleGetSubSubCatFilterationList( );

    }

    @Override
    public void onBackPressed() {
        finish();

    }

    public void initiatePopupwindow(View v) {

        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.filter_menu, (ViewGroup) v.findViewById(R.id.linearlayout_filter));
        final PopupWindow pw = new PopupWindow(layout, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        pw.showAtLocation(v, Gravity.RIGHT, 0, 0);
        View container = (View) pw.getContentView().getRootView();
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams p = (WindowManager.LayoutParams) container.getLayoutParams();
        p.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        p.dimAmount = 0.6f;
        wm.updateViewLayout(container, p);
        ImageView cancel = layout.findViewById(R.id.cancel);
        ed_minprice = layout.findViewById(R.id.minprice);
        ed_maxprice = layout.findViewById(R.id.maxprice);
        ed_location = layout.findViewById(R.id.location);
        spinner = layout.findViewById(R.id.spinner);

        if (userlatitude.equals("") && (userlongitude.equals(""))) {
        } else {


            getaddressfromlat1(Double.parseDouble(userlatitude), Double.parseDouble(userlongitude));
        }

        Log.e("sorting---", sorting);

        ed_maxprice.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                maxprice = s.toString();
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


        Button btn = layout.findViewById(R.id.btn_apply);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e("list of serices--", subserviceidslist.toString());
                handleOnDemandServiceProviderList();
            }
        });
        RecyclerView recyclerView_filter;
        recyclerView_filter = layout.findViewById(R.id.rv_filteration);
        recyclerView_filter.setHasFixedSize(true);
        recyclerView_filter.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter_filter = new SubSubCatFilterationAdapter(VehicalListing.this, subcatdatumList);
        recyclerView_filter.setAdapter(adapter_filter);
        adapter_filter.notifyDataSetChanged();
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
                Intent addressIntent = new Intent(VehicalListing.this, AddAddress.class);
                addressIntent.putExtra("from", "vl");
               startActivityForResult(addressIntent,2);
            }
        });
        //  ed_location.setText(place.getName());

    }






    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        rating = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void getOnDemandserviceproviderList(HashMap<String, String> map) {
        createProgressDialog();

        Observer<GetOnDemandProvidersListRes> observer = apiService.getOnDemandServiceProviderList(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<GetOnDemandProvidersListRes>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GetOnDemandProvidersListRes res) {
                        if (res.getStatus()) {
                            Toast.makeText(VehicalListing.this, res.getMessage(), Toast.LENGTH_SHORT).show();

                            //  for (int i = 0; i < res.getHomescreendata().getParentCatArray().size(); i++) {

                            Picasso.with(VehicalListing.this).
                                    load(res.getServicedata().getHeaderdata().getBannerImage()).into(img);
                            textView.setText(res.getServicedata().getHeaderdata().getTitle());
                            serviceproviderlist.addAll(res.getServicedata().getServicelist());

                            adapter.notifyDataSetChanged();

                            //add more on  the last the position


                        } else {
                            Toast.makeText(VehicalListing.this, res.getMessage(), Toast.LENGTH_SHORT).show();
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


    private void handleOnDemandServiceProviderList() {


        if (subserviceidslist.size() > 0) {
            subserviceids = TextUtils.join(",", subserviceidslist);
        } else {
            subserviceids = "";
        }
        map = new HashMap<>();


        map.put("userid", userid);
        map.put("token", token);
        map.put("parentid", parentid);
        map.put("subparentid", subparentid);
        map.put("minprice", minprice);   //  for filteration
        map.put("maxprice", maxprice);
        map.put("sorting", sorting);
        map.put("rating", rating);
        map.put("userlatitude", userlatitude);
        map.put("userlongitude", userlongitude);
        map.put("subserviceids", subserviceids);

        Log.e("mapp on dmand---", map.toString());
  getOnDemandserviceproviderList(map);
    }


    public void getSubSubCatFilterationList(HashMap<String, String> map) {


        Observer<SubSubCatFilterationRes> observer = apiService1.getSubSubCatFilterationList(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<SubSubCatFilterationRes>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SubSubCatFilterationRes res) {
                        if (res.getStatus()) {
                            //  for (int i = 0; i < res.getHomescreendata().getParentCatArray().size(); i++) {
                            subcatdatumList.clear();

                            subcatdatumList.addAll(res.getSubcatdata());

                            //add more on  the last the position


                        } else {
                            Toast.makeText(VehicalListing.this, res.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }


    private void handleGetSubSubCatFilterationList() {
        map = new HashMap<>();


        map.put("userid", userid);
        map.put("token", token);
        map.put("parentid", parentid);
        map.put("subparentid", subparentid);


        getSubSubCatFilterationList(map);
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
            if(requestCode==2)
            {
           /* String message=data.getStringExtra("MESSAGE");
            textView1.setText(message);*/

                userlongitude=data.getStringExtra("lng");
                userlatitude=data.getStringExtra("lat");

                getaddressfromlat1(Double.parseDouble(userlatitude),Double.parseDouble(userlongitude));
                Log.e("latandln",userlatitude+"--"+userlongitude);
                /*intentVL=data.getStringExtra("from");*/



                //  Log.e("latlng in vl--",userlatitude+userlongitude);

            }

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

                ed_location.setText("" + subLocality + "," + state
                        + "," + country);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
