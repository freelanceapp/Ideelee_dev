package snow.app.ideelee.perperson_permealmodule.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import snow.app.ideelee.R;
import snow.app.ideelee.perday_fixedpricemodule.RentalSubCat;
import snow.app.ideelee.perperson_permealmodule.EventSubCat;

public class EventCatAdapter extends RecyclerView.Adapter<EventCatAdapter.ProductViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<String> productList;

    //getting the context and product list with constructor
    public EventCatAdapter(Context mCtx, List<String> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public EventCatAdapter.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.vehicle_categories_row, null);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent=new Intent(mCtx, EventSubCat.class);
              mCtx.startActivity(intent);
            }
        });
        return new EventCatAdapter.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EventCatAdapter.ProductViewHolder holder, int position) {
        //getting the product of the specified position

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