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

import snow.app.ideelee.HomeScreen.Modals.CompletedJobModal;
import snow.app.ideelee.HomeScreen.Modals.ServiceProvider;
import snow.app.ideelee.R;

public class CompletedJobAdapter extends RecyclerView.Adapter<CompletedJobAdapter.ProductViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<CompletedJobModal> productList;

    //getting the context and product list with constructor
    public CompletedJobAdapter(Context mCtx, List<CompletedJobModal> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public CompletedJobAdapter.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.completed_job_rowlayout, parent,false);
        return new CompletedJobAdapter.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CompletedJobAdapter.ProductViewHolder holder, int position) {
        //getting the product of the specified position
        CompletedJobModal product = productList.get(position);

        //binding the data with the viewholder views

holder.name.setText(product.getName());
holder.payment.setText(product.getPayment());

    }


    @Override
    public int getItemCount() {
        return productList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView name, status,address,payment,servicetype;
        RatingBar ratingBar;

        public ProductViewHolder(View itemView) {
            super(itemView);
            ratingBar = itemView.findViewById(R.id.ratingbar);
            status = itemView.findViewById(R.id.status);
            name=itemView.findViewById(R.id.name);
            servicetype=itemView.findViewById(R.id.servicetype);
            address=itemView.findViewById(R.id.address);
            payment=itemView.findViewById(R.id.payment);

        }
    }
}