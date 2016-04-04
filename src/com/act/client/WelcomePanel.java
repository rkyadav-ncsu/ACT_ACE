package com.act.client;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class WelcomePanel extends JPanel{

	JLabel labelHeader;
	
	public WelcomePanel(){
		labelHeader = new JLabel("Welcome to ACT ACE", JLabel.CENTER);
		labelHeader.setFont(new Font("Serif", Font.BOLD, 32));
	
		setLayout(new BorderLayout());
		add (labelHeader, BorderLayout.CENTER);
	}
}
