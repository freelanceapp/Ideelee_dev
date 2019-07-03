package snow.app.ideelee;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import snow.app.ideelee.HomeScreen.Adapters.CategoriesAdapter;
import snow.app.ideelee.HomeScreen.Adapters.GridAdapter;
import snow.app.ideelee.HomeScreen.Adapters.MainCategory;
import snow.app.ideelee.HomeScreen.Adapters.MoreCatAdapter;
import snow.app.ideelee.HomeScreen.ServiceActivity;
import snow.app.ideelee.api_request_retrofit.ApiService;
import snow.app.ideelee.api_request_retrofit.retrofit_client.ApiClient;
import snow.app.ideelee.fooddelivery.restaurantsmod.RestaurantsList;
import snow.app.ideelee.responses.homescreenres.HomeScreenRes;
import snow.app.ideelee.responses.homescreenres.ParentCatArray;
import snow.app.ideelee.responses.homescreenres.PopularProfile;
import snow.app.ideelee.responses.morecatres.MoreCategoryRes;
import snow.app.ideelee.responses.morecatres.Parentcatdatum;

public class Categories extends AppCompatActivity {
    CategoriesAdapter adapterViewAndroid;
    @BindView(R.id.recyclerView_grid)
    RecyclerView recyclerView_grid;
    @BindView(R.id.back)
    ImageView backbutton1;
    ApiService apiService;
    HashMap<String, String> map;
    List<Parentcatdatum> parentCatArrayArrayList;
String userid,token;
    MoreCatAdapter moreCatAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        ButterKnife.bind(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        recyclerView_grid.setHasFixedSize(true);

        SharedPreferences prefs =  Categories.this.getSharedPreferences("Login", MODE_PRIVATE);
        userid = prefs.getString("userid", "0");
        token = prefs.getString("token", "0");

        parentCatArrayArrayList = new ArrayList<>();
        apiService = ApiClient.getClient( Categories.this)
                .create(ApiService.class);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(Categories.this, 4, LinearLayoutManager.VERTICAL, false);
        recyclerView_grid.setLayoutManager(gridLayoutManager);
          moreCatAdapter = new MoreCatAdapter( Categories.this, parentCatArrayArrayList);
        recyclerView_grid.setAdapter(moreCatAdapter);

        handleMoreCatData();
//        adapterViewAndroid = new CategoriesAdapter(Categories.this, gridViewString, gridViewImageId);
//
//        gridView_category.setAdapter(adapterViewAndroid);
//
//
//        gridView_category.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view,
//                                    int i, long id) {
//
//                startActivity(new Intent(Categories.this, RestaurantsList.class));
//
//                Toast.makeText(Categories.this, "GridView Item: " + gridViewString[+i],
//                        Toast.LENGTH_LONG).show();
//
//            }
//        });
        backbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void getMOreCatData(HashMap<String, String> map) {
        // showProgress();

        Observer<MoreCategoryRes> observer = apiService.getMoreCats(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<MoreCategoryRes>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MoreCategoryRes res) {
                        if (res.getStatus()) {
                            Toast.makeText( Categories.this, res.getMessage(), Toast.LENGTH_SHORT).show();

                            //  for (int i = 0; i < res.getHomescreendata().getParentCatArray().size(); i++) {

                            parentCatArrayArrayList.addAll(res.getParentcatdata());

                            moreCatAdapter.notifyDataSetChanged();

                            //add more on  the last the position








                        } else {
                            Toast.makeText( Categories.this, res.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {


                    }

                    @Override
                    public void onComplete() {


                    }
                });


    }


    private void handleMoreCatData() {
        map = new HashMap<>();

        map.put("userid", userid);
        map.put("token", token);


        getMOreCatData(map);
    }


}
