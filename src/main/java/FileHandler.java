import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {

    private String fileName;
    private int skippedLineCount;

    public FileHandler(String fileName){
        this.fileName = fileName;
    }

    public ArrayList<Movie> loadMovies() throws FileNotFoundException {

        ArrayList<Movie> movies = new ArrayList<>();
        skippedLineCount = 0;

        Scanner fileScanner = new Scanner(new File(fileName));

        if (fileScanner.hasNextLine()){
            fileScanner.nextLine();
        }

        while (fileScanner.hasNextLine()){

            String line = fileScanner.nextLine();

            Movie movie = parseMovie(line);

            if (movie != null){
                movies.add(movie);
            } else {
                skippedLineCount++;
            }
        }
        fileScanner.close();
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
}
