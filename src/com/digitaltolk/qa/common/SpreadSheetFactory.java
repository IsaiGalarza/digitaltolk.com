package com.digitaltolk.qa.common;

import java.io.File;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import com.common.oTest.oTestSpreadSheetFactory;

public final class SpreadSheetFactory {
	
	@SuppressWarnings("unused")
	private static ResourceBundle resources;
	private static String xmlPath;
	String osName = null;
	String pathSeperator = null;
	
	static
	{
		try
		{
			resources = ResourceBundle.getBundle("com.digitaltolk.qa.configuration.oTestGeneral",Locale.getDefault());
		} catch (MissingResourceException mre) {
			System.out.println("oTestGeneral.properties not found: "+mre);
			System.exit(0);
		}
	}
	public SpreadSheetFactory(){

		xmlPath = System.getProperty("user.dir");
		System.out.println("xmlPath: " + xmlPath);
		osName = new String(System.getProperty("os.name")); // Mac OS X, Windows 7
		pathSeperator = new String(System.getProperty("file.separator"));
		System.out.println("oTestSpreadsheetFactory assumes a data folder at the same level");

		if(osName.contains("Windows")){
			xmlPath = new String(xmlPath+"\\data\\");
		}else{ // Mac and linux use the same path 
			xmlPath = new String(xmlPath+"/data/");
		}
		
	}
	//
	// Get test data from spreadsheet. This uses 2 tags to find  the data.  The first tag marks the 
	// start of the data, the second tag marks the end of the data.  the tag names are the same
	//
	/**
	 * This function Demonstrates getTableArray().
	 * <br>This class factory is used to generate data driven tests for all testNG tests
	 * <br>
	 * @param xlFilePath - path/name to the xls file
	 * @param sheetname - name of the workbook inside the xlFilePath
	 * @param tableName - name of table inside the workbook
	 * @return 2 dimensional array of data for any testNG test
	 * @exception None.
	 * @author ysaigalarza
	 * @version 1.0
	 */
	public String[][] getTableArray(String xlFilePath, String sheetName, String tableName){
        String[][] tabArray=null;
        try{
        	System.out.println(" ============ PATH SETUP.XLS =========    " + xmlPath+xlFilePath);
        	System.out.println("xlFilePath: " + xlFilePath);
        	System.out.println("sheetName: " + sheetName);
        	System.out.println("tableName: " + tableName);
            Workbook workbook = Workbook.getWorkbook(new File(xmlPath+xlFilePath));
            
            String [] sheetNames = workbook.getSheetNames();
            for(String sheet : sheetNames){
            	System.out.println("sheetNames =========> : " + sheet);
            }
            
            System.out.println("\nFound The workbook: "+xlFilePath);
            Sheet sheet = workbook.getSheet(sheetName);
            System.out.println("\nFound The sheet: " + sheetName);
            int startRow,startCol, endRow, endCol,ci,cj;
            System.out.println("\nLooking for tableName: "+tableName);
            Cell tableStart=sheet.findCell(tableName);
            System.out.println("\nFound The tableName: "+tableName);
            startRow=tableStart.getRow();
            startCol=tableStart.getColumn();

            Cell tableEnd= sheet.findCell(tableName, startCol+1,startRow+1, 100, 64000,  false);                               

            endRow=tableEnd.getRow();
            endCol=tableEnd.getColumn();
            System.out.println("startRow="+startRow+", endRow="+endRow+", " +
                    "startCol="+startCol+", endCol="+endCol);
            tabArray=new String[endRow-startRow-1][endCol-startCol-1];
            ci=0;

            for (int i=startRow+1;i<endRow;i++,ci++){
                cj=0;
                for (int j=startCol+1;j<endCol;j++,cj++){
                    tabArray[ci][cj]=sheet.getCell(j,i).getContents();
                }
            }
        }
        catch (Exception e)    {
        	e.printStackTrace();
        	System.out.println("Exception: "+ e);
            System.out.println("error in getTableArray()");
        }

        return(tabArray);
    }
	
