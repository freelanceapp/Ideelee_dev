package snow.app.ideelee.HomeScreen.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import snow.app.ideelee.HomeScreen.HomeNavigation;
import snow.app.ideelee.R;
import snow.app.ideelee.responses.homescreenres.Banner;

public class ViewPagerHome extends PagerAdapter {
    private LayoutInflater layoutInflater;
    Activity activity;
    List<Banner> image_arraylist;

    public ViewPagerHome(Activity activity, List<Banner> image_arraylist) {
        this.activity = activity;
        this.image_arraylist = image_arraylist;
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.viewpager_item_home, container, false);
      ImageView im_slider = (ImageView) view.findViewById(R.id.im_slider);
        Picasso.with(activity.getApplicationContext())
                .load(image_arraylist.get(position).getBanner())
                 // optional
                   // optional
                .into(im_slider);




        container.addView(view);
        return view;

    }

    @Override
    public int getCount() {
        return image_arraylist.size();
    }


    @Override
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }
}