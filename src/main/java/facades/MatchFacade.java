package facades;

import dtos.MatchDTO;
import entities.Match;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class MatchFacade {
    private static MatchFacade instance;
    private static EntityManagerFactory emf;


    private MatchFacade(){

    }
    public static MatchFacade getMatchFacade(EntityManagerFactory _emf){
        if(instance == null){
            emf = _emf;
            instance = new MatchFacade();
        }
        return instance;
    }

    //see all matches
    //us1: I would like to see all the matches that are available
    public List<MatchDTO> seeAllMatches() throws EntityNotFoundException {
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();

            TypedQuery <Match> typedQuery = em.createQuery("SELECT m FROM Match m", Match.class);
            List<Match> matches = typedQuery.getResultList();

            List<MatchDTO> matchDTOS = new ArrayList<>();
            for (Match m : matches){
                matchDTOS.add(new MatchDTO(m));
            }
            em.getTransaction().commit();
            return matchDTOS;
        }finally {
            em.close();
        }
    }
}
