package SportsPredictions.management.web.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import SportsPredictions.management.web.data.League;
import SportsPredictions.management.web.data.Team;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebService
public class LeagueServiceSOAP {

    // Déclaration de la map pour stocker les données de la ligue
    private static Map<Integer, League> LEAGUE_DATA = new HashMap<>();

    // Méthode pour générer un nouvel identifiant pour une ligue
    private int getNewId() {
        int newId = 0;
        // Parcours de la map pour trouver l'identifiant le plus élevé
        for (int id : LEAGUE_DATA.keySet()) {
            if (newId < id)
                newId = id;
        }
        // Incrémente l'identifiant pour obtenir un nouvel identifiant unique
        return ++newId;
    }

    // Méthode pour ajouter une nouvelle ligue
    @WebMethod
    public League addLeague(@WebParam(name = "league") League league) {
        // Génère un nouvel identifiant pour la ligue
        int id = getNewId();
        // Vérifie si la ligue existe déjà
        if (LEAGUE_DATA.get(league.getId()) != null) {
            return null;
        }
        // Affecte le nouvel identifiant à la ligue
        league.setId(id);
        // Ajoute la ligue à la map
        LEAGUE_DATA.put(id, league);
        return league;
    }

    // Méthode pour supprimer une ligue existante
    @WebMethod
    public boolean deleteLeague(@WebParam(name = "id") int id) {
        // Vérifie si la ligue existe
        if (LEAGUE_DATA.get(id) == null) {
            return false;
        }
        // Supprime la ligue de la map
        LEAGUE_DATA.remove(id);
        return true;
    }

    // Méthode pour récupérer les détails d'une ligue en fonction de son identifiant
    @WebMethod
    public League getLeague(@WebParam(name = "id") int id) {
        return LEAGUE_DATA.get(id);
    }

    // Méthode pour récupérer la liste des équipes dans une ligue spécifiée
    @WebMethod
    public List<Team> getTeamsInLeague(@WebParam(name = "leagueId") int leagueId) {
        League league = LEAGUE_DATA.get(leagueId);
        // Vérifie si la ligue existe
        if (league == null) {
            return null;
        }
        // Retourne la liste des équipes de la ligue
        return league.getTeams();
    }

    // Méthode pour ajouter une équipe à une ligue spécifiée
    @WebMethod
    public boolean addTeamToLeague(@WebParam(name = "leagueId") int leagueId, @WebParam(name = "team") Team team) {
        League league = LEAGUE_DATA.get(leagueId);
        // Vérifie si la ligue existe
        if (league == null) {
            return false;
        }
        // Ajoute l'équipe à la ligue
        league.addTeam(team);
        return true;
    }

    // Méthode pour mettre à jour le nom d'une ligue
    @WebMethod
    public boolean updateLeagueName(@WebParam(name = "leagueId") int leagueId, @WebParam(name = "newName") String newName) {
        League league = LEAGUE_DATA.get(leagueId);
        // Vérifie si la ligue existe
        if (league == null) {
            return false; // La ligue n'existe pas
        }
        // Met à jour le nom de la ligue
        league.setName(newName);
        return true; // Mise à jour réussie
    }

    // Méthode pour mettre à jour le sport d'une ligue
    @WebMethod
    public boolean updateLeagueSport(@WebParam(name = "leagueId") int leagueId, @WebParam(name = "newSport") String newSport) {
        League league = LEAGUE_DATA.get(leagueId);
        // Vérifie si la ligue existe
        if (league == null) {
            return false; // La ligue n'existe pas
        }
        // Met à jour le sport de la ligue
        league.setSport(newSport);
        return true; // Mise à jour réussie
    }
}
