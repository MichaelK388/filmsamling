import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.lang.Math.abs;

public class UserInterface {
    private Scanner scanner;
    private Controller controller;

    public UserInterface(){
        scanner = new Scanner(System.in);
        controller = new Controller();
    }
    public void startProgram() {
        boolean running = true;

        while (running) {
            printMenu();

            String choice = scanner.nextLine().trim();

            switch (choice){
                case "1" -> createMovie();
                case "2" -> showAllMovies();
                case "3" -> searchMovies();
                case "4" -> editMovie();
                case "5" -> deleteMovie();
                case "6" -> markMovieAsWatched();
                case "7" -> showMoviesNotWatchedForDays();
                case "0" -> running = false;
                default -> System.out.println("Ukendt valg: " + choice);
            }
        }
    }
    private void printMenu() {
        System.out.println("\n=== FILMSAMLING ===");
        System.out.println("1. Opret film");
        System.out.println("2. Vis alle film");
        System.out.println("3. Søg efter film");
        System.out.println("4. Rediger film");
        System.out.println("5. Slet film");
        System.out.println("6. Markér film som set");
        System.out.println("7. Film jeg ikke har set længe");
        System.out.println("0. Afslut");
        System.out.print("Vælg: ");
    }
    //Bruges til at fange exceptions, hvor brugeren skriver bogstaver i stedet for tal. Reliability.
    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("\"" + input + "\" er ikke et helt tal. Prøv igen.");
            }
        }
    }
    //Sørger for at brugeren vælger et gyldigt filmnummer, så man ikke får fejlbeskeder. Reliability.
    private Movie chooseMovie(ArrayList<Movie> results) {
        if (results.isEmpty()) {
            System.out.println("Ingen film fundet."); //besked hvis listen er tom.
            return null;
        }

        for (int i = 0; i < results.size(); i++) {
            System.out.println((i + 1) + ". " + results.get(i).getTitle());
        }

        int choice = readInt("Vælg filmnummer (0 = fortryd): ");

        while (choice < 0 || choice > results.size()) { //valg ugyldigt, hvis < 0 eller > antallet af film.
            System.out.println(
                    "Vælg et nummer mellem 1 og " + results.size()
                            + " – eller 0 for at fortryde."
            );
            choice = readInt("Vælg filmnummer (0 = fortryd): ");
        }

        if (choice == 0) { //hvis brugeren vælger 0, afslutter metoden uden at vælge en film.
            return null;
        }

        return results.get(choice - 1); //brugeren har indtastet et gyldigt filmnummer.
    }
    //Til håndtering af ja/nej spørgsmål. Reliability.
    private boolean readYesNo(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("ja") //kan håndtere ja, JA, Ja, jA
                    || input.equalsIgnoreCase("j")) {
                return true;
            }

            if (input.equalsIgnoreCase("nej")
                    || input.equalsIgnoreCase("n")) {
                return false;
            }

            System.out.println("Svar ja eller nej."); //hvis bruger skriver andet end ja/nej, spørger igen.
        }
    }
    private void createMovie(){
        System.out.print("Titel: ");
        String title = scanner.nextLine();

        System.out.print("Instruktør: ");
        String director = scanner.nextLine();

        int yearCreated = readInt("Årstal: "); //sæt ind i FURPS doc, som R eksempel

        boolean inColor = readYesNo("Er filmen i farver? (ja/nej): ");

        int lengthInMinutes = readInt("Længde i minutter: ");

        System.out.print("Genre: ");
        String genre = scanner.nextLine();

        controller.addMovie(title, director, yearCreated, inColor, lengthInMinutes, genre);

        System.out.print("Filmen \"" + title + "\" er oprettet.");
    }

    private void showAllMovies(){
        ArrayList<Movie> movies = controller.getMovies();

        if (movies.isEmpty()){
            System.out.println("Samlingen er tom.");
        }
        for (Movie movie : movies){
            printMovie(movie);
        }
    }

    private void searchMovies(){
        System.out.print("Søg efter titel: ");
        String searchText = scanner.nextLine();

        ArrayList<Movie> results = controller.searchMovies(searchText);

        if (results.isEmpty()) {
            System.out.println("Ingen film fundet.");
            return;
        }
        for (Movie movie : results) {
            printMovie(movie);
        }
    }

    private void editMovie(){
        System.out.println("Søg efter film: ");
        String searchText = scanner.nextLine();

        ArrayList<Movie> results = controller.searchMovies(searchText);

        if (results.isEmpty()) {
            System.out.println("Ingen film fundet.");
            return;
        }
        for (int i = 0; i < results.size(); i++) {
            System.out.println((i + 1) + ". " + results.get(i).getTitle());
        }

        int choice = readInt("Vælg filmnummer: ");

        Movie movie = results.get(choice - 1);

        System.out.print("Ny titel: ");
        String title = scanner.nextLine();

        System.out.print("Ny instruktør: ");
        String director = scanner.nextLine();

        int yearCreated = readInt("Nyt årstal: ");

        boolean inColor = readYesNo("Er filmen i farver? (ja/nej): ");

        System.out.print("Ny længde i minutter: ");
        int lengthInMinutes = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("Ny genre: ");
        String genre = scanner.nextLine();

        boolean edited = controller.editMovie(movie, title, director, yearCreated, inColor, lengthInMinutes, genre);

        if (edited){
            System.out.println("Filmen blev opdateret.");
            printMovie(movie);
        }
    }

    private void deleteMovie(){
        System.out.print("Søg efter film: ");
        String searchText = scanner.nextLine();

        ArrayList<Movie> results = controller.searchMovies(searchText);

        if (results.isEmpty()) {
            System.out.println("Ingen film fundet.");
            return;
        }
        Movie movie = chooseMovie(results); //chooseMovie(results) håndterer brugerens valg.

        if (movie == null) {
            return;
        }
        boolean confirm = readYesNo("Er du sikker? (ja/nej): ");

        if (confirm) {
            boolean deleted = controller.deleteMovie(movie);

            if (deleted) {
                System.out.println("Filmen blev slettet.");
            }
        }
    }
    private void markMovieAsWatched() {
        System.out.print("Søg efter titel: ");
        String searchText = scanner.nextLine();

        ArrayList<Movie> results = controller.searchMovies(searchText);

        if (results.isEmpty()){
            System.out.println("Ingen film fundet.");
            return;
        }
        for (int i = 0; i < results.size(); i++) {
            System.out.println((i + 1) + ". " + results.get(i).getTitle());
        }
        System.out.println("Vælg filmnummer: ");
        int choice = Integer.parseInt(scanner.nextLine());

        Movie movie = results.get(choice - 1);

        controller.markAsWatched(movie);

        System.out.println(movie.getTitle() + " er markeret som set");
    }

    private void showMoviesNotWatchedForDays() {
        System.out.println("Hvor mange dage siden mindst? ");

        int days = Integer.parseInt(scanner.nextLine());

        ArrayList<Movie> movies = controller.getMoviesNotWatchedForDays(days);

        if (movies.isEmpty()) {
            System.out.println("Ingen film fundet.");
            return;
        }
        for (Movie movie : movies) {
            printMovie(movie);
        }
    }

    private void printMovie(Movie movie) {
        System.out.println("--------------------");
        System.out.println("Titel: " + movie.getTitle());
        System.out.println("Instruktør: " + movie.getDirector());
        System.out.println("Årstal: " + movie.getYearCreated());
        System.out.println("Type: " + (movie.isInColor() ? "Farver" : "Sort-hvid"));
        System.out.println("Længde: " + movie.getLengthInMinutes() + " min");
        System.out.println("Genre " + movie.getGenre());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        if (!movie.hasBeenWatched()) {
            System.out.println("Sidst set: aldrig");
        }
        else {
            String date = movie.getLastWatched().format(formatter);

            long days = movie.getDaysSinceWatched(LocalDate.now());

            System.out.println("Sidst set: " + date + " (for " + days + " dage siden)");
        }
    }
}
