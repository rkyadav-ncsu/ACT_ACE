package com.act.client;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.Stack;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.JTableHeader;
//import javax.swing.JTable;

import com.act.client.models.MHMainTableModel;
import com.act.common.Counsellee;
import com.act.common.SwingUtils;
import java.util.Hashtable;

public class MentalHealthMainPanel extends JPanel implements MHPanelObserver{
	
	private URL codebase;
	private MentalHealthListPanel mhListPanel;
	private Stack<JPanel> stackPanels;
	
	public MentalHealthMainPanel(URL codebase){
		this.codebase = codebase;
		setSize(1200,850);
		
		stackPanels = new Stack<JPanel>();
		initUI();
	}
	
	private void initUI(){
		setLayout(new BorderLayout());
		mhListPanel = new MentalHealthListPanel(codebase);
		mhListPanel.setMHPanelObserver(this);
		addPanel(mhListPanel);
	}

	public void addPanel(JPanel panel) {
		if (!stackPanels.isEmpty()){
			JPanel lastPanel = stackPanels.lastElement();
			if (lastPanel != null){
				remove(lastPanel);
				System.out.println("removed last panel....");
			}
		}
		
		add(panel, BorderLayout.CENTER);
		stackPanels.push(panel);
		revalidate();
		repaint();
	}

	public void removePanel() {
		JPanel panelToRemove = stackPanels.pop();
		remove(panelToRemove);
		add(stackPanels.lastElement(), BorderLayout.CENTER);
		revalidate();
		repaint();
	}
	
}


