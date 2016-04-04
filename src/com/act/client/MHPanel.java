package com.act.client;

import javax.swing.JPanel;

public abstract class MHPanel extends JPanel{
	private MHPanelObserver panelObs;
	
	abstract public void setMHPanelObserver(MHPanelObserver obs);
	public MHPanelObserver  getMHPanelObserver(){
		return panelObs;
	}

}
