import java.util.ArrayList;
import java.util.Scanner;

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
        System.out.println("0. Afslut");
        System.out.print("Vælg: ");
    }
    private void createMovie(){
        System.out.print("Titel: ");
        String title = scanner.nextLine();

        System.out.print("Instruktør: ");
        String director = scanner.nextLine();

        System.out.print("Årstal: ");
        int yearCreated = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("Er filmen i farver? (ja/nej): ");
        boolean inColor = scanner.nextLine().equalsIgnoreCase("ja");

        System.out.print("Længde i minutter: ");
        int lengthInMinutes = Integer.parseInt(scanner.nextLine().trim());

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

        System.out.print("Vælg filmnummer: ");
        int choice = Integer.parseInt(scanner.nextLine());

        Movie movie = results.get(choice - 1);

        System.out.print("Ny titel: ");
        String title = scanner.nextLine();

        System.out.print("Ny instruktør: ");
        String director = scanner.nextLine();

        System.out.print("Nyt årstal: ");
        int yearCreated = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("Er filmen i farver? (ja/nej): ");
        boolean inColor = scanner.nextLine().equalsIgnoreCase("ja");

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

        System.out.print("Vælg filmnummer: ");
        int choice = Integer.parseInt(scanner.nextLine());

        Movie movie = results.get(choice - 1);

        System.out.print("Er du sikker? (ja/nej): ");
        String confirm = scanner.nextLine();

        if (confirm.equalsIgnoreCase("ja")) {

            boolean deleted = controller.deleteMovie(movie);

            if (deleted) {
                System.out.println("Filmen blev slettet.");
            }
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
    }
}
