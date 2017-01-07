package com.act.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.act.client.components.JAceList;
import com.act.client.models.MHOrgnTableModel;
import com.act.client.models.MHZipTableModel;
import com.act.common.Organisation;
import com.act.common.SwingUtils;

public class PanelOrganisation extends MHPanel implements  ActionListener {
	
	JTable tblZip;
	MHZipTableModel mdlZip;
	JPanel panelData, panelAddUpDelBtn, panelSaveCloseButton;
//	JPanel panelDelConfirm;	
//	JPanel panelOrgnSaveCancel;
	JTextField txtNewListItem;
	JTextField  txtAddCity, txtAddPin;
	JTextField txtOrgName, txtOrgID, txtAdd1, txtAdd2, txtAdd3;
//	JList lstOrgServ;
//	JList lstData;
	JOptionPane optConfirmDel;
	JAceList alstOrgServ;
	JList lstZip;
	JTable tblOrgData;
//	MHMainTableModel modelOrg;
	MHOrgnTableModel modelOrg;

//	JScrollPane spMstList ;
//	DefaultListModel lmLstData; 
//	ListSelectionModel listSelModel;
	
	Vector <String> vToSave = new Vector<String>();
	JPanel panelDetails;
	Organisation orgnDetailsObj = new Organisation();

	JButton btnClose;
	JButton btnSave, btnAddList, btnDelList;
	JButton btnUpdtDetails, btnSaveOrgn;
	JButton btnDelConfirm, btnDelCancel;
	
	boolean isUpdtDet = false;
	boolean isItemDel = false;
	boolean isItemSelected = false;
	boolean isItemAdd = false;
	String modeAED = "A";

	
	public PanelOrganisation(){
		initUI();
	}
	
	private void initUI(){
		setLayout(new BorderLayout());
	try{	
		
		panelData = new JPanel();
		add(panelData, BorderLayout.CENTER);
		panelData.setLayout(new GridBagLayout());
		
		panelDetails = new JPanel();
		panelDetails.removeAll();
		
		//Table															// ND added on 12th Sep 16
		modelOrg = new MHOrgnTableModel();
		tblOrgData = new JTable(modelOrg);
		JScrollPane scrollPaneTbl = new JScrollPane(tblOrgData);
		tblOrgData.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		scrollPaneTbl.setBackground(Color.BLUE);
//		panelData.add(scrollPaneTbl, BorderLayout.CENTER);
		panelData.add(scrollPaneTbl, SwingUtils.getConstraints(0, 0, 1, 3, 3, 
				GridBagConstraints.WEST, 
				GridBagConstraints.BOTH, 
				10, 10, 0, 10));
		
		panelAddUpDelBtn = new JPanel();
		panelAddUpDelBtn.setPreferredSize(new Dimension(160,50));
		panelAddUpDelBtn.setBackground(Color.DARK_GRAY);
		btnAddList = new JButton("Add New");
		btnAddList.addActionListener(this);
		btnAddList.setEnabled(true);
		btnUpdtDetails = new JButton("Update Details");
		btnUpdtDetails.addActionListener(this);
		btnDelList = new JButton("Delete Item");
		btnDelList.addActionListener(this);
		panelAddUpDelBtn.setLayout(new FlowLayout());
		panelAddUpDelBtn.add(btnAddList);
		panelAddUpDelBtn.add(btnUpdtDetails);
		panelAddUpDelBtn.add(btnDelList);
		panelData.add(panelAddUpDelBtn, SwingUtils.getConstraints(2, 0, 1, 0, 0, 
				GridBagConstraints.WEST, 
				GridBagConstraints.HORIZONTAL,10, 10, 0, 10));
		
		panelSaveCloseButton = new JPanel();
//		panelSaveCloseButton.setPreferredSize(new Dimension(160,50));
		panelSaveCloseButton.setBackground(Color.DARK_GRAY);
		btnSave = new JButton("Save");
		btnSave.addActionListener(this);
		btnClose = new JButton("Close");
		btnClose.addActionListener(this);
		panelSaveCloseButton.setLayout(new FlowLayout());
		panelSaveCloseButton.add(btnSave);
		panelSaveCloseButton.add(btnClose);
		panelData.add(panelAddUpDelBtn, SwingUtils.getConstraints(3, 0, 1, 0, 0, 
				GridBagConstraints.WEST, 
				GridBagConstraints.HORIZONTAL,10, 10, 0, 10));
		newList();

		} catch(Exception e){
		e.printStackTrace();}
	}
	
