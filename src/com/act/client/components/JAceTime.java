package com.act.client.components;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JAceTime extends JPanel{
	
	JComboBox<String> cbHours;
	JComboBox<String> cbMinutes;
	JComboBox<String> cbAM;
	
	JLabel labelColon, labelMins;
	
	Dimension dimCB = new Dimension(45,22);
	
	public JAceTime(){
		initUI();
	}
	
	private void initUI(){
		try{
			
			//set the layout as flow layout
			setLayout(new FlowLayout(FlowLayout.LEADING,5,0));

			cbHours = new JComboBox<String>();
			cbHours.addItem(" ");
			for (int i = 1; i < 13; i++) {
				if (i <10)
					cbHours.addItem("0" + String.valueOf(i));
				else
					cbHours.addItem(String.valueOf(i));
			}
			add(cbHours);
			cbHours.setPreferredSize(dimCB);		

			labelColon = new JLabel(":");
			add(labelColon);
			
			cbMinutes = new JComboBox<String>();
			cbMinutes.addItem(" ");
			for (int i = 1; i < 61; i++) {
				if (i<10)
					cbMinutes.addItem("0" + String.valueOf(i));
				else
					cbMinutes.addItem(String.valueOf(i));
			}
			add(cbMinutes);
			cbMinutes.setPreferredSize(dimCB);
			
			cbAM = new JComboBox<String>();
			cbAM.addItem("AM");
			cbAM.addItem("PM");
			cbAM.setPreferredSize(dimCB);
			add(cbAM);
			
			cbHours.setSelectedItem(" ");
			cbMinutes.setSelectedItem(" ");
			cbAM.setSelectedIndex(0);
			
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	
	/**
	 * Returns the time in HHMM format
	 * @return
	 */
	public String getTime(){
		
		String sHrs = (String) cbHours.getSelectedItem();
		
		if (sHrs == null || sHrs.equals(" "))
			return "";
		
		String sMins = (String) cbMinutes.getSelectedItem();
		if (sMins == null || sMins.equals(" "))
			return "";
		
		String sAM = (String) cbAM.getSelectedItem();
		
		int hrs = Integer.parseInt(sHrs);
		
		if (sAM.equals("PM"))
			hrs += 12;
		
		return (String.valueOf(hrs) + sMins);
		
	}

	/**
	 * Sets the time in the component
	 * sTime of the format HHMM
	 * 
	 */
	public void setTime(String sTime){
		
		if (sTime == null || sTime.length() < 4)
			return;
		System.out.println("sTime" + sTime);
		
		cbAM.setSelectedItem("AM");
		String sHrs = sTime.substring(0,2);
		int hrs = Integer.parseInt(sHrs);
		if (hrs > 12){
			hrs -= 12;
			cbAM.setSelectedItem("PM");
		}
		sHrs = String.valueOf(hrs);
		if (hrs <10)
			sHrs = "0" + sHrs;
		System.out.println("shrs" + sHrs);
		cbHours.setSelectedItem(sHrs);
		
		String sMins = sTime.substring(2,4);
		int mins = Integer.parseInt(sMins);
//		if (mins <10){
//			sMins = "0" + sMins;
//		}
		cbMinutes.setSelectedItem(sMins);
	}
}
