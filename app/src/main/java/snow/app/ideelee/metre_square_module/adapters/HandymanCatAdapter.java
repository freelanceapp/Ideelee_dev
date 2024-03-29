package snow.app.ideelee.metre_square_module.adapters;

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
import snow.app.ideelee.metre_square_module.HandyManListing;
import snow.app.ideelee.perday_fixedpricemodule.RentalSubCat;
import snow.app.ideelee.perquantity_fixprice.ElectricworkCategories;

public class HandymanCatAdapter extends RecyclerView.Adapter<HandymanCatAdapter.ProductViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<String> productList;

    //getting the context and product list with constructor
    public HandymanCatAdapter(Context mCtx, List<String> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public HandymanCatAdapter.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.vehicle_categories_row, null);

        return new HandymanCatAdapter.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HandymanCatAdapter.ProductViewHolder holder, int position) {
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


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (txt_vehicle_cat.getText().toString().equals("Electrical Work")) {
                        Intent intent = new Intent(mCtx, ElectricworkCategories.class);
                        mCtx.startActivity(intent);
                    } else {
                        Intent intent = new Intent(mCtx, HandyManListing.class);
                        mCtx.startActivity(intent);
                    }
                }
            });
        }
    }





}