	private void newList(){
		
		modelOrg.setList(ACEConnector.getInstance().getOrgnIDName()); 
		Vector vOrganisations = new Vector<Organisation>();
//		Vector vOrganisations = ACEConnector.getInstance().getOrganisationList();

		btnAddList.setVisible(true);
		btnAddList.setEnabled(true);
		
//		btnDelCancel.setVisible(false);
//		btnDelConfirm.setVisible(false);
//		panelDelConfirm.removeAll();
//		panelDelConfirm.repaint();
//		panelDelConfirm.setVisible(false);

		panelAddUpDelBtn.setVisible(true);
		panelSaveCloseButton.setVisible(false);
		panelDetails.removeAll();
		panelDetails.setVisible(false);
		panelData.setVisible(true);
		panelData.repaint();

	}
	public void orgnAllDetails() {
		
		Vector<String> vStrServices = ACEConnector.getInstance().getServiceTypeList();
		System.out.println("Size of Service list is " + vStrServices.size());
		mdlZip = new MHZipTableModel();
		mdlZip.setList(ACEConnector.getInstance().getPinInfo());
		tblZip = new JTable(mdlZip);
		JScrollPane scrPanZipTbl = new JScrollPane(tblZip);
		tblZip.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

//		Vector<Pincode> vPinInfo = ACEConnector.getInstance().getPinInfo();
		Vector<String> sPin = new Vector<String>();

		String selName = new String();
		String sOrgnServices = new String();
		int selOrgn_ID;

		JScrollPane spServList;

		panelData.setVisible(false);
		panelDetails.setVisible(true);

//		isUpdtDet = true;
		txtOrgID = new JTextField();
		txtOrgName = new JTextField("Type in a New Name");	
		JLabel lblName = new JLabel("Name : ");
		txtAdd1 = new JTextField("House or Flat, Bldg");
		JLabel lblAdd1 = new JLabel ("House #, Building :");
		txtAdd2 = new JTextField("Locality and Area");
		JLabel lblAdd2 = new JLabel("Locality, Area :");
		txtAdd3 = new JTextField("State");
		JLabel lblAdd3 = new JLabel("State :");
		txtAddCity = new JTextField("City");
		JLabel lblCity = new JLabel("City :");
		txtAddPin = new JTextField("Zip");
		JLabel lblPin = new JLabel("Pin Code :");
		lstZip = new JList();
		JLabel lblZip = new JLabel("Zip Code :");
		JTextField txtbuff = new JTextField();

		alstOrgServ = new JAceList(); 
		JLabel lblOrgServ = new JLabel("Services");
		alstOrgServ.setForeground(Color.DARK_GRAY);
		if(modeAED == "E"){
//	        selName = lmLstData.getElementAt(maxIndex).toString();
//	        orgnDetailsObj = (Organisation)ACEConnector.getInstance().getOrgnDetails(selName);   
			selName = orgnDetailsObj.getOrgnName().toString();
	        txtOrgID.setText(Integer.toString(orgnDetailsObj.getOrgnId()));
	        txtOrgID.setEditable(false);
	        txtOrgName.setText(selName);
	        System.out.println("Organisation name is " + orgnDetailsObj.getOrgnName());
	        txtAdd1.setText(orgnDetailsObj.getAdd1());
	        txtAdd2.setText(orgnDetailsObj.getAdd2());
	        txtAdd3.setText(orgnDetailsObj.getAdd3());
	        txtAddCity.setText(orgnDetailsObj.getAddCity());
	        txtAddPin.setText(orgnDetailsObj.getAddPin());
	        sOrgnServices = " ";
	        if (orgnDetailsObj.getServiceTypes() != null)
	        	sOrgnServices =  orgnDetailsObj.getServiceTypes().toString();
		}
		
		txtAdd1.setSize(100,400);
		panelDetails.setLayout(new GridBagLayout());
		panelDetails.add(lblName, SwingUtils.getConstraints(0, 0, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				10, 10, 5, 5));
		panelDetails.add(txtOrgName, SwingUtils.getConstraints(0, 1, 3, 0, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
						10, 10, 5, 5));
		panelDetails.add(txtOrgID,  SwingUtils.getConstraints(0, 4, 3, 0, 0, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL,
						10, 10, 5, 5));
		panelDetails.add(lblAdd1, SwingUtils.getConstraints(1, 0, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				10, 10, 5, 5));
		panelDetails.add(txtAdd1, SwingUtils.getConstraints(1, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				10, 10, 5, 5));
		panelDetails.add(lblAdd2, SwingUtils.getConstraints(2, 0, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				10, 10, 5, 5));
		panelDetails.add(txtAdd2, SwingUtils.getConstraints(2, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				10, 10, 5, 5));
		panelDetails.add(lblAdd3, SwingUtils.getConstraints(3, 0, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				10, 10, 5, 5));
		panelDetails.add(txtAdd3, SwingUtils.getConstraints(3, 1, 1, 0.1, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				10, 10, 5, 5));
		panelDetails.add(lblCity, SwingUtils.getConstraints(4, 0, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				10, 10, 5, 5));
		panelDetails.add(txtAddCity, SwingUtils.getConstraints(4, 1, 1, 0.1, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
						10, 10, 5, 5));
		panelDetails.add(lblPin, SwingUtils.getConstraints(4, 2, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				10, 10, 5, 5));
		txtAddPin.setPreferredSize(new Dimension(60,20));
		panelDetails.add(txtAddPin, SwingUtils.getConstraints(4, 3, 1, 0.1, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
						10, 10, 5, 5));
//		panelDetails.add(txtbuff, SwingUtils.getConstraints(5, 2, 3, 0, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
//				10, 10, 5, 5));
		panelDetails.add(lblOrgServ, SwingUtils.getConstraints(6, 0, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				10, 10, 5, 5));
		populateALst(alstOrgServ, vStrServices);

		alstOrgServ.setText(sOrgnServices);
		panelDetails.add(alstOrgServ, SwingUtils.getConstraints(6, 1, 2,  2, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				10, 10, 5, 5));

		panelSaveCloseButton.setVisible(true);
//		panelDetails.add(panelSaveCloseButton, SwingUtils.getConstraints(7, 2, 3, 0, 0.2, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
//				10, 10, 5, 5));
		
		add(panelDetails, BorderLayout.WEST);
		add(panelSaveCloseButton, BorderLayout.SOUTH);
	}
	
