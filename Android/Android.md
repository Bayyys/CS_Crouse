# 0. 课程导入

## 课程资源

- 课程网站：[Android基础教程](https://www.bilibili.com/video/BV19U4y1R7zV?p=1&vd_source=2228fbb3090a9774de8d595d37290e9e)

## Skills

`Alt+Enter`: 代码提示修正方案

`Ctrl+Alt+L`: 代码格式化

`Ctrl+Alt+F`: 自动生成static

`Ctrl+Alt+O`: 自动导入相关包

`Ctrl+Alt+ButtonLeft`: 查看类的实现方法

# 1. 开发环境搭建

### 1.1 简介

| Android版本号 | 对应API | 发布时间 |
| ------------- | ------- | -------- |
| Android 13  | 33  | 2022年2月|
|Android 12 |31| 2021年10月|
|Android 11 |30 |2020年9月|
|Android 10 |29 |2019年8月|
|Android 9 |28| 2018年8月|
|Android 8 |26/27 |2017年8月|
|Android 7 |24/25 |2016年8月|
|Android 6 |23 |2015年9月|
|Android 5 |21/22 |2014年6月|
|Android 4.4 |19/20 |2013年9月|

## 1.2 搭建Android Studio开发环境

### 1.2.1 开发机配置要求

- 硬件基本要求

（1）内存要求至少8GB，越大越好。

（2）CPU要求1.5GHz以上，越快越好。

（3）硬盘要求系统盘剩余空间10GB以上，越大越好。

（4）要求带无线网卡与USB插槽。

- 下面是对操作系统的基本要求（以Windows为例）。


（1）必须是64位系统，不能是32位系统。

（2）Windows系统至少为Windows 7，推荐Windows 10，不支持Windows XP。

- 下面是对网络的基本要求：


（1）最好连接公众网，因为校园网可能无法访问国外的网站。

（2）下载速度至少每秒1MB，越快越好。因为Android Studio安装包大小为1GB左右，还需要另外下载几百MB的SDK，所以网络带宽一定要够大，否则下载文件都要等很久。

### 1.2.2 Android Studio安装

- 安装网址 [Download Android Studio (google.cn)](https://developer.android.google.cn/studio/)

### 1.2.3 下载Android的SDK

![image-20230221103047856](https://s2.loli.net/2023/02/21/VTeyQSd42mMzA1q.png)

#### 1.2.3.1 Android SDK Location目录说明

- build-tools目录，存放各版本Android的编译工具。
- emulator目录，存放模拟器的管理工具。
- platforms目录，存放各版本Android的资源文件与内核JAR包android.jar。
- platform-tools目录，存放常用的开发辅助工具，包括客户端驱动程序adb.exe、数据库管理工具
- sqlite3.exe，等等。
- sources目录，存放各版本Android的SDK源码。

### 1.2.4 App运行日志

- Log.e：表示错误信息，比如可能导致程序崩溃的异常。
- Log.w：表示警告信息。
- Log.i：表示一般消息。
- **Log.d(main)：表示调试信息，可把程序运行时的变量值打印出来，方便跟踪调试。**
- Log.v：表示冗余信息。

```java
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
public class MainActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.d("MainActivity", "我看到你了"); // 添加一行日志信息
	}
}
```

![image-20230221104113967](https://s2.loli.net/2023/02/21/KTHpiuxI8Djncmd.png)

# 2. App开发的特点

## 2.1 App的工程结构

### 2.1.1 App工程目录结构

![image-20230221104404161](https://s2.loli.net/2023/02/21/xHBcLOzfCkF5w9r.png)

- 两个分类：app(代表app模块)、Gradle Scripts
- app 子目录
  - manifests子目录，下面只有一个XML文件，即AndroidManifest.xml，它是App的运行配置文件。
  - java子目录，下面有3个com.example.myapp包，其中第一个包存放当前模块的Java源代码，后面两个包存放测试用的Java代码。
  - res子目录，存放当前模块的资源文件。res下面又有4个子目录：
    - drawable目录存放图形描述文件与图片文件。
    - layout目录存放App页面的布局文件。
    - mipmap目录存放App的启动图标。
    - values目录存放一些常量定义文件，例如字符串常量strings.xml、像素常量dimens.xml、颜色常量colors.xml、样式风格定义styles.xml等。
- Gradle Scripts 为工程的编译配置文件：
  - build.gradle，该文件分为项目级与模块级两种，用于描述App工程的编译规则。
  - proguard-rules.pro，该文件用于描述Java代码的混淆规则。
  - gradle.properties，该文件用于配置编译工程的命令行参数，一般无须改动。
  - settings.gradle，该文件配置了需要编译哪些模块。初始内容为include ':app'，表示只编译app模块。
  - local.properties，项目的本地配置文件，它在工程编译时自动生成，用于描述开发者电脑的环境配置，包括SDK的本地路径、NDK的本地路径等。

### 2.1.2 编译配置文件build.gradle

新创建的App项目默认有两个build.gradle，一个是**Project项目级别**的build.gradle；另一个是**Module模块级别**的build.gradle。

- **项目级别**: build.gradle指定了当前项目的总体编译规则。

  > 打开该文件在buildscript下面找到repositories和dependencies两个节点：
  >
  > - repositories节点用于设置Android Studio插件的网络仓库地址；(由于官方的谷歌仓库位于国外，下载速度相对较慢，因此可在repositories节点添加阿里云的仓库地址，方便国内开发者下载相关插件。)
  > - dependencies节点用于设置gradle插件的版本号。

  - 修改之后的buildscript节点内容如下所示：

    ```java
    buildscript {
    	repositories {
    		// 以下四行添加阿里云的仓库地址，方便国内开发者下载相关插件
    		maven { url 'https://maven.aliyun.com/repository/jcenter' }
    		maven { url 'https://maven.aliyun.com/repository/google'}
    		maven { url 'https://maven.aliyun.com/repository/gradle-plugin'}
    		maven { url 'https://maven.aliyun.com/repository/public'}
    		google()
    		jcenter()
    	}
    	dependencies {
    		// 配置gradle插件版本，下面的版本号就是Android Studio的版本号
    		classpath 'com.android.tools.build:gradle:4.1.0'
    	}
    }
    ```

- 模块级别:  build.gradle对应于具体模块，每个模块都有自己的build.gradle，它指定了当前模块的详细编译规则。

  ```java
  android {
  	// 指定编译用的SDK版本号。比如30表示使用Android 11.0编译
  	compileSdkVersion 30
  	// 指定编译工具的版本号。这里的头两位数字必须与compileSdkVersion保持一致，具体的版本号可在sdk安装目录的“sdk\build-tools”下找到
  	buildToolsVersion "30.0.3"
  	
  	defaultConfig {
  		// 指定该模块的应用编号，也就是App的包名
  		applicationId "com.example.chapter02"
  		// 指定App适合运行的最小SDK版本号。比如19表示至少要在Android 4.4上运行
  		minSdkVersion 19
  		// 指定目标设备的SDK版本号。表示App最希望在哪个版本的Android上运行
  		targetSdkVersion 30
  		// 指定App的应用版本号
  		versionCode 1
  		// 指定App的应用版本名称
  		versionName "1.0"
  		testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
  	}
  	
  	buildTypes {
  		release {
  			minifyEnabled false
  			proguardFiles getDefaultProguardFile('proguard-androidoptimize.txt'), 'proguard-rules.pro'
  		}
  	}
  }
  
  // 指定App编译的依赖信息
  dependencies {
  	// 指定引用jar包的路径
  	implementation fileTree(dir: 'libs', include: ['*.jar'])
  	// 指定编译Android的高版本支持库。如AppCompatActivity必须指定编译appcompat库
  	//appcompat库各版本见https://mvnrepository.com/artifact/androidx.appcompat/appcompat
  	implementation 'androidx.appcompat:appcompat:1.2.0'
  	// 指定单元测试编译用的junit版本号
  	testImplementation 'junit:junit:4.13'
  	androidTestImplementation 'androidx.test.ext:junit:1.1.2'
  	androidTestImplementation 'androidx.test.espresso:espresso-core:3.3.0'
  }
  ```

> 均采用了Gradle工具完成编译构建操作。
>
> Gradle工具的版本配置在gradle\wrapper\gradle-wrapper.properties，也可以依次选择菜单File→Project Structure→Project，在弹出的设置页面中修改Gradle Version。注意每个版本的AndroidStudio都有对应的Gradle版本，只有二者的版本正确对应，App工程才能成功编译。比如AndroidStudio 4.1对应的Gradle版本为6.5，更多的版本对应关系见 	[Android Gradle 插件版本说明 (google.cn)](https://developer.android.google.cn/studio/releases/gradle-plugin?hl=zh-cn#updating-plugin)

### 2.1.3 运行配置文件AndroidManifest.xml

>  AndroidManifest.xml指定了App的运行配置信息，它是一个XML描述文件，初始内容如下所示：

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
	package="com.example.chapter02">
	
    <application
		android:allowBackup="true"
		android:icon="@mipmap/ic_launcher"
		android:label="@string/app_name"
		android:roundIcon="@mipmap/ic_launcher_round"
		android:supportsRtl="true"
		android:theme="@style/AppTheme">
		<activity android:name=".Main2Activity"></activity>
		<!-- activity节点指定了该App拥有的活动页面信息，其中拥有
android.intent.action.MAIN的activity说明它是入口页面 -->
		<activity android:name=".MainActivity">
			<intent-filter>
				<action android:name="android.intent.action.MAIN" />
				
                <category android:name="android.intent.category.LAUNCHER" />
			</intent-filter>
		</activity>
	</application>
</manifest>
```

- AndroidManifest.xml的根节点为manifest，它的package属性指定了该App的包名。

- manifest下面有application节点，它的各属性说明如下：

  - android:allowBackup: 是否允许应用备份。允许用户备份系统应用和第三方应用的apk安装包和应用数据，以便在刷机或者数据丢失后恢复应用，用户即可通过adb backup和adb restore来进行对应用数据的备份和恢复。(为true表示允许，为false则表示不允许。)
  - android:icon，指定App在手机屏幕上显示的图标。
  - android:label，指定App在手机屏幕上显示的名称。
  - android:roundIcon，指定App的圆角图标。
  - android:supportsRtl，是否支持阿拉伯语／波斯语这种从右往左的文字排列顺序。为true表示支持，为false则表示不支持。
  - android:theme，指定App的显示风格。

- activity节点 -> 是活动页面的注册声明

  - >  在AndroidManifest.xml中正确配置的activity节点，才能在运行时访问对应的活动页面。初始配置的MainActivity是App的默认主页，之所以说该页面是App主页，是因为它的activity节点内部还配置了以下的过滤信息：
    >
    >  ```xml
    >  <intent-filter>
    >  	<action android:name="android.intent.action.MAIN" />
    >  	<category android:name="android.intent.category.LAUNCHER" />
    >  </intent-filter>
    >  ```
  
  - action节点设置的android.intent.action.MAIN表示该页面是App的入口页面，启动App时会最先打开该页面。
  
  - category节点设置的android.intent.category.LAUNCHER决定了是否在手机屏幕上显示App图标，如果同时有两个activity节点内部都设置了android.intent.category.LAUNCHER，那么桌面就会显示两个App图标。住默认主页必须同时配置这两种过滤规则即可。

## 2.2 App设计规范

- 界面设计和代码逻辑分离：利用XML标记描绘应用界面，同时使用Java代码书写程序逻辑，从而形成App前后端分离的设计规约，有利于提高App集成的灵活性。

### 2.2.1 优点

- 使用XML文件描述App界面，可以很方便地在Android Studio上预览界面效果。比如新创建的App项目，默认首页布局为activity_main.xml，单击界面右上角的Design按钮，即可看到如图2-15所示的预览界面。

  如果XML文件修改了Hello World的文字内容，立刻就能在预览区域观看最新界面。倘若使用Java代码描绘界面，那么必须运行App才能看到App界面，无疑费时许多。

- 一个界面布局可以被多处代码复用，比如看图界面，既能通过商城购物代码浏览商品图片，也能通过商品评价代码浏览买家晒单。

- 反过来，一段Java代码也可能适配多个界面布局，比如手机有竖屏与横屏两种模式，默认情况App采用同一套布局，然而在竖屏时很紧凑的界面布局，切换到横屏往往变得松垮乃至变形

## 2.3 App活动页面

### 2.3.1 创建新的app页面

1. 创建XML文件 —> res/layout: New->XML->layout XML File(activity_main2)

2. 创建Java代码 —> java: New->Java Class(Main2Activity.java)

   > ```java
   > public class Main2Activity extends AppCompatActivity {
   > 	@Override
   > 	protected void onCreate(Bundle savedInstanceState) {
   > 		super.onCreate(savedInstanceState);
   > 		setContentView(R.layout.activity_main2);
   > 	}
   > }
   > ```

3. 注册页面配置 —> AndroidManifest.xml

   > ```xml
   > <activity android:name=".Main2Activity"></activity>
   > // 或
   > <activity android:name=".Main2Activity /">
   > ```

### 2.3.2 快速生成页面源码

- /Java:	New->Activity->Empty Activity

### 2.3.3 跳转到另一个页面

> ```java
> // 活动页面跳转，从MainActivity跳到Main2Activity
> startActivity(new Intent(MainActivity.this, Main2Activity.class));
> 
> // 活动页面跳转，从当前页面跳转到其他页面
> startActivity(new Intent(this, MainNActivity.class));
> ```

# 3. 简单控件

## 3.1 文本显示

### 3.1.1 文本内容

- xml

  > ```xml
  > // chapter\src\main\res\layout\activity.xml
  > <TextView
  >     android:id="@+id/tv_hello"
  >     android:layout_width="wrap_content"
  >     android:layout_height="wrap_content"
  >     android:text="你好，世界" />
  > ```

- java

  > ```java
  > // chapter\src\main\java\com\Activity.java
  > // 获取名为tv_hello的文本视图
  > TextView tv_hello = findViewById(R.id.tv_hello);
  > tv_hello.setText("你好，世界"); // 设置tv_hello的文字内容
  > ```

- @string (res/values/strings.xml)

  > ```xml
  > // strings.xml
  > <resources>
  > 	<string name="app_name">chapter03</string>
  > 	<string name="hello">你好，世界</string>
  > 	</resources>
  > 
  > // activity.xml
  > <TextView
  >     android:id="@+id/tv_hello"
  >     android:layout_width="wrap_content"
  >     android:layout_height="wrap_content"
  >     android:text="@string/hello" />
  > ```

### 3.1.2 设置文本大小

- 文本大小units: 

  > **1．px**
  >
  > px是手机屏幕的最小显示单位，它与设备的显示屏有关。一般来说，同样尺寸的屏幕（比如6英寸手机），如果看起来越清晰，则表示像素密度越高，以px计量的分辨率也越大。
  >
  > **2. dp**
  >
  > dp有时也写作dip，指的是与设备无关的显示单位，它只与屏幕的尺寸有关。一般来说，同样尺寸的屏幕以dp计量的分辨率是相同的，比如同样是6英寸手机，无论它由哪个厂家生产，其分辨率换算成dp单位都是一个大小。
  >
  > **3. sp**
  >
  > sp的原理跟dp差不多，但它专门用来设置字体大小。手机在系统设置里可以调整字体的大小（小、标准、大、超大）。设置普通字体时，同数值dp和sp的文字看起来一样大；如果设置为大字体，用dp设置的文字没有变化，用sp设置的文字就变大了。
  >
  > 
  >
  > 字体大小采用不同单位的话，显示的文字大小各不相同。例如，30px、30dp、30sp这3个字号，在不同手机上的显示大小有所差异。有的手机像素密度较低，一个dp相当于两个px，此时30px等同于15dp；有的手机像素密度较高，一个dp相当于3个px，此时30px等同于10dp。假设某个App的内部文本使用字号30px，则该App安装到前一部手机的字体大小为15dp，安装到后一部手机的字体大小为10dp，显然后一部手机显示的文本会更小。

| 名称 | 解释 |
| ---- | ---- |
|px（Pixel像素）|也称为图像元素，是作为图像构成的基本单元，单个像素的大小并不固定，跟随屏幕大小和像素数量的关系变化，一个像素点为1px。|
|Resolution（分辨率）|是指屏幕的垂直和水平方向的像素数量，如果分辨率是 1920*1080 ，那就是垂直方向有 1920 个像素，水平方向有 1080 个像素。|
|Dpi（像素密度） |是指屏幕上每英寸（1英寸 = 2.54 厘米）距离中有多少个像素点。|
|Density（密度） |是指屏幕上每平方英寸（2.54 ^ 2 平方厘米）中含有的像素点数量。|
|Dip / dp (设备独立像素) | 也可以叫做dp，长度单位，同一个单位在不同的设备上有不同的显示效果，具体效果根据设备的密度有关|

**计算规则: **

我们以一个 4.95 英寸 1920 * 1080 的 nexus5 手机设备为例：

**Dpi :**

1. 计算直角边像素数量： 1920^2+1080^2=2202^2（勾股定理）。

2. 计算 DPI：2202 / 4.95 = 445。

3. 得到这个设备的 DPI 为 445 （每英寸的距离中有 445 个像素）。

**Density**

上面得到每英寸中有 445 像素，那么 density 为每平方英寸中的像素数量，应该为： 445^2=198025。

**Dip**

所有显示到屏幕上的图像都是以 px 为单位，Dip 是我们开发中使用的长度单位，最后他也需要转换成px，计算这个设备上 1dip 等于多少 px：

>  px = dip x dpi /160

**根据换算关系**：

320 x 480分辨率，3.6寸的手机：dpi为160，1dp=1px

### 3.1.3 设置文本的颜色

- java

  > ```java
  > // 字体颜色
  > // 从布局文件中获取名为tv_code_system的文本视图
  > TextView tv_code_system = findViewById(R.id.tv_code_system);
  > // 将tv_code_system的文字颜色设置系统自带的绿色
  > tv_code_system.setTextColor(Color.GREEN);
  > // 从布局文件中获取名为tv_code_six的文本视图
  > TextView tv_code_six = findViewById(R.id.tv_code_six);
  > // 将tv_code_six的文字颜色设置为透明的绿色，透明就是看不到
  > tv_code_six.setTextColor(0x00ff00);
  > // 从布局文件中获取名为tv_code_eight的文本视图
  > TextView tv_code_eight = findViewById(R.id.tv_code_eight);
  > // 将tv_code_eight的文字颜色设置为不透明的绿色，即正常的绿色
  > tv_code_eight.setTextColor(0xff00ff00);
  > 
  > // 背景颜色
  > // 从布局文件中获取名叫tv_code_background的文本视图
  > TextView tv_code_background = findViewById(R.id.tv_code_background);
  > // 将tv_code_background的背景颜色设置为绿色
  > tv_code_background.setBackgroundColor(Color.GREEN); // 在代码中定义的色值
  > tv_code_background.setBackgroundResource(R.color.green); // 颜色来源于资源文件
  > ```

- xml

  > ```xml
  > // 六位表示，默认不透明
  > android:textColor="#00ff00"
  > // strings表示
  > android:textColor="@color/green"
  > ```

## 3.2 视图基础

### 3.2.1 视图的宽高

1. match_parent：表示与上级视图保持一致。上级视图的尺寸有多大，当前视图的尺寸就有多大。
2. wrap_content：表示与内容自适应。对于文本视图来说，内部文字需要多大的显示空间，当前视图就要占据多大的尺寸。但最宽不能超过上级视图的宽度，一旦超过就要换行；最高不能超过上级视图的高度，一旦超过就会隐藏。
3. 以dp为单位的具体尺寸，比如300dp，表示宽度或者高度就是这么大

- java

  > ```java
  > // chapter\util\utils.java
  > // 根据手机的分辨率从 dp 的单位 转成为 px(像素)
  > public static int dip2px(Context context, float dpValue) {
  > // 获取当前手机的像素密度（1个dp对应几个px）
  > float scale = context.getResources().getDisplayMetrics().density;
  > return (int) (dpValue * scale + 0.5f); // 四舍五入取整
  > }
  > 
  > // Activity.java
  > // 获取名为tv_code的文本视图
  > TextView tv_code = findViewById(R.id.tv_code);
  > // 获取tv_code的布局参数（含宽度和高度）
  > ViewGroup.LayoutParams params = tv_code.getLayoutParams();
  > // 修改布局参数中的宽度数值，注意默认px单位，需要把dp数值转成px数值
  > params.width = Utils.dip2px(this, 300);
  > tv_code.setLayoutParams(params); // 设置tv_code的布局参数
  > ```

### 3.2.2 设置视图的间距

- **layout_margin**: 当前视图与外部视图(包括上级视图和平级视图)之间的距离
- **padding**: 当前视图与内部视图(包括下级视图和内部视图)之间的距离

![image-20230221123548829](https://s2.loli.net/2023/02/21/CHaxXz3Ptsrfb52.png)

> ```xml
> <LinearLayout 
>     android:layout_width="match_parent"
>     android:layout_height="300dp"
>     android:background="#00AAFF">
>     <!--    中间层的布局背景为黄色-->
>     <LinearLayout
>         android:layout_width="match_parent"
>         android:layout_height="match_parent"
>         android:layout_margin="20dp"
>         android:background="#FFFF99"
>         android:padding="60dp">
> <!--        最内层的视图背景为红色-->
>         <LinearLayout
>             android:layout_width="match_parent"
>             android:layout_height="match_parent"
>             android:background="#FF0000">
>         </LinearLayout>
>     </LinearLayout>
> </LinearLayout>
> ```

### 3.2.3 设置视图的对齐方式

- **layout_gravity**: 当前视图相对于上级视图的对齐方式
- **gravity**: 下级视图相对于当前视图的对齐方式
  - 取值包括：`left`、 `top`、 `right`、 `right`、 `bottom`
  - 可用数显连接 —> `left|top` 表示左上角对齐

> ```xml
> <?xml version="1.0" encoding="utf-8"?>
> <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
>     android:layout_width="match_parent"
>     android:layout_height="300dp"
>     android:background="#ffff99"
>     android:orientation="horizontal">
> 
>     <!--    第一个子布局为红色，在上级视图中朝下对齐，其下级视图则靠左对齐-->
>     <LinearLayout
>         android:layout_width="0dp"
>         android:layout_height="200dp"
>         android:layout_gravity="bottom"
>         android:layout_margin="10dp"
>         android:layout_weight="1"
>         android:background="@color/red"
>         android:gravity="left|top"
>         android:padding="10dp">
>         <!--        内部视图的高度和宽度都是100dp,且背景为青色-->
>         <LinearLayout
>             android:layout_width="100dp"
>             android:layout_height="100dp"
>             android:background="@color/cyan">
> 
>         </LinearLayout>
>     </LinearLayout>
> 
>     <!--    第二个子布局背景为红色，在上级视图中朝上对齐，其下级视图则靠右对齐-->
>     <LinearLayout
>         android:layout_width="0dp"
>         android:layout_height="200dp"
>         android:layout_gravity="top"
>         android:layout_margin="10dp"
>         android:layout_weight="1"
>         android:background="@color/red"
>         android:gravity="bottom|right"
>         android:padding="10dp">
> 
>         <LinearLayout
>             android:layout_width="100dp"
>             android:layout_height="100dp"
>             android:background="@color/cyan">
> 
>         </LinearLayout>
>     </LinearLayout>
> </LinearLayout>
> ```

## 3.3 常用布局

### 3.3.1 线形布局LinearLayout

> 在XML文件中，LinearLayout通过属性android:orientation区分两种方向，其中从左到右排列叫作水平方向，属性值为horizontal；从上到下排列叫作垂直方向，属性值为vertical。如果LinearLayout标签不指定具体方向，则系统默认该布局为水平方向排列，也就是默认android:orientation="horizontal"。

- 线形布局的权重
  - layout_width为0dp时，layout_weight表示水平方向的宽度比例
  - layout_height为0dp时，layout_weight表示垂直方向的高度比例

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@color/cyan"
    android:orientation="vertical">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal">

        <TextView
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:gravity="center"
            android:text="横排第一个"
            android:textColor="@color/black"
            android:textSize="17sp" />

        <TextView
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:gravity="center"
            android:text="横排第二个"
            android:textColor="@color/black"
            android:textSize="17sp" />

    </LinearLayout>

    <LinearLayout
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:orientation="vertical">

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="0dp"
            android:layout_weight="1"
            android:text="竖排第一个"
            android:textColor="@color/black"
            android:textSize="17sp" />

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="0dp"
            android:layout_weight="2"
            android:text="竖排第二个"
            android:textColor="@color/black"
            android:textSize="17sp" />

    </LinearLayout>


</LinearLayout>
```

### 3.3.2 相对布局RelativeLayout

- 下级视图位置由其他视图决定
- 如果不设定下级视图的参照物，那么么人显示在RelativeLayout内部的左上角
- 参照物：
  - 与该视图自身平级的视图
  - 该视图的上级视图

| 相对位置的属性取值       | 相对位置说明                     |
| ------------------------ | -------------------------------- |
| layout_toLeftOf          | 当前视图在指定视图的左边         |
| layout_toRightOf         | 当前视图在指定视图的右边         |
| layout_above             | 当前视图在指定视图的上方         |
| layout_below             | 当前视图在指定视图的下方         |
| layout_alignLeft         | 当前视图与指定视图的左侧对齐     |
| layout_alignRight        | 当前视图与指定视图的右侧对齐     |
| layout_alignTop          | 当前视图与指定视图的顶部对齐     |
| layout_alignBottom       | 当前视图与指定视图的底部对齐     |
| layout_centerInParent    | 当前视图在上级视图的中间         |
| layout_centerHorizontal  | 当前视图在上级视图的水平方向居中 |
| layout_centerVertical    | 当前视图在上级视图的垂直方向居中 |
| layout_alignParentLeft   | 当前视图与上级视图的左侧对齐     |
| layout_alignParentRight  | 当前视图与上级视图的右侧对齐     |
| layout_alignParentTop    | 当前视图与上级视图的顶部对齐     |
| layout_alignParentBottom | 当前视图与上级视图的底部对齐     |

![image-20230221132335529](https://s2.loli.net/2023/02/21/Bwr4nZXdlIFKGav.png)

> ```xml
> <RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
>     android:layout_width="match_parent"
>     android:layout_height="150dp">
> 
>     <TextView
>         android:id="@+id/tv_center"
>         android:layout_width="wrap_content"
>         android:layout_height="wrap_content"
>         android:layout_centerInParent="true"
>         android:background="#ffffff"
>         android:text="我在中间"
>         android:textColor="#000000"
>         android:textSize="11sp" />
> 
>     <TextView
>         android:id="@+id/tv_center_horizontal"
>         android:layout_width="wrap_content"
>         android:layout_height="wrap_content"
>         android:layout_centerHorizontal="true"
>         android:background="#eeeeee"
>         android:text="我在水平中间"
>         android:textColor="#000000"
>         android:textSize="11sp" />
> 
>     <TextView
>         android:id="@+id/tv_center_vertical"
>         android:layout_width="wrap_content"
>         android:layout_height="wrap_content"
>         android:layout_centerVertical="true"
>         android:background="#eeeeee"
>         android:text="我在垂直中间"
>         android:textColor="#000000"
>         android:textSize="11sp" />
> 
>     <TextView
>         android:id="@+id/tv_parent_left"
>         android:layout_width="wrap_content"
>         android:layout_height="wrap_content"
>         android:layout_alignParentLeft="true"
>         android:background="#eeeeee"
>         android:text="我跟上级左边对齐"
>         android:textColor="#000000"
>         android:textSize="11sp" />
> 
>     <TextView
>         android:id="@+id/tv_parent_right"
>         android:layout_width="wrap_content"
>         android:layout_height="wrap_content"
>         android:layout_alignParentRight="true"
>         android:background="#eeeeee"
>         android:text="我跟上级右边对齐"
>         android:textColor="#000000"
>         android:textSize="11sp" />
> 
>     <TextView
>         android:id="@+id/tv_parent_top"
>         android:layout_width="wrap_content"
>         android:layout_height="wrap_content"
>         android:layout_alignParentTop="true"
>         android:background="#eeeeee"
>         android:text="我跟上级顶部对齐"
>         android:textColor="#000000"
>         android:textSize="11sp" />
> 
>     <TextView
>         android:id="@+id/tv_parent_bottom"
>         android:layout_width="wrap_content"
>         android:layout_height="wrap_content"
>         android:layout_alignParentBottom="true"
>         android:background="#eeeeee"
>         android:text="我跟上级底部对齐"
>         android:textColor="#000000"
>         android:textSize="11sp" />
> 
>     <TextView
>         android:id="@+id/tv_left_center"
>         android:layout_width="wrap_content"
>         android:layout_height="wrap_content"
>         android:layout_alignTop="@id/tv_center"
>         android:layout_toLeftOf="@id/tv_center"
>         android:background="#eeeeee"
>         android:text="我在中间左边"
>         android:textColor="#000000"
>         android:textSize="11sp" />
> 
>     <TextView
>         android:id="@+id/tv_right_center"
>         android:layout_width="wrap_content"
>         android:layout_height="wrap_content"
>         android:layout_alignBottom="@id/tv_center"
>         android:layout_toRightOf="@id/tv_center"
>         android:background="#eeeeee"
>         android:text="我在中间右边"
>         android:textColor="#000000"
>         android:textSize="11sp" />
> 
>     <TextView
>         android:id="@+id/tv_above_center"
>         android:layout_width="wrap_content"
>         android:layout_height="wrap_content"
>         android:layout_above="@id/tv_center"
>         android:layout_alignLeft="@id/tv_center"
>         android:background="#eeeeee"
>         android:text="我在中间上面"
>         android:textColor="#000000"
>         android:textSize="11sp" />
> 
>     <TextView
>         android:id="@+id/tv_below_center"
>         android:layout_width="wrap_content"
>         android:layout_height="wrap_content"
>         android:layout_below="@id/tv_center"
>         android:layout_alignRight="@id/tv_center"
>         android:background="#eeeeee"
>         android:text="我在中间下面"
>         android:textColor="#000000"
>         android:textSize="11sp" />
> </RelativeLayout>
> ```

### 3.3.3 网格布局GridLayout 		

- 支持多行多列的布局方式
- 默认从左往右、从上到下排列 (为判断能够容纳几行几列，网格布局新增了android:columnCount与android:rowCount两个属性)

### 3.3.4 滚动视图ScrollView

- 垂直滚动视图：ScrollView

  - layout_width属性值设置为match_parent，layout_height属性值设置为

    wrap_content

- 水平滚动视图: HorizontalScrollView

  - layout_width属性值设置为wrap_content，layout_height属性值设置为

    match_parent

- 滚动视图节点下面必须且只能挂着一个子布局节点，否则会在运行时报错Caused by：java.lang.IllegalStateException：ScrollView can host only one direct child。
- 使纵向视图始终填满视图 — 属性：android:fillViewport —> 允许填满视图窗口

## 3.4 按钮触控

### 3.4.1 按钮控件Button

- `Button`是由`TextView`派生而来，拥有其属性和方法；

- `Button`拥有默认的按钮背景

- `Button`内部文本默认居中对齐

- 额外属性`textAllCaps`和`onClick`: 

  - `textAllCaps`： `false` —> 保持英文小写

  - `onClick`: 触发Java方法

    > ```java
    > // xml
    >     <Button
    >         android:layout_width="match_parent"
    >         android:layout_height="wrap_content"
    >         android:onClick="doClick"
    >         android:text="直接指定点击方法"
    >         android:textAllCaps="false"
    >         android:textColor="@color/black"
    >         android:textSize="17sp" />
    > 
    >     <TextView
    >         android:id="@+id/tv_button"
    >         android:layout_width="match_parent"
    >         android:layout_height="wrap_content"
    >         android:gravity="center"
    >         android:text="等待点击"
    >         android:textColor="@color/red"
    >         android:textSize="18sp" />
    > //	utils
    > public class datautil {
    >     public static String getNowTime(){
    >         SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    >         return sdf.format(new Date());
    >     }
    > }
    >             
    > // java
    >     private TextView text;
    > 
    >     @Override
    >     protected void onCreate(Bundle savedInstanceState) {
    >         super.onCreate(savedInstanceState);
    >         setContentView(R.layout.activity_button_style);
    >         text = findViewById(R.id.tv_button);
    >     }
    > 
    >     public void doClick(View view) {
    >         String desc = String.format("%s 您点击了按钮： %s", datautil.getNowTime(), ((Button) view).getText());
    >         text.setText(desc);
    > 
    >     }
    > ```
    >
    > 

### 3.4.2 点击事件和长按事件

- 实际开发中，在代码中给按钮对象注册监听器

- 两种常用的监听器：

  - 点击监听器：setOnClickListener -> (<500 ms)

    > ```java
    > package com.example.chapter03;
    > 
    > import android.os.Bundle;
    > import android.view.View;
    > import android.widget.Button;
    > import android.widget.TextView;
    > 
    > import androidx.appcompat.app.AppCompatActivity;
    > 
    > import com.example.chapter03.util.datautil;
    > 
    > public class ButtonClickActivity extends AppCompatActivity implements View.OnClickListener{
    > 
    >     private TextView btn_text_single;
    >     private TextView btn_text_public;
    > 
    >     @Override
    >     protected void onCreate(Bundle savedInstanceState) {
    >         super.onCreate(savedInstanceState);
    >         setContentView(R.layout.activity_button_click);
    >         // btn_click_single
    >         Button btn_click_single = findViewById(R.id.btn_click_single);
    >         btn_text_single = findViewById(R.id.btn_text_single);
    >         btn_click_single.setOnClickListener(new myOnclickListener(btn_text_single));
    > 
    >         // btn_click_public
    >         Button btn_click_public = findViewById(R.id.btn_click_public);
    >         btn_text_public = findViewById(R.id.btn_text_public);
    >         btn_click_public.setOnClickListener(this);
    >     }
    > 
    >     @Override
    >     public void onClick(View v) {
    >         if(v.getId() == R.id.btn_click_public){
    >             String desc = String.format("%s\t您点击了按钮\t%s", datautil.getNowTime(), ((Button) v).getText());
    >             btn_text_public.setText(desc);
    >         }
    >     }
    > 
    >     static class myOnclickListener implements View.OnClickListener {
    > 
    >         private final TextView btn_text_single;
    > 
    >         public myOnclickListener(TextView btn_text_single) {
    >             this.btn_text_single = btn_text_single;
    >         }
    > 
    >         @Override
    >         public void onClick(View view) {
    >             String desc = String.format("%s\t您点击了按钮\t%s", datautil.getNowTime(), ((Button) view).getText());
    >             btn_text_single.setText(desc);
    >         }
    >     }
    > }
    > ```

  - 长按监听器：setOnLongClickListener -> (>500 ms)

    > ```java
    > package com.example.chapter03;
    > 
    > import android.annotation.SuppressLint;
    > import android.os.Bundle;
    > import android.view.View;
    > import android.widget.Button;
    > import android.widget.TextView;
    > 
    > import androidx.appcompat.app.AppCompatActivity;
    > 
    > import com.example.chapter03.util.datautil;
    > 
    > public class ButtonLongClickActivity extends AppCompatActivity {
    > 
    >     private TextView btn_text;
    > 
    >     @Override
    >     protected void onCreate(Bundle savedInstanceState) {
    >         super.onCreate(savedInstanceState);
    >         setContentView(R.layout.activity_button_long_click);
    >         // btn_click_single
    >         Button btn_long_click = findViewById(R.id.btn_long_click);
    >         btn_text = findViewById(R.id.btn_text);
    >         btn_long_click.setOnLongClickListener(new myOnLongClickListener(btn_text));
    > 
    >         // btn_click_public
    >         Button btn_click_public = findViewById(R.id.btn_long_click_lambda);
    >         btn_click_public.setOnLongClickListener(v -> {
    >             String desc = String.format("%s\t您点击了按钮\t%s", datautil.getNowTime(), ((Button) v).getText());
    >             btn_text.setText(desc);
    >             return true;
    >         });
    >     }
    > 
    >     static class myOnLongClickListener implements View.OnLongClickListener {
    > 
    >         private final TextView btn_text;
    > 
    >         public myOnLongClickListener(TextView btn_text) {
    >             this.btn_text = btn_text;
    >         }
    > 
    >         @Override
    >         public boolean onLongClick(View view) {
    >             String desc = String.format("%s\t您点击了按钮\t%s", datautil.getNowTime(), ((Button) view).getText());
    >             btn_text.setText(desc);
    >             return true;
    >         }
    >     }
    > }
    > ```

### 3.4.3 禁用与恢复按钮

- 不可用状态(按钮)：按钮不允许点击，即使点击也没反应，同时按钮文字为灰色；
- 可用状态(按钮)：按钮允许点击，点击按钮会触发点击事件，同时按钮文字为正常的黑色；
  - —> xml: `enable`       Java:`setEnalbed`

> ```java
> private Button btn_result;
>     private TextView text_result;
> 
>     @Override
>     protected void onCreate(Bundle savedInstanceState) {
>         super.onCreate(savedInstanceState);
>         setContentView(R.layout.activity_button_enabled);
>         // test btn
>         btn_result = findViewById(R.id.btn_result);
>         // enable btn
>         Button btn_enable = findViewById(R.id.btn_enabled);
>         // disable btn
>         Button btn_disable = findViewById(R.id.btn_disable);
>         // text result
>         text_result = findViewById(R.id.text_result);
> 
>         btn_enable.setOnClickListener(this);
>         btn_disable.setOnClickListener(this);
>         btn_result.setOnClickListener(this);
>     }
> 
>     @Override
>     public void onClick(View v) {
>         switch (v.getId()) {
>             case R.id.btn_enabled:
>                 btn_result.setEnabled(true);
>                 btn_result.setTextColor(Color.BLACK);
>                 break;
>             case R.id.btn_disable:
>                 btn_result.setEnabled(false);
>                 btn_result.setTextColor(Color.GRAY);
>                 String desc = String.format("查看按钮的测试结果");
>                 text_result.setText(desc);
>                 break;
>             case R.id.btn_result:
>                 String desc_enable = String.format("%s\t您点击了按钮\t%s", datautil.getNowTime(), ((Button) v).getText());
>                 text_result.setText(desc_enable);
>                 break;
>         }
>     }
> ```

## 3.5 图像显示

### 3.5.1 图像视图ImageView

- 在XML文件中，通过属性android:src设置图片资源，属性值格式形如“@drawable/不含扩展名的图片名称”。
- 在Java代码中，调用setlmageResource方法设置图片资源，方法参数格式形如“R.drawable.不含扩展名的图片名称”。

- 缩放类型的取值说明

    - | XML中的缩放类型 | ScaleType类中的缩放类型 | 说明 |
    | :-------------: | :---------------------: | ---- |
    | fitCenter(default) | FIT_CENTER|保持宽高比例，缩放图片使其位于视图中间|
	  |centerCrop| CENTER_CROP|缩放图片使其充满视图（超出部分会被裁剪）并位于视图中间|
	  |centerlnside|CENTER_INSIDE|保持宽高比例，缩小图片使之位于视图中间（只缩小不放大）|
    |center|CENTER|保持图片原尺寸，并使其位于视图中间|
    |fitXY|FIT_XY|缩放图片使其正好填满视图（图片可能被拉伸变形）|
    |fitStart|FIT_START|保持宽高比例，缩放图片使其位于视图上方或左侧|
    |fitEnd|FIT_END|保持宽高比例，缩放图片使其位于视图下方或右侧|

### 3.5.2 图像按钮ImageButton

- ImageButton继承ImageView

  - 与Button的区别

    >1. Button既可显示文本也可显示图片（通过setBackgroundResource方法设置背景图片），而ImageButton只能显示图片不能显示文本。
    >2. ImageButton上的图像可按比例缩放，而Button通过背景设置的图像会拉伸变形，因为背景图采取fitXY方式，无法按比例缩放。
    >3. Button只能靠背景显示一张图片，而ImageButton可分别在前景和背景显示图片，从而实现两张图片叠加的效果。

  - 与ImageView的区别

    > 1. ImageView无默认的背景，ImageButton有默认的背景
    > 2. ImageView默认为`fitCenter`， ImageButton默认为`center`

  - 默认为`center` —> 通常需要修改为 `fitCenter`

### 3.5.3 同时展示图片和文字

- 使用Button
  - drawableTop：指定文字上方的图片。
  - drawableBottom：指定文字下方的图片。
  - drawableLeft：指定文字左边的图片。
  - drawableRight：指定文字右边的图片。
  - drawablePadding：指定图片与文字的间距。

> ```xml
> <Button
> android:layout_width="wrap_content"
> android:layout_height="wrap_content"
> android:drawableLeft="@drawable/ic_about"
> android:drawablePadding="5dp"
> android:text="图标在左"
> android:textSize="17sp" /
> ```

# 4. 活动Activity

## 4.1 启停活动页面

### 4.1.1 Activity的启动和结束

> ```java
> // 活动页面跳转，从MainActivity跳到Main2Activity
> startActivity(new Intent(MainActivity.this, Main2Activity.class));
> 
> // 活动页面跳转，从当前页面跳转到其他页面
> startActivity(new Intent(this, MainNActivity.class));
> ```

- e.g.

  > ```java
  > // Act_Start
  > public class ActStartActivity extends AppCompatActivity implements View.OnClickListener {
  > 
  >     @Override
  >     protected void onCreate(Bundle savedInstanceState) {
  >         super.onCreate(savedInstanceState);
  >         setContentView(R.layout.activity_act_start);
  >         Button btn_act_next = findViewById(R.id.btn_act_next);
  >         btn_act_next.setOnClickListener(this);
  >     }
  > 
  >     @Override
  >     public void onClick(View v) {
  >         startActivity(new Intent(this, ActFinishActivity.class));
  >     }
  > }
  > 
  > // Act_Finish
  > public class ActFinishActivity extends AppCompatActivity implements View.OnClickListener {
  > 
  >     @Override
  >     protected void onCreate(Bundle savedInstanceState) {
  >         super.onCreate(savedInstanceState);
  >         setContentView(R.layout.activity_act_finish);
  >         findViewById(R.id.iv_back).setOnClickListener(this);
  >         findViewById(R.id.btn_back).setOnClickListener(this);
  >     }
  > 
  >     @Override
  >     public void onClick(View v) {
  >         if (v.getId() == R.id.iv_back || v.getId() == R.id.btn_back) {
  >             finish();
  >         }
  > 
  >     }
  > }
  > ```
  >
  > 

### 4.1.2 Actitity的生命周期

![image-20230222100601404](https://s2.loli.net/2023/02/22/KF4E9U6Vt8svrGb.png)



- 生命周期：

  1. onCreate：创建活动。此时会把页面布局加载进内存，进入了初始状态。

  2. onStart：开启活动。此时会把活动页面显示在屏幕上，进入了就绪状态。

  3. onResume：恢复活动。此时活动页面进入活跃状态，能够与用户正常交互，例如允许响应用户的点击动作、允许用户输入文字等。

  4. onPause：暂停活动。此时活动页面进入暂停状态（也就是退回就绪状态），无法与用户正常交互。

  5. onStop：停止活动。此时活动页面将不在屏幕上显示。

  6. onDestroy：销毁活动。此时回收活动占用的系统资源，把页面从内存中清除掉。

  7. onRestart：重启活动。处于停止状态的活动，若想重新开启的话，无须经历onCreate的重复创建过程，而是走onRestart的重启过程。

  8. onNewIntent：重用已有的活动实例。

     ![image-20230222101224963](https://s2.loli.net/2023/02/22/5ZdochvaJ7ezuPy.png)

- 基于活动栈的生命周期

![image-20230222101007283](https://s2.loli.net/2023/02/22/4SepuAt1dnHzQws.png)

![image-20230222101013817](https://s2.loli.net/2023/02/22/jsvxbVGpMP8NuXm.png)

### 4.1.3 Activity的启动模式

#### 1. 启动模式

##### 1) 默认启动模式standard

<img src="https://s2.loli.net/2023/02/22/2Ys54tSdM19BZPl.png" alt="image-20230222103348918" style="zoom:50%;" />

##### 2) 栈顶复用模式singleTop

<img src="https://s2.loli.net/2023/02/22/CMHat2JOm8bdefh.png" alt="image-20230222103344906" style="zoom:50%;" />

- 应用场景：适合开启渠道多、多应用开启调用的 Activity，通过这种设置可以避免已经创建过的 Activity 被重复创建，多数通过动态设置使用。

##### 3) 栈内复用模式singleTask

<img src="C:/Users/bayyy/AppData/Roaming/Typora/typora-user-images/image-20230222103608875.png" alt="image-20230222103608875" style="zoom:50%;" />

- 应用场景：
  - **程序主界面**：我们肯定不希望主界面被创建多次，而且在主界面退出的时候退出整个 App 是最好的效果。
  - **耗费系统资源的****Activity**：对于那些及其耗费系统资源的 Activity，我们可以考虑将其设为 singleTask模式，减少资源耗费。

##### 4) 全局位移模式singleInstance

<img src="C:/Users/bayyy/AppData/Roaming/Typora/typora-user-images/image-20230222103615106.png" alt="image-20230222103615106" style="zoom:50%;" />

- 模式说明：在该模式下，我们会为目标 Activity 创建一个新的 Task 栈，将目标 Activity 放入新的 Task，并让目标Activity获得焦点。新的 Task 有且只有这一个 Activity 实例。 如果已经创建过目标 Activity 实例，则不会创建新的 Task，而是将以前创建过的 Activity 唤醒。

#### 2. 标志取值

##### 1）XML

- **AndroidManifest.xml** -> **android:launchMode**
- `standard`、 `singleTop`、`singleTask`、`singleInstance`

##### 2）Java

- Intent.setFlags()

- | Intent类的启动标志                                       |  说明    |
  | ------------------------------------------------------------ | ---- |
  | Intent.FLAG_ACTIVITY_NEW_TASK |   开辟一个新的任务栈，该值类似于launchMode="standard";不同之处在于，如果原来不存在活动栈，则FLAG_ACTIVITY_NEW_TASK会创建一个新栈   |
  |Intent.FLAG_ACTIVITY_SINGLE_TOP | 当栈顶为待跳转的活动实例之时，则重用栈顶的实例。该值等同于launchMode="singleTop" |
  | Intent.FLAG_ACTIVITY_CLEAR_TOP | 当栈中存在待跳转的活动实例时，则重新创建一个新实例，并清除原实例上方的所有实例。该值与launchMode="singleTask"类似，但singleTask采取onNewIntent方法启用原任务，而FLAG_ACTIVITY_CLEAR_TOP采取先调用onDestroy再调用onCreate来创建新任务 |
  | Intent.FLAG_ACTIVITY_NO_HISTORY | 该标志与launchMode="standard"情况类似，但栈中不保存新启动的活动实例。这样下次无论以何种方式再启动该实例，也要走standard模式的完整流程 |
  |Intent.FLAG_ACTIVITY_CLEAR_TASK | 该标志非常暴力，跳转到新页面时，栈中的原有实例都被清空。注意该标志需要结合FLAG_ACTIVITY_NEW_TASK使用，即setFlags方法的参数为"Intent.FLAG_ACTIVITY_CLEAR_TASK" |

#### 3. example

1) A->B->A->B->.... 不会重复返回 —> `Intent.FLAG_ACTIVITY_CLEAR_TOP`

2) 登录页面等无需再次返回的页面 —> `Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK`

   - 关闭活动栈后 需要新建栈

   - ```java
         @Override
         public void onClick(View v) {
             Intent intent = new Intent(this, LogSuccessActivity.class);
             intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
             startActivity(intent);
         }
     ```

     

#### 4.动态设置 > 静态设置

## 4.2 在活动之间传递消息

### 4.2.1 显式Intent和隐式Intent

- 作用：

  1. 标明本次通信请求从哪里来、到哪里去、要怎么走。
  2. 发起方携带本次通信需要的数据内容，接收方从收到的意图中解析数据。
  3. 发起方若想判断接收方的处理结果，意图就要负责让接收方传回应答的数据内容。

- 组成：

  - | **元素名称** | **设置方法** | 说明和用途 |
    | ------------ | ------------ | ---------- |
    | Component | setComponent | 组件，它指定意图的来源与目标 |
    | Action | setAction | 动作，它指定意图的动作行为 |
    | Data | setData | 即Uri，它指定动作要操纵的数据路径 |
    | Category | addCategory | 类别，它指定意图的操作类别 |
    | Type | setType | 数据类型，它指定消息的数据类型 |
    | Extras | putExtras | 扩展信息，它指定装载的包裹信息 |
    | Flags | setFlags | 标志位，它指定活动的启动标志 |

#### 1. 显示Intent

- 直接指定来源活动与目标活动，属于精确匹配

- 构建方式：

  1. 在Intent的构造函数中指定，示例代码如下：

     > ```java
     > Intent intent = new Intent(this, ActNextActivity.class); // 创建一个目标确定的意图
     > ```

  2. 调用意图对象的setClass方法指定，示例代码如下：

     > ```java
     > Intent intent = new Intent(); // 创建一个新意图
     > intent.setClass(this, ActNextActivity.class); // 设置意图要跳转的目标活动
     > ```

  3. 调用意图对象的setComponent方法指定，示例代码如下：

     > ```java
     > Intent intent = new Intent(); // 创建一个新意图
     > // 创建包含目标活动在内的组件名称对象
     > ComponentName component = new ComponentName(this, ActNextActivity.class);
     > intent.setComponent(component); // 设置意图携带的组件信息
     > ```

#### 2. 隐式Intent

- 没有明确指定要跳转的目标活动，只给出一个动作字符串让系统自动匹配，属于模糊匹配

- 常见系统动作的取值说明

  | **Intent类的系统动作常量名** | **系统动作的常量值** | **说明** |
  | ----- | ----- | ----- |
  | ACTION_MAIN | android.intent.action.MAIN | App启动时的入口 |
  | ACTION_VIEW | android.intent.action.VIEW | 向用户显示数据 |
  | ACTION_SEND | android.intent.action.SEND | 分享内容 |
  | ACTION_CALL | android.intent.action.CALL | 直接拨号 |
  | ACITON_DIAL | android.intent.action.DIAL | 准备拨号 |
  | ACTION_SENDTO | android.intent.action.SENDTO | 发送短信 |
  | ACTION_ANSWER | android.intent.action.ANSWER | 接听电话 |

- 可以通过setAction方法指定，也可以通过构造函数Intent(String action)直接生成意图对象

- Uri和Category：指定路径和门类信息

  - `Intent(String action, Uri uri)`	或	`setData`
  - `addCategory()`

#### 3. 示例

```java
// chapter 04 调用 chapter03
// chapter 04： ActionUriActivity.java 
public void onClick(View v) {
        String phoneNum = "12345";
        Intent intent = new Intent();
        Uri uri = null;
        switch (v.getId()){
            case R.id.btn_uri_dial:
                intent.setAction(Intent.ACTION_DIAL);
                uri = Uri.parse("tel:" + phoneNum);
                intent.setData(uri);
                break;

            case R.id.btn_uri_sms:
                intent.setAction(Intent.ACTION_SENDTO);
                uri = Uri.parse("smsto:" + phoneNum);
                intent.setData(uri);
                break;
            case R.id.btn_uri_my:
                intent.setAction("android.intent.action.NING");
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                break;
        }
    startActivity(intent);
    }

// chapter03: AndroidManifest.xml
        <activity
            android:name=".CalculatorActivity"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
            // 增加部分
            <intent-filter>
                <action android:name="android.intent.action.NING" />
                <category android:name="android.intent.category.DEFAULT" />
            </intent-filter>
            //增加部分
        </activity>
```

### 4.2.2 向下一个Activity发送数据

- `putExtras` & `getExtras`

- `Bundle`

  > ```java
  > // Send.java
  > public void onClick(View v) {
  >     // 创建一个意图对象，准备跳到指定的活动页面
  >     Intent intent = new Intent(this, ActReceiveActivity.class);
  >     Bundle bundle = new Bundle(); // 创建一个新包裹
  >     // 往包裹存入名为request_time的字符串
  >     bundle.putString("request_time", DateUtil.getNowTime());
  >     // 往包裹存入名为request_content的字符串
  >     bundle.putString("request_content", tv_send.getText().toString());
  >     intent.putExtras(bundle); // 把快递包裹塞给意图
  >     startActivity(intent); // 跳转到意图指定的活动页面
  > }
  > 
  > // Receive.java
  > protected void onCreate(Bundle savedInstanceState) {
  >         super.onCreate(savedInstanceState);
  >         setContentView(R.layout.activity_action_receive);
  >         findViewById(R.id.btn_receive_return).setOnClickListener(this);
  >         // 从布局文件中获取名为tv_receive的文本视图
  >         TextView tv_receive = findViewById(R.id.tv_receive);
  >         // 从上一个页面传来的意图中获取快递包裹
  >         Bundle bundle = getIntent().getExtras();
  >         // 从包裹中取出名为request_time的字符串
  >         String request_time = bundle.getString("request_time");
  >         // 从包裹中取出名为request_content的字符串
  >         String request_content = bundle.getString("request_content");
  >         String desc = String.format("收到请求消息：\n请求时间为%s\n请求内容为%s",
  >         request_time, request_content);
  >         tv_receive.setText(desc); // 把请求消息的详情显示在文本视图上
  > }
  > ```

### 4.2.3 向上一个Activity返回数据

- (过时方法)处理下一个页面的应答数据，详细步骤说明如下：

  - 上一个页面打包好请求数据，调用`startActivityForResult`方法执行跳转动作
  - 下一个页面接收并解析请求数据，进行相应处理
  - 下一个页面在返回上一个页面时，打包应答数据并调用`setResult`方法返回数据包裹
  - 上一个页面重写方法`onActivityResult`, 解析获得下一个页面的返回数据

- (最新方法)，详细步骤说明如下：

  - 上一个页面打包好请求数据，调用`register.launch(intent)`方法执行跳转动作 -> 基于 `registerForActivityResult` 方法

  - 下一个页面接收并解析请求数据，进行相应处理

  - 下一个页面在返回上一个页面时，打包应答数据并调用`setResult(Activity.RESULT_OK, intent)`方法返回数据包裹

  - 上一个页面重写方法`onActivityResult`, 解析获得下一个页面的返回数据

    > ```java
    > ActivityResultLauncher<Intent> register = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
    >             @Override
    >             public void onActivityResult(ActivityResult result) {
    >                 
    >             }
    >         })
    >     
    > // lambda方法
    >     ActivityResultLauncher<Intent> register = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
    >             @Override
    >             public void onActivityResult(ActivityResult result) {
    > 
    >             }
    >         })
    > ```

- 示例

  > ```java
  > // Request
  > public class ActRequestActivity extends AppCompatActivity implements View.OnClickListener {
  >     private final String mRequest = "what's your phone number?";
  >     private ActivityResultLauncher<Intent> register;
  >     private TextView tv_response;
  >     private TextView tv_request;
  > 
  >     @Override
  >     protected void onCreate(Bundle savedInstanceState) {
  >         super.onCreate(savedInstanceState);
  >         setContentView(R.layout.activity_act_request);
  >         tv_request = findViewById(R.id.tv_request_info);
  >         tv_request.setText("待发送的数据为：" + mRequest);
  >         tv_response = findViewById(R.id.tv_request);
  > 
  >         findViewById(R.id.btn_request).setOnClickListener(this);
  >         register = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
  >             if (result != null){
  >                 Intent intent = result.getData();
  >                 if (intent != null && result.getResultCode() == Activity.RESULT_OK) {
  >                     Bundle bundle = intent.getExtras();
  >                     String response_time = bundle.getString("response_time");
  >                     String response_content = bundle.getString("response_content");
  >                     String desc = String.format("收到应答消息：...\n应答时间为: %s\n应答内容为: %s", response_time, response_content);
  >                     tv_response.setText(desc);
  >                 }
  >             }
  >         });
  >     }
  > 
  >     @Override
  >     public void onClick(View v) {
  >         Intent intent = new Intent(this, ActResponseActivity.class);
  >         Bundle bundle = new Bundle();
  >         bundle.putString("request_time", dateUtil.getNowTime());
  >         bundle.putString("request_content", mRequest);
  >         intent.putExtras(bundle);
  > //      过时
  > //        startActivityForResult(intent, 0);
  >         register.launch(intent);
  >     }
  > }
  > 
  > // Response
  > public class ActResponseActivity extends AppCompatActivity implements View.OnClickListener {
  >     private static String mMassage = "12345";
  >     @Override
  >     protected void onCreate(Bundle savedInstanceState) {
  >         super.onCreate(savedInstanceState);
  >         setContentView(R.layout.activity_act_response);
  >         TextView tv_request_response = findViewById(R.id.tv_request_response);
  >         Bundle bundle = getIntent().getExtras();
  >         String request_time = bundle.getString("request_time");
  >         String request_content = bundle.getString("request_content");
  >         String desc = String.format("收到请求消息：...\n请求时间为: %s\n请求内容为: %s", request_time, request_content);
  >         tv_request_response.setText(desc);
  > 
  >         findViewById(R.id.btn_response).setOnClickListener(this);
  > 
  >         TextView tv_response = findViewById(R.id.tv_response);
  >         tv_response.setText("Response" + mMassage);
  >     }
  > 
  >     @Override
  >     public void onClick(View v) {
  >         Intent intent = new Intent();
  >         Bundle bundle = new Bundle();
  >         bundle.putString("response_time", dateUtil.getNowTime());
  >         bundle.putString("response_content", mMassage);
  >         intent.setClass(this, ActRequestActivity.class);
  >         intent.putExtras(bundle);
  >         setResult(Activity.RESULT_OK, intent);
  >         finish();
  >     }
  > }
  > ```
  >
  > 

## 4.3 为Activity补充附加信息

### 4.3.1 利用资源文件配置字符串

> ```java
> public class ActResponseActivity extends AppCompatActivity implements View.OnClickListener {
>     private static String mMassage = "12345";
>     @Override
>     protected void onCreate(Bundle savedInstanceState) {
>         super.onCreate(savedInstanceState);
>         setContentView(R.layout.activity_act_response);
>         TextView tv_request_response = findViewById(R.id.tv_request_response);
>         Bundle bundle = getIntent().getExtras();
>         String request_time = bundle.getString("request_time");
>         String request_content = bundle.getString("request_content");
>         String desc = String.format("收到请求消息：...\n请求时间为: %s\n请求内容为: %s", request_time, request_content);
>         tv_request_response.setText(desc);
> 
>         findViewById(R.id.btn_response).setOnClickListener(this);
> 
>         TextView tv_response = findViewById(R.id.tv_response);
>         tv_response.setText("Response" + mMassage);
>     }
> 
>     @Override
>     public void onClick(View v) {
>         Intent intent = new Intent();
>         Bundle bundle = new Bundle();
>         bundle.putString("response_time", dateUtil.getNowTime());
>         bundle.putString("response_content", mMassage);
>         intent.setClass(this, ActRequestActivity.class);
>         intent.putExtras(bundle);
>         setResult(Activity.RESULT_OK, intent);
>         finish();
>     }
> }
> ```

### 4.3.2 利用元数据传递配置信息

- xml代码

  - > ```xml
    > // AndroidManifest.xml
    > // simple
    > <meta-data android:name="weather" android:value="晴天"/>
    > 
    > // other
    > <activity android:name=".MetaDataActivity">
    > <meta-data
    > android:name="weather"
    > android:value="@string/weather_str" />
    > </activity>
    > ```
    >
    > 

- java代码步骤
  1. 调用getPackageManager方法获得当前应用的包管理器。
  2. 调用包管理器的getActivityInfo方法获得当前活动的信息对象。
  3. 活动信息对象的metaData是Bundle包裹类型，调用包裹对象的getString即可获得指定名称的参数值。
  - > ```java
    >         TextView tv_meta = findViewById(R.id.tv_meta);
    >         PackageManager pm = getPackageManager();
    >         try {
    >             ActivityInfo info = pm.getActivityInfo(getComponentName(), PackageManager.GET_META_DATA);
    >             Bundle bundle = info.metaData;
    >             String weather = bundle.getString("weather");
    >             tv_meta.setText(weather);
    >         } catch (PackageManager.NameNotFoundException e) {
    >             throw new RuntimeException(e);
    >         }
    > ```

### 4.3.3 给App页面注册快捷方式

- src/main/res/xml(新建)/shortcuts.xml

  - > ```xml
    > <?xml version="1.0" encoding="utf-8"?>
    > <shortcuts xmlns:android="http://schemas.android.com/apk/res/android">
    >     <shortcut
    >         android:enabled="true"
    >         android:shortcutId="first"
    >         android:shortcutLongLabel="@string/first_long"
    >         android:shortcutShortLabel="@string/first_short"
    >         android:src="@mipmap/ic_launcher">
    > 
    >         <intent
    >             android:action="android.intent.action.VIEW"
    >             android:targetClass="com.example.chapter04.ActStartActivity"
    >             android:targetPackage="com.example.chapter04" />
    >         <categories android:name="android:shortcut.conversation" />
    >     </shortcut>
    > 
    >     <shortcut
    >         android:enabled="true"
    >         android:shortcutId="second"
    >         android:shortcutLongLabel="@string/second_long"
    >         android:shortcutShortLabel="@string/second_short"
    >         android:src="@mipmap/ic_launcher">
    > 
    >         <intent
    >             android:action="android.intent.action.VIEW"
    >             android:targetClass="com.example.chapter04.JumpFirstActivity"
    >             android:targetPackage="com.example.chapter04" />
    >         <categories android:name="android:shortcut.conversation" />
    >     </shortcut>
    > 
    >     <shortcut
    >         android:enabled="true"
    >         android:shortcutId="third"
    >         android:shortcutLongLabel="@string/third_long"
    >         android:shortcutShortLabel="@string/third_short"
    >         android:src="@mipmap/ic_launcher">
    > 
    >         <intent
    >             android:action="android.intent.action.VIEW"
    >             android:targetClass="com.example.chapter04.LogInputActivity"
    >             android:targetPackage="com.example.chapter04" />
    >         <categories android:name="android:shortcut.conversation" />
    >     </shortcut>
    > 
    > </shortcuts>
    > ```

- AndroidManifest.xml

  - > ```xml
    >         <activity
    >             android:name=".ActStartActivity"
    >             android:exported="true">
    >             <intent-filter>
    >                 <action android:name="android.intent.action.MAIN" />
    >                 <category android:name="android.intent.category.LAUNCHER" />
    >             </intent-filter>
    >  <!-- 指定快捷方式。在桌面上长按应用图标，就会弹出@xml/shortcuts所描述的快捷菜单 -->
    >             <meta-data
    >                 android:name="android.app.shortcuts"
    >                 android:resource="@xml/shortcuts"/>
    >         </activity>
    > ```

  - **注意：** 调用Activity的 `android:exported` 应该设置为 `true` -> 可以由其他应用打开

    - > ```xml
      > // e.g.
      > <activity
      >             android:name=".JumpFirstActivity"
      >             android:exported="true" />
      > <activity
      >             android:name=".JumpSecondActivity"
      >             android:exported="false" />
      >         
      > ```

- 节点属性说明：
  - shortcutId：快捷方式的编号。
  - enabled：是否启用快捷方式。true表示启用，false表示禁用。
  - icon：快捷菜单左侧的图标。
  - shortcutShortLabel：快捷菜单的短标签。
  - shortcutLongLabel：快捷菜单的长标签。优先展示长标签的文本，长标签放不下时才展示短标签的文本。
- 跳转动作由 `intent` 节点定义，主要有两个属性修改
  - targetPackage属性固定为当前App的包名
  - targetClass属性描述了菜单项对应的活动类完整路径

# 5. 中级控件

## 5.1 图形定制

### 5.1.1 图形Drawable

- Android把**所有能够显示的图形**都抽象为**Drawable**类（可绘制的）。这里的图形不止是图片，还包括色块、画板、背景等。
- 包含图片在内的图形文件放在res目录的各个drawable目录下，其中drawable目录一般保存描述性的XML文件，而图片文件一般放在具体分辨率的drawable目录下。例如：
  1. drawable-ldpi里面存放低分辨率的图片（如240×320），现在基本没有这样的智能手机了。
  2. drawable-mdpi里面存放中等分辨率的图片（如320×480），这样的智能手机已经很少了。
  3. drawable-hdpi里面存放高分辨率的图片（如480×800），一般对应4英寸～4.5英寸的手机（但不绝对，同尺寸的手机有可能分辨率不同，手机分辨率就高不就低，因为分辨率低了屏幕会有模糊的感觉）。
  4. drawable-xhdpi里面存放加高分辨率的图片（如720×1280），一般对应5英寸～5.5英寸的手机。
  5. drawable-xxhdpi里面存放超高分辨率的图片（如1080×1920），一般对应6英寸～6.5英寸的手机。
  6. drawable-xxxhdpi里面存放超超高分辨率的图片（如1440×2560），一般对应7英寸以上的平板电脑。

- 基本上，分辨率每加大一级，宽度和高度就要增加二分之一或三分之一像素

### 5.1.2 形状图形

- 包括矩形、圆角矩形、圆形、椭圆等;
- 是以shape标签为根节点的XML描述文件。根节点下定义了6个节点，分别是：size（尺寸）、stroke（描边）、corners（圆角）、solid（填充）、padding（间隔）、gradient（渐变）;
- 实际开发主要使用3个节点: stroke(描边)、corners(圆角)、solid(填充)

#### 1.  shape(形状)

- `string`

| 形状类型  | 说明                               |
| --------- | ---------------------------------- |
| rectangle | 矩形(defauklt)                     |
| oval      | 椭圆(corners节点失效)              |
| line      | 直线(必须设置stroke节点，否则报错) |
| ring      | 圆环                               |

#### 2. size(尺寸)

- 宽高尺寸(不设置，则宽高与宿主视图一样大小)
  - height: 像素类型，图形高度
  - width: 像素类型，图形宽度

#### 3. stroke（描边）

- 描边规格(不设置，则不存在描边)
  - color：颜色类型，描边的颜色。
  - dashGap：像素类型，每段虚线之间的间隔。
  - dashWidth：像素类型，每段虚线的宽度。若dashGap和dashWidth有一个值为0，则描边为实线。
  - width：像素类型，描边的厚度。

#### 4. corners（圆角）

- 圆角大小(不设置，则没有圆角)
  - bottomLeftRadius：像素类型，左下圆角的半径。
  - bottomRightRadius：像素类型，右下圆角的半径。
  - topLeftRadius：像素类型，左上圆角的半径。
  - topRightRadius：像素类型，右上圆角的半径。
  - radius：像素类型，4个圆角的半径（若有上面4个圆角半径的定义，则不需要radius定义）。

#### 5. solid（填充）

- 填充色彩(不设置，则无填充颜色)
  - color：颜色类型，内部填充的颜色

#### 6. padding（间隔）

- 形状图形与周围边界的间隔(不设置，则不设间隔)
  - top：像素类型，与上方的间隔。
  - bottom：像素类型，与下方的间隔。
  - left：像素类型，与左边的间隔。
  - right：像素类型，与右边的间隔。

#### 7. gradient（渐变）

- 颜色渐变(不设置，则没有渐变效果)

  - angle：整型，渐变的起始角度。为0时表示时钟的9点位置，值增大表示往逆时针方向旋转。例如，值为90表示6点位置，值为180表示3点位置，值为270表示0点/12点位置。

  - type：字符串类型，渐变类型。渐变类型的取值说明见表：

    - | 渐变类型 | 说明 |
      | :--: | ---- |
      | linear | 线性渐变，默认值 |
      | radial | 放射渐变，起始颜色就是圆心颜色 |
      | sweep | 滚动渐变，即一个线段以某个端点为圆心做360度旋转 |
  
  - centerX：浮点型，圆心的X坐标。当android:type="linear"时不可用。
  
  - centerY：浮点型，圆心的Y坐标。当android:type="linear"时不可用。
  
  - gradientRadius：整型，渐变的半径。当android:type="radial"时需要设置该属性。
  
  - centerColor：颜色类型，渐变的中间颜色。
  
  - startColor：颜色类型，渐变的起始颜色。
  
  - endColor：颜色类型，渐变的终止颜色。
  
  - useLevel：布尔类型，设置为true为无渐变色、false为有渐变色。

### 5.1.3 九宫格图片(点9图片)

- `ButtonRight` - Create 9-Patch file...

![image-20230222191129788](https://s2.loli.net/2023/02/22/hZ7d6rmzfFlXHOi.png)

### 5.1.4 状态列表图形

- Drawable -> StateListDrawable(状态列表图形)

- /drable/

- 状态类型取值：

  - | 状态类型的属性名称 | 说明         | 适用的组件                          |
    | ------------------ | ------------ | ----------------------------------- |
    | state_pressed      | 是否按下     | 按钮Button                          |
    | state_checked      | 是否勾选     | 复选框CheckBox、单选按钮RadioButton |
    | state_focused      | 是否获取焦点 | 文本编辑框EditText                  |
    | state_selected     | 是否选中     | 各控件通用                          |

## 5.2 选择按钮

![image-20230222194248260](https://s2.loli.net/2023/02/22/xKzLfCXgGoAQVZ2.png)

- CompoundButton在XML中两个主要属性
  1. checked：指定按钮的勾选状态，true表示勾选，false则表示未勾选。默认为未勾选。
  2. button：指定左侧勾选图标的图形资源，如果不指定就使用系统的默认图标。
- CompoundButton在Java中四个主要方法
  1. setChecked：设置按钮的勾选状态。
  2. setButtonDrawable：设置左侧勾选图标的图形资源。
  3. setOnCheckedChangeListener：设置勾选状态变化的监听器。
  4. isChecked：判断按钮是否勾选。

### 5.2.1 复选框CheckBox

- 点击复选框将它勾选，再次点击取消勾选

- 复选框对象调用setOnCheckedChangeListener方法设置勾选监听器

- XML

  - > ```xml
    > <CheckBox
    >         android:id="@+id/ck_system"
    >         android:layout_width="match_parent"
    >         android:layout_height="wrap_content"
    >         android:padding="5dp"
    >         android:text="系统自带复选框" />
    > 
    >     <CheckBox
    >         android:id="@+id/ck_custom"
    >         android:layout_width="match_parent"
    >         android:layout_height="wrap_content"
    >         android:layout_marginTop="10dp"
    >         android:button="@drawable/checkbox_selector"
    >         android:checked="true"
    >         android:padding="5dp"
    >         android:text="自制复选框" />
    > ```

- Java

  - > ```java
    > 	@Override    
    > 	protected void onCreate(Bundle savedInstanceState) {
    >         super.onCreate(savedInstanceState);
    >         setContentView(R.layout.activity_check_box);
    >         CheckBox ck_system = findViewById(R.id.ck_system);
    >         CheckBox ck_custom = findViewById(R.id.ck_custom);
    > 
    >         ck_system.setOnCheckedChangeListener(this);
    >         ck_custom.setOnCheckedChangeListener(this);
    >     }
    > 
    >     @Override
    >     public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
    >         String desc = String.format("You %s this CheckBox", isChecked ? "chose" : "didn't choose");
    >         buttonView.setText(desc);
    >     }
    > ```

### 5.2.2 开关按钮Switch

### 5.2.3 单选按钮RadioButton

- RadioGroup -> 类似于线形布局

  - 单选组多了管理单选按钮的功能，而线性布局不具备该功能。
  - 如果不指定orientation属性，那么单选组默认垂直排列，而线性布局默认水平排列

- 常用方法：

  1. check：选中指定资源编号的单选按钮。
  2. getCheckedRadioButtonId：获取已选中单选按钮的资源编号。
  3. setOnCheckedChangeListener：设置单选按钮勾选变化的监听器。

- RadioButton默认未选中，点击后显示选中，但是再次点击不会取消选中。只有点击同组的其他单选按钮时，原来选中的单选按钮才会取消选中；另需注意，单选按钮的选中事件不是由RadioButton处理，而是由RadioGroup处理。

- > ```xml
  > <?xml version="1.0" encoding="utf-8"?>
  > <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
  >     android:layout_width="match_parent"
  >     android:layout_height="match_parent"
  >     android:orientation="vertical">
  > 
  >     <TextView
  >         android:layout_width="match_parent"
  >         android:layout_height="wrap_content"
  >         android:gravity="left|center"
  >         android:padding="5dp"
  >         android:text="请选择类别："
  >         android:textColor="@color/black"
  >         android:textSize="20dp" />
  > 
  > 
  >     <RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
  >         android:layout_width="match_parent"
  >         android:layout_height="150dp">
  > 
  >         <RadioGroup
  >             android:id="@+id/radio_group"
  >             android:layout_width="wrap_content"
  >             android:layout_height="120dp"
  >             android:layout_alignParentLeft="true"
  >             android:layout_centerVertical="true"
  >             android:layout_marginLeft="40dp">
  > 
  >             <RadioButton
  >                 android:id="@+id/rb_first"
  >                 android:layout_width="wrap_content"
  >                 android:layout_height="0dp"
  >                 android:layout_weight="1"
  >                 android:text="类别一"
  >                 android:textColor="@color/black"
  >                 android:textSize="20dp" />
  > 
  >             <RadioButton
  >                 android:id="@+id/rb_second"
  >                 android:layout_width="wrap_content"
  >                 android:layout_height="0dp"
  >                 android:layout_weight="1"
  >                 android:text="类别二"
  >                 android:textColor="@color/black"
  >                 android:textSize="20dp" />
  > 
  >             <RadioButton
  >                 android:id="@+id/rb_third"
  >                 android:layout_width="wrap_content"
  >                 android:layout_height="0dp"
  >                 android:layout_weight="1"
  >                 android:text="类别三"
  >                 android:textColor="@color/black"
  >                 android:textSize="20dp" />
  >         </RadioGroup>
  > 
  >         <TextView
  >             android:id="@+id/tv_radio_result"
  >             android:layout_width="wrap_content"
  >             android:layout_height="wrap_content"
  >             android:layout_alignParentRight="true"
  >             android:layout_centerVertical="true"
  >             android:layout_marginLeft="20dp"
  >             android:layout_marginRight="40dp"
  >             android:layout_toRightOf="@+id/radio_group"
  >             android:gravity="center"
  >             android:text="您选择了类别一"
  >             android:textColor="@color/black"
  >             android:textSize="20dp" />
  >     </RelativeLayout>
  > </LinearLayout>
  > ```

## 5.3 文本输入

### 5.3.1 编辑框EditText

- 用于TextView已有的各种属性外，还有：

  1. inputType：指定输入的文本类型。输入类型的取值说明见表，若同时使用多种文本类型，则可使用竖线“|”把多种文本类型拼接起来。

     - | 输入类型 | 说明 |
       | --- | --- |
        |text |文本|
        |textPassword |文本密码。显示时用圆点“·”代替|
        |number |整型数|
        |numberSigned |带符号的数字。允许在开头带负号“－”|
        |numberDecimal |带小数点的数字|
        |numberPassword |数字密码。显示时用圆点“·”代替|
        |datetime |时间日期格式。除了数字外，还允许输入横线、斜杆、空格、冒号|
        |date |日期格式。除了数字外，还允许输入横线“-”和斜杆“/”|
        |time |时间格式。除了数字外，还允许输入冒号“:”|

  2. maxLength：指定文本允许输入的最大长度。

  3. hint：指定提示文本的内容。

  4. textColorHint：指定提示文本的颜色。

- > ```xml
  > <?xml version="1.0" encoding="utf-8"?>
  > <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
  >     android:layout_width="match_parent"
  >     android:layout_height="match_parent"
  >     android:orientation="vertical"
  >     android:padding="10dp">
  > 
  >     <TextView
  >         android:id="@+id/tv_edit_info"
  >         android:layout_width="match_parent"
  >         android:layout_height="wrap_content"
  >         android:background="@drawable/button_pressed"
  >         android:gravity="center"
  >         android:text="Log in..."
  >         android:textColor="@color/black"
  >         android:textSize="30dp" />
  > 
  >     <EditText
  >         android:layout_width="match_parent"
  >         android:layout_height="wrap_content"
  >         android:layout_margin="10dp"
  >         android:hint="请输入用户名."
  >         android:inputType="text"
  >         android:paddingLeft="20dp"
  >         android:textColor="@color/black"
  >         android:textColorHint="@color/gray" />
  > 
  > 
  >     <EditText
  >         android:layout_width="match_parent"
  >         android:layout_height="wrap_content"
  >         android:hint="请输入密码."
  >         android:inputType="numberPassword"
  >         android:paddingLeft="20dp"
  >         android:layout_margin="10dp"
  >         android:textColor="@color/black"
  >         android:textColorHint="@color/gray" />
  > 
  >     <EditText
  >         android:layout_width="match_parent"
  >         android:layout_height="wrap_content"
  >         android:layout_margin="10dp"
  >         android:background="@null"
  >         android:hint="没有边框的编辑框"
  >         android:inputType="text"
  >         android:paddingLeft="20dp"
  >         android:textColor="@color/black"
  >         android:textColorHint="@color/gray" />
  > 
  >     <EditText
  >         android:layout_width="match_parent"
  >         android:layout_height="wrap_content"
  >         android:layout_margin="10dp"
  >         android:background="@drawable/editext_selector"
  >         android:hint="圆角边框的编辑框"
  >         android:inputType="text"
  >         android:paddingLeft="20dp"
  >         android:textColor="@color/black"
  >         android:textColorHint="@color/gray" />
  > 
  > </LinearLayout>
  > ```

### 5.3.2 焦点变更监听

- 编辑框比较特殊，**要点击两次后才会触发点击事件**，因为第一次点击只触发焦点变更事件，第二次点击才触发点击事件。编辑框的所谓焦点，直观上就看那个闪动的光标，哪个编辑框有光标，焦点就落在哪里。光标在编辑框之间切换，便产生了焦点变更事件，所以对于编辑框来说，应当注**册焦点变更监听器**，而非注册点击监听器。

- > ```java
  > et_password.setOnFocusChangeListener(this);
  > 
  >     @Override
  >     public void onFocusChange(View v, boolean hasFocus) {
  >         String phoneNum = et_phone.getText().toString();
  >         if (TextUtils.isEmpty(phoneNum) || phoneNum.length() < 11) {
  >             et_phone.requestFocus();
  >             Toast.makeText(this, "请输入11位手机号码", Toast.LENGTH_SHORT).show();
  >             tv_edit_judge.setText("请输入11位手机号码");
  >         } else {
  >             tv_edit_judge.setText("请输入密码");
  >         }
  >     }
  > ```

### 5.3.3 文本变化监听

#### 1. 关闭软键盘

> /util/ViewUtil.java
>
> ```java
> package com.example.chapter05.util;
> 
> import android.app.Activity;
> import android.content.Context;
> import android.view.View;
> import android.view.inputmethod.InputMethodManager;
> 
> public class ViewUtil {
>     public static void hideOneInputMethod(Activity act, View v) {
>         // 从系统服务中获取输入法管理器
>         InputMethodManager imm = (InputMethodManager)act.getSystemService(Context.INPUT_METHOD_SERVICE);
>         // 关闭屏幕上的输入法软键盘
>         imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
>     }
> }
> ```

#### 2. 判断输入文字达到指定位数

> ```java
>  protected void onCreate(Bundle savedInstanceState) {
>         super.onCreate(savedInstanceState);
>         setContentView(R.layout.activity_edit_keyboard);
>         // 从布局文件中获取名为et_phone的手机号码编辑框
>         EditText et_phone = findViewById(R.id.et_phone);
>         // 从布局文件中获取名为et_password的密码编辑框
>         EditText et_password = findViewById(R.id.et_password);
>         // 给手机号码编辑框添加文本变化监听器
>         et_phone.addTextChangedListener(new HideTextWatcher(et_phone, 11));
>         // 给密码编辑框添加文本变化监听器
>         et_password.addTextChangedListener(new HideTextWatcher(et_password, 6));
>     }
> 
>     // 定义一个编辑框监听器，在输入文本达到指定长度时自动隐藏输入法
>     private class HideTextWatcher implements TextWatcher {
>         private EditText mView; // 声明一个编辑框对象
>         private int mMaxLength; // 声明一个最大长度变量
> 
>         public HideTextWatcher(EditText v, int maxLength) {
>             super();
>             mView = v;
>             mMaxLength = maxLength;
>         }
> 
>         // 在编辑框的输入文本变化前触发
>         public void beforeTextChanged(CharSequence s, int start, int count, int
>                 after) {}
> 
>         // 在编辑框的输入文本变化时触发
>         public void onTextChanged(CharSequence s, int start, int before, int count) {}
> 
>         // 在编辑框的输入文本变化后触发
>         public void afterTextChanged(Editable s) {
>             String str = s.toString(); // 获得已输入的文本字符串
>             // 输入文本达到11位（如手机号码），或者达到6位（如登录密码）时关闭输入法
>             if ((str.length() == 11 && mMaxLength == 11)
>                     || (str.length() == 6 && mMaxLength == 6)) {
>                 ViewUtil.hideOneInputMethod(EditKeyboardActivity.this, mView); // 隐藏输入法软键盘
>             }
>         }
>     }
> ```

## 5.4 对话框

### 5.4.1 提醒对话框AlertDialog

- 借助建造器AlertDialog.Builder
  - 常用方法：
    1. setIcon：设置对话框的标题图标。
    2. setTitle：设置对话框的标题文本。
    3. setMessage：设置对话框的内容文本。
    4. setPositiveButton：设置肯定按钮的信息，包括按钮文本和点击监听器。
    5. setNegativeButton：设置否定按钮的信息，包括按钮文本和点击监听器。
    6. setNeutralButton：设置中性按钮的信息，包括按钮文本和点击监听器，该方法比较少用。
    7. 设置参数后，还需调用建造器的create方法生成对话框示例 —> show() 显示

```java
	{...	
		findViewById(R.id.btn_edit_alert).setOnClickListener(this);
        tv_edit_judge = findViewById(R.id.tv_edit_judge);
    }

    @Override
    public void onClick(View v) {
        // 创建提醒对话框的建造器
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("尊敬的用户"); // 设置对话框的标题文本
        builder.setMessage("你真的要卸载我吗？"); // 设置对话框的内容文本
        // 设置对话框的肯定按钮文本及其点击监听器
        builder.setPositiveButton("残忍卸载", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tv_edit_judge.setText("虽然依依不舍，但是只能离开了");
            }
        });
        // 设置对话框的否定按钮文本及其点击监听器, lambda表达式
        builder.setNegativeButton("我再想想", (dialog, which) -> {
            tv_edit_judge.setText("让我再陪你三百六十五个日夜")
        });
        AlertDialog alert = builder.create(); // 根据建造器构建提醒对话框对象
        alert.show(); // 显示提醒对话框
    }
```

### 5.4.2 日期对话框DatePickerDialog

- DatePicker并非弹窗模式，而是在当前页面占据一块区域，并且不会自动关闭。—— 日期控件应该弹出对话框，选择完日期就要自动关闭对话框。因此，利用已经封装好的日期选择对话框DatePickerDialog！

- 编码时只需调用构造方法设置当前的年、月、日，然后调用show方法即可弹出日期对话框。日期选择事件则由监听器OnDateSetListener负责响应，在该监听器的onDateSet方法中，开发者获取用户选择的具体日期，再做后续处理。

- 特别注意onDateSet的月份参数，它的起始值不是1而是0。也就是说，一月份对应的参数值为0，十二月份对应的参数值为11，中间月份的数值以此类推。

- `datePicker` :

  - datePiclerMode : 

    - spinner

      ![image-20230223103245537](https://s2.loli.net/2023/02/23/hdP5WZsHqoTzBgb.png)

    - spinner & calendarViewShown="false"

      ![image-20230223103438094](https://s2.loli.net/2023/02/23/EglmyQfpN8SIBaL.png)

    - calendar

      ![image-20230223103330749](https://s2.loli.net/2023/02/23/qgnoFcC6E7dxj5u.png)

- DatePickerDialog

  - 日期选择监听器为DatePickerDialog.OnDateSetListener，对应实现的方法为onDateSet

  - > ```java
    > public void onClick(View v) {
    > 	case R.id.btn_choose_date:
    >         // 获取日历的一个实例，里面包含了当前的年月日
    >         Calendar calendar = Calendar.getInstance();
    >         // 构建一个日期对话框，该对话框已经集成了日期选择器。
    >         // DatePickerDialog的第二个构造参数指定了日期监听器
    >         DatePickerDialog dialog = new DatePickerDialog(this, this,
    >                 calendar.get(Calendar.YEAR), // 年份
    >                 calendar.get(Calendar.MONTH), // 月份
    >                 calendar.get(Calendar.DAY_OF_MONTH)); // 日子
    >         dialog.show(); // 显示日期对话框
    >         break;
    > }
    > 
    > @Override
    > // 重写DatePickerDialog listener的onDateSet方法 (DatePickerDialog.OnDateSetListener接口)
    >     public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
    >         String desc = String.format("选择的日期为：%d年%d月%d日", year, month + 1, dayOfMonth);
    >         tv_calender.setText(desc);
    >     }
    > ```

### 5.4.3 时间对话框TimePickerDialog

- 构造方法传的是当前的小时与分钟，最后一个参数表示是否采取24小时制，一般为true表示小时的数值范围为0～23；若为false则表示采取12小时制。

- 时间选择监听器为OnTimeSetListener，对应需要实现onTimeSet方法

  - TimePicker

    - > 设置24小时制
      >
      > ```java
      > tp_taker = findViewById(R.id.tp_taker);
      > tp_taker.setIs24HourView(true);
      > ```

  - TimePickerDialog

    - > ```java
      > case R.id.btn_choose_time:
      >                 // 获取日历的一个实例，里面包含了当前的时分秒
      >                 Calendar calendar = Calendar.getInstance();
      >                 // 构建一个时间对话框，该对话框已经集成了时间选择器。
      >                 // TimePickerDialog的第二个构造参数指定了时间监听器
      >                 TimePickerDialog dialog = new TimePickerDialog(this, this,
      >                         calendar.get(Calendar.HOUR_OF_DAY), // 小时
      >                         calendar.get(Calendar.MINUTE), // 分钟
      >                         true); // true表示24小时制，false表示12小时制
      >                 dialog.show(); // 显示时间对话框
      >                 break;
      > ```

## 5.5 找回密码(训练)
