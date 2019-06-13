package snow.app.ideelee;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import snow.app.ideelee.HomeScreen.Adapters.CurrentBookingAdapter;
import snow.app.ideelee.HomeScreen.Adapters.WalletPagerAdapter;


public class WalletFragment extends Fragment {

    WalletPagerAdapter adapter;
    @BindView
            (R.id.pager_wallet)
    ViewPager viewPager;
@BindView(R.id.tab_layout) TabLayout tabLayout;
    private Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_wallet_fragmen, container, false);
        unbinder = ButterKnife.bind(this, v);

        tabLayout.addTab(tabLayout.newTab().setText("PAYMENT"));
        tabLayout.addTab(tabLayout.newTab().setText("PENDING"));
//        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        adapter = new WalletPagerAdapter
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