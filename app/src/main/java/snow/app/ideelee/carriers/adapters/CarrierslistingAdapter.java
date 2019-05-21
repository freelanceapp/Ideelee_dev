package snow.app.ideelee.carriers.adapters;

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
import snow.app.ideelee.camping.CampingBookingPerDay;
import snow.app.ideelee.carriers.CarrierBookingPerDay;
import snow.app.ideelee.metre_square_module.HandymanBookingPerDay;
import snow.app.ideelee.vehical_module.vehicle.dialog.TagItemAdapter;

public class CarrierslistingAdapter extends RecyclerView.Adapter<CarrierslistingAdapter.ProductViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<ServiceProviderList> productList;

    //getting the context and product list with constructor
    public CarrierslistingAdapter(Context mCtx, List<ServiceProviderList> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public CarrierslistingAdapter.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        final View view = inflater.inflate(R.layout.perkm_perperson_row, null);


        view.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(mCtx, BookingAppointment.class);


            }
        });
        return new CarrierslistingAdapter.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CarrierslistingAdapter.ProductViewHolder holder, int position) {
        //getting the product of the specified position
        ServiceProviderList product = productList.get(position);

        //binding the data with the viewholder views
        holder.textViewTitle.setText(product.getName());
        holder.ratingBar.setRating((float) product.getRating());
        holder.distance.setText(product.getDistance());
        holder.imageView.setImageDrawable(ContextCompat.getDrawable(mCtx, product.getImage()));

if (position==1){
    holder.txt_person.setText("Per Person");
}else {
    holder.txt_person.setText("Per KM");
}
    }


    @Override
    public int getItemCount() {
        return productList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {
        TagGroup mTagGroup;
        TextView textViewTitle, distance,txt_person;
        ImageView imageView;
        RatingBar ratingBar;

        public ProductViewHolder(View itemView) {
            super(itemView);
            ratingBar = itemView.findViewById(R.id.ratingbar);
            distance = itemView.findViewById(R.id.distance);
txt_person=itemView.findViewById(R.id.perhour);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            mTagGroup = (TagGroup) itemView.findViewById(R.id.tag_group);
            mTagGroup.setTags(new String[]{"Plumber", "Electrician", "Carpenter",});
            mTagGroup.submitTag();
            imageView = itemView.findViewById(R.id.ux_img_user);

            itemView.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onClick(View v) {
                    initiatePopupwindowperday(v,txt_person);

                }
            });
        }
    }



    @RequiresApi(api = Build.VERSION_CODES.N)
    public void initiatePopupwindowperday(View v,TextView txt_person) {

        LayoutInflater inflater = (LayoutInflater) mCtx.getSystemService(mCtx.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.perkmperperson_dialog, (ViewGroup) v.findViewById(R.id.linearlayout));
        final PopupWindow pw = new PopupWindow(layout, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        pw.showAtLocation(v, Gravity.CENTER, 0, 0);

        View container = (View) pw.getContentView().getRootView();
        WindowManager wm = (WindowManager) mCtx.getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams p = (WindowManager.LayoutParams) container.getLayoutParams();
        p.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        p.dimAmount = 0.6f;
        wm.updateViewLayout(container, p);
        RecyclerView  recyclerView = (RecyclerView) layout.findViewById(R.id.rv_tagitem);
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


                Intent intent = new Intent(mCtx, CampingBookingPerDay.class);
                Toast.makeText(mCtx, "click", Toast.LENGTH_SHORT).show();
                mCtx.startActivity(intent);

            }
        });
        TextView txt = layout.findViewById(R.id.book_txt);
        if (txt_person.getText().toString().equals("Per KM")){
            txt.setText("$ 15.00 Per KM");
        }else{
            txt.setText("$ 15.00 Per Person");
        }

       // txt.setText(Html.fromHtml("<strong><span style=\"color: #ff9900;\">Limousine :-</span> <span style=\"color: #000000;\">$15.00 Per Sq m</span></strong>", Html.FROM_HTML_MODE_COMPACT));
        pw.setOutsideTouchable(true);
        pw.setFocusable(true);
        pw.showAsDropDown(v, 0, 0);
    }



}