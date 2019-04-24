package snow.app.ideelee;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import me.gujun.android.taggroup.TagGroup;
import snow.app.ideelee.HomeScreen.Adapters.CompletedJobAdapter;
import snow.app.ideelee.HomeScreen.Adapters.WalletPaymentAdapter;
import snow.app.ideelee.HomeScreen.Modals.CompletedJobModal;


public class WalletPaymentFragment extends Fragment {
    List<String> serviceproviderlist;
    TagGroup mTagGroup;
    RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
  View v=inflater.inflate(R.layout.fragment_wallet_payment, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView_walletpayment);
        serviceproviderlist = new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, true));
        serviceproviderlist.add("hjghj");
        serviceproviderlist.add("hjghj");
        serviceproviderlist.add("hjghj");
        serviceproviderlist.add("hjghj");
        WalletPaymentAdapter adapter = new WalletPaymentAdapter(getActivity(), serviceproviderlist);
        recyclerView.setAdapter(adapter);
        return  v;
    }

}
