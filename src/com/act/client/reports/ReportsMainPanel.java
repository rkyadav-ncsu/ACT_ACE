package com.act.client.reports;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReportsMainPanel extends JPanel implements ActionListener{
	
	JPanel panelTitle, panelReports,panelBottom;
	JLabel labelTitle;
	JTabbedPane tabbedpaneReports;
	CounsellingReportPanel panelRepCounselling;
	AdminReportPanel panelRepAdmin;
	
//	JButton btnSaveReport, btnClose;
	
	public ReportsMainPanel(){
		initUI();
	}
	
	private void initUI(){
		try{
			
			setLayout(new BorderLayout());
			
			//Title
			panelTitle = new JPanel();
			add(panelTitle, BorderLayout.NORTH);
			panelTitle.setPreferredSize(new Dimension(100, 40));
			panelTitle.setBackground(new Color(176,176,215));
			
			labelTitle = new JLabel("Reports");
			labelTitle.setFont(new Font(Font.SANS_SERIF, labelTitle.getFont().getStyle(), 22));
			panelTitle.add(labelTitle);
			
			//Reports Tab
			tabbedpaneReports = new JTabbedPane();
			add(tabbedpaneReports, BorderLayout.CENTER);
			
			//Reports Counselling
			panelRepCounselling = new CounsellingReportPanel();
			tabbedpaneReports.addTab("Counselling", panelRepCounselling);
			
			//Reports Admin
			panelRepAdmin = new AdminReportPanel();
			tabbedpaneReports.addTab("Admin", panelRepAdmin);
			
//			//Buttons Panel
//			panelBottom = new JPanel();
//			add(panelBottom, BorderLayout.SOUTH);
//			
//			btnSaveReport = new JButton("Save Report");
//			btnSaveReport.addActionListener(this);
//			panelBottom.add(btnSaveReport);
//			
//			btnClose = new JButton("Close");
//			btnClose.addActionListener(this);
//			panelBottom.add(btnClose);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void actionPerformed(ActionEvent e) {

	}

	
	private void createReportxl(){
		//Create Blank workbook
	    XSSFWorkbook workbook = new XSSFWorkbook(); 
	    
	    try{
	    	
			//Create file system using specific name
			FileOutputStream out = new FileOutputStream(new File("MYFirstExcel.xlsx"));
			//write operation workbook using file out object 
			workbook.write(out);
			out.close();
			
	    }catch(IOException ioe){
	       ioe.printStackTrace();
	    }
	    System.out.println("createworkbook.xlsx written successfully");
	}
}
