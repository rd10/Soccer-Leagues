package com.example;

import java.util.List;

public class League {
    private final List<Team> teams;

    public League(List<Team> teams) {
        this.teams = teams;
    }

    public void beginLeague() {
        int againstEach = Prompt.vsEachTeam();
        Schedule schedule = new Schedule(teams, againstEach, "league");
        schedule.setLeagueMatches();
        List<Match> matches = schedule.getMatches();

        for (Match match : matches) {
            Prompt.matchScore(match); //index 0 is home team's score on returned array
            calculatePoints(match);
            Display.table(teams);
        }
    }

    public void calculatePoints(Match match) {
        Team homeTeam = match.getHomeTeam();
        Team awayTeam = match.getAwayTeam();
        int homeScore = match.getHomeGoals();
        int awayScore = match.getAwayGoals();

        homeTeam.setGames(1);
        homeTeam.setGoalsFor(homeScore);
        homeTeam.setGoalsAgainst(awayScore);
        homeTeam.setGoalDifference(homeScore - awayScore);

        awayTeam.setGames(1);
        awayTeam.setGoalsFor(awayScore);
        awayTeam.setGoalsAgainst(homeScore);
        awayTeam.setGoalDifference(awayScore - homeScore);

        if (homeScore == awayScore) {
            homeTeam.setDraws(1);
            homeTeam.setPoints(1);
            awayTeam.setDraws(1);
            awayTeam.setPoints(1);
        } else if (homeScore > awayScore) {//home team wins
            homeTeam.setWins(1);
            homeTeam.setPoints(3);
            awayTeam.setLosses(1);
        } else {
            awayTeam.setWins(1);
            awayTeam.setPoints(3);
            homeTeam.setLosses(1);
        }
    }
}
