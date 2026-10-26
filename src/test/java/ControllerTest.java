import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
}