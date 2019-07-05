package snow.app.ideelee.HomeScreen.Adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import snow.app.ideelee.HomeScreen.Modals.ServiceProvider;
import snow.app.ideelee.R;
import snow.app.ideelee.responses.homescreenres.PopularProfile;

public class ServiceProviderCategoryAdapter extends RecyclerView.Adapter<ServiceProviderCategoryAdapter.ProductViewHolder> {

    String   uri;
    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<PopularProfile> productList;
    int width=0;

    //getting the context and product list with constructor
    public ServiceProviderCategoryAdapter(Context mCtx, List<PopularProfile> productList,int width) {
        this.mCtx = mCtx;
        this.productList = productList;
        this.width = width;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.service_providers_categories_layout, null);
//        if (view == null) {
//            view = inflater.inflate(R.layout.bottom_sheet_layout, null);
//        }
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        //getting the product of the specified position
        PopularProfile product = productList.get(position);



        String name=product.getName();
        Log.e("name---",name);
        Log.e("logo---",product.getProfileImage());
        //binding the data with the viewholder views
        holder.textViewTitle.setText(product.getName());
       holder.ratingBar.setRating(  product.getRating());
//        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(product.getImage()));




         uri = productList.get(position).getProfileImage();
        if (uri.equals("")) {


            Picasso.with(mCtx).load(R.drawable.profile).
                    into(holder.imageView);        } else {





         Picasso.with(mCtx).load(product.getProfileImage()).
                 into(holder.imageView);
        }


    }


    @Override
    public int getItemCount() {
        return productList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {

       @BindView
      (R.id.textViewTitle) TextView textViewTitle;
        TextView textViewRating;
       @BindView(R.id.imageView) ImageView imageView;
       @BindView(R.id.ratingbar) RatingBar ratingBar;

        public ProductViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);




        }
    }
}