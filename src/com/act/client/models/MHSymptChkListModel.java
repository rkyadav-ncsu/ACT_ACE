package com.act.client.models;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import com.act.common.Counsellee;
import com.act.common.MHSymptChkListObj;

public class MHSymptChkListModel extends AbstractTableModel{

	String [] columns = new String[] {"Date" ,
							"Check List Type",
							"Description",
							"Counsellor",
							"Total Score"};
							
	Vector<MHSymptChkListObj> vSymptChkLists;// = new Vector<Counsellee>(); 
	
	public MHSymptChkListModel(){
		super();
		vSymptChkLists = new Vector<MHSymptChkListObj>();
	
	}
	
	public void setList (Vector<MHSymptChkListObj> vSymptChkLists){
		this.vSymptChkLists = vSymptChkLists;
//		System.out.println(vSymptChkLists.size());
		fireTableDataChanged();
		//fireTableStructureChanged();
//		fireTableRowsInserted(0, 2);
		System.out.println("sympt chk list setList  ");

	}
	
	public Object getValueAt(int rowIndex, int columnIndex) {
		String value = "";
		
		try{
			MHSymptChkListObj chkListObj= vSymptChkLists.get(rowIndex);
			
			if (chkListObj == null){
				System.out.println("sympt chk list 1");
				return value;
			}
		
		
			switch (columnIndex) {
			
				case 0:
					return chkListObj.getChkListDate();
				case 1:
					return chkListObj.getChkListType();
				case 2:
					return chkListObj.getChkListDesc();
				case 3:
					return chkListObj.getChkListCounsellor();
				case 4:
					return chkListObj.getChkListTotalScore();
				default:
					break;
				
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return value;
	}
	
	public int getRowCount() {
		if (vSymptChkLists  == null)
			return 0;
		return vSymptChkLists.size();
	}

	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 5;
	}

	public String getColumnName(int columnIndex) {
		// TODO Auto-generated method stub
		
		return columns[columnIndex];
	}


	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		
	}
	
	public MHSymptChkListObj getRowObject(int row){
		if (row > -1){
			if (vSymptChkLists != null && vSymptChkLists.size() > row){
				return vSymptChkLists.get(row);
			}
		}
		
		return null;
	}
	
	
	public Vector getRowObjects(){
		if (vSymptChkLists != null && vSymptChkLists.size() > 0){
			return vSymptChkLists;
		}else
		
			return null;
	}
	
	public void addRow(MHSymptChkListObj obj){
		vSymptChkLists.add(obj);
		fireTableDataChanged();
	}

	public void removeRow(MHSymptChkListObj obj){
		vSymptChkLists.remove(obj);
		fireTableDataChanged();
	}
	
	public void updateRow(MHSymptChkListObj obj){
		fireTableDataChanged();
	}

			
}
