package snow.app.ideelee;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rilixtech.Country;
import com.rilixtech.CountryCodePicker;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import snow.app.ideelee.api_request_retrofit.ApiService;
import snow.app.ideelee.api_request_retrofit.retrofit_client.ApiClient;
import snow.app.ideelee.extrafiles.AppConstants;
import snow.app.ideelee.extrafiles.BaseActivity;
import snow.app.ideelee.responses.registerres.RegisterRes;

public class Register extends BaseActivity {
    @BindView(R.id.ux_txt_loginnow_registerPage)
    TextView txt_loginnow_registerPage;

    @BindView(R.id.register)
    Button register;
    @BindView(R.id.name)
    EditText edt_first_name;
    @BindView(R.id.email)
    EditText edt_email;
    @BindView(R.id.phone)
    EditText edt_phone;
    @BindView(R.id.password)
    EditText edt_password;
    ApiService apiService;

    @BindView(R.id.ccp)
    CountryCodePicker ccp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        apiService = ApiClient.getClient(getApplicationContext())
                .create(ApiService.class);
        txt_loginnow_registerPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_login = new Intent(Register.this, Login.class);
                startActivity(intent_login);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleRegister();

            }
        });
        if (getIntent().hasExtra("name")){
            edt_first_name.setText(getIntent().getStringExtra("name"));
            edt_email.setText(getIntent().getStringExtra("email"));
        }


//        ccp.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
//            @Override
//            public void onCountrySelected(Country selectedCountry) {
//                Toast.makeText(Register.this, "Updated " + selectedCountry.getName(), Toast.LENGTH_SHORT).show();
//            }
//        });

    }

    private void handleRegister() {


        String f_name = edt_first_name.getText().toString();
        //  String l_name = edt_last_name.getText().toString();
        String email = edt_email.getText().toString();
        String phone_ = edt_phone.getText().toString();
        String pass = edt_password.getText().toString();
        if (f_name.isEmpty()) {
            edt_first_name.setError("Enter First Name");
        } else if (email.isEmpty()) {
            edt_email.setError("Enter Email");
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edt_email.setError("Invalid Email");
        } else if (pass.isEmpty() && pass.length() < 6) {
            edt_password.setError("Minumun 6 characters are required");
        } else if (phone_.isEmpty()) {
            edt_phone.setError("Enter Phone");
        } else {
            createProgressDialog();
            HashMap<String, String> map = new HashMap<>();

             map.put("signup_type", "1");
            map.put("name", f_name);
            map.put("usertype", "1");
            map.put("oauth_uid", "");
            map.put("email", email);
            map.put("password", pass);
            map.put("address", "");
            map.put("mobile", String.valueOf(ccp.getSelectedCountryCode()) + "" + phone_);
            registerUser(map);


        }

    }

    private void openActivity(String phoneNumber, String method) {
        Intent verification = new Intent(this, OTP.class);
        verification.putExtra(AppConstants.OTPVerification.INTENT_PHONENUMBER, phoneNumber);
        verification.putExtra(AppConstants.OTPVerification.INTENT_METHOD, method);
        startActivity(verification);
    }


    public void registerUser(HashMap<String, String> map) {
        createProgressDialog();
        Observer<RegisterRes> observer = apiService.registerUser(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<RegisterRes>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegisterRes res) {

//                        Toast.makeText(Register.this, res.getMessage(), Toast.LENGTH_SHORT).show();
//                        Intent intent_continue = new Intent(Register.this, OTP.class);
//                        intent_continue.putExtra("userid",res.getUserid());
//                        startActivity(intent_continue);

                        if (res.getStatus()) {
                            Toast.makeText(Register.this, res.getMessage(), Toast.LENGTH_SHORT).show();

                          /*  new SessionBestPrice(ScreenRegister.this).createSessionReg(res.getData().getFirstName(),
                                    res.getData().getLastName(), res.getData().getEmail(), String.valueOf(res.getData().getPhoneNumber()),
                                    String.valueOf(res.getData().getOpType()), String.valueOf(res.getData().getId()), "", "");*/

                            AppConstants.LoginProcess.mUserIdForActivationAccountAfterOTPVerification =
                                    String.valueOf(res.getUserregisterdata().getId());
                            AppConstants.LoginProcess.mMobileNumber = "+" + map.get("mobile");

                            // after successfull  register then hit otp confirmation mobile verification
                            openActivity(AppConstants.LoginProcess.mMobileNumber, AppConstants.OTPVerification.INTENT_METHOD);


                            //     startActivity(new Intent(context, ScreenOTPEnter.class));


                        } else {
                            Toast.makeText(Register.this, res.getMessage(), Toast.LENGTH_SHORT).show();
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

}
