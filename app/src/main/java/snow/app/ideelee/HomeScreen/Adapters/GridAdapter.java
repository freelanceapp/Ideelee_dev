package snow.app.ideelee.HomeScreen.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import snow.app.ideelee.Categories;
import snow.app.ideelee.CouponActivity;
import snow.app.ideelee.HomeScreen.ServiceActivity;
import snow.app.ideelee.R;
import snow.app.ideelee.camping.CampingCategories;
import snow.app.ideelee.coupons.SelectCouponCat;
import snow.app.ideelee.fixedpricemodule.VehiclewashCategories;
import snow.app.ideelee.metre_square_module.HandymanCategories;
import snow.app.ideelee.perday_fixedpricemodule.RentalCategories;
import snow.app.ideelee.perday_perweek_permonthmodule.GardeningCategories;
import snow.app.ideelee.perhour_fixpricemodule.TeachingCategories;
import snow.app.ideelee.perperson_permealmodule.EventServicesCategories;
import snow.app.ideelee.perquantityperfloor.MovingliftingCategories;
import snow.app.ideelee.responses.homescreenres.ParentCatArray;
import snow.app.ideelee.shoppingservices.ShoppingCategories;
import snow.app.ideelee.vehical_module.vehicle.VehicalListing;
import snow.app.ideelee.vehical_module.vehicle.VehicleCategories;


public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ProductViewHolder> {


    //    private final String[] gridViewString;
//    private final int[] gridViewImageId;
    List<ParentCatArray> parentCatArrays;
    private Context mContext;

    //getting the context and product list with constructor
    public GridAdapter(Context context, List<ParentCatArray> parentCatArrays) {
        mContext = context;
//        this.gridViewImageId = gridViewImageId;
//        this.gridViewString = gridViewString;
        this.parentCatArrays = parentCatArrays;


    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.bottomsheet_gridlayout, parent, false);

        return new ProductViewHolder(view);


    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, final int position) {
        //getting the product of the specified position

//        holder.textViewAndroid.setText(gridViewString[position]);
//        holder.imageViewAndroid.setImageResource(gridViewImageId[position]);
        holder.textViewAndroid.setText(parentCatArrays.get(position).getName());



        if (position==parentCatArrays.size()-1){
            Log.e("name in last positin--",parentCatArrays.get(position).getName());
             holder.imageViewAndroid.setImageDrawable(ContextCompat.getDrawable(mContext,R.drawable.car));

//            Picasso.with(mContext)
//                    .load(R.drawable.more)
//                    .into(holder.imageViewAndroid);
        }
        //Log.e("logoooo", logo);
        Picasso.with(mContext)
                .load(parentCatArrays.get(position).getLogo())
                .into(holder.imageViewAndroid);
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position==parentCatArrays.size()-1){
                    mContext.startActivity(new Intent(mContext, Categories.class));
                    holder.imageViewAndroid.setImageDrawable(ContextCompat.getDrawable(mContext,R.drawable.more));
                 } else if (position == 1) {
//
                    mContext.startActivity(new Intent(mContext, VehiclewashCategories.class));
                } else if (position == 2) {

                    mContext.startActivity(new Intent(mContext, RentalCategories.class));
                } else if (position == 3) {

                    mContext.startActivity(new Intent(mContext, GardeningCategories.class));
                }else if (position == 4) {

                    mContext.startActivity(new Intent(mContext, VehicleCategories.class));
                }
            }
        });
//        holder.parent.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (position == 0) {
//                    mContext.startActivity(new Intent(mContext, CouponActivity.class));
//                } else if (position == 11) {
//                    mContext.startActivity(new Intent(mContext, Categories.class));
//                } else if (position == 1) {
//
//                    mContext.startActivity(new Intent(mContext, VehicleCategories.class));
//                } else if (position == 2) {
//
//                    mContext.startActivity(new Intent(mContext, VehiclewashCategories.class));
//                } else if (position == 3) {
//
//                    mContext.startActivity(new Intent(mContext, RentalCategories.class));
//                } else if (position == 4) {
//
//                    mContext.startActivity(new Intent(mContext, GardeningCategories.class));
//                } /*else if (position == 5) {
//
//                    mContext.startActivity(new Intent(mContext, EventServicesCategories.class));
//                }*/ else if (position == 6) {
//
//                    mContext.startActivity(new Intent(mContext, ShoppingCategories.class));
//                } else if (position == 7) {
//
//                    mContext.startActivity(new Intent(mContext, HandymanCategories.class));
//                } else if (position == 8) {
//
//                    mContext.startActivity(new Intent(mContext, MovingliftingCategories.class));
//                } else if (position == 9) {
//
//                    mContext.startActivity(new Intent(mContext, TeachingCategories.class));
//                } else if (position == 10) {
//
//                    mContext.startActivity(new Intent(mContext, CampingCategories.class));
//                } else if (position == parentCatArrays.size()-1) {
//
//                    mContext.startActivity(new Intent(mContext, Categories.class));
//                } else {
//                    mContext.startActivity(new Intent(mContext, ServiceActivity.class));
///*
//                    Toast.makeText(getActivity(), "GridView Item: " + gridViewString[+i],
//                            Toast.LENGTH_LONG).show();*/
//                }
//            }
//        });


    }


    @Override
    public int getItemCount() {
        return parentCatArrays.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {
        @BindView
                (R.id.android_gridview_text)
        TextView textViewAndroid;
        @BindView(R.id.android_gridview_image)
        ImageView imageViewAndroid;
        @BindView(R.id.parent)
        LinearLayout parent;

        public ProductViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }


}

