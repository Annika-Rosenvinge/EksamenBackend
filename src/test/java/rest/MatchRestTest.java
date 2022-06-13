package rest;

import entities.Match;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

import static io.restassured.RestAssured.given;

@Disabled
public class MatchRestTest {
    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;

    Match match;
    Match match2;

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }
    @BeforeAll
    public static void setUpClass() {
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactoryForTest();

        httpServer = startServer();
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterAll
    public static void closeTestServer() {
        EMF_Creator.endREST_TestWithDB();

        httpServer.shutdownNow();
    }

    @BeforeEach
    public void setUp(){
        EntityManager em = emf.createEntityManager();
        match = new Match("ingen", "træming", "indoors");
        match2 = new Match("Sofus", "hygge inden ferie", "outdoors");
        try{
            em.getTransaction().begin();
            em.createNamedQuery("Match.deleteAll", Match.class);
            em.persist(match);
            em.persist(match2);
            em.getTransaction().commit();
        }finally {
            em.close();
        }
    }

    private static void seeMatchesForTeam (){

    }

    @Test
    public void seeAllMatchesTest(){
        given()
                .when()
                .get("/matches/all").then()
                .statusCode(200);
    }


    /*
    @Test
    public void createPlayerTest(){
        given()
                .when()
                .get("/maches/createplayer").then()
                .statusCode(200);
    }*/



}
