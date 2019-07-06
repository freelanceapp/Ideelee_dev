package snow.app.ideelee.fooddelivery.restaurantsmod.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.gujun.android.taggroup.TagGroup;
import snow.app.ideelee.R;
import snow.app.ideelee.fooddelivery.restdetails.RestDetailsActivity;
import snow.app.ideelee.responses.deliverysubcatres.StoresDetail;

public class RestaurantsAdapter extends RecyclerView.Adapter<RestaurantsAdapter.MyViewHolder> {

    Context context;
    int width;
    List<StoresDetail> couponcategorydatumList;

    public RestaurantsAdapter(List<StoresDetail> couponcategorydatumList, Context context, int width) {
        this.couponcategorydatumList = couponcategorydatumList;
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
        StoresDetail storesDetail = couponcategorydatumList.get(position);
        Picasso.with(context)
                .load(storesDetail.getStoreImage())
                .resize(width / 5, width / 5)
                .centerCrop()
                .into(holder.img);
        holder.name.setText(storesDetail.getStoreName());
        holder.rating.setRating(Float.parseFloat(storesDetail.getRating()));
        String currentString = storesDetail.getCategoryName();

        holder.minordervalue.setText("$" + storesDetail.getMinOrder() + " min order");

        holder.mTagGroup.setTags(Arrays.asList(currentString.split("\\s*,\\s*")));
        holder.mTagGroup.submitTag();



        String opentime=storesDetail.getOpeningTime();
        String closetime=storesDetail.getClosingTime();






        Calendar c = Calendar.getInstance();
        System.out.println("Current time => "+c.getTime());

        SimpleDateFormat df = new SimpleDateFormat(" HH:mm:ss");
        String formattedDate = df.format(c.getTime());

// textView is the TextView view that should display it

        DateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Date date = null;
        Date date1 = null;
        Date date2 = null;
        try {
            date = sdf.parse(opentime);
            date1 = sdf.parse(closetime);
            date2 = sdf.parse(formattedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println("Time: " + sdf.format(date));
        System.out.println("Time1: " + sdf.format(date1));



       System.out.println("current time--"+formattedDate);

         if (date2.after(date1)){

            holder.status.setText("Closed");
            holder.status.setTextColor(ContextCompat.getColor(context,R.color.red));
            holder.status.setCompoundDrawables(ContextCompat.getDrawable(context,R.drawable.ic_brightness_red_24dp),null,null,null);

        }else if (date2.before(date)){
            holder.status.setText("Closed");
            holder.status.setTextColor(ContextCompat.getColor(context,R.color.red));
            holder.status.setCompoundDrawables(ContextCompat.getDrawable(context,R.drawable.ic_brightness_red_24dp),null,null,null);

        }else {
            holder.status.setText("Open Now");
            holder.status.setTextColor(ContextCompat.getColor(context,R.color.green));
            holder.status.setCompoundDrawables(ContextCompat.getDrawable(context,R.drawable.ic_brightness_1_black_24dp),null,null,null);
        }
    }

    @Override
    public int getItemCount() {
        return couponcategorydatumList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView
                (R.id.img)
        ImageView img;
        @BindView(R.id.parent)
        LinearLayout parent;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.minordervalue)
        TextView minordervalue;
        @BindView(R.id.status)
        TextView status;

        @BindView(R.id.rating)
        RatingBar rating;
        @BindView
                (R.id.tag_group)
        TagGroup mTagGroup;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, itemView);

            parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, RestDetailsActivity.class));
                }
            });

        }
    }

}