package Dao;

import Modele.Attraction;

import java.sql.*;
import java.util.*;

public class AttractionDAO extends DAO<Attraction> {

    @Override
    public Attraction find(long id) {
        try (PreparedStatement stmt = connect.prepareStatement("SELECT * FROM attractions WHERE id = ?")) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Attraction(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("description"),
                        rs.getString("prix_base"),
                        rs.getString("limite_age"),
                        rs.getString("image_url")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Attraction create(Attraction obj) {
        try (PreparedStatement stmt = connect.prepareStatement(
                "INSERT INTO attractions(nom, description, prix_base, limite_age, image_url) VALUES (?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, obj.getNom());
            stmt.setString(2, obj.getDescription());
            stmt.setString(3, obj.getPrix());
            stmt.setString(4, obj.getAge());
            stmt.setString(5, obj.getImageUrl());

            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                obj.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public Attraction update(Attraction obj) {
        try (PreparedStatement stmt = connect.prepareStatement(
                "UPDATE attractions SET nom=?, description=?, prix_base=?, limite_age=?, image_url=? WHERE id=?")) {

            stmt.setString(1, obj.getNom());
            stmt.setString(2, obj.getDescription());
            stmt.setString(3, obj.getPrix());
            stmt.setString(4, obj.getAge());
            stmt.setString(5, obj.getImageUrl());
            stmt.setInt(6, obj.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public void delete(Attraction obj) {
        try (PreparedStatement stmt = connect.prepareStatement("DELETE FROM attractions WHERE id=?")) {
            stmt.setInt(1, obj.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Attraction> getAllAttractions() {
        List<Attraction> attractions = new ArrayList<>();
        try (Statement stmt = connect.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM attractions")) {

            while (rs.next()) {
                Attraction a = new Attraction(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("description"),
                        rs.getString("prix_base"),
                        rs.getString("limite_age"),
                        rs.getString("image_url")
                );
                attractions.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attractions;
    }

    public static void ajouterAttraction(Attraction attraction) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdd_attractions", "root", "")) {
            String sql = "INSERT INTO attractions (nom, description, prix, age, imageUrl) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, attraction.getNom());
            stmt.setString(2, attraction.getDescription());
            stmt.setString(3, attraction.getPrix());
            stmt.setString(4, attraction.getAge()); // ou .getLimiteAge() selon ta classe
            stmt.setString(5, attraction.getImageUrl());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(); // Pour débugger
        }
    }

    public void supprimerParNom(String nom) {
        String sql = "DELETE FROM attractions WHERE nom = ?";
        try (PreparedStatement stmt = connect.prepareStatement(sql)) {
            stmt.setString(1, nom);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


