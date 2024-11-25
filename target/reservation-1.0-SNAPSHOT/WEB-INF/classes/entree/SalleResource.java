package entree;

import DAO.SalleDAO;
import entities.Salle;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;

/**
 * Ressource REST pour la gestion des salles.
 */
@Path("salles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SalleResource {

    private final SalleDAO salleDAO;

    public SalleResource() throws SQLException {
        this.salleDAO = new SalleDAO();
    }

    // Obtenir la liste de toutes les salles
    @GET
    public Response getAllSalles() {
        try {
            List<Salle> salles = salleDAO.getAllSalles();
            return Response.ok(salles).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur lors de la récupération des salles").build();
        }
    }

    // Ajouter une nouvelle salle (nécessite une authentification)
    @POST
    @RolesAllowed({"admin"})
    public Response addSalle(Salle salle) {
        try {
            salleDAO.ajouterSalle(salle);
            return Response.status(Response.Status.CREATED).entity("Salle ajoutée avec succès").build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur lors de l'ajout de la salle").build();
        }
    }

    // Mettre à jour une salle existante
    @PUT
    @Path("{id}")
    @RolesAllowed({"admin"})
    public Response updateSalle(@PathParam("id") Long id, Salle salle) {
        try {
            salle.setId(id);
            salleDAO.updateSalle(salle);
            return Response.ok("Salle mise à jour avec succès").build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur lors de la mise à jour de la salle").build();
        }
    }

    // Supprimer une salle
    @DELETE
    @Path("{id}")
    @RolesAllowed({"admin"})
    public Response deleteSalle(@PathParam("id") Long id) {
        try {
            salleDAO.deleteSalle(id);
            return Response.ok("Salle supprimée avec succès").build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur lors de la suppression de la salle").build();
        }
    }
}
