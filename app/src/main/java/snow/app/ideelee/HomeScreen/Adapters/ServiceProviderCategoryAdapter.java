package snow.app.ideelee.HomeScreen.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import snow.app.ideelee.HomeScreen.Modals.ServiceProvider;
import snow.app.ideelee.R;

public class ServiceProviderCategoryAdapter extends RecyclerView.Adapter<ServiceProviderCategoryAdapter.ProductViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<ServiceProvider> productList;
    int width=0;

    //getting the context and product list with constructor
    public ServiceProviderCategoryAdapter(Context mCtx, List<ServiceProvider> productList,int width) {
        this.mCtx = mCtx;
        this.productList = productList;
        this.width = width;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.service_providers_categories_layout, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        //getting the product of the specified position
        ServiceProvider product = productList.get(position);
        //binding the data with the viewholder views
        holder.textViewTitle.setText(product.getTitle());
        holder.ratingBar.setRating((float) product.getRating());
        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(product.getImage()));
        Picasso.with(mCtx).load("https://icon2.kisspng.com/20180419/foq/kisspng-electrician-electrical-contractor-electricity-arch-5ad8e6e9a7cab2.2418352215241643296873.jpg").
                resize(width/3,width/3).into(holder.imageView);



    }


    @Override
    public int getItemCount() {
        return productList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewRating;
        ImageView imageView;
        RatingBar ratingBar;

        public ProductViewHolder(View itemView) {
            super(itemView);
            ratingBar = itemView.findViewById(R.id.ratingbar);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}