import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {

    private static final String HEADER =
            "title;director;yearCreated;inColor;lengthInMinutes;genre;lastWatched";
    private String fileName;
    private int skippedLineCount;

    public FileHandler(String fileName){
        this.fileName = fileName;
    }

    public ArrayList<Movie> loadMovies() throws FileNotFoundException {

        ArrayList<Movie> movies = new ArrayList<>();
        skippedLineCount = 0;

        try (Scanner fileScanner = new Scanner(new File(fileName))) {
        //fileScanner lukkes automatisk når en blok er færdig, selv hvis exception opstår.

            if (fileScanner.hasNextLine()) {
                fileScanner.nextLine();
            }

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();

                Movie movie = parseMovie(line);

                if (movie != null) {
                    movies.add(movie);
                } else {
                    skippedLineCount++;
                }
            }
        }

        return movies;
    }

    public int getSkippedLineCount() {
        return skippedLineCount;
    }

    private Movie parseMovie(String line){
        String[] fields = line.split(";", -1);
        if (fields.length != 7) {
            return null;
        }
        try {
            String title = fields[0];
            String director = fields[1];
            int yearCreated = Integer.parseInt(fields[2]);
            boolean inColor = Boolean.parseBoolean(fields[3]);
            int lengthInMinutes = Integer.parseInt(fields[4]);
            String genre = fields[5];

            Movie movie = new Movie(title, director, yearCreated, inColor, lengthInMinutes, genre);
            if (!fields[6].isEmpty()) {
                movie.markAsWatched(LocalDate.parse(fields[6]));
            }
            return movie;
        } catch (IllegalArgumentException e) {
            return null;
        } catch (DateTimeParseException e) {
            return null;
        }
    }
    public void saveMovies(ArrayList<Movie> movies)
            throws FileNotFoundException {
            //Hvis filen ikke kan åbnes/oprettes, sender FileHandler fejlen videre med throws til UserInterface.
            //UserInterface giver besked til brugeren hvis filen ikke kan gemmes.

        PrintStream output = new PrintStream(new File(fileName));
        //fileName = filnavn som gives til FileHandler når den oprettes.

        output.println(HEADER);

        for (Movie movie : movies) {
            output.println(toCsvLine(movie)); //toCsvLine(movie) omdanner filmen til en tekstlinje
        }

        output.close();
    }
    private String toCsvLine(Movie movie) {
        String lastWatched = "";
        if (movie.hasBeenWatched()) {
            lastWatched = movie.getLastWatched().toString();
        }
        return movie.getTitle() + ";"
                + movie.getDirector() + ";"
                + movie.getYearCreated() + ";"
                + movie.isInColor() + ";"
                + movie.getLengthInMinutes() + ";"
                + movie.getGenre() + ";"
                + lastWatched;
    }
}