package com.yedam.movie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO {

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

	public boolean insertMovie(Movie movie) {

		Connection conn = getConnect();
		String sql = "INSERT INTO TBL_MOVIE ( movie_code " //
				+ "                           , title " //
				+ "                           , director " //
				+ "                           , release_date " //
				+ "                           , genre " //
				+ "                           , plot ) " //
				+ "   VALUES ( movie_seq.nextval " //
				+ "            , ? )" //
				+ "            , ? " //
				+ "            , ? " //
				+ "            , ? " //
				+ "            , ? )";

		try {
			PreparedStatement prst = conn.prepareStatement(sql);
			prst.setString(1, movie.getTitle());
			prst.setString(2, movie.getDirector());
			prst.setString(3, movie.getDate());
			prst.setString(4, movie.getGenre());
			prst.setString(5, movie.getPlot());

			int r = prst.executeUpdate();
			if (r > 0) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	// 영화 이름으로 검색
	public List<Movie> movieSearchList(String title) {
		List<Movie> list = new ArrayList<Movie>();
		Connection conn = getConnect();
		String sql = "SELECT  m.movie_code "//
				+ "		      , m.title "//
				+ "		      , m.director "//
				+ "		      , m.release_date "//
				+ "		      , m.genre "//
				+ "           , TO_CHAR(AVG(r.star), '9.9') AS star "//
				+ "    FROM    TBL_MOVIE m LEFT JOIN TBL_REVIEW r "//
				+ "                           ON m.movie_code = r.movie_code "//
				+ "    WHERE   title = ? "//
				+ "    GROUP BY m.movie_code, m.title, m.director, m.release_date, m.genre";//

		try {
			PreparedStatement prst = conn.prepareStatement(sql);
			prst.setString(1, title);
			ResultSet rs = prst.executeQuery();
			while (rs.next()) {
				Movie movie = new Movie();
				movie.setMovieCode(rs.getInt("movie_code"));
				movie.setTitle(rs.getString("title"));
				movie.setDirector(rs.getString("director"));
				movie.setDate(rs.getString("release_date"));
				movie.setGenre(rs.getString("genre"));
				movie.setStar(rs.getDouble("star"));
				list.add(movie);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	// 영화 단건 출력
	public Movie movieSelect(int code) {
		Connection conn = getConnect();
		String sql = "SELECT   m.movie_code "//
				+ "		      , m.title "//
				+ "		      , m.director "//
				+ "		      , m.release_date "//
				+ "		      , m.genre"
				+ "           , m.plot "//
				+ "           , TO_CHAR(AVG(r.star), '9.9') AS star " //
				+ "           , f.fine "//
				+ "    FROM    TBL_MOVIE m LEFT JOIN TBL_REVIEW r "//
				+ "                           ON m.movie_code = r.movie_code " //
				+ "                        LEFT JOIN TBL_FINEMOVIE f "//
				+ "                           ON m.movie_code = f.movie_code "//
				+ "    WHERE   m.movie_code = ? "//
				+ "    GROUP BY m.movie_code, m.title, m.director, m.release_date, m.genre, m.plot, f.fine";

		try {
			PreparedStatement prst = conn.prepareStatement(sql);
			prst.setInt(1, code);
			ResultSet rs = prst.executeQuery();
			while (rs.next()) {
				Movie movie = new Movie();
				movie.setMovieCode(rs.getInt("movie_code"));
				movie.setTitle(rs.getString("title"));
				movie.setDirector(rs.getString("director"));
				movie.setDate(rs.getString("release_date"));
				movie.setGenre(rs.getString("genre"));
				movie.setPlot(rs.getString("plot"));
				movie.setStar(rs.getDouble("star"));
				movie.setFine(rs.getString("fine"));
				return movie;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	// 관심영화 리스트
	public List<Movie> movieFavoritList(String userId) {
		List<Movie> list = new ArrayList<Movie>();
		Connection conn = getConnect();
		String sql = "SELECT  m.movie_code "//
				+ "		      , m.title "//
				+ "		      , m.director "//
				+ "		      , m.release_date "//
				+ "		      , m.genre "//
				+ "           , TO_CHAR(AVG(r.star), '9.9') AS star " //
				+ "           , f.fine "//
				+ "    FROM    TBL_MOVIE m LEFT JOIN TBL_REVIEW r "//
				+ "                           ON m.movie_code = r.movie_code " //
				+ "                        LEFT JOIN TBL_FINEMOVIE f "//
				+ "                           ON m.movie_code = f.movie_code "//
				+ "    WHERE   f.user_id = ? " //
				+ "    AND     LOWER(fine) = 'true'"//
				+ "    GROUP BY m.movie_code, m.title, m.director, m.release_date, m.genre, f.fine";

		try {
			PreparedStatement prst = conn.prepareStatement(sql);
			prst.setString(1, userId);
			ResultSet rs = prst.executeQuery();
			while (rs.next()) {
				Movie movie = new Movie();
				movie.setMovieCode(rs.getInt("movie_code"));
				movie.setTitle(rs.getString("title"));
				movie.setDirector(rs.getString("director"));
				movie.setDate(rs.getString("release_date"));
				movie.setGenre(rs.getString("genre"));
				movie.setStar(rs.getDouble("star"));
				movie.setFine(rs.getString("fine"));
				list.add(movie);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	// 관심영화 등록(최초)
	public boolean favoritMovie(FineMovie fm) {
		Connection conn = getConnect();
		String sql = "INSERT INTO tbl_finemovie ( movie_code "//
				+ "                               , user_id "//
				+ "                               , fine ) " //
				+ "   VALUES       (  ? " //
				+ "                 , ? " //
				+ "                 , ?)";

		try {
			PreparedStatement prst = conn.prepareStatement(sql);

			prst.setInt(1, fm.getMovieCode());
			prst.setString(2, fm.getUserId());
			prst.setString(3, fm.getFine());

			int r = prst.executeUpdate();
			if (r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
	
	//관심영화 토글
	public boolean favoritMovieEdit(FineMovie fm) {
		Connection conn = getConnect();
		String sql = "UPDATE tbl_finemovie" //
				+ "   SET    fine = ? " //
				+ "   WHERE  movie_code = ? " //
				+ "     AND  user_id = ?";

		try {
			PreparedStatement prst = conn.prepareStatement(sql);

			prst.setString(1, fm.getFine());
			prst.setInt(2, fm.getMovieCode());
			prst.setString(3, fm.getUserId());

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
