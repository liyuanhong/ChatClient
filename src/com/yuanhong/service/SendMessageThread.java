package com.yuanhong.service;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SendMessageThread extends Thread{
	private String message;
	private Socket socket;
	private String address;
	private int port;
	
	public SendMessageThread(String message,String address,int port) {
		this.message=message;
		this.address=address;
		this.port=port;
		
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
