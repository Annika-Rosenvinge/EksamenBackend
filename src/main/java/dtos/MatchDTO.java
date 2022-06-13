package dtos;

import entities.Location;
import entities.Match;
import entities.Team;

import java.util.ArrayList;
import java.util.List;

public class MatchDTO {
    private String judge;
    private String type;
    private String indoors_outdoors;
    private List<Team> teams;
    private Location location;

    //Constructor
    public MatchDTO(){

    }
    public MatchDTO(String judge, String type, String indoors_outdoors){
        this.judge = judge;
        this.type = type;
        this.indoors_outdoors = indoors_outdoors;
    }

    public MatchDTO(Match match){
        this.judge = match.getJudge();
        this.type = match.getType();
        this.indoors_outdoors = match.getIndoors_outdoors();
        if(match.getTeams() != null){
            this.teams = match.getTeams();
        }
        if(match.getLocation() != null){
            this.location = match.getLocation();
        }
    }

    //DTOS as list
    public static List<MatchDTO> getDTOS(List<Match> matches){
        List<MatchDTO> matchDTOS = new ArrayList<>();
        if(matches != null){
            matches.forEach(match -> matchDTOS.add(new MatchDTO(match)));
        }
        return matchDTOS;
    }

    //gettere settere

    public String getJudge() {
        return judge;
    }

    public void setJudge(String judge) {
        this.judge = judge;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIndoors_outdoors() {
        return indoors_outdoors;
    }

    public void setIndoors_outdoors(String indoors_outdoors) {
        this.indoors_outdoors = indoors_outdoors;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "MatchDTO{" +
                "judge='" + judge + '\'' +
                ", type='" + type + '\'' +
                ", indoors_outdoors='" + indoors_outdoors + '\'' +
                ", teams=" + teams +
                ", location=" + location +
                '}';
    }
}

