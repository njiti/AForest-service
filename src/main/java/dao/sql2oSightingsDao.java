package dao;

import models.EndangeredAnimal;
import models.NormalAnimal;
import models.Sighting;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.ArrayList;
import java.util.List;

public class sql2oSightingsDao implements sightingsDao {

    private final Sql2o sql2o;

    public sql2oSightingsDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void save(Sighting sighting) {
        try(Connection con = sql2o.open()) {
            String sql = "INSERT INTO sightings (animalid, location, ranger_name, type, sightedat) VALUES (:animalId, :location, :rangerName, :type, now())";
            int id = (int) con.createQuery(sql)
                    .bind(sighting)
                    .addParameter("animalId", sighting.getAnimalId())
                    .addParameter("location", sighting.getLocation())
                    .addParameter("rangerName", sighting.getRangerName())
                    .addParameter("type", sighting.getType())
                    .executeUpdate().getKey();
            sighting.setId(id);
        }
    }

    @Override
    public Sighting findById(int id) {
        try (Connection con = sql2o.open()) {
            String sql = "SELECT * FROM sightings WHERE id = :id";
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Sighting.class);
        }
    }

    @Override
    public List<Sighting> returnAll() {
        try (Connection con = sql2o.open()) {
            String sql = "SELECT * FROM sightings";
            return con.createQuery(sql).executeAndFetch(Sighting.class);
        }
    }

    @Override
    public void clearAllSightings() {
        try (Connection con = sql2o.open()) {
            String sql = "DELETE FROM sightings";
            con.createQuery(sql).executeUpdate();
        }
    }


    @Override
    public NormalAnimal showNormalAnimal(int animalId, String type) {
        try(Connection con = sql2o.open()) {

            String sql = "SELECT * FROM animals WHERE id = :id AND type = :type";

            return con.createQuery(sql).addParameter("id", animalId)
                    .addParameter("type" ,type)
                    .executeAndFetchFirst(NormalAnimal.class);

        }
    }

    @Override
    public EndangeredAnimal showEndangeredAnimal(int animalId, String type) {
        try(Connection con = sql2o.open()) {

            String sql2 = "SELECT * FROM animals WHERE id = :id AND type = :type";

            return con.createQuery(sql2).addParameter("id", animalId)
                    .addParameter("type", type)
                    .executeAndFetchFirst(EndangeredAnimal.class);

        }
    }
}
