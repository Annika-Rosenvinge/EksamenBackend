package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.MatchDTO;
import dtos.PlayerDTO;
import dtos.TeamDTO;
import entities.Team;
import errorhandling.NotFoundException;
import facades.MatchFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PostLoad;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Path("matches")
public class MatchResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final MatchFacade FACADE = MatchFacade.getMatchFacade(EMF);

    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getInfoForAll() {
        return "{\"msg\":\"Hello anonymous\"}";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    public String allMatches(){
        List<MatchDTO> matchDTOList = FACADE.seeAllMatches();
        return GSON.toJson(matchDTOList);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/createplayer")
    public String createPlayer(String player) throws NotFoundException {
        PlayerDTO playerDTO = GSON.fromJson(player, PlayerDTO.class);
        PlayerDTO created = FACADE.createPlayer(playerDTO);
        return GSON.toJson(created);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/creatematch/{locationid}")
    public String createMatch( String match, @PathParam("locationid") Integer locationid) throws NotFoundException {
        MatchDTO matchDTO= GSON.fromJson(match, MatchDTO.class);
        MatchDTO created = FACADE.createMatch(matchDTO, locationid);
        return GSON.toJson(created);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/createteam")
    public String createTest(String team){
        TeamDTO teamDTO = GSON.fromJson(team, TeamDTO.class);
        TeamDTO created = FACADE.createTeam(teamDTO);
        return GSON.toJson(created);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/seeforteam/{id}")
    public String seeMatchesForTeam(@PathParam("id") Integer id){
        List<MatchDTO> matchDTOList = FACADE.seeMatchesForTeam(id);
        return GSON.toJson(matchDTOList);
    }
}
