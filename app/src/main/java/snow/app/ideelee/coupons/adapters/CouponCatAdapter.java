package snow.app.ideelee.coupons.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import snow.app.ideelee.CouponActivity;
import snow.app.ideelee.R;


public class CouponCatAdapter extends RecyclerView.Adapter<CouponCatAdapter.ProductViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<String> productList;

    //getting the context and product list with constructor
    public CouponCatAdapter(Context mCtx, List<String> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.coupon_subcat_row, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        //getting the product of the specified position

holder.cat.setText(productList.get(position));
    }


    @Override
    public int getItemCount() {
        return productList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {
TextView cat;

        public ProductViewHolder(View itemView) {
            super(itemView);

cat=itemView.findViewById(R.id.cat);
itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        mCtx.startActivity(new Intent(mCtx, CouponActivity.class));
    }
});

        }
    }
}