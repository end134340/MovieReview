package com.yedam.review;

public class Review {

	private int reviewCode;
	private int movieCode;
	private String userId;
	private String review;
	private double star;
	private String edit;

	Review() {
	}

	public Review(int movieCode, String userId, String review, int star) {
		this.movieCode = movieCode;
		this.userId = userId;
		this.review = review;
		this.star = star;
	}

	public Review(int reviewCode, String review, int star, String edit) {
		this.reviewCode = reviewCode;
		this.review = review;
		this.star = star;
		this.edit = edit;
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
