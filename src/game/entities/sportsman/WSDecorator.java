package game.entities.sportsman;

public abstract class WSDecorator implements IWinterSportsman{
    protected IWinterSportsman winterSportsman;

    /**
     * An abstract class that implements a Decorator dp
     * From it inherit other departments color, speed
     * @param WinterSportsman
     */
    public WSDecorator(IWinterSportsman WinterSportsman){
        this.winterSportsman= WinterSportsman;
    }



    public WinterSportsman MakeSportman(){
        return this.winterSportsman.MakeSportman();
    }


}
