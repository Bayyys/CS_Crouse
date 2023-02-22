# 0. 课程导入

## 课程资源

- 课程网站：[Android基础教程](https://www.bilibili.com/video/BV19U4y1R7zV?p=1&vd_source=2228fbb3090a9774de8d595d37290e9e)

## Skills

`Alt+Enter`: 代码提示修正方案

`Ctrl+Alt+L`: 代码格式化

`Ctrl+Alt+F`: 自动生成static

`Ctrl+Alt+O`: 自动导入相关包

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
| layout_alignParentLeft   | 当前视图在上级视图的左侧对齐     |
| layout_alignParentRight  | 当前视图在上级视图的右侧对齐     |
| layout_alignParentTop    | 当前视图在上级视图的顶部对齐     |
| layout_alignParentBottom | 当前视图在上级视图的底部对齐     |

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