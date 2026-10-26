import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
}