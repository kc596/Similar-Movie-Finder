package buzzotel.similarmovies;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * The {@code MovieMatcher} finds out similar movies for each {@link Movie}.
 *
 * @author Kunal Chaudhary
 */

public class MovieMatcher{
	private int N;								// size of graph, number of total movies
	private int sizeOfSimilarMovies;			// number of similar movies to return for a movie
	private ArrayList<Movie> listOfMovies;		// Container for storing movies
	private Edge[][] similarity;				// relation between movies. greater magnitude => more similar
	private Map<String, ArrayList<String>> listOfSimilarMovies;	// final result

	/*
	 * Similarity of a pair of movie is calculated by using 5 factors:
	 *	(i)   Genre 					-> Weight = 100 for each matching genre
	 *	(ii)  Director of movie 		-> Weight = 50
	 *	(iii) Rating 					-> Weight = 10
	 *	(iv)  MetaScore 				-> Weight = 10
	 *	(v)	  Year of Release of movie 	-> Weight = 10
	 */

	/**
	 * Initializes the set of local variables and calculates the similarity between each pair of movies.
	 *
	 * @param listOfMovies is the list of movies
	 * @param sizeOfSimilarMovies is number of similar movies to return for a movie
	 * @throws IllegalArgumentException if number of similar movies to return is -ve or more than remaining movies
	 */
	public MovieMatcher(ArrayList<Movie> listOfMovies, int sizeOfSimilarMovies){
		this.N = listOfMovies.size();
		this.sizeOfSimilarMovies = sizeOfSimilarMovies;
		if(sizeOfSimilarMovies<0 || sizeOfSimilarMovies>N-1){
			throw new IllegalArgumentException("Illegal number of similar movies requested");
		}
		this.listOfMovies = listOfMovies;
		this.listOfSimilarMovies = new HashMap<String, ArrayList<String>>();
		similarity = new Edge[N][N];

		// calculating similarity for each pair of movies
		for(int i=0; i<N; i++){
			similarity[i][i] = new Edge(i, i, -1.0);
			for(int j=i+1; j<N; j++){
				double weight = similarityFactor(listOfMovies.get(i), listOfMovies.get(j));
				similarity[i][j] = new Edge(i, j, weight);
				similarity[j][i] = new Edge(j, i, weight);
			}
		}

		// sorting similar movies for each movie.
		for(int i=0; i<N; i++){
			Arrays.sort(similarity[i]);
		}

		// preparing the result - calculating list of similar movies for each movie
		for(int i=0; i<N; i++){
			listOfSimilarMovies.put(listOfMovies.get(i).getTitle(), getSimilarMovies(i));
		}
	}

	/**
	 * @return a Map whose key is a movie name and value is list of similar movies
	 */
	public Map<String, ArrayList<String>> getNamesOfSimilarMovies(){
		return listOfSimilarMovies;
	}

	/**
	 * return list of names of movies similar to a movie
	 */
	private ArrayList<String> getSimilarMovies(int m){
		ArrayList<String> similarMovies = new ArrayList<String>(sizeOfSimilarMovies);
		for(int i=0; i<sizeOfSimilarMovies; i++){
			Movie y = listOfMovies.get(similarity[m][N-1-i].other(m));
			similarMovies.add(y.getTitle());
		}
		return similarMovies;
	}

	/**
	 * Helper function to calculate the similarity factor between a pair of movies.
	 * It returns similarity factor between two movies
	 */
	private double similarityFactor(Movie x, Movie y){
		return (genreFactor(x, y) + directorFactor(x,y) + ratingFactor(x,y) + metaFactor(x,y) + yearFactor(x,y));
	}

	/**
	 * returns genre factor for a pair of movies
	 */
	private double genreFactor(Movie x, Movie y){
		HashSet<String> genresOfMovieX = x.getGenre();
		HashSet<String> genresOfMovieY = y.getGenre();
		double genreFactor = 0.0;
		for(String s: genresOfMovieX){
			if(genresOfMovieY.contains(s)){
				genreFactor += 100.0;
			}
		}
		return genreFactor;
	}

	/**
	 * returns 50.0 if director of given movies is same else returns 0.0
	 */
	private double directorFactor(Movie x, Movie y){
		return x.getDirector().equals(y.getDirector())? 50.0 : 0.0;
	}

	/**
	 * returns rating factor for a pair of movies
	 */
	private double ratingFactor(Movie x, Movie y){
		return 10.0 - Math.abs(x.getRating() - y.getRating());
	}

	/**
	 * returns a real value based on closeness of metascore of movies
	 */
	private double metaFactor(Movie x, Movie y){
		if(x.getMetaScore().length()==0 || y.getMetaScore().length()==0) return 0.0;
		double scoreX = Double.parseDouble(x.getMetaScore());
		double scoreY = Double.parseDouble(y.getMetaScore());
		double diffOfMetaScore = Math.abs(scoreX - scoreY);
		return (100 - diffOfMetaScore)/10.0;
	}

	/**
	 * Movie released in same decade are more likely to be similar.
	 * This function returns a real value corresponding to year factor of given movies
	 */
	private double yearFactor(Movie x, Movie y){
		int diffOfYear = Math.abs(x.getYear() - y.getYear());
		if(diffOfYear>10) return 0.0;
		return 10.0 - diffOfYear;
	}
}