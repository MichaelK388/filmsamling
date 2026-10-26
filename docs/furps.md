# FURPS – vores filmsamling

| | Krav | Opfyldt? | Hvor |
|---|---|---|---|
| F | Man kan oprette, vise, søge, redigere og slette film | Ja | `MovieCollection.addMovie` `MovieCollection.getMovies` `MovieCollection.searchMovies` `MovieCollection.editMovie` `MovieCollection.deleteMovie` `UserInterface.createMovie` `UserInterface.showAllMovies` `UserInterface.searchMovies` `UserInterface.editMovie` `UserInterface.deleteMovie` `Controller.addMovie` `Controller.getMovies` `Controller.searchMovies` `Controller.editMovie` `Controller.deleteMovie` |
| U | Programmet er let at bruge og giver brugeren tydelig feedback | Ja | `UserInterface.printMenu` `UserInterface.startProgram` `UserInterface.createMovie` `UserInterface.searchMovies` `UserInterface.editMovie` `UserInterface.deleteMovie` |
| R | Programmet går ikke ned ved bogstaver i årstal | Nej - del 6 | |
| P | Søgning og visning af film gennemløber kun samlingen én gang| Ja | `MovieCollection.searchMovies` `UserInterface.showAllMovies` |
| S | Al logik uden for UserInterface er dækket af unit test| Nej - vi har ikke lavet alle tests endnu | |