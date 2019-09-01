package game.entities;

import utilities.Point;

/**
 * Created by itzhak on 07-Mar-19.
 */
public class MobileEntity extends Entity implements IMobileEntity{
    private  double maxSpeed;
    protected   double acceleration;
    private  double speed;

    /**
     * Ctor for a mobile entity in the game
     * @param initialSpeed initial speed of the entity
     * @param acceleration entity acceleration
     * @param maxSpeed entity maximum speed
     */
    public MobileEntity(){
        this.setSpeed(0);
        this.acceleration =20;
        this.maxSpeed =100;
    }
    public MobileEntity( double initialSpeed,double acceleration, double maxSpeed){
        this.setSpeed(initialSpeed);
        this.acceleration = acceleration;
        this.maxSpeed = maxSpeed;
    }

    //region IMobileEntity Implementation

    /**
     * @see IMobileEntity#move(double)
     */
    @Override
    public void move(double friction) {
        this.setSpeed(Math.min(this.maxSpeed,this.speed + this.getAcceleration()* (1-friction)));
        Point newLocation = this.getLocation().offset(this.speed,0);
        this.setLocation(newLocation);
    }
    //endregion

    //region Setters

    /**
     * Note: speed can theoretically be negative
     * @param speed the current speed of the entity
     */
    protected void setSpeed(double speed) {
        this.speed = speed;
    }
    //endregion

    //region Getters

    /**
     * @return the acceleration of the entity
     */
    public double getAcceleration() {
        return acceleration;
    }

    
    public double getSpeed() {
    	return speed;
    }
    
    public double getMaxSpeed() {
    	return maxSpeed;
    }

    @Override
    public MobileEntity MakeCopy() {

        MobileEntity ws=null;
        try{
            ws=(MobileEntity)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return ws;

    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    //endregion


    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public void setAcceleration(double acceleration) {
        this.acceleration = acceleration;
    }
}
