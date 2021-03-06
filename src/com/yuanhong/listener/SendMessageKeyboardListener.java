package com.yuanhong.listener;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import org.json.JSONObject;

import com.yuanhong.service.SendMessageThread;
import com.yuanhong.util.Destination;
import com.yuanhong.util.MessageClass;
import com.yuanhong.util.MessageType;
import com.yuanhong.util.SendMessageStyle;
import com.yuanhong.util.UserInfo;

public class SendMessageKeyboardListener extends KeyAdapter{
	private JButton sendMessage;
	private JTextArea messageArea;
	private String loginName;
	private String address;
	private String sendedUser;
	private int port;
	private MessageClass message;        //要发送的消息
	private Destination destination;
	private UserInfo currentUser;          //表示当前聊天用户
	private JFrame window;
	private SendMessageStyle messageStyle;  //消息是群发还是单发
	
	public SendMessageKeyboardListener(JButton sendMessage,JTextArea messageArea,String loginName,
			String address,int port,UserInfo currentUser,JFrame window,SendMessageStyle messageStyle) {
		this.sendMessage = sendMessage;
		this.messageArea = messageArea;
		this.loginName = loginName;
		this.address = address;
		this.port = port;
		this.currentUser = currentUser;
		this.window = window;
		this.messageStyle = messageStyle;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		super.keyPressed(e);
		String messageSend = messageArea.getText();
		if(e.getKeyCode() == KeyEvent.VK_ALT){
			if(messageStyle.getStyle() == 0){
				if(currentUser.getAddress() == null){
					JOptionPane.showMessageDialog(window, "请指定要发送消息的对象", "警告", JOptionPane.ERROR_MESSAGE);
				}else{
					if(messageSend.equals("")){
						
					}else{
						destination = new Destination();
						destination.setAddress(address);
						destination.setPort(port);
						
						message = new MessageClass();		
						message.setDestination(destination);
						message.setUserName(loginName);
//						message.setSendedUser(sendedUser);
						message.setMessage(messageSend);
						message.setMessType(MessageType.DEFAULT);
						message.setSendedUser(currentUser.getName());
						
						
						JSONObject json = new JSONObject(message.getJsonMap());
						messageArea.setText("");		
						
						SendMessageThread thread = new SendMessageThread(json.toString(), address, port);
						thread.start();	
						messageArea.setText("");
					}	
				}
				
			}else if(messageStyle.getStyle() == 1){
				if(messageArea.getText().equals("")){
					
				}else{
					destination = new Destination();
					destination.setAddress(address);
					destination.setPort(port);
					
					message = new MessageClass();		
					message.setDestination(destination);
					message.setUserName(loginName);
					message.setSendedUser(sendedUser);
					message.setMessage(messageArea.getText());
					message.setMessType(MessageType.ALL);
					message.setSendedUser(currentUser.getName());
					
					message.setSendedUser(currentUser.getName());
					
					JSONObject json = new JSONObject(message.getJsonMap());
					messageArea.setText("");
					
					SendMessageThread thread = new SendMessageThread(json.toString(), address, port);
					thread.start();
				}			
			}
		}
	}
}
