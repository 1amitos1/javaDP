package game.arena;

import game.entities.IMobileEntity;
import game.enums.SnowSurface;
import game.enums.WeatherCondition;
import utilities.ValidationUtils;

public class SummerArena implements IArena {


    private double length;
    private SnowSurface surface;
    private WeatherCondition condition;


    /**
     * Ctor for a generic arena
     * @param the length of the arena
     * @param  the snow surface of the arena
     *@author Bar Lupo 311303416, Amit Hayun 203716659;
     *
     */
    public SummerArena(){}
    public SummerArena(double length, SnowSurface surface, WeatherCondition condition) {
        this.length = length;
        this.surface = surface;
        this.condition = condition;
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

    public void setCondition(WeatherCondition condition) {
        this.condition = condition;
    }
}
