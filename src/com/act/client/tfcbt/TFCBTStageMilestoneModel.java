package com.act.client.tfcbt;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import com.act.common.CnsleeTFCBTMileStone;
import com.act.common.CnsleeTFCBTTask;

public class TFCBTStageMilestoneModel extends AbstractTableModel{

	String [] columns = new String[] {"Task" ,
										"Completed",
										"Date Completed",
										"Notes",	
									 };
	
	Vector<CnsleeTFCBTMilestoneRowObj> vData = new Vector<CnsleeTFCBTMilestoneRowObj>(); 
	
	public String getColumnName(int columnIndex) {
		// TODO Auto-generated method stub
		
		return columns[columnIndex];
	}
	public int getRowCount() {
		// TODO Auto-generated method stub
		return vData.size();
	}

	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columns.length;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		String value = "";
		
		try{
			CnsleeTFCBTMilestoneRowObj cnsleeMileStoneRowObj = vData.get(rowIndex);
			
			if (cnsleeMileStoneRowObj == null){
				return value;
			}
		
		
			switch (columnIndex) {
			
				case 0:
					return cnsleeMileStoneRowObj.getMilestoneObj().getTitle();
				case 1:
					return cnsleeMileStoneRowObj.getCnsleeMilestoneObj().isMilestoneCompleted();
				case 2:
					return cnsleeMileStoneRowObj.getCnsleeMilestoneObj().getDateCompleted();
				case 3:
					return cnsleeMileStoneRowObj.getCnsleeMilestoneObj().getMilestoneNotes();
				default:
					break ;
				
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return value;
	}

	public CnsleeTFCBTMilestoneRowObj getRowObject(int row){
		if (row > -1){
			if (vData != null && vData.size() > row){
				return vData.get(row);
			}
		}
		
		return null;
	}
	

	public void setList(Vector<CnsleeTFCBTMilestoneRowObj> vData){
		if (vData == null)
			return;
		
		this.vData = vData;
		fireTableDataChanged();
	}

}
