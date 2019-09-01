package game.entities.sportsman;
/**
 * An class that  extends Decorator dp
 * change Speed to WS add acceleration
 * @author Bar Lupo 311303416, Amit Hayun 203716659;
 */
public class SpeedSportsman extends WSDecorator {

    private double acceleration=0;

    public SpeedSportsman(IWinterSportsman winterSportsman, double acceleration){
        super(winterSportsman);

        this.acceleration=acceleration;
        this.winterSportsman.Changeacceleration(acceleration);

    }


    @Override
    public void DSetNewColorPic(String color) {

    }

    @Override
    public void Changeacceleration(double Addacceleration) {

    }
    @Override
    public WinterSportsman MakeSportman(){
        return this.winterSportsman.MakeSportman();
    }
}
