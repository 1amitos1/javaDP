/**
 It receives an array array of objects when a competitor has finished racing or another fault has occurred.
 The table works according to the conditions if a competitor is finished he will appear in both tables
 * @version 1.0
 * @author Bar Lupo 311303416, Amit Hayun 203716659;
 */
package gui;
import game.competition.Competitor;
import game.competition.ICompetition;
import game.entities.sportsman.Sportsman;
import game.entities.sportsman.WinterSportsman;
import gui.State.*;
import utilities.Point;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.locks.ReentrantLock;


public class InfoTable extends JFrame{

    ReentrantLock lock = new ReentrantLock();


    public InfoTable(ICompetition competition, int racersNumber){
        super("Competitors information");
        String[] columnNames = {"Name",
                             "Speed",
                             "Max speed",
                             "Location",
                             "Finished"};
        
        String[][] data = new String[racersNumber][5];
        int i=0;
     
        for (Competitor c: competition.getFinishedCompetitors()){
            data[i][0] = ((Sportsman) c).getName();
            data[i][1] = ""+c.getSpeed();
            data[i][2] = ""+c.getMaxSpeed();
            data[i][3] = ""+c.getLocation().getX();
            data[i][4] = "Yes";
            i++;
        }
                    
        for (Competitor c: competition.getActiveCompetitors()){
            data[i][0] = ((Sportsman) c).getName();
            data[i][1] = ""+c.getSpeed();
            data[i][2] = ""+c.getMaxSpeed();
            data[i][3] = ""+c.getLocation().getX();
            data[i][4] = "No";
            i++;
        }
                    
        JTable table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        JScrollPane scrollPane = new JScrollPane(table);

        JPanel tabPan = new JPanel();
        tabPan.add(scrollPane);                   

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(tabPan);
        pack();
        setVisible(true); 
    }

    public InfoTable(Vector<IRaceState> iRaceState, ArrayList<Competitor> FinishedRacer, int racersNumber, WinterSportsman ws, ArrayList<Object> s){
        super("Competitors information");

        lock.lock();
        String state=(String)s.get(3);
        Vector<IRaceState> Sortracer=new Vector<>(this.sort(iRaceState,ws));//Sort RACER ARRAY

        Vector<IRaceState> Finish=bulid_IRaceState(FinishedRacer);//SET STATE
        Vector<IRaceState>SortFinish=new Vector<>(this.sort(Finish,ws));


        String[] columnNames = {"Name", "Speed", "Max speed", "Location", "Finished", "State"};
        String[][] dataF = new String[racersNumber][6];


        int i=0;
        for (IRaceState c : Sortracer) {
            if(c.getClass().getName().equals(Completed.class.getName())){

                dataF[i][0] = "" + c.getName();
                dataF[i][1] = "" + c.getSpeed();
                dataF[i][2] = "" + c.getMaxSpeed();
                dataF[i][3] = "" + c.getLocation().getX();
                dataF[i][4] = "" + c.getFinish();
                dataF[i][5] = "" + c.getState();
                i++;
            }
        }
        i=0;

        String[][] data = new String[racersNumber][6];
        for (IRaceState c: Sortracer){
                data[i][0]=""+c.getName();
                data[i][1]=""+c.getSpeed();
                data[i][2]=""+c.getMaxSpeed();
                data[i][3]=""+c.getLocation().getX();
                data[i][4]=""+c.getFinish();
                data[i][5]=""+c.getState();
            i++;
        }


        JTable table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());

        JTable table2=new JTable(dataF,columnNames);
        table2.setPreferredScrollableViewportSize(table.getPreferredSize());



        JScrollPane scrollPane = new JScrollPane(table);
        JPanel tabPan = new JPanel();
        tabPan.add(scrollPane);


        JScrollPane scrollPane1 = new JScrollPane(table2);
        tabPan.add(scrollPane1);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(tabPan);
        pack();
        setVisible(true);
        lock.unlock();
    }

    /***
     *The purpose of this function is to sort by the state received from the arena
     * @version 1.0
     * @author Bar Lupo 311303416, Amit Hayun 203716659;
     */
    public Vector<IRaceState> sort(Vector<IRaceState> v,WinterSportsman ws){

        Point p;
        Vector<IRaceState> sort=null;
        int n=v.size();
        IRaceState temp;

        for(int i=0;i<n;i++){
            for(int j=0;j<n-i-1;j++){

                if(v.get(j).getLocation().getX()<v.get(j+1).getLocation().getX()){
                    IRaceState tempp=v.get(j);
                    v.set(j,v.get(j+1));
                    v.set(j+1,tempp);
                }
            }
        }

        sort=new Vector<>(v);
        return sort;
    }
    /***
     *The purpose of this function is to build by the state received from the arena
     * @version 1.0
     * @author Bar Lupo 311303416, Amit Hayun 203716659;
     */
    public  Vector<IRaceState> bulid_IRaceState(ArrayList<Competitor> ActiveRacer){

        lock.lock();
        Vector<IRaceState> RaceStateList= new Vector<>();
        ArrayList<IRaceState> Statearr=new ArrayList<>(ActiveRacer);

        if(!Statearr.isEmpty()){
            for(IRaceState com:Statearr){

                if(com.getState().equals("Active")){
                    RaceStateList.add(new Active((WinterSportsman)com));
                }
                else if(com.getState().equals("Injured(")){
                    RaceStateList.add(new Injured((WinterSportsman)com));
                }
                else if(com.getState().equals("Disabled(")){
                    RaceStateList.add(new Disabled((WinterSportsman)com));

                }
                else if(com.getState().equals("Completed")){
                    RaceStateList.add(new Completed((WinterSportsman)com));
                }

            }
        }
        lock.unlock();

        return new Vector<IRaceState>(RaceStateList);
    }//build new stat array
}
