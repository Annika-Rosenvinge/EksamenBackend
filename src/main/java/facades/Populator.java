package facades;

import entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import utils.EMF_Creator;

public class Populator {

    public static void populater(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        EntityManager em = emf.createEntityManager();

        Player player = new Player("Hans", 2675489, "hansensmail@gamil.com", "aktiv");
        Player player1 = new Player("Maria", 37298202, "ms2001@live.dk", "inaktiv");
        Team team = new Team("Hygge fodbold", "Ørested boldklub");
        Team team1 = new Team("Fjeldsteds pensionist bold", "Fjeldsted");
        Location location = new Location("Ørested boldklub", "Idrætsalle 1", "København S", "Ingen");
        Match match = new Match("Anna", "Fodbold", "indoors");



        try{
            em.getTransaction().begin();

            team.addPlayer(player);
            team1.addPlayer(player1);
            match.addTeam(team);
            match.addTeam(team1);
            location.addMatch(match);

            em.persist(player);
            em.persist(player1);
            em.persist(team);
            em.persist(team1);
            em.persist(match);
            em.persist(location);

           /*
            em.merge(player);
            em.merge(player1);
            em.merge(team);
            em.merge(team1);
            em.merge(match);
            em.merge(location);*/

            em.getTransaction().commit();
        }finally {
            em.close();
        }

    }

    public static void main(String[] args) {
        populater();
    }
}
