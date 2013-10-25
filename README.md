Cuzy-android-demo

IDE using:
intellij idea 12



![alt tag](https://raw.github.com/TheIndex/Cuzy-iOS-demo/master/pic/0.png)


=============

android demo, that shows the capabilities of Cuzy SDK (淘宝客)，taobaoke, that can be found on http://www.cuzy.com


------------version 3.3-------------------------

last update 2013.10.25

更多说明可以看后面详细说明


1. 新增团购API

 public ArrayList<CuzyGroupBuyingItem> fetchGroupBuyingItemByLatLon(String latString, String lonString, int CoordType, int searchRadius, int pageIndex)

 public ArrayList<CuzyGroupBuyingItem> fetchGroupBuyingItemByKeyword(String keywords, int pageIndex, String cityString, String DistString , String AreaString)



2.新增taobao推广店铺API

public ArrayList<CuzyTaobaoShopItem> fetchTBshopItemsWithKeyword(String keywords, int pageIndex, String sortingString)



------------version 3.2-------------------------

last update 2013.09.26

1. 新增 是否包邮，物品来源类型（0 为未知， 1为淘宝 2为天猫 ），多张相关图片

CuzyTBKItem tempItem = rawData.get(i);

   Log.d("cuzy.com", "return of the raw data picture url is "+ tempItem.getItemImageURLString());
   Log.d("cuzy.com", "return of the raw data picture itemID is "+ tempItem.getItemID());
   Log.d("cuzy.com", "return of the raw data itemType is "+ tempItem.getItemType());
   Log.d("cuzy.com", "return of the raw data free postage is "+ tempItem.getItemFreePostage());
   Log.d("cuzy.com", "return of the raw data picture array count is "+ tempItem.getItemPictures().size());
   for (int k = 0;k< tempItem.getItemPictures().size();k++)
   {
       Log.d("cuzy.com", "return of the raw data pictures is  "+ tempItem.getItemPictures().get(k));
   }




-------------version 3.1--------------------------
last update 2013.08.14

1. 新增返回结果排序函数

2. 新增商品过滤条件函数


=========================================

last update: 2013.7.24

===============version 3.0===============

1.  this is a android APP SDK for taobaoke (Also has a sdk for ios)

2.  you can download the sdk at : http://cuzy.com/index/download_sdk


==========================================
HOW TO USE

1. copy files in the layout fold to you project's layout fold,

2. copy files in drawable-mdpi fold to your project's drawable-mdpi

3. add CuzyAdSDKAndroid.jar to your project


4. 添加信息到androidManifest.xml 

activity android:name="com.theindex.CuzyAdSDK.CuzyTBKPresentationActivity"   /> 

activity android:name="com.theindex.CuzyAdSDK.CuzyTBKWebviewActivity"    />   

application>


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
 
 2. keyword, the things you want to show, notice
 
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
 
 佣金比例区间: 0-10000  换算为(0%-100% )
 
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

6. 团购类API


6.1 

 /*

    *         fetchGroupBuyingItemByLatLon（）

    *          通过经纬度度信息获取 团购信息



    * --------------------- 必选参数--------------------------

    * 	"latString"        		 //维度

    *	"lonString"        		 //经度


    * -------------------- 可选参数 --------------------------

    *  "type"			//默认为3 . 坐标的类型:

    *                  1.gcj02ll（国测局墨卡托坐标,火星坐标系）、

    *                  2.wgs84ll（GPS经纬度，地球坐标系）、

                      3.bd09ll（百度墨卡托坐标）

    *    "radius"          //半径 ，单位为米. 如果超过10000或小于1时，默认为100 , 建议值为10000

    *    "pageIndex"          //当前的页数不填默认为第一页 即 page:0 ，最大页码为20

    */

   public ArrayList<CuzyGroupBuyingItem> fetchGroupBuyingItemByLatLon(String latString, String lonString, int CoordType, int searchRadius, int pageIndex)
   


6.2

  /*    fetchGroupBuyingItemByKeyword()
  
          通过关键字 或者类别信息获取团购信息
  
    ----------------必选参数-------------------------------
  
     "KeyWord" : 必选参数
  
            KeyWord团购名称关键字


    ----------------可选参数-------------------------------

     "pageIndex"    //当前的页数不填默认为第一页 即 page:0 ，最大页码为20


    "cityString"						//城市名，有街道则只依据街道
  
     "DistString"					//区县名
  
      "Areakey"						//地标或街道名



    */
    
    
    public ArrayList<CuzyGroupBuyingItem> fetchGroupBuyingItemByKeyword(String keywords, int pageIndex, String cityString, String DistString , String AreaString)
    



------------------------------------------------------
7.taobao推广店铺API
    
    /* fetch taobao Shop，获取淘宝店铺API


     -------------------必选参数------------------------------------
    
    "keyword"  //  keyword 店铺名称关键字

     -------------------可选参数---------------------------------------
    
    "page"          //当前的页数不填默认为第一页 即 page:0 ，最大页码为20


    排序方式:
    
     "seller_credit_desc" 卖家信用 降序
    
    "seller_credit_asc" 卖家信用 升序
    
    "commission_rate_desc"  佣金 降序
    
     "commission_rate_asc"  佣金 升序
    
    "auction_count_desc"  商品总数 降序
    
    "auction_count_asc"  商品总数 降序
    
    "total_auction_desc" 累计推广量 降序
    
    "total_auction_asc" 累计推广量 降序
    
    */
    
    
    
    
    public ArrayList<CuzyTaobaoShopItem> fetchTBshopItemsWithKeyword(String keywords, int pageIndex, String sortingString)
   



      
--------------------------------------





////////////////////////////////////////////////////////

QQ：1263572458 QQ群：322622433

意见反馈及技术支持：Email:support@theindex.com,aa@theindex.com

地址：朝阳区西大望路甲3号 蓝堡国际中心1座2303
////////////////////////////////////////////////////////

![alt tag](https://raw.github.com/TheIndex/Cuzy-Android-demo/master/pic/1.png) 

![alt tag](https://raw.github.com/TheIndex/Cuzy-Android-demo/master/pic/2.png)

![alt tag](https://raw.github.com/TheIndex/Cuzy-Android-demo/master/pic/3.png)

![alt tag](https://raw.github.com/TheIndex/Cuzy-Android-demo/master/pic/4.png)

![alt tag](https://raw.github.com/TheIndex/Cuzy-Android-demo/master/pic/5.png)


keywords:taobaoke,淘宝客，掌淘，手机淘客，cuzy，cuzysdk，返利，手机返利，团购，淘宝推广店铺，大众点评团购api
