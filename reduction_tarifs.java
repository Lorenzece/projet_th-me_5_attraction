package Dao;

import Modele.Attraction;

import java.sql.*;
import java.util.*;

 class reduction_tarifs extends DAO<Attraction> {

    @Override
    
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

    public void adapter_reduction(String age) {
        //Entre 14 ans et 65 ans
        String sql1 = "SELECT age FROM utilisateurs WHERE age >= 14 AND age <= 65 ";
      //Enfants
        String sql2 = "SELECT age FROM utilisateurs WHERE age <= 14 ";
      //Personnes agées
        String sql2 = "SELECT prix_base FROM attractions ";
        try (PreparedStatement stmt = connect.prepareStatement(sql1)) {
            stmt.setString(1, age);
            stmt.executeUpdate();
        ResultatSet res= stmt.executeQuery(sql1);
        while(res.next()) {
            //Récupérer par nom de colonne
            int prix_base = attraction.getPrix();
            //Afficher les valeurs
            if (sql1) {
                System.out.print(", Age: " + age);
                System.out.println("vous avez une reduction de " + prix_base * 0.9);
            }
            if (sql2) {
                System.out.print(", Age: " + age);
                System.out.println("vous n'avez pas de reductions " );
            }
            if (sql3) {
                System.out.print(", Age: " + age);
                System.out.println("vous avez une reduction de "+prix_base*0.88 );
            }
        }
        //étape 6: fermez l'objet de connexion
        conn.close();
    }
    catch(Exception e){
         System.out.println(e);
     }
 }

        try (PreparedStatement stmt = connect.prepareStatement(sql2)) {
            stmt.setString(1, age);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


