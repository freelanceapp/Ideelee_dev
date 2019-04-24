package snow.app.ideelee.HomeScreen.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import snow.app.ideelee.ActiveJobFragment;
import snow.app.ideelee.HomeScreen.CompletedJobFragment;
import snow.app.ideelee.WalletPaymentFragment;
import snow.app.ideelee.WalletPendingFragment;

public class WalletPagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public WalletPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                WalletPaymentFragment tab1 = new WalletPaymentFragment();
                return tab1;
            case 1:
                WalletPendingFragment tab2 = new WalletPendingFragment();
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