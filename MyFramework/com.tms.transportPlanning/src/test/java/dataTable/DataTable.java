package dataTable;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.tms.transportPlanning.TestRunner;

public class DataTable extends TestRunner{
	 private  FileInputStream fin_excel = null;
	 private  FileInputStream fin_ORprop = null;
	 private  Workbook wb = null;
	 private  Sheet sheet = null;
	 private  Row row = null;
	 private  Cell cell = null;
	 private  Properties p = null;
	 
	 
	 public DataTable(){
		 try {
			 //fin_excel = new FileInputStream(dataFileLoc);
			 fin_ORprop = new FileInputStream(ORFileLoc);
			 //wb = new XSSFWorkbook(fin_excel);
			 p = new Properties();
			 p.load(fin_ORprop);
		 }
		 catch(Exception e) {
			 e.printStackTrace();
		 }
		 
	 }
	
	 public int rowCount(String filePath,String SheetName) {
		 int totalRowCount = 0;
		 sheet = wb.getSheet(SheetName);
		 totalRowCount = sheet.getLastRowNum();
		 
		 return totalRowCount;
	 }
	 public String getCellValue(String filePath,String sheetName,int rowNum,int cellNum) {
		 String cellValue = null;
		 sheet = wb.getSheet(sheetName);
		 row = sheet.getRow(rowNum);
		 cellValue = row.getCell(cellNum).toString();
		 
		 return cellValue;
	 }
	 
	 public String getValue(String key) {
		 
		 return  p.getProperty(key);
	 }
}
