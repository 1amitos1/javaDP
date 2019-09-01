package game.entities.sportsman;

import game.entities.MobileEntity;

public class MyClone {
    /**
     *
     A class that knows how to create replicas of competitor's PROTOTYPE interface
     * @param @author Bar Lupo 311303416, Amit Hayun 203716659;
     * @return
     */

    public MobileEntity getclone(MobileEntity ws){
        return ws.MakeCopy();
    }
}
