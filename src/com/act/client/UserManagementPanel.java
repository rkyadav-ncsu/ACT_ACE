package com.act.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class UserManagementPanel extends MHPanel{
	MHPanelObserver panelObserver;
	private URL codebase;
	
	JPanel panelTop, panelCentre;
	JPanel panelTopTitle;
	JTabbedPane tabMyProfile;

	
	public UserManagementPanel(URL codebase){
		this.codebase = codebase;
		setSize(1000, 850);
		initUI();
	}


	private void initUI(){
	try {
		setLayout(new BorderLayout());
		panelTop = new JPanel();
		panelTop.setLayout(new BorderLayout());
		panelTop.setBackground(Color.yellow);
		add(panelTop, BorderLayout.NORTH);

		//Top title panel
		panelTopTitle = new JPanel();
		panelTopTitle.setLayout(new BorderLayout());
		panelTopTitle.setPreferredSize(new Dimension(100,40));
		panelTopTitle.setBackground(new Color(180,230,180));
		panelTop.add(panelTopTitle, BorderLayout.NORTH);

		//title label
		JLabel lblTopTitle = new JLabel("This is the User Management Panel", JLabel.CENTER);
		panelTopTitle.add(lblTopTitle, BorderLayout.CENTER);
		
		panelCentre = new JPanel();
		panelCentre.setLayout(new BorderLayout());
		add(panelCentre, BorderLayout.CENTER);
		
		tabMyProfile = new JTabbedPane();
		
		panelCentre.add(tabMyProfile);
		
		tabMyProfile.add("My Profile", new UserProfilePanel());
		tabMyProfile.add("Users", new UsersPanel());
		tabMyProfile.add("Groups", new GroupsPanel());
		

		
		
	} catch (Exception e){
		e.printStackTrace();
	}
	}



	@Override
	public void setMHPanelObserver(MHPanelObserver obs) {
		// TODO Auto-generated method stub
		
	}
}
