package com.cyl.excelTest;


import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;


public class ExcelUtils {

    private static XSSFSheet ExcelWSheet;
    private static XSSFWorkbook ExcelWBook;
    private static XSSFCell Cell;
    private static XSSFRow Row;
    private static String path=new Constants().getPathTestexcel();
    
 // 设置Excel文件路径，方便读取到文件
    public static void setExcelFile() throws Exception {
        try {
            FileInputStream ExcelFile = new FileInputStream(path);
            ExcelWBook = new XSSFWorkbook(ExcelFile);
        } catch (Exception e){
            Log.error("设置Excel文件路径，方便读取到文件: "+e.getMessage());
            //DriverScript.bResult = false;
        }
    }
 // 读取Excel文件单元格数据
    // 新增sheet name参数，这样就可以去读取Test Steps和Test Cases两个工作表的单元格数据
    public static String getCellData(int RowNum, int ColNum, String SheetName ) throws Exception{
        try{
            ExcelWSheet = ExcelWBook.getSheet(SheetName);
            Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
           Cell.setCellType(Cell.CELL_TYPE_STRING);
            String CellData = Cell.getStringCellValue();
            return CellData;
        }catch (Exception e){
        	if(e.getMessage()==null||e.getMessage().equals("")) {
        		
            //Log.error("读取Excel文件单元格数据为空");
            
        	}else {
        	Log.error("读取Excel文件单元格数据发生异常");
            DriverScript.bResult = false;
        	}
            return "";
        }
    }
    //得到一共多少行数据
    public static int getRowCount(String SheetName){
        int iNumber=0;
        try {
            ExcelWSheet = ExcelWBook.getSheet(SheetName);
            iNumber=ExcelWSheet.getLastRowNum()+1;
        } catch (Exception e){
            Log.error("得到一共多少行数据 : "+e.getMessage());
            DriverScript.bResult = false;
        }
        return iNumber;
    }
  //得到测试用例的行号
    public static int getRowContains(String sTestCaseName, int colNum,String SheetName) throws Exception{
        int iRowNum=0;
        try {
            //ExcelWSheet = ExcelWBook.getSheet(SheetName);
            int rowCount = ExcelUtils.getRowCount(SheetName);
            for (; iRowNum<rowCount; iRowNum++){
                if  (ExcelUtils.getCellData(iRowNum,colNum,SheetName).equalsIgnoreCase(sTestCaseName)){
                    break;
                }
            }
        } catch (Exception e){
            Log.error("得到测试用例的行号: "+e.getMessage());
            DriverScript.bResult = false;
        }
        return iRowNum;
    }
    //计算一个测试用例有多少个步骤
    public static int getTestStepsCount(String SheetName, String sTestCaseID, int iTestCaseStart) throws Exception{
        try {
            for(int i=iTestCaseStart;i<=ExcelUtils.getRowCount(SheetName);i++){
                if(!sTestCaseID.equals(ExcelUtils.getCellData(i, Constants.Col_TestCaseID, SheetName))){
                    int number = i;
                    return number;
                }
            }
            ExcelWSheet = ExcelWBook.getSheet(SheetName);
            int number=ExcelWSheet.getLastRowNum()+1;
            return number;
        } catch (Exception e){
            Log.error("计算一个测试用例有多少个步骤: "+e.getMessage());
            DriverScript.bResult = false;
            return 0;
        }
    }
    // 构造一个往单元格写数据的方法，主要是用来写结果pass还是fail
    public static void setCellData(String Result,  int RowNum, int ColNum, String SheetName) throws Exception    {
        try{

            ExcelWSheet = ExcelWBook.getSheet(SheetName);
            Row  = ExcelWSheet.getRow(RowNum);
            Cell = Row.getCell(ColNum);
            if (Cell == null) {
                Cell = Row.createCell(ColNum);
                Cell.setCellValue(Result);
            } else {
                Cell.setCellValue(Result);
            }
         // 常量变量测试数据路径和测试数据文件名
            FileOutputStream fileOut = new FileOutputStream(path);
            ExcelWBook.write(fileOut);
            //fileOut.flush();
            fileOut.close();
            ExcelWBook = new XSSFWorkbook(new FileInputStream(path));
        }catch(Exception e){
            DriverScript.bResult = false;
        }
    }

}
