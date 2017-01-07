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
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.PanelUI;


import com.act.client.components.JAceList;
import com.act.client.models.MHMainTableModel;
import com.act.client.models.MHZipTableModel;
import com.act.common.MastListObj;
import com.act.common.Organisation;
import com.act.common.Pincode;
import com.act.common.SwingUtils;

public class PanelGeneral extends MHPanel implements  ActionListener, ListSelectionListener{
	MHPanelObserver panelObserver;
	JButton btnSave, btnAddList, btnDelList;
	
	JRadioButton rbReligion, rbAddiction, rbCopingSkill;					// ND added on 30th Jul 16
	JRadioButton rbRelativeTypes, rbServiceTypes, rbGrading;				// ND added on 30th Jul 16
	JRadioButton rbEduNonFormal, rbLanguage, rbHobbies;						//ND edited 30th Jun 16
	JRadioButton rbCounslTherapy;												// ND added on 26th Sep 16

	JPanel panelLeft, panelData, panelAddDelBtn, panelSaveButton;							// ND edited 08th Jul 16
	JPanel panelBuffer;
	JLabel labelMasterTables;
	JList lstData;
	JTextField txtNewListItem;
	JTextField  txtAddCity, txtAddPin;
	JTextField txtOrgName, txtAdd1, txtAdd2, txtAdd3;
	JList lstOrgServ;
	JAceList alstZip;
	DefaultListModel lmLstData; 
	ListSelectionModel listSelModel;
	JTable tblZip;
	MHZipTableModel mdlZip;
	
	String selButt = new String();
	boolean isUpdtDet = false;
	boolean isItemDel = false;
	boolean isItemEdit = false;
	boolean isItemAdd = false;
	String modeAED = "A";
	Vector <String> vToSave = new Vector<String>();
//	JPanel panelDetails;
	JScrollPane spMstList ;
//	Organisation orgnDetailsObj = new Organisation();

	public PanelGeneral(){
		initUI();
	}
	
