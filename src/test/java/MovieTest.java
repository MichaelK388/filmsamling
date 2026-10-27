import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MovieTest {

    private Movie movie;

    @BeforeEach
    void setUp() {
        movie = new Movie(
                "Godzilla",
                "Ishiro Honda",
                1954,
                false,
                96,
                "Action"
        );
    }

    @Test
    void getTitle() {
        assertEquals("Godzilla", movie.getTitle());
    }

    @Test
    void setTitle() {
        movie.setTitle("King Kong");
        assertEquals("King Kong", movie.getTitle());
    }

    @Test
    void getDirector() {
        assertEquals("Ishiro Honda", movie.getDirector());
    }

    @Test
    void setDirector() {
        movie.setDirector("Peter Jackson");
        assertEquals("Peter Jackson", movie.getDirector());
    }

    @Test
    void getYearCreated() {
        assertEquals(1954, movie.getYearCreated());
    }

    @Test
    void isInColor() {
        assertFalse(movie.isInColor());
    }
    @Test
    void newMovieHasNotBeenWatched() {

        // Arrange
        // Movie er oprettet i setUp()

        // Act
        // Ingen handling

        // Assert
        assertFalse(movie.hasBeenWatched());
        assertNull(movie.getLastWatched());
    }
    @Test
    void markAsWatchedStoresDate() {

        // Arrange
        LocalDate date = LocalDate.of(2025, 10, 1);

        // Act
        movie.markAsWatched(date);

        // Assert
        assertEquals(date, movie.getLastWatched());
    }
    @Test
    void getDaysSinceWatchedCountsAcrossMonths() {

        // Arrange
        movie.markAsWatched(LocalDate.of(2025, 9, 30));

        // Act
        long days = movie.getDaysSinceWatched(LocalDate.of(2025, 10, 26));

        // Assert
        assertEquals(26, days);
    }
}