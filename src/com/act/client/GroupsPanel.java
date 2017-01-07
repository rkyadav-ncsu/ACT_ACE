package com.act.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import com.act.client.models.MHUserGroupsTableModel;
import com.act.client.models.MHUsersTableModel;
import com.act.common.Organisation;
import com.act.common.SwingUtils;
import com.act.common.User;
import com.act.common.GroupsObj;

public class GroupsPanel extends MHPanel implements ActionListener{
//	MHPanelObserver panelObserver;
	
	MHUserGroupsTableModel modelGroups;
	JTable tblGroups;
	
	JPanel panelMain, panelAddEditDelBtn, panelNewUserGrp, panelSaveClose;
	JPanel panelGrpDetails;;
	JPanel panelEdit, panelEditSaveClose;
	
	JButton btnAddList, btnEdit, btnDelList;
	JButton btnSave, btnClose;
//	JButton btnEditSave, btnEditCancel;
	JTextField txtUserGrpID, txtUserGrpName, txtUserGrpDesc;
//	JTextField txtEditUserID, txtFName, txtMName, txtLName;
	
	JOptionPane optConfirmDel;
	String modeAED = "A";
	
	String oldGrpName = new String();

	Vector<GroupsObj> vGroups = new Vector<GroupsObj>();
	
	public GroupsPanel(){
		initUI();
		System.out.println("Panel User Groups");
	}

	private void initUI(){
		setLayout(new BorderLayout());
		
		try {
		panelMain = new JPanel();
		panelMain.setLayout(new GridBagLayout());
		add(panelMain, BorderLayout.CENTER);
		modelGroups = new MHUserGroupsTableModel();
		tblGroups = new JTable(modelGroups);
		tblGroups.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPaneTbl = new JScrollPane(tblGroups);
		tblGroups.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		scrollPaneTbl.setBackground(Color.BLUE);
//		panelData.add(scrollPaneTbl, BorderLayout.CENTER);
		panelMain.add(scrollPaneTbl, SwingUtils.getConstraints(0, 0, 1, 3, 3, 
				GridBagConstraints.WEST, 
				GridBagConstraints.BOTH, 
				10, 10, 0, 10));
		
		panelGrpDetails = new JPanel();
		panelGrpDetails.setLayout(new GridBagLayout());

		panelAddEditDelBtn = new JPanel();
		panelAddEditDelBtn.setPreferredSize(new Dimension(160,50));
		panelAddEditDelBtn.setBackground(Color.DARK_GRAY);
		btnAddList = new JButton("Add New");
		btnAddList.addActionListener(this);
		btnAddList.setEnabled(true);
		btnEdit = new JButton("Edit");
		btnEdit.addActionListener(this);
		btnDelList = new JButton("Delete Item");
		btnDelList.addActionListener(this);
		panelAddEditDelBtn.setLayout(new FlowLayout());
		panelAddEditDelBtn.add(btnAddList);
		panelAddEditDelBtn.add(btnEdit);
		panelAddEditDelBtn.add(btnDelList);
		add(panelAddEditDelBtn, BorderLayout.SOUTH);
		
		panelSaveClose = new JPanel();
		panelSaveClose.setLayout(new FlowLayout());
		panelSaveClose.setBackground(Color.DARK_GRAY);
		btnSave = new JButton("Save");
		btnSave.addActionListener(this);
		btnClose = new JButton("Close");
		btnClose.addActionListener(this);
		panelSaveClose.add(btnSave);
		panelSaveClose.add(btnClose);
		
		panelEditSaveClose = new JPanel();
		panelEditSaveClose.setPreferredSize(new Dimension(160,50));
		panelEditSaveClose.setBackground(Color.DARK_GRAY);
		panelEditSaveClose.setLayout(new FlowLayout());
//		btnEditSave = new JButton("Save");
//		btnEditSave.addActionListener(this);
//		btnEditCancel = new JButton("Close");
//		btnEditCancel.addActionListener(this);
//		panelEditSaveClose.add(btnEditSave);
//		panelEditSaveClose.add(btnEditCancel);
//		panelEdit.setVisible(false);
		panelEditSaveClose.setVisible(false);
		
		newlist();
		
		} catch(Exception e){
			e.printStackTrace();
		}
	}	
	
	private void newlist(){
		modelGroups.setList(ACEConnector.getInstance().getUserGrpsAll()); 

		btnAddList.setVisible(true);
		btnAddList.setEnabled(true);
		
		panelAddEditDelBtn.setVisible(true);
		panelSaveClose.setVisible(false);
		panelEditSaveClose.setVisible(false);
		panelMain.setVisible(true);
		panelMain.repaint();
	}
	
