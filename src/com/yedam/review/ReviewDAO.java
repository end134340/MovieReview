package com.yedam.review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yedam.MovieDBMS;

public class ReviewDAO {

	MovieDBMS db = new MovieDBMS();

	// 리뷰등록
	public boolean addReview(Review review) {
		Connection conn = db.getConnect();
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
		Connection conn = db.getConnect();
		String sql = "UPDATE TBL_REVIEW " //
				+ "   SET    review = ? " //
				+ "          , star = NVL( ? , star ) " //
				+ "          , edit = 'TRUE' " //
				+ "   WHERE  review_code = ? " //
				+ "   AND    user_id = ?";

		try {
			PreparedStatement prst = conn.prepareStatement(sql);

			prst.setString(1, review.getReview());
			prst.setDouble(2, review.getStar());
			prst.setInt(3, review.getReviewCode());
			prst.setString(4, review.getUserId());

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
		Connection conn = db.getConnect();
		String sql = "DELETE FROM TBL_REVIEW " //
				+ "   WHERE  review_code = ? " //
				+ "   AND    user_id = ?";

		try {
			PreparedStatement prst = conn.prepareStatement(sql);
			prst.setInt(1, review.getReviewCode());
			prst.setString(2, review.getUserId());

			int r = prst.executeUpdate();

			if (r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// 리뷰목록
	public List<Review> ReviewList(int code) {
		Connection conn = db.getConnect();
		List<Review> list = new ArrayList<Review>();
		String sql = "SELECT  review_code " //
				+ "           , movie_code " //
				+ "           , user_id " //
				+ "           , review" //
				+ "           , TO_CHAR(star, '9.9') AS star " //
				+ "           , edit " //
				+ "   FROM    TBL_REVIEW " //
				+ "   WHERE   movie_code = ? ";

		try {
			PreparedStatement prst = conn.prepareStatement(sql);
			prst.setInt(1, code);

			ResultSet rs = prst.executeQuery();

			while (rs.next()) {
				Review rv = new Review();

				rv.setReviewCode(rs.getInt("review_code"));
				rv.setMovieCode(rs.getInt("movie_code"));
				rv.setUserId(rs.getString("user_id"));
				rv.setReview(rs.getString("review"));
				rv.setStar(rs.getDouble("star"));
				rv.setEdit(rs.getString("edit"));

				list.add(rv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 관리자용 삭제(수정)
	public boolean deleteAdminReview(Review review) {
		Connection conn = db.getConnect();
		String sql = "UPDATE TBL_REVIEW " //
				+ "   SET    review = ? "//
				+ "          , star = null "//
				+ "   WHERE  review_code = ? ";

		try {
			PreparedStatement prst = conn.prepareStatement(sql);
			prst.setString(1, review.getReview());
			prst.setInt(2, review.getReviewCode());
			int r = prst.executeUpdate();
			if (r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	// 자신이 쓴 리뷰 목록 보기
	public List<Review> myReviewList(String id) {
		List<Review> list = new ArrayList<Review>();
		Connection conn = db.getConnect();
		String sql = "SELECT review " //
				+ "          , movie_code " //
				+ "          , star " //
				+ "          , edit " //
				+ "          , review_code " //
				+ "          , user_id" //
				+ "  FROM    tbl_review " //
				+ "  WHERE   user_id = ? ";

		try {
			PreparedStatement prst = conn.prepareStatement(sql);
			prst.setString(1, id);
			ResultSet rs = prst.executeQuery();
			while (rs.next()) {
				Review rv = new Review();
				rv.setReview(rs.getString("review"));
				rv.setMovieCode(rs.getInt("movie_code"));
				rv.setStar(rs.getDouble("star"));
				rv.setEdit(rs.getString("edit"));
				rv.setReviewCode(rs.getInt("review_code"));
				rv.setUserId(rs.getString("user_id"));
				list.add(rv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

}//
