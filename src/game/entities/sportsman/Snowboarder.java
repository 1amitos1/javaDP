package game.entities.sportsman;

import game.enums.Discipline;
import game.enums.Gender;

public class Snowboarder extends WinterSportsman{
    public Snowboarder(){
        super();
    }
    public Snowboarder(Snowboarder snowboarder){
        super(snowboarder.getName(),snowboarder.getAge(),snowboarder.getGender(),snowboarder.getAcceleration(),snowboarder.getMaxSpeed(),snowboarder.getDiscipline());
    }
    public Snowboarder(String name, double age, Gender gender, double acceleration, double maxSpeed, Discipline discipline) {
        super(name, age, gender, acceleration, maxSpeed, discipline);
    }
}
