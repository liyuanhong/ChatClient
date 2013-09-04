package com.yuanhong.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

public class LoginView {

	private JFrame frame;
	private JTextField txtLoginname;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView window = new LoginView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 418, 187);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel longinName = new JLabel("\u767B\u5F55\u540D\uFF1A");
		longinName.setFont(new Font("宋体", Font.BOLD, 14));
		longinName.setBounds(10, 23, 65, 15);
		frame.getContentPane().add(longinName);
		
		JLabel serverAdd = new JLabel("\u670D\u52A1\u5668\u5730\u5740\uFF1A");
		serverAdd.setFont(new Font("宋体", Font.BOLD, 14));
		serverAdd.setBounds(10, 59, 90, 15);
		frame.getContentPane().add(serverAdd);
		
		JLabel serverPort = new JLabel("\u7AEF\u53E3\uFF1A");
		serverPort.setFont(new Font("宋体", Font.BOLD, 14));
		serverPort.setBounds(10, 105, 65, 15);
		frame.getContentPane().add(serverPort);
		
		txtLoginname = new JTextField();
		txtLoginname.setText("login_name");
		txtLoginname.setFont(new Font("宋体", Font.PLAIN, 14));
		txtLoginname.setBounds(130, 20, 232, 21);
		frame.getContentPane().add(txtLoginname);
		txtLoginname.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setText("192.168.1.101");
		textField_1.setFont(new Font("宋体", Font.PLAIN, 14));
		textField_1.setColumns(10);
		textField_1.setBounds(130, 56, 232, 21);
		frame.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setText("5000");
		textField_2.setFont(new Font("宋体", Font.PLAIN, 14));
		textField_2.setColumns(10);
		textField_2.setBounds(130, 101, 232, 21);
		frame.getContentPane().add(textField_2);
	}
}
