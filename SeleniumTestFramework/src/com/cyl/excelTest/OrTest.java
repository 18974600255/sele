package com.cyl.excelTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class OrTest {
	public static Properties p;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		// 创建一个文件输入流对象，参数来源外部OR.txt文件
		FileInputStream fs = new FileInputStream(".//object/OR.txt");
		// 创建一个Properties对象
		p = new Properties(System.getProperties());
		// 加载全部对象仓库文件
		p.load(fs);

		String username = p.getProperty("lastLoginUsername");
		System.out.println("lastLoginUsername=" + username);
		// 修改
		 update("lastLoginUsername", "cyl");
		username = p.getProperty("lastLoginUsername");
		System.out.println("lastLoginUsername=" + username);

	}

	// 修改
	public static void update(String key, String value) {
		p.setProperty(key, value);
		FileOutputStream os = null;
		try {
			os = new FileOutputStream(".//object/OR.txt");
			// 将Properties中的属性列表（键和元素对）写入输出流
			p.store(os, "chenyonglin");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	
}
