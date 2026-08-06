package genericUtility_Methods;
import java.io.IOException;
import java.io.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


public class ExcelFileUtility {
	
	public String toReadDataFromExcelFile(String sheetname,int rowno, int cellno) throws Throwable
	{
		FileInputStream fis1=new FileInputStream("./src/test/resources/TestScriptData.xlsx");
		Workbook wb=WorkbookFactory.create(fis1);
		String data=wb.getSheet(sheetname).getRow(rowno).getCell(cellno).getStringCellValue();
		return data;
		
	}

}
