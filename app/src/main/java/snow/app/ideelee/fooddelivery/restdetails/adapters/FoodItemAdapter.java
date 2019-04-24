package snow.app.ideelee.fooddelivery.restdetails.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import snow.app.ideelee.HomeScreen.orders.OrderDetails.OrderDetailActivity;
import snow.app.ideelee.HomeScreen.orders.adapter.OrdersM;
import snow.app.ideelee.R;
import snow.app.ideelee.fooddelivery.restdetails.RestDetailsActivity;

public class FoodItemAdapter extends RecyclerView.Adapter<FoodItemAdapter.MyViewHolder> {

    Context context;
    private List<String> dataList;
    int  width;

    public FoodItemAdapter(List<String> dataList, Context context, int width) {
        this.dataList = dataList;
        this.context = context;
        this.width = width;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.food_item_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


    }
        @Override
        public int getItemCount () {
            return /*dataList.size()*/5;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {


        LinearLayout parent;
            public MyViewHolder(View view) {
                super(view);
               /* parent=(LinearLayout)view.findViewById(R.id.parent);
                parent.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        context.startActivity(new Intent(context, RestDetailsActivity.class));
                    }
                });
*/
            }
        }
    }