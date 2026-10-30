import java.util.ArrayList;
import java.time.LocalDate;

public class Controller {

    private MovieCollection movieCollection;
    private FileHandler fileHandler;
    private boolean changed; //holder styr på om samlingen er blevet ændret siden den sidst blev gemt.

    public Controller() {
        movieCollection = new MovieCollection();
        fileHandler = new FileHandler("movies.csv");
        changed = false;
    }

    public void addMovie(String title,
                         String director,
                         int yearCreated,
                         boolean inColor,
                         int lengthInMinutes,
                         String genre) {

        movieCollection.addMovie(
                title,
                director,
                yearCreated,
                inColor,
                lengthInMinutes,
                genre
        );

        //movieCollection.addMovie(movie);
    }

    public ArrayList<Movie> getMovies() {
        return movieCollection.getMovies();
    }

    public ArrayList<Movie> searchMovies(String searchText) {
        return movieCollection.searchMovies(searchText);
    }

    public boolean editMovie(Movie movie,
                             String title,
                             String director,
                             int yearCreated,
                             boolean inColor,
                             int lengthInMinutes,
                             String genre) {

        return movieCollection.editMovie(
                movie,
                title,
                director,
                yearCreated,
                inColor,
                lengthInMinutes,
                genre
        );
    }

    public boolean deleteMovie(Movie movie) {
        return movieCollection.deleteMovie(movie);
    }
    public void markAsWatched(Movie movie, LocalDate date) {
        movie.markAsWatched(date);
    }
    public ArrayList<Movie> getMoviesNotWatchedForDays(int days) {
        LocalDate limit = LocalDate.now().minusDays(days);
        return movieCollection.getMoviesNotWatchedSince(limit);
    }

}
