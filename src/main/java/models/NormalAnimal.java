package models;

public class NormalAnimal extends Animal {

    public NormalAnimal(String name) {
        this.name = name;
    }

    public String getType() {
        String type = "normal";
        return type;
    }
}