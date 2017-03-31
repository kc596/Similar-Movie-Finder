# Similar Movie Finder  
A program that can identify for every movie in the list, n other similar / related movies in the same list in descending order of "similarity".

## Compiling and Running Project
 - **Windows Environment** :-  
 *Compile&nbsp;:* &nbsp; javac -cp .;./gson/gson-2.8.0.jar test/Test.java -d build/  
 *Run&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:* &nbsp; java -cp ./build/;./gson/gson-2.8.0.jar test.Test  
  
 - **Unix** :-  
 *Compile&nbsp;:* &nbsp; javac -cp .:./gson/gson-2.8.0.jar test/Test.java -d build/  
 *Run&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:* &nbsp; java -cp ./build/:./gson/gson-2.8.0.jar test.Test  


## Input and Output

- *Input :* &nbsp; "input.json" file in input directory
- *Output :* "output.json" file in output directory
  
    **Each movie in input is of form:**

        { 
          "movie_id": 1, 
          "rating": "9.3", 
          "votes": "1,318,626", 
          "description": "Two imprisoned men bond over a number ....", 
          "title": "The Shawshank Redemption", 
          "poster": "", 
          "release_date": "14 October 1994", 
          "metascore": "80", 
          "director": "Frank Darabont", 
          "storyline": "Andy Dufresne is a young and successful ...",
          "stars": [ "Tim Robbins", "Morgan Freeman", "Bob Gunton" ], 
          "year": "1994", 
          "genre": [ "Crime", "Drama" ], 
          "gallery": [ "unknown1394846836._CB379391227_.png", ], 
          "running_time": "142min"
        }

## Calculation of Similarity of Movie
Let the similarity between two movies be defined using a parameter – **weight**  
>More weight -> More similarity  

    
    Then for each pair of movies, similarity factor is calculated using :
        i)   Genre              [weight = 100 for each matching genre]
        ii)  Director of movie  [weight = 50]
        iii) Rating             [weight = 10]
        iv)  MetaScore          [weight = 10]
        v)   Year of Release    [weight = 10]


Finally, the last **"n"** movies of the sorted list corresponding to a movie is chosen for output.

### Reason behind choosing the above mentioned factors
1. **Genre:** Most important factor for similarity of two movies. Movies of same genre are more likely to be similar.

2. **Director:** A director might produce similar movie. Since the same person would be directing two movies, the movies are likely to have similar touch.

3. **Rating:** Good movies are similar to good movies and poor will be similar to poor movies. But this cannot be generalized more often, so the weight of rating is just 10% of genre.

4. **MetaScore:** Similar to audience's rating, Critics score can also be taken into account.

5. **Year of Release:** It is observed quite often that movies released in same decade are more similar. *e.g.,* 90's of Bollywood is known for romantic and fight oriented movies. This factor is not taken into account if release date of movies differ by more than 10 years. 

## Procedure for calculating similarity of two movies

Let there be an imaginary link between two movies say A and B and the weight of the link represents the similarity of movies.

| Increase in Weight |
| ------ | 
| **100** * number of similar genres |
| **50** if director is same | 
| **10 - difference in ratings** | 
| **(100 - difference in metascore)/10** | 
| **10 - (difference in year of release)** &nbsp; if both movies are released in same decade | 

## Procedure for finding the list of similar movies

Similarity as described above is calculated for all pair of movies (250*250 in our example) and stored in a 2D array. Then the array is sorted according to similarity and the required number of similar movies is picked from last (10 in our example) for each(250) movie.
