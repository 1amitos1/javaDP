package gui;

import game.arena.Arena_Factory;
import game.arena.IArena;
import game.arena.WinterArena;
import game.competition.*;
import game.entities.sportsman.*;
import game.enums.*;
import gui.State.*;
import utilities.Point;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.locks.ReentrantLock;

public class ArenaPanel extends JPanel {

    private int arenaLength = 700;
    private int arenaWidth = 1000;
    private int maxCompetitors = 10;
    private String surface = null;
    private String weather = null;
    private IArena arena = null;
    private Arena_Factory arena_factory;
    private String type=null;
    private String competition = null;
    private String discipline = null;
    private String league = null;
    private String gender = null;
    private int competitorsNumber = 0;
    private WinterICompetition winterCompetition = null;
    private static ArrayList<WinterSportsman> competitors;
    private ImageIcon competitorsImages[] = null;
    private CompetitionEngineer competitionEngineer;
    private SkiCompetitionBuilderI skiCompetitionBuilder;//-->>Active when skicopmtitionbuilder press
    private WinterCompetitionBuilderI winterCompetitionBuilder;
    int RacerSiNumber=0;
    String Ucolor;
    private static ArrayList<String> racerindex= new ArrayList<String>();
    private ImageIcon imageIcon1;
    private JLabel picLabel1;
    ReentrantLock lock = new ReentrantLock();
    private Time myTime;
    private WinterSportsman Curr=null;
    private static ArrayList<Object> s=new ArrayList<>();
    private static Vector<IRaceState> RaceStateArr=new Vector<>();
    private static Vector<IRaceState> FinishRacerstate=new Vector<>();
    private CompetitionFrame competitionFrame = null;
    public WinterICompetition getWinterCompetition() {
        return winterCompetition;
    }
    private boolean competitionStarted = false;
    private boolean competitionFinished = false; 
    private InfoTable infoTable = null;
    private static Vector<IRaceState> racerArr=new Vector<>();



//---------------------------------------------------------------------
    public static ArrayList<WinterSportsman> getCompetitors() {
        return competitors;
    }

    public ImageIcon[] getCompetitorsImages() {
        return competitorsImages;
    }
    //----------------------------------------------------------------

    public void initArena(){

        this.removeAll();

        setPreferredSize(new Dimension(arenaWidth,arenaLength+80));
        imageIcon1 = new ImageIcon(new ImageIcon("icons/"+weather+".jpg").getImage().getScaledInstance(arenaWidth,arenaLength+80, Image.SCALE_DEFAULT));
        picLabel1 = new JLabel(imageIcon1);
        picLabel1.setLocation(0, 0);
        picLabel1.setSize(arenaWidth,arenaLength+80);
        add(picLabel1);

        for (int i=0; i<competitorsNumber; i++){
            WinterSportsman ws=competitors.get(i);

            ws.getRpic().setLocation((int) ws.getLocation().getY()+5 ,(int) ws.getLocation().getX());
            ws.getRpic().setSize(70,70);
            this.picLabel1.add(ws.getRpic());
            picLabel1.add(ws.getRpic());

        }



    }

    
    
    public  ArenaPanel()  {
        setLayout(null);
        initArena();

    }
    
    
    //change build arena
    public void buildArena(String surface, String weather,String type){
        this.surface = surface;
        this.weather = weather;
     
        competitors = new ArrayList<>();
        competitorsImages = new ImageIcon[maxCompetitors];
        winterCompetition = null;
        competition = null;
        maxCompetitors=10;
        this.arenaWidth = 1000;
        SnowSurface snowSurf;
        WeatherCondition weatherCond;
        
        if (surface.equals("Powder"))
        	snowSurf = SnowSurface.POWDER;
        else if(surface.equals("Crud"))
        	snowSurf = SnowSurface.CRUD;
        else
        	snowSurf = SnowSurface.ICE;    
        
        if (weather.equals("Sunny"))
        	weatherCond = WeatherCondition.SUNNY;
        else if (weather.equals("Cloudy"))
        	weatherCond = WeatherCondition.CLOUDY;
        else 
        	weatherCond = WeatherCondition.STORMY;

       //use factory


        arena_factory=new Arena_Factory();
        arena=arena_factory.makeArena(arenaLength,snowSurf,weatherCond,type);
        competitionFrame.updateFrame();
    }
    
    
    
    public void createCompetition(String competition, String discipline, String league, String gender) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
    	this.competition = competition;
    	this.discipline = discipline;
    	this.league = league;
    	this.gender = gender;
    	
