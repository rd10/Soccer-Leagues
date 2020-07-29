package com.example;

import java.util.List;

public class Series {

    //TODO mark series as quarterfinal or semifinal or final
    private List<Match> matchSeries;

    private Team firstTeam;
    private Team secondTeam;
    private int firstTeamGoals;
    private int secondTeamGoals;
    private int firstTeamAwayGoals;
    private int secondTeamAwayGoals;


    private int firstTeamPenalties;
    private int secondTeamPenalties;

    public Series(List<Match> series) {
        this.matchSeries = series;
    }

    public List<Match> getSeries() {
        return matchSeries;
    }

    public Team getFirstTeam() {
        return firstTeam;
    }

    public void setFirstTeam(Team firstTeam) {
        this.firstTeam = firstTeam;
    }

    public Team getSecondTeam() {
        return secondTeam;
    }

    public void setSecondTeam(Team secondTeam) {
        this.secondTeam = secondTeam;
    }

    public int getFirstTeamGoals() {
        return firstTeamGoals;
    }

    public void setFirstTeamGoals(int firstTeamGoals) {
        this.firstTeamGoals += firstTeamGoals;
    }

    public int getSecondTeamGoals() {
        return secondTeamGoals;
    }

    public void setSecondTeamGoals(int secondTeamGoals) {
        this.secondTeamGoals += secondTeamGoals;
    }

    public int getFirstTeamAwayGoals() {
        return firstTeamAwayGoals;
    }

    public void setFirstTeamAwayGoals(int firstTeamAwayGoals) {
        this.firstTeamAwayGoals += firstTeamAwayGoals;
    }

    public int getSecondTeamAwayGoals() {
        return secondTeamAwayGoals;
    }

    public void setSecondTeamAwayGoals(int secondTeamAwayGoals) {
        this.secondTeamAwayGoals += secondTeamAwayGoals;
    }

    public int getFirstTeamPenalties() {
        return firstTeamPenalties;
    }

    public void setFirstTeamPenalties(int firstTeamPenalties) {
        this.firstTeamPenalties = firstTeamPenalties;
    }

    public int getSecondTeamPenalties() {
        return secondTeamPenalties;
    }

    public void setSecondTeamPenalties(int secondTeamPenalties) {
        this.secondTeamPenalties = secondTeamPenalties;
    }

}
