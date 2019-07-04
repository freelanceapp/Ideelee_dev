package snow.app.ideelee;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import snow.app.ideelee.HomeScreen.HomeNavigation;
import snow.app.ideelee.api_request_retrofit.ApiService;
import snow.app.ideelee.api_request_retrofit.retrofit_client.ApiClient;
import snow.app.ideelee.extrafiles.AppConstants;
import snow.app.ideelee.extrafiles.BaseActivity;
import snow.app.ideelee.extrafiles.OneTimeLogin;
import snow.app.ideelee.extrafiles.SessionManager;
import snow.app.ideelee.forgot.ForgotPassword;
import snow.app.ideelee.responses.loginres.LoginRes;

import static snow.app.ideelee.AddAddress.TAG;

public class Login extends BaseActivity {

    static final int RC_SIGN_IN = 1;
    private static final String EMAIL = "email";
    @BindView
            (R.id.ux_txt_registernow_loginPage)
    TextView txt_registernow_loginPage;
    @BindView(R.id.ux_btn_continue_loginPage)
    Button btn_continue_loginPage;
    @BindView(R.id.forgot)
    TextView forgot;
    @BindView(R.id.fblogin)
    Button fblogin;

    @BindView(R.id.glogin)
    Button glogin;
    @BindView(R.id.login_button)
    LoginButton loginButton;

    @BindView(R.id.sign_in_button)
    SignInButton signInButton;
    @BindView(R.id.email)
    EditText et_email;
    @BindView(R.id.password)
    EditText et_pass;
    CallbackManager callbackManager;
    ApiService apiService;
    String device_token;
    HashMap<String, String> map;
    OneTimeLogin oneTimeLogin;
    SessionManager sessionManager;
    GoogleSignInOptions gso;

    GoogleSignInClient mGoogleSignInClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        oneTimeLogin = new OneTimeLogin(Login.this);
        device_token = getDeviceToken(Login.this);
        apiService = ApiClient.getClient(getApplicationContext())
                .create(ApiService.class);




        // Configure sign-in to request the user's ID, email address, and basic
// profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestIdToken("985013897309-tgqd1at1e1a0al0dsds98atg8pf4kvqt.apps.googleusercontent.com")
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        txt_registernow_loginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_register = new Intent(Login.this, Register.class);
                startActivity(intent_register);
            }
        });
        //  getOperatortype();

        btn_continue_loginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleLogin();

            }
        });



       glogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();

            }
        });


        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_continue = new Intent(Login.this, ForgotPassword.class);
                startActivity(intent_continue);
            }
        });
        if (!oneTimeLogin.isFirstTimeLaunch()) {
            Intent intent_continue = new Intent(Login.this, HomeNavigation.class);
            startActivity(intent_continue);
            finish();
        }

        /***
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

                AccessToken accessToken = loginResult.getAccessToken();

                // App code
                GraphRequest request = GraphRequest.newMeRequest(
                        accessToken,
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(
                                    JSONObject object,
                                    GraphResponse response) {
                                System.out.println("res--" + response);
                            }
                        });
//                Bundle parameters = new Bundle();
//                parameters.putString("fields", "id,name,link");
//                request.setParameters(parameters);
//                request.executeAsync();


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
//
//        callbackManager = CallbackManager.Factory.create();
//
//        LoginManager.getInstance().registerCallback(callbackManager,
//                new FacebookCallback<LoginResult>() {
//                    @Override
//                    public void onSuccess(LoginResult loginResult) {
//                        // App code
//
//
//                    }
//
//                    @Override
//                    public void onCancel() {
//                        // App code
//                    }
//
//                    @Override
//                    public void onError(FacebookException exception) {
//                        // App code
//                    }
//                });

/**
 * facebook login
 */

    }


    public void loginUser(HashMap<String, String> map) {
        createProgressDialog();

        Observer<LoginRes> observer = apiService.loginUser(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<LoginRes>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginRes res) {

                        if (res.getStatus()) {

                            if (res.getMessage().equals("User is not active")) {
                                AppConstants.LoginProcess.mUserIdForActivationAccountAfterOTPVerification =
                                        String.valueOf(res.getUserdata().getId());
                                AppConstants.LoginProcess.mMobileNumber = "+" + res.getUserdata().getContactNo();

                                // after successfull  register then hit otp confirmation mobile verification
                                openActivity(AppConstants.LoginProcess.mMobileNumber, AppConstants.OTPVerification.INTENT_METHOD);

                            } else {

                                oneTimeLogin.setFirstTimeLaunch(false);
                                SharedPreferences.Editor editor = getSharedPreferences("Login", MODE_PRIVATE).edit();
                                editor.putString("userid", res.getUserdata().getId());
                                editor.putString("token", res.getUserdata().getToken());
                                editor.putString("name", res.getUserdata().getName());
                                editor.putString("contact", res.getUserdata().getContactNo());
                                editor.putString("address", res.getUserdata().getAddress().toString());
                                editor.putString("email", res.getUserdata().getEmail());

                               editor.commit();


//                                sessionManager.createLoginSession(res.getUserdata().getName(),
//                                        res.getUserdata().getEmail(), res.getUserdata().getPassword(), res.getUserdata().getContactNo(), res.getUserdata().getId(),
//                                        res.getUserdata().getStatus(), res.getUserdata().getAddress(), res.getUserdata().getProfileImage(),
//                                        res.getUserdata().getType(), res.getUserdata().getToken());
//

                                Toast.makeText(Login.this, res.getMessage(), Toast.LENGTH_SHORT).show();
                                Intent intent_continue = new Intent(Login.this, HomeNavigation.class);
                                startActivity(intent_continue);
                                finish();

                            }

                        }
                        else {
                            Toast.makeText(Login.this, res.getMessage(), Toast.LENGTH_SHORT).show();
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

    private void openActivity(String phoneNumber, String method) {
        Intent verification = new Intent(this, OTP.class);
        verification.putExtra(AppConstants.OTPVerification.INTENT_PHONENUMBER, phoneNumber);
        verification.putExtra(AppConstants.OTPVerification.INTENT_METHOD, method);
        startActivity(verification);
    }

    private void handleLogin() {
        String email = et_email.getText().toString();
        //  String l_name = edt_last_name.getText().toString();


        String pass = et_pass.getText().toString();
        if (email.isEmpty()) {
            et_email.setError("Enter email");
        } else if (pass.isEmpty()) {
            et_pass.setError("Enter password");
        } else {


            createProgressDialog();
            map = new HashMap<>();
            map.put("email", et_email.getText().toString());
            map.put("password", et_pass.getText().toString());
            map.put("device_type", "Android");
            map.put("device_token", device_token);
            map.put("usertype", "1");

            Log.e("params login", et_email.getText().toString() + et_pass.getText().toString() + device_token);
            loginUser(map);
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }else {
            callbackManager.onActivityResult(requestCode, resultCode, data);

        }

    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.


        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.wtf(TAG, "signInResult:failed code=" + e.getStatusCode());


        }
    }

//    public void onClick(View v) {
//        if (v == fblogin) {
//            loginButton.performClick();
//        }
//
//
//    }
//    @Override
    public void onClick(View v) {
        switch (v.getId()) {


            case R.id.fblogin :
                loginButton.performClick();
                break;

            // ...
        }
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

}
