package snow.app.ideelee;

import android.app.Activity;
import android.content.Intent;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.rilixtech.Country;
import com.rilixtech.CountryCodePicker;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import snow.app.ideelee.HomeScreen.HomeNavigation;
import snow.app.ideelee.Responses.LoginResponse.LoginRes;
import snow.app.ideelee.Responses.opertaorres.OperatorRes;
import snow.app.ideelee.api_request_retrofit.ApiService;
import snow.app.ideelee.api_request_retrofit.retrofit_client.ApiClient;
import snow.app.ideelee.extrafiles.AppConstants;
import snow.app.ideelee.forgot.ForgotPassword;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

import static android.provider.ContactsContract.Intents.Insert.EMAIL;

public class Login extends Activity {


    @BindView
            (R.id.ux_txt_registernow_loginPage)
    TextView txt_registernow_loginPage;
    @BindView(R.id.ux_btn_continue_loginPage)
    Button btn_continue_loginPage;
    @BindView(R.id.forgot)
    TextView forgot;
    @BindView(R.id.fblogin)
    Button fblogin;
    @BindView(R.id.login_button)
    LoginButton loginButton;
    @BindView(R.id.email)
    EditText et_email;
    @BindView(R.id.password)
    EditText et_pass;
    CallbackManager callbackManager;
    private static final String EMAIL = "email";
    ApiService apiService;
    String device_token;
    HashMap<String, String> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        device_token = Settings.Secure.getString(Login.this.getContentResolver(), Settings.Secure.ANDROID_ID);
        System.out.println("device toekn--" + device_token);
        apiService = ApiClient.getClient(getApplicationContext())
                .create(ApiService.class);
        txt_registernow_loginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_register = new Intent(Login.this, Register.class);
                startActivity(intent_register);
            }
        });
        getOperatortype();

        btn_continue_loginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map = new HashMap<>();
                map.put("email", et_email.getText().toString());
                map.put("password", et_pass.getText().toString());
                map.put("device_token", device_token);
                Log.e("params login", et_email.getText().toString() + et_pass.getText().toString() + device_token);
                loginUser(map);

            }
        });

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_continue = new Intent(Login.this, ForgotPassword.class);
                startActivity(intent_continue);
            }
        });


        /***
         * login retrofit
         */


/**
 *
 * login retrofit
 */
        /**
         * facebook login
         */

        FacebookSdk.sdkInitialize(getApplicationContext());
        // AppEventsLogger.activateApp(MainActivity.this);
        callbackManager = CallbackManager.Factory.create();


        loginButton.setReadPermissions(Arrays.asList(EMAIL));
        // If you are using in a fragment, call loginButton.setFragment(this);

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                Intent intent_continue = new Intent(Login.this, OTP.class);
                startActivity(intent_continue);

            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });

        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });

/**
 * facebook login
 */

    }

    public void loginUser(HashMap<String, String> map) {
        // showProgress();

        Observer<LoginRes> observer = apiService.loginUser(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<LoginRes>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginRes res) {
                        Log.e("company data", res.getData().getCompanyData().getCompanyAddress() +
                                res.getData().getFirstName() + res.getData().getLastName());

//                        Intent intent_continue = new Intent(Login.this, OTP.class);
//                        startActivity(intent_continue);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }


    public void getOperatortype() {
        // showProgress();

        Observer<OperatorRes> observer = apiService.getOperatorlist()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<OperatorRes>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(OperatorRes res) {

                        for (int i = 0; i < res.getData().size(); i++) {
                            Log.e("operator data", String.valueOf(res.getData().get(i).getId()));
                        }


//                        Intent intent_continue = new Intent(Login.this, OTP.class);
//                        startActivity(intent_continue);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void onClick(View v) {
        if (v == fblogin) {
            loginButton.performClick();
        }
    }

}
