import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class dataDriven {
	
	public ArrayList<String> getData(String testCaseName) throws IOException {
		//Identifying Testcases column by scanning the entire 1st row
				//Once column is identified then scan entire testcase column to identify purchase testcase
				//After grabbing entire purchase row - pull all the data of that row & feed into test
				
				//fileInputStream argumnet
				ArrayList<String> a = new ArrayList<String>();
				FileInputStream fis= new FileInputStream("/Users/ashish.jawal/Desktop/DemoData.xlsx");
				XSSFWorkbook workbook = new XSSFWorkbook(fis);
				
				int sheets= workbook.getNumberOfSheets(); 
				for(int i=0;i<sheets;i++) {
					if(workbook.getSheetName(i).equalsIgnoreCase("testData")) 
					{	
					
					XSSFSheet sheet= workbook.getSheetAt(i);
					
					//Identifying Testcases column by scanning the entire 1st row
					Iterator<Row> rows=sheet.iterator();
					Row firstrow= rows.next();
					Iterator<Cell> cel= firstrow.cellIterator();
					int k=0;
					int column=0;
					while(cel.hasNext()) {
						Cell value= cel.next();
						if(value.getStringCellValue().equals("Test Cases")) {
							column=k;
						}
						k++;
					}
					System.out.println(column);
					
					//Once column is identified then scan entire testcase column to identify purchase testcase
					
					while(rows.hasNext()) 
					{
						Row r= rows.next();
						
						if(r.getCell(column).getStringCellValue().equalsIgnoreCase(testCaseName)) 
						{
							
							//After grabbing entire purchase row - pull all the data of that row & feed into test

						Iterator<Cell> cv=r.cellIterator();
						while(cv.hasNext())
						{
						//For grabbing & printing each cell value
						//	System.out.println(cv.next().getStringCellValue());
							
						//For Array List concept
							a.add(cv.next().getStringCellValue());
						
						}
						
						}
				 	}
					
					}
					
			}
				return a;
	}

	public static void main(String[] args) throws IOException {
		
		
	}
}
