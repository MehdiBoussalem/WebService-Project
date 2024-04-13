package SportsPredictions.management.web.resource;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import SportsPredictions.management.web.data.League;
import SportsPredictions.management.web.service.LeagueService;
import SportsPredictions.management.web.data.Team;
import SportsPredictions.management.web.service.TeamService;
import SportsPredictions.management.web.service.TeamsWrapper;

import java.net.URI;
import java.util.List;

@Path("/leagues")
public class LeagueResource {
    private LeagueService service = new LeagueService();
    private TeamService teamService = new TeamService();
    

    @Context
    private UriInfo uriInfo;

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response addLeague(League league) { 
        League addedLeague = service.addLeague(league);
        if (addedLeague == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        URI uri = uriInfo.getRequestUri();
        String newUri = uri.getPath() + "/" + addedLeague.getId();
        return Response.status(Response.Status.CREATED)
                       .entity(addedLeague)
                       .contentLocation(uri.resolve(newUri))
                       .build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Response deleteLeague(@PathParam("id") int id) {
        if (!service.deleteLeague(id)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Response getLeague(@PathParam("id") int id) {
        League league = service.getLeague(id);
        if (league == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        Link link = Link.fromUri(uriInfo.getRequestUri())
                        .rel("self")
                        .type("application/xml")
                        .build();
        return Response.status(Response.Status.OK)
                       .entity(league)
                       .links(link)
                       .build();
    }


 // M�thode POST pour ajouter une �quipe � une ligue sp�cifique
    @POST
    @Path("/{id}/teams")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response addTeamToLeague(@PathParam("id") int leagueId, Team team) {
        League league = service.getLeague(leagueId);
        if (league == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        if (team.getId()== null) { // alors la team n'a pas encore �t� cr�e 
        	
        	team = teamService.addTeam(team);
        }
        
        league.addTeam(team);
        // Renvoyer l'entit� (la ligue) dans la r�ponse
        URI uri = uriInfo.getRequestUri();
        String newUri = uri.getPath() + "/" + team.getId();
        System.out.println(Response.status(Response.Status.CREATED).entity(league).build());
        return Response.status(Response.Status.CREATED)
        		.entity(league)
        		.contentLocation(uri.resolve(newUri))
        		.build();
    }


    @DELETE
    @Path("/{leagueId}/teams/{teamId}")
    @Produces(MediaType.APPLICATION_XML)
    public Response removeTeamFromLeague(@PathParam("leagueId") int leagueId, @PathParam("teamId") int teamId) {
        League league = service.getLeague(leagueId);
        if (league == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        Team teamToRemove = null;
        for (Team team : league.getTeams()) {
            if (team.getId() == teamId) {
                teamToRemove = team;
                break;
            }
        }
        if (teamToRemove == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        league.removeTeam(teamToRemove);
        return Response.status(Response.Status.OK).build();
    }
    @GET
    @Path("/{id}/teams") // Endpoint pour r�cup�rer les �quipes dans une ligue sp�cifique
    @Produces(MediaType.APPLICATION_XML)
    public Response getTeamsInLeague(@PathParam("id") int leagueId) {
        List<Team> teams = service.getTeamsInLeague(leagueId);
        if (teams == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        
        // Cr�er un wrapper pour la liste d'�quipes pour JAXB
        TeamsWrapper teamsWrapper = new TeamsWrapper(teams);
        
        // Renvoyer la liste d'�quipes sous forme de XML
        return Response.status(Response.Status.OK)
                       .entity(teamsWrapper)
                       .build();
    }
}

