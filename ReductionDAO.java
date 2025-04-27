package Dao;

import Modele.Reduction;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReductionDAO extends DAO<Reduction> {

    @Override
    public Reduction find(long id) {
        try (PreparedStatement stmt = connect.prepareStatement("SELECT * FROM reductions WHERE id = ?")) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Reduction(
                        rs.getInt("id"),
                        rs.getInt("min_age"),
                        rs.getInt("max_age"),
                        rs.getInt("min_visites"),
                        rs.getInt("pourcentage_reduction")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Reduction> getAllReductions() {
        List<Reduction> reductions = new ArrayList<>();
        try (Statement stmt = connect.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM reductions")) {

            while (rs.next()) {
                Reduction r = new Reduction(
                        rs.getInt("id"),
                        rs.getInt("min_age"),
                        rs.getInt("max_age"),
                        rs.getInt("min_visites"),
                        rs.getInt("pourcentage_reduction")
                );
                reductions.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reductions;
    }

    @Override
    public Reduction create(Reduction r) {
        String sql = "INSERT INTO Reductions (min_age, max_age, min_visites, pourcentage_reduction) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, r.getMin_age());
            stmt.setInt(2, r.getMax_age());
            stmt.setInt(3, r.getMin_visites());
            stmt.setInt(4, r.getPourcentage_reduction());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    r.setId(rs.getInt(1)); // récupère l'ID généré
                }
                return r;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Reduction update(Reduction r) {
        String sql = "UPDATE Reductions SET min_age=?, max_age=?, min_visites=?, pourcentage_reduction=? WHERE id=?";
        try (PreparedStatement stmt = connect.prepareStatement(sql)) {
            stmt.setInt(1, r.getMin_age());
            stmt.setInt(2, r.getMax_age());
            stmt.setInt(3, r.getMin_visites());
            stmt.setInt(4, r.getPourcentage_reduction());
            stmt.setInt(5, r.getId());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                return r;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(Reduction r) {
        try (PreparedStatement stmt = connect.prepareStatement("DELETE FROM Reductions WHERE id = ?")) {
            stmt.setInt(1, r.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
