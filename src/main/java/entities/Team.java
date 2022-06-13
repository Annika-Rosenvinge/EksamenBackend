package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id", nullable = false)
    private Integer id;
    @Column(name = "team_name", nullable = false)
    private String name;
    @Column(name = "team_place", nullable = false)
    private String place;

    //team har players
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "team")
    private List<Player> playersOnTeam = new ArrayList<>();

    @ManyToMany(mappedBy = "teams")
    private List<Match> matches;

    //constructors
    public Team(){

    }
    public Team(String name, String place){
        this.name = name;
        this.place = place;
    }
    public Team(List<Player> playersOnTeam, String name, String place){
        this.playersOnTeam = playersOnTeam;
        this.name = name;
        this.place = place;

    }

    public Team(String name, String place, List<Match> matches){
        this.name = name;
        this.place = place;
        this.matches = matches;
    }
    public Team(String name, String place,List<Player> playersOnTeam, List<Match> matches){
        this.name = name;
        this.place = place;
        this.playersOnTeam = playersOnTeam;
        this.matches = matches;
    }
    //add function
    public void addPlayer(Player player){
        playersOnTeam.add(player);
    }


    //getter setter
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", place='" + place + '\'' +
                ", playersOnTeam=" + playersOnTeam +
                '}';
    }
}