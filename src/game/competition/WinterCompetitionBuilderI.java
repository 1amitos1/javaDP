package game.competition;

import game.arena.IArena;
import game.enums.Discipline;
import game.enums.Gender;
import game.enums.League;
/**
 * This class implements the formatting the Builder dp.
 * And shows the actions needed to produce competition
 * @author Bar Lupo 311303416, Amit Hayun 203716659;
 */

public class WinterCompetitionBuilderI implements ICompetitonBuilder {
    public WinterICompetition winterCompetition;

    public WinterCompetitionBuilderI(IArena arena, int maxCompetitors, Discipline discipline, League league, Gender gender){
        //arena,maxCompetitors,disc,leag,gen
        winterCompetition=new WinterICompetition(arena,maxCompetitors,discipline,league,gender);

    }


    @Override
    public void BuildArena() {

    }

    @Override
    public Void BuildCompetitor() {
        return null;
    }

    @Override
    public WinterICompetition getcompetition() {
        return winterCompetition;
    }
}
