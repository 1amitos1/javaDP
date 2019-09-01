package game.competition;

/**
 * An engineer's class implements the creative process of competition
 * using reference to ICompetitonBuilder
 */

public class CompetitionEngineer {

    private ICompetitonBuilder ICompetitonBuilder;

    public CompetitionEngineer(ICompetitonBuilder ICompetitonBuilder){
        this.ICompetitonBuilder = ICompetitonBuilder;
    }

    public WinterICompetition MakeComptition(){
        ICompetitonBuilder.BuildArena();
        ICompetitonBuilder.BuildCompetitor();
        return null;
    }


    public WinterICompetition getCompetition(){
        return ICompetitonBuilder.getcompetition();
    }
}
