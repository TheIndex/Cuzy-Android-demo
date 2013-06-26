package com.devspark.sidenavigation.cuzyAndroidDemo;

/**
 * Created with IntelliJ IDEA.
 * User: apple
 * Date: 13-6-16
 * Time: 下午8:50
 * To change this template use File | Settings | File Templates.
 */

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Log;
import android.widget.*;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.devspark.sidenavigation.cuzyAndroidDemo.imageCache.ImageLoader;
import com.theindex.CuzyAdSDK.CuzyTBKItem;


public class cuzyAdapter extends BaseAdapter {

    private ArrayList<CuzyTBKItem> items = null;
    private Context             context       = null;
    private Activity thisActivity = null;

    private ImageLoader imageLoader=  null;

    public static final String EXTRA_WEBURL = "com.devspark.sidenavigation.cuzyAndroidDemo.extra.weburl";
    /**
     * 构造函数,初始化Adapter,将数据传入
     * @param items
     * @param context
     */
    public cuzyAdapter(ArrayList<CuzyTBKItem> items, Context context,  Activity adapterActivity, ImageLoader imageLoader) {
        this.items = items;
        this.context = context;
        this.thisActivity = adapterActivity;
        this.imageLoader = imageLoader;
        //for test

    }

    @Override
    public int getCount() {

        //using the 2 items model
        return items == null ? 0 : items.size()/2;
    }

    @Override
    public Object getItem(int position) {
        return items == null ? null : items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //装载view
        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        final View view = layoutInflater.inflate(R.layout.simplelistview, null);
        view.setBackgroundColor(Color.parseColor("#00000000"));

        RelativeLayout leftLayout = (RelativeLayout)view.findViewById(R.id.relativeLayoutLeft);

//        ///the cellimage is 140 pix , we using 40 pix as the middle empty
//        int width = thisActivity.getWindowManager().getDefaultDisplay().getWidth();
//        int windowWith = width; //Utils.getWindowWith(thisActivity);
//        float densityValue = Utils.getDensity(thisActivity);
//        int leftPadding = (int) ((windowWith - 140*2*densityValue - 40)/2);
//
//        leftLayout.setPadding(20,0,0,0);
//
//        RelativeLayout rightLayout = (RelativeLayout)view.findViewById(R.id.relativeLayoutRight);
//
//        ///the cellimage is 140 pix , we using 40 pix as the middle empty
//        int rightPadding = (int)(leftPadding+140*densityValue+40);
//
//        rightLayout.setPadding(rightPadding,0,0,0);

       // Log.i("cuzySDK  ", ""+ leftPadding + "   "+ rightPadding +" the window width " + windowWith +" the density value is" + densityValue);

        //获取控件
        final ImageView itemImageView = (ImageView) view.findViewById(R.id.itemImageView);
        TextView itemName = (TextView) view.findViewById(R.id.itemName);
        TextView itemPrice = (TextView) view.findViewById(R.id.itemPrice);
        TextView itemSellCount = (TextView) view.findViewById(R.id.itemSellCount);

        //对控件赋值
        final  CuzyTBKItem cuzyItem = (CuzyTBKItem) getItem(2*position);
        if (cuzyItem != null) {
            final Bitmap temp = getRes("");

            imageLoader.DisplayImage(cuzyItem.getItemImageURLString() , itemImageView);


            itemImageView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v) {
                    Log.i("Test", "点击");
                    BaseMenuActivity temp = (BaseMenuActivity)thisActivity;
                    temp.startWebViewActivity("http://"+cuzyItem.getItemClickURLString());
                }
            }
            );
            itemName.setText(cuzyItem.getItemName());
            itemPrice.setText("" + cuzyItem.getItemPromotionPrice()+"元");
            itemSellCount.setText("购买量"+cuzyItem.getTradingVolumeInThirtyDays());
        }
        //获取控件
       final  ImageView itemImageView_right = (ImageView) view.findViewById(R.id.itemImageView_right);
        TextView itemName_right = (TextView) view.findViewById(R.id.itemName_right);
        TextView itemPrice_right = (TextView) view.findViewById(R.id.itemPrice_right);
        TextView itemSellCount_right = (TextView) view.findViewById(R.id.itemSellCount_right);

        //对控件赋值
       final CuzyTBKItem cuzyItem_right = (CuzyTBKItem) getItem(2*position+1);

        if (cuzyItem_right != null) {
            final Bitmap tempRight = getRes("");


          	imageLoader.DisplayImage(cuzyItem_right.getItemImageURLString() , itemImageView_right);



            itemImageView_right.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BaseMenuActivity temp = (BaseMenuActivity)thisActivity;
                    temp.startWebViewActivity("http://"+cuzyItem_right.getItemClickURLString());
                    Log.i("Test", "点击");
                }


            });


            itemName_right.setText(cuzyItem_right.getItemName());
            itemPrice_right.setText("" + cuzyItem_right.getItemPromotionPrice()+"元");
            itemSellCount_right.setText("购买量"+cuzyItem_right.getTradingVolumeInThirtyDays());
        }





        return view;
    }

    public Bitmap getRes(String name) {

        Bitmap bMap = BitmapFactory.decodeResource(context.getResources(), R.drawable.icon);
        return bMap;
    }


}