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
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.act.client.components.JAceDate;
import com.act.client.components.JAceList;
import com.act.common.SwingUtils;
import com.act.common.User;
import com.act.server.db.DB;

public class EditUserPanel extends JPanel implements ActionListener  {
	
	JPanel panelEdit, panelEditSaveClose;

	JButton btnAddList, btnEdit, btnDelList;
//	JButton btnEditSave, btnEditCancel;
	JButton btnSave, btnClose;
	
	JTextField txtUserID, txtNewMName, txtNewFName, txtNewLName;
	JTextField txtEditUserID, txtFName, txtMName, txtLName;
	JTextField txtRetypePwd, txtNewPwd;
	JTextField txtDesig;
	JTextArea tarAddress;
	JTextField txtEmail, txtPin, txtMob, txtPhone;
	JAceList lstGroups;
	JAceDate dateDOB;
	JComboBox cbGender;
	
	Vector<User> vUser = new Vector<User>();

	JOptionPane optConfirmDel;
	String modeAED = "E";
	String pwd ;
	
	User userObj = null;
	
	public EditUserPanel (User userObj){
		
		this.userObj = userObj;
		panelEdit = new JPanel();
		panelEdit.setLayout(new GridBagLayout());
//		add(panelEdit, BorderLayout.WEST);

		panelEditSaveClose = new JPanel();
//		panelSaveCloseButton.setPreferredSize(new Dimension(160,50));
		panelEditSaveClose.setBackground(Color.DARK_GRAY);
		panelEditSaveClose.setLayout(new FlowLayout());
//		btnEditSave = new JButton("Save");
//		btnEditSave.addActionListener(this);
//		btnEditCancel = new JButton("Close");
//		btnEditCancel.addActionListener(this);
		btnSave = new JButton("Save");
		btnSave.addActionListener(this);
		btnClose = new JButton("Close");
		btnClose.addActionListener(this);
		panelEditSaveClose.add(btnSave);
		panelEditSaveClose.add(btnClose);
//		panelEditSaveClose.add(btnEditSave);
//		panelEditSaveClose.add(btnEditCancel);
		add(panelEditSaveClose, BorderLayout.SOUTH);
		panelEditSaveClose.setVisible(false);
//		panelEdit.setVisible(false);
//		panelEditSaveClose.setVisible(false);
		
		initUI();
		
	}
	
