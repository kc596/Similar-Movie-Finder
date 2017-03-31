package test;

import java.lang.reflect.Type;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import buzzotel.similarmovies.Movie;
import buzzotel.similarmovies.MovieMatcher;

/**
 * Test class to get similar movies for each movie in the list.
 * 
 * @author Kunal Chaudhary
 */

public class Test{
	public static void main(String[] args){
		Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
		BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader("input/input.json"));
		} catch(IOException e){
			System.out.println(e.getMessage());						// printing the error that occurred while reading file
			return;													// aborting further execution
		}
		Type type = new TypeToken<ArrayList<Movie>>(){}.getType();
		ArrayList<Movie> listOfMovies = gson.fromJson(br, type); 	// Serialization
		MovieMatcher matcher = new MovieMatcher(listOfMovies, 10);	// we need 10 similar movies corresponding to each movie
		Map<String, ArrayList<String>> listOfSimilarMovieNames = matcher.getNamesOfSimilarMovies();
		try{
			PrintWriter writer = new PrintWriter("output/output.json", "UTF-8");
			writer.println(gson.toJson(listOfSimilarMovieNames));	// Deserialization
			writer.close();
		} catch(IOException e){
			System.out.println(e.getMessage());						// printing the error that occurred while writing output
		}
	}
}