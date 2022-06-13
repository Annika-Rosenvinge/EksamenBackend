package facades;

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

public class MatchFacadeTest {

    private static EntityManagerFactory emf;
    private static MatchFacade MATCHFACADE;

    Match match;
    Match match2;
    Location location;
    Player player;
    Team team;

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
        //hvad hedder en man med 2 nn/ænder... Finn
        match = new Match("Finn Andersen", "Træningskamp", "outdoor");
        match2 = new Match("Cliff Mogensen", "Afslutningskamp", "indoor");
        location = new Location("DGI byen", "ved Hovedbanen", "københavn", "Fine");
        player = new Player("Kurt", 9803082, "mail", "aktiv");
        team = new Team("Seniorbold", "Frederiksberg");

        team.addPlayer(player);
        player.setTeam(team);
        try{
            em.getTransaction().begin();
            em.createNamedQuery("Match.deleteAll", Match.class);

            em.persist(match);
            em.persist(location);
            em.persist(player);
            em.persist(team);

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
        String actual = MATCHFACADE.createPlayer(playerDTO, team.getId()).getName();

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
    public void createTeamTest(){
        TeamDTO teamDTO = new TeamDTO("Fede finn og foldbold bois", "Roskilde");
        String expected = teamDTO.getName();
        String actual = MATCHFACADE.createTeam(teamDTO).getName();

        assertEquals(expected,actual);
    }
}