	private void initUI(){
		User userInfoObj = new User();
		pwd = new String("hello");

		try {
			
			panelEdit.add( new JLabel("User ID"), SwingUtils.getConstraints(0, 0, 1, 0, 0,
					GridBagConstraints.WEST, GridBagConstraints.NONE, 5, 5, 5, 5));
			
			txtEditUserID = new JTextField();
//			txtEditUserID.setText("Test_ID");
			txtEditUserID.setEditable(false);
			panelEdit.add(txtEditUserID, SwingUtils.getConstraints(0, 1, 1, 0, 0,
						GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 5, 5, 5, 5));
			
			// Users Name
			txtLName = new JTextField();
			txtFName = new JTextField();
			txtMName = new JTextField();
			panelEdit.add(new JLabel("Last Name: "),SwingUtils.getConstraints(1, 0, 1, 0,0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					10, 5, 5, 5));
			txtLName.setPreferredSize(new Dimension(160,20));
			panelEdit.add(txtLName,SwingUtils.getConstraints(1, 1, 1, 0, 0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.HORIZONTAL, 
					5, 0, 5, 10));
			
			panelEdit.add(new JLabel("First Name: "),SwingUtils.getConstraints(1, 2, 1, 0,0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					5, 5, 5, 5));
			
			txtFName.setSize(new Dimension(50, 140));
			panelEdit.add(txtFName,SwingUtils.getConstraints(1, 3, 1, 0, 0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.HORIZONTAL, 
					5, 0, 5, 10));
			
			panelEdit.add(new JLabel("Middle Name: "),SwingUtils.getConstraints(1, 4, 1, 0,0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					5, 5, 5, 5));
			txtMName.setPreferredSize(new Dimension(160,20));
			panelEdit.add(txtMName,SwingUtils.getConstraints(1, 5, 1, 0, 0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.HORIZONTAL, 
					5, 0, 5, 10));

			JLabel lblGender = new JLabel("Gender: ");
			panelEdit.add(lblGender,SwingUtils.getConstraints(2, 0, 1, 0,0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					5, 5, 5, 5));
			
			cbGender = new JComboBox();
			cbGender.addItem("Female");
			cbGender.addItem("Male");
			cbGender.addItem("Other");
			panelEdit.add(cbGender,SwingUtils.getConstraints(2, 1, 1, 0,0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					5, 5, 5, 10));
			
			//DOB
			JLabel lblDOB	= new JLabel("Date of Birth: ");
			panelEdit.add(lblDOB,SwingUtils.getConstraints(2, 2, 1, 0,0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					5, 5, 5, 5));
			
			dateDOB = new JAceDate();
			dateDOB.setDate(""); //set no date initially
			panelEdit.add(dateDOB,SwingUtils.getConstraints(2, 3, 1, 0,0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.HORIZONTAL, 
					5, 5, 5, 10));
			
			// Designation
			txtDesig = new JTextField();
			panelEdit.add(new JLabel("Designation: "), SwingUtils.getConstraints(2, 4, 1, 0,0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					5, 5, 5, 5));
			txtDesig.setPreferredSize(new Dimension(120,20));
			panelEdit.add(txtDesig,SwingUtils.getConstraints(2, 5, 1, 0,1, 
					GridBagConstraints.WEST, 
					GridBagConstraints.HORIZONTAL, 
					5, 5, 5, 10));
			
			
			// Address
			tarAddress = new JTextArea(5, 20);
			JScrollPane spAddress = new JScrollPane(tarAddress); 
//			tarAddress.setEditable(false);
			txtPin = new JTextField();
			panelEdit.add( new JLabel("Address: "), SwingUtils.getConstraints(3, 0, 1, 0, 0,
					GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, 5, 5, 5, 5));
			
			panelEdit.add(tarAddress, SwingUtils.getConstraints(3, 1, 3, 1, 0.1, 0.1,
						GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, 5, 5, 5, 5));
			
			// Groups
			panelEdit.add(new JLabel("Groups: "), SwingUtils.getConstraints(3, 4, 1, 0,0, 
					GridBagConstraints.NORTHWEST, 
					GridBagConstraints.BOTH, 
					10, 5, 0, 5));
			
			lstGroups = new JAceList();			
			Vector vUsrGrps = ACEConnector.getInstance().getUserGrpsAll();
			lstGroups.setItems(vUsrGrps);  		
			panelEdit.add(lstGroups,SwingUtils.getConstraints(4, 4, 2, 0,0, 
					GridBagConstraints.NORTHWEST, 
					GridBagConstraints.BOTH, 
					5, 5, 5, 5));
			
			
//			mdlZip = new MHZipTableModel();
//			mdlZip.setList(ACEConnector.getInstance().getPinInfo());
//			tblZip = new JTable(mdlZip);
//			JScrollPane scrPanZipTbl = new JScrollPane(tblZip);
//			tblZip.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
//
////			Vector<Pincode> vPinInfo = ACEConnector.getInstance().getPinInfo();
//			Vector<String> sPin = new Vector<String>();

//			lstZip = new JList();
			
			JLabel lblZip = new JLabel("Pincode :");
			JTextField txtbuff = new JTextField();

			panelEdit.add(lblZip, SwingUtils.getConstraints(4, 0, 1, 0, 0, 
					GridBagConstraints.WEST, GridBagConstraints.NONE,
					10, 10, 5, 5));
			
			txtPin.setPreferredSize(new Dimension(60,20));
			panelEdit.add(txtPin, SwingUtils.getConstraints(4, 1, 1, 0, 0, 
					GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
							10, 10, 5, 5));
			
			// Contact info
			txtEmail = new JTextField();
			txtMob = new JTextField();
			txtPhone = new JTextField();
			panelEdit.add(new JLabel("Email: "), SwingUtils.getConstraints(4, 2, 1, 0, 0, 
					GridBagConstraints.WEST, GridBagConstraints.NONE,
					10, 10, 5, 5));
			panelEdit.add(txtEmail, SwingUtils.getConstraints(4, 3, 1, 0, 0, 
					GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
					10, 10, 5, 5));
			
			panelEdit.add(new JLabel("Mobile: "), SwingUtils.getConstraints(5, 0, 1, 0, 0, 
					GridBagConstraints.WEST, GridBagConstraints.NONE,
					10, 10, 5, 5));
			panelEdit.add(txtMob, SwingUtils.getConstraints(5, 1, 1, 0, 0, 
					GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
					10, 10, 5, 5));

			panelEdit.add(new JLabel("Other: "), SwingUtils.getConstraints(5, 2, 1, 0, 0, 
					GridBagConstraints.WEST, GridBagConstraints.NONE,
					10, 10, 5, 5));
			panelEdit.add(txtPhone, SwingUtils.getConstraints(5, 3, 1, 0, 0, 
					GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
					10, 10, 5, 5));
			
			// Change user password
			
			txtRetypePwd = new JTextField();
			txtNewPwd = new JTextField();

			panelEdit.add(new JLabel("New Password: "), SwingUtils.getConstraints(6, 0, 1, 0, 0, 
					GridBagConstraints.WEST, GridBagConstraints.NONE,
					10, 10, 5, 5));
			panelEdit.add(txtNewPwd, SwingUtils.getConstraints(6, 1, 1, 0, 0, 
					GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
					10, 10, 5, 5));
			
			panelEdit.add(new JLabel("Retype : "), SwingUtils.getConstraints(6, 2, 1, 0, 0, 
					GridBagConstraints.WEST, GridBagConstraints.NONE,
					10, 10, 5, 5));
			panelEdit.add(txtRetypePwd, SwingUtils.getConstraints(6, 3, 1, 0, 0, 
					GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
					10, 10, 5, 5));
		
			add(panelEdit, BorderLayout.WEST);
			panelEdit.setVisible(true);
			System.out.println("Displaying panelEdit");
			panelEditSaveClose.setPreferredSize(new Dimension(160,50));
			add(panelEditSaveClose, BorderLayout.SOUTH);
			panelEditSaveClose.setVisible(true);
			setValues();
			panelEdit.setFocusable(true);
			
		} catch (Exception e){
			e.printStackTrace();
		}

	}
	public void setValues(){
		
		try{
//			User userObj = null; //modelUsers.getRowObject(tblUsers.getSelectedRow());
			
//			vUser = ACEConnector.getInstance().getUserInfo(userObj.getUserID());
			
//				if (modeAED == "A"){
//					txtUserID.setText(user.getUserID());
//				} 
//				else if (modeAED == "E"){
				txtEditUserID.setText(userObj.getUserID());
				txtFName.setText(userObj.getfName());
				txtMName.setText(userObj.getmName());
				txtLName.setText(userObj.getlName());
				cbGender.setSelectedItem(userObj.getGender());
				tarAddress.setText(userObj.getAddress());
				txtPin.setText(userObj.getPincode());
				txtDesig.setText(userObj.getDesig());
				dateDOB.setDate(userObj.getDob());
				txtEmail.setText(userObj.getEmail());
				txtMob.setText(userObj.getMobile());
				txtPhone.setText(userObj.getPhone());
				lstGroups.setText(DB.joinVStrings(userObj.getGroups()));
				pwd = (userObj.getPassWord());
				System.out.println("Old pwd ;" + pwd);
//				}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}	

	private void saveUser(){
		
		User userObjSave = new User();
		boolean isUserSaved = false;
		boolean isUserUpdated = false;
		boolean grpChanged = false;
		System.out.println("Initiating save");
		try {
		if (verifyInfo()){
			userObjSave.setmName(noNull(txtMName.getText()));
			userObjSave.setfName(noNull(txtFName.getText()));
			userObjSave.setlName(txtLName.getText());
			userObjSave.setGroups(lstGroups.getSelectedItems());
//			if (modeAED == "A"){
//				System.out.println("Saving new user");
//				userObjSave.setUserID(noNull(txtUserID.getText()));
//				isUserSaved = ACEConnector.getInstance().saveNewUser(userObjSave);
//			} else 	if(modeAED == "E"){
				userObjSave.setUserID(noNull(txtEditUserID.getText()));
				userObjSave.setAddress(noNull(tarAddress.getText()));
				System.out.println("Saving changes made to user info " + userObjSave.getAddress());
				userObjSave.setMobile(noNull(txtMob.getText()));
				userObjSave.setPhone(noNull(txtPhone.getText()));
				userObjSave.setDesig(noNull(txtDesig.getText()));
				userObjSave.setDob(dateDOB.getDate());
				userObjSave.setEmail(noNull(txtEmail.getText()));
				userObjSave.setPincode(noNull(txtPin.getText()));
				userObjSave.setPassWord(pwd);
				grpChanged = true;
				isUserUpdated = ACEConnector.getInstance().saveUserInfo(userObjSave, grpChanged);
//			}
			if (isUserSaved){
				System.out.println("New User entry successfull");
//				newlist();
				setVisible(false);
				
			}
			else if (isUserUpdated) {
				System.out.println("User information updated");
//				newlist();
				setVisible(false);

			}
			else
				System.out.println("New User / User information not saved");
		} else System.out.println("Error in information entered");
		
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	private boolean verifyInfo(){
		boolean correctInfo = true;
		if (lstGroups.getSelectedItems() == null) {
			correctInfo = false;
			System.out.println("All users have to belong to a group");
		}
//		if (modeAED == "E"){
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
//		}

		return correctInfo;
	}

	public String noNull(String val){
		if (val == null || val == "null")
			return "";
		return val.trim().toString();
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(btnSave)){
			saveUser();
			panelEdit.removeAll();
			setVisible(false);
			panelEdit.removeAll();
			panelEditSaveClose.removeAll();
			setFocusable(false);
		}
		if (e.getSource().equals(btnClose)) {
			panelEdit.removeAll();
			setVisible(false);
			panelEdit.removeAll();
			panelEditSaveClose.removeAll();

//			newlist();
			setFocusable(false);
		}

		
	}


}
