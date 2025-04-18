package Dao;

import java.sql.Connection;
import Modele.Attraction;

public abstract class DAO<T> {
    protected Connection connect = ConnexionDB.getInstance();

    public abstract T find(long id);
    public abstract T create(T obj);
    public abstract T update(T obj);
    public abstract void delete(T obj);
}
