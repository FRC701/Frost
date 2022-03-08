package org.robovikes.frost.DataManager;

import org.robovikes.frost.MainActivity;

import java.util.ArrayList;

public class Team  {
    public Integer team;
    public ArrayList<Team> teams;

    Team Team = new Team();
    public ArrayList<Team> getTeams() {
        return teams;
    }
    public Team getTeam(Integer team) {
        Team set = null;
        for(Team Team : teams) {
            if(Team == getTeam(team)) {
                set = Team;
            }
        }
        return set;
    }
    public Integer getScore(Team team) {
        return 1;
    }
    public Double getAveragePoints(Team team) {
        return 1.1;
    }
    public Integer getRankingPoints(Team team) {
        return 1;
    }
}
