# FURPS – vores filmsamling

| | Krav | Opfyldt? | Hvor |
|---|---|---|---|
| F | Man kan oprette, vise, søge, redigere og slette film | Ja | `MovieCollection.addMovie` `MovieCollection.getMovies` `MovieCollection.searchMovies` `MovieCollection.editMovie` `MovieCollection.deleteMovie` `UserInterface.createMovie` `UserInterface.showAllMovies` `UserInterface.searchMovies` `UserInterface.editMovie` `UserInterface.deleteMovie` `Controller.addMovie` `Controller.getMovies` `Controller.searchMovies` `Controller.editMovie` `Controller.deleteMovie` `Movie.compareTo` `MovieCollection.getMoviesSortedByTitle` `Controller.getMoviesSortedBy` `UserInterface.showMoviesSortedBy` |
| U | Programmet er let at bruge og giver brugeren tydelig feedback | Ja | `UserInterface.printMenu` `UserInterface.startProgram` `UserInterface.createMovie` `UserInterface.searchMovies` `UserInterface.editMovie` `UserInterface.deleteMovie` |
| R | Programmet går ikke ned ved bogstaver i årstal | Ja | `UserInterface.readInt` `UserInterface.readYesNo` `UserInterface.readDate`|
| P | Søgning og visning af film gennemløber kun samlingen én gang, og filen skrives kun hvis der er ændringer| Ja | `MovieCollection.searchMovies` `UserInterface.showAllMovies` `Controller.saveMoviesIfChanged`|
| S | Al logik uden for UserInterface er dækket af unit test| Ja | `ControllerTest` `MovieCollectionTest` `MovieTest` `ComparatorTest` `FileHandlerTest`|