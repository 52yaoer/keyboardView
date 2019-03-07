/*
Title: playModule
Description: playModule
*/

<p style="color: #ccc;margin-bottom: 30px;">来自于：开发者</p>

<ul id="tab" class="clearfix">
	<li class="active"><a href="#method-content">Method</a></li>
</ul>
<div id="method-content">
<div class="outline">
[init](#a1)
[play](#a2)
[playUrl](#a3)
[pause](#a4)
[start](#a5)
[stop](#a6)
[getDuration](#a7)
[getCurrentPosition](#a8)
[full](#a9)
[unfull](#a10)
[addEventListener](#a11)
[isFullScreen](#a12)
[seekTo](#a13)
[setVolume](#a14)
[hidePlayer](#a15)
[showPlayer](#a16)
</div>

#**概述**

playModule 封装了视频播放功能（不支持音频播放）。使用本模块时可把本模块当做一个 frame 添加在 window 或 frame 上。Android 平台上支持的的视频文件格式有：MP4、3GP、FLV、RTMP、M3U8；IOS 平台上支持的视频文件格式有：MOV、MP4、M4V、FLV、ACC、3GP、RTMP、M3U8等  ; 

使用有ui方案播放器，可以通过左右滑动调节视频进度，左边上下滑动调节音量，右边上下滑动调节亮度。

**模块效果图**

![描述](http://community.apicloud.com/bbs/data/attachment/forum/201705/27/193750qbss3tkbfzl3bkm5.jpg)

![描述](http://community.apicloud.com/bbs/data/attachment/forum/201705/27/193754az792v2hx9v7a77n.jpg)

![描述](http://community.apicloud.com/bbs/data/attachment/forum/201705/27/193758zfgmd9xmtzzdd99v.jpg)

<div id="a1"></div>
#**init**

初始化视频播放配置

init({params}, callback(ret, err))

##params

ttf：

- 类型：字符串
- 描述：（可选项）设置标题显示文字字体文件路径，仅支持widget://


fullBtnPlan：

- 类型：数字型
- 描述：（可选项）全屏默认按钮方案；1:不包含下面2的按钮，2:包含清晰度，选集，下载，收藏按钮事件
- 默认值：1

logo：

- 类型：字符串
- 描述：（可选项）右上角设置logo(图片类型：png)；支持fs://,widget://

background：

- 类型：字符串
- 描述：（可选项）视频播放前的图片显示(图片类型：jpg,png,gif)；支持fs://,widget://


isImmerse：

- 类型：布尔型
- 描述：（可选项）在APP仅全屏播放有效；当为true时:顶部菜单自动加上顶部状态栏的高度，为false时:顶部菜单不加顶部状态栏高度。
- 默认值：true

FullScreenViewIsFont：

- 类型：布尔型
- 描述：（可选项）全屏view是否显示最上层,true:是|false:否，仅ios有效（为fasle时可通过openframe自定义按钮在播放器上层；注意，未开启沉侵式的App请不要设置该参数。）
- 默认值：true

##示例代码

```js
var playModule = api.require('playModule');
playModule.init({
    ttf: "widget://res/UKIJTor.ttf",
    logo:"widget://res/cklogo.png",
    fullBtnPlan : 2
});
```

##可用性
Android、iOS系统

可提供的1.0.0及更高版本

<div id="a2"></div>
#**play**

播放本地视频、网络视频、rtmp直播流等

play({params}, callback(ret, err))

##params

rect：

- 类型：JSON 对象
- 描述：（必填项）模块的位置及尺寸
- 备注：iOS,Android 必须传此参数。
- 内部字段：


```js
{
	x: 0,   //（必填项）数字类型；模块左上角的 x 坐标（相对于所属的 Window 或 Frame）；默认值：0
	y: 0,   //（必填项）数字类型；模块左上角的 y 坐标（相对于所属的 Window 或 Frame）；默认值：0
	w: 320, //（必填项）数字类型；模块的宽度；默认值：所属的 Window 或 Frame 的宽度
	h: 250  //（必填项）数字类型；模块的高度；默认值：250
}
```

fixedOn：

- 类型：字符串
- 描述：（可选项）模块所属 Frame 的名字，若不传则模块归属于当前 Window

fixed：

- 类型：布尔
- 描述：（（可选项）模块是否随所属 Window 或 Frame 滚动
- 默认值：true（不随之滚动）

url：

- 类型：字符串
- 描述：（必填项）视频资源地址，支持fs://、widget://(在 android 平台上不支持 widget)、rtmp://、http://

title：

- 类型：字符串
- 描述：（可选项）不填时标题默认显示url地址

defaultBtn：

- 类型：布尔
- 描述：（可选项）设置本次播放是否显示默认自带的播放控制按钮
- 默认值：true（显示）

enableFull：

- 类型：布尔
- 描述：（可选项）本次播放视频是否全屏播放，当为true时将直接全屏播放视频，x,y,w,h,fixedOn,fixed值不会生效。
- 默认值：false（窗口播放）

isTopView：

- 类型：布尔
- 描述：（可选项）是否显示顶部标题栏
- 默认值：true（显示）

isFullBtn：

- 类型：布尔
- 描述：（可选项）小窗口是否显示进入全屏按钮
- 默认值：true（显示）


isBackBtn：

- 类型：布尔
- 描述：（可选项）小窗口是否显示返回按钮
- 默认值：false（不显示）


scalingMode：

- 类型：布尔
- 描述：（可选项）视频播放显示铺满模式（1:无缩放, 2:适应大小模式, 3:充满可视范围，可能会被裁剪, 4:缩放到充满视图）
- 默认值：1（无缩放）

isReplayView：

- 类型：布尔
- 描述：（可选项）视频播放结束后是否显示重播视图层(该视图层会覆盖在整个播放器上层，播放器的按钮不能被操作)
- 默认值：false（不显示）


fullscreenMode：

- 类型：字符串
- 描述：（可选项）设置全屏按钮控制全屏显示模式是横屏还是竖屏 竖屏:PORTRAIT ; 横屏:LANDSCAPE
- 默认值：LANDSCAPE（横屏）

isShowProcessView：

- 类型：布尔型
- 描述：（可选项）是否显示进度条 (显示:true ; 不显示:false)【备注:直播流自动默认为false】
- 默认值：true

isShowTimeLable：

- 类型：布尔型
- 描述：（可选项）是否显示播放时间 (显示:true ; 不显示:false)
- 默认值：true

isLive：

- 类型：布尔型
- 描述：（可选项）是否直播视频源 （直播：true；点播：false）
- 默认值：直播:rtmp://开头|rtsp://开头|.m3u8结尾;其余为非直播

isOpenGesture：

- 类型：布尔型
- 描述：（可选项）是否开启手势控制音量，亮度和进度 （开启：true；不开启：false）
- 默认值：true

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
var obj = api.require('playModule');
obj.play({
    rect:
    {	x: 0,
    	y : 0,
    	w : 320,
    	h: 250
    },
    fixedOn: api.frameName,
    title: 'test',
    url: 'http://resource.apicloud.com/video/apicloud3.mp4',
    defaultBtn: true,
    enableFull : false,
	 isTopView : false
}, function(ret, err) {

});
```

##可用性

iOS、Android系统

可提供的1.0.0及更高版本

<div id="a3"></div>
#**playUrl**

同一个页面，已经调用play接口后，切换视频地址时调用（前提条件，同一界面已经调用过play接口）。

playUrl({params}, callback(ret, err))

##params

url：

- 类型：字符串
- 描述：（必填项）视频资源地址，支持fs://、widget://(在 android 平台上不支持 widget)、rtmp://、http://

title：

- 类型：字符串
- 描述：（可选项）不填时标题默认显示url播放文件名

defaultBtn：

- 类型：布尔
- 描述：（可选项）设置本次播放是否显示默认自带的播放控制按钮
- 默认值：true（显示）

isLive：

- 类型：布尔型
- 描述：（可选项）是否直播视频源 （直播：true；点播：false）
- 默认值：直播:rtmp://开头|rtsp://开头|.m3u8结尾;其余为非直播

isOpenGesture：

- 类型：布尔型
- 描述：（可选项）是否开启手势控制音量，亮度和进度 （开启：true；不开启：false）
- 默认值：true

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
var obj = api.require('playModule');
obj.playUrl({
    title: 'test',
    url: 'http://resource.apicloud.com/video/apicloud3.mp4',
    defaultBtn: true
}, function(ret, err) {

});
```

##可用性

iOS、Android系统

可提供的1.0.0及更高版本

<div id="a4"></div>
#**pause**

暂停播放

pause(callback(ret, err))

##示例代码

```js
var obj = api.require('playModule');
obj.pause(function(ret, err) {
    alert(JSON.stringify(ret));
});
```

##可用性
iOS、Android系统

可提供的1.0.0及更高版本



<div id="a5"></div>
#**start**

暂停后开始播放

start(callback(ret, err))

##示例代码

```js
var obj = api.require('playModule');
obj.start(function(ret, err) {
    alert(JSON.stringify(ret));
});
```

##可用性
iOS、Android系统

可提供的1.0.0及更高版本

<div id="a6"></div>
#**stop**

停止播放

stop(callback(ret, err))

##示例代码

```js
var obj = api.require('playModule');
obj.stop(function(ret, err) {
    alert(JSON.stringify(ret));
});
```

##可用性
iOS、Android系统

可提供的1.0.0及更高版本

<div id="a7"></div>
#**getDuration**

获取视频的时长

getDuration(callback(ret, err))


##callback(ret, err)

ret：

- 类型：JSON对象
- 内部字段：

```js
{
	status: true,   //布尔型；true||false
	duration : 1221122//视频的总时长
}
```

##示例代码

```js
var obj = api.require('playModule');
obj.getDuration(function(ret, err) {
    alert(JSON.stringify(ret));
});
```

##可用性
iOS、Android系统

可提供的1.0.0及更高版本


<div id="a8"></div>
#**getCurrentPosition**

获取已经播放的时长

getCurrentPosition(callback(ret, err))

##callback(ret, err)

ret：

- 类型：JSON对象
- 内部字段：

```js
{
	status: true,   //布尔型；true||false
	currentPosition : 2221//已经播放的时长
}
```

##示例代码

```js
var obj = api.require('playModule');
obj.getCurrentPosition(function(ret, err) {
    alert(JSON.stringify(ret));
});
```

##可用性
iOS、Android系统

可提供的1.0.0及更高版本



<div id="a9"></div>
#**full**

全屏播放

full(callback(ret, err))

##示例代码

```js
var obj = api.require('playModule');
obj.full(function(ret, err) {
    alert(JSON.stringify(ret));
});
```

##可用性
iOS、Android系统

可提供的1.0.0及更高版本

<div id="a10"></div>
#**unfull**

退出全屏

unfull(callback(ret, err))

##示例代码

```js
var obj = api.require('playModule');
obj.unfull(function(ret, err) {
    alert(JSON.stringify(ret));
});
```

##可用性
iOS、Android系统

可提供的1.0.0及更高版本


<div id="a11"></div>
#**addEventListener**

视频播放完监听事件

addEventListener({params}, callback(ret, err))

##params

name：

- 类型：字符串
- 描述：监听事件名称,可以设置的播放界面监听类型有：playEnd(播放结束)，qingxidu(清晰度),xuanji(选集)，download(下载),collection(收藏),pause(暂停),play(播放),backBtn(小窗口返回按钮),full(进入全屏按钮),unfull(退出全屏按钮)


##callback(ret, err)

ret：

- 类型：JSON对象
- 内部字段：

```js
{
status: true   //布尔型；true
}
```

##示例代码

```js
var playModule = api.require('playModule');
playModule.addEventListener({
    name: 'playEnd'
}, function(ret, err) {
    console.log("addEventListener>>>>" + JSON.stringify(ret));
});
```

##可用性
iOS、Android系统

可提供的1.0.0及更高版本



<div id="a12"></div>
#**isFullScreen**

获取是否全屏播放状态

isFullScreen({params}, callback(ret, err))


##callback(ret, err)

ret：

- 类型：JSON对象
- 内部字段：

```js
{
	status: true|false   //布尔型；true:全屏 false:窗口
}
```

##示例代码

```js
var playModule = api.require('playModule');
playModule.isFullScreen(function(ret, err) {
    console.log("addEventListener>>>>" + JSON.stringify(ret));
});
```

##可用性
iOS、Android系统

可提供的1.0.0及更高版本


<div id="a13"></div>
#**seekTo**

设置播放进度位置(请根据视频最大时长进行参数控制传入)

seekTo({params}, callback(ret, err))

##params

process：

- 类型：数字型
- 描述：（必填项）设置视频需要播放的时长
- 默认值：0


##示例代码

```js
var playModule = api.require('playModule');
playModule.seekTo({
	process : 402334
},function(ret, err) {
	alert(JSON.stringify(ret));
});
```

##可用性
Android、iOS系统

可提供的1.0.0及更高版本


<div id="a14"></div>
#**setVolume**

设置播音量

setVolume({params}, callback(ret, err))

##params

volume：

- 类型：数字型
- 描述：（必填项）0-100
- 默认值：0


##示例代码

```js
var playModule = api.require('playModule');
playModule.setVolume({
	volume : 50
},function(ret, err) {
	alert(JSON.stringify(ret));
});
```

##可用性
Android、iOS系统

可提供的1.0.0及更高版本

<div id="a15"></div>
#**hidePlayer**

隐藏播放器

hidePlayer(callback(ret, err))

##示例代码

```js
var playModule = api.require('playModule');
playModule.hidePlayer(function(ret, err) {
	alert(JSON.stringify(ret));
});
```

##可用性
Android、iOS系统

可提供的1.0.0及更高版本


<div id="a16"></div>
#**showPlayer**

显示播放器

showPlayer(callback(ret, err))

##示例代码

```js
var playModule = api.require('playModule');
playModule.showPlayer(function(ret, err) {
	alert(JSON.stringify(ret));
});
```

##可用性
Android、iOS系统

可提供的1.0.0及更高版本