	private void initUI(){
		setLayout(new BorderLayout());
	try{	
		
		panelLeft = new JPanel();
		add(panelLeft, BorderLayout.WEST);
		panelLeft.setLayout(new GridBagLayout());
		
		labelMasterTables = new JLabel("Choose Master Data");
		panelLeft.add(labelMasterTables, SwingUtils.getConstraints(0, 0, 1, 0, 0, 
				GridBagConstraints.WEST, 
				GridBagConstraints.HORIZONTAL, 
				10, 10, 0, 10));

		ButtonGroup btnGroup = new ButtonGroup();
		rbAddiction = new JRadioButton("Addictions");
		btnGroup.add(rbAddiction);
		rbAddiction.addActionListener(this);													// ND added 30th Jul 16
		panelLeft.add(rbAddiction, SwingUtils.getConstraints(1, 0, 1, 0, 0, 
				GridBagConstraints.WEST, 
				GridBagConstraints.HORIZONTAL, 
				10, 10, 0, 10));

		rbCopingSkill = new JRadioButton("Coping Skills");
		btnGroup.add(rbCopingSkill);
		rbCopingSkill.addActionListener(this);													// ND added 30th Jul 16
		panelLeft.add(rbCopingSkill, SwingUtils.getConstraints(2, 0, 1, 0, 0, 
				GridBagConstraints.WEST, 
				GridBagConstraints.HORIZONTAL, 
				10, 10, 0, 10));
		
		rbHobbies = new JRadioButton("Hobbies / Recreation");
		btnGroup.add(rbHobbies);
		rbHobbies.addActionListener(this);
		panelLeft.add(rbHobbies, SwingUtils.getConstraints(3, 0, 1, 0, 0, 
				GridBagConstraints.WEST, 
				GridBagConstraints.HORIZONTAL, 
				10, 10, 0, 10));
		
		rbLanguage = new JRadioButton("Languages");
		btnGroup.add(rbLanguage);
		rbLanguage.addActionListener(this);													// ND added 08th Jul 16
		panelLeft.add(rbLanguage, SwingUtils.getConstraints(4, 0, 1, 0, 0, 
				GridBagConstraints.WEST, 
				GridBagConstraints.HORIZONTAL, 
				10, 10, 0, 10));

		rbEduNonFormal= new JRadioButton("Non formal Education");								// ND added 30th Jun 16
		btnGroup.add(rbEduNonFormal);
		rbEduNonFormal.addActionListener(this);
		panelLeft.add(rbEduNonFormal, SwingUtils.getConstraints(5, 0, 1, 0, 0, 
				GridBagConstraints.WEST, 
				GridBagConstraints.HORIZONTAL, 
				10, 10, 0, 10));
		
		rbReligion = new JRadioButton("Religions");
		btnGroup.add(rbReligion);
		rbReligion.addActionListener(this);													// ND added 30th Jul 16
		panelLeft.add(rbReligion, SwingUtils.getConstraints(6, 0, 1, 0, 0, 
				GridBagConstraints.WEST, 
				GridBagConstraints.HORIZONTAL, 
				10, 10, 0, 10));

		rbRelativeTypes = new JRadioButton("Different Relationships");
		btnGroup.add(rbRelativeTypes);
		rbRelativeTypes.addActionListener(this);													// ND added 30th Jul 16
		panelLeft.add(rbRelativeTypes, SwingUtils.getConstraints(7, 0, 1, 0, 0, 
				GridBagConstraints.WEST, 
				GridBagConstraints.HORIZONTAL, 
				10, 10, 0, 10));
		
		rbServiceTypes = new JRadioButton("Various Services");
		btnGroup.add(rbServiceTypes);
		rbServiceTypes.addActionListener(this);													// ND added on 05th Aug 16
		panelLeft.add(rbServiceTypes, SwingUtils.getConstraints(8, 0, 1, 0, 0, 
				GridBagConstraints.WEST, 
				GridBagConstraints.HORIZONTAL, 
				10, 10, 0, 10));
		
		rbCounslTherapy = new JRadioButton("Counseling Therapy");
		btnGroup.add(rbCounslTherapy);
		rbCounslTherapy.addActionListener(this);													// ND added on 26th Sep 16
		panelLeft.add(rbCounslTherapy, SwingUtils.getConstraints(9, 0, 1, 0, 0, 
				GridBagConstraints.WEST, 
				GridBagConstraints.HORIZONTAL, 
				10, 10, 0, 10));

		// rbGrading
		
		panelData = new JPanel();
		add(panelData, BorderLayout.CENTER); 
		panelData.setLayout(new GridBagLayout());
		
//		panelBuffer = new JPanel();
//		panelBuffer.setPreferredSize(new Dimension(160, 70));
//		add(panelBuffer, BorderLayout.SOUTH);
	
		// start ND added 08 Jul 16
		
		lmLstData = new DefaultListModel();
		lstData = new JList(lmLstData);								// referencing the JList to the List Model lmLstData
		listSelModel = lstData.getSelectionModel();
		listSelModel.addListSelectionListener(this);
		
		lstData.setPreferredSize(new Dimension(200, 510));
		spMstList = new JScrollPane(lstData);
//		spMstList.setPreferredSize(new Dimension(210,510));

		panelData.add(spMstList, SwingUtils.getConstraints(0, 0, 1, 3, 3, 
				GridBagConstraints.WEST, 
				GridBagConstraints.BOTH, 
				10, 10, 0, 10));
		

		//add a text field to enter a new field
		//add ADD / UPDATE, DELETE buttons
		// add SAVE button

		txtNewListItem = new JTextField("Type in a New Value");		
//		txtNewListItem.setBorder(arg0)
//		txtNewListItem.setForeground(new Color(0,0,0));
		txtNewListItem.setPreferredSize(new Dimension(40,30));
		panelData.add(txtNewListItem, SwingUtils.getConstraints(1, 0, 1, 0, 0, 
				GridBagConstraints.WEST, 
				GridBagConstraints.HORIZONTAL, 
				10, 10, 0, 10));
		
		panelAddDelBtn = new JPanel();
		panelAddDelBtn.setPreferredSize(new Dimension(160,35));
		panelAddDelBtn.setBackground(Color.DARK_GRAY);
		btnAddList = new JButton("Add New");
		btnAddList.addActionListener(this);
//		panelDataButtons.add(btnAddList, SwingUtils.getConstraints(1, 0, 1, 0, 0, 
//				GridBagConstraints.WEST, 
//				GridBagConstraints.HORIZONTAL, 10, 10, 0, 10));
		btnDelList = new JButton("Delete Item");
		btnDelList.addActionListener(this);
		panelAddDelBtn.setLayout(new FlowLayout());
		panelAddDelBtn.add(btnAddList);
		panelAddDelBtn.add(btnDelList);
		panelData.add(panelAddDelBtn, SwingUtils.getConstraints(2, 0, 1, 0, 0, 
				GridBagConstraints.WEST, 
				GridBagConstraints.HORIZONTAL,10, 10, 0, 10));

		panelSaveButton = new JPanel();
		panelSaveButton.setPreferredSize(new Dimension(160,35));
		panelSaveButton.setBackground(Color.DARK_GRAY);
		btnSave = new JButton("Save List");
		btnSave.addActionListener(this);
		panelSaveButton.setLayout(new FlowLayout());
		panelSaveButton.add(btnSave);
		panelData.add(panelSaveButton, SwingUtils.getConstraints(3, 0, 1, 0, 0, 
				GridBagConstraints.WEST, 
				GridBagConstraints.HORIZONTAL, 2, 10, 0, 10));
		btnDelList.setEnabled(false);
	} catch(Exception e){
		e.printStackTrace();
	}
		// end ND added 08th Jul 16
	}
	
//	private void populateLst (JList lst, Vector vItems){
//		
//		int a = 1;
//		if (lst == null || vItems == null)
//			return;
//		System.out.println("The values in the list b4 Jlist populate: " + vItems.elementAt(a) + ", "+ vItems.elementAt(a+1));
//		lst.setListData(vItems);
//	}
	
