package entree;

import DAO.UtilisateurDAO;
import entities.Utilisateur;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;

/**
 * Ressource REST pour la gestion des utilisateurs.
 */
@Path("utilisateurs")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UtilisateurResource {

    private final UtilisateurDAO utilisateurDAO;

    public UtilisateurResource() throws SQLException {
        this.utilisateurDAO = new UtilisateurDAO();
    }

    // Obtenir la liste de tous les utilisateurs (admin uniquement)
    @GET
    @RolesAllowed({"admin"})
    public Response getAllUtilisateurs() {
        try {
            List<Utilisateur> utilisateurs = utilisateurDAO.getAllUtilisateurs();
            return Response.ok(utilisateurs).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur lors de la récupération des utilisateurs").build();
        }
    }

    // Ajouter un nouvel utilisateur
    @POST
    @RolesAllowed({"admin"})
    public Response addUtilisateur(Utilisateur utilisateur) {
        try {
            utilisateurDAO.ajouterUtilisateur(utilisateur);
            return Response.status(Response.Status.CREATED).entity("Utilisateur ajouté avec succès").build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur lors de l'ajout de l'utilisateur").build();
        }
    }

    // Mettre à jour un utilisateur existant
    @PUT
    @Path("{id}")
    @RolesAllowed({"admin"})
    public Response updateUtilisateur(@PathParam("id") Long id, Utilisateur utilisateur) {
        try {
            utilisateur.setId(id);
            utilisateurDAO.updateUtilisateur(utilisateur);
            return Response.ok("Utilisateur mis à jour avec succès").build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur lors de la mise à jour de l'utilisateur").build();
        }
    }

    // Supprimer un utilisateur
    @DELETE
    @Path("{id}")
    @RolesAllowed({"admin"})
    public Response deleteUtilisateur(@PathParam("id") Long id) {
        try {
            utilisateurDAO.deleteUtilisateur(id);
            return Response.ok("Utilisateur supprimé avec succès").build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur lors de la suppression de l'utilisateur").build();
        }
    }
}
