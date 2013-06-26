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
        CuzyAdSDK.getInstance().registerApp("200056","051a9e4652fc5b881dfc6ba74d3cd633");


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
