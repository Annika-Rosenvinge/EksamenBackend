package entities;

import javax.json.bind.annotation.JsonbProperty;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "match")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "match_id", nullable = false)
    private Integer id;
    @Column(name = "jugde", nullable = false)
    private String judge;
    @Column(name = "type", nullable = false)
    private String type;
    @Column(name = "inddors_outdoors")
    private String indoors_outdoors;

    //relations
    @ManyToMany
    @JoinTable(name = "match_teams", joinColumns = {
            @JoinColumn(name = "match_id", referencedColumnName = "match_id")}, inverseJoinColumns ={
            @JoinColumn(name = "team_id", referencedColumnName = "team_id")
    })
    private List<Team> teams;

    @ManyToOne
    @JoinColumn(name = "matches")
    private Location location;

    //constructors
    public Match(){

    }

    public Match(String judge, String type, String indoors_outdoors){
        this.judge = judge;
        this.type = type;
        this.indoors_outdoors = indoors_outdoors;
    }

    public Match(String judge, String type, String indoors_outdoors, List<Team> teams){
        this.judge = judge;
        this.type = type;
        this.indoors_outdoors = indoors_outdoors;
        this.teams = teams;
    }

    public Match(String judge, String type, String indoors_outdoors, Location location){
        this.judge = judge;
        this.type = type;
        this.indoors_outdoors = indoors_outdoors;
        this.location = location;
    }

    public Match(String judge, String type, String indoors_outdoors, List<Team> teams, Location location){
        this.judge = judge;
        this.type = type;
        this.indoors_outdoors = indoors_outdoors;
        this.teams = teams;
        this.location = location;
    }


    //add func
    public void addTeam(Team team){
        teams.add(team);
    }

    //gettere settere
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
        return "Match{" +
                "id=" + id +
                ", judge='" + judge + '\'' +
                ", type='" + type + '\'' +
                ", indoors_outdoors='" + indoors_outdoors + '\'' +
                ", teams=" + teams +
                ", location=" + location +
                '}';
    }
}