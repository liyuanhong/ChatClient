package com.liyuanhong.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.json.JSONObject;

import com.liyuanhong.util.MessageClass;
import com.liyuanhong.util.MessageType;
import com.yuanhong.service.SendMessageThread;
import com.yuanhong.ui.ChatClient;

public class LoginViewConfigerClick extends MouseAdapter{
	private JFrame window;
	private JTextField loginName;
	private JTextField serverAddress;
	private JTextField theServerPort;
	private MessageClass message;                   //要发送的消息
	private int messType = MessageType.LOGIN;
	private int clientPort;                          //本地接收消息的端口
	private JTextField clientPortField;
	
	public LoginViewConfigerClick(JFrame window,JTextField loginName,JTextField serverAddress,JTextField theServerPort,int clientPort,JTextField clientPortField) {
		this.window = window;
		this.loginName = loginName;
		this.serverAddress = serverAddress;
		this.theServerPort = theServerPort;
		this.clientPort = clientPort;
		this.clientPortField = clientPortField;	
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		ChatClient client = new ChatClient(loginName.getText(),serverAddress.getText(),Integer.parseInt(theServerPort.getText()));
		client.showWin();
		
		message = new MessageClass();		
		message.setUserName(loginName.getText());
		message.setMessType(MessageType.LOGIN);
		System.out.println(clientPortField.getText());
		if(clientPortField.isEditable()){
			message.setClientPort(getUserDefinedPort());	
			System.out.println(clientPortField.isEditable() + "-----------");
		}else{
			message.setClientPort(getDefaultPort());
			System.out.println(clientPortField.isEditable() + "+++++++++++++");
		}
		
		JSONObject json = new JSONObject(message.getJsonMap());	
		SendMessageThread thread = new SendMessageThread(json.toString(), serverAddress.getText(), Integer.parseInt(theServerPort.getText()));
		thread.start();
		
		window.dispose();
	}
	
	//判断将要作为获取消息的端口号是否被占用
	public boolean IsLocalPortUsed(int localPort){
		try {
			ServerSocket serSocket = new ServerSocket(localPort);
			serSocket.close();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return true;
		}
	}
	
	//获取默认的端口号
	public int getDefaultPort(){
		if(IsLocalPortUsed(clientPort)){
			do{
				clientPort++;
			}while(IsLocalPortUsed(clientPort));
			return clientPort;
		}else{
			return clientPort;
		}
	}
	
	public int getUserDefinedPort(){
		try{
			clientPort = Integer.parseInt(clientPortField.getText());
			if(1024 > clientPort && clientPort > 49151){
				JOptionPane.showConfirmDialog(window, "请输入1024~49151", "警告", JOptionPane.ERROR_MESSAGE);
				return 0;    //返回0，表示获取端口号错误，重新输入
			}else{
				return clientPort;
			}
		}catch(Exception e){
			JOptionPane.showConfirmDialog(window, "请输入1024~49151的数字", "警告", JOptionPane.ERROR_MESSAGE);
		}
		return 0;            //返回0，表示获取端口号错误，重新输入
	}
}
