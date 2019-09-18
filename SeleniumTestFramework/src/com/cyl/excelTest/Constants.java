package com.cyl.excelTest;


public class Constants {
    public static final boolean key=true;  //为false则开启无界面模式(打包前改为key=false,ts=false)
    public static final boolean ts=false;//禁默调试模式是否开启
    private String codeip;
    private  String Path_TestData;
    public static final int Col_TestCaseID = 1;//总表：模块
    public static final int Col_PageObject = 7;//步骤表：地址
	public static final int Col_ActionKeyword = 5 ;//步骤表：功能
    public static final int Col_RunMode = 3 ;//总表：是否测试yes?
    public static final String Sheet_TestSteps = "Sheet1";//步骤表
    public static final String Sheet_TestCases = "Sheet";//总表
    public static final int Col_Result = 4;//总表结果
    public static final int Col_DataSet = 8;//步骤表参数
    public static final int Col_TestStepResult = 12;//步骤表结果
    public static final String KEYWORD_FAIL = "FAIL";
    public static final String KEYWORD_PASS = "PASS";
   
	public  String getPathTestexcel() {
    	if(key) {
    		Path_TestData = "C://Users//wflk//Desktop//AutomateTestCases.xlsx";
    		}
    	else {
    		Path_TestData = DriverScript.class.getResource("/AutomateTestCases.xlsx").getFile();
    	}
  		return Path_TestData;
  	}
	 public String getCodeip() {
		 //测试地址
		 if(key) {
			 codeip="https://test-service-aiot.wondfo.info/user/verfycode?id=";
		 }
		 else {
			 codeip="https://test-service-aiot.wondfo.info/user/verfycode?id=";
		 }
			return codeip;
		}
    

}
