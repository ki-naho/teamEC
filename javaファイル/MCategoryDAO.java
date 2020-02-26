package com.internousdev.rose.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.internousdev.rose.dto.MCategoryDTO;
import com.internousdev.rose.util.DBConnector;

public class MCategoryDAO {

	public List<MCategoryDTO> selectCategoryList() throws SQLException{

		List<MCategoryDTO> mCategoryDTOList = new ArrayList<MCategoryDTO>();
		DBConnector db = new DBConnector();
		Connection con =db.getConnection();

		String sql ="SELECT category_id,category_name FROM m_category ORDER BY id ASC";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				MCategoryDTO dto = new MCategoryDTO();
				dto.setCategoryId(rs.getString("category_id"));
				dto.setCategoryName(rs.getString("category_name"));
				mCategoryDTOList.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return mCategoryDTOList;
	}
}
