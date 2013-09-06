package com.liyuanhong.util;

public class MessageClass {
	private int messType;
	private Destination destination;
	private String message;
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
}
