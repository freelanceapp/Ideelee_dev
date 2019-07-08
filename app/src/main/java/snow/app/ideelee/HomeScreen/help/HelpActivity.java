package snow.app.ideelee.HomeScreen.help;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import snow.app.ideelee.R;
import snow.app.ideelee.api_request_retrofit.ApiService;
import snow.app.ideelee.api_request_retrofit.retrofit_client.ApiClient;
import snow.app.ideelee.extrafiles.BaseActivity;
import snow.app.ideelee.responses.gethelpcat.GetHelpCatRes;

public class HelpActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {

    @BindView
            (R.id.backbutton1)
    ImageView backbutton1;
    ImageView notification;
    @BindView(R.id.title_bookingappointement)
    TextView title;
    @BindView(R.id.spinner)
    Spinner spinner;
    ApiService apiService;
    String userid, token, servicetype;
    HashMap<String, String> map;
    SharedPreferences prefs;
    String item;
ArrayList<String> gethelpcat=new ArrayList<>();
    ArrayAdapter<String> dataAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        ButterKnife.bind(this);
        title.setText("Help");


        prefs = getSharedPreferences("Login", MODE_PRIVATE);
        userid = prefs.getString("userid", "0");
        token = prefs.getString("token", "0");


        Log.e("userid--", userid + token);
        apiService = ApiClient.getClient(this)
                .create(ApiService.class);
        backbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        spinner.setOnItemSelectedListener(HelpActivity.this);

        // Spinner Drop down elements



        // Creating adapter for spinner
      dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, gethelpcat);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        handlegetHelpCat();
    }


    public void getHelpCat(HashMap<String, String> map) {
        createProgressDialog();

        Observer<GetHelpCatRes> observer = apiService.getHelpCat(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<GetHelpCatRes>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GetHelpCatRes res) {
                        if (res.getStatus()) {
                            Toast.makeText(HelpActivity.this, res.getMessage(), Toast.LENGTH_SHORT).show();
                            for (int i = 0; i < res.getSubcatdata().size(); i++) {
                                gethelpcat.add(res.getSubcatdata().get(i).getHelpCategory());
                            }

dataAdapter.notifyDataSetChanged();
/*da

                            Picasso.with(getActivity())
                                    .load(res.getServicedata().getProfileImage())
//                                    .resize(width / 4, width / 4)
                                    .transform(new CircleTransform())
                                    .centerCrop()
                                    .into(img);
*/

                        } else {
                            Toast.makeText(HelpActivity.this, res.getMessage(), Toast.LENGTH_SHORT).show();
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


    private void handlegetHelpCat() {
        map = new HashMap<>();
        map.put("userid", userid);
        map.put("token", token);

        getHelpCat(map);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
