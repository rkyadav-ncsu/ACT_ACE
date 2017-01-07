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

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.act.client.models.MHMainTableModel;
import com.act.client.models.MHSessionsTableModel;
import com.act.client.models.MHSymptChkListModel;
import com.act.common.ACEDefines;
import com.act.common.Counsellee;
import com.act.common.CounsellingSessionObj;
import com.act.common.MHSymptChkListObj;
import com.act.common.SwingUtils;
import com.act.common.TSC40Obj;
import com.act.common.TSC54Obj;

public class CounselleeMain extends MHPanel implements ActionListener{
	
	//Panels
	JPanel panelTop, panelCenter, panelBottom,
			panelTopLeft, panelTopRight, panelCenterButtons;
	
	//Sympt Checklists
	JPanel panelSymptTop, panelSymptCenter, panelSymptBottom;
	JTable tblSymptCheckLists;
	MHSymptChkListModel modelSymptChkList;
	JComboBox<String> cbSymptChkLists;
	JButton btnAddSymptChkList, btnEditSymptChkList, btnDelSymptChkList,
				btnSaveSymptChkList;
	
	JTable tblSessions;
	MHSessionsTableModel modelSessions;

	JButton btnAddSession, btnEditSession, btnDelSession;
	JPanel panelSessionButtons, panelSession;
	
	//Counsellee basic details
	JLabel lblName;
	JLabel lblCaseNumber;
	JLabel lblAge;
	JLabel lblParentOrg;
	JLabel lblCounsellorName;
	private Counsellee counsellee;
	MHPanelObserver panelObserver;
	JButton btnClose;
	
	//Trauma symtom check list
	public CounselleeMain(Counsellee counsellee){
		this.counsellee = counsellee;
		setSize(new Dimension(1000,850));
		initUI();
	}
	
