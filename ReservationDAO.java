package Dao;

import Modele.Attraction;
import Modele.Reservation;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

import Modele.Reservation;
import java.sql.*;
        import java.util.*;

public class ReservationDAO extends DAO<Reservation> {

    @Override
    public Reservation find(long id) {
        try (PreparedStatement stmt = connect.prepareStatement("SELECT * FROM reservations WHERE id = ?")) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Reservation(
                        rs.getInt("id"),
                        rs.getInt("utilisateur_id"),
                        rs.getInt("attraction_id"),
                        rs.getInt("prix_paye"),
                        rs.getInt("reduction_appliquee")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Reservation create(Reservation r) {
        String sql = "INSERT INTO reservations (utilisateur_id, attraction_id, prix_paye, reduction_appliquee) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(2, r.getUtilisateur_id());
            stmt.setInt(3, r.getAttraction_id());
            stmt.setInt(4, r.getPrix_paye()); // ou .getLimiteAge() selon ta classe
            stmt.setInt(5, r.getReduction_appliquee());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                r.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return r;
    }

    @Override
    public Reservation update(Reservation r) {
        String sql = "UPDATE reservations SET utilisateur_id=?, attraction_id=?, prix_paye=?, reduction_appliquee=? WHERE id=?";
        try (PreparedStatement stmt = connect.prepareStatement(sql)) {
            stmt.setInt(1, r.getId());
            stmt.setInt(2, r.getUtilisateur_id());
            stmt.setInt(3, r.getAttraction_id());
            stmt.setInt(4, r.getPrix_paye()); // ou .getLimiteAge() selon ta classe
            stmt.setInt(5, r.getReduction_appliquee());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return r;
    }

    @Override
    public void delete(Reservation r) {
        try (PreparedStatement stmt = connect.prepareStatement("DELETE FROM reservations WHERE id=?")) {
            stmt.setInt(1, r.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Reservation> getAllReservations() {
        List<Reservation> reservations = new ArrayList<>();
        try (Statement stmt = connect.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM reservations")) {

            while (rs.next()) {
                Reservation r = new Reservation(
                        rs.getInt("id"),
                        rs.getInt("utilisateur_id"),
                        rs.getInt("attraction_id"),
                        rs.getInt("prix_paye"),
                        rs.getInt("reduction_appliquee")
                );
                reservations.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    public static void ajouterReservation(Reservation r) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/attractions", "root", "raph")) {
            String sql = "INSERT INTO reservations (utilisateur_id, attraction_id, prix_paye, reduction_appliquee) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, r.getId());
            stmt.setInt(2, r.getUtilisateur_id());
            stmt.setInt(3, r.getAttraction_id());
            stmt.setInt(4, r.getPrix_paye()); // ou .getLimiteAge() selon ta classe
            //stmt.setInt(5, r.getReduction_appliquee());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(); // Pour debugging
        }
    }

    public void supprimerParId(int id) {
        String sql = "DELETE FROM reservations WHERE id = ?";
        try (PreparedStatement stmt = connect.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
