package snow.app.ideelee;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Register extends Activity {
TextView txt_loginnow_registerPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        txt_loginnow_registerPage=findViewById(R.id.ux_txt_loginnow_registerPage);
        txt_loginnow_registerPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_login=new Intent(Register.this,Login.class);
                startActivity(intent_login);
            }
        });
    }
}
