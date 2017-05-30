package com.wq.util;

import java.io.IOException;
import java.io.InputStream;
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
	/**
	 * xml 转成 Map集合
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
	 * 将文本消息 转换成　xml
	 * @param textMessage
	 * @return
	 */
	public static String textMessageToXml(TextMessage textMessage){
		XStream stream=new XStream();
		stream.alias("xml", textMessage.getClass());
		return stream.toXML(textMessage);
		
	}
}