        competitionStarted = competitionFinished = false; 
        competitorsNumber = 0;
        
        int newWidth = (maxCompetitors)*75 + 5;
        
        if (newWidth>1000)
            this.arenaWidth = newWidth;
        else
            this.arenaWidth = 1000;
                    
        competitors = new ArrayList<>();
        competitorsImages = new ImageIcon[maxCompetitors];    
        
        Discipline disc;
        League leag;
        Gender gen;
        
        if (discipline.equals("Slalom"))
        	disc = Discipline.SLALOM;
        else if (discipline.equals("Giant-Slalom"))
        	disc = Discipline.GIANT_SLALOM;
        else if(discipline.equals("Downhill"))
        	disc = Discipline.DOWNHILL;
        else 
        	disc = Discipline.FREESTYLE;
        
        
        if (league.equals("Junior"))
        	leag = League.JUNIOR;
        else if(league.equals("Adult"))
        	leag = League.ADULT;
        else
        	leag = League.SENIOR;
        
        if(gender.equals("Male"))
        	gen = Gender.MALE;
        else
        	gen = Gender.FEMALE;


        ClassLoader cl = ClassLoader.getSystemClassLoader();
        Class c = cl.loadClass("game.competition."+competition+"ICompetition");
        Constructor con = c.getConstructor(WinterArena.class,int.class,Discipline.class,League.class,Gender.class);

