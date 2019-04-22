package snow.app.ideelee.HomeScreen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import me.gujun.android.taggroup.TagGroup;
import snow.app.ideelee.HomeScreen.Adapters.ServiceProviderCategoryAdapter;
import snow.app.ideelee.HomeScreen.Adapters.ServiceProvidersAdapter;
import snow.app.ideelee.HomeScreen.Modals.ServiceProvider;
import snow.app.ideelee.HomeScreen.Modals.ServiceProviderList;
import snow.app.ideelee.R;


public class ServiceActivity extends Activity {

    List<ServiceProviderList> serviceproviderlist;
    TagGroup mTagGroup;
    RecyclerView recyclerView;
    public ServiceActivity() {
    }
ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_fragment);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));
imageView=findViewById(R.id.backbutton);
imageView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(ServiceActivity.this,HomeNavigation.class);
        startActivity(intent);
    }
});
        //initializing the productlist
        serviceproviderlist = new ArrayList<>();

        serviceproviderlist.add(
                new ServiceProviderList(
                        getResources().getString(R.string.electrician),
                        "20km",
                        4.5,
                        R.drawable.img
                ));

        serviceproviderlist.add(
                new ServiceProviderList(
                        getResources().getString(R.string.plumber),
                        "25km",
                        4.5,
                        R.drawable.img
                ));

        serviceproviderlist.add(
                new ServiceProviderList(
                        getResources().getString(R.string.carpentar),
                        "67km",
                        4.5,
                        R.drawable.img));
        //

        ServiceProvidersAdapter adapter = new ServiceProvidersAdapter(this, serviceproviderlist);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(ServiceActivity.this,HomeNavigation.class);
        startActivity(intent);
        finish();

    }
}
