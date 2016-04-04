package com.act.client.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;
import javax.swing.JTextField;

import com.act.common.SwingUtils;


public class JAceList extends JPanel implements ActionListener{
	
	private int height = 26;
	
	JTextField txtValues;
	JButton btnDownArrow;
	JPopupMenu popup;
	Vector <JCheckBoxMenuItem> vMenuItems;
	
	public JAceList(){
		initUI();
	}
	
	private void initUI(){
//		FlowLayout fLayout = new FlowLayout(); 
//		setLayout(fLayout);
//		fLayout.setHgap(0);
		setLayout(new GridBagLayout());
		
		txtValues = new JTextField();
		txtValues.setEditable(false);
		txtValues.setPreferredSize(new Dimension(200,height));
		add(txtValues,SwingUtils.getConstraints(0, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.HORIZONTAL, 
				0, 0, 0, 0));
//		add(txtValues);
		
		btnDownArrow = new JButton("v");
		btnDownArrow.setPreferredSize(new Dimension(40,height+1));
		btnDownArrow.addActionListener(this);
		add(btnDownArrow,SwingUtils.getConstraints(0, 1, 1, 1,1, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 0, 0, 0));
//		add(btnDownArrow);
		
		popup = new JPopupMenu();
		setPopupWidth();
		
//		setBackground(Color.red);
//		popup.add(new JScrollBar());
	}

	private void setPopupWidth(){
		popup.setPreferredSize(new Dimension((int)txtValues.getPreferredSize().getWidth()+40, 150));
	}
	
	/**
	 * Sets the list items
	 * @param vItems
	 */
	public void setItems(Vector vItems){
		if (vItems == null)
			return;
	
		vMenuItems = new Vector();
		for (int i = 0; i < vItems.size(); i++) {
			JCheckBoxMenuItem menuItem = new JCheckBoxMenuItem(vItems.elementAt(i).toString());
			popup.add(menuItem);
			vMenuItems.add(menuItem);
			menuItem.addActionListener(this);
		}
	}
	
	/**
	 * Sets the list items
	 * @param vItems
	 */
	public void setItems(Object[] items){
		if (items == null)
			return;
	
		vMenuItems = new Vector();
		for (int i = 0; i < items.length; i++) {
			JCheckBoxMenuItem menuItem = new JCheckBoxMenuItem(items[i].toString());
			popup.add(menuItem);
			vMenuItems.add(menuItem);
			menuItem.addActionListener(this);
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		Object objSource = e.getSource();
		
		if (objSource == btnDownArrow){
			if (!popup.isVisible()) {
	            Point p = txtValues.getLocationOnScreen();
	            popup.setInvoker(btnDownArrow);
	            popup.setLocation((int) p.getX(),
	                    (int) p.getY() + btnDownArrow.getHeight());
	            popup.setVisible(true);
	        } else {
	        	popup.setVisible(false);
	        }
		}else if (vMenuItems != null && vMenuItems.contains(objSource) ){
			JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem)objSource;
			setText();
			popup.setVisible(true);
		}
		
		
	}
	
	private void setText(){
		if (vMenuItems == null)
			return;
		
		StringBuffer sb = new StringBuffer();
		boolean bComma = false;
		for (int i = 0; i < vMenuItems.size(); i++) {
			JCheckBoxMenuItem item =vMenuItems.elementAt(i);
			
			if (item.isSelected()){
				if(bComma)
					sb.append(",");
				sb.append(item.getText());
				bComma = true;
			}
		}
		txtValues.setText(sb.toString());
	}
	
	/**
	 * Sets the values back into the list
	 * @param values  format shd be "value1,value2,.."
	 */
	public void setText(String values){
		if (values == null || values.length() <=0)
			return;
		
		StringTokenizer tok = new StringTokenizer(values, ",");
		
		Vector vValues = new Vector();
		while (tok.hasMoreTokens()){
			String val = tok.nextToken();
			vValues.add(val);
		}
		
		if (vValues.isEmpty())
			return;
		
		for (int i = 0; i < vMenuItems.size(); i++) {
			JCheckBoxMenuItem item =vMenuItems.elementAt(i);
			
			if(vValues.contains(item.getText())){
				item.setSelected(true);
			}
		}
		
		txtValues.setText(values);
	}
	
	/**
	 * Sets the width of the component
	 * @param n
	 */
	public void setPreferredWidth(int n){
		txtValues.setPreferredSize(new Dimension(n, 24));
		setPopupWidth();
	}
	
	//Test code
	public static void main(String[] args){
		
		JFrame frame = new JFrame();
		final JAceList lst = new JAceList();
		Vector v = new Vector();
		v.addElement("abc");
		v.addElement("def");
		v.addElement("asd");
		v.addElement("bnm");
		v.addElement("rty");
		v.addElement("rty");
		v.addElement("rty");
		v.addElement("rty");
		
		lst.setItems(v);
		frame.add(lst);
		
//		JButton btn = new JButton("Set some values back");
//		frame.add(btn);
//		
//		btn.addActionListener(new ActionListener() {
//			
//			public void actionPerformed(ActionEvent e) {
//				lst.setText("abc,def");
//				
//			}
//		});
		
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setVisible(true);
		
	}
	
	public Vector getSelectedItems(){
		String sVal = txtValues.getText();
		if (sVal == null ||sVal.length() <1)
			return null;
		
		StringTokenizer sTok = new StringTokenizer(sVal, ",");
		
		Vector vValues = new Vector();
		while (sTok.hasMoreElements()){
			vValues.add(sTok.nextElement());
		}
		
		return vValues;
	}

	
}