	/**
	 * This class Demonstrates oTestSpreadsheetFactory().
	 * <br>Get test data from spreadsheet. This uses 2 tags to find  the data.  The first tag marks the 
	 * <br> start of the data, the second tag marks the end of the data.  the tag names are the same
	 * <br>
	 * @param File - file to the xls data
	 * @param xlFileName - name of the xls file
	 * @param sheetName - worksheet name
	 * @param tableName - table within the work sheet
	 * @return 2 dimensional array of the test data
	 * @exception None.
	 * @author ysaigalarza
	 * @version 1.0
	 */
	public String[][] getTableArray( File fileToOpen, String xlFileName, String sheetName, String tableName){
        String[][] tabArray=null;
        try{
            Workbook workbook = Workbook.getWorkbook(fileToOpen);
            System.out.println("\nFound The workbook: "+xlFileName);
            Sheet sheet = workbook.getSheet(sheetName);
            System.out.println("\nFound The sheet: " + sheetName);
            int startRow,startCol, endRow, endCol,ci,cj;
            System.out.println("\nLooking for tableName: "+tableName);
            Cell tableStart=sheet.findCell(tableName);
            System.out.println("\nFound The tableName: "+tableName);
            startRow=tableStart.getRow();
            startCol=tableStart.getColumn();

            Cell tableEnd= sheet.findCell(tableName, startCol+1,startRow+1, 100, 64000,  false);                               

            endRow=tableEnd.getRow();
            endCol=tableEnd.getColumn();
            System.out.println("startRow="+startRow+", endRow="+endRow+", " +
                    "startCol="+startCol+", endCol="+endCol);
            tabArray=new String[endRow-startRow-1][endCol-startCol-1];
            ci=0;

            for (int i=startRow+1;i<endRow;i++,ci++){
                cj=0;
                for (int j=startCol+1;j<endCol;j++,cj++){
                    tabArray[ci][cj]=sheet.getCell(j,i).getContents();
                }
            }
        }
        catch (Exception e)    {
        	e.printStackTrace();
        	System.out.println("Exception: "+ e);
            System.out.println("error in getTableArray()");
        }

        return(tabArray);
    }
	
	
	//
	// Get test data from spreadsheet. This uses 2 tags to find  the data.  The first tag marks the 
	// start of the data, the second tag marks the end of the data.  the tag names are the same
	//
	/**
	 * This function Demonstrates getTableMap().
	 * <br>This class factory is used to generate data driven tests for all testNG tests
	 * <br>
	 * @param xlFilePath - path/name to the xls file
	 * @param sheetname - name of the workbook inside the xlFilePath
	 * @param tableName - name of table inside the workbook
	 * @return HashMap
	 * @exception None.
	 * @author Jason So
	 * @version 1.0
	 */
		@SuppressWarnings("unused")
		public Map<String, String> getTableMap(String xlFilePath, String sheetName, String tableName){
        Map<String, String> dataMap = new HashMap<String, String>();
		
        try{
            Workbook workbook = Workbook.getWorkbook(new File(xmlPath+xlFilePath));
            System.out.println("\nFound The workbook: "+xlFilePath);
            Sheet sheet = workbook.getSheet(sheetName);
            System.out.println("\nFound The sheet: " + sheetName);
            int startRow, startCol, endRow, endCol, ci, cj;
            System.out.println("\nLooking for tableName: "+tableName);
            Cell tableStart=sheet.findCell(tableName);
            System.out.println("\nFound The tableName: "+tableName);
            startRow=tableStart.getRow();
            startCol=tableStart.getColumn();

            Cell tableEnd= sheet.findCell(tableName, startCol+1,startRow+1, 100, 64000,  false);                               

            endRow=tableEnd.getRow();
            endCol=tableEnd.getColumn();
            System.out.println("startRow="+startRow+", endRow="+endRow+", " +
                    "startCol="+startCol+", endCol="+endCol);
//	This is for 2 or more rows but the framework won't be able to process this kind of data yet
//			for(int i=startRow+1; i< endRow; i++){
//				Map<String, String> map = new HashMap<String, String>();
//				for(int j=startCol+1; j<endCol; j++){
//					map.put(sheet.getCell(j, startRow).getContents(), sheet.getCell(j,i).getContents());
//				}
//			}
//  This is for single row data           
			for( int j=startCol+1; j<endCol; j++){
				dataMap.put(sheet.getCell(j, startRow).getContents(), sheet.getCell(j,startRow+1).getContents());
			}
        }
        catch (Exception e)    {
        	e.printStackTrace();
        	System.out.println("Exception: " + e);
            System.out.println("error in getTableArray()");
        }
        return(dataMap);
    }
	
	
	public void showObjectElements(){
		
		Field[] list = this.getClass().getDeclaredFields();
		
		System.out.println("Class has the following fields:\n");
		
		if(list.length == 0) System.out.println("No items in the list\n");
		
		for(int x = 0; x < list.length;x++){
			System.out.println("name: "+list[x].getName() + "\n");
		}
		
		
	}
	//
	// New put method that will allocate more rows for the object if needed
	//
	 public void allocateAnotherRow() {
	     
	   }
	
	//
	// Inner class for testing on the command line
	//
	public static class Test
	{
		public static void main(final String[] args)
    	{
			
			if(args.length == 0)
			{
				String xlsPath = new String("SelfTest.xls");
				String sheetName = new String("SelfTestSetup");
				String tableName = new String("elance.SpreadSheet.SelfTest");
			
				String [][] myData = null;
				oTestSpreadSheetFactory xlFactory = new oTestSpreadSheetFactory();
				myData = xlFactory.getTableArray(xlsPath,sheetName,tableName);
				//
				// Display all the data
				//
				if(myData != null)
				{
					System.out.println("     PATH: "+ xlsPath+"\n");
					System.out.println("sheetName: "+ sheetName+"\n");
					System.out.println("tableName: "+ tableName+"\n");
					System.out.println("THE DATA ===================>\n");
					 for (int row = 0; row < myData.length; row++) 
					 {
						 System.out.println();
				            for (int col = 0; col < myData[row].length; col++) {
				            	System.out.println( myData[row][col]);
				            }
				        }
				}
			}else{
				System.out.println("USAGE: java oTestSpreadSheetFactory$Test <xlsPath><sheetname><tableName>");
			}
    	}
	}
	
}
