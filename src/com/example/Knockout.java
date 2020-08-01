package com.example;

import java.util.ArrayList;
import java.util.List;

public class Knockout {
    private final List<Team> teams;
    private boolean awayGoalTieBreaker;
    private final boolean finalIsMultipleGames;
    private final boolean awayGoalInFinal;
    private int againstEach;

    public Knockout(List<Team> teams, boolean awayGoalTieBreaker, boolean finalIsMultipleGames, int againstEach, boolean awayGoalInFinal) {
        this.teams = teams;
        this.awayGoalTieBreaker = awayGoalTieBreaker;
        this.finalIsMultipleGames = finalIsMultipleGames;
        this.againstEach = againstEach;
        this.awayGoalInFinal = awayGoalInFinal;
    }

    public void beginKnockout() {
        int knockoutRounds = Math.getExponent(teams.size());
        List<Series> seriesList = new ArrayList<>();
        List<Team> remainingTeams = new ArrayList<>(teams); //new list which does not point/equal the 'teams' list
        int count = 0;

        for (int i = 0; i < knockoutRounds; i++) {

            //final round, and initial teams were more than two,(change settings for final if user specified)
            if (i == knockoutRounds - 1 && teams.size() > 2) {
                if (!finalIsMultipleGames) { // the final is only one game
                    againstEach = 1;
                }

                if (!finalIsMultipleGames || !awayGoalInFinal) { //do not use awaygoal as the tiebreaker for the final if user requested
                    setAwayGoalTieBreaker(false);
                }
            }

            int lastGameOfSeries = (remainingTeams.size() / 2) * (againstEach - 1); //used to check when the last game of each series is played
            int round = remainingTeams.size() / 2;
            Schedule knockoutTournament = new Schedule(remainingTeams, againstEach, "knockout");
            knockoutTournament.setKnockoutMatches();
            List<Match> matches = knockoutTournament.getMatches();

            ArrayList<Match>[] seriesArray = new ArrayList[round]; //array of lists of matches
            for (int m = 0; m < round; m++) {
                seriesArray[m] = new ArrayList<Match>(); //initialize
            }

            for (int j = 0; j < matches.size(); j++) {
                Prompt.matchScore(matches.get(j));

                if (j % round == 0) count = 0; //used to pair matches with the same teams in a list
                seriesArray[count].add(matches.get(j)); //example: x vs y and y vs x at subscript count

                //after the last round of each series
                if (j >= lastGameOfSeries) {
                    Series series = new Series(seriesArray[count]); //move list of matches into a series
                    seriesList.add(series); //keep a record of all series?
                    nextRoundTeams(series, remainingTeams);
                }
                count++;
            }
        }
        System.out.printf("\nThe champion is %s!! \n\n", remainingTeams.get(0).getName());
    }

    public List<Team> nextRoundTeams(Series series, List<Team> remainingTeams) {

        List<Match> matches = series.getSeries();
        series.setFirstTeam(matches.get(0).getHomeTeam());
        series.setSecondTeam(matches.get(0).getAwayTeam());


        for (int i = 0; i < matches.size(); i++) { //for every match in the series, record the goals
            Match match = matches.get(i);
            if (i % 2 == 0) {
                series.setFirstTeamGoals(match.getHomeGoals());
                series.setSecondTeamGoals(match.getAwayGoals());
                series.setSecondTeamAwayGoals(match.getAwayGoals());

            } else {
                series.setSecondTeamGoals(match.getHomeGoals());
                series.setFirstTeamGoals(match.getAwayGoals());
                series.setFirstTeamAwayGoals(match.getAwayGoals());
            }
        }

        //remove the team that lost
        if (series.getFirstTeamGoals() > series.getSecondTeamGoals()) {
            remainingTeams.remove(series.getSecondTeam());
        } else if (series.getSecondTeamGoals() > series.getFirstTeamGoals()) {
            remainingTeams.remove(series.getFirstTeam());
        } else {
            if (!awayGoalTieBreaker || series.getFirstTeamAwayGoals() == series.getSecondTeamAwayGoals()) { //not using awaygoal as tiebreaker or user is using awaygoal as tiebreaker but awaygoals are equal
                int[] penalties = Prompt.penalties(series.getFirstTeam(), series.getSecondTeam());
                if (penalties[0] > penalties[1]) remainingTeams.remove(series.getSecondTeam());
                else remainingTeams.remove(series.getFirstTeam());
            } else if (series.getFirstTeamAwayGoals() > series.getSecondTeamAwayGoals()) {
                remainingTeams.remove(series.getSecondTeam());
            } else remainingTeams.remove(series.getFirstTeam());
        }
        return remainingTeams;
    }

    private void setAwayGoalTieBreaker(boolean awayGoalTieBreaker) {
        this.awayGoalTieBreaker = awayGoalTieBreaker;
    }

}
