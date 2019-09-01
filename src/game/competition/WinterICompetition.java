package game.competition;

import game.arena.IArena;
import game.entities.sportsman.WinterSportsman;
import game.enums.Discipline;
import game.enums.Gender;
import game.enums.League;

/**
 * Created by itzhak on 24-Mar-19.
 */
public class WinterICompetition extends ICompetition {
    private final Discipline discipline;
    private final League league;
    private final Gender gender;

    public WinterICompetition(IArena arena, int maxCompetitors, Discipline discipline, League league, Gender gender) {
        super(arena, maxCompetitors);
        this.discipline = discipline;
        this.league = league;
        this.gender = gender;
    }

    public boolean isValidCompetitor(Competitor competitor){

        if(competitor instanceof WinterSportsman){

            WinterSportsman winterSportsman = (WinterSportsman) competitor;


            if(discipline.equals(winterSportsman.getDiscipline())){

                if(league.isInLeague(winterSportsman.getAge())){

                    if(gender.equals(winterSportsman.getGender())){

                        return true;
                    }

                }

            }

//            return discipline.equals(winterSportsman.getDiscipline()) &&
//                    league.isInLeague(winterSportsman.getAge()) &&
//                    gender.equals(winterSportsman.getGender());
        }

        else{
            return false;
        }
        return false;
    }
    
    public Discipline getDiscipline() {
    	return discipline;
    }
    
    public League getLeague() {
    	return league;
    }
    
    public Gender getGender() {
    	return gender;
    }

    @Override
    public String toString() {
        String s=""+getGender()+ " "+ getLeague() +" "+ getDiscipline()+" " + getMaxCompetitors();
        return s;
    }
}
