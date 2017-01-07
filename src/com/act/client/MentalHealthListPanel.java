package com.act.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Hashtable;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.act.client.models.MHMainTableModel;
import com.act.common.ACEDefines;
import com.act.common.CounseleeHistoryObj;
import com.act.common.Counsellee;
//import com.act.common.CounselleeHistory;
import com.act.common.SwingUtils;

public class MentalHealthListPanel extends MHPanel implements  ActionListener{
	

	JPanel panelLeft;
	JPanel panelLeftTop;
	JPanel panelSearch;
	JPanel panelCenter;
	JPanel panelButtons;
	JPanel panelBottom;

	JLabel usrName;
	JLabel usrTitle;
	
	JLabel name;
	JTextField  txtName;
	
	JLabel parentOrg;
	JTextField  txtParentOrg;

	JLabel otherName;
	JTextField  txtOtherName;

	JLabel status;
	JTextField  txtStatus;
	
	JLabel counsellorName;
	JTextField txtCounsellorName;
	
	JButton btnSearch;
	JButton btnAdd;
	JButton btnEdit;
	JButton btnDelete;
	JButton btnDetails;
		
	JTable tblMHData;
	MHMainTableModel modelMH;
	JPopupMenu popMHTbl;
	JMenuItem menuItemAdd ;
	JMenuItem menuItemEdit;
	JMenuItem menuItemRemove;
	JMenuItem menuItemDetails;
	MHPanelObserver panelObserver;
	
	private URL codebase;
	
	public MentalHealthListPanel(URL codebase){
		this.codebase = codebase;
		setSize(1000,850);
		initUI();
		
	}
	