	private void initUI(){
		setLayout(new BorderLayout());
		
		panelTop = new JPanel();
		panelTop.setLayout(new GridBagLayout());
		add(panelTop, BorderLayout.NORTH);
		
		//Top Left Panel
		panelTopLeft = new JPanel();
		panelTopLeft.setBorder(new TitledBorder("Counsellee details"));
		panelTopLeft.setLayout(new GridBagLayout());
		panelTop.add(panelTopLeft, SwingUtils.getConstraints(0, 0, 1, 1, 1, 
				GridBagConstraints.WEST, 
				GridBagConstraints.BOTH, 
				0, 0, 0, 0));

		//Name
		lblName = new JLabel("Name: "+ counsellee.getName().getFormattedName());
		panelTopLeft.add(lblName, SwingUtils.getConstraints(0, 0, 1, 0, 0, 
				GridBagConstraints.WEST, 
				GridBagConstraints.NONE, 
				10, 10, 5, 5));

		//Case Number
		lblCaseNumber = new JLabel("Case Number: " +counsellee.getCaseNumber());
		panelTopLeft.add(lblCaseNumber, SwingUtils.getConstraints(1, 0, 1, 0, 0, 
				GridBagConstraints.WEST, 
				GridBagConstraints.NONE, 
				5, 10, 5, 5));

		//Age
		lblAge = new JLabel("Age: " + counsellee.getAge() + " years");
		panelTopLeft.add(lblAge, SwingUtils.getConstraints(2, 0, 1, 0, 0, 
				GridBagConstraints.WEST, 
				GridBagConstraints.NONE, 
				5, 10, 5, 5));

		//Parent Org
		lblParentOrg = new JLabel("Parent Organization: " + counsellee.getParentOrg());
		panelTopLeft.add(lblParentOrg, SwingUtils.getConstraints(3, 0, 1, 0, 0, 
				GridBagConstraints.WEST, 
				GridBagConstraints.NONE, 
				5, 10, 5, 5));

		//Counselllor Name
		lblCounsellorName = new JLabel("Counsellor: " + "Marie D'Souza");
		panelTopLeft.add(lblCounsellorName, SwingUtils.getConstraints(4, 0, 1, 1, 1, 
				GridBagConstraints.WEST, 
				GridBagConstraints.NONE, 
				5, 10, 5, 5));

//		//Case Number
//		lblCaseNumber = new JLabel(counsellee.getCaseNumber());
//		panelTopLeft.add(lblCaseNumber, SwingUtils.getConstraints(1, 0, 1, 0, 0, 
//				GridBagConstraints.WEST, 
//				GridBagConstraints.NONE, 
//				5, 10, 5, 5));
//
//		//Case Number
//		lblCaseNumber = new JLabel(counsellee.getCaseNumber());
//		panelTopLeft.add(lblCaseNumber, SwingUtils.getConstraints(1, 0, 1, 0, 0, 
//				GridBagConstraints.WEST, 
//				GridBagConstraints.NONE, 
//				5, 10, 5, 5));

		
		//Top right Panel
		panelTopRight = new JPanel();
		panelTopRight.setPreferredSize(new Dimension(200, 180));
		panelTopRight.setBorder(new TitledBorder("Symptoms check lists"));
		panelTopRight.setLayout(new BorderLayout());
		panelTop.add(panelTopRight, SwingUtils.getConstraints(0, 1, 1, 2, 2, 
				GridBagConstraints.WEST, 
				GridBagConstraints.BOTH, 
				0, 0, 0, 0));
		
		//table sympts
		panelSymptCenter = new JPanel();
		panelSymptCenter.setLayout(new BorderLayout());
		panelTopRight.add(panelSymptCenter, BorderLayout.CENTER);
		
		modelSymptChkList = new MHSymptChkListModel();
		tblSymptCheckLists = new JTable(modelSymptChkList);
		JScrollPane scrollPaneTbl = new JScrollPane(tblSymptCheckLists);
		tblSymptCheckLists.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		panelSymptCenter.add(scrollPaneTbl, BorderLayout.CENTER);
		
		//buttons
		panelSymptBottom = new JPanel();
		panelSymptBottom.setLayout(new GridBagLayout());
		panelTopRight.add(panelSymptBottom, BorderLayout.SOUTH);
		
		cbSymptChkLists = new JComboBox<String>();
		cbSymptChkLists.addItem(MHSymptChkListObj.SYMPT_CKLIST_TYPE_TSC40);
		cbSymptChkLists.addItem(MHSymptChkListObj.SYMPT_CKLIST_TYPE_TSC54);
		panelSymptBottom.add(cbSymptChkLists, SwingUtils.getConstraints(0, 0, 1, 0, 0, 
				GridBagConstraints.WEST, 
				GridBagConstraints.NONE, 
					5, 0, 10, 0));
		
		btnAddSymptChkList = new JButton("Add a new symptoms checklist");
		btnAddSymptChkList.addActionListener(this);
		panelSymptBottom.add(btnAddSymptChkList, SwingUtils.getConstraints(0, 1, 1, 0, 0, 
				GridBagConstraints.WEST, 
				GridBagConstraints.NONE, 
				5, 5, 10, 0));
		
		btnEditSymptChkList = new JButton("Edit");
		btnEditSymptChkList.addActionListener(this);
		panelSymptBottom.add(btnEditSymptChkList, SwingUtils.getConstraints(0, 2, 1, 0, 0, 
				GridBagConstraints.WEST, 
				GridBagConstraints.NONE, 
				5, 10, 10, 0));
		
		btnDelSymptChkList = new JButton("Delete");
		btnDelSymptChkList.addActionListener(this);
		panelSymptBottom.add(btnDelSymptChkList, SwingUtils.getConstraints(0, 3, 1, 0, 0, 
				GridBagConstraints.WEST, 
				GridBagConstraints.NONE, 
				5, 10, 10, 0));
		
		
		//Center Panel
		panelCenter = new JPanel();
		panelCenter.setBorder(new TitledBorder("Session details"));
		add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(new BorderLayout());
		
		//Table
		modelSessions = new MHSessionsTableModel();
		Hashtable<String, String> htOptionsSessions = new Hashtable<String, String>();
		htOptionsSessions.put(ACEDefines.COUNSELLE_CASE_ID, counsellee.getCaseNumber());
		
		modelSessions.setList(ACEConnector.getInstance().getCounsellingSession(htOptionsSessions));
		tblSessions = new JTable(modelSessions);
		JScrollPane scrollPaneTblSessions = new JScrollPane(tblSessions);
		tblSessions.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		panelCenter.add(scrollPaneTblSessions, BorderLayout.CENTER);
		scrollPaneTblSessions.setBackground(Color.BLUE);
		
		panelSessionButtons = new JPanel();
		panelSessionButtons.setLayout(new FlowLayout());
		panelCenter.add(panelSessionButtons, BorderLayout.SOUTH);
		
		btnAddSession = new JButton("New Session");
		btnAddSession.addActionListener(this);
		panelSessionButtons.add(btnAddSession);
		
		btnEditSession = new JButton("Edit Session");
		btnEditSession.addActionListener(this);
		panelSessionButtons.add(btnEditSession);
		
		btnDelSession = new JButton("Delete Session");
		btnDelSession.addActionListener(this);
		panelSessionButtons.add(btnDelSession);
		
		//Bottom Panel
		panelBottom = new JPanel();
		panelBottom.setLayout(new FlowLayout());
		add(panelBottom, BorderLayout.SOUTH);
		
		//close button
		btnClose = new JButton("Close");
		panelBottom.add(btnClose);
		btnClose.addActionListener(this);
		

		//set the checklists in the table
		Hashtable htOptions = new Hashtable();
		htOptions.put(ACEDefines.COUNSELLE_CASE_ID, counsellee.getCaseNumber());
		Vector<MHSymptChkListObj> vCheckLists = ACEConnector.getInstance().getTSCCheckLists(htOptions);
		modelSymptChkList.setList(vCheckLists);
		System.out.println("nr of row in checklist  " + vCheckLists.size());

	}

