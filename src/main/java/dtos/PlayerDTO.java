package dtos;

import entities.Player;
import entities.Team;

import java.util.ArrayList;
import java.util.List;

public class PlayerDTO {
    private String name;
    private Integer phone;
    private String email;
    private String status;

    private Team team;

    //Constructors
    public PlayerDTO(){

    }

    public PlayerDTO(String name, Integer phone, String email, String status){
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.status = status;
    }

    public PlayerDTO(Player player){
        this.name = player.getName();
        this.phone = player.getPhone();
        this.email = player.getEmail();
        this.status = player.getStatus();
        if (player.getTeam() != null){
            this.team = player.getTeam();
        }
    }
    //Dtos as list
    public static List<PlayerDTO> getDTOS(List<Player> players){
        List<PlayerDTO> playerDTOs = new ArrayList<>();
        if(players != null){
            players.forEach(player -> playerDTOs.add(new PlayerDTO(player)));
        }
        return playerDTOs;
    }

    //gettere settere
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "PlayerDTO{" +
                "name='" + name + '\'' +
                ", phone=" + phone +
                ", email='" + email + '\'' +
                ", status='" + status + '\'' +
                ", team=" + team +
                '}';
    }
}
