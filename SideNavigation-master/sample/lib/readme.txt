


-------------version 3.1--------------------------
last update 2013.08.14
1. 新增返回结果排序函数
2. 新增商品过滤条件函数
-------------version 3.0--------------------------
last update 2013.7.24
android cuzy sdk readme

///////////////////////////////////////////////////////

为了解决天猫商品的跳转问题，需要在webview跳转过程中，构建一个url， 代码如下

 @Override
        public void onPageFinished(android.webkit.WebView view, java.lang.String url) {
            Log.d("CuzyAdSDK","finished " + url);
            if(url.contains("http://detail.tmall.com/"))
            {
               ///if it is a tmall link
                String[] sArray = url.split("\\?|&");
                String idString = sArray[1];
                idString = idString.substring(3);///get rid of "id=";
                idString = "http://a.m.tmall.com/i" + idString + ".htm";

                webView.loadUrl(idString);

            }

        }

＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
HOW TO USE
1. copy files in the layout fold to you project's layout fold,
2. copy files in drawable-mdpi fold to your project's drawable-mdpi
3. add CuzyAdSDKAndroid.jar to your project

4. 添加信息到androidManifest.xml 

       ……
        <activity android:name="com.theindex.CuzyAdSDK.CuzyTBKPresentationActivity"   />
        <activity android:name="com.theindex.CuzyAdSDK.CuzyTBKWebviewActivity"    />
 </application>


5. 函数调用
5.1 
在onCreate()中注册
CuzyAdSDK.getInstance().setContext(this);
	  CuzyAdSDK.getInstance().registerApp("200003","208f53acd6d396867c2a721be6c807eb");

5.2可以使用三种模式调用
/* fetchItems
 asynchronizaed API
 get tbk items,if you give themeid then you the keywords will be ignored.
 
1. themeid is the id of theme you want to show, can be system hot theme, or your self app theme
 
2. keyword, the things you want to show, notice, 
 (keyword的优先级高于theme，如果keyword和theme同时不为空，那么返回keywords的搜索结果)
 
3. pageIndex, [from 0], the pages
 
 */
/* fetchRawItems
 will return a Array of CuzyTBKItems
 1. themeid is the id of theme you want to show, can be system hot theme, or your self app theme
 2. keyword, the things you want to show, notice,
 (keyword的优先级高于theme，如果keyword和theme同时不为空，那么返回keywords的搜索结果)
 3. pageIndex, [from 0], the pages
 
*/
1)CuzyAdSDK.getInstance().fetchItems("6","",0);
2)CuzyAdSDK.getInstance().fetchItems("","茶",0);
3)ArrayList<CuzyTBKItem> rawData = CuzyAdSDK.getInstance().fetchRawItems("", "手机", 0);


5.3 
设置返回图片大小
/*
 picsize: 可以定制返回图片的大小，注意图片越大，获取图片的时间越长。默认返回的图片大小为200*200
 600x600  400x400  360x360  350x350 320x320  310x310
 300x300  290x290   270x270  250x250 240x240 230x230
 220x220  210x210  200x200   190x190  180x180 170x170
 160x160  130x130   120x120  110x110   100x100 90x90
 80x80      70x70      60x60      40x40
 */
  CuzyAdSDK.getInstance().setRawItemPicSize("250x250");


5.4
 结果排序功能函数

//////set Item Sorting Method///////////////
/*
 排序方式:
 "promotion_asc"      折扣价由低到高
 "promotion_desc"     折扣价由高到低
 "seller_credit_score_desc" 卖家信用由高到低
 "commission_rate_desc"  佣金由高到低
 "commission_num_desc"   佣金比例由高到低
 "commission_volume_desc" 30天推广量由高到低
 例如 sort=promotion_asc
 注:
 筛选区间可与排序方式同时使用,其中筛选区间可以设置多个.排序方式只能选择一个,
 */
 public void setSortingMethod(String sortingMethodString);

5.5
 商品过滤功能

//////////set items filter//////////////////


/*
 注:
 筛选区间可与排序方式同时使用,其中筛选区间可以设置多个.排序方式只能选择一个,
 筛选区间:
 折扣价区间:  0 - ∞
 start_promotion: 起始折扣价
 end_promotion: 结束折扣价
 卖家信用区间: 1-20    1-5 心 6-10 钻  11-15 冠 16-20 皇冠
 start_credit:起始信用
 end_credit:结束信用
 佣金比例区间: 0-10000  换算为(0%-100%)
 start_commission_rate:起始佣金比例   规则: 去掉百分号*100  如4% 去掉% 4 *100  传入的参数为400
 end_commission_rate:结束佣金比例
 30天推广量:  0 - ∞
 start_commission_volume:起始推广量
 end_commission_volume:结束推广量
 推广类型: 枚举  1 集市 2 天猫
 item_type  搜索类型 不填为全部
 
 */

public void setFilter_PromotionRange(String startPromotionRange, String endPromotionRange)
public void setFilter_sellerCreditRange(String startCreditRange, String endCreditRange)
public void setFilter_CommissionRate(String startCommissionRate, String endCommissionRate)
public void setFilter_CommissionVolumeIn30days(String startCommissionVolume, String endCommissionVolume)
public void setFilter_itemType(String itemTypeString)


       
--------------------------------------