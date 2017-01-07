package com.act.client.models;

import java.util.Vector;


import javax.swing.table.AbstractTableModel;

import com.act.common.Pincode;

public class MHZipTableModel extends AbstractTableModel{

		String [] columns = new String[] {"Pin ID" ,
								"Post Office",
								"Pincode",
								"City District",
								"State"};
								
		Vector<Pincode> vZipInfo;				// = new Vector<Counsellee>(); 
		
		public MHZipTableModel(){
			super();
		}
		
		public void setList (Vector<Pincode> vPinInfo){
			this.vZipInfo = vPinInfo;
			System.out.println(vPinInfo.size());
			fireTableDataChanged();
			//fireTableStructureChanged();
			fireTableRowsInserted(0, 2);
			
		}
		
		public Object getValueAt(int rowIndex, int columnIndex) {
			String value = "";
			
			try{
				Pincode pin = vZipInfo.get(rowIndex);
				
				if (pin == null){
					return value;
				}
			
				switch (columnIndex) {
				
					case 0:
						return pin.getPinID();
					case 1:
						return pin.getPinPO();
					case 2:
//						System.out.println("couns.getCaseNumber(): " + couns.getCaseNumber());
						return pin.getPinCode();
					case 3:
						return pin.getPinCitDis();
					case 4:
//						obj = return couns.;
						return pin.getPinState();
					default:
						break;
				}
			}catch (Exception e){
				e.printStackTrace();
			}
			return value;
		}
		
		public int getRowCount() {
			if (vZipInfo == null)
				return 0;
			return vZipInfo.size();
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
		
		public Pincode getRowObject(int row){
			if (row > -1){
				if (vZipInfo != null && vZipInfo.size() > row){
					return vZipInfo.get(row);
				}
			}
			
			return null;
		}
		
		public Vector getRowObjects(){
			if (vZipInfo != null && vZipInfo.size() > 0){
				return vZipInfo;
			}else
			
				return null;
		}
		
	}