	public String noNull(String val){
		if (val == null)
			return "";
		return val.trim();
	}

	// ND added 30th Jun 16
	public void trDataToList(String dataMast){
	try {
//		MastListObj dataObj = new MastListObj();
		lmLstData.clear();
		if (dataMast == "Addictions"){
			Vector vAddictions = ACEConnector.getInstance().getAddictionList();
			setToList(vAddictions);
//			for (int i = 0; i < vAddictions.size(); i++)
//				lmLstData.addElement(vAddictions.elementAt(i));
		}
		else if (dataMast == "CopingSkills"){
			Vector vCopingSkills = ACEConnector.getInstance().getCopingSkillList();
			setToList(vCopingSkills);
//			for (int i = 0; i < vCopingSkills.size(); i++)
//				lmLstData.addElement(vCopingSkills.elementAt(i));
		}
		else if (dataMast == "Hobbies"){
			Vector vHobbies = ACEConnector.getInstance().getRecreationList();
			setToList(vHobbies);
//			for (int i = 0; i < vHobbies.size(); i++)
//				lmLstData.addElement(vHobbies.elementAt(i));
		}
		else if (dataMast == "Languages"){
			Vector vLanguage = ACEConnector.getInstance().getLanguageList();
//			lstData.setListData(vLanguage);
			setToList(vLanguage);
//			for (int i=0; i<vLanguage.size(); i++)
//				lmLstData.addElement(vLanguage.elementAt(i));
		} 
		else if (dataMast == "EduNonForm"){
			Vector<String> vStrNFEdu = ACEConnector.getInstance().getNonFormalEducationList();
			//Vector<String> vStrNFEdu = popAllVect("Non Formal Edu", 7);
//			lstData.setListData(vStrNFEdu);
			lmLstData.clear();
			setToList(vStrNFEdu);
//			for (int i= 0; i < vStrNFEdu.size(); i++)
//				lmLstData.addElement(vStrNFEdu.elementAt(i));
		}
		if (dataMast == "Religions"){
			Vector vReligion = ACEConnector.getInstance().getReligionList();
			setToList(vReligion);
//			for (int i = 0; i < vReligion.size(); i++)
//				lmLstData.addElement(vReligion.elementAt(i));
		}
		else if (dataMast == "Relationships"){
			Vector vRelationships = ACEConnector.getInstance().getRelationshipList();
			setToList(vRelationships);
//			for (int i = 0; i < vRelationships.size(); i++)
//				lmLstData.addElement(vRelationships.elementAt(i));
		}
		if (dataMast == "Services"){
			Vector vServices = ACEConnector.getInstance().getServiceTypeList();
			setToList(vServices);
//			for (int i = 0; i < vServices.size(); i++)
//				lmLstData.addElement(vServices.elementAt(i));
		}
		if (dataMast == "CounslTherapy"){															// ND added on 26th Sep 16
			Vector vCounslTherapy = ACEConnector.getInstance().getCounselingTherapy();
			setToList(vCounslTherapy);
			for (int i = 0; i < vCounslTherapy.size(); i++)
				System.out.println("Counselling Methods: " + i + " " + vCounslTherapy.elementAt(i) );
		}
		else if (dataMast == "Zeroto"){
	//		Vector vGrading = ACEConnector.getInstance().getRecreationList();
//			for (int i = 0; i < vGrading.size(); i++)
//				lmLstData.addElement(vGrading.elementAt(i));
		}
//		else if (dataMast == "Organisations"){
//		}
//		else if (dataMast == "CareHomes"){
//		}
	} catch (Exception e){
		e.printStackTrace();
	}
	
	}
	
