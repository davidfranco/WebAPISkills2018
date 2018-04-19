package es.skills2018.api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import es.skills2018.api.beans.Movie;
import es.skills2018.api.enums.SearchFilter;
import es.skills2018.api.filters.MovieFilter;

public class MovieDAO{
	private Connection connection;
	private static final String DELETE_STATEMENT = "DELETE FROM MOVIE WHERE " + Movie.DB_MOVIE_ID_FIELD + "=?";
	private static final String INSERT_STATEMENT = "INSERT INTO MOVIE(" +  Movie.DB_IMAGE_FIELD + " ," + Movie.DB_LINK_FIELD + " ," + Movie.DB_TITLE_FIELD + " ," + Movie.DB_PLACE_FIELD + " ," + Movie.DB_RATING_FIELD + " ," + Movie.DB_STAR_CAST_FIELD + " ," + Movie.DB_VOTE_FIELD + " ," + Movie.DB_YEAR_FIELD  + ") VALUES(?,?,?,?,?,?,?,?)";
	private static final String SELECT_STATEMENT = "SELECT * FROM MOVIE WHERE 1=1";
	private static final String SEARCH_STATEMENT = "SELECT * FROM MOVIE WHERE ";
	private static final String SEARCH_COUNT_STATEMENT = "SELECT COUNT(movie_id) count FROM MOVIE WHERE ";
	private static final String UPDATE_STATEMENT = "UPDATE MOVIE SET <update> WHERE "
	+ Movie.DB_MOVIE_ID_FIELD + "=?";
	private static final String COUNT_STATEMENT = "SELECT COUNT(movie_id) count FROM MOVIE WHERE 1=1";

