package game.competition;

import game.arena.IArena;
import gui.CompetitionFrame;
import utilities.Point;
import utilities.ValidationUtils;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public abstract class ICompetition implements ICompetitionPlan {
    protected IArena arena;
    protected ArrayList<Competitor> activeCompetitors;

    protected ArrayList<Competitor> finishedCompetitors;
    protected int maxCompetitors;
    protected double y;

    private CompetitionFrame competitionFrame;
    public ICompetition(IArena arena, int maxCompetitors) {
        this.maxCompetitors = maxCompetitors;
        this.activeCompetitors = new ArrayList<>();
        this.finishedCompetitors = new ArrayList<>();
        this.setArena(arena);
        y=0;
    }

    protected abstract boolean isValidCompetitor(Competitor competitor);

    public void addCompetitor(Competitor competitor){
        ValidationUtils.assertNotNull(competitor);
        if(maxCompetitors <= activeCompetitors.size()){

            throw new IllegalStateException("WinterArena is full max = "+ maxCompetitors);
        }
        if(isValidCompetitor(competitor)){
        	Point s = new Point(0, y);
        	Point f = new Point(arena.getLength(), y);

            competitor.initRace(s,f,arena);
            activeCompetitors.add(competitor);

            competitor.addObserver(this.competitionFrame);
            y += 75;
        }
        else{

            throw new IllegalArgumentException("Invalid competitor "+ competitor);
        }
    }
    

    @Deprecated
    public void playTurn(){
        ArrayList<Competitor> tmp = new ArrayList<>(activeCompetitors);
        for(Competitor competitor: tmp){
            if(!arena.isFinished(competitor)){
                competitor.move(arena.getFriction());
                if(arena.isFinished(competitor)){
                    finishedCompetitors.add(competitor);
                    activeCompetitors.remove(competitor);
                }
            }
        }
    }
    
	public void startCompetition() throws InterruptedException {
		ExecutorService e = Executors.newFixedThreadPool(activeCompetitors.size());
		for (Competitor c : activeCompetitors) {
			e.execute(c);
		}
		e.shutdown();
	}

	
    public boolean hasActiveCompetitors(){
        return activeCompetitors.size() > 0;
    }

    public ArrayList<Competitor> getFinishedCompetitors() {
        return new ArrayList<>(finishedCompetitors);
    }
    
    public ArrayList<Competitor> getActiveCompetitors() {
        return new ArrayList<>(activeCompetitors);
    }

    public void setArena(IArena arena) {
        this.arena = arena;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setMaxCompetitors(int maxCompetitors) {
        this.maxCompetitors = maxCompetitors;
    }

    public void setActiveCompetitors(ArrayList<Competitor> activeCompetitors) {
        this.activeCompetitors = activeCompetitors;
    }

    public void setFinishedCompetitors(ArrayList<Competitor> finishedCompetitors) {
        this.finishedCompetitors = finishedCompetitors;
    }

    public IArena getArena() {
        return arena;
    }

    public int getMaxCompetitors() {
        return maxCompetitors;
    }

    public double getY() {
        return y;
    }

    ///func to pass refrance to gui to add observer
    public void addGuiObserver(CompetitionFrame competitionFrame){
        this.competitionFrame=competitionFrame;

    }
}
