package snow.app.ideelee.perperson_permealmodule.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import me.gujun.android.taggroup.TagGroup;
import snow.app.ideelee.HomeScreen.Modals.ServiceProviderList;
import snow.app.ideelee.R;
import snow.app.ideelee.perday_fixedpricemodule.RentalBookingPerDay;
import snow.app.ideelee.perhour_fixpricemodule.TeachingBookingAppointment;
import snow.app.ideelee.perperson_permealmodule.BookingRequirements;
import snow.app.ideelee.perperson_permealmodule.EventBookingAppointment;
import snow.app.ideelee.vehical_module.vehicle.dialog.TagItemAdapter;

public class EventsSubCatAdapter extends RecyclerView.Adapter<EventsSubCatAdapter.ProductViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<ServiceProviderList> productList;

    //getting the context and product list with constructor
    public EventsSubCatAdapter(Context mCtx, List<ServiceProviderList> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public EventsSubCatAdapter.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        final View view = inflater.inflate(R.layout.event_subcat_row, null);


        view.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(mCtx, BookingAppointment.class);


            }
        });
        return new EventsSubCatAdapter.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EventsSubCatAdapter.ProductViewHolder holder, int position) {
        //getting the product of the specified position
        ServiceProviderList product = productList.get(position);

        //binding the data with the viewholder views
        holder.textViewTitle.setText(product.getName());
        holder.ratingBar.setRating((float) product.getRating());
        holder.distance.setText(product.getDistance());
        holder.imageView.setImageDrawable(ContextCompat.getDrawable(mCtx, product.getImage()));

if (position==0){
    holder.mTagGroup.setTags(new String[]{"Cook"});
    holder.mTagGroup.submitTag();
    holder.perperson.setText("Per Person");
} else if (position==1){
    holder.mTagGroup.setTags(new String[]{"Chef"});
    holder.mTagGroup.submitTag();
    holder.perperson.setText("Per Person");
}else {
    holder.mTagGroup.setTags(new String[]{"Cook"});
    holder.mTagGroup.submitTag();
    holder.perperson.setText("Per Meal");
}



    }


    @Override
    public int getItemCount() {
        return productList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {
        TagGroup mTagGroup;
        TextView textViewTitle, distance,perperson;
        ImageView imageView;
        RatingBar ratingBar;

        public ProductViewHolder(View itemView) {
            super(itemView);
            ratingBar = itemView.findViewById(R.id.ratingbar);
            distance = itemView.findViewById(R.id.distance);
perperson=itemView.findViewById(R.id.perhour);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            mTagGroup = (TagGroup) itemView.findViewById(R.id.tag_group);
            mTagGroup.setTags(new String[]{"Cook"});
            mTagGroup.submitTag();
            imageView = itemView.findViewById(R.id.ux_img_user);

            itemView.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onClick(View v) {
                    if (perperson.getText().toString().equals("Per Meal")){
                    perMealPopup(v);

                }else{
                    perPersonPopup(v);
                }}
            });
        }
    }



    public void perPersonPopup(View v) {

        LayoutInflater inflater = (LayoutInflater) mCtx.getSystemService(mCtx.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.eventsubcatdialog, (ViewGroup) v.findViewById(R.id.linearlayout));
        final PopupWindow pw = new PopupWindow(layout, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        pw.showAtLocation(v, Gravity.CENTER, 0, 0);
        View container = (View) pw.getContentView().getRootView();
        WindowManager wm = (WindowManager) mCtx.getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams p = (WindowManager.LayoutParams) container.getLayoutParams();
        p.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        p.dimAmount = 0.6f;
        wm.updateViewLayout(container, p);
        Button btn_continue_loginPage = layout.findViewById(R.id.ux_btn);
        Button lay_bg = layout.findViewById(R.id.lay_bg);
        TagGroup mTagGroup = (TagGroup) layout.findViewById(R.id.tag);
        mTagGroup.setTags(new String[]{"08-10 AM", "08-10 AM", "08-10 AM"});
        mTagGroup.submitTag();
        btn_continue_loginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mCtx, EventBookingAppointment.class);
                Toast.makeText(mCtx, "click", Toast.LENGTH_SHORT).show();
                mCtx.startActivity(intent);

            }
        });

        pw.setOutsideTouchable(true);
        pw.setFocusable(true);
        pw.showAsDropDown(v, 0, 0);
    }

    public void perMealPopup(View v) {

        LayoutInflater inflater = (LayoutInflater) mCtx.getSystemService(mCtx.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.event_permealdialog, (ViewGroup) v.findViewById(R.id.linearlayout));
        final PopupWindow pw = new PopupWindow(layout, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        pw.showAtLocation(v, Gravity.CENTER, 0, 0);
        View container = (View) pw.getContentView().getRootView();
        WindowManager wm = (WindowManager) mCtx.getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams p = (WindowManager.LayoutParams) container.getLayoutParams();
        p.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        p.dimAmount = 0.6f;
        wm.updateViewLayout(container, p);
        Button btn_continue_loginPage = layout.findViewById(R.id.ux_btn);
        Button lay_bg = layout.findViewById(R.id.lay_bg);
        TagGroup mTagGroup = (TagGroup) layout.findViewById(R.id.tag);
        mTagGroup.setTags(new String[]{"08-10 AM", "08-10 AM", "08-10 AM"});
        mTagGroup.submitTag();
        btn_continue_loginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mCtx, EventBookingAppointment.class);
                Toast.makeText(mCtx, "click", Toast.LENGTH_SHORT).show();
                mCtx.startActivity(intent);

            }
        });

        pw.setOutsideTouchable(true);
        pw.setFocusable(true);
        pw.showAsDropDown(v, 0, 0);
    }


}