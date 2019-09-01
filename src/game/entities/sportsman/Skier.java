package game.entities.sportsman;

import game.enums.Discipline;
import game.enums.Gender;


public class Skier extends WinterSportsman{

    public Skier(){
        super("sk5",29, Gender.MALE, 3.3,60, Discipline.DOWNHILL);
    }
    public Skier(Skier skier){
        super(skier.getName(),skier.getAge(),skier.getGender(),skier.getAcceleration(),skier.getMaxSpeed(),skier.getDiscipline());
    }
    public Skier(String name, double age, Gender gender, double acceleration, double maxSpeed, Discipline discipline) {
        super(name, age, gender, acceleration, maxSpeed, discipline);
    }



}
