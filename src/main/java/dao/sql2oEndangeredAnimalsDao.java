package dao;

import models.EndangeredAnimal;
import org.sql2o.*;

import java.util.List;

public class sql2oEndangeredAnimalsDao implements endangeredAnimalsDao {

    private final Sql2o sql2o;

    public sql2oEndangeredAnimalsDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void save(EndangeredAnimal animal) {
        try(Connection con = sql2o.open()) {
            String sql = "INSERT INTO animals (name, type, health, age) VALUES (:name, 'endangered', :health, :age)";
            int id = (int) con.createQuery(sql, true)
                    .bind(animal)
                    .addParameter("name", animal.getName())
                    .addParameter("health", animal.getHealth())
                    .addParameter("age", animal.getAge())
                    .executeUpdate()
                    .getKey();
            animal.setId(id);
        }
    }

    @Override
    public List<EndangeredAnimal> returnAll() {
        try(Connection con = sql2o.open()) {
            String sql = "SELECT * FROM animals WHERE type = 'endangered'";
            return con.createQuery(sql).executeAndFetch(EndangeredAnimal.class);
        }
    }

    @Override
    public EndangeredAnimal findById(int id) {
        try(Connection con = sql2o.open()) {
            String sql = "SELECT * FROM animals WHERE type = 'endangered' AND id = :id";
            return con.createQuery(sql).addParameter("id", id).executeAndFetchFirst(EndangeredAnimal.class);
        }
    }

    @Override
    public void clearAllAnimals() {
        try(Connection con = sql2o.open()) {
            String sql = "DELETE FROM animals ";
            con.createQuery(sql).executeUpdate();
        }
    }
}
