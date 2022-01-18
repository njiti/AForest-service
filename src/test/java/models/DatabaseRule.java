package models;

import org.junit.rules.ExternalResource;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class DatabaseRule extends ExternalResource {

    @Override
    protected void before() {
        DB.sql2o = new Sql2o("jdbc:postgresql://ec2-54-158-232-223.compute-1.amazonaws.com/d74l98ruq4hcnn?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory", "zhrozwhrlajtdf", "a69b2dd3de2c30fb05fce08b5ac98bbef3bc218f66740511b071dea25960aeae");
//
//        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker_test", "fiona", "Man");

    }

    @Override
    protected void after() {
        try(Connection con = DB.sql2o.open()) {
            String deleteAnimalQuery = "DELETE FROM animals *;";
            String deleteSightingQuery = "DELETE FROM sightings *;";
            con.createQuery(deleteAnimalQuery).executeUpdate();
            con.createQuery(deleteSightingQuery).executeUpdate();
        }
    }
}