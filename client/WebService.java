package SportsPredictions.management.client;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;

import SportsPredictions.management.web.data.League;
import SportsPredictions.management.web.data.Team;

public class WebService {
    private static String leagueWebServiceUrl = "http://localhost:8082/SportsPredictions.management.web/api/leagues";
    private static String teamWebServiceUrl = "http://localhost:8082/SportsPredictions.management.web/api/teams";
    // URL du service SOAP pour les ligues
    private static String leagueSoapServiceUrl = "http://localhost:8080/SportsPredictions/LeagueServiceSOAP";
    // URL du service SOAP pour les équipes
    private static String teamSoapServiceUrl = "http://localhost:8080/SportsPredictions/TeamServiceSOAP";

	    // Méthode pour ajouter une ligue
	    public  String addLeague(League league) {
	        System.out.print("Adding league " + league.getName() + "... ");
	        WebClient c = WebClient.create(leagueWebServiceUrl).type(MediaType.APPLICATION_XML);
	        Response r = c.type(MediaType.APPLICATION_XML).post(league);
	        if (r.getStatus() == 201) {
	            String uri = r.getHeaderString("Content-Location");

	            return uri;
	        } else {
	            return null;
	        }
	    }
	    
	    // Méthode pour créer une équipe
	    public String createTeam(Team team) {
	        System.out.print("Adding team " + team.getName() + "... ");
	        
	        WebClient c = WebClient.create(teamWebServiceUrl).type(MediaType.APPLICATION_XML);
	        Response r = c.type(MediaType.APPLICATION_XML).post(team);
	        
            System.out.println(r.getStatus());

	        
	        if (r.getStatus() == 201) {
	            String uri = r.getHeaderString("Content-Location");
	            System.out.println(uri);
	            return uri;
	        } else {
	            return null;
	        }
	    }
	    
	    
	    

	    // Méthode pour ajouter une équipe à une ligue

	    public String addTeamToLeague(Integer leagueId, String name, String level) {
	        System.out.print("Adding team " + name + " to league with ID " + leagueId + "... ");
	        WebClient c = WebClient.create(leagueWebServiceUrl + "/" + leagueId + "/teams").type(MediaType.APPLICATION_XML);
	        Team team = new Team(name, level);
	        Response r = c.type(MediaType.APPLICATION_XML).post(team);
	        if (r.getStatus() == 201) {
	            String uri = r.getHeaderString("Content-Location");
	            return uri;
	        } else {
	            return null;
	        }
	    }


	    // Méthode pour récupérer les équipes dans une ligue spécifique
	    public  String getTeamsInLeague(Integer leagueId) {
	        System.out.println("Getting teams in league with ID " + leagueId + "...");
	        String teamWebServiceUrl = leagueWebServiceUrl + "/" + leagueId + "/teams";
	        return teamWebServiceUrl;
	}
	    // Méthode pour ajouter une ligue via le service SOAP
	    public String addLeagueSOAP(League league) {
	        System.out.print("Adding league " + league.getName() + " via SOAP... ");
	        // Créer un client WebClient pour appeler le service SOAP des ligues
	        WebClient client = WebClient.create(leagueSoapServiceUrl).type(MediaType.APPLICATION_XML);
	        // Appeler la méthode addLeague du service SOAP avec la ligue en paramètre
	        Response response = client.post(league);
	        // Vérifier la réponse
	        if (response.getStatus() == 200) {
	            // Si la création a réussi, récupérer l'URI de la nouvelle ligue
	            String uri = response.getHeaderString("Content-Location");
	            System.out.println("League added successfully.");
	            return uri;
	        } else {
	            // Si la création a échoué, afficher un message d'erreur
	            System.out.println("Failed to add league.");
	            return null;
	        }
	    }

	    // Méthode pour ajouter une équipe via le service SOAP
	    public String addTeamSOAP(Team team) {
	        System.out.print("Adding team " + team.getName() + " via SOAP... ");
	        // Créer un client WebClient pour appeler le service SOAP des équipes
	        WebClient client = WebClient.create(teamSoapServiceUrl).type(MediaType.APPLICATION_XML);
	        // Appeler la méthode addTeam du service SOAP avec l'équipe en paramètre
	        Response response = client.post(team);
	        // Vérifier la réponse
	        if (response.getStatus() == 200) {
	            // Si l'ajout a réussi, récupérer l'URI de la nouvelle équipe
	            String uri = response.getHeaderString("Content-Location");
	            System.out.println("Team added successfully.");
	            return uri;
	        } else {
	            // Si l'ajout a échoué, afficher un message d'erreur
	            System.out.println("Failed to add team.");
	            return null;
	        }
	    }

	    // Méthode pour ajouter une équipe à une ligue via le service SOAP
	    public String addTeamToLeagueSOAP(Integer leagueId, String name, String level) {
	        System.out.print("Adding team " + name + " to league with ID " + leagueId + " via SOAP... ");
	        // Créer un client WebClient pour appeler le service SOAP des ligues avec l'identifiant de la ligue
	        WebClient client = WebClient.create(leagueSoapServiceUrl + "/" + leagueId + "/teams").type(MediaType.APPLICATION_XML);
	        // Créer un objet Team avec les informations spécifiées
	        Team team = new Team(name, level);
	        // Appeler la méthode addTeamToLeague du service SOAP avec l'équipe en paramètre
	        Response response = client.post(team);
	        // Vérifier la réponse
	        if (response.getStatus() == 200) {
	            // Si l'ajout a réussi, récupérer l'URI de la nouvelle équipe ajoutée à la ligue
	            String uri = response.getHeaderString("Content-Location");
	            System.out.println("Team added to league successfully.");
	            return uri;
	        } else {
	            // Si l'ajout a échoué, afficher un message d'erreur
	            System.out.println("Failed to add team to league.");
	            return null;
	        }
	    }

	    // Méthode pour récupérer les équipes dans une ligue spécifique via le service SOAP
	    public String getTeamsInLeagueSOAP(Integer leagueId) {
	        System.out.println("Getting teams in league with ID " + leagueId + " via SOAP...");
	        // Construire l'URL du service SOAP pour récupérer les équipes dans la ligue spécifiée
	        String teamWebServiceUrl = leagueSoapServiceUrl + "/" + leagueId + "/teams";
	        return teamWebServiceUrl;
	    }
}


