package snow.app.ideelee;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import snow.app.ideelee.HomeScreen.HomeNavigation;

public class OTP extends Activity {
Button btn_continue_otpPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        btn_continue_otpPage=findViewById(R.id.ux_btn_continue_otpPage);
        btn_continue_otpPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_continue=new Intent(OTP.this, HomeNavigation.class);
                startActivity(intent_continue);
            }
        });
    }
}
