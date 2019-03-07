/*
Title: nim
Description: nim
*/

<p style="color: #ccc; margin-bottom: 30px;">来自于：开发者</p>

<div class="outline">
[login](#a1)

[logout](#a2)

[onKick](#a3)

[willSendIMMessage](#a4)

[onSendMessageWithProgress](#a5)

[onRecvIMMessages](#a6)

[allRecentSession](#a7)

[fetchMessageHistory](#a8)

[sendText](#a9)

[sendImage](#a10)

[sendLocationMsg](#a11)

[sendAudio](#a12)

[sendVideo](#a13)

[sendFile](#a14)

[switchAudioOutputDevice](#a15)

[isPlaying](#a16)

[playAudio](#a17)

[stopPlay](#a18)

[isRecording](#a19)

[recordAudioForDuration](#a20)

[cancelRecord](#a21)

[stopRecord](#a22)

[onIMTeamRemoved](#a23)

[onIMTeamUpdated](#a24)

[onBlackListChanged](#a25)

[allMyTeams](#a26)

[teamById](#a27)

[fetchTeamInfo](#a28)

[createTeam](#a29)

[addUsers](#a30)

[acceptInviteWithTeam](#a31)

[rejectInviteWithTeam](#a32)

[applyToTeam](#a33)

[passApplyToTeam](#a34)

[rejectApplyToTeam](#a35)

[updateTeamName](#a36)

[updateTeamIntro](#a37)

[updateTeamAnnouncement](#a38)

[updateTeamJoinMode](#a39)

[addManagersToTeam](#a40)

[removeManagersFromTeam](#a41)

[transferManagerWithTeam](#a42)

[fetchTeamMembers](#a43)

[quitTeam](#a44)

[kickUsers](#a45)

[dismissTeam](#a46)

[updateNotifyStateForTeam](#a47)

[onIMSystemMessageRecieved](#a48)

[fetchSystemNotifications](#a49)

[allNotificationsUnreadCount](#a50)

[deleteAllNotifications](#a51)

[markAllNotificationsAsRead](#a52)

[onUserInfoUpdate](#a53)

[userInfo](#a54)

[fetchUserInfos](#a55)

[updateMyUserInfo](#a56)

[myFriends](#a57)

[requestFriend](#a58)

[deleteFriend](#a59)

[myBlackList](#a60)

[addToBlackList](#a61)

[removeFromBlackBlackList](#a62)

[isUserInBlackList](#a63)

[myMuteUserList](#a64)

[updateNotifyStateForUser](#a65)

[notifyForNewMsgForUser](#a66)

[onChatRoomStatusChanged](#a67)

[onChatRoomKickOutEvent](#a68)

[enterChatRoom](#a69)

[exitChatRoom](#a70)

[getChatRoomHistoryMsg](#a71)

[getChatRoomInfo](#a72)

[getChatRoomMembers](#a73)

[getChatRoomMembersByIds](#a74)

[addUserToBlackList](#a75)

[muteUser](#a76)

[setAdmin](#a77)

[setNormal](#a78)

[kickMemberFromChatRoom](#a79)
</div>

#**概述**

**nim 模块概述**

本模块是基于NIM(网易云信)原生SDK封装的模块插件,使用本模块可以实现基本的即时通讯功能,包括—— 单聊功能:支持发送语音,图片,表情,文字,位置,附件; 群聊功能:提供了普通群、以及高级群两种形式的群聊功能.高级群拥有更多的权限操作,两种群聊形式在共有操作上保持了接口一致.开发时推荐只选择一种群类型进行开发.普通群和高级群在原则上是不能互相转换的,他们的群类型在创建时就已经确定;

**模块使用攻略**

使用之前须从[网易云平台地址](http://vcloud.163.com/)申请开发者账号并创建应用，获取AppKey。

**使用此模块之前必须先配置config.xml文件，配置完毕，需通过云端编译生效，配置方法如下：**

- 名称：nim
- 参数：AppKey
- 配置示例:

```xml
  <feature name="nim">
    <param name="AppKey" value="9db2db8540544200a606ba10ab1645e1"/>
    <param name="ApnsCername" value="20170701test"/> 
  </feature>
```

- 字段描述:
    
    **AppKey**：（必须配置）从网易云获取的 AppKey值。AppKey的申请方法参考[新手指引](http://dev.netease.im/docs/product/IM即时通讯/新手接入指南)。
    
    **ApnsCername**：（可选配置）iOS推送证书名（仅ios可用）。
	![描述](http://community.apicloud.com/bbs/data/attachment/forum/201708/02/154952heuu4ueu9x657qzq.png)

**模块错误代码含义参考表**

code | 详细描述
-----|------
-1   | 参数错误
1	|  错误的参数
2	|  多媒体文件异常
3	|  图片异常
4	|  url异常
5	|  读取/写入文件异常
6	|  无效的token
7	|  HTTP请求失败
16	|  用户信息缺失 (未登录 或 未提供用户资料)
14	|  SQL语句执行失败
8	| 无录音权限
9	| 录音初始化失败
10	| 录音失效
11	| 播放初始化失败
200  | 操作成功
201  | 客户端版本不对,需升级sdk
302  | 用户名或密码错误
403  | 非法操作或没有权限
404  | 对象不存在
405  | 参数长度过长
406	  | 对象只读
408  | 客户端请求超时
414  | 参数错误
415	  | 客户端网络问题
416	  | 频率控制
422	  | 账号被禁用
500	  | 服务器内部错误
503	  | 服务器繁忙
509	  | 无效协议
801	  | 群人数达到上限
802	  | 没有权限
803	  | 群不存在
804	  | 用户不在群
805	  | 群类型不匹配
806	  | 创建群数量达到限制
807	  | 群成员状态错误
808	  | 申请成功
809	  | 已经在群内
810	  | 邀请成功
998	  | 解包错误
999	  | 打包错误
1000 | 本地操作异常 
    
    
##**模块接口**
    
<div id="a1"></div>
#**login**

登录

login({params}, callback(ret, err))

##params

userId：

- 类型：字符串
- 描述：（必填项）用户账号。

password：

- 类型：字符串
- 描述：（必填项）用户密码。

##callback(ret, err)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
    status: true,      //布尔型；true||false
    userId : ''        //用户id
}
```
err：

- 类型：JSON 对象
- 内部字段：

```js
{
    errorCode: -1,
    errorMessage : ''
}
```
##示例代码

```js
var demo = api.require('nim');
demo.login({
	userId : '1001',
	password : '1qaz2wsx'
},function(ret, err) {
});
```
##补充说明

账号创建请参考：[网易云通信Server Http API接口文档](http://dev.netease.im/docs/product/IM即时通讯/服务端API文档)

##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本

<div id="a2"></div>
#**logout**

退出

logout(callback(ret, err))

##callback(ret, err)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
    status: true      //布尔型；true||false
}
```
err：

- 类型：JSON 对象
- 内部字段：

```js
{
    errorCode: -1,
    errorMessage : ''
}
```

##示例代码

```js
var demo = api.require('nim');
demo.logout(function(ret, err){
});
```
##补充说明

ios登录为异步回调，退出失败会返回信息。安卓没有异步回调，操作接口就返回成功。

##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本

<div id="a3"></div>
#**onKick**

被踢的监听

onKick(callback(ret))

##callback(ret)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
    code : 1 //数字类型；
    			//1	被另外一个客户端踢下线 (互斥客户端一端登录挤掉上一个登录中的客户端)
   				//2	被服务器踢下线, 仅iOS支持
   				//3	被另外一个客户端手动选择踢下线
}
```

##示例代码

```js
var demo = api.require('nim');
demo.onKick(function(ret){
});

```

##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本

<div id="a4"></div>
#**willSendIMMessage**

即将发送消息监听

willSendIMMessage(callback(ret))

##callback(ret)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
    messageId : ,// 消息ID,唯一标识
    messageType : ,//消息类型:
    				//0	文本类型消息
					//1	图片类型消息
					//2	声音类型消息
					//3	视频类型消息
					//4	位置类型消息
					//5	通知类型消息
					//6	文件类型消息
					//10	提醒类型消息
					//100	自定义类型消息
    sessionId : ,// 会话ID,如果当前session为team,则sessionId为teamId,如果是P2P则为对方帐号
    sessionType :, //0-单聊,1-群聊, 2- 聊天室
    content : //发送文本内容
}
```

##示例代码

```js
var demo = api.require('nim');
demo.willSendIMMessage(function(ret){
});
```

##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本

<div id="a5"></div>
#**onSendMessageWithProgress**

消息发送进度监听

onSendMessageWithProgress(callback(ret))

##callback(ret)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
    progress : 0.51222,
    messageId : '123123123adsfad'
}
```

##示例代码

```js
var demo = api.require('nim');
demo.onSendMessageWithProgress(function(ret){
});
```
##补充说明

文本消息,地理位置消息会没有这个监听

##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本

<div id="a6"></div>
#**onRecvIMMessages**

收到新消息监听

onRecvIMMessages(callback(ret))

##callback(ret)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
    messages: [{
		 messageId:, //消息ID,唯一标识
	    messageType:, //消息类型,
	    			//0	文本类型消息
					//1	图片类型消息
					//2	声音类型消息
					//3	视频类型消息
					//4	位置类型消息
					//5	通知类型消息
					//6	文件类型消息
					//10	提醒类型消息
					//100	自定义类型消息
	    from:, //消息来源
	    text:, //消息文本
	    timestamp:, //消息发送时间
	    sessionId:, //会话ID,如果当前session为team,则sessionId为teamId,如果是P2P则为对方帐号
	    sessionType:, //会话类型,0:单聊 1:群聊 2:聊天室 
	    path:,//本地路径
	    url:,//远程路径
	    fileLength:,//文件大小(音频文件没有这个参数)
	    thumbPath://缩略图本地路径(图片)
	    duration:,//时长,毫秒为单位(音频,视频)
	    coverUrl:,//视频封面的远程路径(视频)
	    latitude:,//纬度(位置消息)
	    longitude:,//经度(位置消息)
	    title:,//标题(位置消息)
	    displayName:,//显示名称(文件，图片，视频)
    }]
}
```

##示例代码

```js
var demo = api.require('nim');
demo.onRecvIMMessages(function(ret){
});
```

##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本

<div id="a7"></div>
#**allRecentSession**

获取最近会话

allRecentSession(callback(ret, err))

##callback(ret, err)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
  status: true,       //布尔型；true||false
  messages:[{
  	  sessionType: 0,     //0-单聊,1-群聊, 2- 聊天室
	  unreadCount : 11,   //未读消息数
	  lastMessage : {
	  		 messageId:, //消息ID,唯一标识
		    messageType:, //消息类型,
		    		//0	文本类型消息
					//1	图片类型消息
					//2	声音类型消息
					//3	视频类型消息
					//4	位置类型消息
					//5	通知类型消息
					//6	文件类型消息
					//10	提醒类型消息
					//100	自定义类型消息
		    from:, //消息来源
		    text:, //消息文本
		    timestamp:, //消息发送时间
		    sessionId:, //会话ID,如果当前session为team,则sessionId为teamId,如果是P2P则为对方帐号
		    sessionType:, //会话类型,0:单聊 1:群聊 2:聊天室 
		    path:,//本地路径
		    url:,//远程路径
		    fileLength:,//文件大小(音频文件没有这个参数)
		    thumbPath://缩略图本地路径(图片)
		    duration:,//时长,毫秒为单位(音频,视频)
		    coverUrl:,//视频封面的远程路径(视频)
		    latitude:,//纬度(位置消息)
		    longitude:,//经度(位置消息)
		    title:,//标题(位置消息)
		    displayName:,//显示名称(文件，图片，视频)
	  }
  }]
}
```

err：

- 类型：JSON 对象
- 内部字段：

```js
{
    errorCode: -1,
    errorMessage : '无最近会话消息'
}
```

##示例代码

```js
var demo = api.require('nim');
demo.allRecentSession(function(ret, err){
});
```

##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本

<div id="a8"></div>
#**fetchMessageHistory**

获取云端消息记录

fetchMessageHistory({params}, callback(ret, err))

##params

sessionId：

- 类型：字符串
- 描述：（必填项）单聊时聊天人的userid, 群聊时groupid, 聊天室的id。

sessionType

- 类型：数字型
- 描述：（必填项）0-单聊,1-群聊, 2- 聊天室。
- 默认值: 0

limit

- 类型：字符串
- 描述：检索条数, 最大限制100条
- 默认值: 100

sync

- 类型：布尔型
- 描述：同步数据: 是否在远程获取消息成功之后同步到本地数据库，如果选择同步，则同步之后不会触发消息添加的回调。默认不同步(false),true为同步。
- 默认值: false

startTime

- 类型：Long型
- 描述：需要检索的起始时间,没有则传入0
- 默认值: 0

##callback(ret, err)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
  status: true,      //布尔型；true||false
  messages : [{
  		messageId:, //消息ID,唯一标识
		    messageType:, //消息类型,
		    		//0	文本类型消息
					//1	图片类型消息
					//2	声音类型消息
					//3	视频类型消息
					//4	位置类型消息
					//5	通知类型消息
					//6	文件类型消息
					//10	提醒类型消息
					//100	自定义类型消息
		    from:, //消息来源
		    text:, //消息文本
		    timestamp:, //消息发送时间
		    sessionId:, //会话ID,如果当前session为team,则sessionId为teamId,如果是P2P则为对方帐号
		    sessionType:, //会话类型,0:单聊 1:群聊 2:聊天室 
		    path:,//本地路径
		    url:,//远程路径
		    fileLength:,//文件大小(音频文件没有这个参数)
		    thumbPath://缩略图本地路径(图片)
		    duration:,//时长,毫秒为单位(音频,视频)
		    coverUrl:,//视频封面的远程路径(视频)
		    latitude:,//纬度(位置消息)
		    longitude:,//经度(位置消息)
		    title:,//标题(位置消息)
		    displayName:,//显示名称(文件，图片，视频)
		    ext : //扩展消息
  }]
}
```

err：

- 类型：JSON 对象
- 内部字段：

```js
{
    errorCode: -1,
    errorMessage : ''
}
```

##示例代码

```js
var demo = api.require('nim');
demo.fetchMessageHistory({
	sessionId : '1002',
	sessionType : 0,
	limit : 100,
	sync : false,
	startTime : 0
},function(ret, err){
});
```
##补充说明

安卓在未登录情况下无返回值。

##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本

<div id="a9"></div>
#**sendText**

发送文本消息及表情

sendText({params}, callback(ret, err))

##params

sessionId：

- 类型：字符串
- 描述：（必填项）单聊时聊天人的userid, 群聊时groupid, 聊天室的id。

sessionType

- 类型：数字型
- 描述：（必填项）0-单聊,1-群聊, 2- 聊天室。
- 默认值: 0

content

- 类型：字符串
- 描述：（必填项）消息内容。

ext：

- 类型：JSON对象
- 描述：（可选项）自定义扩展字段

##callback(ret, err)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
	status: true,      //布尔型；true||false
	message : {
			messageId:, //消息ID,唯一标识
		    messageType:, //消息类型, 0	文本类型消息
		    from:, //消息来源
		    text:, //消息文本
		    timestamp:, //消息发送时间
		    sessionId:, //会话ID,如果当前session为team,则sessionId为teamId,如果是P2P则为对方帐号
		    sessionType:, //会话类型,0:单聊 1:群聊 2:聊天室 
		    ext : //扩展消息
	}
}
```

err：

- 类型：JSON 对象
- 内部字段：

```js
{
    errorCode: -1,
    errorMessage : ''
}
```

##示例代码

```js
var demo = api.require('nim');
demo.sendText({
	sessionId : '1002',
	sessionType : 0,
	content : '我是文本测试',
	ext : {
		key : 'key1'
	}
}, function(ret, err){
});
```

##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本

<div id="a10"></div>
#**sendImage**

发送图片

sendImage({params}, callback(ret, err))

##params

sessionId：

- 类型：字符串
- 描述：（必填项）单聊时聊天人的userid, 群聊时groupid, 聊天室的id。

sessionType

- 类型：数字型
- 描述：（必填项）0-单聊,1-群聊, 2- 聊天室。
- 默认值: 0

filePath：

- 类型：字符串
- 描述：（必填项）图片路径(支持fs:// widget:// 路径)。


displayName：

- 类型：字符串
- 描述：（非必填）显示名称。

ext：

- 类型：JSON对象
- 描述：（可选项）自定义扩展字段


##callback(ret, err)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
	status: true,      //布尔型；true||false
	message : {
			messageId:, //消息ID,唯一标识
		    messageType:, //消息类型, 1	图片类型消息
		    from:, //消息来源
		    timestamp:, //消息发送时间
		    sessionId:, //会话ID,如果当前session为team,则sessionId为teamId,如果是P2P则为对方帐号
		    sessionType:, //会话类型,0:单聊 1:群聊 2:聊天室 
		    path:,//本地路径
		    url:,//远程路径
		    fileLength:,//文件大小(音频文件没有这个参数)
		    thumbPath://缩略图本地路径(图片)
		    ext : //扩展消息
	}
}
```

err：

- 类型：JSON 对象
- 内部字段：

```js
{
    errorCode: -1,
    errorMessage : ''
}
```

##示例代码

```js
var demo = api.require('nim');
demo.sendImage({
	sessionId : '1002',
	sessionType : 0,
	filePath : 'widget://image/refresh.png',
	displayName : '我是图片',
	ext : {
		key : 'key1'
	}
}, function(ret, err){
});
```

##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本

<div id="a11"></div>
#**sendLocationMsg**

发送地理位置信息

sendLocationMsg({params}, callback(ret, err))

##params

sessionId：

- 类型：字符串
- 描述：（必填项）单聊时聊天人的userid, 群聊时groupid, 聊天室的id。

sessionType

- 类型：数字型
- 描述：（必填项）0-单聊,1-群聊, 2- 聊天室。
- 默认值: 0

title：

- 类型：字符串
- 描述：（可选项）位置的地址名。

latitude：

- 类型：double
- 描述：（可选项）经度。
- 默认值: 0.0

longitude：

- 类型：double
- 描述：（可选项）纬度。
- 默认值: 0.0

ext：

- 类型：JSON对象
- 描述：（可选项）自定义扩展字段

##callback(ret, err)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
	status: true,      //布尔型；true||false
	message : {
			messageId:, //消息ID,唯一标识
		    messageType:, //消息类型, 4	位置类型消息
		    from:, //消息来源
		    timestamp:, //消息发送时间
		    sessionId:, //会话ID,如果当前session为team,则sessionId为teamId,如果是P2P则为对方帐号
		    sessionType:, //会话类型,0:单聊 1:群聊 2:聊天室 
		    latitude:,//纬度(位置消息)
		    longitude:,//经度(位置消息)
		    title:,//标题(位置消息)
		    ext : //扩展消息
	}
}
```

err：

- 类型：JSON 对象
- 内部字段：

```js
{
    errorCode: -1,
    errorMessage : ''
}
```
##示例代码

```js
var demo = api.require('nim');
demo.sendLocationMsg({
	sessionId : '1002',
	sessionType : 0,
	title : '我是地理位置',
	latitude : 102,
	longitude : 39,
	ext : {
		key : 'key1'
	}
}, function(ret, err){
});
```
##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本

<div id="a12"></div>
#**sendAudio**

发送音频消息

sendAudio({params}, callback(ret, err))

##params

sessionId：

- 类型：字符串
- 描述：（必填项）单聊时聊天人的userid, 群聊时groupid, 聊天室的id。

sessionType

- 类型：数字型
- 描述：（必填项）0-单聊,1-群聊, 2- 聊天室。
- 默认值: 0

filePath：

- 类型：字符串
- 描述：（必填项）语音文件路径(支持fs:// widget://)。

ext：

- 类型：JSON对象
- 描述：（可选项）自定义扩展字段

##callback(ret, err)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
	status: true,      //布尔型；true||false
	message : {
			messageId:, //消息ID,唯一标识
		    messageType:, //消息类型, //2	声音类型消息
		    from:, //消息来源
		    timestamp:, //消息发送时间
		    sessionId:, //会话ID,如果当前session为team,则sessionId为teamId,如果是P2P则为对方帐号
		    sessionType:, //会话类型,0:单聊 1:群聊 2:聊天室 
		    path:,//本地路径
		    url:,//远程路径
		    fileLength:,//文件大小
		    duration:,//时长,毫秒为单位(音频,视频)
		    ext : //扩展消息
	}
}
```

err：

- 类型：JSON 对象
- 内部字段：

```js
{
    errorCode: -1,
    errorMessage : ''
}
```
##示例代码

```js
var demo = api.require('nim');
demo.sendAudio({
	sessionId : '1002',
	sessionType : 0,
	filePath : 'widget://res/bandari.mp3',
	ext : {
		key : 'key1'
	}
}, function(ret, err){
});
```
##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本


<div id="a13"></div>
#**sendVideo**

发送视频

sendVideo({params}, callback(ret, err))

##params

sessionId：

- 类型：字符串
- 描述：（必填项）单聊时聊天人的userid, 群聊时groupid, 聊天室的id。

sessionType

- 类型：数字型
- 描述：（必填项）0-单聊,1-群聊, 2- 聊天室。
- 默认值: 0

filePath：

- 类型：字符串
- 描述：（必填项）视频文件路径(支持fs:// widget://)。

displayName：

- 类型：字符串
- 描述：（可选项）显示名称

ext：

- 类型：JSON对象
- 描述：（可选项）自定义扩展字段

##callback(ret, err)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
	status: true,      //布尔型；true||false
	message : {
			messageId:, //消息ID,唯一标识
		    messageType:, //消息类型,3	视频类型消息
		    from:, //消息来源
		    timestamp:, //消息发送时间
		    sessionId:, //会话ID,如果当前session为team,则sessionId为teamId,如果是P2P则为对方帐号
		    sessionType:, //会话类型,0:单聊 1:群聊 2:聊天室 
		    path:,//本地路径
		    url:,//远程路径
		    fileLength:,//文件大小(音频文件没有这个参数)
		    duration:,//时长,毫秒为单位(音频,视频)
		    coverUrl:,//视频封面的远程路径(视频)
		    displayName:,//显示名称(文件，图片，视频)
		    ext : //扩展消息
	}
}
```

err：

- 类型：JSON 对象
- 内部字段：

```js
{
    errorCode: -1,
    errorMessage : ''
}
```
##示例代码

```js
var demo = api.require('nim');
demo.sendVideo({
	sessionId : '1002',
	sessionType : 0,
	filePath : 'widget://res/demo.mp4',
	displayName : '我是测试视频',
	ext : {
		key : 'key1'
	}
}, function(ret, err){
});
```
##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本


<div id="a14"></div>
#**sendFile**

发送文件

sendFile({params}, callback(ret, err))

##params

sessionId：

- 类型：字符串
- 描述：（必填项）单聊时聊天人的userid, 群聊时groupid, 聊天室的id。

sessionType

- 类型：数字型
- 描述：（必填项）0-单聊,1-群聊, 2- 聊天室。
- 默认值: 0

filePath：

- 类型：字符串
- 描述：（必填项）文件路径(支持fs:// widget://)。

displayName：

- 类型：字符串
- 描述：（可选项）显示名称

ext：

- 类型：JSON对象
- 描述：（可选项）自定义扩展字段

##callback(ret, err)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
	status: true,      //布尔型；true||false
	message : {
			messageId:, //消息ID,唯一标识
		    messageType:, //消息类型,	6 文件类型消息
		    from:, //消息来源
		    timestamp:, //消息发送时间
		    sessionId:, //会话ID,如果当前session为team,则sessionId为teamId,如果是P2P则为对方帐号
		    sessionType:, //会话类型,0:单聊 1:群聊 2:聊天室 
		    path:,//本地路径
		    url:,//远程路径
		    fileLength:,//文件大小(音频文件没有这个参数)
		    displayName:,//显示名称(文件，图片，视频)
		    ext : //扩展消息
	}
}
```

err：

- 类型：JSON 对象
- 内部字段：

```js
{
    errorCode: -1,
    errorMessage : ''
}
```
##示例代码

```js
var demo = api.require('nim');
demo.sendFile({
	sessionId : '1002',
	sessionType : 0,
	filePath : 'widget://res/demo.mp4',
	displayName : '我是测试视频',
	ext : {
		key : 'key1'
	}
}, function(ret, err){
});
```
##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本


<div id="a15"></div>
#**switchAudioOutputDevice**

切换音频的输出设备

switchAudioOutputDevice({params}, callback(ret))

##params

outputDevice

- 类型：数字型
- 描述：（必填项）0:听筒,1:扬声器。
- 默认值: 0

##callback(ret)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
	status: true
}
```

##示例代码

```js
var demo = api.require('nim');
demo.switchAudioOutputDevice({
	outputDevice : 1
}, function(ret, err){
});
```
##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本


<div id="a16"></div>
#**isPlaying**

判断是否正在播放音频

isPlaying(callback(ret))

##callback(ret)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
	status: true  //布尔型；true||false
}
```

##示例代码

```js
var demo = api.require('nim');
demo.isPlaying(function(ret, err){
});
```
##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本


<div id="a17"></div>
#**playAudio**

播放音频

playAudio({params}, callback(ret, err))

##params

filePath：

- 类型：字符串
- 描述：（必填项）语音文件路径(支持fs:// widget://)。

##callback(ret, err)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
	status: true,      //布尔型；true||false
	evenType : 'start' //start:开始播放|end:播放结束
}
```

err：

- 类型：JSON 对象
- 内部字段：

```js
{
    errorCode: -1,
    errorMessage : ''
}
```
##示例代码

```js
var demo = api.require('nim');
demo.playAudio({
	filePath : 'widget://res/demo.mp3',
}, function(ret, err){
});
```
##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本


<div id="a18"></div>
#**stopPlay**

停止播放音频

stopPlay(callback(ret,err))

##callback(ret,err)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
	status: true  //布尔型；true||false
}
```
err：

- 类型：JSON 对象
- 内部字段：

```js
{
    errorCode: -1,
    errorMessage : ''
}
```

##示例代码

```js
var demo = api.require('nim');
demo.stopPlay(function(ret, err){
});
```
##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本


<div id="a19"></div>
#**isRecording**

判断是否正在录制音频

isRecording(callback(ret))

##callback(ret)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
	status: true	//布尔型；true||false
}
```

##示例代码

```js
var demo = api.require('nim');
demo.isRecording(function(ret){
});
```
##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本


<div id="a20"></div>
#**recordAudioForDuration**

录音

recordAudioForDuration({params}, callback(ret,err))

##params

duration：

- 类型：数字型
- 描述：（必填项）最长录音时长（秒）。
- 默认值: 30

##callback(ret)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
	status: true,      //布尔型；true||false
	evenType : 'start', //start:开始录音|success:录音完成|cancel:取消录音
	filePath : ''
}
```
err：

- 类型：JSON 对象
- 内部字段：

```js
{
    errorCode: -1,
    errorMessage : ''
}
```
##示例代码

```js
var demo = api.require('nim');
demo.recordAudioForDuration({
	duration : 30
}, function(ret, err){
});
```
##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本


<div id="a21"></div>
#**cancelRecord**

取消录音

cancelRecord(callback(ret))

##callback(ret)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
	status: true	
}
```

##示例代码

```js
var demo = api.require('nim');
demo.cancelRecord(function(ret){
});
```
##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本

<div id="a22"></div>
#**stopRecord**

停止录制音频

stopRecord(callback(ret))

##callback(ret)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
	status: true
}
```

##示例代码

```js
var demo = api.require('nim');
demo.stopRecord(function(ret){
});
```
##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本

<div id="a23"></div>
#**onIMTeamRemoved**

群组移除监听

onIMTeamRemoved(callback(ret))

##callback(ret)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
	teamId:,//群ID
	teamName:,//群名称
	type:,//群类型
	owner:,//群拥有者ID, 普通群拥有者就是群创建者,但是高级群可以进行拥有信息的转让
	intro:,//群介绍
	announcement:,//群公告
	memberNumber:,//群成员人数,这个值表示是上次登录后同步下来群成员数据,并不实时变化,必要时需要调用fetchTeamInfo进行刷新
	level:,//群等级,目前群人数主要是限制群人数上限
	createTime:,//群创建时间
	joinMode:,//群验证方式,允许所有人加入:0,需要验证:1,不允许任何人加入:2
	notifyForNewMsg://群消息是否需要通知,这个设置影响群消息的APNS推送
}
```

##示例代码

```js
var demo = api.require('nim');
demo.onIMTeamRemoved(function(ret){
});
```
##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本

<div id="a24"></div>
#**onIMTeamUpdated**

群组更新监听

onIMTeamUpdated(callback(ret))

##callback(ret)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
	teamId:,//群ID
	teamName:,//群名称
	type:,//群类型
	owner:,//群拥有者ID, 普通群拥有者就是群创建者,但是高级群可以进行拥有信息的转让
	intro:,//群介绍
	announcement:,//群公告
	memberNumber:,//群成员人数,这个值表示是上次登录后同步下来群成员数据,并不实时变化,必要时需要调用fetchTeamInfo进行刷新
	level:,//群等级,目前群人数主要是限制群人数上限
	createTime:,//群创建时间
	joinMode:,//群验证方式,允许所有人加入:0,需要验证:1,不允许任何人加入:2
	notifyForNewMsg://群消息是否需要通知,这个设置影响群消息的APNS推送
}
```

##示例代码

```js
var demo = api.require('nim');
demo.onIMTeamUpdated(function(ret){
});
```
##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本


<div id="a25"></div>
#**onBlackListChanged**

黑名单列表发生变化监听

onBlackListChanged(callback(ret))

##callback(ret)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
	status : true
}
```

##示例代码

```js
var demo = api.require('nim');
demo.onBlackListChanged(function(ret){
});
```

##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本

<div id="a26"></div>
#**allMyTeams**

获取我的所有群组

allMyTeams(callback(ret,err))

##callback(ret,err)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
	status : true, //布尔型 true|false
	teams:[{
		teamId:,//群ID
		teamName:,//群名称
		type:,//群类型
		owner:,//群拥有者ID, 普通群拥有者就是群创建者,但是高级群可以进行拥有信息的转让
		intro:,//群介绍
		announcement:,//群公告
		memberNumber:,//群成员人数,这个值表示是上次登录后同步下来群成员数据,并不实时变化,必要时需要调用fetchTeamInfo进行刷新
		level:,//群等级,目前群人数主要是限制群人数上限
		createTime:,//群创建时间
		joinMode:,//群验证方式,允许所有人加入:0,需要验证:1,不允许任何人加入:2
		notifyForNewMsg://群消息是否需要通知,这个设置影响群消息的APNS推送
	}]
}
```
err：

- 类型：JSON 对象
- 内部字段：

```js
{
    errorCode: -1,
    errorMessage : ''
}
```
##示例代码

```js
var demo = api.require('nim');
demo.allMyTeams(function(ret, err){
});
```
##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本


<div id="a27"></div>
#**teamById**

本地获取群组信息

teamById({params}, callback(ret,err))

##params

teamId：

- 类型：字符串
- 描述：（必填项）群组Id。

##callback(ret,err)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
	status : true, //布尔型 true|false
	message:[{
		teamId:,//群ID
		teamName:,//群名称
		type:,//群类型
		owner:,//群拥有者ID, 普通群拥有者就是群创建者,但是高级群可以进行拥有信息的转让
		intro:,//群介绍
		announcement:,//群公告
		memberNumber:,//群成员人数,这个值表示是上次登录后同步下来群成员数据,并不实时变化,必要时需要调用fetchTeamInfo进行刷新
		level:,//群等级,目前群人数主要是限制群人数上限
		createTime:,//群创建时间
		joinMode:,//群验证方式,允许所有人加入:0,需要验证:1,不允许任何人加入:2
		notifyForNewMsg://群消息是否需要通知,这个设置影响群消息的APNS推送
	}]
}
```
err：

- 类型：JSON 对象
- 内部字段：

```js
{
    errorCode: -1,
    errorMessage : ''
}
```
##示例代码

```js
var demo = api.require('nim');
demo.teamById({
	teamId : '64430396'
},function(ret, err){
});
```
##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本


<div id="a28"></div>
#**fetchTeamInfo**

远程获取群组信息

fetchTeamInfo({params}, callback(ret,err))

##params

teamId：

- 类型：字符串
- 描述：（必填项）群组Id。

##callback(ret,err)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
	status : true, //布尔型 true|false
	message:[{
		teamId:,//群ID
		teamName:,//群名称
		type:,//群类型
		owner:,//群拥有者ID, 普通群拥有者就是群创建者,但是高级群可以进行拥有信息的转让
		intro:,//群介绍
		announcement:,//群公告
		memberNumber:,//群成员人数,这个值表示是上次登录后同步下来群成员数据,并不实时变化,必要时需要调用fetchTeamInfo进行刷新
		level:,//群等级,目前群人数主要是限制群人数上限
		createTime:,//群创建时间
		joinMode:,//群验证方式,允许所有人加入:0,需要验证:1,不允许任何人加入:2
		notifyForNewMsg://群消息是否需要通知,这个设置影响群消息的APNS推送
	}]
}
```
err：

- 类型：JSON 对象
- 内部字段：

```js
{
    errorCode: -1,
    errorMessage : ''
}
```
##示例代码

```js
var demo = api.require('nim');
demo.fetchTeamInfo({
	teamId : '64430396'
},function(ret, err){
});
```
##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本


<div id="a29"></div>
#**createTeam**

创建群组

createTeam({params}, callback(ret,err))

##params

name：

- 类型：字符串
- 描述：（必填项）群名称。

type：

- 类型：数字型
- 描述：（可选项）创建群类别,普通群:0,高级群:1,默认为普通群.
- 默认值:0

joinMode：

- 类型：数字型
- 描述：（可选项）群验证方式,只有高级群才有群验证模式,普通群一律不需要验证.默认为不需要,允许所有人加入:0,需要验证:1,不允许任何人加入:2;
- 默认值:0

postscript：

- 类型：字符串
- 描述：（可选项）邀请附言,当创建的群为高级群需要带上,普通群没有认证过程,所以不需要;

intro：

- 类型：字符串
- 描述：（可选项）群介绍,可选参数

announcement：

- 类型：字符串
- 描述：（可选项）群公告,可选参数

users：

- 类型：数组
- 描述：（必填项）邀请群成员.不能为空,不邀请人时传自己的userId; 当创建普通群时,必须要添加一个其它成员.

##callback(ret,err)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
	status : true, //布尔型 true|false
	message:[{
		teamId://群ID
	}]
}
```
err：

- 类型：JSON 对象
- 内部字段：

```js
{
    errorCode: -1,
    errorMessage : ''
}
```
##示例代码

```js
var demo = api.require('nim');
demo.createTeam({
	name : '2001',
	type : 0,
	joinMode : 0,
	postscript : '',
	intro : '',
	announcement : '',
	users : ['1002']
},function(ret, err){
});
```
##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本

<div id="a30"></div>
#**addUsers**

邀请用户入群

addUsers({params}, callback(ret,err))

##params

teamId：

- 类型：字符串
- 描述：（必填项）群Id。

users：

- 类型：数组
- 描述：（必填项）userId组成的数组.

postscript：

- 类型：字符串
- 描述：（可选项）邀请附言 (仅iOS有效)。

##callback(ret,err)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
	status : true //布尔型 true|false
}
```
err：

- 类型：JSON 对象
- 内部字段：

```js
{
    errorCode: -1,
    errorMessage : ''
}
```
##示例代码

```js
var demo = api.require('nim');
demo.addUsers({
	teamId : '64430396',
	users : ['1002']
},function(ret, err){
});
```

##补充说明
1、请求完成后，如果是普通群，被邀请者将直接入群；如果是高级群，云信服务器会下发一条系统消息到目标用户，目标用户可以选择同意或者拒绝入群。
2、高级群不能直接拉人，发出邀请成功会返回810，此处应该认为邀请已发出

##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本


<div id="a31"></div>
#**acceptInviteWithTeam**

同意群邀请(仅限高级群)

acceptInviteWithTeam({params}, callback(ret,err))

##params

teamId：

- 类型：字符串
- 描述：（必填项）群Id。

invitorId：

- 类型：字符串
- 描述：（必填项）邀请者Id(不是被邀请者)。

##callback(ret,err)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
	status : true //布尔型 true|false
}
```
err：

- 类型：JSON 对象
- 内部字段：

```js
{
    errorCode: -1,
    errorMessage : ''
}
```
##示例代码

```js
var demo = api.require('nim');
demo.acceptInviteWithTeam({
	teamId : '64430396',
	invitorId : '1001'
},function(ret, err){
});
```

##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本


<div id="a32"></div>
#**rejectInviteWithTeam**

拒绝群邀请(仅限高级群)

rejectInviteWithTeam({params}, callback(ret,err))

##params

teamId：

- 类型：字符串
- 描述：（必填项）群Id。

invitorId：

- 类型：字符串
- 描述：（必填项）邀请者Id(不是被邀请者)。

rejectReason：

- 类型：字符串
- 描述：（可选项）拒绝原因。

##callback(ret,err)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
	status : true //布尔型 true|false
}
```
err：

- 类型：JSON 对象
- 内部字段：

```js
{
    errorCode: -1,
    errorMessage : ''
}
```
##示例代码

```js
var demo = api.require('nim');
demo.rejectInviteWithTeam({
	teamId : '64430396',
	invitorId : '1001',
	rejectReason :''
},function(ret, err){
});
```

##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本

<div id="a33"></div>
#**applyToTeam**

用户主动申请加群（仅限高级群）

applyToTeam({params}, callback(ret,err))

##params

teamId：

- 类型：字符串
- 描述：（必填项）群Id。

message：

- 类型：字符串
- 描述：（可选项）加群信息.

##callback(ret,err)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
	status : true //布尔型 true|false
}
```
err：

- 类型：JSON 对象
- 内部字段：

```js
{
    errorCode: -1,
    errorMessage : ''
}
```
##示例代码

```js
var demo = api.require('nim');
demo.applyToTeam({
	teamId : '64430396',
	message : '我要加群'
},function(ret, err){
});
```

##补充说明
errorCode: 808:已经在群里,809:申请等待通过.

##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本


<div id="a34"></div>
#**passApplyToTeam**

通过申请(仅限高级群)

passApplyToTeam({params}, callback(ret,err))

##params

teamId：

- 类型：字符串
- 描述：（必填项）群Id。

userId：

- 类型：字符串
- 描述：（必填项）用户Id.

##callback(ret,err)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
	status : true //布尔型 true|false
}
```
err：

- 类型：JSON 对象
- 内部字段：

```js
{
    errorCode: -1,
    errorMessage : ''
}
```
##示例代码

```js
var demo = api.require('nim');
demo.passApplyToTeam({
	teamId : '64430396',
	userId : '1002'
},function(ret, err){
});
```

##补充说明
errorCode: 809 : 已经在群里

##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本


<div id="a35"></div>
#**rejectApplyToTeam**

拒绝申请(仅限高级群)

rejectApplyToTeam({params}, callback(ret,err))

##params

teamId：

- 类型：字符串
- 描述：（必填项）群Id。

userId：

- 类型：字符串
- 描述：（必填项）用户Id.

rejectReason：

- 类型：字符串
- 描述：（可选项）拒绝原因.

##callback(ret,err)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
	status : true //布尔型 true|false
}
```
err：

- 类型：JSON 对象
- 内部字段：

```js
{
    errorCode: -1,
    errorMessage : ''
}
```
##示例代码

```js
var demo = api.require('nim');
demo.rejectApplyToTeam({
	teamId : '64430396',
	userId : '1002',
	rejectReason : '拒绝原因'
},function(ret, err){
});
```

##补充说明
errorCode: 509 : 已拒绝

##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本


<div id="a36"></div>
#**updateTeamName**

修改群名称

updateTeamName({params}, callback(ret,err))

##params

teamId：

- 类型：字符串
- 描述：（必填项）群Id。

teamName：

- 类型：字符串
- 描述：（必填项）群组名称.

##callback(ret,err)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
	status : true //布尔型 true|false
}
```
err：

- 类型：JSON 对象
- 内部字段：

```js
{
    errorCode: -1,
    errorMessage : ''
}
```
##示例代码

```js
var demo = api.require('nim');
demo.updateTeamName({
	teamId : '64430396',
	teamName : '群组名称'
},function(ret, err){
});
```

##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本

<div id="a37"></div>
#**updateTeamIntro**

修改群介绍

updateTeamIntro({params}, callback(ret,err))

##params

teamId：

- 类型：字符串
- 描述：（必填项）群Id。

intro：

- 类型：字符串
- 描述：（必填项）群介绍.

##callback(ret,err)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
	status : true //布尔型 true|false
}
```
err：

- 类型：JSON 对象
- 内部字段：

```js
{
    errorCode: -1,
    errorMessage : ''
}
```
##示例代码

```js
var demo = api.require('nim');
demo.updateTeamIntro({
	teamId : '64430396',
	intro : '群介绍'
},function(ret, err){
});
```

##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本


<div id="a38"></div>
#**updateTeamAnnouncement**

修改群公告

updateTeamAnnouncement({params}, callback(ret,err))

##params

teamId：

- 类型：字符串
- 描述：（必填项）群Id。

announcement：

- 类型：字符串
- 描述：（必填项）群公告.

##callback(ret,err)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
	status : true //布尔型 true|false
}
```
err：

- 类型：JSON 对象
- 内部字段：

```js
{
    errorCode: -1,
    errorMessage : ''
}
```
##示例代码

```js
var demo = api.require('nim');
demo.updateTeamAnnouncement({
	teamId : '64430396',
	announcement : '群公告'
},function(ret, err){
});
```

##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本


<div id="a39"></div>
#**updateTeamJoinMode**

修改群验证方式

updateTeamJoinMode({params}, callback(ret,err))

##params

teamId：

- 类型：字符串
- 描述：（必填项）群Id。

joinMode：

- 类型：数字型
- 描述：（必填项）群验证方式,允许所有人加入:0,需要验证:1,不允许任何人加入:2

##callback(ret,err)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
	status : true //布尔型 true|false
}
```
err：

- 类型：JSON 对象
- 内部字段：

```js
{
    errorCode: -1,
    errorMessage : ''
}
```
##示例代码

```js
var demo = api.require('nim');
demo.updateTeamJoinMode({
	teamId : '64430396',
	joinMode : 2
},function(ret, err){
});
```

##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本


<div id="a40"></div>
#**addManagersToTeam**

提升管理员(仅限高级群)

addManagersToTeam({params}, callback(ret,err))

##params

teamId：

- 类型：字符串
- 描述：（必填项）群Id。

users：

- 类型：数组
- 描述：（必填项）userId组成的数组

##callback(ret,err)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
	status : true //布尔型 true|false
}
```
err：

- 类型：JSON 对象
- 内部字段：

```js
{
    errorCode: -1,
    errorMessage : ''
}
```
##示例代码

```js
var demo = api.require('nim');
demo.addManagersToTeam({
	teamId : '64430396',
	users : ['1002']
},function(ret, err){
});
```

##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本


<div id="a41"></div>
#**removeManagersFromTeam**

移除管理员(仅限高级群)

removeManagersFromTeam({params}, callback(ret,err))

##params

teamId：

- 类型：字符串
- 描述：（必填项）群Id。

users：

- 类型：数组
- 描述：（必填项）userId组成的数组

##callback(ret,err)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
	status : true //布尔型 true|false
}
```
err：

- 类型：JSON 对象
- 内部字段：

```js
{
    errorCode: -1,
    errorMessage : ''
}
```
##示例代码

```js
var demo = api.require('nim');
demo.removeManagersFromTeam({
	teamId : '64430396',
	users : ['1002']
},function(ret, err){
});
```

##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本


<div id="a42"></div>
#**transferManagerWithTeam**

转让群(仅限高级群)

transferManagerWithTeam({params}, callback(ret,err))

##params

teamId：

- 类型：字符串
- 描述：（必填项）群Id。

newOwnerId：

- 类型：字符串
- 描述：（必填项）新群主ID

isLeave：

- 类型：布尔型
- 描述：（可选项）是否同时离开群组,true离开
- 默认值: false

##callback(ret,err)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
	status : true	//布尔型 true|false
}
```
err：

- 类型：JSON 对象
- 内部字段：

```js
{
    errorCode: -1,
    errorMessage : ''
}
```
##示例代码

```js
var demo = api.require('nim');
demo.transferManagerWithTeam({
	teamId : '64430396',
	newOwnerId : '1002',
	isLeave : false
},function(ret, err){
});
```

##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本


<div id="a43"></div>
#**fetchTeamMembers**

获取群成员

fetchTeamMembers({params}, callback(ret,err))

##params

teamId：

- 类型：字符串
- 描述：（必填项）群Id。

##callback(ret,err)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
	status : true,	//布尔型 true|false
	message : [{
		teamId : ,//群ID
		userId:,//群成员ID
		invitor:,//邀请者,仅ios支持, android不支持
		type:,//群成员类型,0:普通群员,1:群拥有者,2:群管理员,3:申请加入用户
		nickname:,//群昵称
	}]
}
```
err：

- 类型：JSON 对象
- 内部字段：

```js
{
    errorCode: -1,
    errorMessage : ''
}
```
##示例代码

```js
var demo = api.require('nim');
demo.fetchTeamMembers({
	teamId : '64430396'
},function(ret, err){
});
```

##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本

<div id="a44"></div>
#**quitTeam**

用户退群

quitTeam({params}, callback(ret,err))

##params

teamId：

- 类型：字符串
- 描述：（必填项）群Id。

##callback(ret,err)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
	status : true	//布尔型 true|false
}
```
err：

- 类型：JSON 对象
- 内部字段：

```js
{
    errorCode: -1,
    errorMessage : ''
}
```
##示例代码

```js
var demo = api.require('nim');
demo.quitTeam({
	teamId : '64430396'
},function(ret, err){
});
```

##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本


<div id="a45"></div>
#**kickUsers**

踢出用户

kickUsers({params}, callback(ret,err))

##params

teamId：

- 类型：字符串
- 描述：（必填项）群Id。

users：

- 类型：数组
- 描述：（必填项）userId组成的数组

##callback(ret,err)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
	status : true	//布尔型 true|false
}
```
err：

- 类型：JSON 对象
- 内部字段：

```js
{
    errorCode: -1,
    errorMessage : ''
}
```
##示例代码

```js
var demo = api.require('nim');
demo.kickUsers({
	teamId : '64430396',
	users : ['1002']
},function(ret, err){
});
```
##补充说明

被踢出的用户相关会话信息仍然会保留,但不再能接收关于此群的消息. 当前android只支持每次踢一个用户,故参数users对应的只能是一个用户id.

##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本


<div id="a46"></div>
#**dismissTeam**

解散群

dismissTeam({params}, callback(ret,err))

##params

teamId：

- 类型：字符串
- 描述：（必填项）群Id。

##callback(ret,err)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
	status : true	//布尔型 true|false
}
```
err：

- 类型：JSON 对象
- 内部字段：

```js
{
    errorCode: -1,
    errorMessage : ''
}
```
##示例代码

```js
var demo = api.require('nim');
demo.dismissTeam({
	teamId : '64430396'
},function(ret, err){
});
```
##补充说明

群解散后,所有群用户关于此群会话会被保留,但是不能能够在此群会话里收发消息

##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本


<div id="a47"></div>
#**updateNotifyStateForTeam**

修改群消息通知状态 (关闭群消息提醒)

updateNotifyStateForTeam({params}, callback(ret,err))

##params

teamId：

- 类型：字符串
- 描述：（必填项）群Id。

notify：

- 类型：布尔型
- 描述：（可选项）notify为 false 时,群内消息将不会有 APNS 通知。
- 默认值: true

##callback(ret,err)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
	status : true	//布尔型 true|false
}
```
err：

- 类型：JSON 对象
- 内部字段：

```js
{
    errorCode: -1,
    errorMessage : ''
}
```
##示例代码

```js
var demo = api.require('nim');
demo.updateNotifyStateForTeam({
	teamId : '64430396',
	notify : false
},function(ret, err){
});
```
##补充说明

群组通知是一种消息类型 ,用户在创建群或者进入群成功之后,任何关于群的变动,云信服务器都会下发一条群通知消息.群通知消息和其他消息一样,可从提供的消息查询接口中获取.
DK 在收到群通知之后,会对本地缓存的群信息做出对应的修改,然后触发与修改相对应的委托事件回调.
群通知是接收型的消息,开发者不应该自己手动去创建和发送群通知消息.
群消息通知设置 SDK 提供了修改群消息通知的接口,上层可以通过设置这个选项以影响群消息的通知行为.当设置 notify 为 false 时,群内消息将不会有 APNS 通知.当然上层也可以使用这一属性来决定收到在线消息时的 APP 表现 (是否响铃等).

##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本


<div id="a48"></div>
#**onIMSystemMessageRecieved**

内置系统通知监听

onIMSystemMessageRecieved(callback(ret))

##callback(ret)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
	status : true
}
```
##示例代码

```js
var demo = api.require('nim');
demo.onIMSystemMessageRecieved(function(ret, err){
});
```

##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本

<div id="a49"></div>
#**fetchSystemNotifications**

获取本地存储的内置系统通知

fetchSystemNotifications({params}, callback(ret,err))

##params

limit：

- 类型：数字型
- 描述：（可选项）最大获取数:默认10条。
- 默认值:10

##callback(ret)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
	status : true,
	notifications : [{
		type:,//申请入群:0,拒绝入群:1,邀请入群:2,拒绝入群邀请:3,添加好友:5
		timestamp:,//时间戳
		sourceID:,//操作者
		targetID:,//目标ID,群ID或者是用户ID
		postscript:,//附言 , 仅iOS支持
		read:,//是否已读
	}]
}
```
##示例代码

```js
var demo = api.require('nim');
demo.fetchSystemNotifications({
	limit : 10
},function(ret, err){
});
```

##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本


<div id="a50"></div>
#**allNotificationsUnreadCount**

获取本地存储的内置系统未读数

allNotificationsUnreadCount(callback(ret))

##callback(ret)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
	status : true,
	count : 11
}
```
##示例代码

```js
var demo = api.require('nim');
demo.allNotificationsUnreadCount(function(ret, err){
});
```

##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本

<div id="a51"></div>
#**deleteAllNotifications**

删除本地存储的全部内置系统通知

deleteAllNotifications(callback(ret))

##callback(ret)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
	status : true
}
```
##示例代码

```js
var demo = api.require('nim');
demo.deleteAllNotifications(function(ret, err){
});
```

##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本


<div id="a52"></div>
#**markAllNotificationsAsRead**

标记本地存储的全部内置系统通知为已读

markAllNotificationsAsRead(callback(ret, err))

##callback(ret, err)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
	status : true	//布尔型 true|false
}
```

err：

- 类型：JSON 对象
- 内部字段：

```js
{
    errorCode: -1,
    errorMessage : ''
}
```

##示例代码

```js
var demo = api.require('nim');
demo.markAllNotificationsAsRead(function(ret, err){
});
```

##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本


<div id="a53"></div>
#**onUserInfoUpdate**

用户个人信息发生变化监听

onUserInfoUpdate(callback(ret))

##callback(ret)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
	nickName :,//用户昵称
	avatarUrl :,//用户头像
	sign:,//用户签名
	gender:,//用户性别0:未知性别,1:男,2:女
	email:,//邮箱
	birth:,//生日
	mobile:,//电话号码
	ext:,//用户自定义扩展字段
	account:,//用户账号
}
```

##示例代码

```js
var demo = api.require('nim');
demo.onUserInfoUpdate(function(ret, err){
});
```

##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本

<div id="a54"></div>
#**userInfo**

获取用户资料

userInfo({params}, callback(ret,err))

##params

userId：

- 类型：字符串
- 描述：（必填项）//用户帐号。

##callback(ret)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
	status : true,
	userId :,//用户帐号
	alias :,//备注名
	notifyForNewMsg:,//是否需要消息提醒
	isInMyBlackList:,//是否在黑名单中
	userInfo : {
		nickName :,//用户昵称
		avatarUrl :,//用户头像
		sign:,//用户签名
		gender:,//用户性别0:未知性别,1:男,2:女
		email:,//邮箱
		birth:,//生日
		ext : ,//扩展信息
		mobile:,//电话号码
		account :,//用户帐号
	}
}
```
err：

- 类型：JSON 对象
- 内部字段：

```js
{
    errorCode: -1,
    errorMessage : ''
}
```

##示例代码

```js
var demo = api.require('nim');
demo.userInfo({
	userId : '1001'
},function(ret, err){
});
```

##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本


<div id="a55"></div>
#**fetchUserInfos**

获取服务器用户资料

fetchUserInfos({params}, callback(ret,err))

##params

userIds：

- 类型：数组
- 描述：（必填项）用户id列表。

##callback(ret)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
	status : true,
	message : [{
		nickName :,//用户昵称
		avatarUrl :,//用户头像
		sign:,//用户签名
		gender:,//用户性别0:未知性别,1:男,2:女
		email:,//邮箱
		birth:,//生日
		ext : ,//扩展信息
		mobile:,//电话号码
		account :,//用户帐号
	}]
}
```
err：

- 类型：JSON 对象
- 内部字段：

```js
{
    errorCode: -1,
    errorMessage : ''
}
```

##示例代码

```js
var demo = api.require('nim');
demo.fetchUserInfos({
	userId : '1001'
},function(ret, err){
});
```

##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本


<div id="a56"></div>
#**updateMyUserInfo**

更新当前用户信息

updateMyUserInfo({params}, callback(ret,err))

##params

nickname：

- 类型：字符串
- 描述：（可选项）用户昵称。

avatar：

- 类型：字符串
- 描述：（可选项）用户头像 (仅支持http地址的头像地址，开发者自行验证控制)。

sign：

- 类型：字符串
- 描述：（可选项）用户签名。

gender：

- 类型：数字型
- 描述：（可选项）用户性别 0:未知 ,1:男 ,2:女。
- 默认值:1

email：

- 类型：字符串
- 描述：（可选项）只支持合法邮箱 (开发者自行验证控制)。

birth：

- 类型：字符串
- 描述：（可选项）用户生日yyyy-MM-dd (开发者自行验证控制)。

mobile：

- 类型：字符串
- 描述：（可选项）合法手机号 (开发者自行验证控制)。

ext：

- 类型：JSON对象
- 描述：（可选项）拓展字段。

##callback(ret, err)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
	status : true  //布尔型 true|false
}
```
err：

- 类型：JSON 对象
- 内部字段：

```js
{
    errorCode: -1,
    errorMessage : ''
}
```

##示例代码

```js
var demo = api.require('nim');
demo.updateMyUserInfo({
	nickname : '昵称',
	avatar : '',
	sign : '',
	gender : 1,
	email : '',
	birth : '',
	mobile : '',
	ext : {
		key : 'key1'
	}
},function(ret, err){
});
```

##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本


<div id="a57"></div>
#**myFriends**

获取好友列表

myFriends(callback(ret))

##callback(ret)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
	status : true,  //布尔型 true|false
	users : [{
		nickName :,//用户昵称
		avatarUrl :,//用户头像
		sign:,//用户签名
		gender:,//用户性别0:未知性别,1:男,2:女
		email:,//邮箱
		birth:,//生日
		ext : ,//扩展信息
		mobile:,//电话号码
		account :,//用户帐号
	}]
}
```

##示例代码

```js
var demo = api.require('nim');
demo.myFriends(function(ret){
});
```

##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本


<div id="a58"></div>
#**requestFriend**

好友请求

requestFriend({params}, callback(ret,err))

##params

userId：

- 类型：字符串
- 描述：（必填项）目标用户ID。

operation：

- 类型：数字型
- 描述：（可选项）1:添加好友(直接添加为好友,无需验证) 2:请求添加好友 3:通过添加好友请求 4:拒绝添加好友请求
- 默认值:1

message：

- 类型：字符串
- 描述：（可选项）自定义验证消息。

##callback(ret, err)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
	status : true  //布尔型 true|false
}
```
err：

- 类型：JSON 对象
- 内部字段：

```js
{
    errorCode: -1,
    errorMessage : ''
}
```

##示例代码

```js
var demo = api.require('nim');
demo.requestFriend({
	userId : '1002',
	operation : 1,
	message : ''
},function(ret, err){
});
```

##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本


<div id="a59"></div>
#**deleteFriend**

删除好友

deleteFriend({params}, callback(ret,err))

##params

userId：

- 类型：字符串
- 描述：（必填项）目标用户ID。

##callback(ret, err)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
	status : true  //布尔型 true|false
}
```
err：

- 类型：JSON 对象
- 内部字段：

```js
{
    errorCode: -1,
    errorMessage : ''
}
```

##示例代码

```js
var demo = api.require('nim');
demo.deleteFriend({
	userId : '1002'
},function(ret, err){
});
```

##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本



<div id="a60"></div>
#**myBlackList**

获取黑名单成员列表

myBlackList(callback(ret))

##callback(ret)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
	status : true,
	users : [{
		nickName :,//用户昵称
		avatarUrl :,//用户头像
		sign:,//用户签名
		gender:,//用户性别0:未知性别,1:男,2:女
		email:,//邮箱
		birth:,//生日
		ext : ,//扩展信息
		mobile:,//电话号码
		account :,//用户帐号
	}]
}
```

##示例代码

```js
var demo = api.require('nim');
demo.myBlackList(function(ret){
});
```
##补充说明

云信中,黑名单和好友关系是互相独立的,即修改好友关系不会影响黑名单关系,同时,修改黑名单也不会对好友关系进行操作. 黑名单列表有本地缓存,缓存会在登录后与服务器自动进行同步更新.接口返回的是User列表

##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本



<div id="a61"></div>
#**addToBlackList**

添加用户到黑名单

addToBlackList({params}, callback(ret,err))

##params

userId：

- 类型：字符串
- 描述：（必填项）目标用户ID。

##callback(ret, err)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
	status : true  //布尔型 true|false
}
```
err：

- 类型：JSON 对象
- 内部字段：

```js
{
    errorCode: -1,
    errorMessage : ''
}
```

##示例代码

```js
var demo = api.require('nim');
demo.addToBlackList({
	userId : '1002'
},function(ret, err){
});
```

##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本


<div id="a62"></div>
#**removeFromBlackBlackList**

将用户移除黑名单

removeFromBlackBlackList({params}, callback(ret,err))

##params

userId：

- 类型：字符串
- 描述：（必填项）目标用户ID。

##callback(ret, err)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
	status : true  //布尔型 true|false
}
```
err：

- 类型：JSON 对象
- 内部字段：

```js
{
    errorCode: -1,
    errorMessage : ''
}
```

##示例代码

```js
var demo = api.require('nim');
demo.removeFromBlackBlackList({
	userId : '1002'
},function(ret, err){
});
```

##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本


<div id="a63"></div>
#**isUserInBlackList**

判断某用户是否在自己的黑名单中

isUserInBlackList({params}, callback(ret,err))

##params

userId：

- 类型：字符串
- 描述：（必填项）目标用户ID。

##callback(ret,err)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
	status : true   //布尔型 true|false
}
```
err：

- 类型：JSON 对象
- 内部字段：

```js
{
    errorCode: -1,
    errorMessage : ''
}
```
##示例代码

```js
var demo = api.require('nim');
demo.isUserInBlackList({
	userId : '1002'
},function(ret,err){
});
```

##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本


<div id="a64"></div>
#**myMuteUserList**

获取静音成员列表

myMuteUserList(callback(ret))

##callback(ret)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
	status : true,
	users : [{
		nickName :,//用户昵称
		avatarUrl :,//用户头像
		sign:,//用户签名
		gender:,//用户性别0:未知性别,1:男,2:女
		email:,//邮箱
		birth:,//生日
		ext : ,//扩展信息
		mobile:,//电话号码
		account :,//用户帐号
	}]
	
}
```

##示例代码

```js
var demo = api.require('nim');
demo.myMuteUserList(function(ret){
});
```

##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本


<div id="a65"></div>
#**updateNotifyStateForUser**

设置消息提醒

updateNotifyStateForUser({params}, callback(ret,err))

##params

userId：

- 类型：字符串
- 描述：（必填项）目标用户ID。

notify：

- 类型：布尔型
- 描述：（可选项）是否开启消息提醒。（提醒:true|不提醒:false）
- 默认值: true

##callback(ret,err)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
	status : true   //布尔型 true|false
}
```
err：

- 类型：JSON 对象
- 内部字段：

```js
{
    errorCode: -1,
    errorMessage : ''
}
```
##示例代码

```js
var demo = api.require('nim');
demo.updateNotifyStateForUser({
	userId : '1002',
	notify : true
},function(ret,err){
});
```

##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本




<div id="a66"></div>
#**notifyForNewMsgForUser**

判断是否需要消息通知

notifyForNewMsgForUser({params}, callback(ret,err))

##params

userId：

- 类型：字符串
- 描述：（可选项）目标用户ID。

##callback(ret,err)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
	status : true   //布尔型 true|false
}
```
err：

- 类型：JSON 对象
- 内部字段：

```js
{
    errorCode: -1,
    errorMessage : ''
}
```
##示例代码

```js
var demo = api.require('nim');
demo.notifyForNewMsgForUser({
	userId : '1002'
},function(ret,err){
});
```

##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本


<div id="a67"></div>
#**onChatRoomStatusChanged**

聊天室在线状态变化的监听

onChatRoomStatusChanged(callback(ret))

##callback(ret)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
	roomId : '',//聊天室id
	status : ,//聊天室在线状态:
		//0	正在进入
		//1	进入聊天室成功
		//2	进入聊天室失败
		//3	和聊天室失去链接
}
```

##示例代码

```js
var demo = api.require('nim');
demo.onChatRoomStatusChanged(function(ret,err){
});
```

##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本


<div id="a68"></div>
#**onChatRoomKickOutEvent**

被踢出聊天室的监听

onChatRoomKickOutEvent(callback(ret))

##callback(ret)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
	roomId :,//聊天室id
	code :,//被踢出事件代码:
		//1	聊天室已经被解散
		//2	被管理员踢出
		//3	被其他端踢出
		//4	当前连接状态异常
		//5	被加黑了
}
```

##示例代码

```js
var demo = api.require('nim');
demo.onChatRoomKickOutEvent(function(ret,err){
});
```
##补充说明

当用户被主播或者管理员踢出聊天室、聊天室被关闭(被解散),会收到通知.
注意:收到被踢出通知后,不需要再调用退出聊天室接口,SDK 会负责聊天室的退出工作.可以在踢出通知中做相关缓存的清理工作和界面操作.

##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本


<div id="a69"></div>
#**enterChatRoom**

用户加入聊天室

enterChatRoom({params}, callback(ret,err))

##params

roomId：

- 类型：字符串
- 描述：（必填项）聊天室Id。

nickName：

- 类型：字符串
- 描述：（可选项）用户在聊天室中的呢称。

avatar：

- 类型：字符串
- 描述：（可选项）头像url。

extension：

- 类型：json对象
- 描述：（可选项）扩展字段,进入聊天室后展示用户信息的扩展字段,长度限制4K 。

notifyExtension：

- 类型：json对象
- 描述：（可选项）通知的扩展字段,进入聊天室通知消息扩展字段,长度限制1K(进入聊天室后,聊天室成员都会收到一条通知消息)。

##callback(ret,err)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
	status : true   //布尔型 true|false
}
```
err：

- 类型：JSON 对象
- 内部字段：

```js
{
    errorCode: -1,
    errorMessage : ''
}
```

##示例代码

```js
var demo = api.require('nim');
demo.enterChatRoom({
	roomId : '3002',
	nickName : '小飞',
	avatar : '',
	extension : {key : 'key1'},
	notifyExtension : {key : 'key2'},
},function(ret,err){
});
```
##补充说明

创建聊天室必须服务端创建: 参考[服务端API文档-聊天室](http://dev.netease.im/docs/product/IM即时通讯/服务端API文档?#聊天室)

##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本



<div id="a70"></div>
#**exitChatRoom**

用户退出聊天室

exitChatRoom({params}, callback(ret,err))

##params

roomId：

- 类型：字符串
- 描述：（必填项）聊天室Id。

##callback(ret,err)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
	status : true   //布尔型 true|false
}
```
err：

- 类型：JSON 对象
- 内部字段：

```js
{
    errorCode: -1,
    errorMessage : ''
}
```

##示例代码

```js
var demo = api.require('nim');
demo.exitChatRoom({
	roomId : '3002'
},function(ret,err){
});
```

##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本


<div id="a71"></div>
#**getChatRoomHistoryMsg**

获取聊天室历史消息

getChatRoomHistoryMsg({params}, callback(ret,err))

##params

roomId：

- 类型：字符串
- 描述：（必填项）聊天室Id。

startTime：

- 类型：Long型
- 描述：（可选项）起始时间(单位毫秒)。
- 默认值:0

startTime：

- 类型：数字型
- 描述：（可选项）消息条数。
- 默认值:10

##callback(ret,err)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
	status : true,   //布尔型 true|false
	message : [{
		messageId:, //消息ID,唯一标识
		    messageType:, //消息类型,
		    		//0	文本类型消息
					//1	图片类型消息
					//2	声音类型消息
					//3	视频类型消息
					//4	位置类型消息
					//5	通知类型消息
					//6	文件类型消息
					//10	提醒类型消息
					//100	自定义类型消息
		    from:, //消息来源
		    text:, //消息文本
		    timestamp:, //消息发送时间
		    sessionId:, //会话ID,如果当前session为team,则sessionId为teamId,如果是P2P则为对方帐号
		    sessionType:, //会话类型,0:单聊 1:群聊 2:聊天室 
		    path:,//本地路径
		    url:,//远程路径
		    fileLength:,//文件大小(音频文件没有这个参数)
		    thumbPath://缩略图本地路径(图片)
		    duration:,//时长,毫秒为单位(音频,视频)
		    coverUrl:,//视频封面的远程路径(视频)
		    latitude:,//纬度(位置消息)
		    longitude:,//经度(位置消息)
		    title:,//标题(位置消息)
		    displayName:,//显示名称(文件，图片，视频)
		    ext : ,//扩展消息
		    eventType : ,//操作发起者ID
		    operator : ,//操作类型
		    targets : ,//被操作者ID列表
		    notificationType : ,//通知类型
	}]
}
```
err：

- 类型：JSON 对象
- 内部字段：

```js
{
    errorCode: -1,
    errorMessage : ''
}
```

##示例代码

```js
var demo = api.require('nim');
demo.getChatRoomHistoryMsg({
	roomId : '3002',
	startTime : 0,
	limit : 10
},function(ret,err){
});
```

##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本



<div id="a72"></div>
#**getChatRoomInfo**

获取聊天室基本信息

getChatRoomInfo({params}, callback(ret,err))

##params

roomId：

- 类型：字符串
- 描述：（必填项）聊天室Id。

##callback(ret,err)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
	status : true,   //布尔型 true|false
	message : {
		roomId :, //聊天室id
		name : , //聊天室名称
		creator:,//创建者id
		announcement:,//公告
		onLineUserCount:,//当前在线人数
		broadcastUrl:,//广播流url
		extention:,//扩展属性
	}
}
```
err：

- 类型：JSON 对象
- 内部字段：

```js
{
    errorCode: -1,
    errorMessage : ''
}
```

##示例代码

```js
var demo = api.require('nim');
demo.getChatRoomInfo({
	roomId : '3002'
},function(ret,err){
});
```

##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本


<div id="a73"></div>
#**getChatRoomMembers**

获取聊天室成员

getChatRoomMembers({params}, callback(ret,err))

##params

roomId：

- 类型：字符串
- 描述：（必填项）聊天室Id。

type：

- 类型：数字型
- 描述：（可选项）类别(0:聊天室在线的固定成员, 1: 聊天室临时成员, 2: 在线固定成员)。
- 默认值:0

time：

- 类型：Long型
- 描述：（可选项）起始时间(单位毫秒) IOS无效。
- 默认值:0

limit：

- 类型：数字型
- 描述：（可选项）条数, 最大100。
- 默认值:10

##callback(ret,err)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
	status : true,   //布尔型 true|false
	message : [{
		userId: ,//用户id
		avatar: ,//用户头像url
		enterTime: ,//进入的时间
		extention:,//进聊天室时提交的扩展字段
		nick: ,//呢称
		roomId: ,//聊天室id
		updateTime: ,//更新时间
		isInBlackList: ,//是否在黑名单中
		isMuted: ,//是否被禁言 
		isOnline:,//是否在线
		isValid:,//是否有效 
		memberType:,//成员类型, 游客: -2, 受限用户: -1, 普通用户:0, 创建者:1, 管理员: 2
	}]
}
```
err：

- 类型：JSON 对象
- 内部字段：

```js
{
    errorCode: -1,
    errorMessage : ''
}
```

##示例代码

```js
var demo = api.require('nim');
demo.getChatRoomMembers({
	roomId : '3002',
	type : 0,
	time : 0,
	limit : 10
},function(ret,err){
});
```

##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本


<div id="a74"></div>
#**getChatRoomMembersByIds**

批量获取指定成员在聊天室中的信息

getChatRoomMembersByIds({params}, callback(ret,err))

##params

roomId：

- 类型：字符串
- 描述：（必填项）聊天室Id。

userIds：

- 类型：数组
- 描述：（必填项）用户id列表。

##callback(ret,err)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
	status : true, //布尔型 true|false
	message : [{
		userId: ,//用户id
		avatar: ,//用户头像url
		enterTime: ,//进入的时间
		extention:,//进聊天室时提交的扩展字段
		nick: ,//呢称
		roomId: ,//聊天室id
		updateTime: ,//更新时间
		isInBlackList: ,//是否在黑名单中
		isMuted: ,//是否被禁言 
		isOnline:,//是否在线
		isValid:,//是否有效 
		memberType:,//成员类型, 游客: -2, 受限用户: -1, 普通用户:0, 创建者:1, 管理员: 2
	}]
}
```
err：

- 类型：JSON 对象
- 内部字段：

```js
{
    errorCode: -1,
    errorMessage : ''
}
```

##示例代码

```js
var demo = api.require('nim');
demo.getChatRoomMembersByIds({
	roomId : '3002',
	userIds : ['1001','1002']
},function(ret,err){
});
```

##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本



<div id="a75"></div>
#**addUserToBlackList**

加入/移出黑名单

addUserToBlackList({params}, callback(ret,err))

##params

roomId：

- 类型：字符串
- 描述：（必填项）聊天室Id。

userId：

- 类型：字符串
- 描述：（必填项）用户的帐号。

isAdd：

- 类型：布尔型
- 描述：（可选项) 将用户加入黑名单.
- 默认值:true

##callback(ret,err)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
	status : true  //布尔型 true|false
}
```
err：

- 类型：JSON 对象
- 内部字段：

```js
{
    errorCode: -1,
    errorMessage : ''
}
```

##示例代码

```js
var demo = api.require('nim');
demo.addUserToBlackList({
	roomId : '3002',
	userIds : '1001',
	isAdd : true
},function(ret,err){
});
```

##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本


<div id="a76"></div>
#**muteUser**

加入用户到禁言名单/取消某用户的禁言

muteUser({params}, callback(ret,err))

##params

roomId：

- 类型：字符串
- 描述：（必填项）聊天室Id。

userId：

- 类型：字符串
- 描述：（必填项）用户的帐号。

isMute：

- 类型：布尔型
- 描述：（可选项）加入用户到禁言名单/取消某用户的禁言
- 默认值:true

##callback(ret,err)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
	status : true	//布尔型 true|false
}
```
err：

- 类型：JSON 对象
- 内部字段：

```js
{
    errorCode: -1,
    errorMessage : ''
}
```

##示例代码

```js
var demo = api.require('nim');
demo.muteUser({
	roomId : '3002',
	userIds : '1001',
	isMute : true
},function(ret,err){
});
```

##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本


<div id="a77"></div>
#**setAdmin**

设置/取消管理员

setAdmin({params}, callback(ret,err))

##params

roomId：

- 类型：字符串
- 描述：（必填项）聊天室Id。

userId：

- 类型：字符串
- 描述：（必填项）用户的帐号。

isAdmin：

- 类型：布尔型
- 描述：（可选项）将用户设置/取消管理员.
- 默认值:true

##callback(ret,err)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
	status : true	//布尔型 true|false
}
```
err：

- 类型：JSON 对象
- 内部字段：

```js
{
    errorCode: -1,
    errorMessage : ''
}
```

##示例代码

```js
var demo = api.require('nim');
demo.setAdmin({
	roomId : '3002',
	userIds : '1001',
	isAdmin : true
},function(ret,err){
});
```

##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本


<div id="a78"></div>
#**setNormal**

设置/移除普通成员

setNormal({params}, callback(ret,err))

##params

roomId：

- 类型：字符串
- 描述：（必填项）聊天室Id。

userId：

- 类型：字符串
- 描述：（必填项）用户的帐号。

isNormal：

- 类型：布尔型
- 描述：（可选项）将用户设置/移除普通成员.
- 默认值:true

##callback(ret,err)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
	status : true	//布尔型 true|false
}
```
err：

- 类型：JSON 对象
- 内部字段：

```js
{
    errorCode: -1,
    errorMessage : ''
}
```

##示例代码

```js
var demo = api.require('nim');
demo.setNormal({
	roomId : '3002',
	userIds : '1001',
	isNormal : true
},function(ret,err){
});
```

##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本



<div id="a79"></div>
#**kickMemberFromChatRoom**

从聊天室中移除某个用户

kickMemberFromChatRoom({params}, callback(ret,err))

##params

roomId：

- 类型：字符串
- 描述：（必填项）聊天室Id。

userId：

- 类型：字符串
- 描述：（必填项）用户的帐号。

reason：

- 类型：字符串
- 描述：（可选项）原因。

##callback(ret,err)

ret：

- 类型：JSON 对象
- 内部字段：

```js
{
	status : true  //布尔型 true|false
}
```
err：

- 类型：JSON 对象
- 内部字段：

```js
{
    errorCode: -1,
    errorMessage : ''
}
```

##示例代码

```js
var demo = api.require('nim');
demo.kickMemberFromChatRoom({
	roomId : '3002',
	userIds : '1001',
	reason : '原因'
},function(ret,err){
});
```

##可用性

iOS系统，Android系统

可提供的1.0.0及更高版本


