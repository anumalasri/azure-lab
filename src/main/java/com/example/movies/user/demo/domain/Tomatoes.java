package com.example.movies.user.demo.domain;

public class Tomatoes {

	private Viewer viewer;
	private int rotten;
	private int fresh;
	private Critic critic;
	public Viewer getViewer() {
		return viewer;
	}
	public void setViewer(Viewer viewer) {
		this.viewer = viewer;
	}
	public int getRotten() {
		return rotten;
	}
	public void setRotten(int rotten) {
		this.rotten = rotten;
	}
	public int getFresh() {
		return fresh;
	}
	public void setFresh(int fresh) {
		this.fresh = fresh;
	}
	public Critic getCritic() {
		return critic;
	}
	public void setCritic(Critic critic) {
		this.critic = critic;
	}
	
	
}
