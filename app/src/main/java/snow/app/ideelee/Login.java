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

public class Login extends Activity {
    CountryCodePicker ccp;
    AppCompatEditText edtPhoneNumber;
    TextView txt_registernow_loginPage;
    Button btn_continue_loginPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ccp = (CountryCodePicker) findViewById(R.id.ccp);
        txt_registernow_loginPage=findViewById(R.id.ux_txt_registernow_loginPage);
        edtPhoneNumber = (AppCompatEditText) findViewById(R.id.phone_number_edt);
        btn_continue_loginPage=findViewById(R.id.ux_btn_continue_loginPage);
        ccp.registerPhoneNumberTextView(edtPhoneNumber);
        ccp.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected(Country selectedCountry) {
                Toast.makeText(Login.this, "Updated " + selectedCountry.getName(), Toast.LENGTH_SHORT).show();
            }
        });
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
    }
}
