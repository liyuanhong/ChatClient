package com.yuanhong.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.json.JSONObject;

import com.yuanhong.service.SendMessageThread;
import com.yuanhong.ui.ChatClient;
import com.yuanhong.util.MessageClass;
import com.yuanhong.util.MessageType;
import com.yuanhong.util.UserInfo;

public class LoginViewConfigerClick extends MouseAdapter{
	private JFrame window;
	private JTextField loginName;
	private JTextField serverAddress;
	private JTextField theServerPort;
	private MessageClass message;                   //要发送的消息
	private int messType = MessageType.LOGIN;
	private int clientPort;                          //本地接收消息的端口
	private JTextField clientPortField;
	private Map<String, UserInfo> allUserMap;
	private int serverStatus = 0;
	
	
	public LoginViewConfigerClick(JFrame window,JTextField loginName,JTextField serverAddress,JTextField theServerPort,int clientPort,JTextField clientPortField) {
		this.window = window;
		this.loginName = loginName;
		this.serverAddress = serverAddress;
		this.theServerPort = theServerPort;
		this.clientPort = clientPort;
		this.clientPortField = clientPortField;	
		allUserMap = new TreeMap<String, UserInfo>();
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);		
		
		message = new MessageClass();		
		message.setUserName(loginName.getText());
		message.setMessType(MessageType.LOGIN);
		if(clientPortField.isEditable()){
			int userDefinedPort = getUserDefinedPort();
			if(userDefinedPort != 0){
				message.setClientPort(userDefinedPort);
				
				JSONObject json = new JSONObject(message.getJsonMap());	
				SendMessageThread thread = new SendMessageThread(json.toString(), serverAddress.getText(), Integer.parseInt(theServerPort.getText()));
				thread.start();				
				String temp = getUserStatus();
				if(temp.equals("1")){
					JOptionPane.showMessageDialog(window, "该用户名已经使用，请换其他用户名！", "警告", JOptionPane.ERROR_MESSAGE);
				}else{
					parseUserList(temp, allUserMap);
					ChatClient client = new ChatClient(loginName.getText(),serverAddress.getText(),
							Integer.parseInt(theServerPort.getText()),clientPort,allUserMap);
					client.getFrame().setVisible(true);
					window.dispose();
				}	
			}		
		}else{
			message.setClientPort(getDefaultPort());
			clientPort = getDefaultPort();
			
			JSONObject json = new JSONObject(message.getJsonMap());	
			SendMessageThread thread = new SendMessageThread(json.toString(), serverAddress.getText(), Integer.parseInt(theServerPort.getText()));			
			thread.start();
			String temp = getUserStatus();
			if(temp.equals("1")){
				JOptionPane.showMessageDialog(window, "该用户名已经使用，请换其他用户名！", "警告", JOptionPane.ERROR_MESSAGE);
			}else{
				parseUserList(temp, allUserMap);
				ChatClient client = new ChatClient(loginName.getText(),serverAddress.getText(),
						Integer.parseInt(theServerPort.getText()),clientPort,allUserMap);
				client.getFrame().setVisible(true);
				window.dispose();
			}	
		}	
	}
	
	//判断将要作为获取消息的端口号是否被占用
	public boolean IsLocalPortUsed(int localPort){
		try {
			ServerSocket serSocket = new ServerSocket(localPort);
			serSocket.close();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return true;
		}
	}
	
	//获取默认的端口号
	public int getDefaultPort(){
		if(IsLocalPortUsed(clientPort)){
			do{
				clientPort++;
			}while(IsLocalPortUsed(clientPort));
			return clientPort;
		}else{
			return clientPort;
		}
	}
	
	public int getUserDefinedPort(){
		try{
			clientPort = Integer.parseInt(clientPortField.getText());
			if(1024 > clientPort || clientPort > 49151){
				JOptionPane.showMessageDialog(window, "请输入1024~49151", "警告", JOptionPane.ERROR_MESSAGE);
				return 0;    //返回0，表示获取端口号错误，重新输入
			}else{
				return clientPort;
			}
		}catch(Exception e){
			JOptionPane.showMessageDialog(window, "请输入1024~49151的数字", "警告", JOptionPane.ERROR_MESSAGE);
		}
		return 0;            //返回0，表示获取端口号错误，重新输入
	}
	
	//判断此用户是否在服务器端登录，登录返回1，没有登录返回0
	public String getUserStatus(){
		String status = "";
			try {
				ServerSocket ser = new ServerSocket(clientPort);
				Socket soc = ser.accept();
				int len;
				char[] ch = new char[512];
				InputStreamReader reader = new InputStreamReader(soc.getInputStream());
				while ((len = reader.read(ch)) != -1) {
					status = status + String.valueOf(ch, 0, len);
				}	
				reader = null;
				ser.close();
				serverStatus = 0;
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			return status;
	}
	
	public void parseUserList(String userListString,Map<String, UserInfo> allUserMap){
		try {
			JSONObject jsonSend = new JSONObject(userListString);
			String message = jsonSend.getString("message");
			JSONObject json = new JSONObject(message);
			System.out.println(json.toString());
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
}


