package com.yuanhong.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.yuanhong.listener.ClientCloseingListener;
import com.yuanhong.listener.SendMessageButtonListener;
import com.yuanhong.listener.SendMessageKeyboardListener;
import com.yuanhong.listener.SendMessageTypeListener;
import com.yuanhong.listener.UserInfoListAreaListener;
import com.yuanhong.service.GetMessageThread;
import com.yuanhong.util.SendMessageStyle;
import com.yuanhong.util.UserInfo;

public class ChatClient {

	private JFrame frame;
	private String loginName;
	private String address;
	private int port;
	private int getMessagePort;
	private Map<String, UserInfo> allUserMap;
	private UserInfo currentUser;                  //表示当前聊天用户
	private SendMessageStyle messageStyle;         //消息是群发还是单发
	
	
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ChatClient window = new ChatClient();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	
	/**
	 * Launch the application.
	 */

	public JFrame getFrame() {
		return frame;
	}

	/**
	 * Create the application.
	 */
	public ChatClient() {
		initialize();
	}
	
	public ChatClient(String loginName,String address,int port,int getMessagePort,Map<String, UserInfo> allUserMap) {
		this.loginName = loginName;
		this.address = address;
		this.port = port;
		this.getMessagePort = getMessagePort;
		this.allUserMap = allUserMap;
		initialize();		
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		currentUser = new UserInfo();
		messageStyle = new SendMessageStyle();
		
		frame = new JFrame(loginName);
		frame.setResizable(false);
		frame.setBounds(100, 100, 772, 530);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 40, 488, 309);
		frame.getContentPane().add(scrollPane);
		
		JTextArea messageShow = new JTextArea();
		messageShow.setEditable(false);
		messageShow.setForeground(Color.BLACK);
		scrollPane.setViewportView(messageShow);
		messageShow.setLineWrap(true);
		
		JLabel currentDialog = new JLabel("\u5BF9\u8BDD\uFF1A");
		currentDialog.setForeground(Color.BLACK);
		currentDialog.setFont(new Font("宋体", Font.BOLD, 14));
		currentDialog.setBounds(10, 20, 488, 15);
		frame.getContentPane().add(currentDialog);
		
		JLabel lblNewLabel_1 = new JLabel("\u5728\u7EBF\u7528\u6237\uFF1A\uFF080\uFF09");
		lblNewLabel_1.setFont(new Font("宋体", Font.BOLD, 14));
		lblNewLabel_1.setBounds(534, 20, 163, 15);
		frame.getContentPane().add(lblNewLabel_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(534, 40, 182, 308);
		frame.getContentPane().add(scrollPane_1);
		
		JList userListArea = new JList();
		userListArea.setModel(new AbstractListModel() {
			String[] values = new String[] {};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		userListArea.setToolTipText("");
		initializeUserList(allUserMap, userListArea);
		scrollPane_1.setViewportView(userListArea);
		
		JLabel lblNewLabel_2 = new JLabel("\u8F93\u5165\u6D88\u606F\uFF1A");
		lblNewLabel_2.setFont(new Font("宋体", Font.BOLD, 14));
		lblNewLabel_2.setBounds(10, 365, 83, 15);
		frame.getContentPane().add(lblNewLabel_2);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 390, 488, 81);
		frame.getContentPane().add(scrollPane_2);
		
		JTextArea messageArea = new JTextArea();
		messageArea.setLineWrap(true);
		scrollPane_2.setViewportView(messageArea);
		
		JButton sendMessage = new JButton("\u53D1\u9001");
		sendMessage.setFont(new Font("宋体", Font.BOLD, 14));
		sendMessage.setBounds(534, 391, 75, 28);
		frame.getContentPane().add(sendMessage);
		
		JButton sendGroup = new JButton("");
		sendGroup.setEnabled(false);
		sendGroup.setFont(new Font("宋体", Font.BOLD, 14));
		sendGroup.setBounds(534, 443, 75, 28);
		frame.getContentPane().add(sendGroup);
		
		JButton groupOrSigalSend = new JButton("\u7FA4\u53D1\u6A21\u5F0F");
		groupOrSigalSend.setFont(new Font("宋体", Font.BOLD, 14));
		groupOrSigalSend.setBounds(619, 391, 97, 28);
		frame.getContentPane().add(groupOrSigalSend);
		
		JButton sound = new JButton("");
		sound.setEnabled(false);
		sound.setFont(new Font("宋体", Font.BOLD, 14));
		sound.setBounds(619, 443, 97, 28);
		frame.getContentPane().add(sound);
		
		sendMessage.addMouseListener(new SendMessageButtonListener(sendMessage, messageArea, loginName, address, port,currentUser,frame,messageStyle));
		frame.addWindowListener(new ClientCloseingListener(address, port, loginName));
		userListArea.addMouseListener(new UserInfoListAreaListener(allUserMap, userListArea, currentUser, currentDialog));
		groupOrSigalSend.addMouseListener(new SendMessageTypeListener(sendMessage, sendGroup, groupOrSigalSend, messageStyle));
		messageArea.addKeyListener(new SendMessageKeyboardListener(sendMessage, messageArea, loginName, address, port, currentUser, frame, messageStyle));
		
		ServerSocket serSocket = null;
		try {
			serSocket = new ServerSocket(getMessagePort);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		GetMessageThread getMessageThread = new GetMessageThread(serSocket,messageShow,allUserMap,userListArea,frame,scrollPane);
		getMessageThread.start();
	}
	
	public void initializeUserList(Map<String, UserInfo> allUserMap,JList userListArea){
		Vector userInfoList = new Vector<String>();
		for(Iterator ite = allUserMap.keySet().iterator();ite.hasNext();){
			userInfoList.add(ite.next().toString());
		}
		userListArea.setListData(userInfoList);
	}
}
