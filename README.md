# TestWechat
屎香香的微信公众号开发之路:smirk:  

## 第一章 基础入门
 - 1.公众号开发环境准备
     - natapp注册、实名认证,下载natapp.exe;
     - 下载学习链接https://natapp.cn/ 购买免费隧道
     - 下载config.ini放到natapp.exe文件同级目录，进行accessToken配置
     - 运行natapp.exe 如果出现相应的信息代表映射成功了。验证方法：可以将localhost:8080替换成映射后的地址能访问就行了。

 - 2.反馈发送消息功能
     - 编写校验Signature类：[CheckUtil.java](https://github.com/WuqingVika/TestWechat/blob/master/src/com/wq/util/CheckUtil.java)(包含checkSignature、sha1加密方法)
     - 编写[WechatServlet.java](https://github.com/WuqingVika/TestWechat/blob/master/src/com/wq/servlet/WechatServlet.java)(包含doget、dopost方法)
     - 编写[MessageUtil.java](https://github.com/WuqingVika/TestWechat/blob/master/src/com/wq/util/MessageUtil.java),包含：xml转成 Map集合、将文本消息 转换成xml的方法。
     
