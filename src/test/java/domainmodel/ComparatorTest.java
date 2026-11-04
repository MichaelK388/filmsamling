package domainmodel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ComparatorTest {

    private Movie casablanca;
    private Movie psycho;
    private Movie jaws;
    private Movie twin;

    @BeforeEach
    void setUp() {
        casablanca = new Movie("Casablanca", "Michael Curtiz", 1942, false, 102, "Drama");
        psycho = new Movie("Psycho", "Alfred Hitchcock", 1960, false, 109, "Horror");
        jaws = new Movie("Jaws", "Steven Spielberg", 1975, true, 124, "Thriller");
        twin = new Movie("Anden film", "Michael Curtiz", 1942, false, 102, "Drama");
    }
    @Test
    void titleComparator(){
        TitleComparator comparator = new TitleComparator();

        assertTrue(comparator.compare(casablanca, psycho) < 0);
        assertTrue(comparator.compare(psycho, casablanca) > 0);
    }
    @Test
    void titleComparatorIgnoreCase(){
        TitleComparator comparator = new TitleComparator();
        Movie upperCase = new Movie("CASABLANCA", "Michael Curtiz", 1942, false, 102, "Drama");

        assertEquals(0, comparator.compare(casablanca, upperCase));
    }
    @Test
    void directorComparator() {
        DirectorComparator comparator = new DirectorComparator();

        assertTrue(comparator.compare(psycho, casablanca) < 0);
        assertTrue(comparator.compare(jaws, casablanca) > 0);
        assertEquals(0, comparator.compare(casablanca, twin));
    }
    @Test
    void yearComparator(){
        YearComparator comparator = new YearComparator();

        assertTrue(comparator.compare(casablanca, psycho) < 0);
        assertTrue(comparator.compare(jaws, psycho) > 0);
        assertEquals(0, comparator.compare(casablanca, twin));
    }
    @Test
    void lengthComparator(){
        LengthComparator comparator = new LengthComparator();

        assertTrue(comparator.compare(casablanca, psycho) < 0);
        assertTrue(comparator.compare(jaws, psycho) > 0);
        assertEquals(0, comparator.compare(casablanca, twin));
    }
    @Test
    void genreComparator(){
        GenreComparator comparator = new GenreComparator();

        assertTrue(comparator.compare(casablanca, psycho) < 0);
        assertTrue(comparator.compare(jaws, psycho) > 0);
        assertEquals(0, comparator.compare(casablanca, twin));
    }
}
