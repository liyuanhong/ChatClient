package com.yuanhong.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

import com.liyuanhong.listener.LoginViewConfigerClick;

public class LoginView {

	private JFrame frame;
	private JTextField loginName;
	private JTextField serverAddress;
	private JTextField theServerPort;

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
		frame.setBounds(100, 100, 418, 229);
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
		
		loginName = new JTextField();
		loginName.setText("login_name");
		loginName.setFont(new Font("宋体", Font.PLAIN, 14));
		loginName.setBounds(130, 20, 232, 21);
		frame.getContentPane().add(loginName);
		loginName.setColumns(10);
		
		serverAddress = new JTextField();
		serverAddress.setText("192.168.1.101");
		serverAddress.setFont(new Font("宋体", Font.PLAIN, 14));
		serverAddress.setColumns(10);
		serverAddress.setBounds(130, 56, 232, 21);
		frame.getContentPane().add(serverAddress);
		
		theServerPort = new JTextField();
		theServerPort.setText("3000");
		theServerPort.setFont(new Font("宋体", Font.PLAIN, 14));
		theServerPort.setColumns(10);
		theServerPort.setBounds(130, 101, 232, 21);
		frame.getContentPane().add(theServerPort);
		
		JButton loginButton = new JButton("\u8FDB\u5165\u804A\u5929");
		loginButton.setFont(new Font("宋体", Font.PLAIN, 14));
		loginButton.setBounds(269, 152, 93, 29);
		frame.getContentPane().add(loginButton);
		
		loginButton.addMouseListener(new LoginViewConfigerClick(frame, loginName, serverAddress, theServerPort));
	}
}
