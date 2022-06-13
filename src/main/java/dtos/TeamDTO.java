package dtos;

import entities.Match;
import entities.Player;
import entities.Team;

import java.util.ArrayList;
import java.util.List;

public class TeamDTO {
    private String name;
    private String place;
    private List<Player> playersOnTeam;
    private List<Match> matches;

    public TeamDTO(){

    }

    public TeamDTO(String name, String place){
        this.name = name;
        this.place = place;
    }

    public TeamDTO(Team team){
        this.name = team.getName();
        this.place = team.getPlace();
        if(team.getPlayersOnTeam() != null){
            this.playersOnTeam = team.getPlayersOnTeam();
        }
        if(team.getMatches() != null){
            this.playersOnTeam = team.getPlayersOnTeam();
        }
    }

    //DTOs as list
    public static List<TeamDTO> getDTOs(List<Team> teams){
        List <TeamDTO> teamDTOS = new ArrayList<>();
        if (teams != null){
            teams.forEach(team -> teamDTOS.add(new TeamDTO(team)));
        }
        return teamDTOS;
    }

    //gettere og settere

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public List<Player> getPlayersOnTeam() {
        return playersOnTeam;
    }

    public void setPlayersOnTeam(List<Player> playersOnTeam) {
        this.playersOnTeam = playersOnTeam;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    @Override
    public String toString() {
        return "TeamDTO{" +
                "name='" + name + '\'' +
                ", place='" + place + '\'' +
                ", playersOnTeam=" + playersOnTeam +
                ", matches=" + matches +
                '}';
    }
}
