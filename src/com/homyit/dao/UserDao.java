package com.homyit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.homyit.entity.UserEntity;
import com.homyit.util.*;

/***
 * 用户增删改查
 * @author 1979
 *
 */

public class UserDao {
	/***
	 * 新增用户
	 */
	public boolean insert(UserEntity user) {
		Dbmanage dbmanage = new Dbmanage();
		Connection con = null;
		PreparedStatement pstmt =null;
		String sql = "INSERT INTO user(username,password,email) VALUES(?,?,?)";
		boolean res = false;
		try {
			con = dbmanage.initDB();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,user.getUsername());
			pstmt.setString(2,user.getPassword());
			pstmt.setString(3,user.getEmail());
			res = (pstmt.executeUpdate()==1);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbmanage.closeDB(pstmt, con);
		}
		return res;

	}
	
	
	/**
	 * 查找所有用户名
	 * @return
	 */
	
	public static List<String> findUsername(){
		Dbmanage dbmanage = new Dbmanage();
		Connection con = null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		List<String> list = new ArrayList<String>();
		
		try {
			con = dbmanage.initDB();
			String sql = "select username from user";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				list.add(rs.getString("username"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbmanage.closeDB(rs,pstmt, con);
		}
		return list;
	}
	
	

	/**
	 * 根据用户id删除用户信息
	 * @param userid 用户id
	 * @return 删除是否成功
	 */
	public boolean delete(Integer userid) {
		Dbmanage dbmanage = new Dbmanage();
		Connection con = null;
		PreparedStatement pstmt =null;
		String sql = "delete from user where userid= ?";
		boolean res = false;
		try {
			con = dbmanage.initDB();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,userid);
			res = (pstmt.executeUpdate()==1);
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			dbmanage.closeDB(pstmt, con);
		}
		return res;
	}

	/**
	 * 更新用户信息
	 * @param user 修改后的用户信息
	 * @return 更新是否成功
	 */
	public boolean update(UserEntity user) {
		Dbmanage dbmanage = new Dbmanage();
		Connection con = null;
		PreparedStatement pstmt =null;
		String sql = "update user set username=?,password=?,email=? where userid= ?";
		boolean res = false;
		try {
			con = dbmanage.initDB();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,user.getUsername());
			pstmt.setString(2,user.getPassword());
			pstmt.setString(3,user.getEmail());
			pstmt.setInt(4,user.getUserid());
			res = (pstmt.executeUpdate()==1);
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			dbmanage.closeDB(pstmt, con);
		}
		return res;

	}

	/**
	 * 根据用户名查找用户密码
	 * @param username 用户名
	 * @return 用户信息
	 */
	public UserEntity selectByUsername(String username) throws NullPointerException{
		List<UserEntity> list = new ArrayList<>();
		String sql = "select * from user where username=?";
		Dbmanage dbmanage = new Dbmanage();
		Connection con = null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		try {
			con = dbmanage.initDB();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			list=resultSetToBean(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbmanage.closeDB(rs,pstmt, con);
		}
		return list.isEmpty()?null:list.get(0);
	}

	/**
	 * 根据用户id查询用户信息
	 * @param userid 用户id
	 * @return 用户信息
	 */
	public UserEntity selectByUserId(Integer userid) {
		List<UserEntity> list = new ArrayList<>();
		String sql = "select * from user where userid= ?";
		Dbmanage dbmanage = new Dbmanage();
		Connection con = null;
		PreparedStatement pstmt =null;
		ResultSet rs;
		try {
			con = dbmanage.initDB();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,userid);
			rs = pstmt.executeQuery();
			list=resultSetToBean(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbmanage.closeDB(pstmt, con);
		}
		return list.isEmpty()?null:list.get(0);
	}

	/**
	 * 将查询结果转换为对象
	 * @param rs 查询结果
	 * @return 用户列表
	 * @throws SQLException
	 */
	private static List<UserEntity> resultSetToBean(ResultSet rs) throws SQLException {
		List<UserEntity> list = new ArrayList<>();
		while (rs.next()){
			UserEntity user = new UserEntity();
			user.setUserid(rs.getInt("userid"));
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			user.setEmail(rs.getString("email"));
			list.add(user);
		}
		return list;
	}
}
