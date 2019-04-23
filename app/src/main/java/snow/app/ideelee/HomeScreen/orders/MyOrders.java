package snow.app.ideelee.HomeScreen.orders;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import snow.app.ideelee.HomeScreen.orders.adapter.OrdersAdapter;
import snow.app.ideelee.HomeScreen.orders.adapter.OrdersM;
import snow.app.ideelee.R;

public class MyOrders extends Activity {
    RecyclerView rv_orders;
    OrdersAdapter ordersAdapter;
    TextView title;
    ImageView backbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);
        rv_orders = (RecyclerView) findViewById(R.id.rv_orders);
        rv_orders.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<OrdersM> data = new ArrayList<>();
        data.add(new OrdersM("","Burger King","1 x Burger, 2 x Fries","21 Apr, 2019 11:40","#110","1","1"));
        data.add(new OrdersM("","Burger King","1 x Burger, 2 x Fries","21 Apr, 2019 11:40","#110","1","1"));
        data.add(new OrdersM("","Burger King","1 x Burger, 2 x Fries","21 Apr, 2019 11:40","#110","2","2"));
        data.add(new OrdersM("","Burger King","1 x Burger, 2 x Fries","21 Apr, 2019 11:40","#110","2","2"));
        data.add(new OrdersM("","Burger King","1 x Burger, 2 x Fries","21 Apr, 2019 11:40","#110","2","2"));
        data.add(new OrdersM("","Burger King","1 x Burger, 2 x Fries","21 Apr, 2019 11:40","#110","3","3"));
        data.add(new OrdersM("","Burger King","1 x Burger, 2 x Fries","21 Apr, 2019 11:40","#110","3","3"));
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        ordersAdapter = new OrdersAdapter(data, this,width);
        rv_orders.setAdapter(ordersAdapter);
        title = (TextView) findViewById(R.id.title_bookingappointement);
        backbutton = (ImageView) findViewById(R.id.backbutton1);
        title.setText("My Orders");
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }
}
