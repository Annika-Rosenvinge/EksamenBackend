package facades;

import dtos.LocationDTO;
import dtos.MatchDTO;
import dtos.PlayerDTO;
import dtos.TeamDTO;
import entities.Location;
import entities.Match;
import entities.Player;
import entities.Team;
import errorhandling.NotFoundException;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

public class MatchFacadeTest {

    private static EntityManagerFactory emf;
    private static MatchFacade MATCHFACADE;

    Match match;
    Match match2;
    Location location;
    Player player;
    Team team;

    //ekstra players til players to team test
    Player player1;
    Player player2;
    Player player3;
    Player player4;

    Team team1;

    public MatchFacadeTest(){

    }

    @BeforeAll
    public static void setupClass(){
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        MATCHFACADE = MatchFacade.getMatchFacade(emf);
    }


    @BeforeEach
    public void setUp(){
        EntityManager em = emf.createEntityManager();
        //base
        match = new Match("Finn Andersen", "Træningskamp", "outdoor");
        match2 = new Match("Cliff Mogensen", "Afslutningskamp", "indoor");
        location = new Location("DGI byen", "ved Hovedbanen", "københavn", "Fine");
        player = new Player("Kurt", 9803082, "mail", "aktiv");
        team = new Team("Seniorbold", "Frederiksberg");

        team.addPlayer(player);
        player.setTeam(team);
        match.addTeam(team);

        //ekstra players til players to team test
        player1 = new Player("Sofie", 28732, "mail", "aktiv");
        player2 = new Player("Katt", 782434, "mail", "aktiv");
        player3 = new Player("Vladimir", 9879687, "mail", "aktiv");
        player4 = new Player("Maria", 234525, "maik", "aktiv");

        team1 = new Team("Friske Røde", "Solbjerg idrætshus");
        try{
            em.getTransaction().begin();
            em.createNamedQuery("Match.deleteAll", Match.class);

            em.persist(match);
            em.persist(location);
            em.persist(player);
            em.persist(team);

            em.persist(player1);
            em.persist(player2);
            em.persist(player3);
            em.persist(player4);

            em.getTransaction().commit();
        }finally {
            em.close();
        }
    }

    @Test
    public void seeAllMatchesTest(){
        int expected = 1;
        int actual = MATCHFACADE.seeAllMatches().size();

        assertEquals(expected, actual);
        System.out.println(expected + actual);
    }

    @Test
    public void createPlayerTest() throws NotFoundException {
        PlayerDTO playerDTO = new PlayerDTO("hans", 3084, "email", "død");
        String expected = playerDTO.getName();
        String actual = MATCHFACADE.createPlayer(playerDTO).getName();

        assertEquals(expected, actual);
    }

    @Test
    public void createMatchTest() throws NotFoundException{

        MatchDTO matchDTO = new MatchDTO("Ingen", "test", "udenfor");
        String expected = matchDTO.getJudge();
        String actual = MATCHFACADE.createMatch(matchDTO, location.getId()).getJudge();

        assertEquals(expected, actual);
    }

    @Test
    public void createMatchTest2() throws NotFoundException{

        MatchDTO matchDTO = new MatchDTO("Ingen", "test", "udenfor");
        String expected = matchDTO.getJudge();
        String actual = MATCHFACADE.createMatch2(matchDTO, location.getId(), team.getId(), team1.getId()).getJudge();

        assertEquals(expected, actual);
    }


    @Test
    public void createTeamTest(){
        TeamDTO teamDTO = new TeamDTO("Fede finn og foldbold bois", "Roskilde");
        String expected = teamDTO.getName();
        String actual = MATCHFACADE.createTeam(teamDTO).getName();

        assertEquals(expected,actual);
    }

    @Test
    public void addPlayerToTeamTest() throws Exception {
        Integer playerId = player1.getId();;
        Integer teamId = team.getId();

        String expected = team.getName();
        TeamDTO teamDTO = MATCHFACADE.addPlayerToTeam(playerId, teamId);
        String actual = teamDTO.getName();

        assertEquals(expected, actual);
    }

    @Test
    public void addMulPlayers(){
        List<Player> players = new ArrayList<>();
        players.add(player2);
        players.add(player3);
        players.add(player4);

        String expected = team.getName();
        TeamDTO teamDTO = MATCHFACADE.addMulPlayersToTeam(players, team.getId());
        String actual = teamDTO.getName();
        assertEquals(expected,actual);
    }

    @Test
    public void createLocationTest(){
        LocationDTO locationDTO = new LocationDTO("Parken", "Parken1", "CPH", "Great");
        String expected = locationDTO.getName();
        String actual = MATCHFACADE.createLocation(locationDTO).getName();
        assertEquals(expected, actual);
    }

    @Test
    public void seeMatchesForTeamTest(){
        Integer expected = team.getMatches().size();
        Integer actual = MATCHFACADE.seeMatchesForTeam(team.getId()).size();

        assertEquals(expected, actual);
        System.out.println(expected + " " + actual);
    }
}
