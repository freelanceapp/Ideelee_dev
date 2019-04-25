package snow.app.ideelee.forgot;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import snow.app.ideelee.Login;
import snow.app.ideelee.R;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ForgotPassword extends AppCompatActivity {
    TextView login;
    TextView txt_note;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        login=(TextView)findViewById(R.id.login);
        txt_note=(TextView)findViewById(R.id.txt_note);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ForgotPassword.this, Login.class));
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            txt_note.setText(Html.fromHtml("<pre><span style=\"color: #ff0000;\">Note:-</span> <span style=\"color: #000000;\">Lorem Ipsum is simply dummy text of the printing and typesetting industry..</span></pre>", Html.FROM_HTML_MODE_COMPACT));
        } else {
            txt_note.setText(Html.fromHtml("<pre><span style=\"color: #ff0000;\">Note:-</span> <span style=\"color: #000000;\">Lorem Ipsum is simply dummy text of the printing and typesetting industry..</span></pre>"));
        }
    }

}
