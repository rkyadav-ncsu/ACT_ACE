package com.act.client.util;

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
		setLayout(new FlowLayout());
		
		cbDay = new JComboBox<String>();
//		cbDay.setBackground(Color.white);
		cbDay.setPreferredSize(dimCB);
		for (int i = 0; i < 31; i++) {
			cbDay.addItem(new Integer(i+1).toString());
		}
		add(cbDay);
		
		labelSlash1 = new JLabel("/");
		add(labelSlash1);
		
		cbMonth = new JComboBox<String>();
		cbMonth.setPreferredSize(dimCB);
		for (int i = 0; i < 12; i++) {
			cbMonth.addItem(new Integer(i+1).toString());
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
		cbDay.setSelectedIndex(cal.get(Calendar.DAY_OF_MONTH)-1);
		cbMonth.setSelectedIndex(cal.get(Calendar.MONTH));
		txtYear.setText(new Integer(cal.get(Calendar.YEAR)).toString());
		
	}
	
	/**
	 * Returns the date in the format YYYYMMDD
	 * @return
	 */
	public String getDate(){
		
		String sDate = null;
		
		try{
			
			sDate = new Integer(getYear()).toString();
			sDate += cbMonth.getSelectedItem().toString();
			sDate += cbDay.getSelectedItem().toString();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return sDate;
		
	}
	
	public int getYear(){
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
