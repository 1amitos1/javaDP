
package gui.State;
/**
 * This class implements the modes
 * and operates according to the situation where the
 * competitor is currently in the position
 * @author Bar Lupo 311303416, Amit Hayun 203716659;
 */

import game.arena.IArena;
import game.entities.sportsman.WinterSportsman;
import utilities.Point;

import java.util.Observer;

public class Active implements IRaceState {

    protected WinterSportsman winterSportsman;


    public Active(WinterSportsman ws){
        this.winterSportsman=ws;

    }

    @Override
    public void move(double friction) {

    }

    @Override
    public Point getLocation() {
        return this.winterSportsman.getLocation();
    }

    @Override
    public double getSpeed() {
        return this.winterSportsman.getSpeed();
    }

    @Override
    public double getMaxSpeed() {
        return this.winterSportsman.getMaxSpeed();
    }

    @Override
    public String getState() {
        return this.winterSportsman.getState();
    }

    @Override
    public String getName() {
        return this.winterSportsman.getName();
    }

    @Override
    public String AllData() {
        return this.winterSportsman.toString();
    }

    @Override
    public String getFinish() {
        return "NO";
    }

    @Override
    public void initRace(Point p, Point f, IArena arena) {

    }

    @Override
    public void addObserver(Observer o) {

    }

    @Override
    public WinterSportsman MakeCopy() {
        return null;
    }
}