	private void populateLst (JList lst, Vector vItems){
		
		int a = 1;
		if (lst == null || vItems == null)
			return;
		System.out.println("Size of the Jlist is :"+ lst.getComponentCount() + " Vector" + vItems.size());		// ND edited 06th Sep 16
//		System.out.println("The values in the list b4 Jlist populate: " + vItems.elementAt(a) + ", "+ vItems.elementAt(a+1));
		lst.setListData(vItems);
	}
	
	private void populateALst (JAceList alst, Vector vItems){
		
		int a = 0;
		if (alst == null || vItems == null)
			return;
		System.out.println("Size of the Jlist is :"+ alst.getComponentCount() + " Vector" + vItems.size());		// ND edited 06th Sep 16
		alst.setItems(vItems);
	}
	public void saveOrgnDet(){
		boolean isOrgnSaved;
		System.out.println(txtAdd1.getText() + " *********** ");
		orgnDetailsObj.setOrgnName(txtOrgName.getText());
		orgnDetailsObj.setAdd1(noNull(txtAdd1.getText()));
		orgnDetailsObj.setAdd2(noNull(txtAdd2.getText()));
		orgnDetailsObj.setAdd3(noNull(txtAdd3.getText()));
		orgnDetailsObj.setAddCity(noNull(txtAddCity.getText()));
		orgnDetailsObj.setAddPin(noNull(txtAddPin.getText()));
		orgnDetailsObj.setServiceTypes(alstOrgServ.getSelectedItems());
		System.out.println("Edit / Add / Del mode :" + modeAED);
		if(modeAED == "A"){
		isOrgnSaved = ACEConnector.getInstance().saveOrgn(orgnDetailsObj);
		if(isOrgnSaved)
			System.out.println("Organisation details saved");
		else
			System.out.println("Error in saving Organisation details");
		} 
		else if (modeAED == "E" ) {
			orgnDetailsObj.setOrgnId(Integer.parseInt(txtOrgID.getText()));
			isOrgnSaved = ACEConnector.getInstance().updateOrgn(orgnDetailsObj);
			if(isOrgnSaved)
				System.out.println("Organisation details updated(");
			else
				System.out.println("Error in updatiing Organisation details");
		}
	}
	
	public void editDetails() {
		
        btnAddList.setVisible(false);
        btnUpdtDetails.setVisible(true);
        btnDelList.setVisible(true);
//        btnAddList.setVisible(true);
        modeAED = "E";
        
        orgnAllDetails();
	}
	

