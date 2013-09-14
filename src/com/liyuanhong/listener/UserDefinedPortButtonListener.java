package com.liyuanhong.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;

public class UserDefinedPortButtonListener extends MouseAdapter{
	private JTextField clientPortField;
	
	public UserDefinedPortButtonListener(JTextField clientPortField) {
		this.clientPortField = clientPortField;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		if(clientPortField.isEditable()){
			clientPortField.setText("");
			clientPortField.setEditable(false);
		}else{
			clientPortField.setText("3001");
			clientPortField.setEditable(true);			
		}
	}
}
