package com.yuanhong.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import com.yuanhong.util.SendMessageStyle;

public class SendMessageTypeListener extends MouseAdapter{
	private JButton sendMessage;
	private JButton sendGroup;
	private JButton groupOrSigalSend;
	private SendMessageStyle messageStyle;  //��Ϣ��Ⱥ�����ǵ���
	
	public SendMessageTypeListener(JButton sendMessage,JButton sendGroup,JButton groupOrSigalSend,SendMessageStyle messageStyle) {
		this.sendMessage = sendMessage;
		this.sendGroup = sendGroup;
		this.groupOrSigalSend = groupOrSigalSend;
		this.messageStyle = messageStyle;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		if(groupOrSigalSend.getText().equals("Ⱥ��ģʽ")){
			sendMessage.setText("Ⱥ��");
			messageStyle.setStyle(1);
			groupOrSigalSend.setText("����ģʽ");
		}else if(groupOrSigalSend.getText().equals("����ģʽ")){
			sendMessage.setText("����");
			messageStyle.setStyle(0);
			groupOrSigalSend.setText("Ⱥ��ģʽ");
		}
		
	}
}
