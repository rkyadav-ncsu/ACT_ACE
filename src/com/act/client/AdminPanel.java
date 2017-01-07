package com.act.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.net.URL;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class AdminPanel extends MHPanel {
	
//	JPanel panelLeft;
	JPanel panelTop;
	JPanel panelTopTitle;
	JPanel panelCenter;
	JPanel panelButtons;
	JPanel panelBottom;
	JPanel panelGeneral;
//	JButton btnLeftGeneral;
//	JButton btnLeftOrgn;								// ND added on 26th Aug 16
	
	JTabbedPane tabPaneAdmin;
	
	MHPanelObserver panelObserver;
	private URL codebase;

	public AdminPanel(URL codebase){
		this.codebase = codebase;
		setSize(1000,850);
		initUI();

	}
	
	private void initUI(){
		try{
			setLayout(new BorderLayout());
			
			//create and add Top Panel
			panelTop = new JPanel();
			panelTop.setLayout(new BorderLayout());
			panelTop.setBackground(Color.yellow);
			add(panelTop, BorderLayout.NORTH);
		
			//Top title panel
			panelTopTitle = new JPanel();
			panelTopTitle.setLayout(new BorderLayout());
			panelTopTitle.setPreferredSize(new Dimension(100,40));
			panelTopTitle.setBackground(new Color(180,230,180));
			panelTop.add(panelTopTitle, BorderLayout.NORTH);
			
			//title label
			JLabel lblTopTitle = new JLabel("This is the Administration Panel", JLabel.CENTER);
			panelTopTitle.add(lblTopTitle, BorderLayout.CENTER);

			//center panel
			panelCenter = new JPanel();
			add(panelCenter, BorderLayout.CENTER);
			panelCenter.setLayout(new BorderLayout());
			
			tabPaneAdmin = new JTabbedPane();
			panelCenter.add(tabPaneAdmin);
			
			tabPaneAdmin.addTab("General", new PanelGeneral());
			tabPaneAdmin.addTab("Organisations", new PanelOrganisation());
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	

	@Override
	public void setMHPanelObserver(MHPanelObserver obs) {
		panelObserver = obs;
		
	}

}
