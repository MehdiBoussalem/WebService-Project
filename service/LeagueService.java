package SportsPredictions.management.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import SportsPredictions.management.web.data.League;
import SportsPredictions.management.web.data.Team;

public class LeagueService {
    private static Map<Integer, League> LEAGUE_DATA = new HashMap<>();

    private int getNewId() {
        int newId = 0;
        for (int id : LEAGUE_DATA.keySet()) {
            if (newId < id)
                newId = id;
        }
        return ++newId;
    }

    public League addLeague(League league) {
        int id = getNewId();
        if (LEAGUE_DATA.get(league.getId()) != null) {
            return null;
        }
        league.setId(id);
        LEAGUE_DATA.put(id, league);
        return league;
    }

    public boolean deleteLeague(int id) {
        if (LEAGUE_DATA.get(id) == null) {
            return false;
        }
        LEAGUE_DATA.remove(id);
        return true;
    }

    public League getLeague(int id) {
        return LEAGUE_DATA.get(id);
    }

    public List<Team> getTeamsInLeague(int leagueId) {
        League league = LEAGUE_DATA.get(leagueId);
        if (league == null) {
            return null;
        }
        return league.getTeams();
    }

    public boolean addTeamToLeague(int leagueId, Team team) {
        League league = LEAGUE_DATA.get(leagueId);
        if (league == null) {
            return false;
        }
        league.addTeam(team);
        return true;
    }

    // Méthode pour mettre à jour le nom de la ligue
    public boolean updateLeagueName(int leagueId, String newName) {
        League league = LEAGUE_DATA.get(leagueId);
        if (league == null) {
            return false; // La ligue n'existe pas
        }
        league.setName(newName);
        return true; // Mise à jour réussie
    }

    // Méthode pour mettre à jour le sport de la ligue
    public boolean updateLeagueSport(int leagueId, String newSport) {
        League league = LEAGUE_DATA.get(leagueId);
        if (league == null) {
            return false; // La ligue n'existe pas
        }
        league.setSport(newSport);
        return true; // Mise à jour réussie
    }

    // Ajoutez d'autres méthodes pour mettre à jour d'autres détails de la ligue si nécessaire
}
