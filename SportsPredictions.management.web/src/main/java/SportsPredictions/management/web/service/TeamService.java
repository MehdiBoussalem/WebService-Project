package SportsPredictions.management.web.service;

import java.util.HashMap;
import java.util.Map;
import SportsPredictions.management.web.data.Team;

public class TeamService {
    // Une carte pour stocker les �quipes avec leur identifiant comme cl�
    private static Map<Integer, Team> TEAM_DATA = new HashMap<>();

    // M�thode priv�e pour obtenir un nouvel identifiant pour une nouvelle �quipe
    private int getNewId() {
        int newId = 0;
        for (int id : TEAM_DATA.keySet()) {
            if (newId < id)
                newId = id;
        }
        return ++newId;
    }

    // M�thode pour ajouter une nouvelle �quipe
    public Team addTeam(Team team) {
        int id = getNewId(); // Obtenir un nouvel identifiant pour l'�quipe
        if (TEAM_DATA.get(team.getId()) != null) { // V�rifier si l'�quipe existe d�j�
            return null; // Si oui, retourner null pour indiquer l'�chec de l'ajout
        }
        team.setId(id); // D�finir l'identifiant de l'�quipe
        TEAM_DATA.put(id, team); // Ajouter l'�quipe � la carte avec son identifiant
        return team; // Retourner l'�quipe ajout�e
    }

    // M�thode pour supprimer une �quipe
    public boolean deleteTeam(int id) {
        if (TEAM_DATA.get(id) == null) { // V�rifier si l'�quipe existe
            return false; // Si non, retourner false pour indiquer l'�chec de la suppression
        }
        TEAM_DATA.remove(id); // Supprimer l'�quipe de la carte
        return true; // Retourner true pour indiquer le succ�s de la suppression
    }

    // M�thode pour obtenir une �quipe par son identifiant
    public Team getTeam(int id) {
        return TEAM_DATA.get(id); // Retourner l'�quipe correspondante � l'identifiant donn�
    }

    // M�thode pour mettre � jour les d�tails d'une �quipe existante
    public boolean updateTeam(int id, Team updatedTeam) {
        Team existingTeam = TEAM_DATA.get(id);
        if (existingTeam == null) {
            return false; // L'�quipe n'existe pas, donc la mise � jour �choue
        }
        // Mettre � jour les d�tails de l'�quipe avec les valeurs de updatedTeam
        existingTeam.setName(updatedTeam.getName());
        existingTeam.setLevel(updatedTeam.getLevel());
        // Mise � jour �ventuelle d'autres attributs si n�cessaire
        return true; // Succ�s de la mise � jour
    }
}
