package dao;

import models.NormalAnimal;

import java.util.List;

public interface normalAnimalsDao {

    void save(NormalAnimal animal);

    List<NormalAnimal> returnAll();

    NormalAnimal findById(int id);


    void clearAllAnimals();
}