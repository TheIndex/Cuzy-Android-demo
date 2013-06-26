package com.devspark.sidenavigation.cuzyAndroidDemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.theindex.CuzyAdSDK.*;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created with IntelliJ IDEA.
 * User: apple
 * Date: 13-6-15
 * Time: 下午11:18
 * To change this template use File | Settings | File Templates.
 */
public class ListItemView extends RelativeLayout {

    private int layout;
    private Integer itemID;

    private ImageView imageView;

    private TextView itemName;
    private TextView itemSellCount;
    private TextView itemPrice;
    private TextView itemDescription;
    private Button detailButton;





    public int getLayout() {
        return layout;
    }

    public void setLayout(int layout) {
        this.layout = layout;
    }
    public Integer getItemID() {
        return itemID;
    }

    public void setItemID(Integer itemID) {
        this.itemID = itemID;
    }

    public ListItemView(Context context){
        super(context);
    }
    public ListItemView(Context context,AttributeSet attributeSet){
        super(context,attributeSet);
    }

    public ListItemView(Context context,int layout){
        super(context);
        this.layout = layout;
    }





    public void configureWithItem(CuzyTBKItem item){

        this.setBackgroundColor(Color.parseColor("#00000000"));

        Log.d("CuzyAdSDK", "config with " + item.toString() + " to " + this.toString() + "layout:" + layout);


        if (this.layout == CuzyTBKPresentationActivity.LAYOUT_ONE){
            this.itemDescription = (TextView)findViewById(com.theindex.CuzyAdSDK.R.id.itemDescription);
        }

        this.itemName = (TextView)findViewById(com.theindex.CuzyAdSDK.R.id.itemName);

        this.itemPrice = (TextView)findViewById(com.theindex.CuzyAdSDK.R.id.itemPrice);

        this.itemSellCount = (TextView)findViewById(com.theindex.CuzyAdSDK.R.id.itemSellCount);

        this.imageView = (ImageView)findViewById(com.theindex.CuzyAdSDK.R.id.itemImageView);

        //this.detailButton = (Button)findViewById(com.theindex.CuzyAdSDK.R.id.detailButton);


        final ListItemView thisView = this;

//        if (this.detailButton == null){
//            Log.d("CuzyAdSDK","Bug!!!!!");
//        }
//        Log.d("CuzyAdSDK",this.detailButton.toString() + thisView.toString());
//        this.detailButton.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d("CuzyAdSDK","Clicked Button!~");
//                //((CuzyTBKPresentationActivity)CuzyAdSDK.getInstance().getCurrentShowingActivity()).userClickedView(thisView);
//            }
//        });


        this.setItemID(item.getItemID());

        if (item.getItemName() != null){
            this.itemName.setText(item.getItemName());
        }


        this.itemSellCount.setText("销售量:"+item.getTradingVolumeInThirtyDays());

        this.itemPrice.setText("￥"+item.getItemPromotionPrice());

        if (this.layout == CuzyTBKPresentationActivity.LAYOUT_ONE){
            this.itemDescription.setText(item.getItemDescription());
        }

        setImage(getBitmap(item));
    }
    private Bitmap getBitmap(CuzyTBKItem item){
        Bitmap bitmap = null;
        File file = new File(Utils.appExternalDirPath(),filename(item));
        if (file.exists()){
            try{
                bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
            }catch (Exception e){
                Log.d("CuzyAdSDK", e.getLocalizedMessage());
                e.printStackTrace();
            }
        }
        return bitmap;
    }
    private String filename(CuzyTBKItem item){
        String result = null;
        if (item != null){
            int slash =  item.getItemImageURLString().lastIndexOf("/");
            return item.getItemImageURLString().substring(slash + 1);
        }
        return result;
    }
    public void setImage(Bitmap image){
        imageView.setImageBitmap(image);
    }






}
