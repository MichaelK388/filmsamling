import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileHandlerTest {
    private static final String TEST_FILE = "test-movies.csv";

    private FileHandler fileHandler;

    @BeforeEach
    void setUp() {
        fileHandler = new FileHandler(TEST_FILE);
    }

    @AfterEach
    void deleteTestFile() {
        new File(TEST_FILE).delete();
    }

    @Test
    void brokenLinesAreSkipped() throws FileNotFoundException {

        PrintStream output = new PrintStream(new File(TEST_FILE));

        output.println("title;director;yearCreated;inColor;lengthInMinutes;genre;lastWatched");

        output.println("Godzilla;Ishiro Honda;1954;false;96;Action;");

        output.println("Bad Movie;Director");

        output.println("Jaws;Steven Spielberg;abc;true;124;Thriller;");

        output.close();

        //Act
        ArrayList<Movie> movies = fileHandler.loadMovies();

        //Assert
        assertEquals(1, movies.size());
        assertEquals(2, fileHandler.getSkippedLineCount());
        assertEquals("Godzilla", movies.get(0).getTitle());
    }
}
