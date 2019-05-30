package snow.app.ideelee.HomeScreen.orders.adapter;

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

import butterknife.BindView;
import butterknife.ButterKnife;
import snow.app.ideelee.AppUtils.CircleTransform;
import snow.app.ideelee.HomeScreen.orders.OrderDetails.OrderDetailActivity;
import snow.app.ideelee.R;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.MyViewHolder> {

    Context context;
    private List<OrdersM> dataList;
    int  width;

    public OrdersAdapter(List<OrdersM> dataList, Context context,int width) {
        this.dataList = dataList;
        this.context = context;
        this.width = width;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.orders_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final OrdersM data = dataList.get(position);

        if (data.getStatus().equals("1")) {
            holder.status.setText("Live Tracking");
            holder.status.setTextColor(ContextCompat.getColor(context,R.color.orange));
        } else if (data.getStatus().equals("2")) {
            holder.status.setText("Repeat Order");
            holder.status.setTextColor(ContextCompat.getColor(context,R.color.green));
        } else if (data.getStatus().equals("3")) {
            holder.status.setText("Order Canceled");
            holder.status.setTextColor(ContextCompat.getColor(context,R.color.red));
        }
        Picasso.with(context)
                .load("https://stmedia.stimg.co/KING6.JPG")
                .resize(width / 6, width / 6)
/*
                .transform(new CircleTransform())
*/
                .centerCrop()
                .into(holder.img);

                holder.parent.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        context.startActivity(new Intent(context, OrderDetailActivity.class));
                    }
                });

    }
        @Override
        public int getItemCount () {
            return dataList.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

           @BindView
          (R.id.status) TextView status;
           @BindView(R.id.res_name) TextView res_name;
           @BindView(R.id.summery) TextView summery;
          @BindView(R.id.time)  TextView time;
           @BindView(R.id.price) TextView price;
           @BindView(R.id.img) ImageView img;
           @BindView(R.id.parent) LinearLayout parent;

            public MyViewHolder(View view) {
                super(view);
                ButterKnife.bind(this,itemView);


            }
        }
    }