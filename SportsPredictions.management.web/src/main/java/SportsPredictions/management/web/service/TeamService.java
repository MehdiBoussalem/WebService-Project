package SportsPredictions.management.web.service;

import java.util.HashMap;
import java.util.Map;
import SportsPredictions.management.web.data.Team;

public class TeamService {
    // Une carte pour stocker les équipes avec leur identifiant comme clé
    private static Map<Integer, Team> TEAM_DATA = new HashMap<>();

    // Méthode privée pour obtenir un nouvel identifiant pour une nouvelle équipe
    private int getNewId() {
        int newId = 0;
        for (int id : TEAM_DATA.keySet()) {
            if (newId < id)
                newId = id;
        }
        return ++newId;
    }

    // Méthode pour ajouter une nouvelle équipe
    public Team addTeam(Team team) {
        int id = getNewId(); // Obtenir un nouvel identifiant pour l'équipe
        if (TEAM_DATA.get(team.getId()) != null) { // Vérifier si l'équipe existe déjà
            return null; // Si oui, retourner null pour indiquer l'échec de l'ajout
        }
        team.setId(id); // Définir l'identifiant de l'équipe
        TEAM_DATA.put(id, team); // Ajouter l'équipe à la carte avec son identifiant
        return team; // Retourner l'équipe ajoutée
    }

    // Méthode pour supprimer une équipe
    public boolean deleteTeam(int id) {
        if (TEAM_DATA.get(id) == null) { // Vérifier si l'équipe existe
            return false; // Si non, retourner false pour indiquer l'échec de la suppression
        }
        TEAM_DATA.remove(id); // Supprimer l'équipe de la carte
        return true; // Retourner true pour indiquer le succès de la suppression
    }

    // Méthode pour obtenir une équipe par son identifiant
    public Team getTeam(int id) {
        return TEAM_DATA.get(id); // Retourner l'équipe correspondante à l'identifiant donné
    }

    // Méthode pour mettre à jour les détails d'une équipe existante
    public boolean updateTeam(int id, Team updatedTeam) {
        Team existingTeam = TEAM_DATA.get(id);
        if (existingTeam == null) {
            return false; // L'équipe n'existe pas, donc la mise à jour échoue
        }
        // Mettre à jour les détails de l'équipe avec les valeurs de updatedTeam
        existingTeam.setName(updatedTeam.getName());
        existingTeam.setLevel(updatedTeam.getLevel());
        // Mise à jour éventuelle d'autres attributs si nécessaire
        return true; // Succès de la mise à jour
    }
}
