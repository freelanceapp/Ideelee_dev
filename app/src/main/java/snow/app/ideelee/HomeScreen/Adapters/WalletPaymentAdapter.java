package snow.app.ideelee.HomeScreen.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import snow.app.ideelee.HomeScreen.Modals.CompletedJobModal;
import snow.app.ideelee.HomeScreen.Modals.WalletPaymentModal;
import snow.app.ideelee.R;

public class WalletPaymentAdapter extends RecyclerView.Adapter<WalletPaymentAdapter.ProductViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<String> productList;

    //getting the context and product list with constructor
    public WalletPaymentAdapter(Context mCtx, List<String> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public WalletPaymentAdapter.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.wallet_payment_row, parent, false);
        return new WalletPaymentAdapter.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WalletPaymentAdapter.ProductViewHolder holder, int position) {
        //getting the product of the specified position
       // WalletPaymentModal product = productList.get(position);

        //binding the data with the viewholder views



    }


    @Override
    public int getItemCount() {
        return productList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView name, status, address, payment, servicetype, edit, txt_ratenow;
        RatingBar ratingBar;

        public ProductViewHolder(View itemView) {
            super(itemView);
//            ratingBar = itemView.findViewById(R.id.ratingbar_completed);
//            status = itemView.findViewById(R.id.status);
//            name = itemView.findViewById(R.id.name);
//            edit = itemView.findViewById(R.id.edit);
//            servicetype = itemView.findViewById(R.id.servicetype);
//            address = itemView.findViewById(R.id.address);
//            payment = itemView.findViewById(R.id.payment);
//            txt_ratenow = itemView.findViewById(R.id.ux_txt_ratenow);

        }
    }
}