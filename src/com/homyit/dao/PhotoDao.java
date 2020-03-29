package com.homyit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.homyit.entity.PhotoEntity;

import com.homyit.util.Dbmanage;

/**
 * 上传图片
 * @author 1979
 *
 */
public class PhotoDao {

	public List<PhotoEntity> findAll() {
		Dbmanage dbmanage = new Dbmanage();
		Connection con = null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		List<PhotoEntity> list = new ArrayList<PhotoEntity>();
		String sql = "select * from img";
		try {
			con = dbmanage.initDB();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				PhotoEntity photoEntity = new PhotoEntity();
				photoEntity.setId(rs.getInt("id"));
				photoEntity.setImgUrl(rs.getString("imgUrl"));
				
				list.add(photoEntity);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbmanage.closeDB(rs,pstmt, con);
		}
		return list;
	}
	
	/**
	 * 根据file_id获取图片
	 * @param fiel_id
	 * @return
	 */
	public List<PhotoEntity> findById(String file_id) {
		Dbmanage dbmanage = new Dbmanage();
		Connection con = null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		
		List<PhotoEntity> list = new ArrayList<PhotoEntity>();
		String sql = "select * from img where id=?";
		try {
			con = dbmanage.initDB();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, file_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				PhotoEntity photoEntity = new PhotoEntity();
				photoEntity.setId(rs.getInt("id"));
				photoEntity.setImgUrl(rs.getString("imgUrl"));
				
				list.add(photoEntity);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbmanage.closeDB(rs,pstmt, con);
		}
		return list;
	}
}
