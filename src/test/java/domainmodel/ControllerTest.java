package domainmodel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    private Controller controller;

    @BeforeEach
    void setUp() {
        controller = new Controller();
    }

    @Test
    void addMovie() {

        controller.addMovie(
                "Godzilla",
                "Ishiro Honda",
                1954,
                false,
                96,
                "Action"
        );

        assertEquals(1, controller.getMovies().size());
    }

    @Test
    void getMovies() {

        controller.addMovie(
                "Godzilla",
                "Ishiro Honda",
                1954,
                false,
                96,
                "Action"
        );

        assertFalse(controller.getMovies().isEmpty());
    }

    @Test
    void searchMovies() {

        controller.addMovie(
                "Godzilla",
                "Ishiro Honda",
                1954,
                false,
                96,
                "Action"
        );

        assertEquals(1, controller.searchMovies("Godzilla").size());
    }

    @Test
    void deleteMovie() {

        controller.addMovie(
                "Godzilla",
                "Ishiro Honda",
                1954,
                false,
                96,
                "Action"
        );

        Movie movie = controller.getMovies().get(0);

        assertTrue(controller.deleteMovie(movie));
    }
    @Test
    void moviesSortedByYear(){
        controller.addMovie("Psycho", "Alfred Hitchcock", 1960, false, 109, "Horror");
        controller.addMovie("Casablanca", "Michael Curtiz", 1942, false, 102, "Drama");
        controller.addMovie("Jaws", "Steven Spielberg", 1975, true, 124, "Thriller");

        ArrayList<Movie> sorted = controller.getMoviesSortedBy(SortField.YEAR);

        assertEquals(1942, sorted.get(0).getYearCreated());
        assertEquals(1960, sorted.get(1).getYearCreated());
        assertEquals(1975, sorted.get(2).getYearCreated());
    }
}