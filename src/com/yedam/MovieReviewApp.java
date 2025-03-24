package com.yedam;

import java.util.List;

import com.yedam.movie.Movie;
import com.yedam.movie.MovieDAO;
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
	// 로그인 부분==============================================================

	// 영화 부분================================================================

	// 영화 등록
	public boolean addMovie(String title, String director, String date, String genre, String plot) {
		Movie movie = new Movie(title, director, date, genre, plot);
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

	// 영화 부분================================================================

}//
