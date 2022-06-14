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
            //em.getTransaction().begin();

            TypedQuery <Match> typedQuery = em.createQuery("SELECT m FROM Match m", Match.class);
            List<Match> matches = typedQuery.getResultList();

            List<MatchDTO> matchDTOS = new ArrayList<>();
            for (Match m : matches){
                matchDTOS.add(new MatchDTO(m));
            }
            //em.getTransaction().commit();
            return matchDTOS;
        }finally {
            em.close();
        }
    }

    //create player - us4
    public PlayerDTO createPlayer(PlayerDTO playerDTO) throws NotFoundException {
        EntityManager em = emf.createEntityManager();
        try{
            Player player = new Player(playerDTO.getName(), playerDTO.getPhone(),
                    playerDTO.getEmail(), playerDTO.getStatus());
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

    public MatchDTO createMatch2(MatchDTO matchDTO, Integer location_id, Integer teamid1, Integer teamid2) throws NotFoundException{
        EntityManager em = emf.createEntityManager();
        try{
            Location location = em.find(Location.class, location_id);
            if(location == null){
                throw new NotFoundException("there is no location with the given id");
            }
            Team team1 = em.find(Team.class, teamid1);
            if(team1 == null){
                throw new NotFoundException("there is no team with the id " + teamid1);
            }
            Team team2 = em.find(Team.class, teamid1);
            if(team2 == null){
                throw new NotFoundException("there is no team with the id " + teamid2);
            }

            Match match = new Match(matchDTO.getJudge(), matchDTO.getType(), matchDTO.getIndoors_outdoors(), location);
            location.addMatch(match);
            match.addTeam(team1);
            match.addTeam(team2);

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
    //create location -us4
    public LocationDTO createLocation(LocationDTO locationDTO){
        EntityManager em = emf.createEntityManager();
        try{
            Location location = new Location(locationDTO.getName(), locationDTO.getAddress(), locationDTO.getCity(), locationDTO.getCondition());
            em.getTransaction().begin();
            em.persist(location);
            em.getTransaction().commit();
            return new LocationDTO(location);
        }finally {
            em.close();
        }
    }

    //add players to team
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
    //lav en liste i frontend, evt vis alle mulige og en knap ud fra deres navn ala radio button
    public TeamDTO addMulPlayersToTeam(List<Player> players, Integer teamId){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            Team team = em.find(Team.class,teamId);
            for(int i = 0; i<players.size(); i++ ){
                Player player = em.find(Player.class, players.get(i).getId());
                team.addPlayer(player);
                player.setTeam(team);
                em.merge(team);
                em.merge(player);
            }
            em.getTransaction().commit();
            return new TeamDTO(team);
        }finally {
            em.close();
        }
    }


    //assigned matched- us2
    //denne er baseret på teans i stedet for personer
    //StackOverflowError in integrationstest
    //Unit virker med og uden try finally
    public List<MatchDTO> seeMatchesForTeam(Integer teamId){
        EntityManager em = emf.createEntityManager();
        //try{
            //em.getTransaction().begin();
            TypedQuery<Team> query = em.createQuery("SELECT t FROM Team t WHERE t.id=:teamId ", Team.class);
            query.setParameter("teamId", teamId);
            Team team = query.getSingleResult();
        //em.getTransaction().commit();
            return MatchDTO.getDTOS(team.getMatches());
        /*}finally {
            em.close();
        }*/
    }

    //update match - add teams -us5 halv
    public MatchDTO addTeamToMatch(Integer teamid, Integer matchid){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            Team team = em.find(Team.class, teamid);
            Match match = em.find(Match.class, matchid);
            match.addTeam(team);
            em.merge(match);
            em.getTransaction().commit();
            return new MatchDTO(match);
        }
        finally {
            em.close();
        }
    }

    //update MatchInfoLocation


    //delete player - us7
   /* public void deletePlayer(Integer playerId){
        EntityManager em = emf.createEntityManager();
        try{
            em.find(Player.class, playerId);
            em.getTransaction().begin();
            TypedQuery <Player> query = em.createQuery("DELETE FROM Player p WHERE p.id = :id", Player.class);
            em.getTransaction().commit();
        }finally {
            em.close();
        }
    }*/

    //all players

    //match on location - us3
}