        winterCompetition = (WinterICompetition) con.newInstance(arena,maxCompetitors,disc,leag,gen);//
        competitionFrame.updateFrame();

    }
    
    
    
    public void addCompetitor(String name, double age, double maxSpeed, double acceleration,int racernumber,String color) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{

        WinterSportsman ws = null;
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        Class c = cl.loadClass("game.entities.sportsman."+competition+"er");
        Constructor con = c.getConstructor(String.class, double.class, Gender.class, double.class, double.class, Discipline.class);

        ws = (WinterSportsman) con.newInstance(name,age,winterCompetition.getGender(),acceleration,maxSpeed,winterCompetition.getDiscipline());


        try {
            winterCompetition.addGuiObserver(this.getCompetitionFrame());
            winterCompetition.addCompetitor(ws);

            //
            ws.setGender(winterCompetition.getGender());
        }
        catch(IllegalArgumentException e) {
        	JOptionPane.showMessageDialog(this, "Competitor does not fit to competition! Choose another competitor."); 
        	return;
        }

        RacerSiNumber=racernumber;//<---user pike
        Ucolor=color;////<---user pike

        competitors.add(ws);
        competitorsImages[competitorsNumber] = new ImageIcon(new ImageIcon("icons/"+competition+gender+color+".png").getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
        competitorsNumber++;
        ws.setCompetition(this.getCompetition());
        ws.setPic(new ImageIcon(new ImageIcon("icons/"+competition+gender+color+".png").getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT)));
        competitionFrame.updateFrame();

    }
    
    
    
    public void startRace(){
        competitionStarted = true;
        this.myTime=new Time(0,0,0);
             
        try {
            winterCompetition.startCompetition();
        } catch (InterruptedException ex) {
        	ex.printStackTrace();
        }
    }
    
    
    
    public void showInfo(){
        if (infoTable != null)
            infoTable.dispose();
        this.infoTable = new InfoTable(RaceStateArr, this.winterCompetition.getFinishedCompetitors(), competitorsNumber, this.Curr, this.s);//open new table if active

    }


    
    public void setArenaLength(int arenaLength){
        this.arenaLength = arenaLength;
    }
    
    public int getArenaLength(){
        return arenaLength;
    }
          
    
    public void setArenaWidth(int arenaWidth){
        this.arenaWidth = arenaWidth;
    }
    
    public int getArenaWidth(){
        return arenaWidth; 
    }
    
    
    public String getWeather(){
        return this.weather;
    }
    
    public String getSurface(){
        return this.surface;
    }   
    
    public String getDiscipline() {
    	return discipline;
    }
    
    public String getLeague() {
    	return league;
    }
    
    public String getGender() {
    	return gender;
    }
    
    public void setMaxCompetitors(int maxCompetitors){
        this.maxCompetitors = maxCompetitors;
    }
    
    public int getMaxCompetitors(){
        return this.maxCompetitors;
    }
    
    public boolean noArena(){
        return arena == null;
    }
    
    public boolean fullArena(){
        return competitorsNumber == maxCompetitors;
    }
    
    
    public boolean noCompetitors(){
        return competitorsNumber == 0;
    }
    

    public void setCompetitionFrame(CompetitionFrame competitionFrame){
        this.competitionFrame = competitionFrame;
    }

    public CompetitionFrame getCompetitionFrame() {
        return competitionFrame;
    }

    public boolean isCompetitionStarted(){
        return this.competitionStarted;
    }
    
    public boolean isCompetitionFinished(){
        return this.competitionFinished;
    }
     
    public String getCompetition() {
    	return competition;
    }
    


    public void createSkiCompetitionBuilder(String competition, String discipline, String league, String gender,int NumOfComptitor) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        //this function creates ski competitior by prototype
        //call to addcopetitor in loop--as amunt of NumOfcopetitior

        lock.lock();
        competitors = new ArrayList<>();
        competitorsImages = new ImageIcon[NumOfComptitor];
        competitorsNumber = 0;


        //this.arena=new WinterArena();
        skiCompetitionBuilder=new SkiCompetitionBuilderI(NumOfComptitor,arena);

        competitionEngineer=new CompetitionEngineer(skiCompetitionBuilder);

        competitionEngineer.MakeComptition();
        this.winterCompetition= competitionEngineer.getCompetition();//-->skicompetition object

        //add to competition copmtitior by default
        Skier skier1=new Skier();
        MyClone myClone=new MyClone();

        WinterSportsman competitor1=null;
       competitor1= (WinterSportsman) myClone.getclone(skier1);

        for(int i=0;i<NumOfComptitor;i++){
            competitor1= new Skier((Skier) myClone.getclone(skier1));
            competitor1.setName("R"+i);
            try {
                winterCompetition.addGuiObserver(this.getCompetitionFrame());
                winterCompetition.addCompetitor(competitor1);
                //this.winterCompetition.getActiveCompetitors().add(competitor1);
            }
            catch(IllegalArgumentException e) {
                JOptionPane.showMessageDialog(this, "Competitor does not fit to competition! Choose another competitor.");
                return;
            }

            String color="Blue";
            competitorsImages[competitorsNumber] = new ImageIcon(new ImageIcon("icons/"+competition+gender+color+".png").getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
            competitorsNumber++;


            competitor1.setPic(new ImageIcon(new ImageIcon("icons/"+competition+gender+color+".png").getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT)));
            competitors.add(competitor1);
            this.picLabel1.add(competitor1.getRpic());

            competitionFrame.updateFrame();

        }
        lock.unlock();

    }



    public void PrototypeCopy(String Racernumber,String color ) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{

        //take the last competitor that select
        WinterSportsman winterSportsmanToclone=competitors.get(competitors.size()-1);

        MyClone myClone=new MyClone();
        WinterSportsman ws=null;
        ws= (WinterSportsman) myClone.getclone(winterSportsmanToclone);
        ws.setRacernumber(Integer.parseInt(Racernumber));

        try {

            winterCompetition.addCompetitor(ws);
        }
        catch(IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, "Competitor does not fit to competition! Choose another competitor.");
            return;
        }

        competitors.add(ws);

        competitorsImages[competitorsNumber] = new ImageIcon(new ImageIcon("icons/"+competition+gender+color+".png").getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
        competitorsNumber++;
        ws.setPic(new ImageIcon(new ImageIcon("icons/"+competition+gender+color+".png").getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT)));
        ws.setCompetition(this.getCompetition());
        ws.setCompetition(gender);
        ws.setCompetition(competition);
        competitionFrame.updateFrame();

    }


    public void Decoratoraddition(String color, double acceleration){


        WinterSportsman ws=competitors.get(competitorsNumber-1);
        ///Decorator
        WinterSportsman temp=competitors.get(competitorsNumber-1);
        //temp 2 is type of IWintersportman
        IWinterSportsman temp2;
        temp2=new ColorSportsman(temp,color);//get new competitor with new color
        temp=temp2.MakeSportman();//make sportmans return wintersportmans

        temp2=new SpeedSportsman(temp,acceleration);
        temp=temp2.MakeSportman();
        temp.DSetNewColorPic(color);
        UpdateRacer();

    }


    public synchronized void UpdateRacer(){
        //after each move update
         this.picLabel1.removeAll();

        for (int i=0; i<competitorsNumber; i++){
            WinterSportsman winterSportsman=competitors.get(i);

           winterSportsman.getRpic().setLocation((int) winterSportsman.getLocation().getY()+5 ,(int) winterSportsman.getLocation().getX());
           winterSportsman.getRpic().setSize(70,70);
           this.picLabel1.add(winterSportsman.getRpic());
           this.picLabel1.repaint();
           this.picLabel1.updateUI();

        }

    }

    public  void RacerFinish(WinterSportsman o){
        lock.lock();
                this.picLabel1.remove(o.getRpic());
                o.getRpic().setLocation((int) o.getLocation().getY()+5 ,700);
                o.getRpic().setSize(70,70);
                this.picLabel1.add(o.getRpic());
                this.picLabel1.repaint();
                this.picLabel1.updateUI();


                this.winterCompetition.getFinishedCompetitors().add(o);
                //this.winterCompetition.getActiveCompetitors().remove(o);
        lock.unlock();
    }

    public Vector<IRaceState> bulid_IRaceState(ArrayList<Competitor> ActiveRacer){

        lock.lock();
        Vector<IRaceState> RaceStateList= new Vector<>();

        ArrayList<IRaceState> Statearr=new ArrayList<>(ActiveRacer);


        if(!Statearr.isEmpty()){
            for(IRaceState com:Statearr){

                if(com.getState().equals("Active")){
                    RaceStateList.add(new Active((WinterSportsman)com));
                }
                else if(com.getState().equals("Injured")){
                    RaceStateList.add(new Injured((WinterSportsman)com));
                }
                else if(com.getState().equals("Disabled")){
                    RaceStateList.add(new Disabled((WinterSportsman)com));
                }
                else if(com.getState().equals("Completed")){
                    RaceStateList.add(new Completed((WinterSportsman)com));
                }

            }
        }
        lock.unlock();

        return new Vector<IRaceState>(RaceStateList);
    }

    /***
     * Holds a set of competitors' situations according to their situation.
     * static
     * @author Bar Lupo 311303416, Amit Hayun 203716659;
     */
    public void UpdateRacerArr(){
        lock.lock();
        RaceStateArr=(this.bulid_IRaceState(this.winterCompetition.getActiveCompetitors()));
        FinishRacerstate=(this.bulid_IRaceState(this.winterCompetition.getFinishedCompetitors()));
        lock.unlock();
    }

    public  int FindMach(WinterSportsman ws,ArrayList<Competitor> ActiveRacer){
        int i=0;
        Competitor com=(Competitor)ws;
        for(Competitor c:ActiveRacer){
            i++;
            if(c.equals(com)){
                Competitor temp=c;
                return i;
            }
            else
                continue;
        }
        return -1;
    }
    /***
     * This function removes a competitor who has had an accident and is disabled
     * @author Bar Lupo 311303416, Amit Hayun 203716659;
     */
    //picupdate
    public void updateRacesateDisabled(WinterSportsman ws){

        this.picLabel1.remove(ws.getRpic());
        this.picLabel1.repaint();
        this.updateUI();

    }


    /***
     * This function builds a situation according to the competitor's situation
     * @author Bar Lupo 311303416, Amit Hayun 203716659;
     */
    public  void ChngeState(ArrayList<Object> s){
        lock.lock();
        this.UpdateRacerArr();
        this.s=new ArrayList<>(s);
        String action="";
        utilities.Point currPoint;
        WinterSportsman currWs;

        //for state var 1,2,3
        String state="";
        LocalDateTime Timeofaccident;
        int TimeToHeal=0;

        action=(String)s.get(0);
        currPoint=(Point) s.get(1);

        currWs=(WinterSportsman)s.get(2);
        state=(String)s.get(3);
        Timeofaccident=(LocalDateTime)s.get(4);
        TimeToHeal=(int)s.get(5);
        state=currWs.getState();
        switch (state) {
                case "Active":
                    this.Curr=currWs;
                    break;
                case "Completed":
                    //this.RacerFinish(currWs);
                    if(this.infoTable!=null){
                        this.infoTable.dispose();
                    }
                    this.infoTable = new InfoTable(RaceStateArr, this.winterCompetition.getFinishedCompetitors(), competitorsNumber, currWs, s);//open new table if active
                    JOptionPane.showMessageDialog(this, currWs.getName()+" competitor Completed race");
                    break;
                case "Injured":
                    this.Curr=currWs;
                    int time = TimeToHeal;
                    this.Curr.setState("Active");
                    JOptionPane.showMessageDialog(this, currWs.getName()+" A competitor was injured\n time of injure" + Timeofaccident.toString());
                    break;
                case "Disabled":
                    this.Curr=currWs;
                    JOptionPane.showMessageDialog(this, currWs.getName()+" disabled competitor can not compete.\n time of accident"+
                            Timeofaccident.toString() + "-->RIP");
                    break;
            }

        lock.unlock();

    }




    
}
