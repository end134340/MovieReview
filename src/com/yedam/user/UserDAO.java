package com.yedam.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.yedam.MovieDBMS;

public class UserDAO {

	MovieDBMS db = new MovieDBMS();
	
	// 회원가입
	public boolean addUser(User user) {

		Connection conn = db.getConnect();
		String sql = "INSERT INTO TBL_USER (user_id " //
				+ "                         , user_pw " //
				+ "                         , user_name )" //
				+ "   VALUES ( ? " //
				+ "           , ? " //
				+ "           , ?)";

		try {
			PreparedStatement prst = conn.prepareStatement(sql);
			prst.setString(1, user.getUserId());
			prst.setString(2, user.getUserPw());
			prst.setString(3, user.getUserName());

			int r = prst.executeUpdate();
			if (r > 0) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;

	}

	// 유저 로그인
	public boolean loginUser(User user) {
		Connection conn = db.getConnect();
		String sql = "SELECT user_id " //
				+ "          , user_pw " //
				+ "   FROM   tbl_user " //
				+ "   WHERE  user_id = ? " //
				+ "   AND    user_pw = ? ";

		try {
			PreparedStatement prst = conn.prepareStatement(sql);
			prst.setString(1, user.getUserId());
			prst.setString(2, user.getUserPw());
			ResultSet rs = prst.executeQuery();
			if (rs.next()) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	// 유저이름 반환
	public String whoami(String userId) {
		Connection conn = db.getConnect();
		String sql = "SELECT  user_name " //
				+ "   FROM    TBL_USER" //
				+ "   WHERE   user_id = ?";

		try {
			PreparedStatement prst = conn.prepareStatement(sql);
			prst.setString(1, userId);
			ResultSet rs = prst.executeQuery();
			if (rs.next()) {
				return rs.getString("user_name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	//유저아이디 중복체크
	public boolean idCheck(String userId) {
		Connection conn = db.getConnect();
		String sql = "SELECT  user_id " //
				+ "   FROM    TBL_USER" //
				+ "   WHERE   user_id = ?";

		try {
			PreparedStatement prst = conn.prepareStatement(sql);
			prst.setString(1, userId);
			ResultSet rs = prst.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
	
	//유저 이름 중복체크
	public boolean nameCheck(String userName) {
		Connection conn = db.getConnect();
		String sql = "SELECT  user_name " //
				+ "   FROM    TBL_USER" //
				+ "   WHERE   user_name = ?";

		try {
			PreparedStatement prst = conn.prepareStatement(sql);
			prst.setString(1, userName);
			ResultSet rs = prst.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

}//
