package kc596.similarmovies;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * The {@code Movie} class represents a Movie and its attributes.
 *
 * @author Kunal Chaudhary
 */

public class Movie{

	/*
	 * Locale variables corresponding to attributes of Movies.
	 * Most of the field are taken as String to handle null entries.
	 */
	private int movie_id;
	private double rating;
	private String votes;
	private String description;
	private String title;
	private String poster;
	private String release_date;
	private String metascore;
	private String director;
	private String storyline;
	private ArrayList<String> stars;
	private int year;
	private HashSet<String> genre;
	private ArrayList<String> gallery;
	private String runtime;

	/**
	 * @return a int denoting id of the movie.
	 */
	public int getMovieId(){
		return movie_id;
	}

	/**
	 * @return the rating of the movie.
	 */
	public double getRating(){
		return rating;
	}

	/**
	 * @return number of users that rated the movie.
	 */
	public String getVotes(){
		return votes;
	}

	/**
	 * @return a string containing short description of movie.
	 */
	public String getDescription(){
		return description;
	}

	/**
	 * @return the title of movie.
	 */
	public String getTitle(){
		return title;
	}

	/**
	 * @return the link to poster of movie.
	 */
	public String getPosterURL(){
		return poster;
	}

	/**
	 * @return the release date of movie.
	 */
	public String getReleaseDate(){
		return release_date;
	}

	/**
	 * @return the metascore of movie.
	 */
	public String getMetaScore(){
		return metascore;
	}

	/**
	 * @return the name of director of movie.
	 */
	public String getDirector(){
		return director;
	}

	/**
	 * @return a string containing abstract of story line of movie.
	 */
	public String getStoryLine(){
		return storyline;
	}

	/**
	 * @return the names of stars that acted in movie.
	 */
	public ArrayList<String> getStars(){
		return stars;
	}

	/**
	 * @return the release year of movie.
	 */
	public int getYear(){
		return year;
	}

	/**
	 * @return the set of genres of movie.
	 */
	public HashSet<String> getGenre(){
		return genre;
	}

	/**
	 * @return the URLs for gallery images of movie.
	 */
	public ArrayList<String> getGallery(){
		return gallery;
	}

	/**
	 * @return a string denoting runtime of movie.
	 */
	public String getRuntime(){
		return runtime;
	}
}