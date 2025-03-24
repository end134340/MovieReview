package com.yedam.review;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReviewDAO {

	Connection getConnect() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String userId = "scott";
		String userPw = "tiger";

		try {
			Connection conn = DriverManager.getConnection(url, userId, userPw);
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	// 리뷰등록
	public boolean addReview(Review review) {
		Connection conn = getConnect();
		String sql = "INSERT INTO TBL_REVIEW ( review_code " //
				+ "                         , movie_code " //
				+ "                         , user_id " //
				+ "                         , review " //
				+ "                         , star ) " //
				+ "VALUES ( review_seq.nextval " //
				+ "          , ? " //
				+ "          , ? " //
				+ "          , ? " //
				+ "          , ? ) ";

		try {
			PreparedStatement prst = conn.prepareStatement(sql);

			prst.setInt(1, review.getMovieCode());
			prst.setString(2, review.getUserId());
			prst.setString(3, review.getReview());
			prst.setDouble(4, review.getStar());

			int r = prst.executeUpdate();

			if (r > 0) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	// 리뷰수정
	public boolean modifyReview(Review review) {
		Connection conn = getConnect();
		String sql = "UPDATE TBL_REVIEW " //
				+ "   SET    review = ? " //
				+ "          , star = NVL( ? , star ) " //
				+ "          , edit = 'true' " //
				+ "   WHERE  review_code = ? ";

		try {
			PreparedStatement prst = conn.prepareStatement(sql);

			prst.setString(1, review.getReview());
			prst.setDouble(2, review.getStar());
			prst.setInt(3, review.getReviewCode());

			int r = prst.executeUpdate();
			if (r > 0) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	// 리뷰삭제
	public boolean deleteReview(Review review) {
		Connection conn = getConnect();
		String sql = "DELETE FROM TBL_REVIEW " //
				+ "   WHERE  review_code = ? ";
		if (!review.getUserId().equals("admin")) {
			sql += "   AND    user_id = ?";
		}

		try {
			PreparedStatement prst = conn.prepareStatement(sql);
			prst.setInt(1, review.getReviewCode());
			if (!review.getUserId().equals("admin")) {
				prst.setString(2, review.getUserId());
			}
			int r = prst.executeUpdate();

			if (r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}//
