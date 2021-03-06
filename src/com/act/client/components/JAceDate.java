package com.act.client.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JAceDate extends JPanel{
	
	JComboBox<String> cbDay;
	JComboBox<String> cbMonth;
	JTextField txtYear;
	
	JLabel labelSlash1;
	JLabel labelSlash2;
	Dimension dimCB = new Dimension(40,19);
	
	public JAceDate(){
		
		//set the layout as flow layout
		setLayout(new FlowLayout(FlowLayout.LEADING,5,0));
		
		cbDay = new JComboBox<String>();
//		cbDay.setBackground(Color.white);
		cbDay.setPreferredSize(dimCB);
		cbDay.addItem(" ");
		for (int i = 0; i < 31; i++) {
			cbDay.addItem(new Integer(i+1).toString());
		}
		add(cbDay);
		
		labelSlash1 = new JLabel("/");
		add(labelSlash1);
		
		cbMonth = new JComboBox<String>();
		cbMonth.setPreferredSize(dimCB);
		cbMonth.addItem(" ");
		for (int i = 1; i < 13; i++) {
			cbMonth.addItem(new Integer(i).toString());
		}
		add(cbMonth);
		
		labelSlash2 = new JLabel("/");
		add(labelSlash2);
		
		txtYear = new JTextField(3);
		txtYear.setPreferredSize(new Dimension(35,20));
		add(txtYear);

		cbDay.setFont(txtYear.getFont());
		cbMonth.setFont(txtYear.getFont());
		
		//set curr date
		Calendar cal = Calendar.getInstance();
		cbDay.setSelectedIndex(cal.get(Calendar.DAY_OF_MONTH));
		cbMonth.setSelectedIndex(cal.get(Calendar.MONTH)+1);
		txtYear.setText(new Integer(cal.get(Calendar.YEAR)).toString());
		
	}
	
	public void setEditable(boolean b){
		txtYear.setEditable(b);
		cbDay.setEditable(b);
		cbMonth.setEditable(b);
	}
	/**
	 * Returns the date in the format YYYYMMDD
	 * @return
	 */
	public String getDate(){
		
		String sDate = null;
		
		try{
			sDate = String.valueOf(getYear());
			
			int month = cbMonth.getSelectedIndex();
			if (month < 10){
				sDate += "0" +String.valueOf(month);
			}else{
				sDate += String.valueOf(month);
			}
			
			int day = cbDay.getSelectedIndex();
			if (day < 10){
				sDate += "0" +String.valueOf(day);
			}else{
				sDate += String.valueOf(day);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
			sDate = null;
		}
		return sDate;
		
	}
	
	/**
	 * Set the date into the component
	 * 
	 * 
	 * @param sDate  string should be in the format yyyymmdd
	 */
			
	public void setDate(String sDate){
		int m_int, d_int;
		if (sDate == null || sDate.trim().equals("") || sDate.trim().length() != 8){
			txtYear.setText("");
			cbDay.setSelectedIndex(0);
			cbMonth.setSelectedIndex(0);
			return;
		}
		
		try{
			
			txtYear.setText(sDate.substring(0,4));
//			cbMonth.setSelectedItem(new Integer(sDate.substring(4,6)));								// ND commented 15th Apr 16
			m_int = Integer.valueOf(sDate.substring(4,6));											// ND added 15 Apr 16
			cbMonth.setSelectedItem(String.valueOf(m_int));											// ND added 15 Apr 16
			System.out.println("cbMonth: " + sDate.substring(4,6) + "    " + sDate + " two digits " + String.valueOf(m_int));
//			cbDay.setSelectedItem(new Integer(sDate.substring(6,8)));								// ND commented 15 Apr 16
			d_int = Integer.valueOf(sDate.substring(6,8));
			cbDay.setSelectedItem(Integer.toString(d_int));
			
		}catch(Exception e){
			e.printStackTrace();
			
			//reset the values
			txtYear.setText("");
			cbDay.setSelectedIndex(0);
			cbMonth.setSelectedIndex(0);
		}
	}
	
	private int getYear(){
		return Integer.parseInt(txtYear.getText());
	}

	public static void main(String[] args){
		
		JFrame frame = new JFrame();
		frame.add(new JAceDate());
		
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setVisible(true);
		
	}
}
