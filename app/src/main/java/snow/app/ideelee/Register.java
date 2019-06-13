package snow.app.ideelee;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import snow.app.ideelee.extrafiles.AppConstants;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Register extends Activity {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

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
                handleSignup();

            }
        });
    }

    private void handleSignup() {
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
//            HashMap<String, String> map = new HashMap<>();
//            map.put("first_name", f_name);
//            map.put("last_name", l_name);
//            map.put("email", email);
//            map.put("phone_number", String.valueOf(ccp.getSelectedCountryCode()) + "" + phone_);
//            map.put("op_type", SELECTED_ROLE);
//            map.put("password", pass);

            //  registerCall(map);
            Intent intent_login = new Intent(Register.this, OTP.class);
            intent_login.putExtra(AppConstants.OTPVerification.INTENT_PHONENUMBER,edt_phone.getText().toString());
            startActivity(intent_login);
        }

    }

}
