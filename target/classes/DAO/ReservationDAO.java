package DAO;

import entities.Reservation;
import helper.SQLite;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO {

    private final SQLite sqlite;

    public ReservationDAO() throws SQLException {
        this.sqlite = SQLite.getInstance();
    }

    // Ajouter une nouvelle réservation
    public void ajouterReservation(Reservation reservation) throws SQLException {
        String query = "INSERT INTO Reservation (salleId, utilisateurId, sujet, dateDebut, dateFin, statut) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = sqlite.getConnection().prepareStatement(query)) {
            stmt.setLong(1, reservation.getSalleId());
            stmt.setLong(2, reservation.getUtilisateurId());
            stmt.setString(3, reservation.getSujet());
            stmt.setString(4, reservation.getDateDebut().toString());
            stmt.setString(5, reservation.getDateFin().toString());
            stmt.setString(6, reservation.getStatut());
            stmt.executeUpdate();
        }
    }

    // Récupérer une réservation par son ID
    public Reservation getReservationById(Long id) throws SQLException {
        String query = "SELECT * FROM Reservation WHERE id = ?";
        try (PreparedStatement stmt = sqlite.getConnection().prepareStatement(query)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Reservation(
                    rs.getLong("id"),
                    rs.getLong("salleId"),
                    rs.getLong("utilisateurId"),
                    rs.getString("sujet"),
                    LocalDateTime.parse(rs.getString("dateDebut")),
                    LocalDateTime.parse(rs.getString("dateFin")),
                    rs.getString("statut")
                );
            }
        }
        return null;
    }

    // Mise à jour des informations d'une réservation
    public void updateReservation(Reservation reservation) throws SQLException {
        String query = "UPDATE Reservation SET salleId = ?, utilisateurId = ?, sujet = ?, dateDebut = ?, dateFin = ?, statut = ? WHERE id = ?";
        try (PreparedStatement stmt = sqlite.getConnection().prepareStatement(query)) {
            stmt.setLong(1, reservation.getSalleId());
            stmt.setLong(2, reservation.getUtilisateurId());
            stmt.setString(3, reservation.getSujet());
            stmt.setString(4, reservation.getDateDebut().toString());
            stmt.setString(5, reservation.getDateFin().toString());
            stmt.setString(6, reservation.getStatut());
            stmt.setLong(7, reservation.getId());
            stmt.executeUpdate();
        }
    }

    // Supprimer une réservation par son ID
    public void deleteReservation(Long id) throws SQLException {
        String query = "DELETE FROM Reservation WHERE id = ?";
        try (PreparedStatement stmt = sqlite.getConnection().prepareStatement(query)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    // Récupérer toutes les réservations
    public List<Reservation> getAllReservations() throws SQLException {
        List<Reservation> reservations = new ArrayList<>();
        String query = "SELECT * FROM Reservation";
        try (Statement stmt = sqlite.getConnection().createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                reservations.add(new Reservation(
                    rs.getLong("id"),
                    rs.getLong("salleId"),
                    rs.getLong("utilisateurId"),
                    rs.getString("sujet"),
                    LocalDateTime.parse(rs.getString("dateDebut")),
                    LocalDateTime.parse(rs.getString("dateFin")),
                    rs.getString("statut")
                ));
            }
        }
        return reservations;
    }
}
