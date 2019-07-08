package snow.app.ideelee.HomeScreen.help;

 import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
import snow.app.ideelee.extrafiles.SessionManager;
import snow.app.ideelee.responses.gethelpcat.GetHelpCatRes;
import snow.app.ideelee.responses.gethelpcat.Subcatdatum;
import snow.app.ideelee.responses.sendhelpmsgres.SendHelpMsgRes;

public class HelpActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {

    @BindView
            (R.id.backbutton1)
    ImageView backbutton1;
    ImageView notification;
    @BindView(R.id.title_bookingappointement)
    TextView title;

    @BindView(R.id.email)
    EditText email;


    @BindView(R.id.msg)
    EditText msg;

    @BindView(R.id.phone)
    EditText phone;

    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.ux_btn_continue_loginPage)
    Button btn_continue_loginPage;
    ApiService apiService;
    String userid, token, servicetype;
    HashMap<String, String> map;

    String item,subcat_id;
ArrayList<String> gethelpcat=new ArrayList<>();
List<Subcatdatum> gethelpallcat=new ArrayList<>();
    ArrayAdapter<String> dataAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        ButterKnife.bind(this);
        title.setText("Help");
        SessionManager sessionManager= new SessionManager(this);
        userid = sessionManager.getKeyId();
        token = sessionManager.getKeyToken();

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


        btn_continue_loginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handlesendHelpMsg();
            }
        });
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
                            gethelpallcat.addAll(res.getSubcatdata());
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


    // send help msg


    public void sendHelpMsg(HashMap<String, String> map) {
        createProgressDialog();

        Observer<SendHelpMsgRes> observer = apiService.sendHelpMsg(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<SendHelpMsgRes>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SendHelpMsgRes res) {
                        if (res.getStatus()) {
                            Toast.makeText(HelpActivity.this, res.getMessage(), Toast.LENGTH_SHORT).show();

                            finish();

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


    private void handlesendHelpMsg() {

        String email_ = email.getText().toString();

        if (!Patterns.EMAIL_ADDRESS.matcher(email_).matches()) {
            email.setError("Invalid Email");
        } else if (phone.getText().toString().isEmpty()) {
            phone.setError("Please enter phone number");


        } else if (msg.getText().toString().isEmpty()) {
            msg.setError(" Please enter message");


        } else {
            createProgressDialog();


            map = new HashMap<>();
            map.put("userid", userid);
            map.put("token", token);
            map.put("helpcatid", subcat_id);
            map.put("email", email.getText().toString());
            map.put("phone", phone.getText().toString());
            map.put("message", msg.getText().toString());

            sendHelpMsg(map);

        }


    }


    //send help msg


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        item = parent.getItemAtPosition(position).toString();
        subcat_id = gethelpallcat.get(position).getId();
        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item + "id---" + subcat_id, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
