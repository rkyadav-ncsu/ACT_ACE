package com.act.client.models;

import java.util.Vector;

import javax.swing.event.TableModelListener;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.act.common.Organisation;
import com.act.common.OrgnIDNameObj;
import com.act.common.User;

public class MHUsersTableModel extends AbstractTableModel{
		

	String [] columns = new String[] {"Users ID" ,
							"Name",
							"DOB",
							"Designation",
							"Phone",
							"Email"
							};

	
//	Vector<OrgnIDNameObj> vOrgn;// = new Vector<Counsellee>(); 
	Vector<User> vUsers;// = new Vector<Counsellee>(); 
	
	public MHUsersTableModel(){
		super();
	}
	
	
	public void setList (Vector<User> vUser){
		this.vUsers = vUser;
		System.out.println(vUser.size() + " Size of the vecotr containing the Users details");
		fireTableDataChanged();
		//fireTableStructureChanged();
		fireTableRowsInserted(0, 2);
		
	}
	
	public Object getValueAt(int rowIndex, int columnIndex) {
		String value = "";
		
		try{
//			 OrgnIDNameObj orgnIDN = vOrgn.get(rowIndex);
			 User userInfo = vUsers.get(rowIndex);
			
			if (userInfo == null){
				return value;
			}
		
		
			switch (columnIndex) {
			
				case 0:
					return userInfo.getUserID();
				case 1:
					System.out.println("User :" + userInfo.getfName());
					return nullSpace(userInfo.getlName()) + nullSpace(userInfo.getfName()) +  nullSpace(userInfo.getmName());
				case 2:
					return userInfo.getDob();
				case 3:
					System.out.println("Designation : " + userInfo.getDesig());
					return nullSpace(userInfo.getDesig());
				case 4:
					return nullSpace(userInfo.getMobile()) + nullSpace(userInfo.getPhone());
				case 5:
					return nullSpace(userInfo.getEmail());
				default:
					break;
				
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return value;
	}
	
	public int getRowCount() {
		if (vUsers == null)
			return 0;
		return vUsers.size();
	}

	public int getColumnCount() {
		// TODO Auto-generated method stub
		// return 6;
		return columns.length;
	}

	public String getColumnName(int columnIndex) {
		// TODO Auto-generated method stub
		
		return columns[columnIndex];
	}


	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		
	}
	
	public User getRowObject(int row){
		if (row > -1){
			if (vUsers != null && vUsers.size() > row){
				return vUsers.get(row);
			}
		}
		
		return null;
	}
	
	
	public Vector getRowObjects(){
		if (vUsers != null && vUsers.size() > 0){
			return vUsers;
		}else
		
			return null;
	}
	
	public String nullSpace(String sChkNull){
		
		if(sChkNull == null)
			sChkNull = "";
		else
			sChkNull = sChkNull + " ";
		return sChkNull;
	}
	
	
}

