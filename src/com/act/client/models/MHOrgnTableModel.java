package com.act.client.models;

import java.util.Vector;

import javax.swing.event.TableModelListener;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.act.common.Organisation;
import com.act.common.OrgnIDNameObj;

public class MHOrgnTableModel extends AbstractTableModel{
		

	String [] columns = new String[] {"Organization ID" ,
							"Name",
							"City",
							"Orgn Type",
							"Services",
							};

	
//	Vector<OrgnIDNameObj> vOrgn;// = new Vector<Counsellee>(); 
	Vector<Organisation> vOrgn;// = new Vector<Counsellee>(); 
	
	public MHOrgnTableModel(){
		super();
	}
	
	
	public void setList (Vector<Organisation> vOrgIDN){
		this.vOrgn = vOrgIDN;
		System.out.println(vOrgIDN.size() + " Size of the vecotr containing the Organisation details");
		fireTableDataChanged();
		//fireTableStructureChanged();
		fireTableRowsInserted(0, 2);
		
	}
	
	public Object getValueAt(int rowIndex, int columnIndex) {
		String value = "";
		
		try{
//			 OrgnIDNameObj orgnIDN = vOrgn.get(rowIndex);
			 Organisation orgnIDN = vOrgn.get(rowIndex);
			
			if (orgnIDN == null){
				return value;
			}
		
		
			switch (columnIndex) {
			
				case 0:
					return orgnIDN.getOrgnId();
				case 1:
					System.out.println("OrgnName :" + orgnIDN.getOrgnName());
					return orgnIDN.getOrgnName();
				case 2:
					return orgnIDN.getAddCity();
				case 3:
					System.out.println("Orgn Type : " + orgnIDN.getOrgnType());
					return orgnIDN.getOrgnType();
				case 4:
					return orgnIDN.getServiceTypes();
				default:
					break;
				
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return value;
	}
	
	public int getRowCount() {
		if (vOrgn == null)
			return 0;
		return vOrgn.size();
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
	
	public Organisation getRowObject(int row){
		if (row > -1){
			if (vOrgn != null && vOrgn.size() > row){
				return vOrgn.get(row);
			}
		}
		
		return null;
	}
	
	
	public Vector getRowObjects(){
		if (vOrgn != null && vOrgn.size() > 0){
			return vOrgn;
		}else
		
			return null;
	}
	
	
}

