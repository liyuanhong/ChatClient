package com.yuanhong.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JButton;

public class ChatClient {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChatClient window = new ChatClient();
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
	public ChatClient() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 772, 530);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 40, 488, 309);
		frame.getContentPane().add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setForeground(Color.BLACK);
		scrollPane.setViewportView(textArea);
		textArea.setLineWrap(true);
		
		JLabel lblNewLabel = new JLabel("\u5BF9\u8BDD\uFF1A");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 20, 54, 15);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u5728\u7EBF\u7528\u6237\uFF1A\uFF080\uFF09");
		lblNewLabel_1.setFont(new Font("宋体", Font.BOLD, 14));
		lblNewLabel_1.setBounds(534, 20, 163, 15);
		frame.getContentPane().add(lblNewLabel_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(534, 40, 182, 308);
		frame.getContentPane().add(scrollPane_1);
		
		JList list = new JList();
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setToolTipText("");
		scrollPane_1.setViewportView(list);
		
		JLabel lblNewLabel_2 = new JLabel("\u8F93\u5165\u6D88\u606F\uFF1A");
		lblNewLabel_2.setFont(new Font("宋体", Font.BOLD, 14));
		lblNewLabel_2.setBounds(10, 365, 83, 15);
		frame.getContentPane().add(lblNewLabel_2);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 390, 488, 81);
		frame.getContentPane().add(scrollPane_2);
		
		JTextArea textArea_1 = new JTextArea();
		scrollPane_2.setViewportView(textArea_1);
		
		JButton btnNewButton = new JButton("\u53D1\u9001");
		btnNewButton.setFont(new Font("宋体", Font.BOLD, 14));
		btnNewButton.setBounds(534, 391, 83, 28);
		frame.getContentPane().add(btnNewButton);
	}
}
