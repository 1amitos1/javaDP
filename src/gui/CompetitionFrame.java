/***
 This department is watching the
 *competition and getting updates when
 *there is an event that is obtained by a destiny function
 * @author Bar Lupo 311303416, Amit Hayun 203716659;
 */
package gui;

import game.entities.sportsman.WinterSportsman;
import utilities.Point;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


public final class CompetitionFrame extends JFrame implements Observer,Runnable {
    private ArenaPanel arenaPanel = null;
    private static final long serialVersionUID = 1L;

    private JPanel RmainPanel=null;


    public CompetitionFrame() {
        super("ICompetition");
        updateFrame();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    

    public JPanel getMyContentPane(){

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        if (arenaPanel==null) {
            arenaPanel = new ArenaPanel();
            arenaPanel.setCompetitionFrame(this); 
        }

        else arenaPanel.initArena();
        mainPanel.add(arenaPanel,BorderLayout.WEST); 
        mainPanel.add(new JSeparator(SwingConstants.VERTICAL),BorderLayout.CENTER);

        ControlsPanel controlsPanel=new ControlsPanel(arenaPanel);

        mainPanel.add(controlsPanel,BorderLayout.EAST);
        this.RmainPanel=new JPanel();
        this.RmainPanel=mainPanel;
        return mainPanel;
    }
    

    public void updateFrame(){
        this.setContentPane(getMyContentPane());
        this.pack();
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - getHeight()) / 2);

        this.setLocation(x, y);
        this.setVisible(true);
    }


    public synchronized void update(Observable o, Object arg) {
        WinterSportsman winterSportsman=(WinterSportsman) o;
        ArrayList<Object> s=new ArrayList<>();

        String action="";
        utilities.Point currPoint;
        WinterSportsman currWs;

        //for state var 1,2,3
        String state="";
        LocalDateTime Timeofaccident;
        int TimeToHeal;
            s=(ArrayList<Object>)arg;


            action=(String)s.get(0);
            currPoint=(Point) s.get(1);
            currWs=(WinterSportsman)s.get(2);

            state=(String)s.get(3);
            Timeofaccident=(LocalDateTime)s.get(4);
            TimeToHeal=(int)s.get(5);



           if(currWs.getState().equals("Completed")){
                //this.arenaPanel.UpdateRacerArr();
               this.arenaPanel.RacerFinish(currWs);
               this.arenaPanel.ChngeState(s);
            }
            else if(currWs.getState().equals("Active")){
                this.arenaPanel.UpdateRacer();
                this.arenaPanel.ChngeState(s);
            }
            else if(currWs.getState().equals("Injured")){
                this.arenaPanel.UpdateRacer();
                this.arenaPanel.ChngeState(s);
            }
            else if(currWs.getState().equals("Disabled")){

                this.arenaPanel.updateRacesateDisabled(currWs);
                this.arenaPanel.ChngeState(s);
                Thread.currentThread().stop();

            }

    }


    
    
    public static void main(String[] args) {
    	CompetitionFrame competitionFrame = new CompetitionFrame();
	}


    @Override
    public void run() {




    }
}
