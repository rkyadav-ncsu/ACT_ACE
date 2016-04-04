package com.act.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.act.common.Counsellee;
import com.act.common.PersonName;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;

public class MainApplet extends JApplet implements ActionListener, Observer{
	
	JPanel panelHeader;
	JLabel labelHeader;
	JPanel panelCenter;
	JButton btnMentalHealth;
	JButton btnCommunityServices;
	JButton btnUserManagement;
	JPanel panelParent;
	
	MentalHealthMainPanel panelMH;
	WelcomePanel panelWelcome;
	JPanel currPanel = null;
	URL codeBase;
	
	public void init(){
		
		System.out.println("hit the applet");
		codeBase = getCodeBase();
		System.out.println("login use... from applet " + getParameter("LoginUser"));
		initUI();
		
	}
	
	public void start(){
	}
	
	private void initUI(){
		//add the UI
		setLayout(new BorderLayout());
//		JTable
		//Top Header Panel
		panelHeader = new JPanel();
		panelHeader.setLayout(new FlowLayout());
		panelHeader.setPreferredSize(new Dimension (100,50));
		add(panelHeader, BorderLayout.NORTH);
		
		btnMentalHealth = new JButton("Mental Health");
		btnMentalHealth.addActionListener(this);
		panelHeader.add(btnMentalHealth);
		panelHeader.setBackground(new Color(231,184,198));
		
		btnCommunityServices = new JButton("Community Services");
		panelHeader.add(btnCommunityServices);
		
		btnUserManagement = new JButton("User Management");
		panelHeader.add(btnUserManagement);
				
		
		//Center Panel
		panelCenter = new JPanel();
		add(panelCenter, BorderLayout.CENTER);
		panelCenter.setBackground(Color.WHITE);
		
		panelCenter.setLayout(new BorderLayout());
	    
		addPanel(new WelcomePanel());
		
		//set codebase to ACEConnector
		ACEConnector.codeBase = getCodeBase();
		
	}
	

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getSource() == btnMentalHealth){
//			addPanel(new TSC40MajorPanel()); //TODO testing only
			addPanel(new MentalHealthMainPanel(getCodeBase()));
//			addPanel(new TSC54MinorPanel());
			//test start
//			Counsellee cnsl = new Counsellee();
//			cnsl.setName(new PersonName("Amrita^Dinsa"));
//			//test end
//			addPanel(new CounselleeMain(cnsl)); //TODO for testing 
		}
		
	}
	
	private void addPanel(JPanel panel){
		
		if (currPanel != null){
			panelCenter.remove(currPanel);
			System.out.println("REMOVING PANEL");
		}
		
		panelCenter.add(panel, BorderLayout.CENTER);
		currPanel = panel;
		
		System.out.println("added panel");
		revalidate();
		
		
	}

	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}