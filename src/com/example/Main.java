package com.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        List<Team> teams;
        int num, choice;

        Map<Integer, String> menu = new HashMap<>();
        menu.put(0, "Quit Program");
        menu.put(1, "Play a league tournament");
        menu.put(2, "Play a Knockout tournament");

        for (Map.Entry<Integer, String> option : menu.entrySet()) {
            System.out.printf("%s - %s %n",
                    option.getKey(),
                    option.getValue());
        }

        do {
            System.out.print("What type of tournament do you want to play: ");
            String amount = reader.nextLine();
            choice = Validate.convertToInteger(amount);
            switch (choice) {
                case 1:
                    num = Prompt.NumberOfTeams("league");
                    teams = Prompt.populateTeams(num);
                    League league = new League(teams);
                    league.beginLeague();
                    break;
                case 2:
                    num = Prompt.NumberOfTeams("knockout");
                    teams = Prompt.populateTeams(num);
                    int againstEach = Prompt.vsEachTeam(); //how many teams will each team play one another

                    boolean finalIsMultipleGames = false;
                    if(num != 2 && againstEach > 1){ // if only two  teams, dont prompt for how many games in the final
                        finalIsMultipleGames = Prompt.finalMatch(againstEach);
                    }
                    boolean awayGoalTieBreaker =false;
                    if((againstEach & 1) == 0) { // if against each is even then dont prompt for away goal as the tiebreaker
                        awayGoalTieBreaker = Prompt.awayGoalTieBreaker();
                    }
                    boolean awayGoalInFinal = false;
                    if(finalIsMultipleGames && awayGoalTieBreaker){
                        awayGoalInFinal = Prompt.awayGoalInFinal();
                    }
                    Knockout knockout = new Knockout(teams, awayGoalTieBreaker, finalIsMultipleGames, againstEach, awayGoalInFinal);
                    knockout.beginKnockout();
                    break;
                case 0:
                    System.out.println("Goodbye");
                default:
                    System.out.println("Unknown choice. please try again \n");
            }
        }while (choice != 0);

    }

}