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

    // D�claration de la map pour stocker les donn�es de la ligue
    private static Map<Integer, League> LEAGUE_DATA = new HashMap<>();

    // M�thode pour g�n�rer un nouvel identifiant pour une ligue
    private int getNewId() {
        int newId = 0;
        // Parcours de la map pour trouver l'identifiant le plus �lev�
        for (int id : LEAGUE_DATA.keySet()) {
            if (newId < id)
                newId = id;
        }
        // Incr�mente l'identifiant pour obtenir un nouvel identifiant unique
        return ++newId;
    }

    // M�thode pour ajouter une nouvelle ligue
    @WebMethod
    public League addLeague(@WebParam(name = "league") League league) {
        // G�n�re un nouvel identifiant pour la ligue
        int id = getNewId();
        // V�rifie si la ligue existe d�j�
        if (LEAGUE_DATA.get(league.getId()) != null) {
            return null;
        }
        // Affecte le nouvel identifiant � la ligue
        league.setId(id);
        // Ajoute la ligue � la map
        LEAGUE_DATA.put(id, league);
        return league;
    }

    // M�thode pour supprimer une ligue existante
    @WebMethod
    public boolean deleteLeague(@WebParam(name = "id") int id) {
        // V�rifie si la ligue existe
        if (LEAGUE_DATA.get(id) == null) {
            return false;
        }
        // Supprime la ligue de la map
        LEAGUE_DATA.remove(id);
        return true;
    }

    // M�thode pour r�cup�rer les d�tails d'une ligue en fonction de son identifiant
    @WebMethod
    public League getLeague(@WebParam(name = "id") int id) {
        return LEAGUE_DATA.get(id);
    }

    // M�thode pour r�cup�rer la liste des �quipes dans une ligue sp�cifi�e
    @WebMethod
    public List<Team> getTeamsInLeague(@WebParam(name = "leagueId") int leagueId) {
        League league = LEAGUE_DATA.get(leagueId);
        // V�rifie si la ligue existe
        if (league == null) {
            return null;
        }
        // Retourne la liste des �quipes de la ligue
        return league.getTeams();
    }

    // M�thode pour ajouter une �quipe � une ligue sp�cifi�e
    @WebMethod
    public boolean addTeamToLeague(@WebParam(name = "leagueId") int leagueId, @WebParam(name = "team") Team team) {
        League league = LEAGUE_DATA.get(leagueId);
        // V�rifie si la ligue existe
        if (league == null) {
            return false;
        }
        // Ajoute l'�quipe � la ligue
        league.addTeam(team);
        return true;
    }

    // M�thode pour mettre � jour le nom d'une ligue
    @WebMethod
    public boolean updateLeagueName(@WebParam(name = "leagueId") int leagueId, @WebParam(name = "newName") String newName) {
        League league = LEAGUE_DATA.get(leagueId);
        // V�rifie si la ligue existe
        if (league == null) {
            return false; // La ligue n'existe pas
        }
        // Met � jour le nom de la ligue
        league.setName(newName);
        return true; // Mise � jour r�ussie
    }

    // M�thode pour mettre � jour le sport d'une ligue
    @WebMethod
    public boolean updateLeagueSport(@WebParam(name = "leagueId") int leagueId, @WebParam(name = "newSport") String newSport) {
        League league = LEAGUE_DATA.get(leagueId);
        // V�rifie si la ligue existe
        if (league == null) {
            return false; // La ligue n'existe pas
        }
        // Met � jour le sport de la ligue
        league.setSport(newSport);
        return true; // Mise � jour r�ussie
    }
}
