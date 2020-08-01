package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Prompt {

    static Scanner reader = new Scanner(System.in);

    static int NumberOfTeams(String tournamentType) {
        String numberOfTeams;
        boolean valid = false;
        int num;

        do {
            System.out.print("Number of teams(between 2 & 32): ");
            numberOfTeams = reader.nextLine();
            num = Validate.convertToInteger(numberOfTeams);
            valid = Validate.isRangeValid(2, 32, num);
            if (tournamentType.equals("knockout") && valid) {
                if ((num & (num - 1)) != 0) {
                    valid = false; //integer is not 2^n
                    System.out.println("Amount must be 2,4,8,16, or 32");
                }
            }
        } while (!valid);
        return num;
    }

    static List<Team> populateTeams(int numberOfTeams) {
        List<Team> teams = new ArrayList<>();
        String name;
        boolean valid = false;

        for (int i = 0; i < numberOfTeams; i++) {
            do {
                System.out.printf("Input the name for team %s: ", i + 1);
                name = reader.nextLine().trim();
                valid = Validate.isRangeValid(1, 20, name.length());
                for (Team existingTeam : teams) {
                    if (existingTeam.getName().equals(name)) {
                        System.out.println("A team already contains that name");
                        valid = false;
                    } else valid = true;
                }
            } while (!valid);

            teams.add(new Team(name));
        }
        return teams;
    }

    static int vsEachTeam() {
        String amount;
        boolean valid = false;
        int num = 0;

        do {
            System.out.print("How many games versus each team? (must be between 1 and 4): ");
            amount = reader.nextLine();
            num = Validate.convertToInteger(amount);
            valid = Validate.isRangeValid(1, 4, num);
        } while (!valid);
        return num;
    }

    static void matchScore(Match match) {
        int goals;
        String amount;
        String home = match.getHomeTeam().getName();
        String away = match.getAwayTeam().getName();

        System.out.printf("\n%s vs %s\n", home, away);
        String competitor = home;
        for (int i = 0; i < 2; i++) {
            do {
                System.out.printf("Input the goals for %s: ", competitor);
                amount = reader.nextLine();
                goals = Validate.convertToInteger(amount);
            } while (goals < 0 || goals > 40);

            if (i == 0) {
                competitor = away;
                match.setHomeGoals(goals);
            } else match.setAwayGoals(goals);
        }
    }

    static boolean awayGoalTieBreaker() {
        boolean valid = false;
        char response;
        System.out.println("Will away goals be used as a tiebreaker?");
        System.out.print("Type 'y' to enable the away goal as a tiebreaker or 'n' to go to penalties instead: ");

        do {
            response = reader.nextLine().charAt(0);
            valid = Validate.validateLetter(response);
        } while (!valid);

        return response == 'y';
    }

    static boolean finalMatch(int againstEach) {
        boolean valid = false;
        char response;
        System.out.printf("Will the Final be %s matches? ", againstEach);
        System.out.printf("Type 'y' to keep %s matches in the final or 'n' to have only one match in the final: ", againstEach);

        do {
            response = reader.nextLine().charAt(0);
            valid = Validate.validateLetter(response);
        }while (!valid);

        return response == 'y';
    }

    static int[] penalties(Team firstTeam, Team secondTeam) {
        int[] penalties = new int[2];

        System.out.printf("How many penalties did %s score: ", firstTeam.getName());
        penalties[0] = Validate.convertToInteger(reader.nextLine());
        do {
            System.out.printf("How many penalties did %s score: ", secondTeam.getName());
            penalties[1] = Validate.convertToInteger(reader.nextLine());
        } while (penalties[0] == penalties[1]);

        return penalties;
    }

    static boolean awayGoalInFinal(){
        boolean valid = false;
        char response;

        System.out.println("Will the Final use away goals as the tiebreaker?");
        System.out.println("Type 'y' to keep away goals as the tiebreaker or 'n' to use penalties: ");

        do {
            response = reader.nextLine().charAt(0);
            valid = Validate.validateLetter(response);
        }while (!valid);

        return response == 'y';
    }
}