	public MovieDAO(Connection c){
		this.connection = c;
	}
	public List<Movie> findAll(MovieFilter filter, int startIndex, int limit, int order){
		List<Movie> result = new ArrayList<Movie>();
		try{
			String query = SELECT_STATEMENT;
			HashMap<Integer, Object> values = new HashMap<Integer,Object>();
			int c = 0;
			Movie bean = filter.getMovie();
			if(filter.getMovieIdFilter() != SearchFilter.NONE){
				query += filter.getMovieIdFilter().getText(Movie.DB_MOVIE_ID_FIELD);
				c++;
				values.put(c, bean.getMovieId());
			}
			if(filter.getImageFilter() != SearchFilter.NONE){
				query += filter.getImageFilter().getText(Movie.DB_IMAGE_FIELD);
				c++;
				values.put(c, bean.getImage());
			}
			if(filter.getLinkFilter() != SearchFilter.NONE){
				query += filter.getLinkFilter().getText(Movie.DB_LINK_FIELD);
				c++;
				values.put(c, bean.getLink());
			}
			if(filter.getTitleFilter() != SearchFilter.NONE){
				query += filter.getTitleFilter().getText(Movie.DB_TITLE_FIELD);
				c++;
				values.put(c, bean.getTitle());
			}
			if(filter.getPlaceFilter() != SearchFilter.NONE){
				query += filter.getPlaceFilter().getText(Movie.DB_PLACE_FIELD);
				c++;
				values.put(c, bean.getPlace());
			}
			if(filter.getRatingFilter() != SearchFilter.NONE){
				query += filter.getRatingFilter().getText(Movie.DB_RATING_FIELD);
				c++;
				values.put(c, bean.getRating());
			}
			if(filter.getStartCastFilter() != SearchFilter.NONE){
				query += filter.getStartCastFilter().getText(Movie.DB_STAR_CAST_FIELD);
				c++;
				values.put(c, bean.getStartCast());
			}
			if(filter.getVoteFilter() != SearchFilter.NONE){
				query += filter.getVoteFilter().getText(Movie.DB_VOTE_FIELD);
				c++;
				values.put(c, bean.getVote());
			}
			if(filter.getYearFilter() != SearchFilter.NONE){
				query += filter.getYearFilter().getText(Movie.DB_YEAR_FIELD);
				c++;
				values.put(c, bean.getYear());
			}
			if(order == 1){
				query+= " ORDER BY rating";
			}else if(order == 2){
				query+= " ORDER BY year";
			}else if(order == 3){
				query+= " ORDER BY title";
			}
			if(limit > 0){
				query+= " LIMIT " + limit;
			}
			if(startIndex > -1){
				query+= " OFFSET " + startIndex;
			}
			PreparedStatement pS = connection.prepareStatement(query);
			for(Integer x : values.keySet()){
				pS.setObject(x, values.get(x));
			}
			ResultSet rs = pS.executeQuery();
			while(rs.next()){
				result.add(new Movie(rs.getInt(Movie.DB_MOVIE_ID_FIELD),
					rs.getString(Movie.DB_IMAGE_FIELD),
					rs.getString(Movie.DB_LINK_FIELD),
					rs.getString(Movie.DB_TITLE_FIELD),
					rs.getInt(Movie.DB_PLACE_FIELD),
					rs.getDouble(Movie.DB_RATING_FIELD),
					rs.getString(Movie.DB_STAR_CAST_FIELD),
					rs.getInt(Movie.DB_VOTE_FIELD),
					rs.getInt(Movie.DB_YEAR_FIELD)));
			}
			rs.close();
			pS.close();
		}catch(SQLException e){
			e.printStackTrace();
			result = null;
		}
		return result;
	}
	public List<Movie> search(String text, int startIndex, int limit, int order){
		List<Movie> result = new ArrayList<Movie>();
		try{
			String query = SEARCH_STATEMENT;
			HashMap<Integer, Object> values = new HashMap<Integer,Object>();
			query += "LOWER(title) LIKE CONCAT('%',?,'%')";
			values.put(1, text.toLowerCase());
			query += " OR LOWER(star_cast) LIKE CONCAT('%',?,'%')";
			values.put(2, text.toLowerCase());
			if(order == 1){
				query+= " ORDER BY rating";
			}else if(order == 2){
				query+= " ORDER BY year";
			}else if(order == 3){
				query+= " ORDER BY title";
			}
			if(limit > 0){
				query+= " LIMIT " + limit;
			}
			if(startIndex > -1){
				query+= " OFFSET " + startIndex;
			}
			PreparedStatement pS = connection.prepareStatement(query);
			for(Integer x : values.keySet()){
				pS.setObject(x, values.get(x));
			}
			ResultSet rs = pS.executeQuery();
			while(rs.next()){
				result.add(new Movie(rs.getInt(Movie.DB_MOVIE_ID_FIELD),
					rs.getString(Movie.DB_IMAGE_FIELD),
					rs.getString(Movie.DB_LINK_FIELD),
					rs.getString(Movie.DB_TITLE_FIELD),
					rs.getInt(Movie.DB_PLACE_FIELD),
					rs.getDouble(Movie.DB_RATING_FIELD),
					rs.getString(Movie.DB_STAR_CAST_FIELD),
					rs.getInt(Movie.DB_VOTE_FIELD),
					rs.getInt(Movie.DB_YEAR_FIELD)));
			}
			rs.close();
			pS.close();
		}catch(SQLException e){
			e.printStackTrace();
			result = null;
		}
		return result;
	}
	public int countSearch(String text){
		int result = 0;
		try{
			String query = SEARCH_COUNT_STATEMENT;
			HashMap<Integer, Object> values = new HashMap<Integer,Object>();
			query += "LOWER(title) LIKE CONCAT('%',?,'%')";
			values.put(1, text.toLowerCase());
			query += " OR LOWER(star_cast) LIKE CONCAT('%',?,'%')";
			values.put(2, text.toLowerCase());
			PreparedStatement pS = connection.prepareStatement(query);
			for(Integer x : values.keySet()){
				pS.setObject(x, values.get(x));
			}
			ResultSet rs = pS.executeQuery();
			rs.next();
			result = rs.getInt("count");
			rs.close();
			pS.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	public int count(MovieFilter filter){
		int result = 0;
		try{
			String query = COUNT_STATEMENT;
			HashMap<Integer, Object> values = new HashMap<Integer,Object>();
			int c = 0;
			Movie bean = filter.getMovie();
			if(filter.getMovieIdFilter() != SearchFilter.NONE){
				query += filter.getMovieIdFilter().getText(Movie.DB_MOVIE_ID_FIELD);
				c++;
				values.put(c, bean.getMovieId());
			}
			if(filter.getImageFilter() != SearchFilter.NONE){
				query += filter.getImageFilter().getText(Movie.DB_IMAGE_FIELD);
				c++;
				values.put(c, bean.getImage());
			}
			if(filter.getLinkFilter() != SearchFilter.NONE){
				query += filter.getLinkFilter().getText(Movie.DB_LINK_FIELD);
				c++;
				values.put(c, bean.getLink());
			}
			if(filter.getTitleFilter() != SearchFilter.NONE){
				query += filter.getTitleFilter().getText(Movie.DB_TITLE_FIELD);
				c++;
				values.put(c, bean.getTitle());
			}
			if(filter.getPlaceFilter() != SearchFilter.NONE){
				query += filter.getPlaceFilter().getText(Movie.DB_PLACE_FIELD);
				c++;
				values.put(c, bean.getPlace());
			}
			if(filter.getRatingFilter() != SearchFilter.NONE){
				query += filter.getRatingFilter().getText(Movie.DB_RATING_FIELD);
				c++;
				values.put(c, bean.getRating());
			}
			if(filter.getStartCastFilter() != SearchFilter.NONE){
				query += filter.getStartCastFilter().getText(Movie.DB_STAR_CAST_FIELD);
				c++;
				values.put(c, bean.getStartCast());
			}
			if(filter.getVoteFilter() != SearchFilter.NONE){
				query += filter.getVoteFilter().getText(Movie.DB_VOTE_FIELD);
				c++;
				values.put(c, bean.getVote());
			}
			if(filter.getYearFilter() != SearchFilter.NONE){
				query += filter.getYearFilter().getText(Movie.DB_YEAR_FIELD);
				c++;
				values.put(c, bean.getYear());
			}
			
			PreparedStatement pS = connection.prepareStatement(query);
			for(Integer x : values.keySet()){
				pS.setObject(x, values.get(x));
			}
			ResultSet rs = pS.executeQuery();
			rs.next();
			result = rs.getInt("count");
			rs.close();
			pS.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	public boolean delete(int movieId){
		boolean result = true;
		try{
			PreparedStatement pS = connection.prepareStatement(DELETE_STATEMENT);
			pS.setInt(1, movieId);
			pS.execute();
			pS.close();
		}catch(SQLException e){
			result = false;
		}
		return result;
	}
	public boolean update(int movieId, HashMap<String, Object> fields){
		boolean result = true;
		if(fields.size() == 0){
			return true;
		}
		try{
			String s = UPDATE_STATEMENT;
			String toAdd = "";
			for(String field : fields.keySet()){
				toAdd+= field + "=?, ";
			}
			toAdd = toAdd.substring(0,toAdd.length() - 2);
			s = s.replace("<update>", toAdd);
			PreparedStatement pS = connection.prepareStatement(s);
			int c = 0;
			for(String field : fields.keySet()){
				c++;
				pS.setObject(c, fields.get(field));
			}
			pS.setInt(c+1, movieId);
			pS.execute();
			pS.close();
		}catch(SQLException e){
			result = false;
		}
		return result;
	
	}
	public boolean add(Movie bean){
		boolean result = true;
		try{
			PreparedStatement pS = connection.prepareStatement(INSERT_STATEMENT);
			pS.setString(1, bean.getImage());
			pS.setString(2, bean.getLink());
			pS.setString(3, bean.getTitle());
			pS.setInt(4, bean.getPlace());
			pS.setDouble(5, bean.getRating());
			pS.setString(6, bean.getStartCast());
			pS.setInt(7, bean.getVote());
			pS.setInt(8, bean.getYear());
			pS.execute();
			pS.close();
		}catch(SQLException e){
			result = false;
		}
		return result;
	}
}