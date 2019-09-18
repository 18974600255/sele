package com.cyl.excelTest;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import com.cyl.dao.TestServlet;

import java.util.concurrent.TimeUnit;

public class ActionsKeywords {

	public static WebDriver driver;
	private static Actions action;
	
	
	  public static void 打开浏览器(String object, String value01) {
	  
	  Log.info("打开chrome。"); 
	  try{ 
		  
		if(Constants.key) {
              if(Constants.ts) {
      			ChromeOptions chromeOptions=new ChromeOptions();
        	    chromeOptions.setHeadless(Boolean.TRUE);
      	        driver = new ChromeDriver(chromeOptions);
			}else {
		        driver = new ChromeDriver();
			}
			
			
		}else {
			//System.setProperty("webdriver.chrome.driver", DriverScript.class.getResource("/chromedriver").getFile());
			ChromeOptions chromeOptions=new ChromeOptions();
  	        chromeOptions.setHeadless(Boolean.TRUE);
		    chromeOptions.addArguments("--headless");
			chromeOptions.addArguments("--whitelisted-ips");
			chromeOptions.addArguments("--no-sandbox");
			chromeOptions.addArguments("--disable-extensions");
			driver = new ChromeDriver(chromeOptions);
		  }
	  
	  }catch
	  (Exception e){
	  Log.error("无法打开浏览器 --- " + e.getMessage());
	  DriverScript.bResult = false; 
	  } 
	  }
	  
	  
	  
	//元素操作
	  public static void 清除文本内容(String object, String value01) {
			try {
				Log.info("清除文本内容");
				driver.findElement(By.xpath(object)).clear();
			} catch (Exception e) {
				Log.error("无法清除文本内容 ---- " + e.getMessage());
				DriverScript.bResult = false;
			}
		}
	  public static void 键盘清除文本内容(String object, String value01) {
		  WebElement  el=driver.findElement(By.xpath(object));
			try {
				Log.info("键盘清除文本内容");
			    el.sendKeys(Keys.CONTROL,"a");
			    el.sendKeys(Keys.BACK_SPACE);
				
			} catch (Exception e) {
				Log.error("无法键盘清除文本内容 ---- " + e.getMessage());
				DriverScript.bResult = false;
			}
		}
	  public static void 输入(String object, String value01) {
			try {
				Log.info("开始  " + object + "输入");
				driver.findElement(By.xpath(object)).sendKeys(value01);
			} catch (Exception e) {
				Log.error("无法输入-- " + e.getMessage());
				DriverScript.bResult = false;
			}
		}
		public static void 点击(String object, String value01) {
			try {
				Log.info("点击 " + object);
				driver.findElement(By.xpath(object)).click();
			} catch (Exception e) {
				Log.error("无法点击-- " + e.getMessage());
				DriverScript.bResult = false;
			}
		}

		public static void 模拟回车操作(String object, String value01) {
			try {
				Log.info("模拟回车操作");
				driver.findElement(By.xpath(object)).submit();
			} catch (Exception e) {
				Log.error("无法模拟回车操作 ---- " + e.getMessage());
				DriverScript.bResult = false;
			}
		}
		public static void 下拉框选择(String object, String value01) {
			try {
				Log.info("下拉框选择");
				Select sel = new Select(driver.findElement(By.xpath(object)));
				sel.selectByValue(value01);
			} catch (Exception e) {
				Log.error("无法下拉框选择 ---- " + e.getMessage());
				DriverScript.bResult = false;
			}
		}
		public static void Tab键(String object, String value01) {
			try {
				Log.info("Tab键");
				driver.findElement(By.xpath(object)).sendKeys(Keys.TAB);
				
			} catch (Exception e) {
				Log.error("无法Tab键 ---- " + e.getMessage());
				DriverScript.bResult = false;
			}
		}
		public static void 强制等待(String object, String value01) {
			try {
				Log.info("强制等待");
				Thread.sleep(Integer.valueOf(value01).intValue());
			} catch (Exception e) {
				Log.error("无法强制等待--- " + e.getMessage());
				DriverScript.bResult = false;
			}
		}
	  
//浏览器操作-----
	  
