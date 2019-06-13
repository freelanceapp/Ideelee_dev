package snow.app.ideelee.perday_fixedpricemodule.adapters;

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

import butterknife.BindView;
import butterknife.ButterKnife;
import me.gujun.android.taggroup.TagGroup;
import snow.app.ideelee.HomeScreen.Modals.ServiceProviderList;
import snow.app.ideelee.R;
import snow.app.ideelee.bookings.BookingPerDay;
import snow.app.ideelee.fixedpricemodule.VehicleWashBookingAppointment;

import snow.app.ideelee.perday_fixedpricemodule.RentalBookingPerDay;
import snow.app.ideelee.vehical_module.vehicle.dialog.TagItemAdapter;

public class RentalSubCatAdapter extends RecyclerView.Adapter<RentalSubCatAdapter.ProductViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<ServiceProviderList> productList;

    //getting the context and product list with constructor
    public RentalSubCatAdapter(Context mCtx, List<ServiceProviderList> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public RentalSubCatAdapter.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        final View view = inflater.inflate(R.layout.rental_subcat_row, null);


        view.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(mCtx, BookingAppointment.class);


            }
        });
        return new RentalSubCatAdapter.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RentalSubCatAdapter.ProductViewHolder holder, int position) {
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
        @BindView
                (R.id.tag_group)
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
            ButterKnife.bind(this, itemView);
            mTagGroup.setTags(new String[]{"Plumber", "Electrician", "Carpenter",});
            mTagGroup.submitTag();
            itemView.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onClick(View v) {
                    initiatePopupwindowperday(v);

                }
            });
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void initiatePopupwindowperday(View v) {

        LayoutInflater inflater = (LayoutInflater) mCtx.getSystemService(mCtx.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.serviceproviderdialog_perhour, (ViewGroup) v.findViewById(R.id.linearlayout));
        final PopupWindow pw = new PopupWindow(layout, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        pw.showAtLocation(v, Gravity.CENTER, 0, 0);

        View container = (View) pw.getContentView().getRootView();
        WindowManager wm = (WindowManager) mCtx.getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams p = (WindowManager.LayoutParams) container.getLayoutParams();
        p.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        p.dimAmount = 0.6f;
        wm.updateViewLayout(container, p);
        RecyclerView recyclerView = (RecyclerView) layout.findViewById(R.id.rv_tagitem);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(mCtx, LinearLayoutManager.HORIZONTAL, false));
        ArrayList<String> list_tag = new ArrayList<>();
        list_tag.add("01 May");
        list_tag.add("02 May");
        list_tag.add("03 May");
        list_tag.add("04 May");
        list_tag.add("05 May");
        list_tag.add("06 May");
        list_tag.add("07 May");
        TagItemAdapter adapter = new TagItemAdapter(mCtx, list_tag);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);
        Button btn_continue_loginPage = layout.findViewById(R.id.ux_btn);
        btn_continue_loginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(mCtx, RentalBookingPerDay.class);

                Toast.makeText(mCtx, "click", Toast.LENGTH_SHORT).show();
                mCtx.startActivity(intent);

            }
        });
        TextView txt = layout.findViewById(R.id.book_txt);
        txt.setText(Html.fromHtml("<strong><span style=\"color: #ff9900;\">Limousine :-</span> <span style=\"color: #000000;\">$15.00 Per Day</span></strong>", Html.FROM_HTML_MODE_COMPACT));
        pw.setOutsideTouchable(true);
        pw.setFocusable(true);
        pw.showAsDropDown(v, 0, 0);
    }


}