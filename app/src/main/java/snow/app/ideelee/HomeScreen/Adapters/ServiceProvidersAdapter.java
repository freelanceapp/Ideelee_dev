package snow.app.ideelee.HomeScreen.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
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

import me.gujun.android.taggroup.TagGroup;
import snow.app.ideelee.BookingAppointment;
import snow.app.ideelee.HomeScreen.Modals.ServiceProvider;
import snow.app.ideelee.HomeScreen.Modals.ServiceProviderList;
import snow.app.ideelee.R;

public class ServiceProvidersAdapter extends RecyclerView.Adapter<ServiceProvidersAdapter.ProductViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<ServiceProviderList> productList;

    //getting the context and product list with constructor
    public ServiceProvidersAdapter(Context mCtx, List<ServiceProviderList> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ServiceProvidersAdapter.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.service_providers_row, null);


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(mCtx, BookingAppointment.class);
           initiatePopupwindow(v);
            }
        });
        return new ServiceProvidersAdapter.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ServiceProvidersAdapter.ProductViewHolder holder, int position) {
        //getting the product of the specified position
        ServiceProviderList product = productList.get(position);

        //binding the data with the viewholder views
        holder.textViewTitle.setText(product.getName());
        holder.ratingBar.setRating((float) product.getRating());
        holder.distance.setText(product.getDistance());
        holder.imageView.setImageDrawable(ContextCompat.getDrawable(mCtx,product.getImage()));

    }


    @Override
    public int getItemCount() {
        return productList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {
TagGroup mTagGroup;
        TextView textViewTitle, distance;
        ImageView imageView;
        RatingBar ratingBar;

        public ProductViewHolder(View itemView) {
            super(itemView);
            ratingBar = itemView.findViewById(R.id.ratingbar);
            distance=itemView.findViewById(R.id.distance);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            mTagGroup = (TagGroup) itemView.findViewById(R.id.tag_group);
            mTagGroup.setTags(new String[]{"Plumber", "Tag2", "Tag3","tag4","ytag","ytag","ytag","ytag","ytag","ytag","ytag","ytag","ytag",});
            mTagGroup.submitTag();
            imageView = itemView.findViewById(R.id.ux_img_user);
        }
    }




    public void initiatePopupwindow(View v){

            LayoutInflater inflater=(LayoutInflater)mCtx.getSystemService(mCtx.LAYOUT_INFLATER_SERVICE);
            View layout=inflater.inflate(R.layout.serviceproviderdialog,(ViewGroup) v.findViewById(R.id.linearlayout));
            final PopupWindow pw=new PopupWindow(layout,ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT,true);
            pw.showAtLocation(v, Gravity.CENTER,0,0);
            View container= (View) pw.getContentView().getRootView();
            WindowManager wm=(WindowManager)mCtx.getSystemService(Context.WINDOW_SERVICE);
            WindowManager.LayoutParams p=(WindowManager.LayoutParams)container.getLayoutParams();
            p.flags=WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            p.dimAmount=0.6f;
            wm.updateViewLayout(container,p);
           Button btn_continue_loginPage= layout.findViewById(R.id.ux_btn);
      TagGroup  mTagGroup = (TagGroup) layout.findViewById(R.id.tag);
        mTagGroup.setTags(new String[]{"08-10 AM", "08-10 AM", "08-10 AM"});
        mTagGroup.submitTag();
            btn_continue_loginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(mCtx,BookingAppointment.class);
                Toast.makeText(mCtx, "click", Toast.LENGTH_SHORT).show();
                mCtx.startActivity(intent);
            }
        });

    }
}