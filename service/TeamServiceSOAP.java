package SportsPredictions.management.web.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import SportsPredictions.management.web.data.Team;
import java.util.HashMap;
import java.util.Map;

@WebService
public class TeamServiceSOAP {

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
    @WebMethod
    public Team addTeam(@WebParam(name = "team") Team team) {
        int id = getNewId(); // Obtenir un nouvel identifiant pour l'équipe
        if (TEAM_DATA.get(team.getId()) != null) { // Vérifier si l'équipe existe déjà
            return null; // Si oui, retourner null pour indiquer l'échec de l'ajout
        }
        team.setId(id); // Définir l'identifiant de l'équipe
        TEAM_DATA.put(id, team); // Ajouter l'équipe à la carte avec son identifiant
        return team; // Retourner l'équipe ajoutée
    }
    
    // Méthode pour supprimer une équipe
    @WebMethod
    public boolean deleteTeam(@WebParam(name = "id") int id) {
        if (TEAM_DATA.get(id) == null) { // Vérifier si l'équipe existe
            return false; // Si non, retourner false pour indiquer l'échec de la suppression
        }
        TEAM_DATA.remove(id); // Supprimer l'équipe de la carte
        return true; // Retourner true pour indiquer le succès de la suppression
    }

    // Méthode pour obtenir une équipe par son identifiant
    @WebMethod
    public Team getTeam(@WebParam(name = "id") int id) {
        return TEAM_DATA.get(id); // Retourner l'équipe correspondante à l'identifiant donné
    }

    // Méthode pour mettre à jour les détails d'une équipe existante
    @WebMethod
    public boolean updateTeam(@WebParam(name = "id") int id, @WebParam(name = "updatedTeam") Team updatedTeam) {
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
