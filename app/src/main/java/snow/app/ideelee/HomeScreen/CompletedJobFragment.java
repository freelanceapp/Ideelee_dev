package snow.app.ideelee.HomeScreen;

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
import snow.app.ideelee.HomeScreen.Adapters.ServiceProviderCategoryAdapter;
import snow.app.ideelee.HomeScreen.Modals.CompletedJobModal;
import snow.app.ideelee.HomeScreen.Modals.ServiceProvider;
import snow.app.ideelee.HomeScreen.Modals.ServiceProviderList;
import snow.app.ideelee.R;


public class CompletedJobFragment extends Fragment {

    List<CompletedJobModal> serviceproviderlist;
    TagGroup mTagGroup;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_completed_job, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView_completedjob);
        serviceproviderlist = new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, true));
        serviceproviderlist.add(
                new CompletedJobModal(
                        "completed", "APR 18,2019 02:00PM", "JACK HARRY", "", "", "", "Online Payment",0));
        serviceproviderlist.add(
                new CompletedJobModal(
                        "completed", "APR 18,2019 02:00PM", "JACK HARRY", "", "", "", "Cash Payment",4));
        serviceproviderlist.add(
                new CompletedJobModal(
                        "completed", "APR 18,2019 02:00PM", "JACK HARRY", "", "", "", "Online payment",5));
        CompletedJobAdapter adapter = new CompletedJobAdapter(getActivity(), serviceproviderlist);
        recyclerView.setAdapter(adapter);
        return v;
    }

}