	public static void 打开网页(String object, String value01) {
		try {
			Log.info("打开测试地址");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.get(object);
		} catch (Exception e) {
			Log.error("无法打开测试地址--- " + e.getMessage());
			DriverScript.bResult = false;
		}

	}
	public static void 浏览器最大化(String object, String data) {
		try {
			Log.info("浏览器最大化");
			driver.manage().window().maximize();
		} catch (Exception e) {
			Log.error("无法浏览器最大化--- " + e.getMessage());
			DriverScript.bResult = false;
		}

	}
	public static void 浏览器前进(String object, String data) {
		try {
			Log.info("浏览器前进");
			driver.navigate().forward();
		} catch (Exception e) {
			Log.error("无法浏览器前进 ---- " + e.getMessage());
			DriverScript.bResult = false;
		}
	}
	public static void 浏览器后退(String object, String data) {
		try {
			Log.info("浏览器后退");
			driver.navigate().back();
		} catch (Exception e) {
			Log.error("无法浏览器后退 ---- " + e.getMessage());
			DriverScript.bResult = false;
		}
	}
	public static void 关闭当前浏览器(String object, String data) {
		try {
			Log.info("关闭当前浏览器");
			driver.close();
		} catch (Exception e) {
			Log.error("无法关闭当前浏览器 ---- " + e.getMessage());
			DriverScript.bResult = false;
		}
	}
	public static void 彻底关闭浏览器(String object, String data) {
		try {
			Log.info("彻底关闭浏览器");
			driver.quit(); 
		} catch (Exception e) {
			Log.error("无法彻底关闭浏览器 ---- " + e.getMessage());
			DriverScript.bResult = false;
		}
	}
	public static void 隐式等待(String object, String data) {
		try {
			Log.info("隐式等待");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		} catch (Exception e) {
			Log.error("无法隐式等待--- " + e.getMessage());
			DriverScript.bResult = false;
		}

	}
	
	//键盘事件
	
	public static void 键盘ctrl(String object, String data) {
		try {
			Log.info("键盘ctrl");
			action = new Actions(driver);
			action.keyDown(Keys.CONTROL).sendKeys(data).perform();
		} catch (Exception e) {
			Log.error("无法键盘ctrl--- " + e.getMessage());
			DriverScript.bResult = false;
		}
	}
	public static void 键盘tab(String object, String data) {
		try {
			Log.info("键盘tab");
			action = new Actions(driver);
			action.keyDown(Keys.TAB).perform();
			System.out.println("tab...");
		} catch (Exception e) {
			Log.error("无法键盘tab--- " + e.getMessage());
			DriverScript.bResult = false;
		}
	}
	public static void 键盘alt(String object, String data) {
		try {
			Log.info("键盘alt");
			action = new Actions(driver);
			action.keyDown(Keys.ALT).sendKeys(data).perform();
		} catch (Exception e) {
			Log.error("无法键盘alt--- " + e.getMessage());
			DriverScript.bResult = false;
		}
	}
	public static void 键盘shift(String object, String data) {
		try {
			Log.info("键盘shift");
			action = new Actions(driver);// 鼠标
			action.keyDown(Keys.SHIFT).sendKeys(data).perform();
		} catch (Exception e) {
			Log.error("无法键盘alt--- " + e.getMessage());
			DriverScript.bResult = false;
		}
	}
	
	//验证
	public static void 验证元素文本(String object, String data) {
		String text = "";
		try {
			Log.info("验证元素文本");
			text = driver.findElement(By.xpath(object)).getText();
			if (text.equals(data) || text == data) {
				
			} else {
				DriverScript.bResult = false;
			}
		} catch (Exception e) {
			Log.error("无法验证元素文本 ---- " + e.getMessage());
			DriverScript.bResult = false;
		}
	}

