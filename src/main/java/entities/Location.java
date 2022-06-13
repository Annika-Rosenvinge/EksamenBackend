

package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;



@Entity
@Table(name = "location")
@NamedQuery(name="Location.deleteAll", query = "DELETE FROM Location l")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "locationname", nullable = false)
    private String name;
    @Column(name = "address", nullable = false)
    private String address;
    @Column(name = "city", nullable = false)
    private String city;
    @Column(name = "cond", nullable = false, length = 1000)
    private String condition;

    //relations
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "location")
    private List<Match> matches = new ArrayList<>();

    //constructors
    public Location(){

    }
    public Location(String name, String address, String city, String condition){
        this.name = name;
        this.address = address;
        this.city  = city;
        this.condition = condition;
    }

    public Location(String name, String address, String city, String condition, List<Match> matches){
        this.name = name;
        this.address = address;
        this.city  = city;
        this.condition = condition;
        this.matches = matches;
    }

    //add func
    public void addMatch(Match match){
        matches.add(match);
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", condition='" + condition + '\'' +
                ", matches=" + matches +
                '}';
    }
}