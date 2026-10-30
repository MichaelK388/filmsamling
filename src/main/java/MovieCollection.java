import java.util.ArrayList;
import java.time.LocalDate;

public class MovieCollection {
    private ArrayList<Movie> movies;

    public MovieCollection() {
        movies = new ArrayList<>();
    }

    public void addMovie(String title, String director, int yearCreated,
                         boolean inColor, int lengthInMinutes, String genre) {

        Movie movie = new Movie(title, director, yearCreated, inColor, lengthInMinutes, genre);
        movies.add(movie);
    }
    // Method overloading, bruges til Filehandler.java
    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public int getNumberOfMovies() {
        return movies.size();
    }

    public ArrayList<Movie> searchMovies(String searchText) {
        ArrayList<Movie> matches = new ArrayList<>();
        String text = searchText.toLowerCase();
        for (Movie movie : movies) {
            if (movie.getTitle().toLowerCase().contains(text)) {
                matches.add(movie);
            }
        }
        return matches;
    }

    public boolean editMovie(
            Movie movie,
            String title,
            String director,
            int yearCreated,
            boolean inColor,
            int lengthInMinutes,
            String genre) {

        if (!movies.contains(movie)) {
            return false;
        }

        // Kontrollér alle værdier, før vi ændrer den eksisterende film.
        new Movie(
                title,
                director,
                yearCreated,
                inColor,
                lengthInMinutes,
                genre
        );

        // Først nu ændres den eksisterende film.
        movie.setTitle(title);
        movie.setDirector(director);
        movie.setYearCreated(yearCreated);
        movie.setInColor(inColor);
        movie.setLengthInMinutes(lengthInMinutes);
        movie.setGenre(genre);

        return true;
    }

    public boolean deleteMovie(Movie movie) {
        return movies.remove(movie);
    }

    // Alle film i samlingen, der er LÆNGERE end det angivne antal minutter
    public ArrayList<Movie> getMoviesLongerThan(double minutes){
        ArrayList<Movie> result = new ArrayList<>();
        for (Movie movie : movies) {
            if (movie.getLengthInMinutes() > minutes) {
                result.add(movie);}
        }
        return result;
    }
    // Alle film fra det årti, der starter med decade – fx 1970 giver film fra 1970 til og med 1979
    public ArrayList<Movie> getMoviesFromDecade(int decade){
        ArrayList<Movie> result = new ArrayList<>();
        for (Movie movie : movies) {
            if (movie.getYearCreated() >= decade && movie.getYearCreated() <= decade +9) {
                result.add(movie);}
        }
        return result;
    }
    public ArrayList<Movie> getMoviesNotWatchedSince(LocalDate date) {

        ArrayList<Movie> results = new ArrayList<>();

        for (Movie movie : movies) {
            if (!movie.hasBeenWatched() || movie.getLastWatched().isBefore(date)){
                results.add(movie);
            }
        }
        return results;
    }
}