package com.cyl.tools;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.cyl.dao.ExcelDao;

public class ImportExcel {
	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;
    private static ExcelDao excelDao = new ExcelDao();
	// 设置Excel文件路径，方便读取到文件
	public static void setExcelFile(String Path, String SheetName) throws IOException {

		FileInputStream ExcelFile = new FileInputStream(Path);
		ExcelWBook = new XSSFWorkbook(ExcelFile);
		ExcelWSheet = ExcelWBook.getSheet(SheetName);
	}

	// 读取Excel文件单元格数据
	public static String getCellData(int RowNum, int ColNum) throws Exception {
		Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
		Cell.setCellType(Cell.CELL_TYPE_STRING);
		String CellData = Cell.getStringCellValue();
		
		return CellData;
	}

	// 得到一共多少行数据
	public static int getRowCount(String SheetName) {
		int iNumber = 0;
		try {
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
			iNumber = ExcelWSheet.getLastRowNum() + 1;
		} catch (Exception e) {
			System.out.print("无法获取测试用例总数量");
		}
		return iNumber;
	}

	public static void main(String[] args) throws Exception {
		String excel_path = "C:\\Users\\wflk\\Desktop\\ex\\AutomateTestCases.xlsx";
		String Sheet = "Sheet1";
		// 加载读取excel文件
		ImportExcel.setExcelFile(excel_path, Sheet);
		// 获取excel行数
		int iTotalTestCases = ImportExcel.getRowCount(Sheet);
		System.out.println("excel行数：" + iTotalTestCases);
		for (int iRow =1; iRow <iTotalTestCases; iRow++) {//必须从1开始
			String bh = "" ,bz = "" ,gn= "" ,dw = "" ,dz = "" ,value02= "" ,value03= "" ;
			try {bh = ImportExcel.getCellData(iRow, 3);} catch (Exception e) {bh = "";}
			try {bz =ImportExcel.getCellData(iRow, 4);} catch (Exception e) {bz = "";}
			try {gn =ImportExcel.getCellData(iRow, 5);} catch (Exception e) {gn = "";}
			try {dw =ImportExcel.getCellData(iRow, 6);} catch (Exception e) {dw = "";}
			try {dz =ImportExcel.getCellData(iRow, 7);} catch (Exception e) {dz = "";}
			try {value02 =ImportExcel.getCellData(iRow, 8);} catch (Exception e) {value02 = "";}
			try {value03 =ImportExcel.getCellData(iRow, 9);} catch (Exception e) {value03 = "";}
			
			excelDao.addCz("test",1,Integer.valueOf(bh).intValue(), bz, gn, dw, dz, value02, value03, "T", "10");
		
		}

	}

}
