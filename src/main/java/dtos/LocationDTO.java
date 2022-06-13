package dtos;

import entities.Location;
import entities.Match;

import java.util.ArrayList;
import java.util.List;

public class LocationDTO {
    private String name;
    private String address;
    private String city;
    private String condition;

    private List<Match> matches;

    public LocationDTO(){

    }

    public LocationDTO(String name, String address, String city, String condition){
        this.name = name;
        this.address = address;
        this.city = city;
        this.condition = condition;
    }

    public LocationDTO(Location location){
        this.name = location.getName();
        this.address = location.getAddress();
        this.city = location.getCity();
        this.condition = location.getCondition();
        if(location.getMatches() != null){
            this.matches = location.getMatches();
        }
    }

    //DTOS as list
    /*public static List<LocationDTO> getDTOS(List<Location> locations){
        List<LocationDTO> locationDTOS = new ArrayList<>();
        if(locations != null){
            locations.forEach(location -> locationDTOS.add(new LocationDTO(location)));
        }
        return locationDTOS;
    }*/

    //gettere og settere

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
}
