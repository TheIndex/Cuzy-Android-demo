package com.devspark.sidenavigation.cuzyAndroidDemo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.devspark.sidenavigation.SideNavigationView;
import com.devspark.sidenavigation.cuzyAndroidDemo.LoadMoreListView.LoadMoreListView;
import com.devspark.sidenavigation.cuzyAndroidDemo.imageCache.ImageLoader;
import com.theindex.CuzyAdSDK.*;


/**
 * Created with IntelliJ IDEA.
 * User: apple
 * Date: 13-5-12
 * Time: 下午6:24
 * To change this template use File | Settings | File Templates.
 */
public class baseMenuActivity2 extends BaseMenuActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        CuzyAdSDK.getInstance().setContext(this);
        CuzyAdSDK.getInstance().registerApp("200003","208f53acd6d396867c2a721be6c807eb");


        setContentView(R.layout.menuactivity1);
        listView = (LoadMoreListView)findViewById(R.id.listView);
        listView.setDividerHeight(0);
        listView.setOnLoadMoreListener(new LoadMoreListView.OnLoadMoreListener() {
            public void onLoadMore() {
                // Do the work to load more items at the end of list
                // here
                new LoadDataTask().execute();
            }
        });

        int layoutID = com.theindex.CuzyAdSDK.R.layout.cuzy_list_cell_2;

        progressBar = (ProgressBar)findViewById(R.id.myprogressBar);
        progressBar.setVisibility(View.INVISIBLE);
        testSimpleListView();

        icon = (ImageView) findViewById(android.R.id.icon);
        sideNavigationView = (SideNavigationView) findViewById(R.id.side_navigation_view);
        sideNavigationView.setMenuItems(R.menu.side_navigation_menu);
        sideNavigationView.setMenuClickCallback(this);



        if (getIntent().hasExtra(EXTRA_TITLE)) {
            String title = getIntent().getStringExtra(EXTRA_TITLE);
            int resId = getIntent().getIntExtra(EXTRA_RESOURCE_ID, 0);
            setTitle(title);
            //icon.setImageResource(resId);
            sideNavigationView.setMode(getIntent().getIntExtra(EXTRA_MODE, 0) == 0 ? SideNavigationView.Mode.LEFT : SideNavigationView.Mode.RIGHT);
        }


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        testCuzySDKfunction();
    }

    public void testSimpleListView()
    {


        imageLoader=new ImageLoader(this);
        adapter = new cuzyAdapter(rawData, this,this, imageLoader);


        listView.setAdapter(adapter);


    }
    public void testCuzySDKfunction()
    {

        new LongOperation().execute("");

    }

    protected class LongOperation extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String...params){

            if (LoadingMoreFlag ==0)
            {
                currentPageIndex = 0;

                //////可以使用这个函数改变排序方式， 支持的排序方式有多种，具体可以参考文档/////////////////////////
                //CuzyAdSDK.getInstance().setSortingMethod("commission_volume_desc");

                ////// 可以用以下函数设置 过滤功能/////////////////////////////////////////////////////////////////

                //1.商品降价 大于100元
                //CuzyAdSDK.getInstance().setFilter_PromotionRange("100", "");
                //2. 卖家等级 大于10,五钻以上//
                //CuzyAdSDK.getInstance().setFilter_sellerCreditRange("10","");
                //3.佣金比例大于20%//
                //CuzyAdSDK.getInstance().setFilter_CommissionRate("2000","");
                //4. 30天内促销量 大于500//
                CuzyAdSDK.getInstance().setFilter_CommissionVolumeIn30days("500","");

                //5. 淘宝类型/
                //CuzyAdSDK.getInstance().setFilter_itemType("1");
                /////////end of filter/////////////////////////////////////////////////



                //CuzyAdSDK.getInstance().setRawItemPicSize("250x250");
                rawData = CuzyAdSDK.getInstance().fetchRawItems("", "鞋子", 0);
                Log.d("cuzy.com: ", "return of raw data: theindex:  " + rawData.size());
            }
            else
            {

                currentPageIndex++;
                LoadingMoreArray = CuzyAdSDK.getInstance().fetchRawItems("", "鞋子", currentPageIndex);
                rawData.addAll(LoadingMoreArray);
                Log.d("cuzy.com : ", " the size of rawData after loadingmore is " + rawData.size());

            }



            return"Executed";
        }

        @Override
        protected void onPostExecute(String result){
            progressBar.setVisibility(View.INVISIBLE);
            if (LoadingMoreFlag==0)
            {
                reloadListView();

            }
            else
            {
                appendListView();
            }
        }

        @Override
        protected void onPreExecute(){
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onProgressUpdate(Void... values){
        }
    }

    private class LoadDataTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            if (isCancelled()) {
                return null;
            }

            // Simulates a background task


            try
            {
                LoadingMoreFlag = 1;
                new LongOperation().execute("");
            }
            catch (Exception e)
            {
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void result) {


            //adapter.notifyDataSetChanged();
            listView.onLoadMoreComplete();

            super.onPostExecute(result);
        }

        @Override
        protected void onCancelled() {
            // Notify the loading more operation has finished
            //((LoadMoreListView) getListView()).onLoadMoreComplete();
            listView.onLoadMoreComplete();;
        }
    }








}
