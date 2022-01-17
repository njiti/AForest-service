package models;

public class EndangeredAnimal extends Animal {

    private String health;
    private String age;
    private String type = "endangered";

    public static final String MAX_HEALTH = "healthy";
    public static final String AV_HEALTH = "okay";
    public static final String MIN_HEALTH = "ill";

    public static final String MAX_AGE = "adult";
    public static final String AV_AGE = "young";
    public static final String MIN_AGE = "newborn";


    public EndangeredAnimal(String name, String health, String age) {
        this.name = name;
        this.health = health;
        this.age = age;
    }

    public String getAge() {
        return age;
    }

    public String getHealth() {
        return health;
    }

    public String getType() {
        return type;
    }
}
