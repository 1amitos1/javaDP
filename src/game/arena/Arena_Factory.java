package game.arena;

import game.enums.SnowSurface;
import game.enums.WeatherCondition;

public class Arena_Factory {
    /**
     *Produces arenas according to the input of a winter or summer scene
     @author Bar Lupo 311303416, Amit Hayun 203716659;
     */

    public IArena makeArena(int arenaLength, SnowSurface snowSurface, WeatherCondition weatherCond, String type){
        //type can be “winter” or “summer
        IArena arena=null;
        if(type.equals("winter arena")){
            arena=new WinterArena(arenaLength,snowSurface,weatherCond);
            //arenaLength,snowSurf,weatherCond)
        }
        else if(type.equals("summer arena")){
            arena=new SummerArena(arenaLength,snowSurface,weatherCond);
        }
        else{
            System.out.println("error12\n");
        }
        return arena;

    }
}
