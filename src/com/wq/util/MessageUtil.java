package com.wq.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;
import com.wq.po.TextMessage;

public class MessageUtil {
	//定义消息类型
	public static final String MSGTYPE_TEXT = "text";
	public static final String MSGTYPE_NEWS = "news";
	public static final String MSGTYPE_IMAGE = "image";
	public static final String MSGTYPE_VOICE = "voice";
	public static final String MSGTYPE_MUSIC = "music";
	public static final String MSGTYPE_LOCATION = "location";
	public static final String MSGTYPE_LINK = "link";
	public static final String MSGTYPE_EVENT = "event";
	public static final String EVENT_SUBSCRIBE = "subscribe";
	public static final String EVENT_SCAN = "SCAN";
	public static final String EVENT_LOCATION = "location_select";
	public static final String EVENT_CLICK = "CLICK";
	public static final String EVENT_VIEW = "VIEW";
	public static final String EVENT_SCANCODE_PUSH = "scancode_push";
	
	/**
	 * 1.xml 转成 Map集合
	 * @param req
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 */
	public static  Map<String,String> xmlToMap(HttpServletRequest req) throws IOException, DocumentException{
		Map<String,String> map=new HashMap<String, String>();
		SAXReader reader=new SAXReader();
		InputStream ins=req.getInputStream();
		Document doc=reader.read(ins);
		Element root=doc.getRootElement();
		List<Element> list=root.elements();
		for(Element e : list){
			map.put(e.getName(), e.getText());
		}
		ins.close();
		return map;
	}
	/**
	 * 2.将文本消息对象转换成　xml
	 * @param textMessage
	 * @return
	 */
	public static String textMessageToXml(TextMessage textMessage){
		XStream stream=new XStream();
		stream.alias("xml", textMessage.getClass());
		return stream.toXML(textMessage);
		
	}
	/**
	 * 3.主菜单
	 */
	public static String menuText(){
		StringBuffer sb=new StringBuffer();
		sb.append("欢迎您关注小吴庆同学,请按照菜单提示进行操作：\n\n");
		sb.append("1.公众号介绍\n");
		sb.append("2.开发者介绍\n\n");
		sb.append("回复\"？\"调出此菜单。");
		return sb.toString();
	}
	/**
	 * 4.组合文本消息
	 * @param toUserName
	 * @param fromUserName
	 * @param content
	 * @return
	 */
	public static String initText(String toUserName,String fromUserName,String content){
		TextMessage textMessage=new TextMessage();
		textMessage.setFromUserName(toUserName);
		textMessage.setToUserName(fromUserName);
		textMessage.setContent(content);
		textMessage.setMsgType(MessageUtil.MSGTYPE_TEXT);
		textMessage.setCreateTime(String.valueOf(new Date().getTime()));
		return textMessageToXml(textMessage);
	}
	/**
	 * 5.第一个子菜单：1--公众号介绍
	 */
	public static String firstSubMenu(){
		StringBuffer sb=new StringBuffer();
		sb.append("此公众号为测试公众号");
		return sb.toString();
	}
	
	/**
	 * 6.第二个子菜单：2--开发者介绍
	 */
	public static String secondSubMenu(){
		StringBuffer sb=new StringBuffer();
		sb.append("此开发人员叫小吴庆，她很帅呢!");
		return sb.toString();
	}
}
