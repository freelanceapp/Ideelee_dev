package snow.app.ideelee.vehical_module.vehicle.dialog;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import snow.app.ideelee.R;

public class TagItemAdapter extends RecyclerView.Adapter<TagItemAdapter.ProductViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<String> productList;
    int width=0;

    //getting the context and product list with constructor
    public TagItemAdapter(Context mCtx, List<String> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
        this.width = width;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.row_tag, null);
//        if (view == null) {
//            view = inflater.inflate(R.layout.bottom_sheet_layout, null);
//        }
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        //getting the product of the specified position

        //binding the data with the viewholder views
        holder.textViewTitle.setText(productList.get(position));




    }


    @Override
    public int getItemCount() {
        return productList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle;

        public ProductViewHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);



        }
    }
}