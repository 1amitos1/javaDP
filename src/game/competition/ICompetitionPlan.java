package game.competition;
/**
 * This class implements the formatting template.
 * And shows the actions needed to produce competition
 * @author Bar Lupo 311303416, Amit Hayun 203716659;
 */

import game.arena.IArena;

public interface ICompetitionPlan {
    void addCompetitor(Competitor competitor);
    void setArena(IArena arena);
}
