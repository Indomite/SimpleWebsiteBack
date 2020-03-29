package com.homyit.util;

import java.sql.*;

/****
 * ���ݿ�������
 * @author 1979
 *
 */

public class Dbmanage {

	//������
	public Connection initDB() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://172.0.0.1/homyit?useUnicode=true&characterEncoding=utf-8&useSSL=FALSE&serverTimezone=UTC";
            conn = DriverManager.getConnection(url, "root", "");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
 
    //�ر��޽����
    public void closeDB(PreparedStatement pstmt, Connection con) {
        try {
        	if(pstmt!=null) {
				pstmt.close();
			}
			if(con!=null) {
				con.close();
			}
        } catch (SQLException e) {
            e.printStackTrace();
        }
 
    }
 
    //�ر��н����
    public void closeDB(ResultSet rs, PreparedStatement pstmt, Connection con) {
        try {
        	if(pstmt!=null) {
				pstmt.close();
			}
			if(con!=null) {
				con.close();
			}
			if(rs!=null)
			{
				rs.close();
			}
        } catch (SQLException e) {
            e.printStackTrace();
        }
 
    }

}

