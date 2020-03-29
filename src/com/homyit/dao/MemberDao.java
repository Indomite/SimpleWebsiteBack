package com.homyit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.homyit.entity.MemberEntity;
import com.homyit.util.Dbmanage;


public class MemberDao {

	/**
	 * 查询所有成员信息
	 */
	
	public List<MemberEntity> findAll() {
		Dbmanage dbmanage = new Dbmanage();
		//MemberEntity memberEntity = new MemberEntity();
		Connection con = null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		List<MemberEntity> list = new ArrayList<MemberEntity>();
		String sql = "select * from member";
		try {
			con = dbmanage.initDB();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				MemberEntity memberEntity = new MemberEntity();
				memberEntity.setId(rs.getInt("id"));
				memberEntity.setName(rs.getString("name"));
				memberEntity.setGroup(rs.getString("group"));
				memberEntity.setHobby(rs.getString("hobby"));
				memberEntity.setInfo(rs.getString("info"));
				memberEntity.setImg(rs.getString("img"));
				
				list.add(memberEntity);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbmanage.closeDB(rs,pstmt, con);
		}
		return list;
	}
	
	/**
	 * 根据id查询成员信息
	 * @param id
	 * @return
	 */
	
	public List<MemberEntity> findById(String id) {
		Dbmanage dbmanage = new Dbmanage();
		Connection con = null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		//MemberEntity memberEntity = new MemberEntity();
		List<MemberEntity> list = new ArrayList<MemberEntity>();
		String sql = "select * from member where id=?";
		try {
			con = dbmanage.initDB();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				MemberEntity memberEntity = new MemberEntity();
				memberEntity.setId(rs.getInt("id"));
				memberEntity.setName(rs.getString("name"));
				memberEntity.setGroup(rs.getString("group"));
				memberEntity.setHobby(rs.getString("hobby"));
				memberEntity.setInfo(rs.getString("info"));
				memberEntity.setImg(rs.getString("img"));
				list.add(memberEntity);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbmanage.closeDB(rs,pstmt, con);
		}
		return list;
	}
	
}
