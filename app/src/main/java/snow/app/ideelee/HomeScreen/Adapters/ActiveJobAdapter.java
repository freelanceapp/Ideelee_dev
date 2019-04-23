package snow.app.ideelee.HomeScreen.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import snow.app.ideelee.HomeScreen.Modals.ActiveJobModal;
import snow.app.ideelee.HomeScreen.Modals.ServiceProvider;
import snow.app.ideelee.R;

public class ActiveJobAdapter extends RecyclerView.Adapter<ActiveJobAdapter.ProductViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<ActiveJobModal> productList;

    //getting the context and product list with constructor
    public ActiveJobAdapter(Context mCtx, List<ActiveJobModal> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ActiveJobAdapter.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.active_job_rowlayout, parent,false);
        return new ActiveJobAdapter.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ActiveJobAdapter.ProductViewHolder holder, int position) {
        //getting the product of the specified position
        ActiveJobModal product = productList.get(position);

//        //binding the data with the viewholder views
//        holder.textViewTitle.setText(product.getTitle());
//        holder.ratingBar.setRating((float) product.getRating());
//        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(product.getImage()));


    }


    @Override
    public int getItemCount() {
        return productList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView name, status,address,number,payment,servicetype;
        ImageView imageView;
        RatingBar ratingBar;

        public ProductViewHolder(View itemView) {
            super(itemView);
            ratingBar = itemView.findViewById(R.id.ratingbar);
            name = itemView.findViewById(R.id.name);

            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}