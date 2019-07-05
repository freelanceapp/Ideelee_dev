package snow.app.ideelee.search;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import snow.app.ideelee.R;
import snow.app.ideelee.search.adapters.SearchAdapter;

public class SearchActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    @BindView(R.id.spinner)
    Spinner spinner;

    @BindView(R.id.rv_list)
    RecyclerView rv_list;
    SearchAdapter searchAdapter;
    String item;
    List<String> couponcategorydatumList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(SearchActivity.this);

couponcategorydatumList=new ArrayList<>();
        rv_list.setLayoutManager(new LinearLayoutManager(SearchActivity.this, LinearLayoutManager.VERTICAL, false));

        couponcategorydatumList.add("Veg Loaded");
        couponcategorydatumList.add("Margherita");
        couponcategorydatumList.add("Margherita");
        searchAdapter = new SearchAdapter(SearchActivity.this, couponcategorydatumList);
        rv_list.setAdapter(searchAdapter);

        spinner.setOnItemSelectedListener(SearchActivity.this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("OnDemand");
        categories.add("Delivery");



        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
