package com.act.client.models;

import java.util.Iterator;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import com.act.client.reports.CounsellingReportPanel;
import com.act.common.ACEDefines;
import com.act.common.CounselleeReportDataObject;
import com.act.common.CounsellingSessionObj;
import com.act.common.PersonName;

public class CounselleeReportsModel extends AbstractTableModel {
	
	private Vector<String> columnNames = new Vector<String>();
	
	Vector<CounselleeReportDataObject> vCnsleeReportData = new Vector<CounselleeReportDataObject>();

	public CounselleeReportsModel(){
		columnNames.add("Name");
		columnNames.add("Counsellor Name");
		
	}
	public void setList(Vector<CounselleeReportDataObject> vCnsleeReportData){
		this.vCnsleeReportData = vCnsleeReportData;
		fireTableDataChanged();
	}
	
	public int getRowCount() {
		// TODO Auto-generated method stub
		return vCnsleeReportData.size();
	}

	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.size();
	}

	public String getColumnName(int columnIndex) {
		// TODO Auto-generated method stub
		
		return columnNames.elementAt(columnIndex);
	}
	
	public Object getValueAt(int rowIndex, int columnIndex) {

		String value = "";
		CounselleeReportDataObject reportData = vCnsleeReportData.get(rowIndex);
		
		if (reportData == null){
			return value;
		}
	
		String colName = columnNames.elementAt(columnIndex);
		try{
			switch (colName) {
			
				case ACEDefines.COUNSELLE_NAME:
					return PersonName.getFormattedName(reportData.getsCnsleeName());
				case ACEDefines.COUNSELLE_AGE:
					return reportData.getsCnsleeAge();
				case ACEDefines.COUNSELLE_LOCATION:
					return reportData.getsLocation();
				case ACEDefines.COUNSELLE_ORGANIZATION:
					return reportData.getsOrganization();
				case ACEDefines.CNSLING_SESSION_DATE:
					return reportData.getsSessionDate();
				case ACEDefines.CNSLING_SESSION_DURATION:
					return reportData.getsDuration();
				case ACEDefines.CNSLING_SESSION_SUMMARY:
					return reportData.getsSessionContents();
				case ACEDefines.CNSLING_SESSION_NEXT_OBJ:
					return reportData.getsNextObjectives();
				case ACEDefines.CNSLING_SESSION_CASE_MGMT:
					return reportData.getsCaseManagement();
				case ACEDefines.TFCBT_STAGE:
					return reportData.getsTfcbtStage();
				case ACEDefines.TFCBT_COMMENTS:
					return reportData.getsTfcbtComments();
				case ACEDefines.TFCBT_ACTIVITIES:
					return reportData.getsTfcbtActivities();
				default:
					break;
				
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	
		return value;

	}

	public String [] getColumnNames() {
		String[] colNames = new String[columnNames.size()];
		for (int i = 0; i < colNames.length; i++) {
			colNames[i] = this.columnNames.elementAt(i);
		}
		
		return colNames;
	}

	public void setColumnNames(Vector<String> vColumnNames) {
		columnNames = vColumnNames;
		fireTableStructureChanged();
	
	}

}

