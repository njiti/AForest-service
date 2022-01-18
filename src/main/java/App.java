//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import dao.sql2oEndangeredAnimalsDao;
import dao.sql2oNormalAnimalDao;
import dao.sql2oSightingsDao;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import models.EndangeredAnimal;
import models.NormalAnimal;
import models.Sighting;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class App {
    public App() {
    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder(new String[0]);
        return processBuilder.environment().get("PORT") != null ? Integer.parseInt((String)processBuilder.environment().get("PORT")) : 4567;
    }

    public static void main(String[] args) {
        Spark.port(getHerokuAssignedPort());
        Spark.staticFileLocation("/public");
        String connectionString = "jdbc:postgresql://ec2-3-232-22-121.compute-1.amazonaws.com:5432/dbie0sfc5mt62m";
        Sql2o sql2o = new Sql2o(connectionString, "bgyifudirguvza", "ce9fb937d9b5c0d02c15be407406c71b53ca8c269f8ce6353b43598931fdad63");
        sql2oNormalAnimalDao sql2oNormalAnimalDao = new sql2oNormalAnimalDao(sql2o);
        sql2oEndangeredAnimalsDao sql2oEndangeredAnimalsDao = new sql2oEndangeredAnimalsDao(sql2o);
        sql2oSightingsDao sql2oSightingsDao = new sql2oSightingsDao(sql2o);
        Spark.get("/", (request, response) -> {
            Map<String, Object> model = new HashMap();
            List<EndangeredAnimal> endangeredAnimals = sql2oEndangeredAnimalsDao.returnAll();
            List<NormalAnimal> normalAnimals = sql2oNormalAnimalDao.returnAll();
            List<Sighting> allSightings = sql2oSightingsDao.returnAll();
            model.put("allSightings", allSightings);
            model.put("endangeredAnimals", endangeredAnimals);
            model.put("normalAnimals", normalAnimals);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());
        Spark.get("/normal-sightings/new", (request, response) -> {
            Map<String, Object> model = new HashMap();
            return new ModelAndView(model, "normal-sighting-form.hbs");
        }, new HandlebarsTemplateEngine());
        Spark.get("/endangered-sightings/new", (request, response) -> {
            Map<String, Object> model = new HashMap();
            return new ModelAndView(model, "endangered-sighting-form.hbs");
        }, new HandlebarsTemplateEngine());
        Spark.post("/normal-sightings/new", (request, response) -> {
            String animalIdStr = request.queryParams("animalId");
            int animalId = Integer.parseInt(animalIdStr);
            String animalName = request.queryParams("animalName");
            String animalType = request.queryParams("type");
            String location = request.queryParams("location");
            String rangerName = request.queryParams("rangerName");
            NormalAnimal normalAnimal = new NormalAnimal(animalName);
            sql2oNormalAnimalDao.save(normalAnimal);
            Sighting newSighting = new Sighting(animalId, location, rangerName, animalType);
            sql2oSightingsDao.save(newSighting);
            response.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());
        Spark.post("/endangered-sightings/new", (request, response) -> {
            String endangeredAnimalIdStr = request.queryParams("animalId");
            int endangeredAnimalId = Integer.parseInt(endangeredAnimalIdStr);
            String endangeredAnimalName = request.queryParams("animalName");
            String type = request.queryParams("type");
            String health = request.queryParams("health");
            String age = request.queryParams("age");
            String location = request.queryParams("location");
            String rangerName = request.queryParams("rangerName");
            EndangeredAnimal endangeredAnimal = new EndangeredAnimal(endangeredAnimalName, health, age);
            sql2oEndangeredAnimalsDao.save(endangeredAnimal);
            Sighting newSighting = new Sighting(endangeredAnimalId, location, rangerName, type);
            sql2oSightingsDao.save(newSighting);
            response.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());
    }
}