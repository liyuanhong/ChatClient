package com.yuanhong.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import com.yuanhong.util.SendMessageStyle;

public class SendMessageTypeListener extends MouseAdapter{
	private JButton sendMessage;
	private JButton sendGroup;
	private JButton groupOrSigalSend;
	private SendMessageStyle messageStyle;  //消息是群发还是单发
	
	public SendMessageTypeListener(JButton sendMessage,JButton sendGroup,JButton groupOrSigalSend,SendMessageStyle messageStyle) {
		this.sendMessage = sendMessage;
		this.sendGroup = sendGroup;
		this.groupOrSigalSend = groupOrSigalSend;
		this.messageStyle = messageStyle;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		if(groupOrSigalSend.getText().equals("群发模式")){
			sendMessage.setText("群发");
			messageStyle.setStyle(1);
			groupOrSigalSend.setText("单发模式");
		}else if(groupOrSigalSend.getText().equals("单发模式")){
			sendMessage.setText("单发");
			messageStyle.setStyle(0);
			groupOrSigalSend.setText("群发模式");
		}
		
	}
}
