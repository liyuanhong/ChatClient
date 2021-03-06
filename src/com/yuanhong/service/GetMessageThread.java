package com.yuanhong.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.json.JSONObject;

import com.yuanhong.util.AnalyseMessage;
import com.yuanhong.util.MessageType;
import com.yuanhong.util.UserInfo;

public class GetMessageThread extends Thread{
	private ServerSocket serSocket;
	private Socket socket;
	private InputStreamReader reader;
	private String sendedUser;
	private JTextArea messageShow;
	private Map<String, UserInfo> allUserMap;
	private JList userListArea;
	private JFrame window;
	private JScrollPane scrollPane;

	private int messType;
	private String message;
	private String userName;
	private int port;
	private String addrress;

	public GetMessageThread( ServerSocket serSocket,JTextArea messageShow,
			Map<String, UserInfo> allUserMap,JList userListArea,JFrame window,
			JScrollPane scrollPane) {
		this.serSocket = serSocket;
		this.messageShow = messageShow;
		this.allUserMap = allUserMap;
		this.userListArea = userListArea;
		this.window = window;
		this.scrollPane = scrollPane;
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
				AnalyseMessage analyze = new AnalyseMessage(infomation);
				
				port = analyze.getPort();
				message = analyze.getMessage();
				messType = analyze.getMessType();
				userName = analyze.getUserName();
				sendedUser = analyze.getSendedUser();
				addrress = analyze.getAddress();
				sendedUser = analyze.getSendedUser();
				
				
				
				dealWithMessage(messType,infomation);
				
				reader = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	//对不同的消息类型进行处理
		public void dealWithMessage(int messType,String infomation){
			switch(messType){
			case 0 : 
				String gotMessage;
				gotMessage = userName + ":\n";
				gotMessage = gotMessage + "       " + message + "\n";
				messageShow.append(gotMessage);
				messageShow.setCaretPosition(messageShow.getText().length());
				break;
			case 1 :
				break;
			case 2 :
				UpdateLogoutUserList(infomation);
				break;
			case 3 :
				UpdateUserList(infomation);
				break;
			case 4 :
				dealWithServerClosed();
				break;
			}		
		}
		
		//更新客户端用户列表
		public void UpdateUserList(String infomation){
			parseUserList(infomation, allUserMap);
			
			Vector userInfoList = new Vector<String>();
			for(Iterator ite = allUserMap.keySet().iterator();ite.hasNext();){
				userInfoList.add(ite.next().toString());
			}
			userListArea.setListData(userInfoList);
		}
		
		public void parseUserList(String userListString,Map<String, UserInfo> allUserMap){
			try {
				JSONObject jsonSend = new JSONObject(userListString);
				String message = jsonSend.getString("message");
				JSONObject json = new JSONObject(message);
				for(Iterator ite = json.keys();ite.hasNext();){
					String name = ite.next().toString();
					JSONObject subJson = new JSONObject(json.get(name).toString());
					UserInfo userinfo = new UserInfo();
					userinfo.setAddress(subJson.getString("address"));
					userinfo.setPort(Integer.parseInt(subJson.getString("port")));
					allUserMap.put(name, userinfo);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public void UpdateLogoutUserList(String infomation){
			parseLogoutUserList(infomation, allUserMap);
			
			Vector userInfoList = new Vector<String>();
			for(Iterator ite = allUserMap.keySet().iterator();ite.hasNext();){
				userInfoList.add(ite.next().toString());
			}
			userListArea.setListData(userInfoList);
		}
		
		public void parseLogoutUserList(String userListString,Map<String, UserInfo> allUserMap){
			try {
				JSONObject jsonSend = new JSONObject(userListString);
				String message = jsonSend.getString("message");
				for(Iterator ite = allUserMap.keySet().iterator();ite.hasNext();){
					if(ite.next().toString().equals(message)){
						allUserMap.remove(message);
					}	
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public void dealWithServerClosed(){
			JOptionPane.showMessageDialog(window, "服务器已经停止工作！", "信息", JOptionPane.INFORMATION_MESSAGE);
		}
}
