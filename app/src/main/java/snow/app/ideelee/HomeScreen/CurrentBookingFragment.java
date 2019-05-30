package snow.app.ideelee.HomeScreen;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import snow.app.ideelee.HomeScreen.Adapters.CurrentBookingAdapter;
import snow.app.ideelee.R;


public class CurrentBookingFragment extends Fragment {


    CurrentBookingAdapter adapter;
    @BindView
            (R.id.pager)
    ViewPager viewPager;
    @
            BindView(R.id.tab_layout)
    TabLayout tabLayout;
    private Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_current_booking, container, false);
        unbinder = ButterKnife.bind(this, v);
        tabLayout.addTab(tabLayout.newTab().setText("ACTIVE JOB"));
        tabLayout.addTab(tabLayout.newTab().setText("COMPLETED JOB"));
//        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        adapter = new CurrentBookingAdapter
                (getActivity().getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return v;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}





