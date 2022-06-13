package facades;

import entities.Location;
import entities.Match;
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

        try{
            em.getTransaction().begin();
            em.createNamedQuery("Match.deleteAll", Match.class);

            em.persist(match);
            em.persist(location);

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

}