package snow.app.ideelee.filter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import snow.app.ideelee.CouponActivity;
import snow.app.ideelee.R;
import snow.app.ideelee.responses.couponcatres.Couponcategorydatum;
import snow.app.ideelee.responses.subsubcatfileration.Subcatdatum;
import snow.app.ideelee.vehical_module.vehicle.VehicalListing;


public class SubSubCatFilterationAdapter extends RecyclerView.Adapter<SubSubCatFilterationAdapter.ProductViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    List<Subcatdatum> couponcategorydatumList;


    //getting the context and product list with constructor
    public SubSubCatFilterationAdapter(Context mCtx, List<Subcatdatum> couponcategorydatumList) {
        this.mCtx = mCtx;
        this.couponcategorydatumList = couponcategorydatumList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.filter_services_row, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        //getting the product of the specified position
        Subcatdatum product = couponcategorydatumList.get(position);
        holder.cat.setText(product.getName());


        holder.cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (holder.cat.isChecked()){
//                    if (VehicalListing.subserviceidslist.contains(String.valueOf(product.getId()))){

                        VehicalListing.subserviceidslist.add(String.valueOf(product.getId()));

//                    }
                }else {

                    VehicalListing.subserviceidslist.remove(String.valueOf(product.getId()));
                }
                Toast.makeText(mCtx, product.getId(), Toast.LENGTH_SHORT).show();
/*
                if (VehicalListing.subserviceidslist.contains(String.valueOf(product.getId()))){
                   VehicalListing.subserviceidslist.remove(String.valueOf(product.getId()));

                }else {

                    VehicalListing.subserviceidslist.add(String.valueOf(product.getId()));
                }*/
                Log.e("subserviceidslist----",VehicalListing.subserviceidslist.toString());
            }
        });
    }


    @Override
    public int getItemCount() {
        return couponcategorydatumList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cat)
        CheckBox cat;
        @BindView(R.id.parent)
        LinearLayout parent;

        public ProductViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);


        }
    }
}