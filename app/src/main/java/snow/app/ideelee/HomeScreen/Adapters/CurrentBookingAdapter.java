package snow.app.ideelee.HomeScreen.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import snow.app.ideelee.ActiveJobFragment;
import snow.app.ideelee.HomeScreen.CompletedJobFragment;

public class CurrentBookingAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public CurrentBookingAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                ActiveJobFragment tab1 = new ActiveJobFragment();
                return tab1;
            case 1:
                CompletedJobFragment tab2 = new CompletedJobFragment();
                return tab2;


            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}