package com.bentyn.traincoll.simulator.gui;

import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import org.springframework.beans.factory.annotation.Autowired;
import java.awt.Font;

public class EndpointWindow extends JFrame {
	
	private static final long serialVersionUID = 1396806541507104318L;
	
	JTextField urlTextField;
	JButton startBtn;
	public JTextArea eventsTextArea;
	
	@Autowired
	EventButtonListener controller;
	
	public EndpointWindow() {
	//	init();
	}

	public void init(){
		getContentPane().setLayout(null);
		this.setBounds(new Rectangle(0,0,700,400));
		this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
		JLabel lblUrl = new JLabel("URL");
		lblUrl.setBounds(10, 11, 46, 14);
		getContentPane().add(lblUrl);
		
		urlTextField = new JTextField();
		urlTextField.setText("ws://localhost:8080/TrainCollServer/collision");
		urlTextField.setBounds(52, 8, 306, 20);
		getContentPane().add(urlTextField);
		urlTextField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 199, 674, 130);
		getContentPane().add(scrollPane);
		
		eventsTextArea = new JTextArea();
		eventsTextArea.setFont(new Font("Tahoma", Font.PLAIN, 11));
		scrollPane.setViewportView(eventsTextArea);
		
		startBtn = new JButton("START");
		startBtn.setBounds(573, 24, 89, 59);
		startBtn.addActionListener(controller);
		getContentPane().add(startBtn);
		
		JLabel lblEvents = new JLabel("Messages");
		lblEvents.setBounds(10, 174, 124, 14);
		getContentPane().add(lblEvents);
	}
}
