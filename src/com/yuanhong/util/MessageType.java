package com.yuanhong.util;

public class MessageType {
	public static int DEFAULT = 0;    //默认的发送消息的形式
	public static int ALL = 1;        //向所有用户发送消息
	public static int LOGOUT = 2;     //某用户退出时，向在线用户发送用户列表更新消息
	public static int LOGIN = 3;      //当某用户登录时，向在线用户发送用户列表更新消息
	public static int SERVERCLOSE = 4;  //标志服务器关闭时的消息
}
