package com.example;

import java.util.ArrayList;
import java.util.List;

public class Schedule {
    private final List<Team> teams;
    private int numberOfTeams;
    private final List<Match> matches = new ArrayList<>();
    private final int againstEach;
    private String tournamentType;

    public Schedule(List<Team> teams, int againstEach, String tournamentType){
        this.teams = teams;
        this.againstEach = againstEach;
        setNumberOfTeams(teams.size());
        this.tournamentType = tournamentType;
    }

    public int getNumberOfTeams() {
        return numberOfTeams;
    }

    public void setNumberOfTeams(int numberOfTeams) {
        this.numberOfTeams = numberOfTeams;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setLeagueMatches(){
        //(numberOfTeams - 1) * numberOfTeams)/2

        int rounds, count, saveCount;
        Match match;

        if(numberOfTeams %2 == 0){
            rounds = count = saveCount = numberOfTeams -1;
        }
        else {
            rounds = numberOfTeams;                       //calculate the amount of rounds based on amount of teams
            count = saveCount = numberOfTeams - 2;
        }

        for(int j=0; j<rounds; j++){
            for(int i = 0; i<numberOfTeams/2; i++) {                               //numberOfTeams/2 = amount of games per round

                if(i%2 != 0 || ( i==0 && j%2 != 0 && numberOfTeams%2 == 0)){       // used so a team will play the same amount of games home and away
                    match = new Match(teams.get(count), teams.get(i));
                }
                else{
                    match = new Match(teams.get(i), teams.get(count));
                }

                matches.add(match);
                count --;
            }

            count = saveCount;                              // count goes back to the initial value in the array to begin matching teams again for the next round
            rotateTeams();                                  // use the round robin method so every team will play a new team each round
        }
            againstEachTeam();                              //create more matches depending on how many times each team plays one another
        System.out.print("\n");
    }

    public void setKnockoutMatches(){
        Match match;
        int count = numberOfTeams-1;
        for (int i=0; i<numberOfTeams/2; i++) {
            match = new Match(teams.get(i), teams.get(count));
            matches.add(match);
            count--;
        }
        againstEachTeam();
    }


    public void againstEachTeam(){
        Match newMatch;
        int count = matches.size();
        for(int i=1; i<againstEach; i++){
            for(int j=0; j<count; j++){
                if(i%2 != 0) {
                    newMatch = new Match(matches.get(j).getAwayTeam(), matches.get(j).getHomeTeam());
                } else {
                    newMatch = new Match(matches.get(j).getHomeTeam(), matches.get(j).getAwayTeam());
                }
                matches.add(newMatch);
            }
        }
    }

    //needed to implement an algorithm for a round robin tournament
    public void rotateTeams(){
        int rotateAt = 0;                                       // if there is an odd number of teams rotate them all
        if(numberOfTeams %2 == 0) rotateAt = 1;                 // if there is an even numer of teams rotate all except the first one

        Team temp;
        temp = teams.get(rotateAt);                             // store the first team in a temporary location to no lose it when it's replaced
        for(int i = rotateAt; i< numberOfTeams -1; i++) {
            teams.set(i, teams.get(i+1));                       // replace team at index k with team at index k+1
        }
        teams.set((numberOfTeams -1), temp);
    }

}
