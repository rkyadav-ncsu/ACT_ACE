package com.act.client.test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import com.act.client.models.MHMainTableModel;
import com.act.common.Counsellee;
import com.act.common.PersonName;

public class TestFrame extends JFrame implements ActionListener {
	JTable tblMHData;
	MHMainTableModel modelMH;
	
	JButton btnShow;
	private int val;
	
	public TestFrame(){
		
		setSize(1000,500);
		setLayout(new BorderLayout());
		
		btnShow = new JButton("Show List");
		SwingUtilities.invokeLater(new Runnable() {
			
			public void run() {
				// TODO Auto-generated method stub
				modelMH = new MHMainTableModel();
				Vector v = new Vector<Counsellee>();
				Counsellee c = new Counsellee();
				c.setCaseNumber("IJM20120909-1234");
				c.setParentOrg("IJM");
				c.setName(new PersonName("Mary"));
				
				v.add(c);
				modelMH.setList(v);
				tblMHData = new JTable(modelMH);
				
				JScrollPane scrollPaneTbl = new JScrollPane(tblMHData);
				tblMHData.setFillsViewportHeight(true);
//				scrollPaneTbl.setv
				scrollPaneTbl.setPreferredSize(new Dimension (1000,500));
				tblMHData.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
				add(scrollPaneTbl, BorderLayout.CENTER);
//				scrollPaneTbl.setAlignmentX(CENTER_ALIGNMENT);
				scrollPaneTbl.setAutoscrolls(true);
				scrollPaneTbl.setBackground(Color.BLUE);

				
				add(btnShow, BorderLayout.SOUTH);
				
			}
		});
		btnShow.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		Vector v = new Vector<Counsellee>();
		Counsellee c = new Counsellee();
		c.setCaseNumber("IJM20120909-1234");
		c.setParentOrg("IJM");
		c.setName(new PersonName("Mary"));
		
		v.add(c);
		modelMH.setList(v);
//		modelMH.fireTableCellUpdated(row, column)
		
repaint();
		
	}

}

