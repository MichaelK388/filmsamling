import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class MovieCollectionTest {

    private MovieCollection collection;

    // Alle film i samlingen, der er LÆNGERE end det angivne antal minutter

    @BeforeEach
    void setUp() {
        // Kører før HVER test – så starter alle tests med den samme, friske samling
        collection = new MovieCollection();
        collection.addMovie("The Godfather", "Francis Ford Coppola", 1972, true, 175, "Crime");
        collection.addMovie("Casablanca", "Michael Curtiz", 1942, false, 102, "Drama");
        collection.addMovie("Godzilla", "Ishirō Honda", 1954, false, 96, "Sci-fi");
    }

    @Test
    void searchWithSeveralMatches() {
        // "god" findes i både "The Godfather" og "Godzilla"
        ArrayList<Movie> result = collection.searchMovies("god");

        assertEquals(2, result.size());
    }

    @Test
    void deleteMovieRemovesIt() {
        // Arrange
        Movie casablanca = collection.searchMovies("Casablanca").get(0);

        // Act
        boolean deleted = collection.deleteMovie(casablanca);

        // Assert
        assertTrue(deleted);
        assertEquals(2, collection.getNumberOfMovies());
        assertTrue(collection.searchMovies("Casablanca").isEmpty());
    }

    @Test
    void getMoviesDoubleThan100Minutes() {
        // Arrange

        // Act
        ArrayList<Movie> result2 = collection.getMoviesLongerThan(100.5);
        // Assert
        assertEquals(2, result2.size());
    }

    @Test
    void getMoviesIntegerThan100Minutes() {
        // Act
        ArrayList<Movie> result1 = collection.getMoviesLongerThan(100);
        // Assert
        assertEquals(2, result1.size());
    }

    @Test
    void getMoviesNoThan100Minutes() {
        // Act
        ArrayList<Movie> result = collection.getMoviesLongerThan(0);
        for (Movie movie : result) {
            collection.deleteMovie(movie);
        }
        // Assert
        ArrayList<Movie> result2 = collection.getMoviesLongerThan(0);
        assertEquals(0, result2.size());

    }

    @Test
    void getMoviesNegative100Minutes() {
        // Act
        ArrayList<Movie> result = collection.getMoviesLongerThan(-100);
        for (Movie movie : result) {
            collection.deleteMovie(movie);
        }
        // Assert
        ArrayList<Movie> result2 = collection.getMoviesLongerThan(-100);

    }
    @Test
    void getMoviesFrom70Ties() {
        // Arrange

        // Act
        ArrayList<Movie> result = collection.getMoviesFromDecade(1940);
        // Assert
        assertEquals(1, result.size());
        System.out.println(result.get(0).getTitle());
        assertTrue(Objects.equals(result.get(0).getTitle(), "Casablanca")); }
}