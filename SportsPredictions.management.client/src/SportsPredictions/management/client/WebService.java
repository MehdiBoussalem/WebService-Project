package SportsPredictions.management.client;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;

import SportsPredictions.management.web.data.League;
import SportsPredictions.management.web.data.Team;

public class WebService {
    private static String leagueWebServiceUrl = "http://localhost:8082/SportsPredictions.management.web/api/leagues";
    private static String teamWebServiceUrl = "http://localhost:8082/SportsPredictions.management.web/api/teams";

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
}


