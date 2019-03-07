/*
Title: leCloudPlay
Description: leCloudPlay
*/

<p style="color: #ccc; margin-bottom: 30px;">来自于：开发者</p>


<div class="outline">
[playLeCloudActivity](#a1)

[playLeCloudVod](#a2)

[playUrl](#a3)

[start](#a4)

[pause](#a5)

[stop](#a6)

[addEventListener](#a7)

[uploader](#a8)

[setSensor](#a9)
</div>

#**概述**

**乐视云直播播放器接入**

开发者在使用leCloudPlay模块时，需要开发者自行到乐视云后台管理平台进行终端播放器接入的授权操作。由于乐视播放器sdk中采集了IDFA标示，所以在集成了本模块后，苹果上架需要设置IDFA。


##申请步骤
1、登录乐视云帐号

访问[微视频云](http://uc.lecloud.com/login.do)控制台页面，若您未登录账号，将会进入账号登录页面， 如下图：

![描述](http://community.apicloud.com/bbs/data/attachment/forum/201608/01/170333lpiib89lb2lbi92b.png)

2、进入帐号管理，进行资质认证，具体如下图：
![描述](http://community.apicloud.com/bbs/data/attachment/forum/201608/01/170334t229j4jijqe6yj99.png)

3、认证通过后，点击终端接入，进入后创建终端接入
点击"创建终端接入"，系统将为您弹出需要填写app应用的相关信息，具体如下图：
![描述](http://community.apicloud.com/bbs/data/attachment/forum/201608/27/164842w9vdqkbqq1cb49yq.png)

![描述](http://community.apicloud.com/bbs/data/attachment/forum/201608/27/164845pz4i4lvi0qttm7q0.png)


**模块使用攻略**

**使用此模块之前必须先配置  [config.xml] 文件，配置完毕，需通过云端编译生效，配置方法如下：**
- 名称：leCloudPlay
- 参数：uuid、secretKey
- 配置示例:

```js
<feature name="leCloudPlay">
<param name="uuid" value="uuid"/>
<param name="secretKey" value="私钥"/>
</feature>
```

- 字段描述:
	**uuid**：（必须配置）用户id，在乐视官方后台用户中心中获取uuid。
	**secretKey**：（必须配置）私钥，在乐视官方后台用户中心中获取私钥。
	
**全屏切换注意**
全屏切换时，请使用apicloud的api.setScreenOrientation调用方式，切换app横竖屏，切换后，视频会自动进行全屏和窗口间的切换


**标准直播播放**
在乐视后台的标准主播管理界面，获取需要直播播放的“活动ID”，调用playLeCloudActivity接口进行直播播放。


**移动直播播放**

获取leCloudLive模块的createPushUrl接口生成的移动直播播放地址，调用playUrl接口进行播放。


**点播播放**

在乐视后台的点播播放界面，在视频管理中获取需要点播的视频的“视频ID”和“视频VU”，调用playLeCloudVod接口进行点播播放。


##**模块接口**


<div id="a1"></div>
#**playLeCloudActivity**

标准直播播放

playLeCloudActivity({params}, callback(ret, err))

##params

pageType：

- 类型：字符串
- 描述：（必输项）播放器当前挂载的页面类型,openWin:'Window',openFrame:'Frame';(备注：在win和frame上面进行全屏控制实现不一样，win要流畅很多，frame全屏时会卡，请注意。);(该参数仅安卓有效)
- 默认值：Frame

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
- 默认值：250

ActivityId：

- 类型：字符串
- 描述：（必填项）标准直播活动id，在乐视标准直播后台管理中获取。

hasSkin：

- 类型：布尔型
- 描述：（可选项）是否开启皮肤模式
- 默认值：true（有皮肤）


hasTopView：

- 类型：布尔型
- 描述：（可选项）是否显示顶部菜单(该参数仅在 hasSkin:true 有效)
- 默认值：true（显示顶部菜单）


hasBackBtn：

- 类型：布尔型
- 描述：（可选项）是否开启全屏模式下的返回按钮(该参数仅在 hasSkin:true 和 hasTopView:true 有效)
- 默认值：false（隐藏全屏下的返回按钮）

hasStartBtn：

- 类型：布尔型
- 描述：（可选项）是否显示开始和暂停按钮(仅安卓有效)
- 默认值：true（显示开始和暂停按钮）

isSensor：

- 类型：布尔型
- 描述：（可选项）是否开启重力感应 （仅UI界面下有效）
- 默认值：false（关闭重力感应）


fixedOn：

- 类型：字符串
- 描述：（可选项）模块所属 Frame 的名字，若不传则模块归属于当前 Window

fixed：

- 类型：布尔型
- 描述：（可选项）模块是否随所属 Window 或 Frame 滚动
- 默认值：true（不随之滚动）




##示例代码

```js
var demo = api.require('leCloudPlay');
demo.playLeCloudActivity({
    pageType: 'Frame',
    x: 0,
    y: 0,
    h: 250,
    ActivityId: "A2016080500003gk",
    isSensor: false,
    hasSkin: true,
    fixedOn: api.frameName,
    fixed: true
},
function(ret, err) {
    //alert(JSON.stringify(ret) + "   " + JSON.stringify(err));
});
```

##callback(ret, err)

ret：

- 类型：JSON对象
- 内部字段：

```js
{
    status: true//布尔型；true||false
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
IOS、Android系统

可提供的1.0.0及更高版本


<div id="a2"></div>
#**playLeCloudVod**

标准点播播放

playLeCloudVod({params}, callback(ret, err))

##params

pageType：

- 类型：字符串
- 描述：（必输项）播放器当前挂载的页面类型,openWin:'Window',openFrame:'Frame';(备注：在win和frame上面进行全屏控制实现不一样，frame模式下全屏切换进度不能保持，会从头开始播放，请注意。);(该参数仅安卓有效)
- 默认值：Frame

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
- 默认值：250

uuid：

- 类型：字符串
- 描述：（必填项）用户的uuid，请在乐视标后台用户私钥中获取。(该参数可以在config.xml中配置，此处接口设置可省略)

vuid：

- 类型：字符串
- 描述：（必填项）标准点播的vuid，请在乐视标准点播的后台中获取。

puid：

- 类型：字符串
- 描述：（必填项）播放器的puid，请在乐视播放器配置界面中获取。

hasSkin：

- 类型：布尔型
- 描述：（可选项）是否开启皮肤模式
- 默认值：true（有皮肤）


hasTopView：

- 类型：布尔型
- 描述：（可选项）是否显示顶部菜单(该参数仅在 hasSkin:true 有效)
- 默认值：true（显示顶部菜单）


hasBackBtn：

- 类型：布尔型
- 描述：（可选项）是否开启全屏模式下的返回按钮(该参数仅在 hasSkin:true 和 hasTopView:true 有效)
- 默认值：false（隐藏全屏下的返回按钮）


hasStartBtn：

- 类型：布尔型
- 描述：（可选项）是否显示开始和暂停按钮(仅安卓有效)
- 默认值：true（显示开始和暂停按钮）

autoPlay：

- 类型：布尔型
- 描述：（可选项）视频加载成功后是否自动播放
- 默认值：true（自动播放）


isSensor：

- 类型：布尔型
- 描述：（可选项）是否开启重力感应 （仅UI界面下有效）
- 默认值：false（关闭重力感应）

fixedOn：

- 类型：字符串
- 描述：（可选项）模块所属 Frame 的名字，若不传则模块归属于当前 Window

fixed：

- 类型：布尔
- 描述：（可选项）模块是否随所属 Window 或 Frame 滚动
- 默认值：true（不随之滚动）


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
var demo = api.require('leCloudPlay');
demo.playLeCloudVod({
    pageType: 'Frame',
    x: 0,
    y: 50,
    h: 250,
    uuid: "8sowghkzbp",
    vuid: "6901d5904b",
    isSensor: false,
    hasSkin: true,
    fixedOn: api.frameName,
    fixed: true
},
function(ret, err) {
    //alert(JSON.stringify(ret) + "   " + JSON.stringify(err));
});
```

##可用性

IOS、Android系统

可提供的1.0.0及更高版本


<div id="a3"></div>
#**playUrl**

移动直播播放

playUrl({params}, callback(ret, err))

##params

pageType：

- 类型：字符串
- 描述：（必输项）播放器当前挂载的页面类型,openWin:'Window',openFrame:'Frame';(备注：在win和frame上面进行全屏控制实现不一样，当播放的url不是直播流，在frame模式下全屏切换进度不能保持，会从头开始播放，请注意。);(该参数仅安卓有效)
- 默认值：Frame

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
- 默认值：250

uuid：

- 类型：字符串
- 描述：（必填项）用户的uuid，请在乐视标后台用户私钥中获取。(该参数可以在config.xml中配置，此处接口设置可省略)

playUrl：

- 类型：字符串类型
- 描述：（必填项）直播播放地址,rtmp://格式 (备注：仅支持乐视直播地址，参考在模块leCloudLive中获取播放的地址)。

fixedOn：

- 类型：字符串
- 描述：（可选项）模块所属 Frame 的名字，若不传则模块归属于当前 Window

fixed：

- 类型：布尔
- 描述：（可选项）模块是否随所属 Window 或 Frame 滚动
- 默认值：true（不随之滚动）


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
var demo = api.require('leCloudPlay');
demo.playUrl({
    pageType: 'Frame',
    x: 0,
    y: 50,
    h: 250,
    playUrl: "rtmp://7936.mpull.live.lecloud.com/live/testpush?&tm=20160801130626&sign=5086bc042a26eeffa84ad8de54b6f001",
    fixedOn: api.frameName,
    fixed: true
},
function(ret, err) {
    //alert(JSON.stringify(ret) + "   " + JSON.stringify(err));
});
```

##可用性

IOS、Android系统

可提供的1.0.0及更高版本


<div id="a4"></div>
#**start**

开始播放

start(callback(ret, err))


##callback(ret, err)

ret：

- 类型：JSON对象
- 内部字段：

```js
{
    status: true,
    //布尔型；true||false
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
var demo = api.require('leCloudPlay');
demo.start(function(ret, err) {
    alert("start>>" + JSON.stringify(ret) + "   " + JSON.stringify(err));
});
```
##可用性

IOS、Android系统

可提供的1.0.0及更高版本


<div id="a5"></div>
#**pause**

暂停播放

pause(callback(ret, err))


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
var demo = api.require('leCloudPlay');
demo.pause(function(ret, err) {
    alert("pause>>" + JSON.stringify(ret) + "   " + JSON.stringify(err));
});
```

##可用性

IOS、Android系统

可提供的1.0.0及更高版本


<div id="a6"></div>
#**stop**

暂停直播

stop(callback(ret, err))

##示例代码

```js
var demo = api.require('leCloudPlay');
demo.stop(function(ret, err) {
    alert("stop>>" + JSON.stringify(ret) + "   " + JSON.stringify(err));
});
```

##可用性
IOS、Android系统

可提供的1.0.0及更高版本


<div id="a7"></div>
#**addEventListener**

播放器相关监听

addEventListener(callback(ret, err))

##params

name：

- 类型：字符串
- 描述：（必填项）监听事件名称,参数规则如下:fullChange(全屏切换事件监听)，backBtn(有皮肤播放器顶部左上角返回按钮事件监听,注意:ios全屏无返回按钮)，play(开始播放事件监听)，playEnd(播放结束事件监听)


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
demo.addEventListener({
    name: "fullChange"
},
function(ret, err) {
    alert("fullChange>>" + JSON.stringify(ret) + "   " + JSON.stringify(err));
});
demo.addEventListener({
    name: "backBtn"
},
function(ret, err) {
    alert("backBtn>>" + JSON.stringify(ret) + "   " + JSON.stringify(err));
});
demo.addEventListener({
    name: "play"
},
function(ret, err) {
    alert("play>>" + JSON.stringify(ret) + "   " + JSON.stringify(err));
});
demo.addEventListener({
    name: "playEnd"
},
function(ret, err) {
    alert("playEnd>>" + JSON.stringify(ret) + "   " + JSON.stringify(err));
});
```

##可用性
IOS、Android系统

可提供的1.0.0及更高版本

<div id="a8"></div>
#**uploader**

上传视频文件到乐视平台(支持断点续传)

uploader({params}, callback(ret, err))

##params

uuid：

- 类型：字符串
- 描述：（必填项）用户的uuid，请在乐视标后台用户私钥中获取。(该参数可以在config.xml中配置，此处接口设置可省略)

secretKey：

- 类型：字符串
- 描述：（必填项）用户私钥，请在乐视标后台用户私钥中获取。(该参数可以在config.xml中配置，此处接口设置可省略)

token：

- 类型：字符串
- 描述：（非必填）断点续传的token，在第一次上传接口中返回。

uploadFile：

- 类型：字符串型
- 描述：（必填项）上传视频的文件地址：ios支持 widget:// fs://、安卓支持 fs://或者直接传转换后的文件物理路径。


##callback(ret, err)

ret：

- 类型：JSON对象
- 内部字段：

```js
{
	status: true,   //布尔型；true||false,token,progress
	progress:92,     //上传视频进百分比 
	uploadSpeed:1231 , //上传速度
	token:'',        //用于断点续传的token
	msg:''           //上传成功message
}
```

err：

- 类型：JSON对象
- 内部字段：

```js
{
	code: "",    //上传视频错误代码
	msg: ""      //上传视频错误信息
}
```

##示例代码

```js
var demo = api.require('leCloudPlay');
demo.uploader({
	uuid : "uuid",
	secretKey : "secretKey",
	token:"token",
	uploadFile : "widget://res/test1.mp4"
}, function(ret, err) {
	if (ret.progress) {
		api.toast({
			msg : JSON.stringify(ret)
		});
	} else {
		alert("stop>>" + JSON.stringify(ret) + "   " + JSON.stringify(err));
	}
});
```

##可用性

IOS、Android系统

可提供的1.0.0及更高版本


<div id="a9"></div>
#**setSensor**

开启或者关闭重力感应

setSensor({params}, callback(ret, err))

##params

isSensor：

- 类型：布尔
- 描述：（可选项）是否开启重力感应 （仅UI界面下有效）
- 默认值：false（不开启）

##示例代码

```js
var demo = api.require('leCloudPlay');
demo.setSensor({
	isSensor : true,
},function(ret, err) {
    alert("stop>>" + JSON.stringify(ret) + "   " + JSON.stringify(err));
});
```

##可用性
IOS、Android系统

可提供的1.0.0及更高版本
