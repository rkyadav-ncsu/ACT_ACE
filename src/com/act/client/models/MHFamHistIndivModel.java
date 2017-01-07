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
			"Comments",
			"Case Number"};										// ND edited 21st Apr 16
			
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
				case 7:
					return indiv.getCaseNumber();								// ND added 21st Apr 16
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
	public  void setRelTableValues(Vector<CounseleeRelativeIndivObj> vReln) {
		if(vReln == null)
			return;
		
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
	
	// ND added 26th Apr 16
	public CounseleeRelativeIndivObj getRowObject(int row){
			if (row > -1){
				if (vData != null && vData.size() > row){
					return vData.get(row);
				}
			}
			
			return null;
	}
	
	// ND added 28th Apr 16
	public void delTableRows(Vector vRelDel, int[] sRows) {
		Vector vFamRel = null;
		
//		for(int i = 0; i < sRows.length; i++){
//			vFamRel.add(vRelDel.elementAt(i));
//		}
		vData.removeAll(vRelDel);
		fireTableStructureChanged();
		
	}
	
	// ND added 28th Apr 16
	public void delTableRow( int sRow) {
		Vector vFamRel = null;
		
		vData.removeElementAt(sRow);
		fireTableStructureChanged();
		
	}
		
}
