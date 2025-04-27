package Dao;

import Modele.Attraction;
import Modele.Utilisateur;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtilisateurDAO extends DAO<Utilisateur> {

    @Override
    public Utilisateur find(long id) {
        try (PreparedStatement stmt = connect.prepareStatement("SELECT * FROM utilisateurs WHERE id = ?")) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Utilisateur(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("mot_de_passe"),
                        rs.getString("date_naissance"),
                        rs.getInt("role")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Utilisateur> getAllUtilisateurs() {
        List<Utilisateur> attractions = new ArrayList<>();
        try (Statement stmt = connect.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM utilisateurs")) {

            while (rs.next()) {
                Utilisateur a = new Utilisateur(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("mot_de_passe"),
                        rs.getString("date_naissance"),
                        rs.getInt("role")
                );
                attractions.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attractions;
    }
    @Override
    public Utilisateur create(Utilisateur u) {
        String sql = "INSERT INTO utilisateurs (nom, prenom, email, mot_de_passe, date_naissance, role) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, u.getNom());
            stmt.setString(2, u.getPrenom());
            stmt.setString(3, u.getEmail());
            stmt.setString(4, u.getMot_de_passe());
            stmt.setString(5, u.getDate_naissance());
            stmt.setInt(6, u.getRole());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    u.setId(rs.getInt(1)); // récupère l'ID généré
                }
                return u;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Utilisateur update(Utilisateur u) {
        String sql = "UPDATE utilisateurs SET nom=?, prenom=?, email=?, mot_de_passe=?, date_naissance=?, role=? WHERE id=?";
        try (PreparedStatement stmt = connect.prepareStatement(sql)) {
            stmt.setString(1, u.getNom());
            stmt.setString(2, u.getPrenom());
            stmt.setString(3, u.getEmail());
            stmt.setString(4, u.getMot_de_passe());
            stmt.setString(5, u.getDate_naissance());
            stmt.setInt(6, u.getRole());
            stmt.setInt(7, u.getId());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                return u;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(Utilisateur u) {
        try (PreparedStatement stmt = connect.prepareStatement("DELETE FROM utilisateurs WHERE id = ?")) {
            stmt.setInt(1, u.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
