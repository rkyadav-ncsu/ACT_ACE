package com.act.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.act.client.components.JAceDate;
import com.act.client.components.JAceList;
import com.act.client.models.MHUsersTableModel;
import com.act.common.SwingUtils;
import com.act.common.User;
import com.act.server.db.DB;

public class NewEditUserPanel extends JPanel implements ActionListener {
	NewUsersMainPanel parent;
	User userObj;
	MHUsersTableModel modUsersList;
	
//	JTextField txtUserID, txtNewMName, txtNewFName, txtNewLName;
	JTextField txtUserID, txtFName, txtMName, txtLName;
	JPasswordField pwdRetypePwd, pwdNewPwd;
	JTextField txtDesig;
	JTextField txtEmail, txtPin, txtMob, txtPhone;
	JTextArea tarAddress;
	JAceList lstGroups;
	JComboBox cbGender;
	JAceDate dateDOB;
	
	JPanel panelSaveClosebtn;
	JButton btnSave, btnClose;
	
	boolean bEditMode = false;
	boolean bRetToParent = false;
	String pwd = "";
	
	public NewEditUserPanel(NewUsersMainPanel parent){
		this.parent = parent;
		
		initUI();
	}

	public void setUser(User selectedUser){
		
		userObj = selectedUser;
		bEditMode = (userObj != null);
		
		setValues();
	}
	
	public void setAllUsers(MHUsersTableModel modListUsers){
		modUsersList = modListUsers;
		
	}
	//Note for Nathan:
	//In this panel, when close button is clicked, we need to go back to
	//userslistpanel. For that make this call below
	
	// parent.showCard(NewUsersMainPanel.USER_LIST);

