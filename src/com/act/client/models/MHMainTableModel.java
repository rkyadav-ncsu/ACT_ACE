package com.act.client.models;

import java.util.Vector;

import javax.swing.event.TableModelListener;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.act.common.Counsellee;
import com.act.common.PersonName;
import com.mysql.jdbc.UpdatableResultSet;

public class MHMainTableModel extends AbstractTableModel{

	String [] columns = new String[] {"Partner Organization" ,
							"Name",
							"Case Number",
							"DOB",
							"Gender"};
							
	Vector<Counsellee> vCounselee;// = new Vector<Counsellee>(); 
	
	public MHMainTableModel(){
		super();
	}
	
	public void setList (Vector<Counsellee> vCounselee){
		this.vCounselee = vCounselee;
		System.out.println(vCounselee.size());
		fireTableDataChanged();
		//fireTableStructureChanged();
		fireTableRowsInserted(0, 2);
		
	}
	
	public Object getValueAt(int rowIndex, int columnIndex) {
		String value = "";
		
		try{
			Counsellee couns = vCounselee.get(rowIndex);
			
			if (couns == null){
				return value;
			}
		
		
			switch (columnIndex) {
			
				case 0:
			
					return couns.getParentOrg();
				case 1:
					return couns.getName().getFormattedName();
				case 2:
//					System.out.println("couns.getCaseNumber(): " + couns.getCaseNumber());
					return couns.getCaseNumber();
				case 3:
					return"";
				case 4:
//					obj = return couns.;
					return "";
				default:
					break;
				
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return value;
	}
	
	public int getRowCount() {
		if (vCounselee == null)
			return 0;
		return vCounselee.size();
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
	
	public Counsellee getRowObject(int row){
		if (row > -1){
			if (vCounselee != null && vCounselee.size() > row){
				return vCounselee.get(row);
			}
		}
		
		return null;
	}
	
	
	public Vector getRowObjects(){
		if (vCounselee != null && vCounselee.size() > 0){
			return vCounselee;
		}else
		
			return null;
	}
	
	
}
