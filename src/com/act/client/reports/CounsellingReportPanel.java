package com.act.client.reports;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;


import com.act.client.ACEConnector;
import com.act.client.components.JAceDate;
import com.act.client.models.CounselleeReportsModel;
import com.act.common.ACEDefines;
import com.act.common.CounselleeReportDataObject;
import com.act.common.CounsellingTherapy;
import com.act.common.Counsellor;
import com.act.common.Organisation;
import com.act.common.SwingUtils;

public class CounsellingReportPanel extends JPanel implements ActionListener{
	
	JPanel 	panelTop, panelCriteria, panelTherapyCriteriaTFCBT,
			panelColumnNames, panelCriterianBtns,
			panelViewReports, panelBottom;
	
	//Criteria Fields
	JLabel 	counsellor, location, date, lblDateFrom, lblDateTo,
			organization, cnsleeName, caseId, status, tfcbtStage,
			tfcbtStageFrom, tfcbtStageTo, therapy;
	
	JTextField txtCounsellor;
	JTextField txtLocation;
	JTextField txtOrganization;
	JComboBox<CounsellingTherapy> cbTherapy;
	JAceDate dateFrom, dateTo;
	JTextField txtCnsleeName, txtCnsleeId;
	JComboBox<String> cbTFCBTStageFrom, cbTFCBTStageTo, cbStatus;
	
	JButton btnShowReports, btnGenerateReport, btnClose;
	
	JTable tblReports;
	CounselleeReportsModel modelCnsleeReports;
	
	//Required Columns
	JCheckBox chkCnsleeName, 
				chkCnsleeAge,
				chkOrganization,
				chkLocation,
				chkSessionDate,
				chkDuration,
				chkSessionContents,
				chkTfcbtStage,
				chkTfcbtActivities,
				chkTfcbtComments,
				chkNextObjectives,
				chkCaseManagement;
	
	
	public CounsellingReportPanel(){
		initUI();
	}
	
