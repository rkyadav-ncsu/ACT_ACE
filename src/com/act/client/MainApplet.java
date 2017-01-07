package com.act.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.act.client.reports.ReportsMainPanel;

public class MainApplet extends JApplet implements ActionListener, Observer{
	
	JPanel panelHeader;
	JLabel labelHeader;
	JPanel panelCenter;
	JButton btnMentalHealth;
	JButton btnCommunityServices;
	JButton btnUserManagement;
	JButton btnAdmin;
	JPanel panelParent;
	JButton btnReport;
	
	MentalHealthMainPanel panelMH;
	WelcomePanel panelWelcome;
	JPanel currPanel = null;
	private String  currUser ;
	
	public void init(){
		
		currUser = getParameter("LoggedInUser");
		
		System.out.println("HURRAAAAAAAAAAAAAAAAAY" + currUser);
		
		System.out.println("hit the applet");
		
		//set codebase to ACEConnector
		ACEConnector.codeBase = getCodeBase();
		
		//Do a server side validation here
		if (currUser != null && currUser.length() > 0 && !currUser.equals("null/")){
			System.out.println("Oh noooooooooooo !!!!!!!!!!!");
			
			ACEConnector.getInstance().setCurrUsrName(currUser);
			ACEConnector.getInstance().setCurrUsrPasswrd(getParameter("LoggedInUsrPwrd"));
			
			initUI();
		}else{
			initUI(); //only for testing
		}
		
	}
	
	public void start(){
		
	}
	
	private void initUI(){
		
		try{
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
			btnUserManagement.addActionListener(this);
			panelHeader.add(btnUserManagement);
					
			btnAdmin = new JButton("Admin");
			btnAdmin.addActionListener(this);
			panelHeader.add(btnAdmin);
					
			btnReport = new JButton("Reports");
			btnReport.addActionListener(this);
			panelHeader.add(btnReport);
					
			//Center Panel
			panelCenter = new JPanel();
			add(panelCenter, BorderLayout.CENTER);
			panelCenter.setBackground(Color.WHITE);
			
			panelCenter.setLayout(new BorderLayout());
		    
			addPanel(new WelcomePanel());
			
		}catch(Exception e){
			e.printStackTrace();
		}
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
		}else if (e.getSource() == btnAdmin){
			addPanel(new AdminPanel(getCodeBase()));
		}else if (e.getSource() == btnReport){
			addPanel(new ReportsMainPanel());
		}else if (e.getSource() == btnUserManagement){
			addPanel(new NewUsersMainPanel());
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