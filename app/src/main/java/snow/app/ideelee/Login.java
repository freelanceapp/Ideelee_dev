package snow.app.ideelee;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.rilixtech.Country;
import com.rilixtech.CountryCodePicker;

import snow.app.ideelee.HomeScreen.HomeNavigation;
import snow.app.ideelee.forgot.ForgotPassword;

public class Login extends Activity {


    TextView txt_registernow_loginPage;
    Button btn_continue_loginPage;
    TextView forgot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
         txt_registernow_loginPage=findViewById(R.id.ux_txt_registernow_loginPage);
         btn_continue_loginPage=findViewById(R.id.ux_btn_continue_loginPage);
        forgot=(TextView) findViewById(R.id.forgot);

        txt_registernow_loginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_register=new Intent(Login.this,Register.class);
                startActivity(intent_register);
            }
        });
        btn_continue_loginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_continue=new Intent(Login.this, OTP.class);
                startActivity(intent_continue);
            }
        });

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_continue=new Intent(Login.this, ForgotPassword.class);
                startActivity(intent_continue);
            }
        });
    }
}
