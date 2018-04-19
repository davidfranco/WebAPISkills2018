package es.skills2018.api.beans;

public class Movie {
	// Database Fields
	// Primary Key is Auto Incremental
	public static final String DB_MOVIE_ID_FIELD = "movie_id";
	public static final String DB_IMAGE_FIELD = "image";
	public static final String DB_LINK_FIELD = "link";
	public static final String DB_TITLE_FIELD = "title";
	public static final String DB_PLACE_FIELD = "place";
	public static final String DB_RATING_FIELD = "rating";
	public static final String DB_STAR_CAST_FIELD = "star_cast";
	public static final String DB_VOTE_FIELD = "vote";
	public static final String DB_YEAR_FIELD = "year";
	// Object Fields
	public int movieId;
	public String image;
	public String link;
	public String title;
	public int place;
	public double rating;
	public String startCast;
	public int vote;
	public int year;
	// Constructors
	public Movie() {
	}
	public Movie(int movieId, String image, String link, String title, int place, double rating, String startCast,
			int vote, int year) {
		this.movieId = movieId;
		this.image = image;
		this.link = link;
		this.title = title;
		this.place = place;
		this.rating = rating;
		this.startCast = startCast;
		this.vote = vote;
		this.year = year;
	}
	//Getters and Setters
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPlace() {
		return place;
	}
	public void setPlace(int place) {
		this.place = place;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public String getStartCast() {
		return startCast;
	}
	public void setStartCast(String startCast) {
		this.startCast = startCast;
	}
	public int getVote() {
		return vote;
	}
	public void setVote(int vote) {
		this.vote = vote;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}

}