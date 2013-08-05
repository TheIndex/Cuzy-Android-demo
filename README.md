Cuzy-android-demo

IDE using:
intellij idea 12



![alt tag](https://raw.github.com/TheIndex/Cuzy-iOS-demo/master/pic/0.png)


=============

android demo, that shows the capabilities of Cuzy SDK (淘宝客), that can be found on http://www.cuzy.com

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

1)CuzyAdSDK.getInstance().fetchItems("6","",0);

2)CuzyAdSDK.getInstance().fetchItems("","茶",0);

3)ArrayList<CuzyTBKItem> rawData = CuzyAdSDK.getInstance().fetchRawItems("", "手机", 0);

4)you can use this function to change the return raw pic size
/*
 picsize: 可以定制返回图片的大小，注意图片越大，获取图片的时间越长。默认返回的图片大小为200*200
 600x600  400x400  360x360  350x350 320x320  310x310
 300x300  290x290   270x270  250x250 240x240 230x230
 220x220  210x210  200x200   190x190  180x180 170x170
 160x160  130x130   120x120  110x110   100x100 90x90
 80x80      70x70      60x60      40x40
 */
  CuzyAdSDK.getInstance().setRawItemPicSize("250x250");

      
--------------------------------------
////////////////////////////////////////////////////////
-------------version 3.0--------------------------

为了解决天猫商品的跳转问题，需要在webview跳转过程中，构建一个url， 代码如下

 @Override
        public void onPageFinished(android.webkit.WebView view, java.lang.String url) {
            Log.d("CuzyAdSDK","finished " + url);
            if(url.contains("http://detail.tmall.com/";))
            {
               ///if it is a tmall link
                String[] sArray = url.split("\\?|&");
                String idString = sArray[1];
                idString = idString.substring(3);///get rid of "id=";
                idString = "http://a.m.tmall.com/i"; + idString + ".htm";

                webView.loadUrl(idString);

            }

        }

////////////////////////////////////////////////////////


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