	private void grpDetails(){
		panelMain.setVisible(false);
		panelAddEditDelBtn.setVisible(false);
		panelGrpDetails.removeAll();
		panelGrpDetails.setVisible(true);
		panelSaveClose.setVisible(true);
//		if (modeAED == "A"){
//			panelSaveClose.setVisible(true);
//			panelEditSaveClose.setVisible(false);
//		} else if (modeAED == "E") {
//			panelSaveClose.setVisible(false);
//			panelEditSaveClose.setVisible(true);
//		}
			
//		txtUserGrpID = new JTextField();
//		txtUserGrpID.setEditable(false);
		txtUserGrpName = new JTextField();
		txtUserGrpName.setPreferredSize(new Dimension(120, 25));
		txtUserGrpDesc = new JTextField();
		txtUserGrpDesc.setPreferredSize(new Dimension(260, 25));
		

		panelGrpDetails.add(new JLabel("Group Name :"),  SwingUtils.getConstraints(1, 0, 1, 0, 0, 
				GridBagConstraints.WEST, 
				GridBagConstraints.NONE, 
				10, 10, 0, 10));
		
		panelGrpDetails.add(new JLabel("Description :"),  SwingUtils.getConstraints(1, 1, 1, 0, 0, 
				GridBagConstraints.WEST, 
				GridBagConstraints.NONE, 
				10, 10, 0, 10));

		panelGrpDetails.add(txtUserGrpName,  SwingUtils.getConstraints(2, 0, 1, 0, 0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.HORIZONTAL, 
				10, 10, 0, 10));
		
		panelGrpDetails.add(txtUserGrpDesc,  SwingUtils.getConstraints(2, 1, 1, 0.1, 0.1, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.REMAINDER, 
				10, 10, 0, 10));
		
		add(panelGrpDetails, BorderLayout.CENTER);
		add(panelSaveClose, BorderLayout.SOUTH);
//		if (modeAED == "A"){
//			add(panelSaveClose, BorderLayout.SOUTH);
//		} else 
		if (modeAED == "E"){
//			add(panelEditSaveClose);
			setValues();
		}
	}

	
	private boolean verifyInfo(){
		boolean correctInfo = true;
		
		
		return correctInfo;
	}

		
	private void saveUserInfo(){
		
		GroupsObj userGrpObjSave = new GroupsObj();
		boolean isUserGrpSaved = false;
		System.out.println("Saving information in " + modeAED + " mode");
		try {
		if (verifyInfo()){
			userGrpObjSave.setGroupName(txtUserGrpName.getText());
			userGrpObjSave.setGroupDesc(txtUserGrpDesc.getText());
			System.out.println("After verifying saving information in " + modeAED + " mode");
			if (modeAED == "A")
				isUserGrpSaved = ACEConnector.getInstance().saveNewMstUserGroup(userGrpObjSave);
			else if (modeAED == "E")
				isUserGrpSaved = ACEConnector.getInstance().updateGroupInfo(userGrpObjSave, oldGrpName);
			if (isUserGrpSaved){
				System.out.println("User Group entry successfull");
				newlist();
			}
			else
				System.out.println("User Group info not saved");
		}
		
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void setValues(){
		try{
			GroupsObj groupObj = modelGroups.getRowObject(tblGroups.getSelectedRow());
			
			vGroups = ACEConnector.getInstance().getGroupInfo(groupObj.getGroupName());
			String usersInGrp = new String();
			for (int i = 0; i < vGroups.size(); i++){
				GroupsObj grpObj = new GroupsObj();
				grpObj = vGroups.elementAt(0);
//				if (modeAED == "A"){
//					txtUserID.setText(user.getUserID());
//				} 
//				else if (modeAED == "E"){
//				txtEditUserID.setText(grpObj.getUserID());
				txtUserGrpName.setText(grpObj.getGroupName());
				oldGrpName = grpObj.getGroupName();
				txtUserGrpDesc.setText(grpObj.getGroupDesc());
				
			}	
		} catch (Exception e){
			e.printStackTrace();
		}	
	}
	
	public void delGroups(){									// only one record can be deleted
		Vector <String> vGroupNames = new Vector<String>();
		
//		OrgnIDNameObj orgnIDN = modelOrg.getRowObject(tblOrgData.getSelectedRow()); 
		int selRows = tblGroups.getSelectedRow();
		String grpName = new String();
		
		GroupsObj groupObj = modelGroups.getRowObject(selRows); 
		System.out.println("Group being deleted: " + groupObj.getGroupName() );
		grpName = groupObj.getGroupName();
		String sResult = ACEConnector.getInstance().delGroups(grpName);
		System.out.println("Delete Groups result:" + sResult);
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(btnAddList)) {
			modeAED = "A";
			grpDetails();
		}
		
		if (e.getSource().equals(btnEdit)){
			modeAED = "E";
			grpDetails();
		}
		
		if (e.getSource().equals(btnSave))
			saveUserInfo();
		
		if (e.getSource().equals(btnClose)) {
			panelGrpDetails.setVisible(false);
			panelSaveClose.setVisible(false);
			newlist();
		}
		if(e.getSource().equals(btnDelList)){
			modeAED =  "D";
			int selGrps = tblGroups.getSelectedRowCount();
			String sTtl = new String("Delete Group");
			if (selGrps > 1) 
				sTtl = "Delete Groups";
			if (selGrps > 0){
				int r = optConfirmDel.showConfirmDialog(null, "Sure?", sTtl, 2);
				if (r == JOptionPane.YES_OPTION){
					modeAED = "D";
					System.out.println("modeAED set to ---- D ");
					delGroups();
					newlist();
				}
			}
		}	
	}

	@Override
	public void setMHPanelObserver(MHPanelObserver obs) {
		// TODO Auto-generated method stub
		
	}

}
