package snow.app.ideelee.HomeScreen.invites;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import snow.app.ideelee.R;

public class InviteActivity extends Activity {
    TextView title;
    ImageView backbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_invite);
        title=(TextView)findViewById(R.id.title);
        backbutton=(ImageView) findViewById(R.id.backbutton);
        title.setText("Invite");
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



}
