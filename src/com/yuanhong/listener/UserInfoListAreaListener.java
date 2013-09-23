package com.yuanhong.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import com.yuanhong.util.UserInfo;

public class UserInfoListAreaListener extends MouseAdapter{
	private Map<String, UserInfo> allUserMap;
	private JList userInfo;
	private UserInfo currentUser;
	private JLabel currentDialog;
	
	public UserInfoListAreaListener(Map<String, UserInfo> allUserMap,JList userInfo,
			UserInfo currentUser,JLabel currentDialog) {
		this.allUserMap = allUserMap;
		this.userInfo = userInfo;
		this.currentUser = currentUser;
		this.currentDialog = currentDialog;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		
		if(e.getClickCount() == 2){
			int position = userInfo.getSelectedIndex();
			String name = userInfo.getSelectedValue().toString();
			currentUser.setAddress(allUserMap.get(name).getAddress());
			currentUser.setPort(allUserMap.get(name).getPort());
			currentUser.setName(name);
			currentDialog.setText("¶Ô»°£º" + name);
		}
	}
}
