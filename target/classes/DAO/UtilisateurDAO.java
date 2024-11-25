package DAO;
import entities.Utilisateur;
import helper.SQLite;

import java.sql.*;

import java.util.List

public class UtilisateurDAO {

    private final SQLite sqlite;

    public UtilisateurDAO() throws SQLException {
        this.sqlite = SQLite.getInstance();
    }

    // Ajouter un nouvel utilisateur
    public void ajouterUtilisateur(Utilisateur utilisateur) throws SQLException {
        String query = "INSERT INTO Utilisateur (nom, email, role) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = sqlite.getConnection().prepareStatement(query)) {
            stmt.setString(1, utilisateur.getNom());
            stmt.setString(2, utilisateur.getEmail());
            stmt.setString(3, utilisateur.getRole());
            stmt.executeUpdate();
        }
    }

    // Récupérer un utilisateur par son ID
    public Utilisateur getUtilisateurById(Long id) throws SQLException {
        String query = "SELECT * FROM Utilisateur WHERE id = ?";
        try (PreparedStatement stmt = sqlite.getConnection().prepareStatement(query)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Utilisateur(
                    rs.getLong("id"),
                    rs.getString("nom"),
                    rs.getString("email"),
                    rs.getString("role")
                );
            }
        }
        return null;
    }

    // Mise à jour des informations d'un utilisateur
    public void updateUtilisateur(Utilisateur utilisateur) throws SQLException {
        String query = "UPDATE Utilisateur SET nom = ?, email = ?, role = ? WHERE id = ?";
        try (PreparedStatement stmt = sqlite.getConnection().prepareStatement(query)) {
            stmt.setString(1, utilisateur.getNom());
            stmt.setString(2, utilisateur.getEmail());
            stmt.setString(3, utilisateur.getRole());
            stmt.setLong(4, utilisateur.getId());
            stmt.executeUpdate();
        }
    }

    // Supprimer un utilisateur par son ID
    public void deleteUtilisateur(Long id) throws SQLException {
        String query = "DELETE FROM Utilisateur WHERE id = ?";
        try (PreparedStatement stmt = sqlite.getConnection().prepareStatement(query)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    // Récupérer tous les utilisateurs
    public List<Utilisateur> getAllUtilisateurs() throws SQLException {
        List<Utilisateur
