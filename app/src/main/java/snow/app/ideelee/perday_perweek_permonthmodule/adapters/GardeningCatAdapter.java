package snow.app.ideelee.perday_perweek_permonthmodule.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import snow.app.ideelee.R;
import snow.app.ideelee.perday_fixedpricemodule.RentalSubCat;
import snow.app.ideelee.perday_perweek_permonthmodule.GardeningListing;

public class GardeningCatAdapter extends RecyclerView.Adapter<GardeningCatAdapter.ProductViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<String> productList;

    //getting the context and product list with constructor
    public GardeningCatAdapter(Context mCtx, List<String> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public GardeningCatAdapter.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.vehicle_categories_row, null);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent=new Intent(mCtx, GardeningListing.class);
              mCtx.startActivity(intent);
            }
        });
        return new GardeningCatAdapter.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GardeningCatAdapter.ProductViewHolder holder, int position) {
        //getting the product of the specified position

        //binding the data with the viewholder views
    holder.txt_vehicle_cat.setText(productList.get(position));


    }


    @Override
    public int getItemCount() {
        return productList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {
       @BindView
      (R.id.txt_vehicle_cat) TextView txt_vehicle_cat;


        public ProductViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);

        }
    }





}