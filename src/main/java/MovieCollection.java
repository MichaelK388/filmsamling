import java.util.ArrayList;

public class MovieCollection {
    private ArrayList<Movie> movies;

    public MovieCollection() {
        movies = new ArrayList<>();
    }

    public void addMovie(String title, String director, int yearCreated, boolean inColor, int lengthInMinutes, String genre) {
        // TODO US1
    }

    public ArrayList<Movie> getMovies() {
        return null;   // TODO US3
    }

    public int getNumberOfMovies() {
        return 0;      // TODO
    }

    // Finder alle film, hvor søgeteksten indgår i titlen – uanset store og små bogstaver
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

    public boolean editMovie(Movie movie, String title, String director, int yearCreated, boolean inColor, int lengthInMinutes, String genre) {
        return false;  // TODO US5
    }

    public boolean deleteMovie(Movie movie) {
        return false;  // TODO US6
    }
}