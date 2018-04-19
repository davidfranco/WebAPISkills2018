package es.skills2018.api.response;

import java.util.ArrayList;

import es.skills2018.api.beans.Movie;

public interface MovieResponse {
	public class Update implements MovieResponse{
		public boolean success;
		public Update(){}
		public Update(boolean success){
			this.success = success;
		}
		public boolean isSuccess() {
			return success;
		}
		public void setSuccess(boolean success) {
			this.success = success;
		}
		
	}
	public class Delete implements MovieResponse{
		public boolean success;
		public Delete(){}
		public Delete(boolean success){
			this.success = success;
		}
		public boolean isSuccess() {
			return success;
		}
		public void setSuccess(boolean success) {
			this.success = success;
		}
		
	}
	public class Count implements MovieResponse {
		public int count;
		public Count(){}
		public Count(int count){
			this.count = count;
		}
		public int getCount() {
			return count;
		}
		public void setCount(int count) {
			this.count = count;
		}
	}
	public class List implements MovieResponse{
		public ArrayList<Movie> movieList;
		public List(){}
		public List(ArrayList<Movie> movieList) {
			this.movieList = movieList;
		}

		public ArrayList<Movie> getMovieList() {
			return movieList;
		}

		public void setMovieList(ArrayList<Movie> movieList) {
			this.movieList = movieList;
		}
		
	}
	public class Get implements MovieResponse{
		public int movieId;
		public String image;
		public String link;
		public String title;
		public int place;
		public double rating;
		public String starsCast;
		public int vote;
		public int year;
		public Get(){}
		public Get(Movie movie){
			this.movieId = movie.getMovieId();
			this.image  = movie.getImage();
			this.link = movie.getLink();
			this.title = movie.getTitle();
			this.place = movie.getPlace();
			this.rating = movie.getRating();
			this.starsCast = movie.getStartCast();
			this.vote = movie.getVote();
			this.year = movie.getYear();
		}
	}
}
