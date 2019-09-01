package game.arena;

import game.entities.IMobileEntity;
import game.enums.SnowSurface;
import game.enums.WeatherCondition;
import utilities.ValidationUtils;

/**
 * Created by itzhak on 07-Mar-19.
 */
public class WinterArena implements IArena {

    private double length;
    private SnowSurface surface;
    private WeatherCondition condition;


    /**
     * Ctor for a generic arena
     * @param length the length of the arena
     * @param surface the snow surface of the arena
     * @param condition the weather condition in the arena
     */

    public WinterArena(double length, SnowSurface surface, WeatherCondition condition) {
        this.length = length;
        this.surface = surface;
        this.condition = condition;
    }

    public WinterArena() {
        this.length = 700;
        this.surface =SnowSurface.ICE ;
        this.condition =WeatherCondition.CLOUDY;
    }



    @Override
    public double getFriction(){
        return surface.getFriction();
    }

    @Override
    public boolean isFinished(IMobileEntity mobileEntity) {
        ValidationUtils.assertNotNull(mobileEntity);
        return mobileEntity.getLocation().getX() >= length;
    }
    
    public double getLength() {
    	return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public void setSurface(SnowSurface surface) {
        this.surface = surface;
    }

    public SnowSurface getSurface() {
        return surface;
    }

    public WeatherCondition getCondition() {
        return condition;
    }

    public void setCondition(WeatherCondition condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return "WinterArena{" +
                "length=" + length +
                ", surface=" + surface +
                ", condition=" + condition +
                '}';
    }
}
