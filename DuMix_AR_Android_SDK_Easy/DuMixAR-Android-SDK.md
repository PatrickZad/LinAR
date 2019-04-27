
# Android SDK Easy版

# 简介

本文档主要介绍 DuMix AR Android SDK Easy版的集成和使用。在使用本文档前，您需要先了解AR（Augmented Reality）的基础知识，并已经开通了百度AR应用授权，您可以在 [AR技术开放平台](https://ar.baidu.com/developer) 的[应用控制台](https://ar.baidu.com/console#) 申请应用授权。

# 快速入门

支持的系统和硬件版本

- 系统：支持 Android 4.4（API Level 19）到Android 9（API Level 28）系统。需要开发者通过minSdkVersion来保证支持系统的检测。
- CPU架构：armeabi-v7a，arm64-v8a
- 硬件要求：要求设备上有相机模块，CPU 4核及以上，内存2G及以上。
- 网络：支持WIFI及移动网络，移动网络支持使用NET网关及WAP网关（CMWAP、CTWAP、UNIWAP、3GWAP）。

## 开发包说明

    DuMix_AR_Android_SDK_Easy.zip
        |- libs                           // lib库，包括jar包和so库
        |- res                            // Easy版相关资源
        |- DuMixARDemo                    // demo工程
        |- sample                         // 示例AR内容
        |- DuMixAR-Android-SDK.md         // 说明文档

SDK提供的demo工程以Android Studio方式提供。

# SDK集成步骤

## 第1步：导入jar包和so库
将解压后的 libs 文件夹中的 dumixar.jar 拷贝到您的工程的 libs 文件夹中，并检查编译依赖。
将解压后的 libs 文件夹中的arm64-v8a和armeabi-v7a文件夹拷贝到Android Studio工程`src/main/jniLibs`目录中。

## 第2步：导入资源
将解压后的 res 文件夹中的所有资源拷贝到您的工程中对应的资源文件夹中。

## 第3步：配置Mannifest文件，添加必要的权限

```
<uses-permission android:name="android.permission.CAMERA"/>
<uses-permission android:name="android.permission.INTERNET"/>
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
<uses-permission android:name="android.permission.VIBRATE"/>
<uses-permission android:name="android.permission.RECORD_AUDIO"/>

<uses-feature android:name="android.hardware.camera"/>
<uses-feature android:name="android.hardware.camera.autofocus"/>
```

各个权限的用途说明见下表：

| 名称                     | 用途                      |
| ---------------------- | ----------------------- |
| CAMERA                                   | 调用相机进行图像识别和跟踪       |
| INTERNET                                 | 应用联网，发送请求数据至服务器，获得图像识别结果、下载AR资源等。 |
| WRITE_EXTERNAL_STORAGE | AR资源存储                |
| ACCESS_NETWORK_STATE    | 获取网络状态              |
| ACCESS_WIFI_STATE              | 获取wifi状态                |
| VIBRATE                                    | 调用振动                     |
| RECORD_AUDIO                      | 调用麦克风进行录音                                      |
| android.hardware.camera                      | 使用设备的后置相机                         |
| android.hardware.camera.autofocus     | 使用设备相机支持的自动对焦功能   |

## 第4步：创建AR应用

请前往[百度云控制台 -> 百度AR](https://console.bce.baidu.com/ai/#/ai/ar/app/list)创建您的AR应用:

![](https://ai.bdstatic.com/file/BB44EDFEEA4C47FFA05BFA6B8337B62C)

## 第5步：初始化相关参数

创建完AR应用后，在“应用详情”页面下载license文件，并将license文件放到您应用的assets目录中（注意：切勿修改license文件名称：aip.license），例如：```/src/main/assets/```
初始化参数示例代码如下：

```
// 设置获取资源的上下文Context
Res.addResource(this);
// 设置App Id
DuMixARConfig.setAppId("您的App Id");
// 设置API Key
DuMixARConfig.setAPIKey("您的API Key");
// 设置Secret Key
DuMixARConfig.setSecretKey("您的Secret Key");
```

## 第6步：创建ARFragment

示例代码如下：（您可以参考demo中的示例）

```
ARFragment mARFragment；
Bundle data = new Bundle();
JSONObject jsonObj = new JSONObject();
try {
	jsonObj.put(ARConfigKey.AR_TYPE, "您创建的AR内容的ar_type");
	// 当加载云端AR内容时，需传入AR内容平台生成的ar_key
	jsonObj.put(ARConfigKey.AR_KEY, "您创建的AR内容的ar_key");
	// 当加载本地AR内容时，需传入ar_path
	jsonObj.put(ARConfigKey.AR_PATH, "您创建的AR内容的ar_path");
} catch (JSONException e) {
	e.printStackTrace();
}
data.putString(ARConfigKey.AR_VALUE, jsonObj.toString());
if (mARFragment != null) {
	mARFragment.release();
	mARFragment = null;
}
mARFragment = new ARFragment();
mARFragment.setArguments(data);
// 将ARFragment添加到布局上
fragmentTransaction.replace(R.id.bdar_id_fragment_container, mARFragment);
fragmentTransaction.commitAllowingStateLoss();
```

## 第7步：获取 AR Key 和 AR Type

完成以上6步，您已经完成DuMix AR SDK的集成。但想要预览AR内容，您还需要在 [AR内容平台](https://dumix.baidu.com/content) 上传您自己的AR内容（DuMix AR提供了sample case）。AR内容上传完成后，平台会分配相应的AR Key，您可以在“详情页面”查看AR Key和AR Type，将AR Key和AR Type传给ARFragment（参考第6步），运行App，您就可以预览AR内容了。

注：如果加载本地AR内容，则把AR内容包解压放到SD卡中，将路径通过AR Path传入即可，具体可参见AR Demo。

## 第8步：运行您的App

运行您的App即可预览AR内容。


# 语音功能的集成步骤

DuMix AR打通了和百度语音技术相关的接口，如果您想在AR内容中使用语音识别、语音合成等能力，在申请AR服务时勾选语音相关功能即可。

注1：语音相关功能是可选功能，您可以根据需要选择性集成。

注2：如果勾选语音识别功能，App即可获得语音SDK权限。

注3：关于百度语音SDK更详细的介绍，请参考[百度AI开放平台](https://ai.baidu.com) 中【语音技术】的相关章节。

## 第1步：配置Manifest文件

在您的App的AndroidManifest文件中加入如下代码：

```
<service
    android:name="com.baidu.speech.VoiceRecognitionService"
    android:exported="false"/>
```

## 第2步：导入jar包和so库

将语音SDK的相关jar包和so库拷贝到对应目录，并添加必要的编译依赖。这部分可参考DuMixARDemo。

# 如何使用DuMixARDemo

Andoird SDK提供了一个可快速运行的demo工程，该工程已经集成了SDK，您只需直接在Android Studio中导入DuMixARDemo并修改相应的参数即可运行。
步骤如下：

1. 设置相关参数，请您参考 **SDK集成步骤** 中的【第5步】。
2. 设置 AR Key 和 AR Type，请您参考 **SDK集成步骤** 中的【第7步】。
3. 修改 DuMixARDemo 的包名，如：在 Android Studio 环境修改 build.gradle 中的 applicationId。
4. 运行 DuMixARDemo，即可预览 AR 内容。

# 接口调用说明

## 初始化接口

* 调用示例

```
// 设置获取资源的上下文Context
Res.addResource(this);
// 设置您的App Id
DuMixARConfig.setAppId("您的App Id");
// 设置您的API Key
DuMixARConfig.setAPIKey("您的API Key");
// 设置Secret Key
DuMixARConfig.setSecretKey("您的Secret Key");
```
注：将license文件放到您应用的assets目录中。

## 功能接口

* 调用示例

```
mARFragment.setARCallbackClient(new ARCallbackClient() {
    // 分享接口
    @Override
    public void share(String title, String content, String shareUrl, String resUrl, int type) {
        // type = 1 视频，type = 2 图片
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, content);
        shareIntent.putExtra(Intent.EXTRA_TITLE, title);
        shareIntent.setType("text/plain");
        // 设置分享列表的标题，并且每次都显示分享列表
        startActivity(Intent.createChooser(shareIntent, "分享到"));
    }

    // 透传url接口：当AR Case中需要传出url时通过该接口传出url
    @Override
    public void openUrl(String url) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri contentUrl = Uri.parse(url);
        intent.setData(contentUrl);
        startActivity(intent);
    }

    // AR黑名单回调接口：当手机不支持AR时，通过该接口传入退化H5页面的url
    @Override
    public void nonsupport(String url) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri contentUrl = Uri.parse(url);
        intent.setData(contentUrl);
        startActivity(intent);
        ARActivity.this.finish();
    }
});
```

**接口说明**

1. share 是分享接口。参数说明：
        
		title-分享标题;
		content-分享内容;
		shareUrl-H5分享页面的url;
		resUrl-对应的图片或视频的url;
		type-分享的类型，1 为视频，2 为图片。
2. openUrl 是透传 url 接口，AR case 中需要打开浏览器时将 url 传出，供宿主实现相关方法。参数说明：
        
		url-需要透传的url;
3. nonsupport 是不支持AR的回调接口。
   参数说明：
		
		url-不支持AR时，H5退化页面的url;

## 拍摄结果处理接口

* 调用示例

```
mARFragment.setARCaptureResultCallback(new ARCaptureResultCallback(){
    @Override
    public void onPictureTaken(String filePath) {
        Toast.makeText(ARActivity.this, "picture filepath=" + filePath, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onVideoTaken(String filePath) {
        Toast.makeText(ARActivity.this, "video filepath=" + filePath, Toast.LENGTH_SHORT).show();
    }
});
```

**接口说明**

1. onPictureTaken 是拍摄结果处理接口。
	参数说明：
        
		filePath-拍摄的图片的本地路径。
2. onVideoTaken 是录制结果处理接口。
   参数说明：
        
		filePath-录制的视频的本地路径。


# Proguard配置

在Proguard配置文件中增加：

```-keep class com.baidu.ar.*{*;} ```

```-keep class com.baidu.aip.*{*;} ```

# 注意事项

1. DuMix AR是一个全屏幕的功能，需要您结合自己 APP 的使用场景进行开发。
2. DuMix AR需要打开相机，所以在调起 DuMix AR 前请确保您的 APP 已经关闭了相机。
3. DuMix AR引擎正常运行依赖必要的硬件基础，因此对硬件有一定的要求，对不满足的手机采用“黑名单”策略，也就是命中“黑名单”的手机会被屏蔽，不能正常使用DuMix AR。屏蔽依据的参数主要有内存大小，CPU核数，安卓版本等等手机基本信息；还有针对特殊机型的屏蔽，依据手机型号。
4. DuMix AR中的资源名（包括文件名和 ID）均不可以修改。
5. 请将DuMix AR集成在竖屏的Activity中，不要设置横屏。
6. DuMix AR SDK的 minSdkVersion为 19；targetSdkVersion 建议设为 24。

# 版本更新说明

**DuMix AR Android SDK Easy版2.6-2018年10月** 
引擎支持空间小游戏。

**DuMix AR Android SDK Easy版2.5-2018年08月**
提供加载本地AR内容等功能接口。

**DuMix AR Android SDK Easy版2.4.1-2018年07月**
修复已知问题。

**DuMix AR Android SDK Easy版2.4-2018年06月**
优化2D图像跟踪和本地识图算法。

**DuMix AR Android SDK Easy版2.3-2018年05月**
提供在线视频等功能。

**DuMix AR Android SDK Easy版2.2-2018年04月**
提供手势识别和LOGO识别等功能。

**DuMix AR Android SDK Easy版2.1-2018年03月**
提供滤镜等功能。

**DuMix AR Android SDK Easy版2.0-2017年11月**
提供单目SLAM、2D图像跟踪、本地识图、云端识图、语音识别和语音合成等功能。

