package com.liyuanhong.util;

import org.json.JSONException;
import org.json.JSONObject;

public class AnalyseMessage {
	private int port;
	private String address;
	private String message;
	private int messType;
	private String userName;
	private String sendedUser;
	private int clientPort;
	private String clientAddress;
	
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getMessType() {
		return messType;
	}
	public void setMessType(int messType) {
		this.messType = messType;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getSendedUser() {
		return sendedUser;
	}
	public void setSendedUser(String sendedUser) {
		this.sendedUser = sendedUser;
	}
	public int getClientPort() {
		return clientPort;
	}
	public void setClientPort(int clientPort) {
		this.clientPort = clientPort;
	}
	public String getClientAddress() {
		return clientAddress;
	}
	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}
	
	public AnalyseMessage(String information) {
		try {
			JSONObject json = new JSONObject(information);			
			
			messType = Integer.parseInt(json.get("messType").toString());
			message = json.get("message").toString();
			userName = json.get("userName").toString();
			sendedUser = json.getString("sendedUser").toString();
			clientPort = Integer.parseInt(json.get("clientPort").toString());
			clientAddress = json.get("clientAddress").toString();;
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
	}
}
