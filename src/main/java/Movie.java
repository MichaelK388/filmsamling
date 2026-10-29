import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Movie {
    private String title;
    private String director;
    private int yearCreated;
    private boolean inColor;
    private int lengthInMinutes;
    private String genre;
    private LocalDate lastWatched;

    public Movie(String title, String director, int yearCreated, boolean inColor, int lengthInMinutes, String genre) {

        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Titel må ikke være tom");
        }

        if (director == null || director.trim().isEmpty()) {
            throw new IllegalArgumentException("Instruktør må ikke være tom");
        }

        if (genre == null || genre.trim().isEmpty()) {
            throw new IllegalArgumentException("Genre må ikke være tom");
        }
        if (yearCreated < 1888 || yearCreated > 2027) {
            throw new IllegalArgumentException("Årstallet skal være mellem 1888 og 2027");
        }
        if (lengthInMinutes < 1) {
            throw new IllegalArgumentException("Filmens længde skal være mindst 1 minut");
        }

        this.title = title;
        this.director = director;
        this.yearCreated = yearCreated;
        this.inColor = inColor;
        this.lengthInMinutes = lengthInMinutes;
        this.genre = genre;
        this.lastWatched = null;
    }

    public String getTitle(){
        return title;
    }
    public String getDirector(){
        return director;
    }
    public int getYearCreated(){
        return yearCreated;
    }
    public boolean isInColor(){
        return inColor;
    }
    public int getLengthInMinutes(){
        return lengthInMinutes;
    }
    public String getGenre(){
        return genre;
    }
    public LocalDate getLastWatched() {
        return lastWatched;
    }
    public boolean hasBeenWatched() {
        return lastWatched != null;
    }
    public void markAsWatched(LocalDate date) {

        System.out.println("Dato modtaget: " + date);

       if (date.isAfter(LocalDate.now())){
        throw new IllegalArgumentException("Datoen må ikke være i fremtiden");
       }
        lastWatched = date;
    }
    public long getDaysSinceWatched(LocalDate today) {
        return ChronoUnit.DAYS.between(lastWatched, today);
    }
    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Titel må ikke være tom");
        }
        this.title = title;
    }    public void setDirector(String director){
        this.director = director;
    }
    public void setYearCreated(int yearCreated){
        this.yearCreated = yearCreated;
    }
    public void setInColor(boolean inColor){
        this.inColor = inColor;
    }
    public void setLengthInMinutes(int lengthInMinutes){
        this.lengthInMinutes = lengthInMinutes;
    }
    public void setGenre(String genre){
        this.genre = genre;
    }

}