package snow.app.ideelee.fooddelivery.adapter;

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

public class RestaurantsAdapter extends RecyclerView.Adapter<RestaurantsAdapter.MyViewHolder> {

    Context context;
    private List<String> dataList;
    int  width;

    public RestaurantsAdapter(List<String> dataList, Context context, int width) {
        this.dataList = dataList;
        this.context = context;
        this.width = width;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.food_del_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

 

    }
        @Override
        public int getItemCount () {
            return /*dataList.size()*/10;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
          
            public MyViewHolder(View view) {
                super(view);
                img=(ImageView)view.findViewById(R.id.img);
                Picasso.with(context)
                        .load("https://stmedia.stimg.co/KING6.JPG")
                        .resize(width / 5, width / 5)
                        .centerCrop()
                        .into(img);

            }
        }
    }