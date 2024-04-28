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
    // URL du service SOAP pour les �quipes
    private static String teamSoapServiceUrl = "http://localhost:8080/SportsPredictions/TeamServiceSOAP";

	    // M�thode pour ajouter une ligue
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
	    
	    // M�thode pour cr�er une �quipe
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
	    
	    
	    

	    // M�thode pour ajouter une �quipe � une ligue

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


	    // M�thode pour r�cup�rer les �quipes dans une ligue sp�cifique
	    public  String getTeamsInLeague(Integer leagueId) {
	        System.out.println("Getting teams in league with ID " + leagueId + "...");
	        String teamWebServiceUrl = leagueWebServiceUrl + "/" + leagueId + "/teams";
	        return teamWebServiceUrl;
	}
	    // M�thode pour ajouter une ligue via le service SOAP
	    public String addLeagueSOAP(League league) {
	        System.out.print("Adding league " + league.getName() + " via SOAP... ");
	        // Cr�er un client WebClient pour appeler le service SOAP des ligues
	        WebClient client = WebClient.create(leagueSoapServiceUrl).type(MediaType.APPLICATION_XML);
	        // Appeler la m�thode addLeague du service SOAP avec la ligue en param�tre
	        Response response = client.post(league);
	        // V�rifier la r�ponse
	        if (response.getStatus() == 200) {
	            // Si la cr�ation a r�ussi, r�cup�rer l'URI de la nouvelle ligue
	            String uri = response.getHeaderString("Content-Location");
	            System.out.println("League added successfully.");
	            return uri;
	        } else {
	            // Si la cr�ation a �chou�, afficher un message d'erreur
	            System.out.println("Failed to add league.");
	            return null;
	        }
	    }

	    // M�thode pour ajouter une �quipe via le service SOAP
	    public String addTeamSOAP(Team team) {
	        System.out.print("Adding team " + team.getName() + " via SOAP... ");
	        // Cr�er un client WebClient pour appeler le service SOAP des �quipes
	        WebClient client = WebClient.create(teamSoapServiceUrl).type(MediaType.APPLICATION_XML);
	        // Appeler la m�thode addTeam du service SOAP avec l'�quipe en param�tre
	        Response response = client.post(team);
	        // V�rifier la r�ponse
	        if (response.getStatus() == 200) {
	            // Si l'ajout a r�ussi, r�cup�rer l'URI de la nouvelle �quipe
	            String uri = response.getHeaderString("Content-Location");
	            System.out.println("Team added successfully.");
	            return uri;
	        } else {
	            // Si l'ajout a �chou�, afficher un message d'erreur
	            System.out.println("Failed to add team.");
	            return null;
	        }
	    }

	    // M�thode pour ajouter une �quipe � une ligue via le service SOAP
	    public String addTeamToLeagueSOAP(Integer leagueId, String name, String level) {
	        System.out.print("Adding team " + name + " to league with ID " + leagueId + " via SOAP... ");
	        // Cr�er un client WebClient pour appeler le service SOAP des ligues avec l'identifiant de la ligue
	        WebClient client = WebClient.create(leagueSoapServiceUrl + "/" + leagueId + "/teams").type(MediaType.APPLICATION_XML);
	        // Cr�er un objet Team avec les informations sp�cifi�es
	        Team team = new Team(name, level);
	        // Appeler la m�thode addTeamToLeague du service SOAP avec l'�quipe en param�tre
	        Response response = client.post(team);
	        // V�rifier la r�ponse
	        if (response.getStatus() == 200) {
	            // Si l'ajout a r�ussi, r�cup�rer l'URI de la nouvelle �quipe ajout�e � la ligue
	            String uri = response.getHeaderString("Content-Location");
	            System.out.println("Team added to league successfully.");
	            return uri;
	        } else {
	            // Si l'ajout a �chou�, afficher un message d'erreur
	            System.out.println("Failed to add team to league.");
	            return null;
	        }
	    }

	    // M�thode pour r�cup�rer les �quipes dans une ligue sp�cifique via le service SOAP
	    public String getTeamsInLeagueSOAP(Integer leagueId) {
	        System.out.println("Getting teams in league with ID " + leagueId + " via SOAP...");
	        // Construire l'URL du service SOAP pour r�cup�rer les �quipes dans la ligue sp�cifi�e
	        String teamWebServiceUrl = leagueSoapServiceUrl + "/" + leagueId + "/teams";
	        return teamWebServiceUrl;
	    }
}


