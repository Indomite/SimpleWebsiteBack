package com.homyit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.homyit.entity.MessageEntity;
import com.homyit.util.Dbmanage;

/****
 * 留言
 * @author 1979
 *
 */
public class MessageDao {
	/***
	 * 新增留言
	 */
	public boolean insert(MessageEntity Message) {
		Dbmanage dbmanage = new Dbmanage();
		Connection con = null;
		PreparedStatement pstmt =null;
		String sql = "insert into message(messageid,content,author) VALUES(?,?,?)";
		boolean res = false;
		try {
			con = dbmanage.initDB();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,Message.getMessageid());
			pstmt.setString(2,Message.getContent());
			pstmt.setString(3,Message.getAuthor());
			res = (pstmt.executeUpdate() == 1);
		}catch (SQLException e) {
				e.printStackTrace();
		}finally {
			dbmanage.closeDB(pstmt, con);
		}
		return res;
	}
	
	/***
	 * 根据messageid删除留言
	 */
	public boolean delete(String messageid) {
			
		Dbmanage dbmanage = new Dbmanage();
		Connection con = null;
		PreparedStatement pstmt =null;
		String sql = "delete from message where messageid = ?";
		boolean flag = false;
			
		try {
			con = dbmanage.initDB();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, messageid);
			int result = pstmt.executeUpdate();
			if (result > 0) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbmanage.closeDB(pstmt, con);
		}
		return flag;
	}
	
	/**
	 * 查询所有留言信息
	 */
	
	public List<MessageEntity> findAll() {
		Dbmanage dbmanage = new Dbmanage();
		//MessageEntity messageEntity = new MessageEntity();
		Connection con = null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		List<MessageEntity> list = new ArrayList<MessageEntity>();
		String sql = "select * from message";
		try {
			con = dbmanage.initDB();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				MessageEntity messageEntity = new MessageEntity();
				messageEntity.setMessageid(rs.getInt("messageid"));
				messageEntity.setAuthor(rs.getString("author"));
				messageEntity.setContent(rs.getString("content"));
				messageEntity.setCreate_time(rs.getTimestamp("create_time"));
				
				list.add(messageEntity);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbmanage.closeDB(rs,pstmt, con);
		}
		return list;
	}
	
	/**
	 * 根据id查询留言信息
	 * @param id
	 * @return
	 */
	
	public List<MessageEntity> findById(String messageid) {
		Dbmanage dbmanage = new Dbmanage();
		Connection con = null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		//MessageEntity messageEntity = new MessageEntity();
		List<MessageEntity> list = new ArrayList<MessageEntity>();
		String sql = "select * from message where messageid=?";
		try {
			con = dbmanage.initDB();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, messageid);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				MessageEntity messageEntity = new MessageEntity();
				messageEntity.setMessageid(rs.getInt("messageid"));
				messageEntity.setAuthor(rs.getString("author"));
				messageEntity.setContent(rs.getString("content"));
				messageEntity.setCreate_time(rs.getTimestamp("create_time"));
				
				list.add(messageEntity);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbmanage.closeDB(rs,pstmt, con);
		}
		return list;
	}
	
	/***
	 * 获取留言总数
	 */
	public int getCount() {
		int count = 0;
		Dbmanage dbmanage = new Dbmanage();
		Connection con = null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		String sql = "select count(messageid) as total from message ";
		try {
			con = dbmanage.initDB();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt("total");
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbmanage.closeDB(rs,pstmt, con);
		}
		return count;
		
	}
	/**
	 * 
	 * @param index  当前页的起始索引
	 * @param pageSize  每页显示条数
	 * @return
	 */
	public List<MessageEntity> findAll(int index, int pageSize) {
		Dbmanage dbmanage = new Dbmanage();
		
		Connection con = null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		List<MessageEntity> list = new ArrayList<MessageEntity>();
		String sql = "select * from message order by create_time desc limit ?,?";
		try {
			con = dbmanage.initDB();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, index);
			pstmt.setInt(2, pageSize);
			rs = pstmt.executeQuery();
			 
			while (rs.next()) {
				MessageEntity messageEntity = new MessageEntity();//放在外面会使输出内容相同
				messageEntity.setMessageid(rs.getInt("messageid"));
				messageEntity.setCreate_time(rs.getTimestamp("create_time"));
				messageEntity.setAuthor(rs.getString("author"));
				messageEntity.setContent(rs.getString("content"));
				
				list.add(messageEntity);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbmanage.closeDB(rs,pstmt, con);
		}
		return list;
	}
	
}
