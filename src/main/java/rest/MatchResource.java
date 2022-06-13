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
    @Path("/createplayer/{id}")
    public String createPlayer(@PathParam("id") String player, Integer id) throws NotFoundException {
        PlayerDTO playerDTO = GSON.fromJson(player, PlayerDTO.class);
        PlayerDTO created = FACADE.createPlayer(playerDTO, id);
        return GSON.toJson(created);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/creatematch/{id}")
    public String createMatch(@PathParam("id") String match, Integer id) throws NotFoundException {
        MatchDTO matchDTO= GSON.fromJson(match, MatchDTO.class);
        MatchDTO created = FACADE.createMatch(matchDTO, id);
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

}
