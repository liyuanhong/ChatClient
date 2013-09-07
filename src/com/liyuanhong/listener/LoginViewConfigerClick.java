package com.liyuanhong.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JTextField;

import com.yuanhong.ui.ChatClient;

public class LoginViewConfigerClick extends MouseAdapter{
	private JFrame window;
	private JTextField loginName;
	private JTextField serverAddress;
	private JTextField theServerPort;
	
	public LoginViewConfigerClick(JFrame window,JTextField loginName,JTextField serverAddress,JTextField theServerPort) {
		this.window = window;
		this.loginName = loginName;
		this.serverAddress = serverAddress;
		this.theServerPort = theServerPort;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		ChatClient client = new ChatClient(loginName.getText(),serverAddress.getText(),Integer.parseInt(theServerPort.getText()));
		client.showWin();
		window.dispose();
	}
}
