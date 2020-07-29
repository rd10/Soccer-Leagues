package com.example;

public class Team {
    private String name;
    private int games, wins, draws, losses, points, goalsFor, goalsAgainst, goalDifference;

    public Team(String name) {
        this.name = name;
        this.games = 0;
        this.wins = 0;
        this.draws = 0;
        this.losses = 0;
        this.points = 0;
        this.goalsFor = 0;
        this.goalsAgainst = 0;
        this.goalDifference = 0;
    }

    public String getName(){
        return name;
    }

    public int getGames() {
        return games;
    }

    public void setGames(int games) {
        this.games += games;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins += wins;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws += draws;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses += losses;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points += points;
    }

    public int getGoalsFor() {
        return goalsFor;
    }

    public void setGoalsFor(int goalsFor) {
        this.goalsFor += goalsFor;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public void setGoalsAgainst(int goalsAgainst) {
        this.goalsAgainst += goalsAgainst;
    }

    public int getGoalDifference() {
        return goalDifference;
    }

    public void setGoalDifference(int goalDifference) {
        this.goalDifference += goalDifference;
    }

    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                ", games='" + games + '\'' +
                ", wins" + wins + '\'' +
                ", points" + points + '\'' +
                '}';
    }
}
