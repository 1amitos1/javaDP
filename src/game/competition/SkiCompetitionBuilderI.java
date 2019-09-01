package game.competition;

import game.arena.IArena;
import game.arena.WinterArena;
import game.enums.Discipline;
import game.enums.Gender;
import game.enums.League;

/***
 * This class is able to create a competitive competition with 10 ski competitors.
 * And knows how to build an arena accordingly
 * implements ICompetitonBuilder
 * @author Bar Lupo 311303416, Amit Hayun 203716659;
 */
public class SkiCompetitionBuilderI implements ICompetitonBuilder {

    private int NumOfComptitor=0;
    public WinterICompetition winterCompetition;
    public IArena arena;

    public SkiCompetitionBuilderI(int NumOfComptitor,IArena arena){
        this.NumOfComptitor=NumOfComptitor;
        this.arena=arena;
        this.arena=new WinterArena();


    }

    @Override
    public void BuildArena() {
        //build arena default value
        this.winterCompetition=new SkiICompetition((WinterArena)arena,NumOfComptitor,Discipline.DOWNHILL, League.ADULT, Gender.MALE);
    }

    @Override
    public Void BuildCompetitor() {
        return null;
    }

    @Override
    public WinterICompetition getcompetition() {
        return this.winterCompetition;
    }
}
