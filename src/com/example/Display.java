package com.example;
import java.util.*;

public class Display {

    static void table(List<Team> teams){
        sortTable(teams);

        System.out.printf("\n%-20s %4s %4s %4s %4s %4s %4s %4s %4s \n", "Team", "G","W","D","L","Gf","Ga","Gd","Pts");
        System.out.println("--------------------------------------------------------\n");
        for(Team club: teams) {
            System.out.printf("%-20s %4s %4s %4s %4s %4s %4s %4s %4s \n",
                    club.getName(),
                    club.getGames(),
                    club.getWins(),
                    club.getDraws(),
                    club.getLosses(),
                    club.getGoalsFor(),
                    club.getGoalsAgainst(),
                    club.getGoalDifference(),
                    club.getPoints());
        }
        System.out.println("\n");
    }

    //sort teams by points, goals and goal differential in descending order
    static void sortTable(List<Team> teams) {
        teams.sort(Comparator.comparing(Team::getPoints).
                thenComparing(Team::getGoalDifference).
                thenComparing(Team::getGoalsFor).
                reversed());
        //teams.forEach(System.out::println);
    }

}
