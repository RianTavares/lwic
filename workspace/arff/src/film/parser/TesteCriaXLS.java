package film.parser;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TesteCriaXLS {
	public static void main(String [] args) throws IOException{
		Workbook wb = new XSSFWorkbook();
		//CreationHelper createHelper = wb.getCreationHelper();
		Sheet sheet1 = wb.createSheet("new sheet");
		// Create a row and put some cells in it. Rows are 0 based.
	    Row row = sheet1.createRow((short)0);
	    // Create a cell and put a value in it.
	    Cell cell = row.createCell(0);
	    cell.setCellValue(1);


	    System.out.println("To aqui");
	}

}
