package com.yuanhong.listener;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import org.json.JSONObject;

import com.yuanhong.util.MessageClass;
import com.yuanhong.util.MessageType;

public class ClientCloseingListener extends WindowAdapter{
	private String serverAddress;
	private int serverPort;
	private String userName;
	
	public ClientCloseingListener(String serverAddress,int serverPort,String userName) {
		this.serverAddress = serverAddress;
		this.serverPort = serverPort;
		this.userName = userName;
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		super.windowClosing(e);
		MessageClass message = new MessageClass();
		
		message.setMessType(MessageType.LOGOUT);
		message.setUserName(userName);
		
		JSONObject json = new JSONObject(message.getJsonMap());
		Socket socket;
		try {
			socket = new Socket(serverAddress, serverPort);
			OutputStreamWriter output = new OutputStreamWriter(socket.getOutputStream());
			output.write(json.toString());
			output.close();
			socket.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}
