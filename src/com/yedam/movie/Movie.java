package com.yedam.movie;

public class Movie {

	private int movieCode;
	private String title;
	private String director;
	private String date;
	private String genre;
	private String plot;
	private double star;
	private String fine;

	public Movie() {
	}

	public Movie(String title, String director, String date, String genre, String plot) {
		this.title = title;
		this.director = director;
		this.date = date;
		this.genre = genre;
		this.plot = plot;
	}

	public int getMovieCode() {
		return movieCode;
	}

	public void setMovieCode(int movieCode) {
		this.movieCode = movieCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getPlot() {
		return plot;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}

	public double getStar() {
		return star;
	}

	public void setStar(double star) {
		this.star = star;
	}

	public String getFine() {
		return fine;
	}

	public void setFine(String fine) {
		this.fine = fine;
	}

	public String showList() {
		String tspace = "";
		String dspace = "";

		if (title.length() >= 17) {
			tspace = "             ";
		} else if (title.length() >= 5) {
			tspace = "                          ";
		} else {
			tspace = "                               ";
		}

		if (director.length() >= 7) {
			dspace = "    ";
		} else {
			dspace = "      ";
		}

		return "[ " + movieCode + " ]  " + title + tspace + director + dspace + genre + "    " + date + "   " + star;
	}

	public String selectMovie() {

		return "no [ " + movieCode + " ] " + title + "\n" //
				+ "감독: " + director + " | 장르: " + genre + " | 개봉년도: " + date + "\n" //
				+ "줄거리:" + plot + "\n"//
				+ "-----------------------------------------------------------------------------------------\n";
	}

	public String selectStar() {
		String stars = "";
		for (int i = 1; i <= star; i++) {
			stars += "★";
		}
		while (stars.length() < 5) {
			stars += "☆";
		}
		return "  " + stars + " (" + star + ") ";
	}

}
