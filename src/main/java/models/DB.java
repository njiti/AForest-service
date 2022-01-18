package models;

import org.sql2o.*;

public class DB {

    public static Sql2o sql2o = new Sql2o("jdbc:postgresql://ec2-54-158-232-223.compute-1.amazonaws.com:5432/d74l98ruq4hcnn?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory", "administrator", "2541kamau");


//    public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker", "administrator", "2541kamau");

}
