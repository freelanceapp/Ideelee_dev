package snow.app.ideelee.HomeScreen.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
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
        View view = inflater.inflate(R.layout.completed_job_rowlayout, parent, false);
        return new CompletedJobAdapter.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CompletedJobAdapter.ProductViewHolder holder, int position) {
        //getting the product of the specified position
        CompletedJobModal product = productList.get(position);

        //binding the data with the viewholder views

        holder.name.setText(product.getName());
        holder.payment.setText(product.getPayment());
        double rating = productList.get(position).getRating();
        if (rating == 0) {
            holder.ratingBar.setVisibility(View.GONE);
            holder.edit.setVisibility(View.GONE);

            holder.txt_ratenow.setVisibility(View.VISIBLE);
        } else {
            holder.txt_ratenow.setVisibility(View.GONE);
            holder.edit.setVisibility(View.VISIBLE);
            holder.ratingBar.setVisibility(View.VISIBLE);
            holder.ratingBar.setRating((float) rating);
        }

    }


    @Override
    public int getItemCount() {
        return productList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {

        @BindView
                (R.id.name)
        TextView name;
        @BindView(R.id.status)
        TextView status;
        @BindView(R.id.address)
        TextView address;
        @BindView(R.id.payment)
        TextView payment;
        @BindView(R.id.servicetype)
        TextView servicetype;
        @BindView(R.id.edit)
        TextView edit;
        @BindView(R.id.ux_txt_ratenow)
        TextView txt_ratenow;
        @BindView(R.id.ratingbar_completed)
        RatingBar ratingBar;

        public ProductViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }
}