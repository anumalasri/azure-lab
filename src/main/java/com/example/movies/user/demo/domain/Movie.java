package com.example.movies.user.demo.domain;

import java.util.List;

public class Movie {

	
    private String title;
    private String year;
    private String released;
    private String plot;
    private List<String> genres;
    private String[] cast;
    private String[] directors;
    private IMDB imdb;
    private Tomatoes tomatoes;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getReleased() {
		return released;
	}
	public void setReleased(String released) {
		this.released = released;
	}
	public String getPlot() {
		return plot;
	}
	public void setPlot(String plot) {
		this.plot = plot;
	}
	public List<String> getGenres() {
		return genres;
	}
	public void setGenres(List<String> genres) {
		this.genres = genres;
	}
	public String[] getCast() {
		return cast;
	}
	public void setCast(String[] cast) {
		this.cast = cast;
	}
	public String[] getDirectors() {
		return directors;
	}
	public void setDirectors(String[] directors) {
		this.directors = directors;
	}
	public IMDB getImbd() {
		return imdb;
	}
	public void setImbd(IMDB imdb) {
		this.imdb = imdb;
	}
	public Tomatoes getTomatoes() {
		return tomatoes;
	}
	public void setTomatoes(Tomatoes tomatoes) {
		this.tomatoes = tomatoes;
	}
    
    
}
