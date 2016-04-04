package com.act.client.models;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import com.act.common.Counsellee;
import com.act.common.CounseleeRelativeIndivObj;

public class MHFamHistIndivModel extends AbstractTableModel{
	
	String [] columns = new String[] {"Relative's Name" ,
			"Relationship",
			"Rel Strength",
			"Profession",
			"Age",
			"Aware of Victim's situation",
			"Comments"};
			
	Vector<CounseleeRelativeIndivObj> vData= new Vector<CounseleeRelativeIndivObj>(); 

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
			CounseleeRelativeIndivObj indiv = vData.get(rowIndex);
			
			if (indiv == null){
				return value;
			}
		
		
			switch (columnIndex) {
			
				case 0:
					return indiv.getRelName();
				case 1:
					return indiv.getRelationship();
				case 2:
					return indiv.getRelStrength();
				case 3:
					return indiv.getRelProfession();
				case 4:
					return indiv.getRelAge();
				case 5:
					return indiv.isRelAwareOfVictimsSituation();
				case 6:
					return indiv.getRelComments();
				default:
					break ;
				
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return value;
	}
	
	public void addRowObject(CounseleeRelativeIndivObj obj){
		if (obj == null)
			return;
		
		if (vData == null)
			vData = new Vector();
		
		vData.add(obj);
		
		fireTableStructureChanged();
	}

	public Vector getRowObjects(){
		if (vData != null && vData.size() > 0){
			return vData;
		}else
		
			return null;
	}
	
	// ND added 12th Mar 16
	// To transfer the values in the vector (read from the database) to a table
	public  void setValues(Vector<CounseleeRelativeIndivObj> vReln) {
		int rowz = vReln.size();
		CounseleeRelativeIndivObj cnslReln = new CounseleeRelativeIndivObj();
		
		if (rowz > 0) {
		for (int i = 0; i < rowz; i++) {
			cnslReln = vReln.get(i);
			addRowObject(cnslReln);
			System.out.println(cnslReln.getRelName());
		} 
		} else System.out.println("No data to transfer to table");
		
		
		
		
	}
}
