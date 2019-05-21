package snow.app.ideelee.camping.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import snow.app.ideelee.R;
import snow.app.ideelee.camping.CampingListing;
import snow.app.ideelee.carriers.CarriersListing;

public class CampingCatAdapter extends RecyclerView.Adapter<CampingCatAdapter.ProductViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<String> productList;

    //getting the context and product list with constructor
    public CampingCatAdapter(Context mCtx, List<String> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public CampingCatAdapter.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.vehicle_categories_row, null);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent=new Intent(mCtx, CampingListing.class);
              mCtx.startActivity(intent);
            }
        });
        return new CampingCatAdapter.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CampingCatAdapter.ProductViewHolder holder, int position) {
        //getting the product of the specified positionHandyManListing

        //binding the data with the viewholder views
    holder.txt_vehicle_cat.setText(productList.get(position));


    }


    @Override
    public int getItemCount() {
        return productList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView txt_vehicle_cat;


        public ProductViewHolder(View itemView) {
            super(itemView);

            txt_vehicle_cat = itemView.findViewById(R.id.txt_vehicle_cat);

        }
    }





}