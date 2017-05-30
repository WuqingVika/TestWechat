# TestWechat
屎香香的微信公众号开发之路:smirk:  

## 第一章 屎香香踩过的坑 :blush:
 - 1.报错“org.xmlpull.v1.XmlPullParserException”
     - xstream有依赖的，需要同时加入xmlpull_xx.jar就行了
 - 2.发送消息没反应
     - 检查返回消息文本元素节点大小写，如[TextMessage.java](https://github.com/WuqingVika/TestWechat/blob/master/src/com/wq/po/TextMessage.java)里面所有字段大写。我一开始小写的。
 - 3.乱码问题
     - 设置req、resp为utf-8，详见[WechatServlet.java](https://github.com/WuqingVika/TestWechat/blob/master/src/com/wq/servlet/WechatServlet.java)中的doPost方法前两行代码。
 
 
## 第二章 基础入门
 - 1.公众号开发环境准备
     - natapp注册、实名认证,下载natapp.exe;
     - 下载学习链接https://natapp.cn/ 购买免费隧道
     - 下载config.ini放到natapp.exe文件同级目录，进行accessToken配置
     - 运行natapp.exe 如果出现相应的信息代表映射成功了。验证方法：可以将localhost:8080替换成映射后的地址能访问就行了。

 - 2.反馈发送消息功能
     - 编写校验Signature类：[CheckUtil.java](https://github.com/WuqingVika/TestWechat/blob/master/src/com/wq/util/CheckUtil.java)(包含checkSignature、sha1加密方法)
     - 编写[WechatServlet.java](https://github.com/WuqingVika/TestWechat/blob/master/src/com/wq/servlet/WechatServlet.java)(包含doget、dopost方法)
     - 编写[MessageUtil.java](https://github.com/WuqingVika/TestWechat/blob/master/src/com/wq/util/MessageUtil.java),包含：xml转成 Map集合、将文本消息 转换成xml的方法。
     - 利用自己微信向公众号发送文本，能反馈回发送的文本消息。

 - 3.显示操作菜单
     - 修改[MessageUtil.java](https://github.com/WuqingVika/TestWechat/blob/master/src/com/wq/util/MessageUtil.java)类：(定义消息类型字段、制作主菜单方法、组合文本消息、定义操作项一和二)
     - 设置关键字分别有:1、2和中英文问号，返回对应的消息
