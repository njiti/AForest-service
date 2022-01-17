package models;

public class NormalAnimal extends Animal {
    private String type = "normal";

    public NormalAnimal(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }
}