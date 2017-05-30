package com.wq.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;

import com.wq.po.TextMessage;
import com.wq.util.CheckUtil;
import com.wq.util.MessageUtil;

public class WechatServlet extends HttpServlet {
   @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	   //微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
	    String signature=req.getParameter("signature");
		String timestamp=req.getParameter("timestamp");//时间戳
		String nonce=req.getParameter("nonce");//随机数
		String echostr=req.getParameter("echostr");//随机字符串
		PrintWriter out=resp.getWriter();
		if(CheckUtil.checkSignature(signature, timestamp, nonce)){
			out.print(echostr);
		}
	}
   
   @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	   req.setCharacterEncoding("UTF-8");
	   resp.setCharacterEncoding("UTF-8");
	   PrintWriter out=resp.getWriter();
	   try {
			Map<String,String> map=MessageUtil.xmlToMap(req);
			String fromUserName=map.get("FromUserName");
			String toUserName=map.get("ToUserName");
			String msgType=map.get("MsgType");
			String content=map.get("Content");
			String message=null;
			if("text".equals(msgType)){
				TextMessage textMessage=new TextMessage();
				textMessage.setFromUserName(toUserName);
				textMessage.setToUserName(fromUserName);
				textMessage.setContent("您发送的消息是："+content);
				textMessage.setMsgType("text");
				textMessage.setCreateTime(String.valueOf(new Date().getTime()));
				textMessage.toString();
				message=MessageUtil.textMessageToXml(textMessage);
			}
			out.print(message);
			System.out.println(message);
		} catch (DocumentException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
	}
}
