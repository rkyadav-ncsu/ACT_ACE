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
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.act.client.components.JAceDate;
import com.act.client.components.JAceList;
import com.act.client.models.MHZipTableModel;
import com.act.common.SwingUtils;
import com.act.common.User;
import com.act.common.GroupsObj;
import com.act.common.UsersandGroupsObj;
import com.sun.jmx.snmp.UserAcl;
import com.act.server.db.DB;

public class UserProfilePanel extends MHPanel implements ActionListener {
	MHPanelObserver panelObserver;
	MHZipTableModel mdlZip;
	JTable tblZip;
	JList lstZip;
	
	JPanel panelMain, panelSaveCloseButton, panelPassword;
	JTextField txtUserID, txtFName, txtMName, txtLName;
	JTextField txtRetypePwd, txtGroups, txtNewPwd;
	JTextField txtDesig;
	JTextArea tarAddress;
	JTextField txtEmail, txtPin, txtMob, txtPhone;
	JComboBox cbGender;
	JAceDate dateDOB;
	
	JButton btnSave;
	Vector<User> vUser;
	String pwd ;
	
	public UserProfilePanel() {
		initUI();
	}
	
	private void initUI(){
		
		User userInfoObj = new User();
		pwd = new String("hello");
		setLayout(new BorderLayout());
		try {
			panelMain = new JPanel();
			panelMain.setLayout(new GridBagLayout());
			add(panelMain, BorderLayout.WEST);
			
			panelMain.add( new JLabel("User ID"), SwingUtils.getConstraints(0, 0, 1, 0, 0,
					GridBagConstraints.WEST, GridBagConstraints.NONE, 5, 5, 5, 5));
			
			txtUserID = new JTextField();
			txtUserID.setText("Test_ID");
			txtUserID.setEditable(false);
			panelMain.add(txtUserID, SwingUtils.getConstraints(0, 1, 1, 0, 0,
						GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 5, 5, 5, 5));
			
			// Users Name
			txtLName = new JTextField();
			txtFName = new JTextField();
			txtMName = new JTextField();
			panelMain.add(new JLabel("Last Name: "),SwingUtils.getConstraints(1, 0, 1, 0,0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					10, 5, 5, 5));
			txtLName.setPreferredSize(new Dimension(160,20));
			panelMain.add(txtLName,SwingUtils.getConstraints(1, 1, 1, 0, 0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.HORIZONTAL, 
					5, 0, 5, 10));
			
			panelMain.add(new JLabel("First Name: "),SwingUtils.getConstraints(1, 2, 1, 0,0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					5, 5, 5, 5));
			
			txtFName.setSize(new Dimension(50, 140));
			panelMain.add(txtFName,SwingUtils.getConstraints(1, 3, 1, 0, 0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.HORIZONTAL, 
					5, 0, 5, 10));
			
			panelMain.add(new JLabel("Middle Name: "),SwingUtils.getConstraints(1, 4, 1, 0,0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					5, 5, 5, 5));
			txtMName.setPreferredSize(new Dimension(160,20));
			panelMain.add(txtMName,SwingUtils.getConstraints(1, 5, 1, 0, 0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.HORIZONTAL, 
					5, 0, 5, 10));

			JLabel lblGender = new JLabel("Gender: ");
			panelMain.add(lblGender,SwingUtils.getConstraints(2, 0, 1, 0,0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					5, 5, 5, 5));
			
			cbGender = new JComboBox();
			cbGender.addItem("Female");
			cbGender.addItem("Male");
			cbGender.addItem("Other");
			panelMain.add(cbGender,SwingUtils.getConstraints(2, 1, 1, 0,0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					5, 5, 5, 10));
			
			//DOB
			JLabel lblDOB	= new JLabel("Date of Birth: ");
			panelMain.add(lblDOB,SwingUtils.getConstraints(2, 2, 1, 0,0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					5, 5, 5, 5));
			
			dateDOB = new JAceDate();
			dateDOB.setDate(""); //set no date initially
			panelMain.add(dateDOB,SwingUtils.getConstraints(2, 3, 1, 0,0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.HORIZONTAL, 
					5, 5, 5, 10));
			
			// Designation
			txtDesig = new JTextField();
			panelMain.add(new JLabel("Designation: "), SwingUtils.getConstraints(2, 4, 1, 0,0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					5, 5, 5, 5));
			txtDesig.setPreferredSize(new Dimension(120,20));
			panelMain.add(txtDesig,SwingUtils.getConstraints(2, 5, 1, 0,1, 
					GridBagConstraints.WEST, 
					GridBagConstraints.HORIZONTAL, 
					5, 5, 5, 10));
			
			
			// Address
			tarAddress = new JTextArea(5, 20);
			JScrollPane spAddress = new JScrollPane(tarAddress); 
			panelMain.add( new JLabel("Address: "), SwingUtils.getConstraints(3, 0, 1, 0, 0,
					GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, 5, 5, 5, 5));
			
			panelMain.add(tarAddress, SwingUtils.getConstraints(3, 1, 3, 1, 0.1, 0.1,
						GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, 5, 5, 5, 5));
			
			// Groups
			panelMain.add(new JLabel("Groups: "), SwingUtils.getConstraints(3, 4, 1, 0,0, 
					GridBagConstraints.NORTHWEST, 
					GridBagConstraints.NONE, 10, 5, 0, 5));
			
			txtGroups = new JTextField("No Group");			
			panelMain.add(txtGroups,SwingUtils.getConstraints(4, 4, 2, 0,0, 
					GridBagConstraints.NORTHWEST, 
					GridBagConstraints.BOTH, 5, 5, 5, 10));
			txtGroups.setEditable(false);
			
//			mdlZip = new MHZipTableModel();
//			mdlZip.setList(ACEConnector.getInstance().getPinInfo());
//			tblZip = new JTable(mdlZip);
//			JScrollPane scrPanZipTbl = new JScrollPane(tblZip);
//			tblZip.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
//
////			Vector<Pincode> vPinInfo = ACEConnector.getInstance().getPinInfo();
//			Vector<String> sPin = new Vector<String>();

//			lstZip = new JList();
			
			txtPin = new JTextField();
			JLabel lblZip = new JLabel("Pincode :");
			JTextField txtbuff = new JTextField();

			panelMain.add(lblZip, SwingUtils.getConstraints(4, 0, 1, 0, 0, 
					GridBagConstraints.WEST, GridBagConstraints.NONE,
					10, 10, 5, 5));
			
			txtPin.setPreferredSize(new Dimension(60,20));
			panelMain.add(txtPin, SwingUtils.getConstraints(4, 1, 1, 0, 0, 
					GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
							10, 10, 5, 5));
			
			// Contact info
			txtEmail = new JTextField();
			txtMob = new JTextField();
			txtPhone = new JTextField();
			panelMain.add(new JLabel("Email: "), SwingUtils.getConstraints(4, 2, 1, 0, 0, 
					GridBagConstraints.WEST, GridBagConstraints.NONE,
					10, 10, 5, 5));
			panelMain.add(txtEmail, SwingUtils.getConstraints(4, 3, 1, 0, 0, 
					GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
					10, 10, 5, 5));
			
			panelMain.add(new JLabel("Mobile: "), SwingUtils.getConstraints(5, 0, 1, 0, 0, 
					GridBagConstraints.WEST, GridBagConstraints.NONE,
					10, 10, 5, 5));
			panelMain.add(txtMob, SwingUtils.getConstraints(5, 1, 1, 0, 0, 
					GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
					10, 10, 5, 5));

			panelMain.add(new JLabel("Other: "), SwingUtils.getConstraints(5, 2, 1, 0, 0, 
					GridBagConstraints.WEST, GridBagConstraints.NONE,
					10, 10, 5, 5));
			panelMain.add(txtPhone, SwingUtils.getConstraints(5, 3, 1, 0, 0, 
					GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
					10, 10, 5, 5));
			
			// Change user password
			
			txtRetypePwd = new JTextField();
			txtNewPwd = new JTextField();

			panelMain.add(new JLabel("New Password: "), SwingUtils.getConstraints(6, 0, 1, 0, 0, 
					GridBagConstraints.WEST, GridBagConstraints.NONE,
					10, 10, 5, 5));
			panelMain.add(txtNewPwd, SwingUtils.getConstraints(6, 1, 1, 0, 0, 
					GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
					10, 10, 5, 5));
			
			panelMain.add(new JLabel("Retype : "), SwingUtils.getConstraints(6, 2, 1, 0, 0, 
					GridBagConstraints.WEST, GridBagConstraints.NONE,
					10, 10, 5, 5));
			panelMain.add(txtRetypePwd, SwingUtils.getConstraints(6, 3, 1, 0, 0, 
					GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
					10, 10, 5, 5));
		
			panelSaveCloseButton = new JPanel();
//			panelSaveCloseButton.setPreferredSize(new Dimension(160,50));
			panelSaveCloseButton.setBackground(Color.DARK_GRAY);
			btnSave = new JButton("Save");
			btnSave.addActionListener(this);
			panelSaveCloseButton.setLayout(new FlowLayout());
			panelSaveCloseButton.add(btnSave);
//			panelMain.add(panelSaveCloseButton, SwingUtils.getConstraints(5, 0, 1, 0, 0, 
//					GridBagConstraints.WEST, 
//					GridBagConstraints.HORIZONTAL,10, 10, 0, 10));
			add(panelSaveCloseButton, BorderLayout.SOUTH);

			setValues();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void setValues(){
		
		try{
			vUser = ACEConnector.getInstance().getUserInfo("AceAdmin");
			Vector<UsersandGroupsObj> vUsrNGrp = new Vector<UsersandGroupsObj>();
			UsersandGroupsObj usrNGrpObj;
			Vector<GroupsObj> vAUsrGrps = new Vector<GroupsObj>();
			GroupsObj usrGrpsObj = new GroupsObj();
			String sUsrID = new String();
			String[] usrsGrps;
			
			System.out.println("Number of users is " + vUser.size());
			for (int i = 0; i < vUser.size(); i++){
				User user = new User();
				
				user = vUser.elementAt(0);
				txtUserID.setText(user.getUserID());
				
				txtFName.setText(user.getfName());
				txtMName.setText(user.getmName());
				txtLName.setText(user.getlName());
				cbGender.setSelectedItem(user.getGender());
				tarAddress.setText(user.getAddress());
				txtPin.setText(user.getPincode());
				txtDesig.setText(user.getDesig());
				dateDOB.setDate(user.getDob());
				txtEmail.setText(user.getEmail());
				txtMob.setText(user.getMobile());
				txtPhone.setText(user.getPhone());
				sUsrID = user.getUserID();
//				vUsrNGrp = (ACEConnector.getInstance().getTheUsersGrp(sUsrID));
//				System.out.println( "Number of groups that the user belongs to :" + vUsrNGrp.size());
//				String sUsrGrps = new String();
//				for (int g = 0; g < vUsrNGrp.size(); g++){
//					usrNGrpObj = new UsersandGroupsObj();
//					usrNGrpObj = vUsrNGrp.elementAt(g);
//					sUsrGrps = sUsrGrps + usrNGrpObj.getGrpName().toString();
//					if (g < vUsrNGrp.size()-1)
//						sUsrGrps = sUsrGrps + ", ";
//				}
//				txtGroups.setText(sUsrGrps);
//				System.out.println("Names of the groups :" + sUsrGrps);
				// OR 
				// use the group names stored in the User object
				txtGroups.setText(DB.joinVStrings(user.getGroups()));
				System.out.println("Names of the groups :" + DB.joinVStrings(user.getGroups()));
				
//				Vector vUsrGrps = ACEConnector.getInstance().getUserGrpsAll();
//				System.out.println("Filling up the User groups drop down list with " + vUsrGrps.size() + " items");
//				lstGroups.setItems(vUsrGrps);  		

				pwd = (user.getPassWord());
				System.out.println("Old pwd ;" + pwd);
			}	
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	// update the info of the logged in user
	public void save(){
		boolean isUserInfoSaved;
		boolean grpChanged = false;
		User userObj = new User();
		
		userObj.setAddress(tarAddress.getText());
		userObj.setDesig(txtDesig.getText());
		userObj.setDob(dateDOB.getDate());
		System.out.println("Date of birth: " + dateDOB.getDate());
		
		userObj.setEmail(txtEmail.getText());
		userObj.setfName(txtFName.getText());
		userObj.setlName(txtLName.getText());
		userObj.setmName(txtMName.getText());
		userObj.setGender(cbGender.getSelectedItem().toString());
		userObj.setMobile(txtMob.getText());
		userObj.setPhone(txtPhone.getText());
		userObj.setPincode(txtPin.getText());
		userObj.setGroups(DB.stringToVector(txtGroups.getText()));
	//	userObj.setUserDescription(userDescription)
		userObj.setUserID(txtUserID.getText());
		userObj.setPassWord(pwd);
		System.out.println("Password being saved: " + pwd);
		
		isUserInfoSaved = ACEConnector.getInstance().saveUserInfo(userObj, grpChanged);
		if (isUserInfoSaved){
			System.out.println("User information has been saved");
		}
		else
			System.out.println("Error in saving user information");
			
	}
	
	@Override
	public void setMHPanelObserver(MHPanelObserver obs) {
		// TODO Auto-generated method stub
		
	}


	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getSource() == btnSave){
//			if (txtNewPwd.getText() != null){
			if (txtNewPwd.getText().toString().length() > 0){
				if (txtRetypePwd.getText() != null){
				if (txtNewPwd.getText().equals(txtRetypePwd.getText())){
					System.out.println("Before saving password is :" + pwd + " and saving " + txtNewPwd.getText());
					pwd = txtNewPwd.getText();
					save();
				}	else {
					    JOptionPane.showMessageDialog(null, "Passwords do not match.");
					}
				}
				else {
				    JOptionPane.showMessageDialog(null, "Retype password.");
				}
			}
			else {
				save();
			} 
		} 
		
	}

}
