/*
Title: leCloudLive
Description: leCloudLive
*/

<p style="color: #ccc; margin-bottom: 30px;">来自于：开发者</p>

<div class="outline">
[createPushUrl](#a1)

[customInitLive](#a2)

[customStartPushUrl](#a3)

[getLiveMachines](#a4)

[customStartCloudLive](#a5)

[customPuaselive](#a6)

[customStoplive](#a7)

[setTorchOpenState](#a8)

[setMute](#a9)

[setCamare](#a10)

[setFilter](#a11)
</div>

#**概述**

**乐视云直播接入**

开发者在使用leCloudLive模块时，需要开发者自行到乐视云申请相应的AppKey。

##申请步骤
1、登录乐视云帐号

访问[微视频云](http://uc.lecloud.com/login.do)控制台页面，若您未登录账号，将会进入账号登录页面, 如下图：

![描述](http://community.apicloud.com/bbs/data/attachment/forum/201608/01/170333lpiib89lb2lbi92b.png)

2、进入帐号管理，进行资质认证，具体如下图：
![描述](http://community.apicloud.com/bbs/data/attachment/forum/201608/01/170334t229j4jijqe6yj99.png)

3、认证通过后，点击我的服务，进入“移动直播”管理平台创建应用
点击"创建应用"，系统将为您弹出需要填写应用的相关信息，具体如下图：
![描述](http://community.apicloud.com/bbs/data/attachment/forum/201608/01/170337qv3xc11cz5bca1c1.png)

4、获取AppKey和推流域名、播放域名

注册成功后，在应用管理里面“密钥”处查看AppKey和推流域名、播放域名，具体如下图：
![描述](http://community.apicloud.com/bbs/data/attachment/forum/201608/01/170337t88wftvre66rew7p.png)

5、获取用户id和私钥，用于云直播配置

注册成功后，在用户私钥里面获取用户id和私钥，具体如下图：
![描述](http://community.apicloud.com/bbs/data/attachment/forum/201608/01/182819nbixzkkvzetdxxid.png)

6、视频分辨率,总码率（kbps）参考值。

信号源 | 视频分辨率 | 总码率（kbps）
-----|------|----
高清    | 1920*1080P    | 5000
    | 1280*720P    | 1800
    | 960*540P    | 1300
    | 960*540P    | 1300
    | 640*360P    | 800
    | 640*360P    | 350
标清    | 720*576P   | 1800
    | 720*576P    | 1300
    | 720*576P    | 800
    | 640*480P    | 350


7、iOS常见错误码。

错误码 | 错误描述
-----|----------
-1 |	未知错误
120 |	打开摄像头失败(无权限)
121 |	打开摄像头失败(参数错误)
123 |	打开麦克风失败(无权限)
124 |	打开麦克风失败(家长控制)
125 |	打开闪光灯失败(没有闪光灯)
126 |	打开闪关灯失败(设备异常)
129 |	切换摄像头失败(设备异常)
130 |	编码器打开失败
131 |	网络连接失败(超时导致)
132 |	不是标准的rtmp地址
133 |	直播时间未到
134 |	直播已结束
140 |	网络断开导致推流断开
141 |	网络较差导致推流断开
142 |	其他错误原因
201 |	连接服务器错误
202 |	云直播接口错误
203 |	云直播网络错误
204 |	socket错误码
205 |	RTMP错误码
206 |	网络状态由WIFI变成移动网络


8、Android常见错误码。

错误码 | 错误描述
-----|----------
-1 |	未知错误
100 |	RMTP链接建立不成功
101 |	推流连接成功:只有当连接成功以后才能开始推流
102 |	推流失败,原因:网络较差,编码出错,推流崩溃,第一针数据发送失败...等等各种原因导致
104 |	第一帧数据发送成功，推流成功的标志
107 |	视频出现丢帧现象。如果一分钟丢帧次数大于5次,导致声音跳动:可以对网络进行判定
108 |	音频出现丢帧现象,如果一分钟丢帧次数大于5次,导致画面跳动:可以对网络进行判定
110 |	推流已成功停止
111 |	可以再次切换摄像头了：缩短切换摄像头需要10延时的问题，可以通过这个事件判断是否可以再次切换摄像头
112 |	摄像头操作异常
113 |	麦克风操作异常
114 |	成功打开摄像头
115 |	成功打开麦克风
116 |	闪光灯操作异常
117 |	成功打开闪关灯
1001 |	直播未开始
1003 |	当前直播剩余分钟数
1004 |	直播已结束(如果活动不可用，则会回调这个信息)
1005 |	未开启/开启直播录制功能
1006 |	直播已结束(如果推流完成，则会回调这个信息)
1007 |	其他错误信息


**模块使用攻略**

**使用此模块之前必须先配置  [config.xml] 文件，配置完毕，需通过云端编译生效，配置方法如下：**
- 名称：leCloudLive
- 参数：mpushDomain、mpullDomain、appKey、userId、secretKey
- 配置示例:

```js
<feature name="leCloudLive">
<param name="mpushDomain" value="推流域名"/>
<param name="mpullDomain" value="播放域名"/>
<param name="appKey" value="乐视云appKey"/>
<param name="userId" value="用户id"/>
<param name="secretKey" value="私钥"/>
</feature>
```

- 字段描述:
	**mpushDomain**：（必须配置）推流域名，在乐视官方后台移动直播所创建的应用中获取推流域名。
	**mpullDomain**：（必须配置）播放域名，在乐视官方后台移动直播所创建的应用中获取播放域名。
	**appKey**：（必须配置）签名密钥，在乐视官方后台移动直播所创建的应用中获取签名密钥。
	**userId**：（必须配置）用户id，在乐视官方后台用户中心中获取用户ID。
	**secretKey**：（必须配置）私钥，在乐视官方后台用户中心中获取私钥。

**标准直播顺序**
1、初始化标准直播试图预览：customInitLive

2、获取标准直播机位信息：getLiveMachines

3、获取直播机位id进行标准直播：customStartCloudLive

**移动直播顺序**
1、生成推流端和播放端地址：createPushUrl

2、初始化移动直播试图预览：customInitLive

3、开始移动直播：customStartPushUrl


##**模块接口**


<div id="a1"></div>
#**createPushUrl**

生成推流、播放地址

createPushUrl({params}, callback(ret, err))

##params

streamName：

- 类型：字符串
- 描述：（必输项）申请直播流名称。流名称可以是任意数字、字母的组合


##示例代码

```js
var demo = api.require('leCloudLive');
demo.createPushUrl({
    streamName: 'testpush'
},
function(ret, err) {
    alert(JSON.stringify(ret) + "   " + JSON.stringify(err));
});
```

##callback(ret, err)

ret：

- 类型：JSON对象
- 内部字段：

```js
{
    status: true,//布尔型；true||false
    pushUrl: "",//推流地址
    playUrl: "" //播放地址
}
```

err：

- 类型：JSON对象
- 内部字段：

```js
{
	msg: ""
}
```


##可用性
iOS、Android系统

可提供的1.0.0及更高版本


<div id="a2"></div>
#**customInitLive**

直播初始化

customInitLive({params}, callback(ret, err))

##params

x：

- 类型：数字类型
- 描述：（必填项）模块左上角的 x 坐标（相对于所属的 Window 或 Frame）；
- 默认值：0

y：

- 类型：数字类型
- 描述：（必填项）模块左上角的 y 坐标（相对于所属的 Window 或 Frame）；默认：0
- 默认值：0

w：

- 类型：数字类型
- 描述：（可选项）模块的宽度；默认：所属的 Window 或 Frame 的宽度

h：

- 类型：数字类型
- 描述：（可选项）模块的高度；默认：所属的 Window 或 Frame 的高度
- 默认值：300


previewWidth：

- 类型：数字类型
- 描述：（可选项）摄像头预览分辨率的宽。


previewHeight：

- 类型：数字类型
- 描述：（可选项）摄像头预览分辨率的高。

frameRate：

- 类型：数字类型
- 描述：（可选项）推流的每秒的视频帧数。
- 默认值：24


bitrate：

- 类型：数字类型
- 描述：（可选项）推流的视频流比特率。
- 默认值：1000000


fixedOn：

- 类型：字符串
- 描述：（可选项）模块所属 Frame 的名字，若不传则模块归属于当前 Window

fixed：

- 类型：布尔
- 描述：（可选项）模块是否随所属 Window 或 Frame 滚动
- 默认值：true（不随之滚动）

isLandscape：

- 类型：布尔
- 描述：（可选项）是否横屏直播
- 默认值：false

isBackCamare：

- 类型：布尔
- 描述：（可选项）是否后置摄像头(true:后置|false:前置)
- 默认值：false


type：

- 类型：数字类型
- 描述：（可选项）1:移动直播 2:标准直播。
- 默认值：1


filterName：

- 类型：字符串
- 描述：（可选项）滤镜参数：美颜 温暖 平静 浪漫 正常。
- 默认值：正常



##callback(ret, err)

ret：

- 类型：JSON对象
- 内部字段：

```js
{
	status: true,  //布尔型；true||false
}
```


##示例代码

```js
var demo = api.require('leCloudLive');
demo.customInitLive({
    x: 0,
    y: 0,
    h: 250,
    fixedOn: api.frameName,
    fixed: true,
    previewWidth: 640,
    previewHeight: 480,
    isLandscape: false,
    filterName : "美颜",
    type : 2
},
function(ret, err) {});
```

##可用性

iOS、Android系统

可提供的1.0.0及更高版本


<div id="a3"></div>
#**customStartPushUrl**

开始移动直播

customStartPushUrl({params}, callback(ret, err))

##params

pushUrl：

- 类型：字符串类型
- 描述：（必填项）直播推流地址；

##callback(ret, err)

ret：

- 类型：JSON对象
- 内部字段：

```js
{
	status: true   //布尔型；true||false
}
```

err：

- 类型：JSON对象
- 内部字段：

```js
{
	msg: ""
}
```

##示例代码

```js
var demo = api.require('leCloudLive');
demo.customStartPushUrl({
    pushUrl: "rtmp://12345.mpush.live.lecloud.com/live/testpush?&tm=20160801130626&sign=5086bc02a26eeffa84ad8de54b6f003"
},
function(ret, err) {});
```

##可用性

iOS、Android系统

可提供的1.0.0及更高版本


<div id="a4"></div>
#**getLiveMachines**

获取云直播机位信息

getLiveMachines({params}, callback(ret, err))

##params

activityID：

- 类型：字符串类型
- 描述：（必填项）活动id；

##callback(ret, err)

ret：

- 类型：JSON对象
- 内部字段：

```js
{
    status: true,
    //布尔型；true||false
    data: [{
        machine: 1,//机位id
        status: true,//是否有效
        liveId: 3333,//直播id
        streamId: 5555 //直播流id
    },{
        machine: 2,//机位id
        status: true,//是否有效
        liveId: 1142333,//直播id
        streamId: 123123123 //直播流id
    }]
}
```

err：

- 类型：JSON对象
- 内部字段：

```js
{
	code: "",
	msg: ""
}
```

##示例代码

```js
var demo = api.require('leCloudLive');
demo.getLiveMachines({
    activityID: "A2016080100002ev"
},
function(ret, err) {});
```
##可用性

iOS、Android系统

可提供的1.0.0及更高版本


<div id="a5"></div>
#**customStartCloudLive**

开始云直播

customStartCloudLive({params}, callback(ret, err))

##params

machine：

- 类型：数字型
- 描述：（必填项）机位id；

##callback(ret, err)

ret：

- 类型：JSON对象
- 内部字段：

```js
{
	status: true   //布尔型；true||false
}
```

err：

- 类型：JSON对象
- 内部字段：

```js
{
	code: "",
	msg: ""
}
```

##示例代码

```js
var demo = api.require('leCloudLive');
demo.customStartCloudLive({
    machine: 1
},
function(ret, err) {});
```

##可用性

iOS、Android系统

可提供的1.0.0及更高版本


<div id="a6"></div>
#**customPuaselive**

暂停直播

customPuaselive(callback(ret, err))

##示例代码

```js
var obj = api.require('leCloudLive');
obj.customPuaselive(function(ret, err) {
    alert(JSON.stringify(ret));
});
```

##可用性
iOS、Android系统

可提供的1.0.0及更高版本


<div id="a7"></div>
#**customStoplive**

停止直播

customStoplive(callback(ret, err))

##示例代码

```js
var obj = api.require('leCloudLive');
obj.customStoplive(function(ret, err) {
    alert(JSON.stringify(ret));
});
```

##可用性
iOS、Android系统

可提供的1.0.0及更高版本

<div id="a8"></div>
#**setTorchOpenState**

闪光灯

setTorchOpenState(callback(ret, err))

##示例代码

```js
var obj = api.require('leCloudLive');
obj.setTorchOpenState(function(ret, err) {
    alert(JSON.stringify(ret));
});
```

##可用性
iOS、Android系统

可提供的1.0.0及更高版本


<div id="a9"></div>
#**setMute**

静音

setMute(callback(ret, err))

##示例代码

```js
var obj = api.require('leCloudLive');
obj.setMute(function(ret, err) {
    alert(JSON.stringify(ret));
});
```

##可用性
iOS、Android系统

可提供的1.0.0及更高版本


<div id="a10"></div>
#**setCamare**

摄像头

setCamare(callback(ret, err))

##示例代码

```js
var obj = api.require('leCloudLive');
obj.setCamare(function(ret, err) {
    alert(JSON.stringify(ret));
});
```

##可用性
iOS、Android系统

可提供的1.0.0及更高版本


<div id="a11"></div>
#**setFilter**

直播初始化

setFilter({params}, callback(ret, err))

##params

filterName：

- 类型：字符串
- 描述：（可选项）滤镜参数：美颜 温暖 平静 浪漫 正常。
- 默认值：正常


##callback(ret, err)

ret：

- 类型：JSON对象
- 内部字段：

```js
{
	status: true,  //布尔型；true||false
}
```


##示例代码

```js
var demo = api.require('leCloudLive');
demo.setFilter({
    filterName : "平静" //  美颜 温暖 平静 浪漫 正常
},
function(ret, err) {});
```

##可用性

iOS、Android系统

可提供的1.0.0及更高版本
