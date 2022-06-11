package facades;

import entities.Role;
import entities.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class UserFacadeTest {

    private static EntityManagerFactory emf;
    private static UserFacade userFacade;

    User user;
    User user2;
    Role role;
    Role role2;

    public UserFacadeTest(){

    }

    @BeforeAll
    public static void setUpClass(){
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        userFacade = UserFacade.getUserFacade(emf);
    }

    @AfterAll
    public static void tearDownClass(){

    }

    @BeforeEach
    public void setUp(){
        EntityManager em = emf.createEntityManager();
        user = new User("Alice","AliceBob");
        user2 = new User ("Bob", "BobAlice");
        role = new Role ("user");
        role2 = new Role("admin");
        user.addRole(role);
        user2.addRole(role2);
        try{
            em.getTransaction().begin();
            em.createNamedQuery("Users.DeleteAll").executeUpdate();
            em.persist(user);
            em.persist(user2);
            em.getTransaction().commit();
        }
        finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown(){

    }
}
