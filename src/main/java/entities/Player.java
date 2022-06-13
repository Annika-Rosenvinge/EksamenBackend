package entities;

import javax.persistence.*;

@Entity
@Table(name = "player")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "player_name", nullable = false)
    private String name;
    @Column(name = "phone", nullable = false)
    private Integer phone;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "playerstatus", nullable = false)
    private String status;

    //relations
    @ManyToOne
    private Team team;

    //constructors
    public Player(){

    }

    public Player(String name, Integer phone, String email, String status){{
            this.name = name;
            this.phone = phone;
            this.email = email;
            this.status = status;

        }
    }
    public Player(String name, Integer phone, String email, String status, Team team){{
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.status = status;
        this.team = team;

    }
    }

    //gettere settere
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
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone=" + phone +
                ", email='" + email + '\'' +
                ", status='" + status + '\'' +
                ", team=" + team +
                '}';
    }
}