package snow.app.ideelee.vehical_module.vehicle.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import snow.app.ideelee.HomeScreen.ServiceActivity;
import snow.app.ideelee.R;
import snow.app.ideelee.responses.ondemandservicessubcatres.ServicesDetail;
import snow.app.ideelee.vehical_module.vehicle.VehicalListing;

public class VehicleCategoriesAdapter extends RecyclerView.Adapter<VehicleCategoriesAdapter.ProductViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    ArrayList<ServicesDetail> servicesDetailArrayList;

    //getting the context and product list with constructor
    public VehicleCategoriesAdapter(Context mCtx, ArrayList<ServicesDetail> servicesDetailArrayList) {
        this.mCtx = mCtx;
        this.servicesDetailArrayList = servicesDetailArrayList;
    }

    @Override
    public VehicleCategoriesAdapter.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.vehicle_categories_row, null);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return new VehicleCategoriesAdapter.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VehicleCategoriesAdapter.ProductViewHolder holder, int position) {
        //getting the product of the specified position
ServicesDetail servicesDetail=servicesDetailArrayList.get(position);
        //binding the data with the viewholder views
        holder.txt_vehicle_cat.setText(servicesDetail.getName());

holder.parent.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(mCtx, VehicalListing.class);
        intent.putExtra("parentid",servicesDetail.getParentCategory());
        intent.putExtra("subparentid",servicesDetail.getId());
        mCtx.startActivity(intent);
    }
});
    }


    @Override
    public int getItemCount() {
        return servicesDetailArrayList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {
        @BindView
                (R.id.txt_vehicle_cat)
        TextView txt_vehicle_cat;
        @BindView(R.id.parent)
        LinearLayout parent;

        public ProductViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

        }
    }


}