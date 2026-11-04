classDiagram

class Main {
+main(String[] args)
}

class UserInterface {
-Scanner scanner
-Controller controller

    +startProgram()

    -printMenu()
    -readInt(String prompt)
    -readYear(String prompt)
    -readLength(String prompt)
    -readNonEmptyString(String fieldName)
    -readYesNo(String prompt)

    -createMovie()
    -showAllMovies()
    -searchMovies()
    -editMovie()
    -deleteMovie()
    -markMovieAsWatched()
    -showMoviesNotWatchedForDays()

    -chooseMovie(ArrayList<Movie> results)
    -printMovie(Movie movie)
}

class Controller {
-MovieCollection movieCollection

    +addMovie(String title, String director, int yearCreated, boolean inColor, int lengthInMinutes, String genre)
    +getMovies()
    +searchMovies(String searchText)
    +editMovie(Movie movie, String title, String director, int yearCreated, boolean inColor, int lengthInMinutes, String genre)
    +deleteMovie(Movie movie)
    +markAsWatched(Movie movie)
    +getMoviesNotWatchedForDays(int days)
}

class MovieCollection {
-ArrayList<Movie> movies

    +addMovie(String title, String director, int yearCreated, boolean inColor, int lengthInMinutes, String genre)
    +getMovies()
    +getNumberOfMovies()
    +searchMovies(String searchText)
    +editMovie(Movie movie, String title, String director, int yearCreated, boolean inColor, int lengthInMinutes, String genre)
    +deleteMovie(Movie movie)
    +getMoviesLongerThan(double minutes)
    +getMoviesFromDecade(int decade)
    +getMoviesNotWatchedSince(LocalDate date)
}

class Movie {
-String title
-String director
-int yearCreated
-boolean inColor
-int lengthInMinutes
-String genre
-LocalDate lastWatched

    +Movie(String title, String director, int yearCreated, boolean inColor, int lengthInMinutes, String genre)

    +getTitle()
    +getDirector()
    +getYearCreated()
    +isInColor()
    +getLengthInMinutes()
    +getGenre()
    +getLastWatched()

    +hasBeenWatched()
    +markAsWatched(LocalDate date)
    +getDaysSinceWatched(LocalDate today)

    +setTitle(String title)
    +setDirector(String director)
    +setYearCreated(int yearCreated)
    +setInColor(boolean inColor)
    +setLengthInMinutes(int lengthInMinutes)
    +setGenre(String genre)
}

Main --> UserInterface
UserInterface --> Controller
Controller --> MovieCollection
MovieCollection "1" o-- "*" Movie