	private void initUI(){
		
		setLayout(new BorderLayout());
		
		//left panel
		panelLeft = new JPanel();
		panelLeft.setLayout(new GridBagLayout());
		add(panelLeft, BorderLayout.WEST);
		
		panelLeftTop = new JPanel();
		panelLeftTop.setLayout(new GridBagLayout());
		panelLeft.add(panelLeftTop, SwingUtils.getConstraints(0, 0, 1, 0, 0, 
				GridBagConstraints.WEST, 
				GridBagConstraints.NONE, 
				10, 10, 5, 5));
		
		//logged in counsellor name
		usrName = new JLabel ("Marie D'Souza");
		usrName.setFont(new Font(usrName.getFont().getFontName(),usrName.getFont().getStyle(), 20));
		panelLeftTop.add(usrName,SwingUtils.getConstraints(0, 0, 1, 0, 0, 
				GridBagConstraints.WEST, 
				GridBagConstraints.NONE, 
				10, 10, 5, 5));
		
		usrTitle = new JLabel ("Admin");
		usrTitle.setFont(new Font(usrName.getFont().getFontName(),usrName.getFont().getStyle(), 14));
		panelLeftTop.add(usrTitle,SwingUtils.getConstraints(1, 0, 1, 0, 0, 
				GridBagConstraints.WEST, 
				GridBagConstraints.NONE, 
				5, 10, 5, 5));
		
		//left search panel
		panelSearch = new JPanel();
		panelLeft.add(panelSearch, SwingUtils.getConstraints(1, 0, 1, 0, 0, 
				GridBagConstraints.WEST, 
				GridBagConstraints.NONE, 
				20, 10, 5, 5));
		panelSearch.setBorder(new TitledBorder("Search"));
		panelSearch.setLayout(new GridBagLayout());
		
		//Name
		name = new JLabel("Counsellee Name:");
		panelSearch.add(name,SwingUtils.getConstraints(0, 0, GridBagConstraints.RELATIVE, 0, 0, 
													GridBagConstraints.WEST, 
													GridBagConstraints.NONE, 
													10, 10, 5, 5));
		txtName = new JTextField();
		txtName.setPreferredSize(new Dimension(150, 22));
		panelSearch.add(txtName,SwingUtils.getConstraints(0, 1, GridBagConstraints.REMAINDER, 0,0, 
				GridBagConstraints.WEST, 
				GridBagConstraints.HORIZONTAL, 
				10, 5, 5, 5));
		
		
		//CounsellorName
		counsellorName = new JLabel("Counsellor Name:");
		panelSearch.add(counsellorName,SwingUtils.getConstraints(1, 0, GridBagConstraints.RELATIVE, 0, 0, 
													GridBagConstraints.WEST, 
													GridBagConstraints.NONE, 
													5, 10, 5, 5));
		txtCounsellorName = new JTextField();
		panelSearch.add(txtCounsellorName,SwingUtils.getConstraints(1, 1, GridBagConstraints.REMAINDER, 0, 0, 
				GridBagConstraints.WEST, 
				GridBagConstraints.HORIZONTAL, 
				5, 5, 5, 5));
		
		//Organization
		parentOrg = new JLabel("Parent Organization:");
		panelSearch.add(parentOrg,SwingUtils.getConstraints(2, 0, GridBagConstraints.RELATIVE, 0, 0, 
													GridBagConstraints.WEST, 
													GridBagConstraints.NONE, 
													5, 10, 5, 5));
		txtParentOrg = new JTextField();
		panelSearch.add(txtParentOrg,SwingUtils.getConstraints(2, 1, GridBagConstraints.REMAINDER, 0, 0, 
				GridBagConstraints.WEST, 
				GridBagConstraints.HORIZONTAL, 
				5, 5, 5, 5));
		
		
		//Date
		otherName = new JLabel("Other Name:");
		panelSearch.add(otherName,SwingUtils.getConstraints(3, 0, GridBagConstraints.RELATIVE, 0, 0, 
													GridBagConstraints.WEST, 
													GridBagConstraints.NONE, 
													5, 10, 5, 5));
		txtOtherName = new JTextField();
		panelSearch.add(txtOtherName,SwingUtils.getConstraints(3, 1, GridBagConstraints.REMAINDER, 0, 0, 
				GridBagConstraints.WEST, 
				GridBagConstraints.HORIZONTAL, 
				5, 5, 5, 5));
		
		
        //Search
		btnSearch = new JButton("Search");
		btnSearch.addActionListener(this);
		btnSearch.setPreferredSize(new Dimension(100,30));
		panelSearch.add(btnSearch,SwingUtils.getConstraints(4, 0, GridBagConstraints.REMAINDER, 0, 0, 
				GridBagConstraints.WEST, 
				GridBagConstraints.HORIZONTAL, 
				5, 5, 5, 5));
		
		//this is added as a dummy for layout adjustments
		JLabel lblTest = new JLabel();
		panelLeft.add(lblTest, SwingUtils.getConstraints(2, 0, 1, 1, 1, 
				GridBagConstraints.WEST, 
				GridBagConstraints.NONE, 
				10, 10, 5, 5));
						
		//center panel
		panelCenter = new JPanel();
		add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(new BorderLayout());
		
		//Table
		modelMH = new MHMainTableModel();
		modelMH.setList(ACEConnector.getInstance(codebase).getCounseleeList(new Hashtable()));
		tblMHData = new JTable(modelMH);
		JScrollPane scrollPaneTbl = new JScrollPane(tblMHData);
		tblMHData.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		panelCenter.add(scrollPaneTbl, BorderLayout.CENTER);
		scrollPaneTbl.setBackground(Color.BLUE);
		
		//Create pop up menu for the table
		popMHTbl = new JPopupMenu();
		menuItemAdd = new JMenuItem("New");
		menuItemAdd.addActionListener(this);
		
		menuItemEdit = new JMenuItem("Edit");
		menuItemEdit.addActionListener(this);
		
		menuItemRemove = new JMenuItem("Delete");
		menuItemRemove.addActionListener(this);
		
		menuItemDetails = new JMenuItem("Counselling Details");
		menuItemDetails.addActionListener(this);

		popMHTbl.add(menuItemDetails);
		popMHTbl.add(menuItemAdd);
		popMHTbl.add(menuItemEdit);
		popMHTbl.add(menuItemRemove);
		tblMHData.setComponentPopupMenu(popMHTbl);
		
		//buttons panel
		panelButtons = new JPanel();
		panelButtons.setLayout(new FlowLayout());
		panelCenter.add(panelButtons, BorderLayout.SOUTH);
		
		//Add button
        //Add
		btnAdd = new JButton("Add");
		btnAdd.addActionListener(this);
		panelButtons.add(btnAdd);
		
		//Edit
		btnEdit = new JButton("Edit");
		panelButtons.add(btnEdit);
		btnEdit.addActionListener(this);

		//Delete
		btnDelete = new JButton("Delete");
		panelButtons.add(btnDelete);
		btnDelete.addActionListener(this);
		
		//bottom panel
		panelBottom = new JPanel();
		add(panelBottom, BorderLayout.SOUTH);
//		panelBottom.setBackground(Color.red);
		panelBottom.setSize(new Dimension(100, 60));
		
		
	}

	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == btnSearch){
			System.out.println("search button hit");
			search();
			
		}
		
		else if (e.getSource() == btnAdd ||
				e.getSource() == menuItemAdd){
			IntakeFormDlg dlg = new IntakeFormDlg(this, codebase);
			dlg.setModal(true);
			dlg.setLocation(new Point (200, 0));
			dlg.setSize(new Dimension(1000, 720));
			dlg.setVisible(true);
		}
		
		else if (e.getSource() == btnEdit ||
				e.getSource() == menuItemEdit){
			
			Counsellee cnslee = modelMH.getRowObject(tblMHData.getSelectedRow()); 
			CounseleeHistoryObj cnsleeHist = ACEConnector.getInstance(codebase).getCounseleeHist(cnslee.getCaseNumber());
			
			IntakeFormDlg dlg = new IntakeFormDlg(this, codebase,cnslee, cnsleeHist);
			dlg.setModal(true);
			dlg.setLocation(new Point (200, 0));
			dlg.setSize(new Dimension(1000, 720));
			dlg.setVisible(true);
		}
		
		else if (e.getSource() == btnDelete ||
				e.getSource() == menuItemRemove){
			delete();
		}
		else if (e.getSource() == menuItemDetails){
			CounselleeMain cnsleeMain = new CounselleeMain(modelMH.getRowObject(tblMHData.getSelectedRow()));
			cnsleeMain.setMHPanelObserver(panelObserver);
			panelObserver.addPanel(cnsleeMain);
		}
		
	}

	private void search(){
		//get the search criteria
		Hashtable<String, String> htOptions  = new Hashtable<String, String>();
		
		String sName = txtName.getText().trim(); 
		if (sName.length() >0){
			htOptions.put(ACEDefines.COUNSELLE_NAME, sName);
		}
		
		String sCnslrName = txtCounsellorName.getText().trim(); 
		if (sCnslrName.length() >0){
			htOptions.put(ACEDefines.COUNSELLOR_NAME, sCnslrName);
		}
		
		String sParentOrg = txtParentOrg.getText().trim(); 
		if (sParentOrg.length() >0){
			htOptions.put(ACEDefines.COUNSELLE_ORGANIZATION, sParentOrg);
		}
		
		String sOtherName = txtOtherName.getText().trim(); 
		if (sOtherName.length() >0){
			htOptions.put(ACEDefines.COUNSELLE_OTHER_NAME, sOtherName);
		}
		
		modelMH.setList(ACEConnector.getInstance(codebase).getCounseleeList(htOptions));
		revalidate();
		System.out.println("table updated");

	}
	private void delete(){
		if (tblMHData.getSelectedRowCount() >0){
			Vector vCaseIds = new Vector();
			
			int[] selRows = tblMHData.getSelectedRows();
			
			for (int i = 0; i < selRows.length; i++) {
				Counsellee couns = ((MHMainTableModel) tblMHData.getModel()).getRowObject(selRows[i]);
				vCaseIds.add(couns.getCaseNumber());
				
			}
			
			if (!ACEConnector.getInstance(codebase).deleteCounseleeList(vCaseIds))
				JOptionPane.showMessageDialog(this, "Error deleting counsellee record.","Error", JOptionPane.ERROR_MESSAGE);
			
			refreshData();
		}
	} 
	
	public void refreshData(){
		modelMH.setList(ACEConnector.getInstance(codebase).getCounseleeList(new Hashtable()));
	}

	@Override
	public void setMHPanelObserver(MHPanelObserver obs) {
		panelObserver = obs;
	}


}
