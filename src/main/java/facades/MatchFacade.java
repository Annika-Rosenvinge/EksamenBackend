package facades;

import dtos.*;
import entities.*;
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

    //create player - us4
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

    //create match - us4
    public MatchDTO createMatch(MatchDTO matchDTO, Integer location_id) throws NotFoundException{
        EntityManager em = emf.createEntityManager();
        try{
            Location location = em.find(Location.class, location_id);
            if(location == null){
                throw new NotFoundException("there is no location with the given id");
            }
            Match match = new Match(matchDTO.getJudge(), matchDTO.getType(), matchDTO.getIndoors_outdoors(), location);
            location.addMatch(match);

            em.getTransaction().begin();
            em.persist(match);
            em.getTransaction().commit();
            return new MatchDTO(match);
        }finally {
            em.close();
        }
    }

    //create team - us4
    public TeamDTO createTeam(TeamDTO teamDTO){
        EntityManager em = emf.createEntityManager();
        try{
            Team team = new Team(teamDTO.getName(), teamDTO.getPlace());
            em.getTransaction().begin();
            em.persist(team);
            em.getTransaction().commit();
            return new TeamDTO(team);
        }finally {
            em.close();
        }
    }
    //add players to team
    /*
    public TeamDTO addPlayerToTeam(Integer playerId, Integer teamId) throws Exception{
        EntityManager em = emf.createEntityManager();
        try{
            Player player = em.find(Player.class, playerId);
            if(player == null){
                throw new NotFoundException("There is no player with his name");
            }
            if(player.getTeam() != null){
                throw new Exception("The player already has a team");
            }
            Team team = em.find(Team.class, teamId);
            if (team == null){
                throw new NotFoundException("There is no team with this id");
            }
            team.addPlayer(player);
            player.setTeam(team);
            em.getTransaction().begin();
            em.merge(team);
            em.merge(player);
            em.getTransaction().commit();
            return new TeamDTO(team);
        }finally {
            em.close();
        }
    }
    //lav en liste i frontend, evt vis alle mulige og en knap ud fra deres navn
    public TeamDTO addMulPlayersToTeam(List<Player> players, Integer teamId){
        EntityManager em = emf.createEntityManager();
        try{
            Team team = em.find(Team.class,teamId);
            for(int i = 0; i<players.size(); i++ ){
                Player player = em.find(Player.class, players.get(i).getId());
                team.addPlayer(player);
            }
            return null;
        }finally {
            em.close();
        }
    }*/

    //create location -us4
    //delete player - us7
    //all players
    //update match - add teams -us5
    //match on location - us3


    //assigned matched- us2
    //big update on match
}
