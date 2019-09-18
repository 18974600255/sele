package com.cyl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.cyl.tools.LinkSql;


public class ExcelDao {
	//添加
			public int addCz(String user_name,int mk,int cz_bh,String cz_name,String cz_gn,String cz_dw,String cz_value01,String cz_value02,String cz_value03,String waitkey,String waittime){
				
				int add=0;
				Connection conn = null;
				PreparedStatement ps = null;
				conn = LinkSql.getConnection();
				String sql = "insert into project01_module0"+mk+"(user_name,cz_bh,cz_name,cz_gn,cz_dw,cz_value01,cz_value02,cz_value03,yuliu02,yuliu03) values (?,?,?,?,?,?,?,?,?,?)";
				try {
					ps = conn.prepareStatement(sql);
					ps.setString(1, user_name);
					ps.setInt(2, cz_bh);
					ps.setString(3, cz_name);
					ps.setString(4, cz_gn);
					ps.setString(5, cz_dw);
					ps.setString(6, cz_value01);
					ps.setString(7, cz_value02);
					ps.setString(8, cz_value03);
					ps.setString(9, waitkey);
					ps.setString(10, waittime);
					add=ps.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally{
					if(ps != null && !"".equals(ps)){
						try {
							ps.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					if(conn != null && !"".equals(conn)){
						try {
							conn.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}
				return add;
			}
}
