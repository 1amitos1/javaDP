package gui.State;

import game.arena.IArena;
import game.entities.sportsman.WinterSportsman;
import utilities.Point;

import java.util.Observer;

/***
 * A common interface of competitors where we can change situations accordingly
 */
public interface IRaceState {
    void move(double friction);
    Point getLocation();
    double getSpeed();
    double getMaxSpeed();
    String getState();
    String getName();
    String AllData();
    String getFinish();
    void initRace(Point p, Point f, IArena arena);
    void addObserver(Observer o);
    WinterSportsman MakeCopy();


}