	@Override
	public void setMHPanelObserver(MHPanelObserver obs) {
		panelObserver = obs;
		
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnClose){
			panelObserver.removePanel();
		}
		else if (e.getSource() == btnAddSymptChkList){
			if (cbSymptChkLists.getSelectedItem().equals(MHSymptChkListObj.SYMPT_CKLIST_TYPE_TSC40)){
				TSC40MajorPanel majorPanel = new TSC40MajorPanel(counsellee, this);
				majorPanel.setMHPanelObserver(panelObserver);
				panelObserver.addPanel(majorPanel);
			}
			else if (cbSymptChkLists.getSelectedItem().equals(MHSymptChkListObj.SYMPT_CKLIST_TYPE_TSC54)){
				TSC54MinorPanel minorPanel = new TSC54MinorPanel(counsellee, this);
				minorPanel.setMHPanelObserver(panelObserver);
				panelObserver.addPanel(minorPanel);
			}
		}
		else if (e.getSource() == btnEditSymptChkList){
			int row = tblSymptCheckLists.getSelectedRow();
			MHSymptChkListObj obj = modelSymptChkList.getRowObject(row);
			System.out.println("chklist selected: " + obj.getChkListType());
			if (obj.getChkListType().equals(MHSymptChkListObj.SYMPT_CKLIST_TYPE_TSC40)){
				TSC40MajorPanel majorPanel = new TSC40MajorPanel(counsellee, this, (TSC40Obj)obj);
				majorPanel.setMHPanelObserver(panelObserver);
				panelObserver.addPanel(majorPanel);
				
			}else{
				TSC54MinorPanel minorPanel = new TSC54MinorPanel(counsellee, this, obj);
				minorPanel.setMHPanelObserver(panelObserver);
				panelObserver.addPanel(minorPanel);
			}
		}
		else if (e.getSource() == btnDelSymptChkList){
			int row = tblSymptCheckLists.getSelectedRow();
			MHSymptChkListObj obj = modelSymptChkList.getRowObject(row);
			if (obj.getChkListType().equals(MHSymptChkListObj.SYMPT_CKLIST_TYPE_TSC40)){
				if (ACEConnector.getInstance().deleteTSC40CheckLst((TSC40Obj)obj)){
					modelSymptChkList.removeRow(obj);
				}else{
					JOptionPane.showMessageDialog(this, "Error deleting TSC 40 details from server","Error", JOptionPane.ERROR_MESSAGE);
				}

			}else{
				if (ACEConnector.getInstance().deleteTSC54CheckLst((TSC54Obj)obj)){
					modelSymptChkList.removeRow(obj);
				}else{
					JOptionPane.showMessageDialog(this, "Error deleting TSC 54 details from server","Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		else if (e.getSource() == btnAddSession){
			CounsellingSessionPanel sessionsPanel = new CounsellingSessionPanel(counsellee, this);
			sessionsPanel.setMHPanelObserver(panelObserver);
			panelObserver.addPanel(sessionsPanel);
		}		
		else if (e.getSource() == btnEditSession){
			CounsellingSessionPanel sessionsPanel = new CounsellingSessionPanel(counsellee,this,
													modelSessions.getRowObject(tblSessions.getSelectedRow()));
			sessionsPanel.setMHPanelObserver(panelObserver);
			panelObserver.addPanel(sessionsPanel);
		}		
		else if (e.getSource() == btnDelSession){
			if (ACEConnector.getInstance().deleteCounsellingSession(
							modelSessions.getRowObject(tblSessions.getSelectedRow()))){
				modelSessions.removeRow(tblSessions.getSelectedRow());
			}
		}		
	}
	
	public void addSymptChkLists(MHSymptChkListObj chklistObj){
		System.out.println("add row chklist to model");
		modelSymptChkList.addRow(chklistObj);
	}
	
	public void updateSymptChkLists(MHSymptChkListObj chklistObj){
		System.out.println("add row chklist to model");
		modelSymptChkList.updateRow(chklistObj);
	}

	public void addCnslingSession(CounsellingSessionObj sessionObj){
		System.out.println("add row chklist to model");
		modelSessions.addRow(sessionObj);
	}
	
	public void updateCnslingSession(CounsellingSessionObj sessionObj){
		System.out.println("add row chklist to model");
		modelSessions.updateRow(sessionObj);
	}
	

}

