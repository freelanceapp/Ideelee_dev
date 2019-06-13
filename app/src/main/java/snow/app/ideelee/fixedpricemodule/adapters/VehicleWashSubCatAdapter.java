package snow.app.ideelee.fixedpricemodule.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.gujun.android.taggroup.TagGroup;
import snow.app.ideelee.BookingAppointment;
import snow.app.ideelee.HomeScreen.Modals.ServiceProviderList;
import snow.app.ideelee.R;
import snow.app.ideelee.fixedpricemodule.VehicleWashBookingAppointment;

public class VehicleWashSubCatAdapter extends RecyclerView.Adapter<VehicleWashSubCatAdapter.ProductViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<ServiceProviderList> productList;

    //getting the context and product list with constructor
    public VehicleWashSubCatAdapter(Context mCtx, List<ServiceProviderList> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public VehicleWashSubCatAdapter.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        final View view = inflater.inflate(R.layout.vehiclewashsubcat_row, null);


        view.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(mCtx, BookingAppointment.class);


            }
        });
        return new VehicleWashSubCatAdapter.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VehicleWashSubCatAdapter.ProductViewHolder holder, int position) {
        //getting the product of the specified position
        ServiceProviderList product = productList.get(position);

        //binding the data with the viewholder views
        holder.textViewTitle.setText(product.getName());
        holder.ratingBar.setRating((float) product.getRating());
        holder.distance.setText(product.getDistance());
        holder.imageView.setImageDrawable(ContextCompat.getDrawable(mCtx, product.getImage()));


    }


    @Override
    public int getItemCount() {
        return productList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tag_group)
        TagGroup mTagGroup;
        @BindView(R.id.textViewTitle)
        TextView textViewTitle;
        @BindView(R.id.distance)
        TextView distance;
        @BindView(R.id.ux_img_user)
        ImageView imageView;
        @BindView(R.id.ratingbar)
        RatingBar ratingBar;

        public ProductViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            mTagGroup.setTags(new String[]{"Plumber", "Electrician", "Carpenter",});
            mTagGroup.submitTag();


            itemView.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onClick(View v) {
                    perHourPopup(v);

                }
            });
        }
    }


    public void perHourPopup(View v) {

        LayoutInflater inflater = (LayoutInflater) mCtx.getSystemService(mCtx.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.vehiclewashdialog, (ViewGroup) v.findViewById(R.id.linearlayout));
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
                Intent intent = new Intent(mCtx, VehicleWashBookingAppointment.class);
                Toast.makeText(mCtx, "click", Toast.LENGTH_SHORT).show();
                mCtx.startActivity(intent);

            }
        });

        pw.setOutsideTouchable(true);
        pw.setFocusable(true);
        pw.showAsDropDown(v, 0, 0);
    }


}