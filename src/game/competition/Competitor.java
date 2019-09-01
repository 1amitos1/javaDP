package game.competition;

import game.arena.IArena;
import game.entities.IMobileEntity;
import game.entities.sportsman.WinterSportsman;
import gui.State.IRaceState;
import utilities.Point;

import java.util.Observer;


public interface Competitor extends IMobileEntity, Runnable, IRaceState {
    void initRace();
    void initRace(Point p, Point f, IArena arena); 
    void addObserver(Observer o);
    WinterSportsman MakeCopy();
}
