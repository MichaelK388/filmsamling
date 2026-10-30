import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Movie {

    private static final int FIRST_MOVIE_YEAR = 1888;

    private String title;
    private String director;
    private int yearCreated;
    private boolean inColor;
    private int lengthInMinutes;
    private String genre;
    private LocalDate lastWatched;

    // Constructor: Opretter en film og kontrollerer dens oplysninger.
    public Movie(String title, String director, int yearCreated,
                 boolean inColor, int lengthInMinutes, String genre) {

        setTitle(title);
        setDirector(director);
        setYearCreated(yearCreated);
        setInColor(inColor);
        setLengthInMinutes(lengthInMinutes);
        setGenre(genre);

        this.lastWatched = null;
    }

    // Getters: Henter filmens oplysninger.

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public int getYearCreated() {
        return yearCreated;
    }

    public boolean isInColor() {
        return inColor;
    }

    public int getLengthInMinutes() {
        return lengthInMinutes;
    }

    public String getGenre() {
        return genre;
    }

    public LocalDate getLastWatched() {
        return lastWatched;
    }

    // Returnerer true, hvis filmen er blevet markeret som set.
    public boolean hasBeenWatched() {
        return lastWatched != null;
    }

    // Markerer filmen som set på en bestemt dato.
    public void markAsWatched(LocalDate date) {

        if (date == null) {
            throw new IllegalArgumentException(
                    "Datoen må ikke være tom."
            );
        }

        if (date.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException(
                    "Datoen må ikke være i fremtiden."
            );
        }

        lastWatched = date;
    }

    // Beregner antallet af dage siden filmen sidst blev set.
    public long getDaysSinceWatched(LocalDate today) {

        if (lastWatched == null) {
            throw new IllegalStateException(
                    "Filmen er ikke blevet markeret som set."
            );
        }

        return ChronoUnit.DAYS.between(lastWatched, today);
    }

    // Setters: Kontrollerer oplysningerne, før de gemmes.

    public void setTitle(String title) {

        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Titel må ikke være tom."
            );
        }

        this.title = title;
    }

    public void setDirector(String director) {

        if (director == null || director.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Instruktør må ikke være tom."
            );
        }

        this.director = director;
    }

    public void setYearCreated(int yearCreated) {

        int latestYear = LocalDate.now().getYear() + 1;

        if (yearCreated < FIRST_MOVIE_YEAR
                || yearCreated > latestYear) {

            throw new IllegalArgumentException(
                    "Årstallet skal være mellem "
                            + FIRST_MOVIE_YEAR + " og "
                            + latestYear + "."
            );
        }

        this.yearCreated = yearCreated;
    }

    public void setInColor(boolean inColor) {
        this.inColor = inColor;
    }

    public void setLengthInMinutes(int lengthInMinutes) {

        if (lengthInMinutes < 1) {
            throw new IllegalArgumentException(
                    "Filmens længde skal være mindst 1 minut."
            );
        }

        this.lengthInMinutes = lengthInMinutes;
    }

    public void setGenre(String genre) {

        if (genre == null || genre.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Genre må ikke være tom."
            );
        }

        this.genre = genre;
    }
}