package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionDB {
    private static Connection connexion;

    private ConnexionDB() {}

    public static Connection getInstance() {
        if (connexion == null) {
            try {
                connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdd_attraction", "root", "");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connexion;
    }
}
