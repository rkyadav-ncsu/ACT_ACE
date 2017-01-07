package com.act.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import com.act.client.models.MHUsersTableModel;
import com.act.common.SwingUtils;
import com.act.common.User;

public class NewUserListPanel extends JPanel implements ActionListener {
	NewUsersMainPanel parent;
	
//	JPanel panelMain,
	JPanel panelAddEditDelBtn;
	JButton btnAddList, btnEdit, btnDelList;

	JPanel panelTopTitle;

	JTable tblUsers;
	MHUsersTableModel modelUsers;
	
	JOptionPane optConfirmDel;
	String pwd = new String();
	
	//names to identify the cards in the layout
//	public static String USER_LIST = "UserList";
//	public static String EDIT_USER = "EditUser";
	
	public NewUserListPanel(NewUsersMainPanel parent){
		this.parent = parent;
		initUI();
	}
	
	private void initUI(){
		//Note to Nathan
		//when edit button is clicked here to go to edit panel
		//just call the below line
		
		//parent.showCard(NewUsersMainPanel.EDIT_USERUSER_LIST);
		
		setLayout(new BorderLayout());
		pwd = new String("hello");
		
		try {
//			panelMain = new JPanel();
//			add(panelMain, BorderLayout.CENTER);
//			panelMain.setLayout(new GridBagLayout());
//			panelMain.setLayout(new BorderLayout());
			setLayout(new BorderLayout());
			//Top title panel
			panelTopTitle = new JPanel();
			panelTopTitle.setLayout(new BorderLayout());
			panelTopTitle.setPreferredSize(new Dimension(100,40));
			panelTopTitle.setBackground(new Color(180,230,180));
			add(panelTopTitle, BorderLayout.NORTH);
			
			//title label
			JLabel lblTopTitle = new JLabel("This is the User Management Panel", JLabel.CENTER);
			panelTopTitle.add(lblTopTitle, BorderLayout.CENTER);
			
			//Users list
			modelUsers = new MHUsersTableModel();
			tblUsers = new JTable(modelUsers);
			tblUsers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			JScrollPane scrollPaneTbl = new JScrollPane(tblUsers);
			tblUsers.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			scrollPaneTbl.setBackground(Color.BLUE);
//			panelMain.add(scrollPaneTbl, BorderLayout.CENTER);
			add(scrollPaneTbl, BorderLayout.CENTER);
			modelUsers.setList(ACEConnector.getInstance().getUsersAll() );
			
			// add the buttons to the main panel
			panelAddEditDelBtn = new JPanel();
			panelAddEditDelBtn.setPreferredSize(new Dimension(160,50));
			panelAddEditDelBtn.setBackground(Color.DARK_GRAY);
			panelAddEditDelBtn.setLayout(new FlowLayout());
//			panelMain.add(panelAddEditDelBtn, BorderLayout.SOUTH);
			add(panelAddEditDelBtn, BorderLayout.SOUTH);

			btnAddList = new JButton("Add New");
			btnAddList.addActionListener(this);
			btnEdit = new JButton("Edit");
			btnEdit.addActionListener(this);
			btnDelList = new JButton("Delete Item");
			btnDelList.addActionListener(this);

			panelAddEditDelBtn.add(btnAddList);
			panelAddEditDelBtn.add(btnEdit);
			panelAddEditDelBtn.add(btnDelList);
			
			
//			panelMain.add(scrollPaneTbl, SwingUtils.getConstraints(0, 0, 1, 3, 3, 
//					GridBagConstraints.WEST, 
//					GridBagConstraints.BOTH, 
//					10, 10, 0, 10));
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void delUser(){												// Only one selection allowed
//		Vector <String> vUserIDs = new Vector<String>();
		
//		OrgnIDNameObj orgnIDN = modelOrg.getRowObject(tblOrgData.getSelectedRow()); 
		if (tblUsers.getSelectedRowCount() > 0){
//			Vector vOrgID = new Vector();
			int selRow = tblUsers.getSelectedRow();
			String selUserID = new String();
			
			User userObj = modelUsers.getRowObject(selRow); 
			System.out.println("User being deleted: " + userObj.getUserID() + " " + userObj.getfName());
			selUserID = (userObj.getUserID());
			String sResult = ACEConnector.getInstance().delUsers(selUserID);
			System.out.println("Delete Users result:" + sResult);
		}
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(btnEdit)){
			System.out.println("Edit button selected");
			 User usrObj = modelUsers.getRowObject(tblUsers.getSelectedRow());
			System.out.println("User ID: " + usrObj.getUserID());
					
			parent.editUserPanel.setUser(usrObj);
			parent.editUserPanel.setAllUsers(modelUsers);
			parent.showCard(NewUsersMainPanel.EDIT_USER);
		}
		if(e.getSource().equals(btnAddList)){
			System.out.println("Add button selected");
			parent.editUserPanel.setUser(null);
			parent.editUserPanel.setAllUsers(modelUsers);
			parent.showCard(NewUsersMainPanel.EDIT_USER);
			
		}
		if(e.getSource().equals(btnDelList)){
			int selUser = tblUsers.getSelectedRowCount();
			String sTtl = new String("Delete User");
			if (selUser > 1) 
				sTtl = "Delete Users";
			if (selUser > 0){
				int r = optConfirmDel.showConfirmDialog(null, "Sure?", sTtl, 2);
				if (r == JOptionPane.YES_OPTION){
					System.out.println("Delete Sure ");
					delUser();
					parent.showCard(NewUsersMainPanel.USER_LIST);
				}
			}
		}

	}
}
