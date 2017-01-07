package com.act.server.db;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.act.common.ACEDefines;
import com.act.common.Counsellee;
import com.act.common.CounselleeReportDataObject;
import com.act.common.PersonName;

public class ReportsMSDB extends ReportsDB {
	
	private static ReportsMSDB reportMSDB;
	
	private ReportsMSDB(){
		
	}
	
	public static ReportsMSDB getInstance(){
		if(reportMSDB ==null){
			reportMSDB = new ReportsMSDB();
		}
		return reportMSDB;
	}
	
	public Vector<CounselleeReportDataObject> getCounselleeReports(
									Hashtable<String, String> htOptions,
									Vector<String> colNames){
		Connection con = null;
		
		Vector<CounselleeReportDataObject> vCnsleeReports = new Vector<CounselleeReportDataObject>();
		if (colNames == null || colNames.size() <1){
			System.out.println("return for colnames empty");
			return null;
		}
		
		StringBuffer sql= new StringBuffer("Select * ");
		
		StringBuffer sbColNames = new StringBuffer();

		boolean bIncludeTFCBT = false;
		
		//add the column names in the sql statement
		for (Iterator iterator = colNames.iterator(); iterator.hasNext();) {
			String colName = (String) iterator
					.next();
			
			if (sbColNames.length() > 0){
				sbColNames.append(",");
			}

			String colNameDB = "";
			switch (colName) {
//				case ACEDefines.COUNSELLE_NAME:
//					colNameDB = DB_COL_INDIV_NAME;
//					break;
//	
//				case ACEDefines.COUNSELLE_AGE:
//					colNameDB = DB_COL_INDIV_AGE;
//					break;
//	
//				case ACEDefines.COUNSELLE_ORGANIZATION:
//					colNameDB = DB_COL_ORGN_NAME;
//					break;
//	
//				case ACEDefines.COUNSELLE_LOCATION:
//					colNameDB = DB_COL_CAREHOME_LOCATION;
//					break;
//	
//				case ACEDefines.CNSLING_SESSION_DATE:
//					colNameDB = DB_COL_CNSLING_SESSION_DATE;
//					break;
//	
//				case ACEDefines.CNSLING_SESSION_DURATION:
//					colNameDB = DB_COL_CNSLING_SESSION_DURATION;
//					break;
//	
//				case ACEDefines.CNSLING_SESSION_SUMMARY:
//					colNameDB = DB_COL_CNSLING_SESSION_CONTENTS;
//					break;
//	
//				case ACEDefines.CNSLING_SESSION_CASE_MGMT:
//					colNameDB = DB_COL_CNSLING_SESSION_CASE_MGMT;
//					break;
//	
//				case ACEDefines.CNSLING_SESSION_NEXT_OBJ:
//					colNameDB = DB_COL_CNSLING_SESSION_FOLLOW_UP_PREP;
//					break;
//	
				case ACEDefines.TFCBT_STAGE:
					colNameDB = DB_COL_TFCBT_CNSLEE_TASK_STAGE_NUM;
					bIncludeTFCBT = true;
					break;
	
				case ACEDefines.TFCBT_ACTIVITIES:
					colNameDB = DB_COL_TFCBT_TASK_TITLE;
					bIncludeTFCBT = true;
					break;
					
				case ACEDefines.TFCBT_COMMENTS:
					colNameDB = DB_COL_TFCBT_CNSLEE_TASK_NOTES;
					bIncludeTFCBT = true;
					break;
//					
//				default:
//					break;
		
			}
//			sbColNames.append(colNameDB);
			
		}
		sql.append( sbColNames.toString() + " from " + 
				DB_TBL_INDIV + " LEFT JOIN " +
				DB_TBL_COUNSELLING_SESSION+ 
				" ON "+
				DB_TBL_INDIV+"."+DB_COL_INDIV_ID + " = " + 
						DB_TBL_COUNSELLING_SESSION+"."+ DB_COL_CNSLING_SESSION_CASE_ID);

//		+ " LEFT JOIN " +
//		DB_TBL_ORGANISATION + " LEFT JOIN "+
//		DB_TBL_CAREHOME ;

		if (bIncludeTFCBT){
			sql.append(" LEFT JOIN "+
							DB_TBL_TFCBT_CNSLEE_TASK +
							" ON ("+
							DB_TBL_INDIV+"."+DB_COL_INDIV_ID + " = " + 
							DB_TBL_TFCBT_CNSLEE_TASK +"." +DB_COL_TFCBT_CNSLEE_ID +
							" AND "+
							DB_TBL_COUNSELLING_SESSION+"."+ DB_COL_CNSLING_SESSION_ID + " = " +
							DB_TBL_TFCBT_CNSLEE_TASK +"." +DB_COL_TFCBT_CNSLEE_TASK_CNSL_SESSION_ID + 
							
							") JOIN " +DB_TBL_TFCBT_TASK_MASTER + 
							" ON " +DB_TBL_TFCBT_TASK_MASTER + "." +DB_COL_TFCBT_TASK_ID + " = " +
										DB_TBL_TFCBT_CNSLEE_TASK + "." +DB_COL_TFCBT_CNSLEE_TASK_ID
							);
		}
	
		//form the where condition
		StringBuffer sbWhere = new StringBuffer();
//		sbWhere.append(" AND ");
//		sbWhere.append(DB_COL_INDIV_PARTNER_ORGN + " = " + DB_COL_ORGN_NAME);
//		sbWhere.append(" AND ");
//		sbWhere.append(DB_COL_INDIV_HOME + " = " + DB_COL_CAREHOME_NAME);

		if (htOptions != null){
//			sbWhere.append(" Where ");
//			sbWhere.append(DB_TBL_INDIV+"."+DB_COL_INDIV_ID + " = 'BBC201618112'");  //TODO
			
			//Counsellor Id
			String value = htOptions.get(ACEDefines.COUNSELLOR_NAME); 
			if (value != null){
				sbWhere.append(likeClause(DB_TBL_INDIV+"."+DB_COL_INDIV_CNSLR_NAME , value ));
			}
			
			//Organization
			value = htOptions.get(ACEDefines.COUNSELLE_ORGANIZATION);
			if (value != null){
				if (sbWhere.length() >0)
					sbWhere.append(" and ");
				
				sbWhere.append(likeClause(DB_TBL_INDIV+"."+DB_COL_INDIV_PARTNER_ORGN , value ));
			}
			
			//Location
			value = htOptions.get(ACEDefines.COUNSELLE_LOCATION);
			if (value != null){
				if (sbWhere.length() >0)
					sbWhere.append(" and ");
				
				sbWhere.append(likeClause(DB_TBL_INDIV+"."+DB_COL_INDIV_HOME , value ));
			}
			
			//Counsellee Name
			value = htOptions.get(ACEDefines.COUNSELLE_NAME);
			if (value != null){
				if (sbWhere.length() >0)
					sbWhere.append(" and ");
				
				sbWhere.append(likeClause(DB_TBL_INDIV+"."+DB_COL_INDIV_NAME, value ));
			}
			
			//Status  //TODO
//			value = htOptions.get(ACEDefines.CnLOCATION);
//			if (value != null){
//				if (sbWhere.length() >0)
//					sbWhere.append(" and ");
//				
//				sbWhere.append(likeClause(DB_TBL_INDIV+"."+DB_COL_INDIV_HOME , value );
//			}
			
			//Therapy
			value = htOptions.get(ACEDefines.COUNSELLE_THERAPY);
			if (value != null){
				if (sbWhere.length() >0)
					sbWhere.append(" and ");
				
				sbWhere.append(likeClause(DB_TBL_COUNSELLING_SESSION+"."+DB_COL_CNSLING_SESSION_THERAPY, value ));
			}
			
			//Session Dates
			value = htOptions.get(ACEDefines.CNSLING_SESSION_DATE_FROM);
			if (value != null){
				if (sbWhere.length() >0)
					sbWhere.append(" and ");
				
				sbWhere.append(DB_TBL_COUNSELLING_SESSION+"."+DB_COL_CNSLING_SESSION_DATE + " >= " + value );
			}
			
			value = htOptions.get(ACEDefines.CNSLING_SESSION_DATE_TO);
			if (value != null){
				if (sbWhere.length() >0)
					sbWhere.append(" and ");
				
				sbWhere.append(DB_TBL_COUNSELLING_SESSION+"."+DB_COL_CNSLING_SESSION_DATE + " <= " + value );
			}
			
			//TFCBT Stage
			value = htOptions.get(ACEDefines.TFCBT_STAGE_FROM);
			if (value != null){
				if (sbWhere.length() >0)
					sbWhere.append(" and ");
				
				sbWhere.append(DB_TBL_TFCBT_CNSLEE_TASK+"."+DB_COL_TFCBT_CNSLEE_TASK_STAGE_NUM + " >= " + value );
			}
			
			value = htOptions.get(ACEDefines.TFCBT_STAGE_TO);
			if (value != null){
				if (sbWhere.length() >0)
					sbWhere.append(" and ");
				
				sbWhere.append(DB_TBL_TFCBT_CNSLEE_TASK+"."+DB_COL_TFCBT_CNSLEE_TASK_STAGE_NUM + " <= " + value );
			}
			
		}
	
		sql.append(" Where " + sbWhere.toString());
		
		//Give the sorting order
		sql.append(" ORDER BY ");
		sql.append(DB_TBL_INDIV + "." + DB_COL_INDIV_ID );
		sql.append(",");
		sql.append(DB_TBL_COUNSELLING_SESSION + "." + DB_COL_CNSLING_SESSION_ID);
		
		if (bIncludeTFCBT){
			sql.append(",");
			sql.append(DB_TBL_TFCBT_CNSLEE_TASK+ "." + DB_COL_TFCBT_CNSLEE_TASK_ID);
		}
		sql.append("");
		
		System.out.println("REPORTS QUERY: "+ sql);
		//write database queries to retrieve the user 
		try{
			con = getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql.toString());
			
			while (rs.next()){
				
				CounselleeReportDataObject report = new CounselleeReportDataObject();
				
				System.out.println("rs.getString(DB_COL_INDIV_ID): " + rs.getString(DB_COL_INDIV_ID));
				
				report.setCaseId(rs.getString(DB_COL_INDIV_ID));
				report.setsCnsleeName(rs.getString(DB_COL_INDIV_NAME));
				report.setsCnsleeAge(rs.getString(DB_COL_INDIV_AGE));
				report.setSessionId(rs.getString(DB_COL_CNSLING_SESSION_ID));
				report.setsSessionDate(rs.getString(DB_COL_CNSLING_SESSION_DATE));
				report.setsDuration(rs.getString(DB_COL_CNSLING_SESSION_DURATION));
				report.setsSessionContents(rs.getString(DB_COL_CNSLING_SESSION_CONTENTS));
				report.setsNextObjectives(rs.getString(DB_COL_CNSLING_SESSION_FOLLOW_UP_PREP));
				report.setsCaseManagement(rs.getString(DB_COL_CNSLING_SESSION_CASE_MGMT));
				
				if (bIncludeTFCBT){
					report.setsTfcbtStage(rs.getString(DB_COL_TFCBT_CNSLEE_TASK_STAGE_NUM));
					report.setsTfcbtComments(rs.getString(DB_COL_TFCBT_CNSLEE_TASK_NOTES));
					report.setsTfcbtActivities(rs.getString(DB_COL_TFCBT_TASK_TITLE)); //TODO
				}
				System.out.println("report retrieved" + report.getsCnsleeName());
				
				vCnsleeReports.add(report);
			}
				
			con.close();
		}catch(SQLException e){
			e.printStackTrace();
			if (con != null){
				try {
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}catch (Exception e){
			e.printStackTrace();
			if (con != null){
				try {
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		
		createReportxl(vCnsleeReports, colNames);
		
		return vCnsleeReports;
	}
	
	//gg test start
	private void createReportxl(Vector vCnsleeReport, Vector<String> vColNames){
		
		if (vCnsleeReport == null || vColNames == null ||
				vCnsleeReport.size() < 1)
			return;
		
			//Create Blank workbook
		XSSFWorkbook workbook = new XSSFWorkbook(); 
		
		try{
			
			//Create file system using specific name
			FileOutputStream out = new FileOutputStream(new File("ExceReport.xlsx"));
			XSSFSheet sheet = workbook.createSheet("Reports");
			
			XSSFRow row;
//			XSSFCellStyle styleOrgTitle = workbook.createCellStyle();
//			styleOrgTitle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
//		      style1.setVerticalAlignment(XSSFCellStyle.VERTICAL_TOP);
//		      cell.setCellValue("Top Left");
//		      cell.setCellStyle(style1);

			///////// /Write out the Header  ///////
			//Organization Title
			int nRowIndex = 0;
			row = sheet.createRow(nRowIndex);
			row.setHeight((short)600);
			XSSFCell cellOrgTitle = (XSSFCell)row.createCell(0, 5);
			cellOrgTitle.setCellValue("Association For Christian Thoughtfulness (ACT)");
			
			XSSFCellStyle styleOrgTitle = workbook.createCellStyle();
			styleOrgTitle.setFillForegroundColor(new XSSFColor(new Color(9,73,138)));
			
//			styleOrgTitle.setegroundColor(IndexedColors.BLUE.getIndex());
			styleOrgTitle.setFillPattern(CellStyle.SOLID_FOREGROUND);
			styleOrgTitle.setAlignment(XSSFCellStyle.ALIGN_LEFT);
			
			XSSFFont fontOrgTitle = workbook.createFont();
//			fontOrgTitle.setFontHeightInPoints((short) 30);
			fontOrgTitle.setBold(true);
			fontOrgTitle.setFontHeightInPoints((short)18);
			fontOrgTitle.setColor(IndexedColors.WHITE.index);
			styleOrgTitle.setFont(fontOrgTitle);

			cellOrgTitle.setCellStyle(styleOrgTitle);
			sheet.addMergedRegion(new CellRangeAddress(
					nRowIndex, //first row (0-based)
					nRowIndex, //last row (0-based)
				      0, //first column (0-based)
				      vColNames.size() //last column (0-based)
				      ));
			
			
			//TODO add the ACT Logo
//			Cell cellLogo = row.createCell(5);
//			cellLogo.setCellType()
			
			//Title
			row = sheet.createRow(++nRowIndex);
			Cell cellTitle = row.createCell(0, 5);
			sheet.addMergedRegion(new CellRangeAddress(
					nRowIndex, 
					nRowIndex, 
				      0,
				      vColNames.size() 
				      ));

			XSSFCellStyle styleTitle = workbook.createCellStyle();
			
//			byte[] colorTitle1 = {(byte)FF,(byte)223,(byte)223};
			styleTitle.setFillBackgroundColor(new XSSFColor(new Color(40,30,255)));
			styleTitle.setFillPattern(CellStyle.BORDER_DASHED);
			styleTitle.setFillForegroundColor(new XSSFColor(Color.white));
//			styleTitle.setFillBackgroundColor(HSSFColor.BLUE.index);
			styleTitle.setAlignment(XSSFCellStyle.ALIGN_LEFT);
			cellTitle.setCellStyle(styleTitle);
			
			XSSFFont fontTitle = workbook.createFont();
			fontTitle.setBold(true);
			fontTitle.setFontHeightInPoints((short)15);
			styleTitle.setFont(fontTitle);

			cellTitle.setCellValue("Counselling Sessions Report");

			//Created by
			XSSFCellStyle styleCreatedBy = workbook.createCellStyle();
			styleCreatedBy.setWrapText(true);
			
			XSSFFont fontCreatedBy = workbook.createFont();
			fontCreatedBy.setBold(true);
			fontCreatedBy.setFontHeightInPoints((short)12);
			styleCreatedBy.setFont(fontCreatedBy);
			
			row = sheet.createRow(++nRowIndex); //empty Row
			row = sheet.createRow(++nRowIndex);
			Cell cellCreatedBy = row.createCell(1);
			cellCreatedBy.setCellStyle(styleCreatedBy);
			cellCreatedBy.setCellValue("Created By: ");
			
			Cell cellCreatedByVal = row.createCell(2);
			cellCreatedByVal.setCellStyle(styleCreatedBy);
			cellCreatedByVal.setCellValue("ACE Admin"); //TODO to change this
			sheet.addMergedRegion(new CellRangeAddress(
					nRowIndex, 
					nRowIndex, 
				      2,
				      3 
				      ));
			
			//Created Date
			row = sheet.createRow(++nRowIndex);
			Cell cellDate = row.createCell(1);
			cellDate.setCellStyle(styleCreatedBy);
			cellDate.setCellValue("Created Date/Time: ");
			
			Cell cellDateVal = row.createCell(2);
			cellDateVal.setCellStyle(styleCreatedBy);
			
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			Date date = new Date();
			cellDateVal.setCellValue(dateFormat.format(date)); 
			
			sheet.addMergedRegion(new CellRangeAddress(
					nRowIndex, 
					nRowIndex, 
				      2,
				      3 
				      ));
			
			//Add the report table 
			sheet.createRow(++nRowIndex); //empty Row
			row = sheet.createRow(++nRowIndex);
			
			//column Names row
			XSSFCellStyle styleColumnNames = workbook.createCellStyle();
			XSSFFont fontColumnNames = workbook.createFont();
			fontColumnNames.setBold(true);
			fontColumnNames.setFontHeightInPoints((short)11);
			styleColumnNames.setFont(fontColumnNames);
			styleColumnNames.setFillForegroundColor(new XSSFColor(new Color(233,243,253)));
			styleColumnNames.setFillPattern(CellStyle.SOLID_FOREGROUND);
			styleColumnNames.setWrapText(true);
			
			//set border
			styleColumnNames.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			styleColumnNames.setBorderTop(HSSFCellStyle.BORDER_THIN);
			styleColumnNames.setBorderRight(HSSFCellStyle.BORDER_THIN);
			styleColumnNames.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			
			//create the serial number column
			Cell cellSerNo = row.createCell(0);
			cellSerNo.setCellValue("Sl No.");
			cellSerNo.setCellStyle(styleColumnNames);
			sheet.setColumnWidth(0, 1000);
			
			//create the other data column names row
			for (int j = 0; j < vColNames.size(); j++) {
				String colName = vColNames.elementAt(j);
				Cell cellColName = row.createCell(j+1);
				cellColName.setCellStyle(styleColumnNames);
				System.out.println("column anme:**************" +colName);
				cellColName.setCellValue(colName);
			}			
			
			String caseId = "";
			String sessionId = "";
			
			//row numbers where merging starts
			int rowMergeStartCnsleeName 	= 0;
			int rowMergeStartSessionDate 	= 0;
			
			//Column indices
			int[] colsCnsleeBasic 	= new int[6];
			int[] colsSession 		= new int [6]; 
					
			colsCnsleeBasic[0] = 0; //serial number
			colsCnsleeBasic[1] = -1; //name
			colsCnsleeBasic[2] = -1; //age
			colsCnsleeBasic[3] = -1; //org
			colsCnsleeBasic[4] = -1; //loc
			
			colsSession[0] = -1; //session date
			colsSession[1] = -1; //duration
			colsSession[2] = -1; //summary
			colsSession[3] = -1; //next obj
			colsSession[4] = -1; //case mgmt
			
			//Style for Merged Cols
			XSSFCellStyle styleMergedCols = workbook.createCellStyle();
			styleMergedCols.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
			styleMergedCols.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			styleMergedCols.setBorderTop(HSSFCellStyle.BORDER_THIN);
			styleMergedCols.setBorderRight(HSSFCellStyle.BORDER_THIN);
			styleMergedCols.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			styleMergedCols.setWrapText(true);
			
			XSSFCellStyle styleDataCell = workbook.createCellStyle();
			styleDataCell.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
			styleDataCell.setWrapText(true);
			styleDataCell.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			styleDataCell.setBorderTop(HSSFCellStyle.BORDER_THIN);
			styleDataCell.setBorderRight(HSSFCellStyle.BORDER_THIN);
			styleDataCell.setBorderLeft(HSSFCellStyle.BORDER_THIN);

			int serialNumber = 1;
			
			//Data rows
			for (int i = 0; i < vCnsleeReport.size(); i++) {
				CounselleeReportDataObject reportData = (CounselleeReportDataObject)vCnsleeReport.elementAt(i);
				
				row = sheet.createRow(++nRowIndex);
				
	            ////////////////////////////////////////
	            /////// Merge rows appropriately  //////
	            ////////////////////////////////////////
	            
	            //We need to merge rows for counsellee name and related details

	            //get the case Id, sessionId - based on these we merge the rows
	            String currCaseId = reportData.getCaseId();
	            String currSessionId = reportData.getSessionId();
	            
	            if (caseId.equals("")){
	            	
	            	rowMergeStartCnsleeName = nRowIndex;
	            	rowMergeStartSessionDate = nRowIndex;
	            	caseId = currCaseId;
	            	sessionId = currSessionId;
	            	
	            	
	            }else if (!caseId.equals(currCaseId) ){
	            	System.out.println("Merge Row Range :" + rowMergeStartCnsleeName + ", " + (nRowIndex-1) );
	            	
	            	// merge all the cnslee basic details,
	            	// if more than 2 rows
	            	if (rowMergeStartCnsleeName < (nRowIndex-1)){
	            		mergeRows(sheet, colsCnsleeBasic, rowMergeStartCnsleeName, nRowIndex-1);
	            	}
	            	
	            	if (rowMergeStartSessionDate < (nRowIndex-1)){
	            		mergeRows(sheet, colsSession, rowMergeStartSessionDate, nRowIndex-1);
	            	}
	            	
	            	
	            	caseId = currCaseId;
	            	sessionId = currSessionId;
	            	
	            	rowMergeStartCnsleeName = nRowIndex;
	            	rowMergeStartSessionDate = nRowIndex;

	            	++serialNumber;
	            	
	            }else if (i == (vCnsleeReport.size()-1) ){
	            	System.out.println("Merge Row Range :" + rowMergeStartCnsleeName + ", " + nRowIndex );
	            	
	            	//merge only if more than 2 rows
	            	if (rowMergeStartCnsleeName < nRowIndex){
	            		mergeRows(sheet, colsCnsleeBasic, rowMergeStartCnsleeName, nRowIndex);
	            	}
	            	
	            	if (rowMergeStartSessionDate < nRowIndex){
	            		mergeRows(sheet, colsSession, rowMergeStartSessionDate, nRowIndex);
	            	}
	            }else if (!sessionId.equals(currSessionId)){
	            	System.out.println("Merge Row Range session details:" + rowMergeStartSessionDate + ", " + (nRowIndex-1) );
	            	
	            	// merge all the session details,
	            	// if more than 2 rows
	            	if (rowMergeStartSessionDate < (nRowIndex-1)){
	            		mergeRows(sheet, colsSession, rowMergeStartSessionDate, nRowIndex-1);
	            	}
	            	
	            	sessionId = currSessionId;
	            	rowMergeStartSessionDate = nRowIndex;
	            }

				//set serial Number column values
				Cell cellSerialNo = row.createCell(0);
				cellSerialNo.setCellStyle(styleMergedCols);
				cellSerialNo.setCellValue(serialNumber);

				for (int j = 0; j <vColNames.size(); j++) {
					String colName = vColNames.elementAt(j);
					XSSFCell cell = row.createCell(j+1);
					cell.setCellStyle(styleDataCell);
					
					switch (colName) {
						case ACEDefines.COUNSELLE_NAME:
							setColumnWidth(sheet, i, j+1, 6000);
							colsCnsleeBasic[1] = j+1;
				            cell.setCellValue(PersonName.getFormattedName(reportData.getsCnsleeName()));
				            cell.setCellStyle(styleMergedCols);
							break;
						case ACEDefines.COUNSELLE_AGE:
							setColumnWidth(sheet, i, j+1, 2000);
				            cell.setCellValue(reportData.getsCnsleeAge());
				            colsCnsleeBasic[2] = j+1;
							break;
						case ACEDefines.COUNSELLE_ORGANIZATION:
							setColumnWidth(sheet, i, j+1, 4000);
				            cell.setCellValue(reportData.getsOrganization());
				            colsCnsleeBasic[3]  = j+1;
							break;
						case ACEDefines.COUNSELLE_LOCATION:
							setColumnWidth(sheet, i, j+1, 4000);
				            cell.setCellValue(reportData.getsLocation());
				            colsCnsleeBasic[4] = j+1;
							break;
						case ACEDefines.CNSLING_SESSION_DATE:
							setColumnWidth(sheet, i, j+1, 3000);
							SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
				            cell.setCellValue(reportData.getsSessionDate());
				            colsSession[0] = j+1;
							break;
						case ACEDefines.CNSLING_SESSION_DURATION:
							setColumnWidth(sheet, i, j+1, 2500);
				            cell.setCellValue(reportData.getsDuration());
				            colsSession[1] = j+1;
							break;
						case ACEDefines.CNSLING_SESSION_SUMMARY:
							setColumnWidth(sheet, i, j+1, 10000);
				            cell.setCellValue(reportData.getsSessionContents());
				            colsSession[2] = j+1;
							break;
						case ACEDefines.CNSLING_SESSION_NEXT_OBJ:
							setColumnWidth(sheet, i, j+1, 10000);
				            cell.setCellValue(reportData.getsNextObjectives());
				            colsSession[3] = j+1;
							break;
						case ACEDefines.CNSLING_SESSION_CASE_MGMT:
							setColumnWidth(sheet, i, j+1, 10000);
				            cell.setCellValue(reportData.getsCaseManagement());
				            colsSession[4] = j+1;
							break;
						case ACEDefines.TFCBT_STAGE:
							setColumnWidth(sheet, i, j+1, 1600);
				            cell.setCellValue(reportData.getsTfcbtStage());
							break;
						case ACEDefines.TFCBT_ACTIVITIES:
							setColumnWidth(sheet, i, j+1, 10000);
				            cell.setCellValue(reportData.getsTfcbtActivities());
							break;
						case ACEDefines.TFCBT_COMMENTS:
							setColumnWidth(sheet, i, j+1, 10000);
				            cell.setCellValue(reportData.getsTfcbtComments());
							break;
							
						default:
							break;
					} 
				}
			}
			
			//write operation workbook using file out object 
			workbook.write(out);
			out.close();
			System.out.println("done writing file");
			System.out.println("createworkbook.xlsx written successfully");
			
		}catch(IOException ioe){
		   ioe.printStackTrace();
		   
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
//gg test end

	private void setColumnWidth(XSSFSheet sheet, int i, int colIndex, int width){
		if(i ==0)
			sheet.setColumnWidth(colIndex, width);
	}

	private void mergeRows(XSSFSheet sheet, int[] colIndices, int startRow, int endRow){
	
		try{
			for (int i = 0; i < colIndices.length; i++) {
				
				//if this column exists in the report
				if (colIndices[i] > -1){
	    			sheet.addMergedRegion(new CellRangeAddress(
	    					startRow, 
	    					endRow, 
	    					colIndices[i],
	    					colIndices[i]));
				}					
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
