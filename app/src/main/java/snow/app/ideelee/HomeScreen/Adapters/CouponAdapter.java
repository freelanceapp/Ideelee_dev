package snow.app.ideelee.HomeScreen.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import snow.app.ideelee.CouponDetails;
import snow.app.ideelee.HomeScreen.Modals.CompletedJobModal;
import snow.app.ideelee.R;
import snow.app.ideelee.responses.getcouponsres.Coupondatum;

public class CouponAdapter extends RecyclerView.Adapter<CouponAdapter.ProductViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    List<Coupondatum> serviceproviderlist;

    //getting the context and product list with constructor
    public CouponAdapter(Context mCtx, List<Coupondatum> serviceproviderlist) {
        this.mCtx = mCtx;
        this.serviceproviderlist = serviceproviderlist;
    }

    @Override
    public CouponAdapter.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.coupon_row, parent, false);

    return new CouponAdapter.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CouponAdapter.ProductViewHolder holder, int position) {
        //getting the product of the specified position
          final Coupondatum product = serviceproviderlist.get(position);
        Picasso.with(mCtx.getApplicationContext())
                .load(product.getBanner())
                // optional
                // optional
                .into(holder.imageView);
        //binding the data with the viewholder views
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mCtx, CouponDetails.class);
                intent.putExtra("couponid",product.getId());
                mCtx.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return serviceproviderlist.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView name, status, address, payment, servicetype, edit, txt_ratenow;
        RatingBar ratingBar;
       @BindView
      (R.id.img_coupon) ImageView imageView;

        public ProductViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}