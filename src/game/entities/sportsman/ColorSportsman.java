package game.entities.sportsman;
/**
 * An class that  extends Decorator dp
 * From it inherit other departments color change color to WS
 * @param WinterSportsman
 */
import javax.swing.*;

public class ColorSportsman extends WSDecorator {

    private ImageIcon pic;
    private String color="Red";

    public ColorSportsman(IWinterSportsman winterSportsman,String color){
        super(winterSportsman);
        this.color=color;

        this.winterSportsman.DSetNewColorPic(color);

    }





    @Override
    public void DSetNewColorPic(String color){

    }

    @Override
    public void Changeacceleration(double Addacceleration) {

    }

    public WinterSportsman MakeSportman(){
        return this.winterSportsman.MakeSportman();
    }
}