	private void initUI(){
		try{
			
			setLayout(new BorderLayout());
			
			panelTop = new JPanel();
			panelTop.setLayout(new GridBagLayout());
			add(panelTop, BorderLayout.NORTH);
			
			//Panel Criteria
			panelCriteria = new JPanel();
			panelCriteria.setLayout(new GridBagLayout());
			panelCriteria.setBorder(new TitledBorder("Report Criteria"));
			panelTop.add(panelCriteria, SwingUtils.getConstraints(0, 0, 1, 1, 1, 
					GridBagConstraints.WEST, 
					GridBagConstraints.BOTH, 
					0, 0, 0, 0));

			//Add criteria
			//Counsellor
			counsellor = new JLabel("Counsellor:");
			panelCriteria.add(counsellor, SwingUtils.getConstraints(0, 0, 1, 0, 0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					10, 10, 0, 5));
			
			txtCounsellor = new JTextField();
			panelCriteria.add(txtCounsellor, SwingUtils.getConstraints(0, 1, 1, 0, 0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.HORIZONTAL, 
					10, 5, 0, 5));
			
			//Organization
			organization = new JLabel("Organization:");
			panelCriteria.add(organization, SwingUtils.getConstraints(1, 0, 1, 0, 0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					5, 10, 0, 5));
			
			txtOrganization = new JTextField();
			panelCriteria.add(txtOrganization, SwingUtils.getConstraints(1, 1, 1, 0, 0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.HORIZONTAL, 
					5, 5, 0, 5));
			
			//Location
			location = new JLabel("Location:");
			panelCriteria.add(location, SwingUtils.getConstraints(2, 0, 1, 0, 0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					5, 10, 0, 5));
			
			txtLocation = new JTextField();
			panelCriteria.add(txtLocation, SwingUtils.getConstraints(2, 1, 1, 0, 0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.HORIZONTAL, 
					5, 5, 0, 5));
			
			//Cnslee Name
			cnsleeName = new JLabel("Counsellee Name:");
			panelCriteria.add(cnsleeName, SwingUtils.getConstraints(3, 0, 1, 0, 0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					5, 10, 0, 5));
			
			txtCnsleeName = new JTextField();
			txtCnsleeName.setPreferredSize(new Dimension(200,22));
			panelCriteria.add(txtCnsleeName, SwingUtils.getConstraints(3, 1, 1, 0, 0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.HORIZONTAL, 
					5, 5, 0, 5));
			
			//Status
			status = new JLabel("Status:");
			panelCriteria.add(status, SwingUtils.getConstraints(4, 0, 1, 0, 0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					5, 10, 0, 5));
			
			cbStatus = new JComboBox<String>();
			panelCriteria.add(cbStatus, SwingUtils.getConstraints(4, 1, 1, 0, 0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.HORIZONTAL, 
					5, 5, 0, 5));
			
			//Therapy
			therapy = new JLabel("Therapy:");
			panelCriteria.add(therapy, SwingUtils.getConstraints(5, 0, 1, 0, 0, 
								GridBagConstraints.WEST, 
								GridBagConstraints.NONE, 
								5, 10, 5, 5));
			
			cbTherapy = new JComboBox<CounsellingTherapy>();
			panelCriteria.add(cbTherapy, SwingUtils.getConstraints(5, 1, 1, 0, 0, 
								GridBagConstraints.WEST, 
								GridBagConstraints.HORIZONTAL, 
								5, 5, 0, 5));
			
			date = new JLabel("From Date:");
			panelCriteria.add(date, SwingUtils.getConstraints(0, 2, 1, 0, 0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					10, 10, 0, 5));
			
			dateFrom = new JAceDate();
			panelCriteria.add(dateFrom, SwingUtils.getConstraints(0, 3, 1, 0, 0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.HORIZONTAL, 
					10, 5, 0, 5));
			
			panelCriteria.add(new JLabel("To Date:"), SwingUtils.getConstraints(1, 2, 1, 0, 0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					5, 10, 0, 5));
			
			dateTo = new JAceDate();
			panelCriteria.add(dateTo, SwingUtils.getConstraints(1, 3, 1, 0, 0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.HORIZONTAL, 
					5, 5, 0, 5));
			
			//Therapy Criteria
			panelTherapyCriteriaTFCBT = new JPanel();
			panelTherapyCriteriaTFCBT.setLayout(new GridBagLayout());
			panelTherapyCriteriaTFCBT.setBorder(new TitledBorder("Therapy Criteria"));
			panelCriteria.add(panelTherapyCriteriaTFCBT, SwingUtils.getConstraints(2, 2, 2,4, 1, 1, 
								GridBagConstraints.WEST, 
								GridBagConstraints.BOTH, 
								5, 10, 0, 5));
			
			tfcbtStage = new JLabel("TFCBT Stage from");
			panelTherapyCriteriaTFCBT.add(tfcbtStage, SwingUtils.getConstraints(0, 0, 1, 0, 0, 
								GridBagConstraints.WEST, 
								GridBagConstraints.NONE, 
								5, 5, 0, 5));
			
			cbTFCBTStageFrom = new JComboBox<String>();
			panelTherapyCriteriaTFCBT.add(cbTFCBTStageFrom, SwingUtils.getConstraints(0, 1, 1, 0, 0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.HORIZONTAL, 
					5, 5, 0, 5));
			
			tfcbtStageTo = new JLabel("To");
			panelTherapyCriteriaTFCBT.add(tfcbtStageTo, SwingUtils.getConstraints(0, 2, 1, 0, 0, 
								GridBagConstraints.WEST, 
								GridBagConstraints.NONE, 
								5, 15, 0, 5));
			
			cbTFCBTStageTo = new JComboBox<String>();
			panelTherapyCriteriaTFCBT.add(cbTFCBTStageTo, SwingUtils.getConstraints(0, 3, 1, 0, 0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.HORIZONTAL, 
					5, 5, 0, 5));

			//populate the stages
			cbTFCBTStageFrom.addItem("");
			cbTFCBTStageTo.addItem("");
			for (int i = 1; i < 11; i++) {
				String item = String.valueOf(i);
				cbTFCBTStageFrom.addItem(item);
				cbTFCBTStageTo.addItem(item);
			}
			
			//Panel ColumnNames
			panelColumnNames = new JPanel();
			panelColumnNames.setLayout(new GridBagLayout());
			panelColumnNames.setBorder(new TitledBorder("Required Columns"));
			panelTop.add(panelColumnNames, SwingUtils.getConstraints(0, 1, 1, 1, 1, 
					GridBagConstraints.WEST, 
					GridBagConstraints.BOTH, 
					0, 0, 0, 0));

			//Column Names
			chkCnsleeName = new JCheckBox("Counsellee Name");
			panelColumnNames.add(chkCnsleeName, SwingUtils.getConstraints(0, 0, 1, 0, 0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					5, 5, 0, 5));
			
			chkCnsleeAge = new JCheckBox("Age");
			panelColumnNames.add(chkCnsleeAge, SwingUtils.getConstraints(1, 0, 1, 0, 0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					5, 5, 0, 5));
			
			chkOrganization = new JCheckBox("Organization");
			panelColumnNames.add(chkOrganization, SwingUtils.getConstraints(2, 0, 1, 0, 0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					5, 5, 0, 5));
			
			chkLocation = new JCheckBox("Location");
			panelColumnNames.add(chkLocation, SwingUtils.getConstraints(3, 0, 1, 0, 0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					5, 5, 0, 5));
			
			chkSessionDate = new JCheckBox("Session Date");
			panelColumnNames.add(chkSessionDate, SwingUtils.getConstraints(0, 1, 1, 0, 0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					5, 5, 0, 5));
			
			chkDuration = new JCheckBox("Duration");
			panelColumnNames.add(chkDuration, SwingUtils.getConstraints(1, 1, 1, 0, 0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					5, 5, 0, 5));
			
			chkSessionContents = new JCheckBox("Session Summary");
			panelColumnNames.add(chkSessionContents, SwingUtils.getConstraints(2, 1, 1, 0, 0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					5, 5, 0, 5));
			
			
			chkTfcbtStage = new JCheckBox("TFCBT Stage");
			panelColumnNames.add(chkTfcbtStage, SwingUtils.getConstraints(0, 2, 1, 0, 0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					5, 5, 0, 5));
			
			chkTfcbtActivities = new JCheckBox("TFCBT Activities");
			panelColumnNames.add(chkTfcbtActivities, SwingUtils.getConstraints(1, 2, 1, 0, 0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					5, 5, 0, 5));
			
			chkTfcbtComments = new JCheckBox("TFCBT Comments");
			panelColumnNames.add(chkTfcbtComments, SwingUtils.getConstraints(2, 2, 1, 0, 0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					5, 5, 0, 5));
			
			chkNextObjectives = new JCheckBox("Next Objectives");
			panelColumnNames.add(chkNextObjectives, SwingUtils.getConstraints(3, 1, 1, 0, 0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					5, 5, 0, 5));
			
			chkCaseManagement = new JCheckBox("Case Management/Action Items");
			panelColumnNames.add(chkCaseManagement, SwingUtils.getConstraints(4, 0, 1, 0, 0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					5, 5, 0, 5));

			//SHow Reports
			btnShowReports = new JButton("Show Reports");
			btnShowReports.addActionListener(this);
			panelTop.add(btnShowReports, SwingUtils.getConstraints(1, 0, 2, 0, 0, 
					GridBagConstraints.CENTER, 
					GridBagConstraints.NONE, 
					10, 0, 10, 0));
			
			//Reports table
			modelCnsleeReports = new CounselleeReportsModel();
			tblReports = new JTable(modelCnsleeReports);
			JScrollPane spRports = new JScrollPane(tblReports);
			
			add(spRports, BorderLayout.CENTER);
			
			//Buttons
			panelBottom = new JPanel();
			panelBottom.setBorder(new TitledBorder(""));
			add(panelBottom, BorderLayout.SOUTH);
			
			btnGenerateReport = new JButton("Generate Report");
			btnGenerateReport.addActionListener(this);
			panelBottom.add(btnGenerateReport);

			btnClose = new JButton("Close");
			btnClose.addActionListener(this);
			panelBottom.add(btnClose);

			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Fetch the criteria for generating reports
	 * @return
	 */
	private Hashtable<String, String> getReportsCriteria(){
		Hashtable<String, String> htCriteria = new  Hashtable<String, String>();
		
		//Counsellor Id
		if (txtCounsellor.getText().length() > 0){
			htCriteria.put(ACEDefines.COUNSELLOR_NAME, txtCounsellor.getText());
		}
		
		//Organization
		if (txtOrganization.getText().length() > 0){
			htCriteria.put(ACEDefines.COUNSELLE_ORGANIZATION, txtOrganization.getText());
		}
		
		//Location
		if (txtLocation.getText().length() > 0){
			htCriteria.put(ACEDefines.COUNSELLE_LOCATION, txtLocation.getText());
		}
		
		//Counsellee Name
		if (txtCnsleeName.getText().length() > 0){
			htCriteria.put(ACEDefines.COUNSELLE_NAME, txtCnsleeName.getText());
		}
		
		//Status
		if (cbStatus.getSelectedIndex() > 0){
//			htCriteria.put(ACEDefines.COUNSELLOR_ID, cbCounsellor.getSelectedItem().toString()); //TODO
		}
		
		//Therapy
		if (cbTherapy.getSelectedIndex() > 0){
			htCriteria.put(ACEDefines.COUNSELLE_THERAPY, cbTherapy.getSelectedItem().toString());
		}
		
		//Dates
		if (dateFrom.getDate() != null)
			htCriteria.put(ACEDefines.CNSLING_SESSION_DATE_FROM, dateFrom.getDate());
		if (dateTo.getDate() != null)
			htCriteria.put(ACEDefines.CNSLING_SESSION_DATE_TO, dateTo.getDate());
		
		//TFCBT Stage
		String tfcbtStageFrom = cbTFCBTStageFrom.getSelectedItem().toString();
		if (tfcbtStageFrom.length() > 0){
			htCriteria.put(ACEDefines.TFCBT_STAGE_FROM, tfcbtStageFrom);
		}
		
		String tfcbtStageTo = cbTFCBTStageTo.getSelectedItem().toString();
		if (tfcbtStageTo.length() > 0){
			htCriteria.put(ACEDefines.TFCBT_STAGE_TO, tfcbtStageTo);
		}
		
		return htCriteria;
		
	}

	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == btnShowReports){
			
			//get the column names selected
			Vector vColNames = getSelectedColNames();
			
			Hashtable<String, String> htCriteria = getReportsCriteria();
			
			//Fetch the reports from the server
			Vector<CounselleeReportDataObject> vReportData = 
					ACEConnector.getInstance().getCounsellingReports(vColNames, htCriteria);
			
			if(vReportData == null){
				JOptionPane.showMessageDialog(this, "Error getting report data from server","Error", JOptionPane.ERROR_MESSAGE);
				
			}else{
				//display the report on the table
				modelCnsleeReports.setColumnNames(vColNames);
				modelCnsleeReports.setList(vReportData);
			}
			
		}else if (e.getSource() == btnGenerateReport){
			createReportExcel();
		}
		else if(e.getSource() == btnClose){
			
		}
		
	}
	
