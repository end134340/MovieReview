package com.yedam.movie;

public class FineMovie {
	private int movieCode;
	private String userId;
	private String fine;

	FineMovie() {
	}

	public FineMovie(int movieCode, String userId, String fine) {
		super();
		this.movieCode = movieCode;
		this.userId = userId;
		this.fine = fine;
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

	public String getFine() {
		return fine;
	}

	public void setFine(String fine) {
		this.fine = fine;
	}

}
