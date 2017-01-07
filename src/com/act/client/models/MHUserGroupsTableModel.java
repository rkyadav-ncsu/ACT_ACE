package com.act.client.models;

import java.util.Vector;

import javax.swing.event.TableModelListener;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.act.common.Organisation;
import com.act.common.OrgnIDNameObj;
import com.act.common.User;
import com.act.common.GroupsObj;

public class MHUserGroupsTableModel extends AbstractTableModel{
		

	String [] columns = new String[] {"Group Name",
							"Description"
							};

	
//	Vector<OrgnIDNameObj> vOrgn;// = new Vector<Counsellee>(); 
	Vector<GroupsObj> vUserGroups;// = new Vector<Counsellee>(); 
	
	public MHUserGroupsTableModel(){
		super();
	}
	
	
	public void setList (Vector<GroupsObj> vUserGrp){
		this.vUserGroups = vUserGrp;
		System.out.println(vUserGrp.size() + " Size of the vecotr containing the User groups");
		fireTableDataChanged();
		//fireTableStructureChanged();
		fireTableRowsInserted(0, 2);
		
	}
	
	public Object getValueAt(int rowIndex, int columnIndex) {
		String value = "";
		
		try{
//			 OrgnIDNameObj orgnIDN = vOrgn.get(rowIndex);
			 GroupsObj userGrpInfo = vUserGroups.get(rowIndex);
			
			if (userGrpInfo == null){
				return value;
			}
		
		
			switch (columnIndex) {
			
				case 0:
					System.out.println("Group Name :" + userGrpInfo.getGroupName());
					return userGrpInfo.getGroupName();
				case 1:
					return userGrpInfo.getGroupDesc();
				default:
					break;
				
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return value;
	}
	
	public int getRowCount() {
		if (vUserGroups == null)
			return 0;
		return vUserGroups.size();
	}

	public int getColumnCount() {
		// TODO Auto-generated method stub
		// return 3;
		return columns.length;
	}

	public String getColumnName(int columnIndex) {
		// TODO Auto-generated method stub
		
		return columns[columnIndex];
	}


	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		
	}
	
	public GroupsObj getRowObject(int row){
		if (row > -1){
			if (vUserGroups != null && vUserGroups.size() > row){
				return vUserGroups.get(row);
			}
		}
		
		return null;
	}
	
	
	public Vector getRowObjects(){
		if (vUserGroups != null && vUserGroups.size() > 0){
			return vUserGroups;
		}else
		
			return null;
	}
	
	
	
}

