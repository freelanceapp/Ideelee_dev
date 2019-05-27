package snow.app.ideelee.HomeScreen.Adapters;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import snow.app.ideelee.R;

public class BottomGridAdapter extends BaseAdapter {
    private Context mContext;
    private final String[] gridViewString;
    private final int[] gridViewImageId;

    public BottomGridAdapter(Context context, String[] gridViewString, int[] gridViewImageId) {
        mContext = context;
        this.gridViewImageId = gridViewImageId;
        this.gridViewString = gridViewString;
    }

    @Override
    public int getCount() {
        return gridViewString.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View gridViewAndroid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            gridViewAndroid = new View(mContext);
            gridViewAndroid = inflater.inflate(R.layout.bottomsheet_gridlayout, null);
            TextView textViewAndroid = (TextView) gridViewAndroid.findViewById(R.id.android_gridview_text);
            ImageView imageViewAndroid = (ImageView) gridViewAndroid.findViewById(R.id.android_gridview_image);
            textViewAndroid.setText(gridViewString[i]);
            imageViewAndroid.setImageResource(gridViewImageId[i]);


//
//            DisplayMetrics displayMetrics = new DisplayMetrics();
//            ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//            int width = displayMetrics.widthPixels;
//            textViewAndroid.getLayoutParams().width = width / 4;
//            imageViewAndroid.getLayoutParams().width=width/4;
        } else {
            gridViewAndroid = (View) convertView;
        }

        return gridViewAndroid;
    }
}


