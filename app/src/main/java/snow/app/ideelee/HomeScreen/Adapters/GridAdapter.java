package snow.app.ideelee.HomeScreen.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import snow.app.ideelee.Categories;
import snow.app.ideelee.CouponActivity;
import snow.app.ideelee.HomeScreen.ServiceActivity;
import snow.app.ideelee.R;
import snow.app.ideelee.camping.CampingCategories;
import snow.app.ideelee.carriers.CarriersCategories;
import snow.app.ideelee.coupons.SelectCouponCat;
import snow.app.ideelee.fixedpricemodule.VehiclewashCategories;
import snow.app.ideelee.metre_square_module.HandymanCategories;
import snow.app.ideelee.perday_fixedpricemodule.RentalCategories;
import snow.app.ideelee.perday_perweek_permonthmodule.GardeningCategories;
import snow.app.ideelee.perhour_fixpricemodule.TeachingCategories;
import snow.app.ideelee.perperson_permealmodule.EventServicesCategories;
import snow.app.ideelee.perquantityperfloor.MovingliftingCategories;
import snow.app.ideelee.vehical_module.vehicle.VehicleCategories;


public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ProductViewHolder> {


      private Context mContext;
    private final String[] gridViewString;
    private final int[] gridViewImageId;


    //getting the context and product list with constructor
    public GridAdapter(Context context, String[] gridViewString, int[] gridViewImageId) {
        mContext = context;
        this.gridViewImageId = gridViewImageId;
        this.gridViewString = gridViewString;
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

        holder.textViewAndroid.setText(gridViewString[position]);
        holder.imageViewAndroid.setImageResource(gridViewImageId[position]);
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position==0){
                    mContext.startActivity(new Intent(mContext, SelectCouponCat.class));
                }else if (position == 11) {
                    mContext.startActivity(new Intent(mContext, Categories.class));
                }else if (position == 1) {

                    mContext.startActivity(new Intent(mContext, VehicleCategories.class));
                } else if (position == 2) {

                    mContext.startActivity(new Intent(mContext, VehiclewashCategories.class));
                }else if (position == 3) {

                    mContext.startActivity(new Intent(mContext, RentalCategories.class));
                }else if (position == 4) {

                    mContext.startActivity(new Intent(mContext, GardeningCategories.class));
                }else if (position == 5) {

                    mContext.startActivity(new Intent(mContext, EventServicesCategories.class));
                }else if (position == 6) {

                    mContext.startActivity(new Intent(mContext, CarriersCategories.class));
                }
                else if (position == 7) {

                    mContext.startActivity(new Intent(mContext, HandymanCategories.class));
                }else if (position == 8) {

                    mContext.startActivity(new Intent(mContext, MovingliftingCategories.class));
                }else if (position == 9) {

                    mContext.startActivity(new Intent(mContext, TeachingCategories.class));
                }
                else if (position == 10) {

                    mContext.startActivity(new Intent(mContext, CampingCategories.class));
                }

                else {
                    mContext.startActivity(new Intent(mContext, ServiceActivity.class));
/*
                    Toast.makeText(getActivity(), "GridView Item: " + gridViewString[+i],
                            Toast.LENGTH_LONG).show();*/
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return gridViewString.length;
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView textViewAndroid;
        ImageView imageViewAndroid ;
        LinearLayout parent ;

        public ProductViewHolder(View itemView) {
            super(itemView);

             textViewAndroid = (TextView) itemView.findViewById(R.id.android_gridview_text);
             imageViewAndroid = (ImageView) itemView.findViewById(R.id.android_gridview_image);
            parent = (LinearLayout) itemView.findViewById(R.id.parent);




    }}


                }


