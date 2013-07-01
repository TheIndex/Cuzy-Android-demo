Cuzy-android-demo

IDE using:
intellij idea 12



![alt tag](https://raw.github.com/TheIndex/Cuzy-iOS-demo/master/pic/0.png)


=============

android demo, that shows the capabilities of Cuzy SDK (淘宝客), that can be found on http://www.cuzy.com

=========================================

last update: 2013.6.26

===============version 1.0===============

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
