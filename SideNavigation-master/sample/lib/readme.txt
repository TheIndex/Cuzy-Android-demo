
last update 2013.7.24
android cuzy sdk readme
-------------version 3.0--------------------------
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