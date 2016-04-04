package com.act.client.models;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import com.act.common.Counsellee;
import com.act.common.SessionObj;

public class MHSessionsTableModel extends AbstractTableModel{

	String [] columns = new String[] {"Session Date",
							"Therapy Name",
							"Status",
							"Comments"};
							
	Vector<SessionObj> vSessions;// = new Vector<Counsellee>(); 
	
	public MHSessionsTableModel(){
		super();
	}
	
	public void setList (Vector<SessionObj> vSessions){
		this.vSessions = vSessions;
		System.out.println(vSessions.size());
		fireTableDataChanged();
		//fireTableStructureChanged();
		fireTableRowsInserted(0, 2);
		
	}
	
	public Object getValueAt(int rowIndex, int columnIndex) {
		String value = "";
		
		try{
			SessionObj session = vSessions.get(rowIndex);
			
			if (session == null){
				return value;
			}
		
		
			switch (columnIndex) {
			
				case 0:
			
					return session.getSessionDate();
				case 1:
					return session.getTherapyName();
				case 2:
					return session.getStatus();
				case 3:
					return session.getComments();
				default:
					break;
				
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return value;
	}
	
	public int getRowCount() {
		if (vSessions == null)
			return 0;
		return vSessions.size();
	}

	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 4;
	}

	public String getColumnName(int columnIndex) {
		// TODO Auto-generated method stub
		
		return columns[columnIndex];
	}


	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		
	}
	
	public SessionObj getRowObject(int row){
		if (row > -1){
			if (vSessions != null && vSessions.size() > row){
				return vSessions.get(row);
			}
		}
		
		return null;
	}
	
	
	public Vector getRowObjects(){
		if (vSessions != null && vSessions.size() > 0){
			return vSessions;
		}else
		
			return null;
	}
	
	

}
