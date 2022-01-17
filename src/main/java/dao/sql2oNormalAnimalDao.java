package dao;

import models.NormalAnimal;
import org.sql2o.*;

import java.util.List;

public class sql2oNormalAnimalDao implements dao.normalAnimalsDao {

    private final Sql2o sql2o;

    public sql2oNormalAnimalDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void save(NormalAnimal animal) {
        try(Connection con = sql2o.open()) {
            String sql = "INSERT INTO animals (name, type) VALUES (:name, 'normal')";
            int id = (int) con.createQuery(sql, true)
                    .bind(animal)
                    .addParameter("name", animal.getName())
                    .executeUpdate()
                    .getKey();
            animal.setId(id);
        }
    }

    @Override
    public List<NormalAnimal> returnAll() {
        try(Connection con = sql2o.open()) {
            String sql = "SELECT * FROM animals WHERE type = 'normal'";
            return con.createQuery(sql).throwOnMappingFailure(false).executeAndFetch(NormalAnimal.class);
        }
    }

    @Override
    public NormalAnimal findById(int id) {
        try(Connection con = sql2o.open()) {
            String sql = "SELECT * FROM animals WHERE type = 'normal' AND id = :id";
            return con.createQuery(sql).throwOnMappingFailure(false).addParameter("id", id).executeAndFetchFirst(NormalAnimal.class);
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
