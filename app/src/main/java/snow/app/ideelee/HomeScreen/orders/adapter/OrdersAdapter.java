package snow.app.ideelee.HomeScreen.orders.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import snow.app.ideelee.AppUtils.CircleTransform;
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
                .resize(width / 4, width / 4)
                .transform(new CircleTransform())
                .centerCrop()
                .into(holder.img);

    }
        @Override
        public int getItemCount () {
            return dataList.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            TextView status;
            TextView res_name;
            TextView summery;
            TextView time;
            TextView price;
            ImageView img;

            public MyViewHolder(View view) {
                super(view);
                status = (TextView) view.findViewById(R.id.status);
                res_name = (TextView) view.findViewById(R.id.res_name);
                summery = (TextView) view.findViewById(R.id.summery);
                time = (TextView) view.findViewById(R.id.time);
                price = (TextView) view.findViewById(R.id.price);
                img = (ImageView) view.findViewById(R.id.img);

            }
        }
    }