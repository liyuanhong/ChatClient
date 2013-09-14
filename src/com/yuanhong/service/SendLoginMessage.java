package com.yuanhong.service;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SendLoginMessage extends Thread{
	private Socket socket;
	private String address;
	private int port;
	private String message;    //要发送给服务器的本地地址信息
	
	public SendLoginMessage(String address,int port){
		this.address = address;
		this.port = port;	
		try {
			socket=new Socket(address, port);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		try {		
			OutputStreamWriter output = new OutputStreamWriter(socket.getOutputStream());
			output.write(message);
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
