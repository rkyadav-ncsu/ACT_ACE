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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

import com.act.client.components.JAceDate;
import com.act.client.components.JAceList;
import com.act.client.models.MHOrgnTableModel;
import com.act.client.models.MHUsersTableModel;
import com.act.common.Organisation;
import com.act.common.SwingUtils;
import com.act.common.User;
import com.act.server.db.DB;

public class UsersPanel extends MHPanel implements ActionListener {
	MHPanelObserver panelObserver;
	
	MHUsersTableModel modelUsers;
	JTable tblUsers;
	JPanel panelMain, panelAddEditDelBtn, panelNewUser, panelSaveClose;
	JPanel panelEdit, panelEditSaveClose;
	
	JButton btnAddList, btnEdit, btnDelList;
	JButton btnEditSave, btnEditCancel;
	JButton btnSave, btnClose;
	
	JTextField txtUserID, txtNewMName, txtNewFName, txtNewLName;
	JTextField txtEditUserID, txtFName, txtMName, txtLName;
	JTextField txtRetypePwd, txtNewPwd;
	JTextField txtDesig;
	JTextField txtAddress, txtEmail, txtPin, txtMob, txtPhone;
	JAceList lstGroups;
	JComboBox cbGender;
	JAceDate dateDOB;
	
	Vector<User> vUser = new Vector<User>();

	JOptionPane optConfirmDel;
	String modeAED = "A";
	String pwd ;
	
	public UsersPanel() {
		initUI();
	}
	
