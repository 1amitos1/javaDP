package game.entities.sportsman;

import game.entities.MobileEntity;
import game.enums.Gender;


public class Sportsman extends MobileEntity {
    private String name;
    private double age;
    private Gender gender;
    protected int racernumber=-1;

    public Sportsman(){
        super();
        this.name = "defult";
        this.age = 0;
        this.gender = Gender.MALE;
    }
    public Sportsman(String name, double age, Gender gender, double acceleration, double maxSpeed) {
        super(0, acceleration,maxSpeed);
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    //region Getters & setters
    public String getName() {
        return name;
    }

    public double getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getRacernumber() {
        return racernumber;
    }

    public void setRacernumber(int racernumber) {
        this.racernumber = racernumber;
    }
}
