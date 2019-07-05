package snow.app.ideelee;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.sinch.verification.Config;
import com.sinch.verification.InitiationResult;
import com.sinch.verification.InvalidInputException;
import com.sinch.verification.ServiceErrorException;
import com.sinch.verification.SinchVerification;
import com.sinch.verification.Verification;
import com.sinch.verification.VerificationListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import snow.app.ideelee.HomeScreen.HomeNavigation;
import snow.app.ideelee.api_request_retrofit.ApiService;
import snow.app.ideelee.api_request_retrofit.retrofit_client.ApiClient;
import snow.app.ideelee.extrafiles.AppConstants;
import snow.app.ideelee.extrafiles.BaseActivity;
import snow.app.ideelee.extrafiles.SessionManager;
import snow.app.ideelee.responses.confirmotpresponse.ConfirmOtpRes;

import static snow.app.ideelee.extrafiles.AppConstants.LoginProcess.mUserIdForActivationAccountAfterOTPVerification;

public class OTP extends BaseActivity {
    private static final String TAG = "ScreenEnterOtp";
    private static final String[] SMS_PERMISSIONS = {
            Manifest.permission.READ_SMS,
            Manifest.permission.SEND_SMS};
    @BindView(R.id.ux_btn_continue_otpPage)
    Button btn_continue_otpPage;
    @BindView(R.id.pinview_otp)
    PinView pinviewOtp;
    @BindView(R.id.txt_Resend)
    TextView mTextResendCode;
    ApiService apiService;
    HashMap<String, String> map;
    String userid, device_token;
    private Verification mVerification;
    private KProgressHUD mKProgressHUD, mKProgressHUDWithText;
    private boolean mShouldFallback = true;
    private boolean mIsVerified;
    private String mPhoneNumber;
SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        ButterKnife.bind(this);
        device_token = getDeviceToken(OTP.this);
        apiService = ApiClient.getClient(getApplicationContext())
                .create(ApiService.class);

        userid = getIntent().getStringExtra("userid");

        // initialisation
        init();

        mTextResendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(OTP.this, "Resending...", Toast.LENGTH_SHORT).show();
                requestPermissions();
            }
        });
    }

    private void init() {
        mTextResendCode.setText(Html.fromHtml("If you didn't receive a code! <font color='#0082E6'>Resend!</font>"));

        // get previous vale from the intent
        Intent intent = getIntent();
        if (intent != null) {
            mPhoneNumber = intent.getStringExtra(AppConstants.OTPVerification.INTENT_PHONENUMBER);
//            final String method = intent.getStringExtra(AppConstants.OTPVerification.INTENT_METHOD);

            requestPermissions();

        } else {
            Log.e(TAG, "The provided intent is null.");
        }


        pinviewOtp.setAnimationEnable(true);
        pinviewOtp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    private void requestPermissions() {
        List<String> missingPermissions;
        String methodText;

        missingPermissions = getMissingPermissions(SMS_PERMISSIONS);
        methodText = "SMS";


        if (missingPermissions.isEmpty()) {
            createVerification();
        } else {
            if (needPermissionsRationale(missingPermissions)) {
                Toast.makeText(this, "This application needs permissions to read your " + methodText + " to automatically verify your "
                        + "phone, you may disable the permissions once you have been verified.", Toast.LENGTH_LONG)
                        .show();
            }
            ActivityCompat.requestPermissions(this,
                    missingPermissions.toArray(new String[missingPermissions.size()]),
                    0);
        }
    }

    private void createVerification() {
        // It is important to pass ApplicationContext to the Verification config builder as the
        // verification process might outlive the activity.
        Config config = SinchVerification.config()
                .applicationKey(AppConstants.OTPVerification.APPLICATION_KEY)
                .appHash(AppConstants.OTPVerification.APPLICATION_HASH)
                .context(getApplicationContext())
                .build();

        VerificationListener listener = new MyVerificationListener();

        mVerification = SinchVerification.createSmsVerification(config, mPhoneNumber, listener);
        mVerification.initiate();


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        // Proceed with verification after requesting permissions.
        // If the verification SDK fails to intercept the code automatically due to missing permissions,
        // the VerificationListener.onVerificationFailed(1) method will be executed with an instance of
        // CodeInterceptionException. In this case it is still possible to proceed with verification
        // by asking the user to enter the code manually.
        createVerification();
    }

    private List<String> getMissingPermissions(String[] requiredPermissions) {
        List<String> missingPermissions = new ArrayList<>();
        for (String permission : requiredPermissions) {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), permission)
                    != PackageManager.PERMISSION_GRANTED) {
                missingPermissions.add(permission);
            }
        }
        return missingPermissions;
    }


    private boolean needPermissionsRationale(List<String> permissions) {
        for (String permission : permissions) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                return true;
            }
        }
        return false;
    }

    private void showCompleted() {
        // Todo: after success send the control according
        //  showToast("Number " + AppConstants.LoginProcess.mMobileNumber + " is Verified!");

        // goForHomeFromLeftToRight(ScreenLogin.class);
        //  activateUser(AppConstants.LoginProcess.mUserIdForActivationAccountAfterOTPVerification);


        //Todo: work done here what you want to do next after verification
//        goToNextWithClearBackStack(ScreenLogin.class);
        Intent i = new Intent(this, Login.class);
        // set the new task and clear flags
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
//        overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
        finish();


    }

    public void onSubmitClicked() {

        String code = pinviewOtp.getText().toString().trim();
        if (!code.isEmpty()) {
            if (mVerification != null) {
                mVerification.verify(code);
                createProgressDialog();


            }
        }
    }

    @OnClick(R.id.ux_btn_continue_otpPage)
    public void onViewClicked() {
        //goForHomeFromLeftToRight(ScreenLogin.class);

        onSubmitClicked();
    }

    public void showIOSProgress() {
        try {
            mKProgressHUD = KProgressHUD.create(this)
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    //  .setLabel("Please wait")
                    //  .setDetailsLabel(msg)
                    .setCancellable(true)
                    .setAnimationSpeed(1)
                    .setDimAmount(.2f)
                    .show();
        } catch (Exception ex) {
            Log.wtf("IOS_error_starting", ex.getCause());
        }
    }

    public void dismissIOSProgress() {
        try {
            if (mKProgressHUD != null) {
                if (mKProgressHUD.isShowing()) {
                    mKProgressHUD.dismiss();
                }
            }
        } catch (Exception ex) {
            Log.wtf("IOS_error_dismiss", ex.getCause());
        }


    }

    private void handleConfirmOtp() {

        createProgressDialog();


        HashMap<String, String> map = new HashMap<>();
        map.put("userid", mUserIdForActivationAccountAfterOTPVerification);
        map.put("signup_type", "1");
        map.put("device_type", "Android");
        map.put("device_token", device_token);
        map.put("usertype", "1");

        confirmOtp(map);


    }

    public void confirmOtp(HashMap<String, String> map) {
        createProgressDialog();

        Observer<ConfirmOtpRes> observer = apiService.confirmOtp(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<ConfirmOtpRes>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ConfirmOtpRes res) {

                        if (res.getStatus()) {

                            SharedPreferences.Editor editor = getSharedPreferences("Login", MODE_PRIVATE).edit();
                            editor.putString("userid", res.getUserdata().getId());
                            editor.putString("token", res.getUserdata().getToken());
                            editor.putString("name", res.getUserdata().getName());
                            editor.putString("contact", res.getUserdata().getContactNo());
                            editor.putString("address", res.getUserdata().getAddress().toString());
                            editor.putString("email", res.getUserdata().getEmail());
                            editor.commit();
//                            sessionManager.createLoginSession(res.getUserdata().getName(),
//                                    res.getUserdata().getEmail(),res.getUserdata().getPassword(),res.getUserdata().getContactNo(),res.getUserdata().getId(),
//                                    res.getUserdata().getStatus(),res.getUserdata().getAddress().toString(),res.getUserdata().getProfileImage().toString(),
//                                    res.getUserdata().getType(),res.getUserdata().getToken());

                            Toast.makeText(OTP.this, res.getMessage(), Toast.LENGTH_SHORT).show();
                            Intent intent_continue = new Intent(OTP.this, HomeNavigation.class);
                            startActivity(intent_continue);
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

    class MyVerificationListener implements VerificationListener {

        @Override
        public void onInitiated(InitiationResult result) {
            Log.d(TAG, "Initialized!");

        }

        @Override
        public void onInitiationFailed(Exception exception) {
            Log.e(TAG, "Verification initialization failed: " + exception.getMessage());
            dismissIOSProgress();

            if (exception instanceof InvalidInputException) {
                // Incorrect number provided
                Toast.makeText(OTP.this, "Incorrect number", Toast.LENGTH_SHORT).show();
            } else if (exception instanceof ServiceErrorException) {
                // Verification initiation aborted due to early reject feature,
                // client callback denial, or some other Sinch service error.
                // Fallback to other verification method here.
                fallbackIfNecessary();
            } else {
                // Other system error, such as UnknownHostException in case of network error

                Toast.makeText(OTP.this, "System error", Toast.LENGTH_SHORT).show();

            }
        }


        @Override
        public void onVerificationFallback() {
            fallbackIfNecessary();
        }

        private void fallbackIfNecessary() {
            if (mShouldFallback) {
                Log.wtf(TAG, "Falling back to sms verification.");
                mShouldFallback = false;

                requestPermissions(); // Initiate verification with the alternative method.
            }
        }

        @Override
        public void onVerified() {
            mIsVerified = true;
            Log.d(TAG, "Verified!");
            dismissProgressDialog();
            handleConfirmOtp();
            showCompleted();
        }

        @Override
        public void onVerificationFailed(Exception exception) {
            dismissProgressDialog();
            if (mIsVerified) {

                return;
            }



            Log.wtf(TAG, "Verification failed: " + exception.getMessage());
            Toast.makeText(OTP.this, "Verification failed: Enter Correct OTP", Toast.LENGTH_SHORT).show();

        }

    }


}
