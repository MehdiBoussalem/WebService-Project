package SportsPredictions.management.client;
// 
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import org.apache.cxf.jaxrs.client.*;
import SportsPredictions.management.web.data.League;
import SportsPredictions.management.web.data.Team;

public class Test {
    private static String leagueWebServiceUrl = "http://localhost:8080/SportsPredictions.management.web/api/leagues";

    public static void main(String[] args) {
        // Création de deux ligues
        League premierLeague = new League("Premier League", "Football", "England", "Division 1");
        Integer premierLeagueId = addLeague(premierLeague);

        League nba = new League("NBA", "Basketball", "USA", "Division 1");
        Integer nbaId = addLeague(nba);
        
        if (premierLeagueId != null && nbaId != null) {
            // Ajout d'une équipe à la ligue Premier League
            Integer teamAId1 = addTeamToLeague(premierLeagueId, "Manchester city", "elite");
            if (teamAId1 != null) {
                System.out.println("Team A added to Premier League.");
            } else {
                System.out.println("Failed to add Team A to Premier League.");
            }

            // Récupération des équipes dans la ligue Premier League
            getTeamsInLeague(premierLeagueId);
        } else {
            System.out.println("Failed to add leagues.");
        }
    }

    // Méthode pour ajouter une ligue
    private static Integer addLeague(League league) {
        System.out.print("Adding league " + league.getName() + "... ");
        WebClient c = WebClient.create(leagueWebServiceUrl).type(MediaType.APPLICATION_XML);
        Response r = c.type(MediaType.APPLICATION_XML).post(league);
        if (r.getStatus() == 201) {
            String uri = r.getHeaderString("Content-Location");
            System.out.println("OK.");
            return Integer.parseInt(uri.substring(uri.lastIndexOf('/') + 1));
        } else {
            System.out.println("Oops!");
            return null;
        }
    }

    // Méthode pour ajouter une équipe à une ligue

    private static Integer addTeamToLeague(Integer leagueId, String name, String level) {
        System.out.print("Adding team " + name + " to league with ID " + leagueId + "... ");
        WebClient c = WebClient.create(leagueWebServiceUrl + "/" + leagueId + "/teams").type(MediaType.APPLICATION_XML);
        Team team = new Team(name, level);
        Response r = c.type(MediaType.APPLICATION_XML).post(team);
        if (r.getStatus() == 201) {
            String uri = r.getHeaderString("Content-Location");
            System.out.println("OK.");
            return Integer.parseInt(uri.substring(uri.lastIndexOf('/') + 1));
        } else {
            System.out.println("Oops!");
            return null;
        }
    }


    // Méthode pour récupérer les équipes dans une ligue spécifique
    private static void getTeamsInLeague(Integer leagueId) {
        System.out.println("Getting teams in league with ID " + leagueId + "...");
        String teamWebServiceUrl = leagueWebServiceUrl + "/" + leagueId + "/teams";
        WebClient c = WebClient.create(teamWebServiceUrl);
        Response r = c.accept(MediaType.APPLICATION_XML).get();
        if (r.getStatus() == 200) {
            Team[] teams = r.readEntity(Team[].class);
            if (teams != null) {
                if (teams.length > 0) {
                    System.out.println("Teams in the league:");
                    for (Team team : teams) {
                        System.out.println(team.toString());
                    }
                } else {
                    System.out.println("No teams found in the league.");
                }
            } else {
                System.out.println("Failed to read teams from the response.");
            }
        } else {
            System.out.println("Failed to get teams in the league. Status: " + r.getStatus());
        }
    }
}
