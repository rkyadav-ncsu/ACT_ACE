package com.act.client;

import java.awt.CardLayout;

import javax.swing.JPanel;

public class NewUsersMainPanel extends JPanel {
	
	NewUserListPanel userListPanel;
	NewEditUserPanel editUserPanel;
	
	//names to identify the cards in the layout
	public static String USER_LIST = "UserList";
	public static String EDIT_USER = "EditUser";
	
	public NewUsersMainPanel(){
		initUI();
	}

	private void initUI(){
		
		//set the parent panel layout as card layout
		setLayout(new CardLayout());
		
		//add the cards to the parent panel which is in card layout
		//by default UsersListPanel is made visible first
		userListPanel = new NewUserListPanel(this);
		add(userListPanel,USER_LIST);
		
		editUserPanel = new NewEditUserPanel(this);
		add(editUserPanel, EDIT_USER);
		
	}
	
	public void showCard(String cardName){
		CardLayout cl = (CardLayout)(getLayout());
	    cl.show(this, cardName);

	}
	
}
