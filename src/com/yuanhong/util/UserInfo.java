package com.yuanhong.util;

//此类用于存储用户的地址信息，以便于在主要服务中遍历map，实现群发群发
public class UserInfo {
	private int port;
	private String address;
	private int position;
	private String name;
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
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
