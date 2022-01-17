package dao;

import models.EndangeredAnimal;


import java.util.List;

public interface endangeredAnimalsDao {
    void save(EndangeredAnimal animal);

    List<EndangeredAnimal> returnAll();

    EndangeredAnimal findById(int id);

    void clearAllAnimals();
}
