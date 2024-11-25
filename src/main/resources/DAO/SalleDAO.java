package DAO;

import entities.Salle;
import helper.SQLite;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalleDAO {

    private final SQLite sqlite;

    public SalleDAO() throws SQLException {
        this.sqlite = SQLite.getInstance();
    }

    // Ajouter une nouvelle salle
    public void ajouterSalle(Salle salle) throws SQLException {
        String query = "INSERT INTO Salle (nom, capacite, equipements, statut) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = sqlite.getConnection().prepareStatement(query)) {
            stmt.setString(1, salle.getNom());
            stmt.setInt(2, salle.getCapacite());
            stmt.setString(3, String.join(",", salle.getEquipements()));
            stmt.setString(4, salle.getStatut());
            stmt.executeUpdate();
        }
    }

    // Récupérer une salle par son ID
    public Salle getSalleById(Long id) throws SQLException {
        String query = "SELECT * FROM Salle WHERE id = ?";
        try (PreparedStatement stmt = sqlite.getConnection().prepareStatement(query)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Salle(
                    rs.getLong("id"),
                    rs.getString("nom"),
                    rs.getInt("capacite"),
                    List.of(rs.getString("equipements").split(",")),
                    rs.getString("statut")
                );
            }
        }
        return null;
    }

    // Mise à jour des informations d'une salle
    public void updateSalle(Salle salle) throws SQLException {
        String query = "UPDATE Salle SET nom = ?, capacite = ?, equipements = ?, statut = ? WHERE id = ?";
        try (PreparedStatement stmt = sqlite.getConnection().prepareStatement(query)) {
            stmt.setString(1, salle.getNom());
            stmt.setInt(2, salle.getCapacite());
            stmt.setString(3, String.join(",", salle.getEquipements()));
            stmt.setString(4, salle.getStatut());
            stmt.setLong(5, salle.getId());
            stmt.executeUpdate();
        }
    }

    // Supprimer une salle par son ID
    public void deleteSalle(Long id) throws SQLException {
        String query = "DELETE FROM Salle WHERE id = ?";
        try (PreparedStatement stmt = sqlite.getConnection().prepareStatement(query)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    // Récupérer toutes les salles
    public List<Salle> getAllSalles() throws SQLException {
        List<Salle> salles = new ArrayList<>();
        String query = "SELECT * FROM Salle";
        try (Statement stmt = sqlite.getConnection().createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                salles.add(new Salle(
                    rs.getLong("id"),
                    rs.getString("nom"),
                    rs.getInt("capacite"),
                    List.of(rs.getString("equipements").split(",")),
                    rs.getString("statut")
                ));
            }
        }
        return salles;
    }
}
