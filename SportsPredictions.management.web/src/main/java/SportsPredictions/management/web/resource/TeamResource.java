package SportsPredictions.management.web.resource;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import SportsPredictions.management.web.data.Team;
import SportsPredictions.management.web.service.TeamService;
import java.net.URI;

@Path("/teams")
public class TeamResource {
    // Instance du service TeamService pour effectuer les op�rations sur les �quipes
    private TeamService service = new TeamService();

    // Injection de l'URI de la requ�te
    @Context
    private UriInfo uriInfo;

    // M�thode POST pour ajouter une nouvelle �quipe
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response addTeam(Team team) {
        Team addedTeam = service.addTeam(team); // Ajouter l'�quipe en utilisant le service
        if (addedTeam == null) { // V�rifier si l'ajout a �chou�
            return Response.status(Response.Status.BAD_REQUEST).build(); // Retourner une r�ponse d'erreur
        }
        // Construire l'URI de la nouvelle �quipe ajout�e
        URI uri = uriInfo.getRequestUri();
        String newUri = uri.getPath() + "/" + addedTeam.getId();
        // Retourner une r�ponse de succ�s avec l'�quipe ajout�e et l'URI de la ressource nouvellement cr��e
        return Response.status(Response.Status.CREATED)
                       .entity(addedTeam)
                       .contentLocation(uri.resolve(newUri))
                       .build();
    }

    // M�thode DELETE pour supprimer une �quipe existante
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Response deleteTeam(@PathParam("id") int id) {
        if (!service.deleteTeam(id)) { // V�rifier si la suppression a �chou�
            return Response.status(Response.Status.NOT_FOUND).build(); // Retourner une r�ponse d'erreur si l'�quipe n'est pas trouv�e
        }
        // Retourner une r�ponse de succ�s si l'�quipe est supprim�e avec succ�s
        return Response.status(Response.Status.OK).build();
    }

    // M�thode GET pour obtenir les d�tails d'une �quipe par son identifiant
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Response getTeam(@PathParam("id") int id) {
        Team team = service.getTeam(id); // Obtenir l'�quipe en utilisant le service
        if (team == null) { // V�rifier si l'�quipe n'est pas trouv�e
            return Response.status(Response.Status.NOT_FOUND).build(); // Retourner une r�ponse d'erreur si l'�quipe n'est pas trouv�e
        }
        // Ajouter un lien vers cette ressource dans la r�ponse
        Link link = Link.fromUri(uriInfo.getRequestUri())
                        .rel("self")
                        .type("application/xml")
                        .build();
        // Retourner une r�ponse de succ�s avec les d�tails de l'�quipe et le lien vers elle-m�me
        return Response.status(Response.Status.OK)
                       .entity(team)
                       .links(link)
                       .build();
    }
}
