package snow.app.ideelee.forgot;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import snow.app.ideelee.Login;
import snow.app.ideelee.OTP;
import snow.app.ideelee.R;
import snow.app.ideelee.api_request_retrofit.ApiService;
import snow.app.ideelee.api_request_retrofit.retrofit_client.ApiClient;
import snow.app.ideelee.extrafiles.BaseActivity;
import snow.app.ideelee.responses.forgotpassword.ForgotPassRes;

public class ForgotPassword extends BaseActivity {
    @BindView(R.id.login)
    TextView login;
    @BindView(R.id.ed_email)
    EditText ed_email;
    @BindView(R.id.txt_note)
    TextView txt_note;

    @BindView(R.id.ux_btn_continue_otpPage)
    Button btn_for;
    ApiService apiService;
    HashMap<String, String> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        ButterKnife.bind(this);
        apiService = ApiClient.getClient(getApplicationContext())
                .create(ApiService.class);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ForgotPassword.this, Login.class));
            }
        });


        btn_for.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             handleForgotPass();
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            txt_note.setText(Html.fromHtml("<pre><span style=\"color: #ff0000;\">Note:-</span> <span style=\"color: #000000;\">Lorem Ipsum is simply dummy text of the printing and typesetting industry..</span></pre>", Html.FROM_HTML_MODE_COMPACT));
        } else {
            txt_note.setText(Html.fromHtml("<pre><span style=\"color: #ff0000;\">Note:-</span> <span style=\"color: #000000;\">Lorem Ipsum is simply dummy text of the printing and typesetting industry..</span></pre>"));
        }


    }


    public void forgotPassword(HashMap<String, String> map) {
        createProgressDialog();

        Observer<ForgotPassRes> observer = apiService.forgotpass(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<ForgotPassRes>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ForgotPassRes res) {
                        if (res.getStatus()) {

                            Toast.makeText(ForgotPassword.this, res.getMessage(), Toast.LENGTH_LONG).show();
                            Intent intent_continue = new Intent(ForgotPassword.this, Login.class);
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

    private void handleForgotPass() {
        String email = ed_email.getText().toString();
        if (email.isEmpty()) {
            ed_email.setError("Please enter email");

        } else {

            map = new HashMap<>();
            map.put("useremail", ed_email.getText().toString());

            Log.e("params login", ed_email.getText().toString());
             forgotPassword(map);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        dismissProgressDialog();
    }
}
