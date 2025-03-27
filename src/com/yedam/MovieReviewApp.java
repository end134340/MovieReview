package com.yedam;

import java.util.List;

import com.yedam.movie.FineMovie;
import com.yedam.movie.Movie;
import com.yedam.movie.MovieDAO;
import com.yedam.review.Review;
import com.yedam.review.ReviewDAO;
import com.yedam.user.User;
import com.yedam.user.UserDAO;

public class MovieReviewApp {
	MovieDAO mdao = new MovieDAO();
	ReviewDAO rdao = new ReviewDAO();
	UserDAO udao = new UserDAO();

	// 로그인 부분==============================================================
	// 회원가입
	public boolean signUp(String uid, String upw, String uname) {
		User user = new User(uid, upw, uname);
		if (udao.addUser(user)) {
			return true;
		}
		return false;
	}

	// 로그인
	public boolean login(String uid, String upw) {
		User user = new User(uid, upw);
		if (udao.loginUser(user)) {
			return true;
		}
		return false;
	}

	// 아이디 중복체크
	public boolean checkId(String uid) {
		if (udao.idCheck(uid)) {
			return false;
		}
		return true;
	}

	// 닉네임 중복체크
	public boolean checkName(String name) {
		if (udao.nameCheck(name)) {
			return false;
		}
		return true;
	}

	// 아이디로 닉네임 알아오기
	public String myName(String uid) {
		return udao.whoami(uid);
	}

	// 영화 부분================================================================

	// 영화 등록
	public boolean addMovie(String title, String director, String date, String genre, String plot) {
		Movie movie = new Movie();
		movie.setTitle(title);
		movie.setDirector(director);
		movie.setDate(date);
		movie.setGenre(genre);
		movie.setPlot(plot);
		if (mdao.insertMovie(movie)) {
			return true;
		}

		return false;
	}

	// 영화 검색
	public List<Movie> movieList(String title) {
		return mdao.movieSearchList(title);
	}

	// 영화 단건 뿌려주기
	public Movie selectMovie(int code) {
		if (mdao.movieSelect(code) != null) {
			return mdao.movieSelect(code);
		}
		return null;
	}

	// 관심영화 조회 메소드
	public String favorit(String id, int code) {
		FineMovie fm = new FineMovie();
		fm.setMovieCode(code);
		fm.setUserId(id);
		String result = mdao.favoritMovie(fm);
		return result;
	}
	
	//fine값이 자꾸 true로 나와서 따로 뺌
	public String favoritMark(String id, int code) {
		String str = "♡";

		String fine = favorit(id, code);
		if (fine == null) {
			str = "♡";
		} else {
			if (fine.equals("FALSE")) {
				str = "♡";
			} else if (fine.equals("TRUE")) {
				str = "♥";
			}
		}
		return "          관심영화 " + str;
	}

	// 관심 영화 최초 등록
	public boolean addFavoritMovie(int code, String id) {
		FineMovie fm = new FineMovie();
		fm.setFine("TRUE");
		fm.setMovieCode(code);
		fm.setUserId(id);
		if (mdao.favoritMovieInsert(fm)) {
			return true;
		}

		return false;
	}

	// 관심영화 토글
	public boolean modifyFavoritMovie(int code, String id) {
		FineMovie fm = new FineMovie();
		fm.setMovieCode(code);
		fm.setUserId(id);
		String result = favorit(id, code);

		if (result.equals("TRUE")) {
			fm.setFine("FALSE");
		} else if (result.equals("FALSE")) {
			fm.setFine("TRUE");
		}

		if (mdao.favoritMovieEdit(fm)) {
			return true;
		}
		return false;
	}

	// 관심영화 리스트
	public List<Movie> favoritMovieList(String id) {
		return mdao.movieFavoritList(id);
	}

	// 리뷰 부분================================================================

	// 리뷰 리스트
	public List<Review> reviewList(int code) {
		return rdao.ReviewList(code);
	}

	// 리뷰 등록
	public boolean addReview(int code, String id, String review, double star) {
		Review rv = new Review();
		rv.setMovieCode(code);
		rv.setUserId(id);
		rv.setReview(review);
		rv.setStar(star);
		if (rdao.addReview(rv)) {
			return true;
		}
		return false;
	}

	// 리뷰 수정
	public boolean modifyReview(int code, String review, double star, String id) {
		Review rv = new Review();
		rv.setReviewCode(code);
		rv.setReview(review);
		rv.setStar(star);
		rv.setUserId(id);
		if (rdao.modifyReview(rv)) {
			return true;
		}
		return false;
	}

	// 리뷰 삭제
	public boolean removeReview(int code, String id) {
		Review rv = new Review();
		rv.setReviewCode(code);
		rv.setUserId(id);
		if (rdao.deleteReview(rv)) {
			return true;
		}
		return false;
	}

	// 관리자용 삭제
	public boolean adminDelete(int code, String review) {
		Review rv = new Review();
		rv.setReviewCode(code);
		rv.setReview(review);
		if (rdao.deleteAdminReview(rv)) {
			return true;
		}
		return false;
	}

	// 자기가 쓴 리뷰 목록
	public List<Review> ownReviewList(String id) {
		return rdao.myReviewList(id);
	}

}//
