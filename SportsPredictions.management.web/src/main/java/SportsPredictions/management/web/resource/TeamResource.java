package SportsPredictions.management.web.resource;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import SportsPredictions.management.web.data.Team;
import SportsPredictions.management.web.service.TeamService;
import java.net.URI;

@Path("/teams")
public class TeamResource {
    // Instance du service TeamService pour effectuer les opérations sur les équipes
    private TeamService service = new TeamService();

    // Injection de l'URI de la requête
    @Context
    private UriInfo uriInfo;

    // Méthode POST pour ajouter une nouvelle équipe
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response addTeam(Team team) {
        Team addedTeam = service.addTeam(team); // Ajouter l'équipe en utilisant le service
        if (addedTeam == null) { // Vérifier si l'ajout a échoué
            return Response.status(Response.Status.BAD_REQUEST).build(); // Retourner une réponse d'erreur
        }
        // Construire l'URI de la nouvelle équipe ajoutée
        URI uri = uriInfo.getRequestUri();
        String newUri = uri.getPath() + "/" + addedTeam.getId();
        // Retourner une réponse de succès avec l'équipe ajoutée et l'URI de la ressource nouvellement créée
        return Response.status(Response.Status.CREATED)
                       .entity(addedTeam)
                       .contentLocation(uri.resolve(newUri))
                       .build();
    }

    // Méthode DELETE pour supprimer une équipe existante
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Response deleteTeam(@PathParam("id") int id) {
        if (!service.deleteTeam(id)) { // Vérifier si la suppression a échoué
            return Response.status(Response.Status.NOT_FOUND).build(); // Retourner une réponse d'erreur si l'équipe n'est pas trouvée
        }
        // Retourner une réponse de succès si l'équipe est supprimée avec succès
        return Response.status(Response.Status.OK).build();
    }

    // Méthode GET pour obtenir les détails d'une équipe par son identifiant
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Response getTeam(@PathParam("id") int id) {
        Team team = service.getTeam(id); // Obtenir l'équipe en utilisant le service
        if (team == null) { // Vérifier si l'équipe n'est pas trouvée
            return Response.status(Response.Status.NOT_FOUND).build(); // Retourner une réponse d'erreur si l'équipe n'est pas trouvée
        }
        // Ajouter un lien vers cette ressource dans la réponse
        Link link = Link.fromUri(uriInfo.getRequestUri())
                        .rel("self")
                        .type("application/xml")
                        .build();
        // Retourner une réponse de succès avec les détails de l'équipe et le lien vers elle-même
        return Response.status(Response.Status.OK)
                       .entity(team)
                       .links(link)
                       .build();
    }
}
