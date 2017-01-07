package com.act.client.tfcbt;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import com.act.common.Counsellee;
import com.act.common.CnsleeTFCBTTask;

public class TFCBTStageTaskModel extends AbstractTableModel{

	String [] columns = new String[] {"Task" ,
										"Completed",
										"Date Completed",
										"Notes",	
									 };
	
	Vector<CnsleeTFCBTTaskRowObj> vData = new Vector<CnsleeTFCBTTaskRowObj>(); 
	
	public String getColumnName(int columnIndex) {
		return columns[columnIndex];
	}
	public int getRowCount() {
		
		return vData.size();
	}

	public int getColumnCount() {
		
		return columns.length;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {

		String value = "";
		
		try{
			CnsleeTFCBTTaskRowObj cnsleeTaskRowObj = vData.get(rowIndex);
			
			if (cnsleeTaskRowObj == null){
				return value;
			}
		
		
			switch (columnIndex) {
			
				case 0:
					return cnsleeTaskRowObj.getTaskObj().getTitle();
				case 1:
					return String.valueOf(cnsleeTaskRowObj.getCnsleeTaskObj().isTaskCompleted());
				case 2:
					return cnsleeTaskRowObj.getCnsleeTaskObj().getDateCompleted();
				case 3:
					return cnsleeTaskRowObj.getCnsleeTaskObj().getTaskNotes();
				default:
					break ;
				
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return value;
	}

	public CnsleeTFCBTTaskRowObj getRowObject(int row){
		if (row > -1){
			if (vData != null && vData.size() > row){
				return vData.get(row);
			}
		}
		
		return null;
	}
	

	public void setList(Vector<CnsleeTFCBTTaskRowObj> vData){
		if (vData == null)
			return;
		
		this.vData = vData;
		fireTableDataChanged();
		fireTableRowsInserted(0, 1);
	}
}
