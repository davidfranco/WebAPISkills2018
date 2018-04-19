package es.skills2018.api.filters;

import es.skills2018.api.beans.Movie;
import es.skills2018.api.enums.SearchFilter;

public class MovieFilter {

	private Movie movie;
	private SearchFilter movieIdFilter = SearchFilter.NONE;
	private SearchFilter imageFilter = SearchFilter.NONE;
	private SearchFilter linkFilter = SearchFilter.NONE;
	private SearchFilter titleFilter = SearchFilter.NONE;
	private SearchFilter placeFilter = SearchFilter.NONE;
	private SearchFilter ratingFilter = SearchFilter.NONE;
	private SearchFilter startCastFilter = SearchFilter.NONE;
	private SearchFilter voteFilter = SearchFilter.NONE;
	private SearchFilter yearFilter = SearchFilter.NONE;

	public MovieFilter(Movie movie) {
		this.movie = movie;
	}

	public Movie getMovie() {
		return this.movie;
	}

	public SearchFilter getMovieIdFilter() {
		return movieIdFilter;
	}

	public void setMovieIdFilter(SearchFilter movieIdFilter) {
		this.movieIdFilter = movieIdFilter;
	}

	public SearchFilter getImageFilter() {
		return imageFilter;
	}

	public void setImageFilter(SearchFilter imageFilter) {
		this.imageFilter = imageFilter;
	}

	public SearchFilter getLinkFilter() {
		return linkFilter;
	}

	public void setLinkFilter(SearchFilter linkFilter) {
		this.linkFilter = linkFilter;
	}

	public SearchFilter getTitleFilter() {
		return titleFilter;
	}

	public void setTitleFilter(SearchFilter titleFilter) {
		this.titleFilter = titleFilter;
	}

	public SearchFilter getPlaceFilter() {
		return placeFilter;
	}

	public void setPlaceFilter(SearchFilter placeFilter) {
		this.placeFilter = placeFilter;
	}

	public SearchFilter getRatingFilter() {
		return ratingFilter;
	}

	public void setRatingFilter(SearchFilter ratingFilter) {
		this.ratingFilter = ratingFilter;
	}

	public SearchFilter getStartCastFilter() {
		return startCastFilter;
	}

	public void setStartCastFilter(SearchFilter startCastFilter) {
		this.startCastFilter = startCastFilter;
	}

	public SearchFilter getVoteFilter() {
		return voteFilter;
	}

	public void setVoteFilter(SearchFilter voteFilter) {
		this.voteFilter = voteFilter;
	}

	public SearchFilter getYearFilter() {
		return yearFilter;
	}

	public void setYearFilter(SearchFilter yearFilter) {
		this.yearFilter = yearFilter;
	}
}
