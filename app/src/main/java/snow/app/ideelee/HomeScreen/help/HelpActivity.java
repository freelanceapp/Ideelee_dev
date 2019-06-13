package snow.app.ideelee.HomeScreen.help;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import snow.app.ideelee.R;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class HelpActivity extends AppCompatActivity {

   @BindView
  (R.id.backbutton1) ImageView backbutton1;
    ImageView notification;
  @BindView(R.id.title_bookingappointement)  TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        ButterKnife.bind(this);
        title.setText("Help");
        backbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

}