	//未完善--------------------------------无法使用
	public static void 验证元素属性值(String object, String data) {
		String text = "";
		try {
			Log.info("验证元素属性值");
			text = driver.findElement(By.xpath(object)).getAttribute(data);
			//System.out.println(data+"属性得到的值："+text);
			/*
			 * if (text.equals(value) || text == value) { } else { DriverScript.bResult =
			 * false; }
			 */
		} catch (Exception e) {
			Log.error("无法验证元素属性值 ---- " + e.getMessage());
			DriverScript.bResult = false;
		}
	}
	public static void 验证元素是否可见(String object, String value01) {
		boolean getdis;
		boolean dis;
		try {
			Log.info("验证元素是否可见,预期："+value01);
			getdis = driver.findElement(By.xpath(object)).isDisplayed();
			
			if (value01.equals("可见")) {
				dis = true;
			} else {
				dis = false;
			}
			if ((getdis && dis)||(!getdis&&!dis)) {
			} else {
				DriverScript.bResult = false;
			}
		} catch (Exception e) {
			Log.error("无法验证元素是否可见 ---- " + e.getMessage());
		
		}
	}
	public static void 验证元素是否存在(String object, String value01) {
		try {
		Log.info("验证元素是否存在,预期："+value01);
		driver.findElement(By.xpath(object));
		
		if(value01.equals("不存在")) {
			DriverScript.bResult = false;
			Log.error("该元素存在！！！");
		}else {
			Log.info("该元素存在！！！");
		}
		} catch (Exception e) {
			String[] getvalueArray = e.getClass().getName().split("\\.");
			String getvalue = getvalueArray[getvalueArray.length - 1];
			if(!getvalue.equals("NoSuchElementException")) {//找不到元素也为true
				Log.error("元素不存在！！！");
				if(value01.equals("存在")) {
					DriverScript.bResult = false;
				}
			}else {
				Log.error("发生其他异常！！！");
			}
		}
	}
	
	public static void 验证页面的title(String object, String data) {
		String text = "";
		try {
			Log.info("验证页面的title");
			text = driver.getTitle();
			if (text.equals(data) || text == data) {
				
			} else {
				DriverScript.bResult = false;
			}
		} catch (Exception e) {
			Log.error("无法验证页面的title ---- " + e.getMessage());
			DriverScript.bResult = false;
		}
	}
	
	public static void 验证页面url(String object, String data) {
		String text = "";
		try {
			Log.info("验证页面的URL");
			text = driver.getCurrentUrl();
			if (text.equals(data) || text == data) {
				
			} else {
				DriverScript.bResult = false;
			}
		} catch (Exception e) {
			Log.error("无法验证页面的URL ---- " + e.getMessage());
			DriverScript.bResult = false;
		}
	}
	public static void 元素文本所包含(String object, String data) {
		String text = "";
		try {
			Log.info("包含元素文本");
			text = driver.findElement(By.xpath(object)).getText();
			if (text.contains(data)) {
				
			} else {
				DriverScript.bResult = false;
			}
		} catch (Exception e) {
			Log.error("无法验证包含元素文本 ---- " + e.getMessage());
			DriverScript.bResult = false;
		}
	}
	public static void 页面的URL所包含(String object, String data) {
		String text = "";
		try {
			Log.info("页面的URL所包含");
			text = driver.getCurrentUrl();
			if (text.contains(data)) {
				
			} else {
				DriverScript.bResult = false;
			}
		} catch (Exception e) {
			Log.error("无法验证页面的URL所包含 ---- " + e.getMessage());
			DriverScript.bResult = false;
		}
	}
	public static void 正确验证码(String object, String data) {
		try {
		String getcode =driver.findElement(By.xpath("//*[@id=\"app\"]/div/form/section/button")).getAttribute("image-id");
		String servercode= new TestServlet().doGet(new Constants().getCodeip()+getcode);
		driver.findElement(By.xpath(object)).sendKeys(servercode);
		//System.out.println("得到的验证码ID："+getcode+"   - -  得到的验证码："+servercode);
	}catch (Exception e) {
		Log.error("无法输入正确验证码 ---- " + e.getMessage());
		DriverScript.bResult = false;
	}
}
	//--------------------------------------仅框架独立功能，需要通用化设计--------------------------------------
	
	
	
	
	
}
