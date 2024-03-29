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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.gujun.android.taggroup.TagGroup;
import snow.app.ideelee.HomeScreen.Adapters.ActiveJobAdapter;
import snow.app.ideelee.HomeScreen.Adapters.CompletedJobAdapter;
import snow.app.ideelee.HomeScreen.Modals.ActiveJobModal;
import snow.app.ideelee.HomeScreen.Modals.CompletedJobModal;


public class ActiveJobFragment extends Fragment {

    List<ActiveJobModal> serviceproviderlist;
    TagGroup mTagGroup;
    @BindView
            (R.id.recyclerView_activejob)
    RecyclerView recyclerView;
    private Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_active_job, container, false);
        unbinder = ButterKnife.bind(this, v);


        serviceproviderlist = new ArrayList<>();

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, true));
        serviceproviderlist.add(
                new ActiveJobModal(
                        "Pending", "APR 18,2019 02:00PM", "JACK HARRY", "", "", "", "Online Payment"));

        serviceproviderlist.add(
                new ActiveJobModal(
                        "Accept", "APR 18,2019 02:00PM", "JACK HARRY", "", "", "", "cash on delivery"));

        serviceproviderlist.add(
                new ActiveJobModal(
                        "On Going", "APR 18,2019 02:00PM", "JACK HARRY", "", "", "", "Online payment"));
        //

        ActiveJobAdapter adapter = new ActiveJobAdapter(getActivity(), serviceproviderlist);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);
        return v;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
