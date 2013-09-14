package com.yuanhong.util;

import java.util.HashMap;
import java.util.Map;

public class MessageClass {
	private int messType;
	private Destination destination;
	private String message;
	private String userName;
	private String sendedUser;
	private int clientPort;
	private String clientAddress;
	
	public int getMessType() {
		return messType;
	}
	public void setMessType(int messType) {
		this.messType = messType;
	}
	public Destination getDestination() {
		return destination;
	}
	public void setDestination(Destination destination) {
		this.destination = destination;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
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
	//将json对象转换为字符串
	public String getJsonString(){
		return getJsonMap().toString();
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
	//将本类中的字段转换为json对象
	public Map<String, Object> getJsonMap(){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> subMap = new HashMap<String, Object>();
		
		if(destination != null){
			subMap.put("address", destination.getAddress());
			subMap.put("port", destination.getPort());
		}

		map.put("messType", this.getMessType());
		map.put("destination", subMap);
		map.put("message", this.getMessage());
		map.put("userName", this.getUserName());
		map.put("sendedUser", this.getSendedUser());
		map.put("clientPort", clientPort);
		map.put("clientAddress", clientAddress);
		
		return map;
	}
}
