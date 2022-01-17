package dao;

import models.Animal;
import models.EndangeredAnimal;
import models.NormalAnimal;
import models.Sighting;

import java.util.List;

public interface sightingsDao {

    void save(Sighting sighting);

    Sighting findById(int id);

    List<Sighting> returnAll();

    void clearAllSightings();

    NormalAnimal showNormalAnimal(int animalId, String type);

    EndangeredAnimal showEndangeredAnimal(int animalId, String type);

}