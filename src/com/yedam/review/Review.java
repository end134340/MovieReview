package com.yedam.review;

import com.yedam.user.UserDAO;

public class Review {

	private int reviewCode;
	private int movieCode;
	private String userId;
	private String review;
	private double star;
	private String edit;

	public Review() {
	}

	public Review(int movieCode, String userId, String review, double star) {
		this.movieCode = movieCode;
		this.userId = userId;
		this.review = review;
		this.star = star;
	}

	public Review(int reviewCode, String review, double star, String userId) {
		this.reviewCode = reviewCode;
		this.userId = userId;
		this.review = review;
		this.star = star;
	}

	public String showReviewList() {
		UserDAO dao = new UserDAO();
		String stars = "";
		String str = "";
		
		for (int i = 1; i <= star; i++) {
			stars += "★";
		}
		while (stars.length() < 5) {
			stars += "☆";
		}

		if (edit.equals("TRUE")) {
			str = "*";
		}

		return reviewCode + ")" + str + "  " + dao.whoami(userId) + "  " + stars + "       " + review;
	}

	public int getReviewCode() {
		return reviewCode;
	}

	public void setReviewCode(int reviewCode) {
		this.reviewCode = reviewCode;
	}

	public int getMovieCode() {
		return movieCode;
	}

	public void setMovieCode(int movieCode) {
		this.movieCode = movieCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public double getStar() {
		return star;
	}

	public void setStar(double star) {
		this.star = star;
	}

	public String getEdit() {
		return edit;
	}

	public void setEdit(String edit) {
		this.edit = edit;
	};

}
