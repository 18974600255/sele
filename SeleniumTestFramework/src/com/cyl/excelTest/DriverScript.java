package com.cyl.excelTest;

import java.lang.reflect.Method;
import java.util.Properties;

import com.cyl.tools.StringFormat;


/**
 * 用于直接获取excel用例进行测试
 * {{但部分功能缺失}}！！！
 */
public class DriverScript {

    public static ActionsKeywords actionsKeywords;
    public static String sActionKeyword;
    public static Method method[];
    public static Properties OR; 
    public static String sPageObject;
    public static int iTestStep;
    public static int iTestLastStep;
    public static String sTestCaseID;
    public static String sRunMode;
    public static boolean bResult;
    public static String value01;
    public static String value02;
    public static StringFormat sfmr=new StringFormat(Constants.key);//默认补充横杠
    public static StringFormat sfkg=new StringFormat(Constants.key," ");//补充空格
    public DriverScript() throws NoSuchMethodException, SecurityException{
        actionsKeywords = new ActionsKeywords();
        method = actionsKeywords.getClass().getMethods();
     
    }

    public static void main(String[] args) throws Exception {
        ExcelUtils.setExcelFile();
        DriverScript startEngine = new DriverScript();
        startEngine.execute_TestCase();
        
    }

    private void execute_TestCase() throws Exception {
    	//获取测试用例数量
        int iTotalTestCases = ExcelUtils.getRowCount(Constants.Sheet_TestCases);
        //外层for循环，有多少个测试用例就执行多少次循环
      
        for(int iTestcase=1;iTestcase<iTotalTestCases;iTestcase++){
            bResult = true;
            sTestCaseID = ExcelUtils.getCellData(iTestcase, Constants.Col_TestCaseID, Constants.Sheet_TestCases);
       
            sRunMode = ExcelUtils.getCellData(iTestcase, Constants.Col_RunMode,Constants.Sheet_TestCases);
         
            if (sRunMode.equals("yes")){
            
                iTestStep = ExcelUtils.getRowContains(sTestCaseID, Constants.Col_TestCaseID, Constants.Sheet_TestSteps);
                iTestLastStep = ExcelUtils.getTestStepsCount(Constants.Sheet_TestSteps, sTestCaseID, iTestStep);

                bResult=true;
                
                for (;iTestStep <iTestLastStep;iTestStep++){
                    sActionKeyword = ExcelUtils.getCellData(iTestStep, Constants.Col_ActionKeyword,Constants.Sheet_TestSteps);
                    sPageObject = ExcelUtils.getCellData(iTestStep, Constants.Col_PageObject, Constants.Sheet_TestSteps);
                    value01 = ExcelUtils.getCellData(iTestStep, Constants.Col_DataSet, Constants.Sheet_TestSteps);
                     value02 = ExcelUtils.getCellData(iTestStep, Constants.Col_DataSet+1, Constants.Sheet_TestSteps);
                   execute_Actions();
                    if(bResult==false){
                        //如果“false”，那么将测试用例结果存储为Fail
                        ExcelUtils.setCellData(Constants.KEYWORD_FAIL,iTestcase,Constants.Col_Result,Constants.Sheet_TestCases);
                        //在日志中结束测试用例
                        Log.endTestCase();
                        //通过这个break语句，执行流将不再执行失败测试用例的任何测试步骤
                        break;
                    }
                   
                }
                //这只会在测试用例的最后一步之后执行，如果value在任何一步都不是“false”的话
                if(bResult==true){
                    //将结果作为Pass存储在excel工作表中
                    ExcelUtils.setCellData(Constants.KEYWORD_PASS,iTestcase,Constants.Col_Result,Constants.Sheet_TestCases);
                    Log.endTestCase();
                }
            }
        }
        
        endlog();
    }

    private static void execute_Actions() throws Exception {

        for(int i=0;i<method.length;i++){
            if(method[i].getName().equals(sActionKeyword)){
                //这段代码将向每个调用方法传递三个参数
                method[i].invoke(actionsKeywords,sPageObject, value01);
                
                if(bResult==true){
                    ExcelUtils.setCellData(Constants.KEYWORD_PASS, iTestStep, Constants.Col_TestStepResult, Constants.Sheet_TestSteps);
                    //单挑记录测试日志
                    log(iTestStep);
                    break;
                }else{
                    ExcelUtils.setCellData(Constants.KEYWORD_FAIL, iTestStep, Constants.Col_TestStepResult, Constants.Sheet_TestSteps);
                    //单挑记录测试日志
                    log(iTestStep);
                    //ActionsKeywords.关闭当前浏览器("","");
                    break;
                }
            }
        }
    }
    
 public static void log(int iTestStep) throws Exception {
	 String ym,mk,bh,ms,gn,jg;
     ym = ExcelUtils.getCellData(iTestStep, 0, Constants.Sheet_TestSteps);
     mk = ExcelUtils.getCellData(iTestStep, 1, Constants.Sheet_TestSteps);
     bh = ExcelUtils.getCellData(iTestStep, 3, Constants.Sheet_TestSteps);
     ms = ExcelUtils.getCellData(iTestStep, 4, Constants.Sheet_TestSteps);
     gn = ExcelUtils.getCellData(iTestStep, 5, Constants.Sheet_TestSteps);
     jg = ExcelUtils.getCellData(iTestStep, 12,Constants.Sheet_TestSteps);
   System.out.print(sfkg.textFormat(ym+"页面 -"+mk,32));
   System.out.print(sfkg.textFormat("：测试编号："+bh,16));
   System.out.print(sfmr.textFormat("描述:"+ms,70));
   System.out.print(sfmr.textFormat("该步骤功能："+gn,50));
   System.out.println(sfkg.textFormat("测试结果:"+jg,16));
   
    }
 public static void endlog() throws Exception {
	//获取测试用例数量
     int iTotalTestCases = ExcelUtils.getRowCount(Constants.Sheet_TestCases);
     //外层for循环，有多少个测试用例就执行多少次循环
   String xm,mk,jg,ms,key;
     for(int iTestcase=1;iTestcase<iTotalTestCases;iTestcase++){
    	 xm = ExcelUtils.getCellData(iTestcase,0, Constants.Sheet_TestCases);
         mk = ExcelUtils.getCellData(iTestcase,1, Constants.Sheet_TestCases);
         ms = ExcelUtils.getCellData(iTestcase,2, Constants.Sheet_TestCases);
         key= ExcelUtils.getCellData(iTestcase,3, Constants.Sheet_TestCases);
         jg = ExcelUtils.getCellData(iTestcase,4, Constants.Sheet_TestCases);
    	  System.out.print(sfkg.textFormat(xm+"-"+mk,20));
    	  System.out.print(sfmr.textFormat("描述:"+ms,30));
    	  System.out.print(sfkg.textFormat(":WhetherTheTest:"+key,20));
    	  System.out.println(sfkg.textFormat("result:"+jg,11));
      }
	 System.out.println("End of all tests ！");
 }
   
}