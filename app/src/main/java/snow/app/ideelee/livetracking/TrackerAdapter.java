package snow.app.ideelee.livetracking;

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

import snow.app.ideelee.R;
import snow.app.ideelee.fooddelivery.restdetails.RestDetailsActivity;

public class TrackerAdapter extends RecyclerView.Adapter<TrackerAdapter.MyViewHolder> {

    Context context;
    int statuss;
    private List<TrackerM> dataList;

    public TrackerAdapter(List<TrackerM> dataList, Context context, int statuss) {
        this.dataList = dataList;
        this.context = context;
        this.statuss = 2;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tracker_row, parent, false);

          return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {



        // status= 1 order accepted
        // status= 2 order preparing
        // status= 3 order out for delivery
        // status= 4 order Delivered
        if (statuss==1){
            if (position!=0){
                holder.top.setBackgroundColor(ContextCompat.getColor(context,R.color.gray));
                holder.btm.setBackgroundColor(ContextCompat.getColor(context,R.color.gray));
                holder.img.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.gray_track));
            }
        }else if (statuss==2){
            if (position!=0&&position!=1){
                holder.top.setBackgroundColor(ContextCompat.getColor(context,R.color.gray));
                holder.btm.setBackgroundColor(ContextCompat.getColor(context,R.color.gray));
                holder.img.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.gray_track));
            }
            if (position==1){
                holder.img.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.orange_track));
            }
        }else if (statuss==3){
            if (position!=0&&position!=1&position!=2){
                holder.top.setBackgroundColor(ContextCompat.getColor(context,R.color.gray));
                holder.btm.setBackgroundColor(ContextCompat.getColor(context,R.color.gray));
                holder.img.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.gray_track));
            }
            if (position==2){
                holder.img.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.orange_track));
            }
        }else if (statuss==4){
            if (position!=0&&position!=1&&position!=2&&position!=3){
                holder.top.setBackgroundColor(ContextCompat.getColor(context,R.color.gray));
                holder.btm.setBackgroundColor(ContextCompat.getColor(context,R.color.gray));
                holder.img.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.gray_track));
            }
            if (position==2){
                holder.img.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.green_track));
            }
        }

        if (position == getItemCount() - 1) {
            holder.btm.setVisibility(View.INVISIBLE);
        }
        if (position ==0) {
            holder.top.setVisibility(View.INVISIBLE);
        }


        TrackerM m=dataList.get(position);
        if (m.getOrderStatus().equals("1")){
            holder.status.setText("Order Accepted");

        }else if(m.getOrderStatus().equals("2")){
            holder.status.setText("Order Preparing");

        }else if(m.getOrderStatus().equals("3")){
            holder.status.setText("Order out for delivery");

        }else if(m.getOrderStatus().equals("4")){
            holder.status.setText("Order Delivered");

        }

        holder.msg.setText(m.getTime()+" Lorem ipsum dolor sit amet");

    }

    @Override
    public int getItemCount() {
        return /*dataList.size()*/4;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView status;
        TextView msg;
        ImageView img;
        View top,btm;

        public MyViewHolder(View view) {
            super(view);

            status = (TextView) itemView.findViewById(R.id.status);
            msg = (TextView) itemView.findViewById(R.id.msg);
            img = (ImageView) itemView.findViewById(R.id.img);
            top = (View) itemView.findViewById(R.id.top);
            btm = (View) itemView.findViewById(R.id.btm);


        }
    }

}