	public void delOrgn(){
		Vector <Integer> vOrgnIDs = new Vector<Integer>();
		
//		OrgnIDNameObj orgnIDN = modelOrg.getRowObject(tblOrgData.getSelectedRow()); 
		if (tblOrgData.getSelectedRowCount() > 0){
			Vector vOrgID = new Vector();
			int[] selRows = tblOrgData.getSelectedRows();
			
			for (int i = 0; i < selRows.length; i++ ){
				Organisation orgnIDN = modelOrg.getRowObject(selRows[i]); 
				System.out.println("Organisation being deleted: " + orgnIDN.getOrgnName());
				vOrgnIDs.add(orgnIDN.getOrgnId());
			}
			String sResult = ACEConnector.getInstance().delOrganisations(vOrgnIDs);
			System.out.println("Delete Organisations result:" + sResult);
		}
	}

	public String noNull(String val){
		if (val == null)
			return "";
		return val.trim().toString();
	}

//	public void valueChanged(ListSelectionEvent arg0) {
//		// TODO Auto-generated method stub
//        ListSelectionModel lsmOrgn = (ListSelectionModel)arg0.getSource();
//        String selName = new String();
//        
//		int firstIndex = arg0.getFirstIndex();
//        int lastIndex = arg0.getLastIndex();
//        System.out.println("e.getLastIndex = " + lastIndex);
//        
//        
//        if (lsmOrgn.isSelectionEmpty()) {
//        	System.out.println(" <none> ");
//        	btnAddList.setText("Add New");
//        	btnAddList.setEnabled(true);
////        	btnDelList.setEnabled(false);
//         //   output.append(" <none>");
//        } else {
//            // Find out which indexes are selected.
////            int minIndex = lsm.getMinSelectionIndex();
////            int maxIndex = lsm.getMaxSelectionIndex();
////    		btnUpdateList.setEnabled(true);
//    		btnDelList.setEnabled(true);
// //   		btnAddList.setText("Update");
//    		btnUpdtDetails.setEnabled(true);
//    		btnAddList.setEnabled(false);
//        	
//            int minIndex = listSelModel.getMinSelectionIndex();
//            int maxIndex = listSelModel.getMaxSelectionIndex();
//            isItemSelected = true;
// //           if (minIndex == maxIndex)
// //           	editItem(minIndex);
//
////            for (int i = minIndex; i <= maxIndex; i++) {
////                if (lsm.isSelectedIndex(i)) {
////                    System.out.println(" Index number of selected item " + i);
////                }
////            }
////            selName = lmLstData.getElementAt(lastIndex).toString();
////            System.out.println("The selected item is :" + selName);
////            txtNewListItem.setText(selName.toString());
//        }
//		
//	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(btnAddList)){
			modeAED = "A";
			orgnAllDetails();
			System.out.println("modeAED set to ---- A ");
		}
		if(e.getSource().equals(btnDelList)){
			int selOrg = tblOrgData.getSelectedRowCount();
			String sTtl = new String("Delete Organisation");
			if (selOrg > 1) 
				sTtl = "Delete Organisations";
			if (selOrg > 0){
				int r = optConfirmDel.showConfirmDialog(null, "Sure?", sTtl, 2);
				if (r == JOptionPane.YES_OPTION){
					modeAED = "D";
					System.out.println("modeAED set to ---- D ");
					delOrgn();
					newList();
				}
			}
		}
		if(e.getSource().equals(btnDelConfirm)){
			delOrgn();
			newList();
		}
		if (e.getSource().equals(btnDelCancel)){
			newList();
		}
		if(e.getSource().equals(btnUpdtDetails)) {
//			OrgnIDNameObj orgnIDN = modelOrg.getRowObject(tblOrgData.getSelectedRow()); 
			if (tblOrgData.getSelectedRowCount() > 0){
				modeAED = "E";
				System.out.println("modeAED set to ---- E ");
				Organisation orgnIDN = modelOrg.getRowObject(tblOrgData.getSelectedRow()); 
				orgnDetailsObj = ACEConnector.getInstance().getOrgnDetails(orgnIDN.getOrgnId());
				editDetails();
			} 
		}
		if(e.getSource().equals(btnClose)){
			modeAED = " ";
			newList();
		}
		if (e.getSource().equals(btnSave)){
			saveOrgnDet();
			newList();
		}
	}

	@Override
	public void setMHPanelObserver(MHPanelObserver obs) {
		// TODO Auto-generated method stub
		
	}

}
