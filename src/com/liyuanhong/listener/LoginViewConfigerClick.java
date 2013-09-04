package com.liyuanhong.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

import com.yuanhong.ui.ChatClient;

public class LoginViewConfigerClick extends MouseAdapter{
	JFrame window;
	
	public LoginViewConfigerClick(JFrame window) {
		this.window = window;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		ChatClient client = new ChatClient();
		client.showWin();
		window.dispose();
	}
}
