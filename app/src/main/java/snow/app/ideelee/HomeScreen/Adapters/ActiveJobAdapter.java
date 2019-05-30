package snow.app.ideelee.HomeScreen.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import snow.app.ideelee.HomeScreen.Modals.ActiveJobModal;
import snow.app.ideelee.HomeScreen.Modals.ServiceProvider;
import snow.app.ideelee.PaymentDetailsActivity;
import snow.app.ideelee.R;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class ActiveJobAdapter extends RecyclerView.Adapter<ActiveJobAdapter.ProductViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<ActiveJobModal> productList;

    //getting the context and product list with constructor
    public ActiveJobAdapter(Context mCtx, List<ActiveJobModal> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ActiveJobAdapter.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.active_job_rowlayout, parent, false);
        return new ActiveJobAdapter.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ActiveJobAdapter.ProductViewHolder holder, int position) {
        //getting the product of the specified position
        ActiveJobModal product = productList.get(position);

//        //binding the data with the viewholder views
//        holder.textViewTitle.setText(product.getTitle());
//        holder.ratingBar.setRating((float) product.getRating());
//        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(product.getImage()));

        String status = product.getStatus();
        holder.status.setText(status);
        if (status.equals("On Going")) {
            holder.jobdone.setVisibility(View.VISIBLE);
            holder.linearLayout.setVisibility(View.GONE);
        } else {
            holder.jobdone.setVisibility(View.GONE);
            holder.linearLayout.setVisibility(View.VISIBLE);
        }


        holder.jobdone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initiatePopupwindow(v);
            }
        });

    }


    @Override
    public int getItemCount() {
        return productList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {
        @BindView
                (R.id.linearlayout_button)
        LinearLayout linearLayout;

        @BindView(R.id.status)
        TextView status;
        TextView address, number, payment, servicetype;


        @BindView(R.id.ux_btnjobdone)
        Button jobdone;

        public ProductViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    public void initiatePopupwindow(View v) {

        LayoutInflater inflater = (LayoutInflater) mCtx.getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.job_done_dialog, (ViewGroup) v.findViewById(R.id.linearlayout_jobdone));
        final PopupWindow pw = new PopupWindow(layout, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        pw.showAtLocation(v, Gravity.CENTER, 0, 0);
        View container = (View) pw.getContentView().getRootView();
        WindowManager wm = (WindowManager) mCtx.getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams p = (WindowManager.LayoutParams) container.getLayoutParams();
        p.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        p.dimAmount = 0.6f;
        wm.updateViewLayout(container, p);
        Button btn_continue_loginPage = layout.findViewById(R.id.ux_btn_proceedtopay);
        btn_continue_loginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mCtx, PaymentDetailsActivity.class);
                mCtx.startActivity(intent);

            }
        });

    }
}