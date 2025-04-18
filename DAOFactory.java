package Dao;

import Modele.Attraction;

public class DAOFactory {
    private static DAOFactory instance;

    private DAOFactory() {
        // constructeur privé (singleton)
    }

    public static DAOFactory getInstance() {
        if (instance == null) {
            instance = new DAOFactory();
        }
        return instance;
    }

    public AttractionDAO getAttractionDAO() {
        return new AttractionDAO(); // ✅ Ici on retourne le type concret
    }
}

