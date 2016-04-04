package com.act.client;

import javax.swing.JComboBox;

public class JComboBoxSymptoms extends JComboBox {
	
	public JComboBoxSymptoms(){
		super();
		populateValues();
	}
	
	private void populateValues(){
		addItem(new Integer (0));
		addItem(new Integer (1));
		addItem(new Integer (2));
		addItem(new Integer (3));
	}

	public int getScore(){
		Integer val = (Integer)getSelectedItem();
		return val.intValue();
	}
	
	public int getSelectedScore(){
		Integer val = (Integer) getSelectedItem();
		return val.intValue();
	}
}
