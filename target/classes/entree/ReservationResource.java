package entree;

import DAO.ReservationDAO;
import entities.Reservation;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;

/**
 * Ressource REST pour la gestion des réservations.
 */
@Path("reservations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReservationResource {

    private final ReservationDAO reservationDAO;

    public ReservationResource() throws SQLException {
        this.reservationDAO = new ReservationDAO();
    }

    // Obtenir la liste de toutes les réservations
    @GET
    public Response getAllReservations() {
        try {
            List<Reservation> reservations = reservationDAO.getAllReservations();
            return Response.ok(reservations).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur lors de la récupération des réservations").build();
        }
    }

    // Ajouter une nouvelle réservation (nécessite une authentification)
    @POST
    @RolesAllowed({"user", "admin"})
    public Response addReservation(Reservation reservation) {
        try {
            reservationDAO.ajouterReservation(reservation);
            return Response.status(Response.Status.CREATED).entity("Réservation ajoutée avec succès").build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur lors de l'ajout de la réservation").build();
        }
    }

    // Mettre à jour une réservation existante
    @PUT
    @Path("{id}")
    @RolesAllowed({"user", "admin"})
    public Response updateReservation(@PathParam("id") Long id, Reservation reservation) {
        try {
            reservation.setId(id);
            reservationDAO.updateReservation(reservation);
            return Response.ok("Réservation mise à jour avec succès").build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur lors de la mise à jour de la réservation").build();
        }
    }

    // Supprimer une réservation
    @DELETE
    @Path("{id}")
    @RolesAllowed({"admin"})
    public Response deleteReservation(@PathParam("id") Long id) {
        try {
            reservationDAO.deleteReservation(id);
            return Response.ok("Réservation supprimée avec succès").build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur lors de la suppression de la réservation").build();
        }
    }
}
