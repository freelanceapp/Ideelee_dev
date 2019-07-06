package snow.app.ideelee.coupons.adapters;

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
import snow.app.ideelee.CouponActivity;
import snow.app.ideelee.R;
import snow.app.ideelee.responses.couponcatres.Couponcategorydatum;


public class CouponCatAdapter extends RecyclerView.Adapter<CouponCatAdapter.ProductViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    List<Couponcategorydatum> couponcategorydatumList;


    //getting the context and product list with constructor
    public CouponCatAdapter(Context mCtx,  List<Couponcategorydatum> couponcategorydatumList) {
        this.mCtx = mCtx;
        this.couponcategorydatumList = couponcategorydatumList;
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
        Couponcategorydatum product = couponcategorydatumList.get(position);
        holder.cat.setText(product.getCategoryName());
    }


    @Override
    public int getItemCount() {
        return couponcategorydatumList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cat)
        TextView cat;

        public ProductViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCtx.startActivity(new Intent(mCtx, CouponActivity.class));
                }
            });

        }
    }
}