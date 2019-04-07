package com.tc.ak.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@SuppressWarnings("unused")
public class ExcelFileOperation 
{
	Workbook myWorkbook;
	public ExcelFileOperation(String path) throws IOException
	{
		File file = new File(path);
		FileInputStream inStream = new FileInputStream(file);
		myWorkbook = new XSSFWorkbook(inStream);
	}
	
	public Object[][]readData(String sheetName)
	{
		Sheet mySheet = myWorkbook.getSheet(sheetName);
		int totalRows = mySheet.getLastRowNum();
		int totalCol = mySheet.getRow(0).getLastCellNum();
		
		DataFormatter dataFormatter = new DataFormatter();
		
		Object[][] data = new Object[totalRows][totalCol];
		
		for(int rowIndex=1;rowIndex<=totalRows;rowIndex++)
		{
			for(int colIndex=0;colIndex<totalCol;colIndex++)
			{
				Cell cell = myWorkbook.getSheet(sheetName).getRow(rowIndex).getCell(colIndex);
				data[rowIndex-1][colIndex] = dataFormatter.formatCellValue(cell);
				/*if(cell == null)
					data[rowIndex-1][colIndex]="";
				else if(cell.getCellType() == CellType.STRING)
				{
					try
					{
						data[rowIndex-1][colIndex]=cell.getStringCellValue();
					}
					catch(Exception e)
					{
						data[rowIndex-1][colIndex]="";
					}
				}
				else if(cell.getCellType() == CellType.NUMERIC)
					data[rowIndex-1][colIndex]=cell.getNumericCellValue();
				else if (cell.getCellType() == CellType.BOOLEAN)
					data[rowIndex-1][colIndex]=cell.getBooleanCellValue();					
				else
					data[rowIndex-1][colIndex]="";*/
			}
		}
				
		return data;
	}
}