	public void setToList (Vector vToTrf) {
		
		try{
		
		for (int i = 0; i < vToTrf.size(); i++)
			lmLstData.addElement(vToTrf.elementAt(i));
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void newList(){
		panelData.setEnabled(true);
		panelData.setVisible(true);

		spMstList.setVisible(true);
		spMstList.setEnabled(true);
		spMstList.getVerticalScrollBar().setValue(0);
		txtNewListItem.setVisible(true);
		txtNewListItem.setText("Enter a New Value");
		panelAddDelBtn.setEnabled(true);
		panelAddDelBtn.setVisible(true);

		btnAddList.setText("Add New");
		btnAddList.setVisible(true);
		modeAED = "A";
//		txtNewListItem.setText("");
		isItemEdit = false;
		isUpdtDet = false;
		
		btnSave.setVisible(true);
//		btnSaveOrgn.setVisible(false);
	}
	
	// To transfer the selected value from the list to the text box
	public void editItem(int selIndex){
		
        if (listSelModel.isSelectionEmpty()) {
        	System.out.println(" <none> ");
         //   output.append(" <none>");
        } else {
        
		String selName = new String();
        int minIndex = listSelModel.getMinSelectionIndex();
        int maxIndex = listSelModel.getMaxSelectionIndex();
        
        for (int i = minIndex; i <= maxIndex; i++) {
            if (listSelModel.isSelectedIndex(i)) {
                System.out.println(" Index number of selected item " + i);
            }
        }
        selName = lmLstData.getElementAt(maxIndex).toString();
        System.out.println("The selected item is :" + selName);
        txtNewListItem.setText(selName.toString());
 //       lmLstData.remove(selIndex);
 //       listSelModel.clearSelection();
        }
        btnAddList.setText("Update");
        btnAddList.setVisible(true);
        modeAED = "E";
	}
	
	// Transfer the value from the text box to the List at selected entry
	public void listEdit(){
		// Save the list to the database
		vToSave = new Vector();
        int minIndex = listSelModel.getMinSelectionIndex();
		int sl = lmLstData.size();													
		
		String sNewVal = txtNewListItem.getText().toString();
		
		System.out.println("***************  " + minIndex + "   " + sNewVal);
		if (sNewVal.trim().length()>0)
			lmLstData.set(minIndex, sNewVal);
//		  lmLstData.add(minIndex, sNewVal);
	}
	
	private void save(){
		vToSave = new Vector();
		for (int i = 0; i < lmLstData.getSize(); i++) {
			vToSave.add(lmLstData.getElementAt(i).toString());
		}
		System.out.println("Size of list after clicking Update " + lmLstData.getSize() + 
				" Transfd to List to save is " + vToSave.size() + " " + selButt.toString());

		if (selButt != null){
			if (selButt.equals("rbAddiction")) {
				if (ACEConnector.getInstance().saveAddiction(vToSave)){
					System.out.println("Addiction table updated");
					// vToSave.removeAllElements();
				}
				else System.out.println("Error in saving the updated addiction list");
			}
			else if (selButt.equals("rbCopingSkill")) {
				if (ACEConnector.getInstance().saveCopingSkill(vToSave)){
					System.out.println("Coping Skill table updated");
					// vToSave.removeAllElements();
				}
				else System.out.println("Error in saving the updated coping skill list");
			}
			else if (selButt.equals("rbHobby")){
				if (ACEConnector.getInstance().saveHobby(vToSave)){
					System.out.println("Recreation/ Hobby table updated");
				}
				else System.out.println("Error in saving the updated hobbies list");
			}
			else if (selButt.equals("rbLanguage")) {
				if (ACEConnector.getInstance().saveLanguage(vToSave)){
					System.out.println("Language table updated");
					// vToSave.removeAllElements();
				}
				else System.out.println("Error in saving the updated language list");
			}

			else if (selButt.equals("rbEduNonFormal")){
				if (ACEConnector.getInstance().saveNFEdu(vToSave)){
					System.out.println("Non Formal Education table updated");
				}
				else System.out.println("Error in saving the updated non formal education list");
			}
			else if (selButt.equals("rbReligion")) {
				if (ACEConnector.getInstance().saveReligion(vToSave)){
					System.out.println("Religion table updated");
					// vToSave.removeAllElements();
				}
				else System.out.println("Error in saving the updated religion list");
			}
			else if (selButt.equals("rbRelativeTypes")) {
				if (ACEConnector.getInstance().saveRelationship(vToSave)){
					System.out.println("Relationship table updated");
					// vToSave.removeAllElements();
				}
				else System.out.println("Error in saving the updated relationship list");
			}
			else if (selButt.equals("rbServiceTypes")) {
				if (ACEConnector.getInstance().saveServiceType(vToSave)){
					System.out.println("Service Type table updated");
				}
			}
			else if (selButt.equals("rbCounslTherapy")){									// ND added on 26th Sep 16
				if (ACEConnector.getInstance().saveCounselMeth(vToSave)){
					System.out.println("Couneling Methods table updated");
				}
			}
			else if (selButt.equals("rbCareHome")) {
				if (ACEConnector.getInstance().saveCareHome(vToSave)){
					System.out.println("Care Home table updated");
				}
			}
		}
		vToSave = null;
	}
	
	public void delItem(){
		
		vToSave = new Vector();
        int maxIndex = listSelModel.getMaxSelectionIndex();
		int sl = lmLstData.size();	
		
		lmLstData.remove(maxIndex);
		isItemDel = true;
		modeAED = "D";
	}

	Boolean isDataValid(){
		System.out.println(txtOrgName.getText().toString().trim().length() + " -------------");
		if(txtOrgName.getText().toString().trim().isEmpty()){
//			System.out.println("Valid Organisation Name"); 
//		else {
			System.out.println("Please enter an Organisation Name");
			return (false);
		}
		return (true);
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// ND added 30th Jun 2016
//		txtNewListItem.setText("");
		if (e.getSource().equals(rbAddiction)){
			System.out.println("Action Listener Transfer addictions to List");
			trDataToList("Addictions");
			selButt = "rbAddiction";
			newList();
		}
		if (e.getSource().equals(rbCopingSkill)){
			System.out.println("Action Listener Transfer coping skills to List");
			trDataToList("CopingSkills");
			selButt = "rbCopingSkill";
			newList();
		}
		if (e.getSource().equals(rbHobbies)){
			System.out.println("Action Listenor Transfer hobbies to List");
			trDataToList("Hobbies");
			selButt = "rbHobby";
			newList();
		}
		if (e.getSource().equals(rbLanguage)) {
			System.out.println("Action Listenor Transfer languages to List");
			trDataToList("Languages");
			selButt = "rbLanguage";
			newList();
		}
		if (e.getSource().equals(rbEduNonFormal)) {
			System.out.println("Action Listenor Transfer Non Formal Edu to List");
			trDataToList("EduNonForm");
			selButt = "rbEduNonFormal";
			newList();
		}
		if (e.getSource().equals(rbReligion)){
			System.out.println("Action Listener Transfer religions to List");
			trDataToList("Religions");
			selButt = "rbReligion";
			newList();
		}
		if (e.getSource().equals(rbRelativeTypes)){
			System.out.println("Action Listener Transfer relative types to List");
			trDataToList("Relationships");
			selButt = "rbRelativeTypes";
			newList();
		}
		if (e.getSource().equals(rbServiceTypes)){
			System.out.println("Action Listener Transfer service types to List");
			trDataToList("Services");
			selButt = "rbServiceTypes";
			newList();
		}
		if (e.getSource().equals(rbCounslTherapy)){
			System.out.println("Action Listener Transfer counseling methods to List");
			trDataToList("CounslTherapy");
			selButt = "rbCounslTherapy";
			newList();
		}

		if (e.getSource().equals(rbGrading)){
			System.out.println("Action Listener Transfer gradings to List");
			trDataToList("Zeroto");
			selButt = "rbGrading";
			newList();
		}

		if (e.getSource().equals(btnAddList)){
			if (modeAED == "A") {
				System.out.println("New value: " + txtNewListItem.getText());
				if (txtNewListItem.getText().toString() != null)
					if(txtNewListItem.getText().toString().trim().length() > 0)
						lmLstData.addElement(txtNewListItem.getText());
				lstData.repaint();
			} else if (modeAED == "E") {
				listEdit();
				txtNewListItem.setText("Enter a New Value");
				btnAddList.setText("Add New");
				modeAED = "A";
			}
//			btnUpdateList.setVisible(true);
		}
		
		if (e.getSource().equals(btnSave)){
				save();
		}
		
		if (e.getSource().equals(btnDelList)){
			delItem();
		}
	}

	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
        ListSelectionModel lsmOrgn = (ListSelectionModel)e.getSource();
        String selName = new String();
        
		int firstIndex = e.getFirstIndex();
        int lastIndex = e.getLastIndex();
        System.out.println("e.getLastIndex = " + lastIndex);
        
        
        if (lsmOrgn.isSelectionEmpty()) {
        	System.out.println(" <none> ");
        	btnAddList.setText("Add New");
        	btnAddList.setEnabled(true);
//        	if (isItemDel = false)
//        		btnUpdateList.setEnabled(false);
//        	else
//        		btnUpdateList.setEnabled(true);
        	btnDelList.setEnabled(false);
         //   output.append(" <none>");
        } else {
            // Find out which indexes are selected.
//            int minIndex = lsm.getMinSelectionIndex();
//            int maxIndex = lsm.getMaxSelectionIndex();
//    		btnUpdateList.setEnabled(true);
    		btnDelList.setEnabled(true);
    		btnAddList.setText("Update");
        	
            int minIndex = listSelModel.getMinSelectionIndex();
            int maxIndex = listSelModel.getMaxSelectionIndex();
            isItemEdit = true;
            if (minIndex == maxIndex)
            	editItem(minIndex);

//            for (int i = minIndex; i <= maxIndex; i++) {
//                if (lsm.isSelectedIndex(i)) {
//                    System.out.println(" Index number of selected item " + i);
//                }
//            }
//            selName = lmLstData.getElementAt(lastIndex).toString();
//            System.out.println("The selected item is :" + selName);
//            txtNewListItem.setText(selName.toString());
        }
		
	}

	@Override
	public void setMHPanelObserver(MHPanelObserver obs) {
		panelObserver = obs;
		
	}
	
}


