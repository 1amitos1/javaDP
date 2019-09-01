package game.entities;

import utilities.Point;


public interface IMobileEntity extends Cloneable{
    /**
     * move the a mobile entity (only on the x axis for this stage of the work)
     * @param friction reduce acceleration by a factor of (1-friction)
     */
    void move(double friction);
    Point getLocation();
    double getSpeed();
    double getMaxSpeed();

    public MobileEntity MakeCopy();
}
