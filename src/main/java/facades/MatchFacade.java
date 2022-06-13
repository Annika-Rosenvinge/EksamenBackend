package facades;

import dtos.MatchDTO;
import dtos.PlayerDTO;
import entities.Match;
import entities.Player;
import entities.Team;
import errorhandling.NotFoundException;

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

    //create player
    public PlayerDTO createPlayer(PlayerDTO playerDTO, Integer team_id) throws NotFoundException {
        EntityManager em = emf.createEntityManager();
        try{
            Team team = em.find(Team.class, team_id);
            if (team == null) {
                throw new NotFoundException("no team found with the id " + team_id );
            }
            Player player = new Player(playerDTO.getName(), playerDTO.getPhone(),
                    playerDTO.getEmail(), playerDTO.getStatus(), team);
            team.addPlayer(player);
            em.getTransaction().begin();
            em.persist(player);
            em.getTransaction().commit();
            return new PlayerDTO(player);
        }finally {
            em.close();
        }


    }

    //create match

    //create team
    //create location
    //delete player
    //update match
    //match on location
}
