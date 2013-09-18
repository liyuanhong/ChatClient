package com.yuanhong.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.yuanhong.util.AnalyseMessage;

public class GetMessageThread extends Thread{
	private ServerSocket serSocket;
	private Socket socket;
	private InputStreamReader reader;
	private String sendedUser;
	private JTextArea messageShow;

	private int messType;
	private String message;
	private String userName;
	private int port;
	private String addrress;

	public GetMessageThread( ServerSocket serSocket,JTextArea messageShow) {
		this.serSocket = serSocket;
		this.messageShow = messageShow;
	}

	@Override
	public void run() {
		int len;
		String infomation = "";
		char[] ch = new char[512];
		while (true) {
			try {
				infomation = "";
				socket = serSocket.accept();
				reader = new InputStreamReader(socket.getInputStream());
				while ((len = reader.read(ch)) != -1) {
					infomation = infomation + String.valueOf(ch, 0, len);
				}
				System.out.println(infomation);
				AnalyseMessage analyze = new AnalyseMessage(infomation);
				
				port = analyze.getPort();
				message = analyze.getMessage();
				messType = analyze.getMessType();
				userName = analyze.getUserName();
				sendedUser = analyze.getSendedUser();
				addrress = analyze.getAddress();
				sendedUser = analyze.getSendedUser();
				
				
				dealWithMessage(messType);
				
				reader = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	//对不同的消息类型进行处理
		public void dealWithMessage(int messType){
			switch(messType){
			case 0 : 
				String gotMessage;
				gotMessage = sendedUser + ":\n";
				gotMessage = gotMessage + "       " + message + "\n";
				messageShow.append(gotMessage);
			case 1 :
				
			case 2 :
				
			case 3 :
				
			}
		}
}