	private Vector<String> getSelectedColNames(){
	
		Vector<String> vColNames = new Vector<String>();
		
		if(chkCnsleeName.isSelected())
			vColNames.add(ACEDefines.COUNSELLE_NAME);
		if(chkCnsleeAge.isSelected())
			vColNames.add(ACEDefines.COUNSELLE_AGE);
		if(chkOrganization.isSelected())
			vColNames.add(ACEDefines.COUNSELLE_ORGANIZATION);
		if(chkLocation.isSelected())
			vColNames.add(ACEDefines.COUNSELLE_LOCATION);
		if(chkSessionDate.isSelected())
			vColNames.add(ACEDefines.CNSLING_SESSION_DATE);
		if(chkDuration.isSelected())
			vColNames.add(ACEDefines.CNSLING_SESSION_DURATION);
		if(chkSessionContents.isSelected())
			vColNames.add(ACEDefines.CNSLING_SESSION_SUMMARY);
		if(chkNextObjectives.isSelected())
			vColNames.add(ACEDefines.CNSLING_SESSION_NEXT_OBJ);
		if(chkCaseManagement.isSelected())
			vColNames.add(ACEDefines.CNSLING_SESSION_CASE_MGMT);
		if(chkTfcbtStage.isSelected())
			vColNames.add(ACEDefines.TFCBT_STAGE);
		if(chkTfcbtActivities.isSelected())
			vColNames.add(ACEDefines.TFCBT_ACTIVITIES);
		if(chkTfcbtComments.isSelected())
			vColNames.add(ACEDefines.TFCBT_COMMENTS);
		
		return vColNames;
	}
	
	private void createReportExcel(){
		
		try{
//			XSSFWorkbook workbook = new XSSFWorkbook();
//			FileOutputStream out1 = new FileOutputStream(new File("C:\\Geena\\ReportTest\\ClientReportTest.xlsx"));
//			XSSFSheet sheet = workbook.createSheet("Reports");
//			workbook.write(out1);
//
//			File reportFile = new File("C:\\Geena\\MyFirstTrial.txt");
//			if (!reportFile.exists()){
//				reportFile.createNewFile();
//				reportFile.setWritable(true);
//				
//			}
//			FileOutputStream out = new FileOutputStream(reportFile);
//			String content = "Trial text";
//			out.write(content.getBytes());
//			
//			out.close();
//			out1.close();
			
		}catch (Exception e){
			e.printStackTrace();
		}
	}

}
