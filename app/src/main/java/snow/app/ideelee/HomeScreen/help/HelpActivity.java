package snow.app.ideelee.HomeScreen.help;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import snow.app.ideelee.R;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class HelpActivity extends AppCompatActivity {

    ImageView backbutton1;
    ImageView notification;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        backbutton1=(ImageView)findViewById(R.id.backbutton1);
        notification=(ImageView)findViewById(R.id.notification);
        title=(TextView) findViewById(R.id.title_bookingappointement);
        title.setText("Help");
        backbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

}
