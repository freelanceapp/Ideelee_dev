package snow.app.ideelee;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.gujun.android.taggroup.TagGroup;
import snow.app.ideelee.HomeScreen.Adapters.CompletedJobAdapter;
import snow.app.ideelee.HomeScreen.Adapters.ServiceJobAdapter;
import snow.app.ideelee.HomeScreen.HomeNavigation;
import snow.app.ideelee.HomeScreen.Modals.CompletedJobModal;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ServiceProfile extends AppCompatActivity {
    List<String> serviceproviderlist;
@BindView(R.id.rv_serviceprofile)
RecyclerView recyclerView;
  @BindView(R.id.backbutton1)  ImageView backbutton1;
    Button btn_back;
    @BindView(R.id.ux_btn_book) Button book;
   @BindView(R.id.title_bookingappointement) TextView title_bookingappointement;
   @BindView(R.id.ratingbar_service) RatingBar ratingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_profile);
        ButterKnife.bind(this);

        serviceproviderlist = new ArrayList<>();
        recyclerView.setNestedScrollingEnabled(false);

        ratingBar.setRating(5);

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initiatePopupwindow(v);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(ServiceProfile.this, LinearLayoutManager.VERTICAL, true));
       serviceproviderlist.add("dfds");
       serviceproviderlist.add("dfsf");
       serviceproviderlist.add("fghf");
       serviceproviderlist.add("dfd");
        ServiceJobAdapter adapter = new ServiceJobAdapter(ServiceProfile.this, serviceproviderlist);
        recyclerView.setAdapter(adapter);


        backbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        title_bookingappointement.setText("");
    }
    public void initiatePopupwindow(View v) {

        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.book_now_dialog, (ViewGroup) v.findViewById(R.id.linearlayout_book));
        final PopupWindow pw = new PopupWindow(layout, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        pw.showAtLocation(v, Gravity.RIGHT, 0, 0);
        View container = (View) pw.getContentView().getRootView();
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams p = (WindowManager.LayoutParams) container.getLayoutParams();
        p.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        p.dimAmount = 0.6f;
        wm.updateViewLayout(container, p);


    }

}
