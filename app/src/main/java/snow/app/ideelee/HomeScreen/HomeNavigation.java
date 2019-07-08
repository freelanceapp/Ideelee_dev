package snow.app.ideelee.HomeScreen;

import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import snow.app.ideelee.AddAddress;
import snow.app.ideelee.AppUtils.CircleTransform;
import snow.app.ideelee.CouponActivity;
import snow.app.ideelee.HomeScreen.Adapters.MainCategory;
import snow.app.ideelee.HomeScreen.Adapters.ViewPagerHome;
import snow.app.ideelee.HomeScreen.help.HelpActivity;
import snow.app.ideelee.HomeScreen.invites.InviteActivity;
import snow.app.ideelee.HomeScreen.orders.MyOrders;
import snow.app.ideelee.HomeScreen.profile.ProfileFragment;
import snow.app.ideelee.Login;
import snow.app.ideelee.R;
import snow.app.ideelee.WalletFragment;
import snow.app.ideelee.api_request_retrofit.ApiService;
import snow.app.ideelee.api_request_retrofit.retrofit_client.ApiClient;
import snow.app.ideelee.extrafiles.OneTimeLogin;
import snow.app.ideelee.extrafiles.SessionManager;
import snow.app.ideelee.responses.loginres.LoginRes;
import snow.app.ideelee.responses.logoutres.LogoutRes;
import snow.app.ideelee.search.SearchActivity;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class HomeNavigation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    GridView androidGridView;
    MainCategory adapterViewAndroid;
    ViewPager viewpager;
    PagerAdapter adapter;
    private ViewPager vp_slider;
    private LinearLayout ll_dots;
    ViewPagerHome sliderPagerAdapter;
    ArrayList<String> slider_image_list;
    private TextView[] dots;
    int page_position = 0;
    ViewPager viewPager;
    LinearLayout sliderDotspanel;
    private int dotscount;


    @BindView(R.id.profile)
    TextView profile;
    @BindView(R.id.wallet)
    TextView wallet;

    @BindView(R.id.home)
    TextView home;


    @BindView(R.id.bookings)
    TextView booking;

    @BindView(R.id.ed_search)
    EditText ed_search;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
ImageView img;

TextView h_name;
    ApiService apiService;
    String device_token,userid,name,token;
    HashMap<String, String> map;
    OneTimeLogin oneTimeLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_navigation);

        setSupportActionBar(toolbar);
        ButterKnife.bind(this);



        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);

        oneTimeLogin =new OneTimeLogin(HomeNavigation.this);
        apiService = ApiClient.getClient(getApplicationContext())
                .create(ApiService.class);
        toggle.syncState();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);


         SessionManager sessionManager= new SessionManager(this);
           userid =sessionManager.getKeyId();
          token = sessionManager.getKeyToken();
          name = sessionManager.getKeyName();


        //set user details in nav header
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.hname);
        navUsername.setText(name);






        ed_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
startActivity(new Intent(HomeNavigation.this, SearchActivity.class));
            }
        });



        Fragment fragment = null;
        if (getIntent().hasExtra("key")) {
            if (getIntent().getStringExtra("key").equals("wallet")) {
                fragment = new WalletFragment();
            } else {
                fragment = new HomeFragment();
            }
        } else {
            fragment = new HomeFragment();
        }
        View view = navigationView.getHeaderView(0);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new ProfileFragment(), "Edit Profile");
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
            }
        });
        img = (ImageView) view.findViewById(R.id.img);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment, "Home");
        fragmentTransaction.commit();


        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().addToBackStack(null)
                        .replace(R.id.content_frame, new ProfileFragment(), "Profile");
                fragmentTransaction.commit();

            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().addToBackStack(null)
                        .replace(R.id.content_frame, new HomeFragment(), "Home");
                fragmentTransaction.commit();
            }
        });

       /*

        edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.closeDrawer(GravityCompat.START);
                profile.performClick();
            }
        });

        invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.closeDrawer(GravityCompat.START);

            }
        });

        orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.closeDrawer(GravityCompat.START);
                 startActivity(new Intent(HomeNavigation.this, MyOrders.class));
            }
        });
      */

        booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().addToBackStack(null)
                        .replace(R.id.content_frame, new CurrentBookingFragment(), "CurrentBookingFragment");
                fragmentTransaction.commit();
            }
        });
        wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().addToBackStack(null)
                        .replace(R.id.content_frame, new WalletFragment(), "WalletFragment");
                fragmentTransaction.commit();
            }
        });

        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        Picasso.with(this)
                .load("https://pbs.twimg.com/profile_images/572905100960485376/GK09QnNG.jpeg")
                .resize(width / 4, width / 4)
                .transform(new CircleTransform())
                .centerCrop()
                .into(img);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            if (getFragmentManager().getBackStackEntryCount() > 0) {
                getFragmentManager().popBackStack();
            } else {
                super.onBackPressed();
            }


        }
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.edit_profile) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().addToBackStack(null)
                    .replace(R.id.content_frame, new ProfileFragment(), "Profile");
            fragmentTransaction.commit();

        } else if (id == R.id.invite) {
            startActivity(new Intent(HomeNavigation.this, InviteActivity.class));
        } else if (id == R.id.manage_address) {
            startActivity(new Intent(HomeNavigation.this, AddAddress.class));

        } else if (id == R.id.help_support) {
            startActivity(new Intent(HomeNavigation.this, HelpActivity.class));
        } else if (id == R.id.orders) {
            startActivity(new Intent(HomeNavigation.this, MyOrders.class));
        } else if (id == R.id.logout) {

            handleLogout();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setFragment(Fragment fragment, String title) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment, title);
        fragmentTransaction.commit();
    }



    public void logoutUser(HashMap<String, String> map) {
        // showProgress();

        Observer<LogoutRes> observer = apiService.logoutUser(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<LogoutRes>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LogoutRes res) {


                            oneTimeLogin.setFirstTimeLaunch(true);

                            Intent intent_continue = new Intent(HomeNavigation.this, Login.class);
                            startActivity(intent_continue);
                            finishAffinity();

                    }
                    @Override
                    public void onError(Throwable e) {
                        Intent intent_continue = new Intent(HomeNavigation.this, Login.class);
                        startActivity(intent_continue);
                        finish();

                    }

                    @Override
                    public void onComplete() {


                    }
                });


    }


    private void handleLogout() {


//        if (userid != null) {
//
//        }


            map = new HashMap<>();
            map.put("userid",userid);
            map.put("token", token);


            logoutUser(map);
        }




}