	private void initUI(){
		
		try {
		
		setLayout(new GridBagLayout());	
		add( new JLabel("User ID"), SwingUtils.getConstraints(0, 0, 1, 0, 0,
				GridBagConstraints.WEST, GridBagConstraints.NONE, 5, 5, 5, 5));
		
		txtUserID = new JTextField();
//		txtEditUserID.setText("Test_ID");
		if (bEditMode)
			txtUserID.setEditable(false);
		else
			txtUserID.setEditable(true);
		
		add(txtUserID, SwingUtils.getConstraints(0, 1, 1, 0, 0,
					GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 5, 5, 5, 5));
		
		// Users Name
		txtLName = new JTextField();
		txtFName = new JTextField();
		txtMName = new JTextField();
		add(new JLabel("Last Name: "),SwingUtils.getConstraints(1, 0, 1, 0,0, 
				GridBagConstraints.WEST, 
				GridBagConstraints.NONE, 
				10, 5, 5, 5));
		txtLName.setPreferredSize(new Dimension(160,20));
		add(txtLName,SwingUtils.getConstraints(1, 1, 1, 0, 0, 
				GridBagConstraints.WEST, 
				GridBagConstraints.HORIZONTAL, 
				5, 0, 5, 10));
		
		add(new JLabel("First Name: "),SwingUtils.getConstraints(1, 2, 1, 0,0, 
				GridBagConstraints.WEST, 
				GridBagConstraints.NONE, 
				5, 5, 5, 5));
		
		txtFName.setSize(new Dimension(50, 140));
		add(txtFName,SwingUtils.getConstraints(1, 3, 1, 0, 0, 
				GridBagConstraints.WEST, 
				GridBagConstraints.HORIZONTAL, 
				5, 0, 5, 10));
		
		add(new JLabel("Middle Name: "),SwingUtils.getConstraints(1, 4, 1, 0,0, 
				GridBagConstraints.WEST, 
				GridBagConstraints.NONE, 
				5, 5, 5, 5));
		txtMName.setPreferredSize(new Dimension(160,20));
		add(txtMName,SwingUtils.getConstraints(1, 5, 1, 0, 0, 
				GridBagConstraints.WEST, 
				GridBagConstraints.HORIZONTAL, 
				5, 0, 5, 10));

		JLabel lblGender = new JLabel("Gender: ");
		add(lblGender,SwingUtils.getConstraints(2, 0, 1, 0,0, 
				GridBagConstraints.WEST, 
				GridBagConstraints.NONE, 
				5, 5, 5, 5));
		
		cbGender = new JComboBox();
		cbGender.addItem("Female");
		cbGender.addItem("Male");
		cbGender.addItem("Other");
		add(cbGender,SwingUtils.getConstraints(2, 1, 1, 0,0, 
				GridBagConstraints.WEST, 
				GridBagConstraints.NONE, 
				5, 5, 5, 10));
		
		//DOB
		JLabel lblDOB	= new JLabel("Date of Birth: ");
		add(lblDOB,SwingUtils.getConstraints(2, 2, 1, 0,0, 
				GridBagConstraints.WEST, 
				GridBagConstraints.NONE, 
				5, 5, 5, 5));
		
		dateDOB = new JAceDate();
//		dateDOB.setDate(""); //set no date initially
		add(dateDOB,SwingUtils.getConstraints(2, 3, 1, 0,0, 
				GridBagConstraints.WEST, 
				GridBagConstraints.HORIZONTAL, 
				5, 5, 5, 10));
		
		// Designation
		txtDesig = new JTextField();
		add(new JLabel("Designation: "), SwingUtils.getConstraints(2, 4, 1, 0,0, 
				GridBagConstraints.WEST, 
				GridBagConstraints.NONE, 
				5, 5, 5, 5));
		txtDesig.setPreferredSize(new Dimension(120,20));
		add(txtDesig,SwingUtils.getConstraints(2, 5, 1, 0,1, 
				GridBagConstraints.WEST, 
				GridBagConstraints.HORIZONTAL, 
				5, 5, 5, 10));
		
		// Address
		tarAddress = new JTextArea();
		JScrollPane spAddress = new JScrollPane(tarAddress); 
		txtPin = new JTextField();
		add( new JLabel("Address: "), SwingUtils.getConstraints(3, 0, 1, 0, 0,
				GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, 5, 5, 5, 5));
		
		add(tarAddress, SwingUtils.getConstraints(3, 1, 3, 1, 0.1, 0.1,
					GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, 5, 5, 5, 5));
		
		// Groups
		add(new JLabel("Groups: "), SwingUtils.getConstraints(3, 4, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.BOTH, 
				10, 5, 0, 5));
		
		lstGroups = new JAceList();			
		Vector vUsrGrps = ACEConnector.getInstance().getUserGrpsAll();
		lstGroups.setItems(vUsrGrps);  		
		add(lstGroups,SwingUtils.getConstraints(4, 4, 2, 0.1,0.1, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.BOTH, 
				5, 5, 5, 5));
		if (bEditMode)
			lstGroups.setText(userObj.getGroups().toString());
		
//		mdlZip = new MHZipTableModel();
//		mdlZip.setList(ACEConnector.getInstance().getPinInfo());
//		tblZip = new JTable(mdlZip);
//		JScrollPane scrPanZipTbl = new JScrollPane(tblZip);
//		tblZip.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
//
////		Vector<Pincode> vPinInfo = ACEConnector.getInstance().getPinInfo();
//		Vector<String> sPin = new Vector<String>();

//		lstZip = new JList();
		
		JLabel lblZip = new JLabel("Pincode :");
		JTextField txtbuff = new JTextField();

		add(lblZip, SwingUtils.getConstraints(4, 0, 1, 0, 0, 
				GridBagConstraints.WEST, GridBagConstraints.NONE,
				10, 10, 5, 5));
		
		txtPin.setPreferredSize(new Dimension(60,20));
		add(txtPin, SwingUtils.getConstraints(4, 1, 1, 0, 0, 
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
						10, 10, 5, 5));
		
		// Contact info
		txtEmail = new JTextField();
		txtMob = new JTextField();
		txtPhone = new JTextField();
		add(new JLabel("Email: "), SwingUtils.getConstraints(4, 2, 1, 0, 0, 
				GridBagConstraints.WEST, GridBagConstraints.NONE,
				10, 10, 5, 5));
		add(txtEmail, SwingUtils.getConstraints(4, 3, 1, 0, 0, 
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				10, 10, 5, 5));
		
		add(new JLabel("Mobile: "), SwingUtils.getConstraints(5, 0, 1, 0, 0, 
				GridBagConstraints.WEST, GridBagConstraints.NONE,
				10, 10, 5, 5));
		add(txtMob, SwingUtils.getConstraints(5, 1, 1, 0, 0, 
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				10, 10, 5, 5));

		add(new JLabel("Other: "), SwingUtils.getConstraints(5, 2, 1, 0, 0, 
				GridBagConstraints.WEST, GridBagConstraints.NONE,
				10, 10, 5, 5));
		add(txtPhone, SwingUtils.getConstraints(5, 3, 1, 0, 0, 
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				10, 10, 5, 5));
		
		// Change user password
		
		pwdRetypePwd = new JPasswordField();
		pwdNewPwd = new JPasswordField();

		add(new JLabel("New Password: "), SwingUtils.getConstraints(6, 0, 1, 0, 0, 
				GridBagConstraints.WEST, GridBagConstraints.NONE,
				10, 10, 5, 5));
		add(pwdNewPwd, SwingUtils.getConstraints(6, 1, 1, 0, 0, 
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				10, 10, 5, 5));
		
		add(new JLabel("Retype : "), SwingUtils.getConstraints(6, 2, 1, 0, 0, 
				GridBagConstraints.WEST, GridBagConstraints.NONE,
				10, 10, 5, 5));
		add(pwdRetypePwd, SwingUtils.getConstraints(6, 3, 1, 0, 0, 
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				10, 10, 5, 5));
		
		panelSaveClosebtn = new JPanel();
		panelSaveClosebtn.setPreferredSize(new Dimension(160,50));	
		panelSaveClosebtn.setBackground(Color.DARK_GRAY);
		panelSaveClosebtn.setLayout(new FlowLayout());
//		add(panelSaveClosebtn, BorderLayout.SOUTH);
		add(panelSaveClosebtn, SwingUtils.getConstraints(7, 1, 4, 0, 0, 
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				10, 10, 5, 5));
		btnSave = new JButton("Save");
		btnSave.addActionListener(this);
		btnClose = new JButton("Close");
		btnClose.addActionListener(this);
		panelSaveClosebtn.add(btnSave);
		panelSaveClosebtn.add(btnClose);
//
		
			
	} catch (Exception e){
		e.printStackTrace();
	}

		
	}
	

