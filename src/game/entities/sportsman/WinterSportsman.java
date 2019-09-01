package game.entities.sportsman;
/**
 * @author Bar Lupo 311303416, Amit Hayun 203716659;
 */

import game.arena.IArena;
import game.competition.Competitor;
import game.enums.Discipline;
import game.enums.Gender;
import game.enums.League;
import gui.State.IRaceState;
import utilities.Point;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;


public class WinterSportsman extends Sportsman implements Competitor, IRaceState,IWinterSportsman{
    private Discipline discipline;
    private Point finish;
    private IArena arena;



    //---------------------------
    protected ImageIcon pic;
    protected JLabel Rpic;

    protected String color="Red";
    public String competition;
    public String CurrGender;
    protected String state;
    protected String Finish_Race="NO";



    public  ImageIcon getPic() {
        return pic;
    }

    public void setPic(ImageIcon pic) {
        this.pic = pic;
        this.Rpic=new JLabel(this.pic);

      //  System.out.println("gen="+Gen+" color="+color+" competiton="+competition);
        //pic= new ImageIcon(new ImageIcon("icons/"+competition+Gen+color+".png").getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));

    }

    public JLabel getRpic() {
        return Rpic;
    }



    public WinterSportsman(){
        super();
        this.discipline = Discipline.SLALOM;
    }
    public WinterSportsman(WinterSportsman winterSportsman){
        super(winterSportsman.getName(), winterSportsman.getAge(),winterSportsman.getGender(), winterSportsman.getAcceleration(), winterSportsman.getMaxSpeed());
        this.discipline=winterSportsman.discipline;
        this.setState("Active");
    }
    public WinterSportsman(Competitor com){
        this((WinterSportsman)com);
    }

    public WinterSportsman(String name, double age, Gender gender, double acceleration, double maxSpeed, Discipline discipline) {
        super(name, age, gender, acceleration, maxSpeed);
        this.discipline = discipline;
    }
    @Override
    public void initRace() {
        this.setLocation(new Point(0,this.getLocation().getY()));
    }
    
    @Override
    public void initRace(Point p, Point f, IArena arena) {
        this.setLocation(p); 
        this.finish = f;
        this.arena = arena;
    }

    @Override
    public String toString() {
        return  getName() + " " +getAge() + " " +getAcceleration()+ " " +getGender()+ " " +getDiscipline()+ " " +getRacernumber()+ " "+getState();
    }

    //region Getters & setters
    public Discipline getDiscipline() {
        return discipline;
    }

    @Override
    public double getAcceleration() {
        return super.getAcceleration()+ League.calcAccelerationBonus(this.getAge());
    }
    //endregion


    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public void setFinish(Point finish) {
        this.finish = finish;
    }

    public void setArena(IArena arena) {
        this.arena = arena;
    }

    private boolean competitionInProgress() {
		boolean res = getLocation().getX() < finish.getX();
		Point p = getLocation();
		if (!res) setLocation(new Point(finish.getX(),p.getY()));
		return res;
	}

    /**
     * Function Destiny drags the competitor's
     * situation after each step and updates the arena in which it is watching
     * When another competitor is injured his speed and acceleration decreases accordingly
     * @author Bar Lupo 311303416, Amit Hayun 203716659;
     * @return  ArrayList<Object>
     */
    public synchronized ArrayList<Object> destiny(){
        ArrayList<Object> o=new ArrayList<>();

        String destinystate="";
        int time=0;
        int temp;
        Random random=new Random();
        temp=random.nextInt(4)+1;
        time=random.nextInt(20);//number of second in case of injure
        LocalDateTime myDateObj = LocalDateTime.now();

        switch(temp){
            case 1:
                destinystate="Injured";
                this.state=new String("Injured");
                o.add(destinystate);
                o.add(myDateObj);
                o.add(time);
                break;
            case 2:
                destinystate="Active";
                this.state=new String("Active");
                o.add(destinystate);
                o.add(myDateObj);
                time=0;
                o.add(time);
                break;

            case 3:
                destinystate="Disabled";
                this.state=new String("Disabled");
                this.setPic(null);
                o.add(destinystate);
                o.add(myDateObj);
                time=-1;
                o.add(time);
                break;

            default:
                destinystate="Active";
                this.state=new String("Active");
                o.add(destinystate);
                o.add(myDateObj);
                time=0;
                o.add(time);
                break;
        }

        return o;

    }
    
	
	@Override
	public void run() {

        ArrayList<Object> o=new ArrayList<>();
        ArrayList<Object> disntyData=new ArrayList<>();

        String curr="move";
        Point p=this.getLocation();
        o.add(curr);
        o.add(p);
        o.add(this);
        this.state="Active";//start race all racer are active

		while (competitionInProgress()) {
            Random random=new Random();
            int x=random.nextInt(85);
            move(arena.getFriction());

            if(!this.getState().isEmpty()) {
                while(this.getState().equals("Disabled")) {
                    //state=(String)s.get(3);
                    setChanged();
                    String s="disable again";
                    notifyObservers(s);
                }
            }

			//here we check if the racer is need to use destiny func
            if(x<5){
                disntyData=destiny();
            }
			else
            {
                LocalDateTime localTime=LocalDateTime.now();
                disntyData.add(0,"Active");
                disntyData.add(1,  localTime);
                disntyData.add(2,0);
            }


            this.setState((String)disntyData.get(0));

            o.add(disntyData.get(0));
            o.add(disntyData.get(1));
            o.add((disntyData.get(2)));


            if(this.getState().equals("Injured")){//if the state is Injured we drop speed/2 and acc to 10;
                this.setSpeed(this.getSpeed()/2);
                this.setAcceleration(10);

            }

            setChanged();
            notifyObservers(o);
            try {
                   Thread.sleep(300);
            } catch (InterruptedException ex) {
                   ex.printStackTrace();
            }
		}

        this.setState("Completed");
		this.setFinish("YES");
        o.set(0,"Completed");
		setChanged();//racee is over notiffy to gui
		notifyObservers(o);

	}

    @Override
    public WinterSportsman MakeCopy() {
        WinterSportsman ws=null;
        try{
            ws=(WinterSportsman)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return ws;
    }

    public String getColor() {
        return color;
    }



    //IwinterSportsman
    public void Changeacceleration(double Addacceleration) {
        super.setAcceleration(this.getAcceleration()+Addacceleration);
    }
    @Override
    public WinterSportsman MakeSportman() {
        return this;
    }

    public void DSetNewColorPic(String color){


        this.setColor(color);
        //this.setPic();

        String Gen=this.getGender().name();//take gender as string
        if(Gen.equals("MALE")){
            Gen="Male";
        }
        if(Gen.equals("FEMALE")){
            Gen="Female";
        }
       //System.out.println("gen="+Gen+" color="+color+" competiton="+competition);
        if(competition==null){
            this.setCompetition("Ski");
        }

        ImageIcon icon1=new ImageIcon(new ImageIcon("icons/"+this.getCompetition()+super.getGender().name()+this.color+".png").getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
        this.setPic(icon1);


    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCompetition() {
        return competition;
    }

    public void setCompetition(String competition) {
        this.competition = competition;
    }

    public String getState() {
        return state;
    }

    @Override
    public String AllData() {
        return this.toString();
    }

    @Override
    public String getFinish() {
        return this.Finish_Race;
    }
    public void setFinish(String f) {
        this.Finish_Race=f;
    }

    public void setState(String state) {
        this.state = state;
    }


}
