package snow.app.ideelee;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class PaymentMethodActivity extends AppCompatActivity {
TextView title_bookingappointement;
ImageView backbutton1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);
        backbutton1=findViewById(R.id.backbutton1);
        title_bookingappointement=(TextView) findViewById(R.id.title_bookingappointement);
        backbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        title_bookingappointement.setText("Payment Method");
    }
}
