package com.liyuanhong.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.json.JSONObject;

import com.liyuanhong.util.Destination;
import com.liyuanhong.util.MessageClass;
import com.liyuanhong.util.MessageType;

public class SendMessageButtonListener extends MouseAdapter{
	private JButton sendMessage;
	private JTextArea messageArea;
	private String loginName;
	private String address;
	private int port;
	private MessageClass message;        //Ҫ���͵���Ϣ
	private Destination destination;
	
	public SendMessageButtonListener(JButton sendMessage,JTextArea messageArea,String loginName,String address,int port) {
		this.sendMessage = sendMessage;
		this.messageArea = messageArea;
		this.loginName = loginName;
		this.address = address;
		this.port = port;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		destination = new Destination();
		destination.setAddress(address);
		destination.setPort(port);
		
		message = new MessageClass();		
		message.setDestination(destination);
		message.setMessage(messageArea.getText());
		message.setMessType(MessageType.DEFAULT);
		
		JSONObject json = new JSONObject(message.getJsonMap());
		
		System.out.println(json.toString());
	}
}