	private void setValues() {
		
		if (userObj == null){
			//add code to clear the fields
//			JTextField  txtFName, txtMName, txtLName;
//			JTextField txtRetypePwd, txtNewPwd;
//			JTextField txtDesig;
//			JTextField txtAddress, txtEmail, txtPin, txtMob, txtPhone;
//			JAceList lstGroups;
//			JComboBox cbGender;
//			JAceDate dateDOB;
			
			bEditMode = false;
			txtUserID.setText("");
			txtFName.setText("");
			txtMName.setText("");
			txtLName.setText("");
			pwdNewPwd.setText("");
			pwdRetypePwd.setText("");
			txtDesig.setText("");
			tarAddress.setText("");
			txtEmail.setText("");
			txtPin.setText("");
			txtMob.setText("");
			txtPhone.setText("");
			
			dateDOB.setDate("20000101");
			

		}else{
			bEditMode = true;
			txtUserID.setText(userObj.getUserID());
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
			System.out.println("Old pwd : " + pwd);
		}
	}
	
	private boolean verifyInfo(){
		String newUser = new String();
		String oldUser = new String();
		User oldUserObj = new User(); 
		char[] pwdN = pwdNewPwd.getPassword();
		char[] pwdR = pwdRetypePwd.getPassword();
		
		if(!bEditMode){							// !bEditMode is New User being added
			if(txtUserID.getText().equals("")){
				System.out.println("User ID required for new User");
				return false;
			} else 
			newUser = txtUserID.getText();	
			// Check if user id already exists
			for (int x = 0; x < modUsersList.getRowCount(); x++){
				if(newUser.equals(modUsersList.getValueAt(x, 1))){
					System.out.println("This User ID already exists.");
					return false;
				}
			}
		}
		
		if(bEditMode){
	//		oldUserObj = modelUsers.getRowObject(tblUsers.getSelectedRow());
			oldUserObj = userObj;
			oldUser = userObj.getUserID();
		}
		
		if (lstGroups.getSelectedItems() == null) {
			System.out.println("All users have to belong to a group");
			return false;
		}
		
		// Check if pwd has been entered/ changed and matches retyped password
		// for bEditMode true and false the pwdNewPwd is kept blank

		System.out.println("Length of password : " + pwdNewPwd.getPassword().length);
		if (pwdNewPwd.getPassword().length > 0 ) {
			if (pwdRetypePwd.getPassword().length > 0){
			if(Arrays.equals(pwdN, pwdR)){	
				System.out.println("Before saving password is :" + pwd + " and saving " +
											pwdNewPwd.getPassword().toString());
//				pwd = pwdNewPwd.getPassword().toString();
				pwd = pwdN.toString();
				System.out.println("Password entered is : " + pwd);
//				correctInfo = true;
			}	else {
				    JOptionPane.showMessageDialog(null, "Passwords do not match.");
				    System.out.println(pwdN.toString() + " " + pwdR.toString() );
				    return false;
				}
			}
			else {			// if retype password not entered
				System.out.println("Retype password is : " + pwdR[1] + " " + 
						pwdRetypePwd.getPassword().length + " characters. " + pwdN[1]);
			    JOptionPane.showMessageDialog(null, "Retype password.");
			    return false;
			}
		}	// Check if new user then pwdNewPwd cannot be null.
		else {	
			if(!bEditMode){
			    JOptionPane.showMessageDialog(null, "Password has to be entered for new user.");
				return false;
			}
		} 

		return true;
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
			System.out.println("Saving changes made to user info");
			userObjSave.setUserID(noNull(txtUserID.getText()));
			userObjSave.setAddress(noNull(tarAddress.getText()));
			userObjSave.setMobile(noNull(txtMob.getText()));
			userObjSave.setPhone(noNull(txtPhone.getText()));
			userObjSave.setDesig(noNull(txtDesig.getText()));
			userObjSave.setDob(noNull(dateDOB.getDate()));
			userObjSave.setEmail(noNull(txtEmail.getText()));
			userObjSave.setPincode(noNull(txtPin.getText()));
			userObjSave.setPassWord(pwd);
			grpChanged = true;
			if (bEditMode == false){
				System.out.println("Saving new user");
//				userObjSave.setUserID(noNull(txtUserID.getText()));
				isUserSaved = ACEConnector.getInstance().saveNewUser(userObjSave);
			} else
				isUserUpdated = ACEConnector.getInstance().saveUserInfo(userObjSave, grpChanged);

			if (isUserSaved){
			System.out.println("New User entry successfull");
//				newlist();
			bRetToParent = true;
			}else if (isUserUpdated) {
				System.out.println("User information updated");
//				newlist();
				bRetToParent = true;
			}
			else
				System.out.println("New User / User information not saved");
		} else System.out.println("Error in information entered");
		
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	public String noNull(String val){
		if (val == null || val == "null")
			return "";
		return val.trim().toString();
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	
		if (e.getSource().equals(btnClose)){
			parent.showCard(NewUsersMainPanel.USER_LIST);
			
		}
		if (e.getSource().equals(btnSave)) {
			saveUser();
			if(bRetToParent)
				parent.showCard(NewUsersMainPanel.USER_LIST);
		}
	}


}