	private void initUI(){

		setLayout(new BorderLayout());
		pwd = new String("hello");
		
		try {
			panelMain = new JPanel();
			add(panelMain, BorderLayout.CENTER);
			panelMain.setLayout(new GridBagLayout());
			
			//Users list
			modelUsers = new MHUsersTableModel();
			tblUsers = new JTable(modelUsers);
			tblUsers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			JScrollPane scrollPaneTbl = new JScrollPane(tblUsers);
			tblUsers.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			scrollPaneTbl.setBackground(Color.BLUE);
//			panelData.add(scrollPaneTbl, BorderLayout.CENTER);
			panelMain.add(scrollPaneTbl, SwingUtils.getConstraints(0, 0, 1, 3, 3, 
					GridBagConstraints.WEST, 
					GridBagConstraints.BOTH, 
					10, 10, 0, 10));
			
			panelNewUser = new JPanel();
			panelNewUser.setLayout(new GridBagLayout());

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
//			add(panelAddEditDelBtn, BorderLayout.SOUTH);
			
			
//			panelEdit = new JPanel();
//			panelEdit.setLayout(new GridBagLayout());
////			add(panelEdit, BorderLayout.WEST);
//			panelEditSaveClose = new JPanel();
////			panelSaveCloseButton.setPreferredSize(new Dimension(160,50));
//			panelEditSaveClose.setBackground(Color.DARK_GRAY);
//			panelEditSaveClose.setLayout(new FlowLayout());
//			btnEditSave = new JButton("Save");
//			btnEditSave.addActionListener(this);
//			btnEditCancel = new JButton("Close");
//			btnEditCancel.addActionListener(this);
//			panelEditSaveClose.add(btnEditSave);
//			panelEditSaveClose.add(btnEditCancel);
////			add(panelEditSaveClose, BorderLayout.SOUTH);
//			panelEdit.setVisible(false);
//			panelEditSaveClose.setVisible(false);

			
			panelSaveClose = new JPanel();
			panelSaveClose.setLayout(new FlowLayout());
			panelSaveClose.setBackground(Color.DARK_GRAY);
			btnSave = new JButton("Save");
			btnSave.addActionListener(this);
			btnClose = new JButton("Close");
			btnClose.addActionListener(this);
			panelSaveClose.add(btnSave);
			panelSaveClose.add(btnClose);
//			System.out.println("Displaying the list of users");
			newlist();
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	private void newlist(){
		modelUsers.setList(ACEConnector.getInstance().getUsersAll() );
		
		btnAddList.setVisible(true);
		btnAddList.setEnabled(true);
		
		panelNewUser.setVisible(false);
		panelSaveClose.setVisible(false);
//		panelEdit.setVisible(false);
//		panelEdit.removeAll();
//		panelEditSaveClose.setVisible(false);
//		panelAddEditDelBtn.repaint();
		panelMain.setVisible(true);
		panelMain.repaint();
		add(panelMain, BorderLayout.CENTER);
		panelAddEditDelBtn.setVisible(true);
		add(panelAddEditDelBtn, BorderLayout.SOUTH);
//		System.out.println("Added  panelAddEditDelBtn");

	}	
	
	private void userDetails(){
		panelMain.setVisible(false);
		panelAddEditDelBtn.setVisible(false);
//		panelEdit.setVisible(false);
//		panelEditSaveClose.setVisible(false);
//		panelNewUser.repaint();
		panelNewUser.removeAll();
		panelNewUser.setVisible(true);
		panelSaveClose.setVisible(true);
		
		txtUserID = new JTextField();
		txtFName = new JTextField();
		txtFName.setPreferredSize(new Dimension(120, 25));
		txtLName = new JTextField();
		txtLName.setPreferredSize(new Dimension(120, 25));
		txtMName = new JTextField();
		txtMName.setPreferredSize(new Dimension(120, 25));
		
		panelNewUser.add(new JLabel("User ID : "), SwingUtils.getConstraints(0, 0, 1, 0, 0, 
				GridBagConstraints.WEST, 
				GridBagConstraints.NONE, 
				10, 10, 0, 10));
		panelNewUser.add(txtUserID, SwingUtils.getConstraints(0, 1, 1, 0, 0, 
				GridBagConstraints.WEST, 
				GridBagConstraints.HORIZONTAL, 
				10, 10, 0, 10));

		panelNewUser.add(new JLabel("Middle Name :"),  SwingUtils.getConstraints(1, 0, 1, 0, 0, 
				GridBagConstraints.WEST, 
				GridBagConstraints.NONE, 
				10, 10, 0, 10));
		
		panelNewUser.add(new JLabel("First Name :"),  SwingUtils.getConstraints(1, 1, 1, 0, 0, 
				GridBagConstraints.WEST, 
				GridBagConstraints.NONE, 
				10, 10, 0, 10));
		
		panelNewUser.add(new JLabel("Last Name :"),  SwingUtils.getConstraints(1, 2, 1, 0, 0, 
				GridBagConstraints.WEST, 
				GridBagConstraints.NONE, 
				10, 10, 0, 10));

		panelNewUser.add(txtMName,  SwingUtils.getConstraints(2, 0, 1, 0, 0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.HORIZONTAL, 
				10, 10, 0, 10));
		
		panelNewUser.add(txtFName,  SwingUtils.getConstraints(2, 1, 1, 0, 0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.HORIZONTAL, 
				10, 10, 0, 10));
		
		panelNewUser.add(txtLName,  SwingUtils.getConstraints(2, 2, 1, 0.1, 0.1, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.REMAINDER, 
				10, 10, 0, 10));
		
		// Groups
		panelNewUser.add(new JLabel("Groups: "), SwingUtils.getConstraints(3, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.BOTH, 
				10, 5, 5, 5));
		
		lstGroups = new JAceList();			
		Vector vUsrGrps = ACEConnector.getInstance().getUserGrpsAll();
		lstGroups.setItems(vUsrGrps);  		
		panelNewUser.add(lstGroups,SwingUtils.getConstraints(3, 1, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.BOTH, 
				10, 5, 5, 5));
		

		
		add(panelNewUser, BorderLayout.CENTER);
		add(panelSaveClose, BorderLayout.SOUTH);

	}
	
	private boolean verifyInfo(){
		String newUser = new String();
		String oldUser = new String();
		User oldUserObj = new User(); 
		boolean correctInfo = true;

		newUser = txtUserID.getText();
		oldUserObj = modelUsers.getRowObject(tblUsers.getSelectedRow());
		oldUser = oldUserObj.getUserID();
		if (lstGroups.getSelectedItems() == null) {
			correctInfo = false;
			System.out.println("All users have to belong to a group");
		}
		// Check if user id already exists
		if(modeAED == "A"){
			for (int x = 0; x < modelUsers.getRowCount(); x++){
				if(newUser.equals(tblUsers.getValueAt(x, 1))){
					System.out.println("This User ID already exists.");
					correctInfo = false;
				}
			}
		}
		
		if (modeAED == "E"){
		if (txtNewPwd.getText().toString().length() > 0){
			if (txtRetypePwd.getText() != null){
			if (txtNewPwd.getText().equals(txtRetypePwd.getText())){
				System.out.println("Before saving password is :" + pwd + " and saving " + txtNewPwd.getText());
				pwd = txtNewPwd.getText();
//				correctInfo = true;
			}	else {
				    JOptionPane.showMessageDialog(null, "Passwords do not match.");
				    correctInfo = false;
				}
			}
			else {
			    JOptionPane.showMessageDialog(null, "Retype password.");
			    correctInfo = false;
			}
		}
//		else {
//			txtNewPwd.setText(pwd);
//		} 
		}

		return correctInfo;
	}
	
	
	private void saveUser(){
		
		User userObjSave = new User();
		boolean isUserSaved = false;
		boolean isUserUpdated = false;
		boolean grpChanged = false;
		try {
		if (verifyInfo()){
			userObjSave.setmName(noNull(txtMName.getText()));
			userObjSave.setfName(noNull(txtFName.getText()));
			userObjSave.setlName(txtLName.getText());
			userObjSave.setGroups(lstGroups.getSelectedItems());
			if (modeAED == "A"){
				System.out.println("Saving new user");
				userObjSave.setUserID(noNull(txtUserID.getText()));
				isUserSaved = ACEConnector.getInstance().saveNewUser(userObjSave);
			} else 	if(modeAED == "E"){
				System.out.println("Saving changes made to user info");
				userObjSave.setUserID(noNull(txtEditUserID.getText()));
				userObjSave.setAddress(noNull(txtAddress.getText()));
				userObjSave.setMobile(noNull(txtMob.getText()));
				userObjSave.setPhone(noNull(txtPhone.getText()));
				userObjSave.setDesig(noNull(txtDesig.getText()));
				userObjSave.setDob(noNull(dateDOB.getDate()));
				userObjSave.setEmail(noNull(txtEmail.getText()));
				userObjSave.setPincode(noNull(txtPin.getText()));
				userObjSave.setPassWord(pwd);
				grpChanged = true;
				isUserUpdated = ACEConnector.getInstance().saveUserInfo(userObjSave, grpChanged);
			}
			if (isUserSaved){
				System.out.println("New User entry successfull");
				newlist();
			}
			else if (isUserUpdated) {
				System.out.println("User information updated");
				newlist();
			}
			else
				System.out.println("New User / User information not saved");
		} else System.out.println("Error in information entered");
		
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void editUser() {
		User userObj = new User();
		int x = tblUsers.getSelectedRow();
		userObj = modelUsers.getRowObject(tblUsers.getSelectedRow());
		panelMain.setVisible(false);
		panelAddEditDelBtn.setVisible(false);
		EditUserPanel editUserPanel = new EditUserPanel(userObj);
		add(editUserPanel, BorderLayout.CENTER);
		System.out.println("Back to UsersPanel.java");
		panelMain.setVisible(true);
		
//		User userInfoObj = new User();
//		pwd = new String("hello");
//
//		panelNewUser.setVisible(false);
//		panelSaveClose.setVisible(false);
////		panelEdit.removeAll();
//		panelEdit.setVisible(true);
//		panelEditSaveClose.setVisible(true);
//		panelAddEditDelBtn.setVisible(false);
//		panelMain.setVisible(false);
//		panelEdit.repaint();
//
//
//		try {
//			
//			panelEdit.add( new JLabel("User ID"), SwingUtils.getConstraints(0, 0, 1, 0, 0,
//					GridBagConstraints.WEST, GridBagConstraints.NONE, 5, 5, 5, 5));
//			
//			txtEditUserID = new JTextField();
////			txtEditUserID.setText("Test_ID");
//			txtEditUserID.setEditable(false);
//			panelEdit.add(txtEditUserID, SwingUtils.getConstraints(0, 1, 1, 0, 0,
//						GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 5, 5, 5, 5));
//			
//			// Users Name
//			txtLName = new JTextField();
//			txtFName = new JTextField();
//			txtMName = new JTextField();
//			panelEdit.add(new JLabel("Last Name: "),SwingUtils.getConstraints(1, 0, 1, 0,0, 
//					GridBagConstraints.WEST, 
//					GridBagConstraints.NONE, 
//					10, 5, 5, 5));
//			txtLName.setPreferredSize(new Dimension(160,20));
//			panelEdit.add(txtLName,SwingUtils.getConstraints(1, 1, 1, 0, 0, 
//					GridBagConstraints.WEST, 
//					GridBagConstraints.HORIZONTAL, 
//					5, 0, 5, 10));
//			
//			panelEdit.add(new JLabel("First Name: "),SwingUtils.getConstraints(1, 2, 1, 0,0, 
//					GridBagConstraints.WEST, 
//					GridBagConstraints.NONE, 
//					5, 5, 5, 5));
//			
//			txtFName.setSize(new Dimension(50, 140));
//			panelEdit.add(txtFName,SwingUtils.getConstraints(1, 3, 1, 0, 0, 
//					GridBagConstraints.WEST, 
//					GridBagConstraints.HORIZONTAL, 
//					5, 0, 5, 10));
//			
//			panelEdit.add(new JLabel("Middle Name: "),SwingUtils.getConstraints(1, 4, 1, 0,0, 
//					GridBagConstraints.WEST, 
//					GridBagConstraints.NONE, 
//					5, 5, 5, 5));
//			txtMName.setPreferredSize(new Dimension(160,20));
//			panelEdit.add(txtMName,SwingUtils.getConstraints(1, 5, 1, 0, 0, 
//					GridBagConstraints.WEST, 
//					GridBagConstraints.HORIZONTAL, 
//					5, 0, 5, 10));
//
//			JLabel lblGender = new JLabel("Gender: ");
//			panelEdit.add(lblGender,SwingUtils.getConstraints(2, 0, 1, 0,0, 
//					GridBagConstraints.WEST, 
//					GridBagConstraints.NONE, 
//					5, 5, 5, 5));
//			
//			cbGender = new JComboBox();
//			cbGender.addItem("Female");
//			cbGender.addItem("Male");
//			cbGender.addItem("Other");
//			panelEdit.add(cbGender,SwingUtils.getConstraints(2, 1, 1, 0,0, 
//					GridBagConstraints.WEST, 
//					GridBagConstraints.NONE, 
//					5, 5, 5, 10));
//			
//			//DOB
//			JLabel lblDOB	= new JLabel("Date of Birth: ");
//			panelEdit.add(lblDOB,SwingUtils.getConstraints(2, 2, 1, 0,0, 
//					GridBagConstraints.WEST, 
//					GridBagConstraints.NONE, 
//					5, 5, 5, 5));
//			
//			dateDOB = new JAceDate();
//			dateDOB.setDate(""); //set no date initially
//			panelEdit.add(dateDOB,SwingUtils.getConstraints(2, 3, 1, 0,0, 
//					GridBagConstraints.WEST, 
//					GridBagConstraints.HORIZONTAL, 
//					5, 5, 5, 10));
//			
//			// Designation
//			txtDesig = new JTextField();
//			panelEdit.add(new JLabel("Designation: "), SwingUtils.getConstraints(2, 4, 1, 0,0, 
//					GridBagConstraints.WEST, 
//					GridBagConstraints.NONE, 
//					5, 5, 5, 5));
//			txtDesig.setPreferredSize(new Dimension(120,20));
//			panelEdit.add(txtDesig,SwingUtils.getConstraints(2, 5, 1, 0,1, 
//					GridBagConstraints.WEST, 
//					GridBagConstraints.HORIZONTAL, 
//					5, 5, 5, 10));
//			
//			
//			// Address
//			txtAddress = new JTextField();
//			txtPin = new JTextField();
//			panelEdit.add( new JLabel("Address: "), SwingUtils.getConstraints(3, 0, 1, 0, 0,
//					GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, 5, 5, 5, 5));
//			
//			panelEdit.add(txtAddress, SwingUtils.getConstraints(3, 1, 3, 1, 0.1, 0.1,
//						GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, 5, 5, 5, 5));
//			
//			// Groups
//			panelEdit.add(new JLabel("Groups: "), SwingUtils.getConstraints(3, 4, 1, 0,0, 
//					GridBagConstraints.NORTHWEST, 
//					GridBagConstraints.BOTH, 
//					10, 5, 0, 5));
//			
//			lstGroups = new JAceList();			
//			Vector vUsrGrps = ACEConnector.getInstance().getUserGrpsAll();
//			lstGroups.setItems(vUsrGrps);  		
//			panelEdit.add(lstGroups,SwingUtils.getConstraints(4, 4, 2, 0,0, 
//					GridBagConstraints.NORTHWEST, 
//					GridBagConstraints.BOTH, 
//					5, 5, 5, 5));
//			
//			
////			mdlZip = new MHZipTableModel();
////			mdlZip.setList(ACEConnector.getInstance().getPinInfo());
////			tblZip = new JTable(mdlZip);
////			JScrollPane scrPanZipTbl = new JScrollPane(tblZip);
////			tblZip.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
////
//////			Vector<Pincode> vPinInfo = ACEConnector.getInstance().getPinInfo();
////			Vector<String> sPin = new Vector<String>();
//
////			lstZip = new JList();
//			
//			JLabel lblZip = new JLabel("Pincode :");
//			JTextField txtbuff = new JTextField();
//
//			panelEdit.add(lblZip, SwingUtils.getConstraints(4, 0, 1, 0, 0, 
//					GridBagConstraints.WEST, GridBagConstraints.NONE,
//					10, 10, 5, 5));
//			
//			txtPin.setPreferredSize(new Dimension(60,20));
//			panelEdit.add(txtPin, SwingUtils.getConstraints(4, 1, 1, 0, 0, 
//					GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
//							10, 10, 5, 5));
//			
//			// Contact info
//			txtEmail = new JTextField();
//			txtMob = new JTextField();
//			txtPhone = new JTextField();
//			panelEdit.add(new JLabel("Email: "), SwingUtils.getConstraints(4, 2, 1, 0, 0, 
//					GridBagConstraints.WEST, GridBagConstraints.NONE,
//					10, 10, 5, 5));
//			panelEdit.add(txtEmail, SwingUtils.getConstraints(4, 3, 1, 0, 0, 
//					GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
//					10, 10, 5, 5));
//			
//			panelEdit.add(new JLabel("Mobile: "), SwingUtils.getConstraints(5, 0, 1, 0, 0, 
//					GridBagConstraints.WEST, GridBagConstraints.NONE,
//					10, 10, 5, 5));
//			panelEdit.add(txtMob, SwingUtils.getConstraints(5, 1, 1, 0, 0, 
//					GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
//					10, 10, 5, 5));
//
//			panelEdit.add(new JLabel("Other: "), SwingUtils.getConstraints(5, 2, 1, 0, 0, 
//					GridBagConstraints.WEST, GridBagConstraints.NONE,
//					10, 10, 5, 5));
//			panelEdit.add(txtPhone, SwingUtils.getConstraints(5, 3, 1, 0, 0, 
//					GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
//					10, 10, 5, 5));
//			
//			// Change user password
//			
//			txtRetypePwd = new JTextField();
//			txtNewPwd = new JTextField();
//
//			panelEdit.add(new JLabel("New Password: "), SwingUtils.getConstraints(6, 0, 1, 0, 0, 
//					GridBagConstraints.WEST, GridBagConstraints.NONE,
//					10, 10, 5, 5));
//			panelEdit.add(txtNewPwd, SwingUtils.getConstraints(6, 1, 1, 0, 0, 
//					GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
//					10, 10, 5, 5));
//			
//			panelEdit.add(new JLabel("Retype : "), SwingUtils.getConstraints(6, 2, 1, 0, 0, 
//					GridBagConstraints.WEST, GridBagConstraints.NONE,
//					10, 10, 5, 5));
//			panelEdit.add(txtRetypePwd, SwingUtils.getConstraints(6, 3, 1, 0, 0, 
//					GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
//					10, 10, 5, 5));
//		
//			add(panelEdit, BorderLayout.WEST);
//			add(panelEditSaveClose, BorderLayout.SOUTH);
//			setValues();
//		} catch (Exception e){
//			e.printStackTrace();
//		}

	}
	
//	public void setValues(){
//		
//		try{
//			User userObj = modelUsers.getRowObject(tblUsers.getSelectedRow());
//			
//			vUser = ACEConnector.getInstance().getUserInfo(userObj.getUserID());
//			
//			for (int i = 0; i < vUser.size(); i++){
//				User user = new User();
//				user = vUser.elementAt(0);
////				if (modeAED == "A"){
////					txtUserID.setText(user.getUserID());
////				} 
////				else if (modeAED == "E"){
//				txtEditUserID.setText(user.getUserID());
//				txtFName.setText(user.getfName());
//				txtMName.setText(user.getmName());
//				txtLName.setText(user.getlName());
//				cbGender.setSelectedItem(user.getGender());
//				txtAddress.setText(user.getAddress());
//				txtPin.setText(user.getPincode());
//				txtDesig.setText(user.getDesig());
//				dateDOB.setDate(user.getDob());
//				txtEmail.setText(user.getEmail());
//				txtMob.setText(user.getMobile());
//				txtPhone.setText(user.getPhone());
//				lstGroups.setText(DB.joinVStrings(user.getGroups()));
//				pwd = (user.getPassWord());
//				System.out.println("Old pwd ;" + pwd);
////				}
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}	
	
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

	public String noNull(String val){
		if (val == null || val == "null")
			return "";
		return val.trim().toString();
	}

	
	@Override
	public void setMHPanelObserver(MHPanelObserver obs) {
		// TODO Auto-generated method stub
		
	}






	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(btnAddList)){
			modeAED = "A";
			userDetails();
		}
		
		if (e.getSource().equals(btnSave))
			saveUser();
		
		if (e.getSource().equals(btnClose)) {
//			panelNewUser.setVisible(false);
//			panelSaveClose.setVisible(false);
			newlist();
		}
		if(e.getSource().equals(btnDelList)){
			modeAED =  "D";
			int selUser = tblUsers.getSelectedRowCount();
			String sTtl = new String("Delete User");
			if (selUser > 1) 
				sTtl = "Delete Users";
			if (selUser > 0){
				int r = optConfirmDel.showConfirmDialog(null, "Sure?", sTtl, 2);
				if (r == JOptionPane.YES_OPTION){
					modeAED = "D";
					System.out.println("modeAED set to ---- D ");
					delUser();
					newlist();
				}
			}
		}
		if(e.getSource().equals(btnEdit)){
			modeAED = "E";
			System.out.println("Edit button selected");
			editUser();
		}
		if (e.getSource().equals(btnEditSave))
			saveUser();
		if(e.getSource().equals(btnEditCancel))
			newlist();
	}

}
