package snow.app.ideelee;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import snow.app.ideelee.HomeScreen.HomeNavigation;
import snow.app.ideelee.extrafiles.AppConstants;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class OTP extends Activity {
   @BindView(R.id.ux_btn_continue_otpPage) Button btn_continue_otpPage;
    private static final String TAG = "ScreenEnterOtp";

    private static final String[] SMS_PERMISSIONS = {
            Manifest.permission.READ_SMS,
            Manifest.permission.SEND_SMS};
    private Verification mVerification;
    private KProgressHUD mKProgressHUD, mKProgressHUDWithText;
    private boolean mShouldFallback = true;
    private boolean mIsVerified;
    private String mPhoneNumber;
    @BindView(R.id.pinview_otp)
    PinView pinviewOtp;
    @BindView(R.id.txt_Resend)
    TextView mTextResendCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        ButterKnife.bind(this);
        btn_continue_otpPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_continue=new Intent(OTP.this, HomeNavigation.class);
                startActivity(intent_continue);
            }
        });

    //  init();

//        mTextResendCode.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(OTP.this, "Resending...", Toast.LENGTH_SHORT).show();
//                requestPermissions();
//            }
//        });
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
                showIOSProgress();
            }
        }
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
              //  showToast("Incorrect number");
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
            dismissIOSProgress();

            showCompleted();
        }

        @Override
        public void onVerificationFailed(Exception exception) {
            if (mIsVerified) {
                return;
            }

            dismissIOSProgress();

            Log.wtf(TAG, "Verification failed: " + exception.getMessage());
           // showToastLong("Verification failed: Enter Correct OTP");
            Toast.makeText(OTP.this, "Verification failed: Enter Correct OTP", Toast.LENGTH_SHORT).show();
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
}
