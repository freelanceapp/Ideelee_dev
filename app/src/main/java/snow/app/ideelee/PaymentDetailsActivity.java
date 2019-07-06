package snow.app.ideelee;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class PaymentDetailsActivity extends Activity {
    @BindView(R.id.ux_btn_pay) Button btn_pay;
    @BindView(R.id.backbutton1) ImageView backbutton1;
    @BindView(R.id.title_bookingappointement)
    TextView title_bookingappointement;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_details);
        ButterKnife.bind(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        backbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        title_bookingappointement.setText("Checkout");
        btn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PaymentDetailsActivity.this,PaymentSuccessfulActivity.class);
                intent.putExtra("key","Rate Harry");
                startActivity(intent);
            }
        });
    }

}
