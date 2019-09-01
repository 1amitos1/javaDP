package gui;
/**
 This class builds the interface of a competition.
 Listens to the action and activates the arena accordingly
 * @version 1.0
 * @author Bar Lupo 311303416, Amit Hayun 203716659;
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;


public class ControlsPanel extends JPanel implements ActionListener{
    private final JTextField tfArenaLength;
    private final JTextField tfMaxCompetitors;
    private final JTextField tfCompetitorName;
    private final JTextField tfMaxSpeed;
    private final JTextField tfAcceleration;
    private final JTextField tfAge;
    
    private final JComboBox<String> cmbArenaSurface;
    private final JComboBox<String> cmbArenaWeather;
    private final JComboBox<String> cmbCompetition;
    private final JComboBox<String> cmbDiscipline;
    private final JComboBox<String> cmbLeague;
    private final JComboBox<String> cmbGender;
    
    private ArenaPanel arenaPanel = null;

    //add new JCombox for arena type
    private JComboBox<String> cmbArenaType;

    //add Button and Jtext for creating arena
    private JTextField tBuildComptitor;
    //add button Combobox for color-->4 red,blue green black  option,number of competitor and 2 buttons prototype and decreator
    private JComboBox<String> cmRacerNumber;
    private JComboBox<String> cmbColor;
    //add obj Arraylist to contain data from addcompetitor choose
    private static boolean flag=false;



    
    public ControlsPanel(ArenaPanel arenaPanel) {
        this.arenaPanel = arenaPanel;
        setLayout(null);
        setPreferredSize(new Dimension(165,arenaPanel.getArenaLength()));

        
        JLabel l1 = new JLabel("<HTML><font color='blue'><U>BUILD ARENA</U></font></HTML>");
        add(l1);
        l1.setLocation(10,0);
        l1.setSize(145, 20);
        
        JLabel l2 = new JLabel("Arena length");
        l2.setLocation(10,20);
        l2.setSize(145, 10);
        add(l2);
        
        tfArenaLength = new JTextField(""+arenaPanel.getArenaLength());
        tfArenaLength.setLocation(10,32);
        tfArenaLength.setSize(145, 20);
        add(tfArenaLength);   
        
        
        JLabel l4 = new JLabel("Snow surface");
        l4.setLocation(10,54);
        l4.setSize(170, 15);
        add(l4);
        
        cmbArenaSurface = new JComboBox<>();       
        cmbArenaSurface.addItem("Powder");
        cmbArenaSurface.addItem("Crud");
        cmbArenaSurface.addItem("Ice");  
        
        if (arenaPanel.getSurface() != null)
        	cmbArenaSurface.setSelectedItem(arenaPanel.getSurface()); 
        else
        	cmbArenaSurface.setSelectedItem("Powder");
        
        add(cmbArenaSurface);
        cmbArenaSurface.setLocation(10,70);
        cmbArenaSurface.setSize(145,20);
        
        JLabel l5 = new JLabel("Weather condition");
        l5.setLocation(10,92);
        l5.setSize(170, 15);
        add(l5);
        
        cmbArenaWeather = new JComboBox<>();       
        cmbArenaWeather.addItem("Sunny");
        cmbArenaWeather.addItem("Cloudy");
        cmbArenaWeather.addItem("Stormy");  
        
        if (arenaPanel.getWeather() != null)
        	cmbArenaWeather.setSelectedItem(arenaPanel.getWeather()); 
        else
        	cmbArenaWeather.setSelectedItem("Sunny");
        
        add(cmbArenaWeather);
        cmbArenaWeather.setLocation(10,105);
        cmbArenaWeather.setSize(145,20);


        //add arena type
          JLabel l_type = new JLabel("Arena type");
          l_type.setLocation(10,129);
          l_type.setSize(145, 10);
          add(l_type);

          cmbArenaType=new JComboBox<>();
          cmbArenaType.addItem("winter arena");
          cmbArenaType.addItem("summer arena");
          cmbArenaType.setLocation(10,142);
          cmbArenaType.setSize(145,20);
          add(cmbArenaType);



        JButton buildArenaBut = new JButton("Build arena");
        buildArenaBut.setLocation(10,165);
        buildArenaBut.setSize(145, 25);
        buildArenaBut.addActionListener(this);
        add(buildArenaBut);
        
        JSeparator sep = new JSeparator(SwingConstants.HORIZONTAL);
        sep.setLocation(0,195);
        sep.setSize(170, 10);
        add(sep);
        
        
        //-------------------------------------------------------------------
        
        JLabel l6 = new JLabel("<HTML><font color='blue'><U>CREATE COMPETITION</U></font></HTML>");
        add(l6);
        l6.setLocation(10,194);
        l6.setSize(145, 20);
        
        JLabel l7 = new JLabel("Choose competition");
        l7.setLocation(10,215);
        l7.setSize(170, 15);
        add(l7);
        
        cmbCompetition = new JComboBox<>();       
        cmbCompetition.addItem("Ski");
        cmbCompetition.addItem("Snowboard");         
        if (arenaPanel.getCompetition() != null)
        	cmbCompetition.setSelectedItem(arenaPanel.getCompetition()); 
        else
        	cmbCompetition.setSelectedItem("Ski");   
        add(cmbCompetition);
        cmbCompetition.setLocation(10,230);
        cmbCompetition.setSize(145,20);
        
        
        JLabel l8 = new JLabel("Max competitors number");
        l8.setLocation(10,252);
        l8.setSize(170, 15);
        add(l8);
        
        tfMaxCompetitors = new JTextField(""+arenaPanel.getMaxCompetitors());
        tfMaxCompetitors.setLocation(10,268);
        tfMaxCompetitors.setSize(145, 20);
        add(tfMaxCompetitors);
        
        
        JLabel l9 = new JLabel("Discipline");
        l9.setLocation(10,287);
        l9.setSize(170, 15);
        add(l9);
        
        cmbDiscipline = new JComboBox<>();       
        cmbDiscipline.addItem("Slalom");
        cmbDiscipline.addItem("Giant-Slalom");
        cmbDiscipline.addItem("Downhill");
        cmbDiscipline.addItem("Freestyle");
        if (arenaPanel.getCompetition() != null)
        	cmbDiscipline.setSelectedItem(arenaPanel.getDiscipline()); 
        else
        	cmbDiscipline.setSelectedItem("Slalom"); 
        add(cmbDiscipline);
        cmbDiscipline.setLocation(10,302);
        cmbDiscipline.setSize(145,20);
        
        
        JLabel l10 = new JLabel("League");
        l10.setLocation(10,322);
        l10.setSize(170, 15);
        add(l10);
        
        cmbLeague = new JComboBox<>();       
        cmbLeague.addItem("Junior");
        cmbLeague.addItem("Adult");
        cmbLeague.addItem("Senior");
        if (arenaPanel.getCompetition() != null)
        	cmbLeague.setSelectedItem(arenaPanel.getLeague()); 
        else
        	cmbLeague.setSelectedItem("Junior");
        add(cmbLeague);
        cmbLeague.setLocation(10,339);
        cmbLeague.setSize(145,20);
        
        
        JLabel l11 = new JLabel("Gender");
        l11.setLocation(10,360);
        l11.setSize(170, 15);
        add(l11);
        
        cmbGender = new JComboBox<>();       
        cmbGender.addItem("Male");
        cmbGender.addItem("Female");
        if (arenaPanel.getCompetition() != null)
        	cmbGender.setSelectedItem(arenaPanel.getGender()); 
        else
        	cmbGender.setSelectedItem("Male");
        add(cmbGender);
        cmbGender.setLocation(10,375);
        cmbGender.setSize(145,20);


        JLabel Lbuilder = new JLabel("Enter multiple competitors");
        Lbuilder.setLocation(10,395);
        Lbuilder.setSize(170,15);
        add(Lbuilder);

        tBuildComptitor=new JTextField(""+10);
        tBuildComptitor.setLocation(10,410);
        tBuildComptitor.setSize(145,20);
        add(tBuildComptitor);

        JButton createSkiCompetitionBuilder=new JButton("SkiCompetitionBuilderI");
        createSkiCompetitionBuilder.setLocation(10,430);
        createSkiCompetitionBuilder.setSize(145,20);
        createSkiCompetitionBuilder.addActionListener(this);
        add(createSkiCompetitionBuilder);


        JButton createCompetitionBut = new JButton("Create competition");
        createCompetitionBut.setLocation(10,453);
        createCompetitionBut.setSize(145, 20);
        createCompetitionBut.addActionListener(this);
        add(createCompetitionBut);
        
        JSeparator sep2 = new JSeparator(SwingConstants.HORIZONTAL);
        sep2.setLocation(0,475);
        sep2.setSize(170, 10);
        add(sep2);
        
        //-------------------------------------------------------------------------------
        
        JLabel l12 = new JLabel("<HTML><font color='blue'><U>ADD COMPETITOR</U></font></HTML>");
        add(l12);
        l12.setLocation(10,472);
        l12.setSize(145, 20);
        
        JLabel l13 = new JLabel("Name");
        l13.setLocation(10,489);
        l13.setSize(170, 15);
        add(l13);
        
        tfCompetitorName = new JTextField("");
        tfCompetitorName.setLocation(10,501);
        tfCompetitorName.setSize(145, 17);
        add(tfCompetitorName);
        
        JLabel l14 = new JLabel("Age");
        l14.setLocation(10,519);
        l14.setSize(150, 15);
        add(l14);
        
        tfAge = new JTextField("");
        tfAge.setLocation(10,535);
        tfAge.setSize(145, 17);
        add(tfAge);
           
        JLabel l15 = new JLabel("Max speed");
        l15.setLocation(10,550);
        l15.setSize(150, 15);
        add(l15);
        
        tfMaxSpeed = new JTextField("");
        tfMaxSpeed.setLocation(10,564);
        tfMaxSpeed.setSize(145, 17);
        add(tfMaxSpeed);
        
        JLabel l16 = new JLabel("Acceleration");
        l16.setLocation(10,580);
        l16.setSize(150, 15);
        add(l16);
        
        tfAcceleration = new JTextField("");
        tfAcceleration.setLocation(10,595);
        tfAcceleration.setSize(145, 17);
        add(tfAcceleration);


        JLabel lnumber=new JLabel("Number of racing player");
        lnumber.setLocation(10,610);
        lnumber.setSize(150, 15);
        add(lnumber);


        cmRacerNumber=new JComboBox<String>();
        for(int j=0;j<20;j++){
            String temp=Integer.toString(j);
            cmRacerNumber.addItem(temp);
        }
        cmRacerNumber.setLocation(10,625);
        cmRacerNumber.setSize(145,17);
        cmRacerNumber.addActionListener(this);
        add(cmRacerNumber);


        JLabel lcolor=new JLabel("color");
        lcolor.setLocation(10,643);
        lcolor.setSize(150, 15);
        add(lcolor);
        cmbColor=new JComboBox<>();
        cmbColor.addItem("Red");
        cmbColor.addItem("Blue");
        cmbColor.addItem("Green");
        cmbColor.addItem("Black");
        cmbColor.addActionListener(this);
        cmbColor.setLocation(10,657);
        cmbColor.setSize(145,17);
        add(cmbColor);



        JButton PrototypeBut = new JButton("Prototype");
        PrototypeBut.setLocation(10,676);
        PrototypeBut.setSize(145, 20);
        PrototypeBut.addActionListener(this);
        add(PrototypeBut);


        JButton DecoratorBut = new JButton("Decorator");
        DecoratorBut.setLocation(10,696);
        DecoratorBut.setSize(145, 20);
        DecoratorBut.addActionListener(this);
        add( DecoratorBut);












        
        JButton addCompetitorBut = new JButton("Add competitor");
        addCompetitorBut.setLocation(10,716);
        addCompetitorBut.setSize(145, 20);
        addCompetitorBut.addActionListener(this);
        add(addCompetitorBut);
        
        JSeparator sep3 = new JSeparator(SwingConstants.HORIZONTAL);
        sep3.setLocation(0,738);
        sep3.setSize(170, 10);
        add(sep3);
      
        //---------------------------------------------
       
        JButton startCompetitionBut = new JButton("Start competition");
        startCompetitionBut.setLocation(10,740);
        startCompetitionBut.setSize(145, 20);
        startCompetitionBut.addActionListener(this);
        add(startCompetitionBut);
        
        JButton printInfoBut = new JButton("Show info");
        printInfoBut.setLocation(10,760);
        printInfoBut.setSize(145, 20);
        printInfoBut.addActionListener(this);
        add(printInfoBut);   
    }
    
    
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
       // System.out.println(e.getActionCommand().toString());
        switch (e.getActionCommand()) {
				case "Build arena":  
                    int arenaLength = arenaPanel.getArenaLength();
                    if (arenaPanel.isCompetitionStarted() && !arenaPanel.isCompetitionFinished()){
                        JOptionPane.showMessageDialog(arenaPanel, "ICompetition started! Please wait.");
                        return;
                    }
                    try{
                        arenaLength = Integer.parseInt(tfArenaLength.getText());
                        arenaPanel.setArenaLength(arenaLength);
                        if (arenaLength<700 || arenaLength>900) throw new Exception();
                    } catch (Exception ex){
                        JOptionPane.showMessageDialog(arenaPanel, "Invalid input values! Please try again.");
                        return;
                    }                  
                                                        
                    arenaPanel.buildArena((String)cmbArenaSurface.getSelectedItem(),(String)cmbArenaWeather.getSelectedItem(),(String)cmbArenaType.getSelectedItem());
                    break;

            case "SkiCompetitionBuilderI":
                    flag=true;
                    int maxCompetitors = arenaPanel.getMaxCompetitors();
                    int NumOfComptitor=Integer.parseInt(tBuildComptitor.getText());


                    if (arenaPanel.isCompetitionStarted() && !arenaPanel.isCompetitionFinished()){
                        JOptionPane.showMessageDialog(arenaPanel, "ICompetition started! Please wait.");
                        return;
                    }

                    if (arenaPanel.noArena()){
                        JOptionPane.showMessageDialog(arenaPanel, "Please build arena first!");
                        return;
                    }

                    try{

                        arenaPanel.setMaxCompetitors(NumOfComptitor);
                        if (NumOfComptitor<=0 || NumOfComptitor > 20) throw new Exception();
                    } catch (Exception ex){
                        JOptionPane.showMessageDialog(arenaPanel, "Invalid input values! Please try again.");
                        return;
                    }

                    try {// change to createSkiCompetitionBuilder

                        arenaPanel.createSkiCompetitionBuilder((String)cmbCompetition.getSelectedItem(),(String)cmbDiscipline.getSelectedItem(),
                                (String)cmbLeague.getSelectedItem(),(String)cmbGender.getSelectedItem(),NumOfComptitor);
                    } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
                        e1.printStackTrace();
                    }

                    break;

                    
				case "Create competition":
					 maxCompetitors = arenaPanel.getMaxCompetitors();
                    if (arenaPanel.isCompetitionStarted() && !arenaPanel.isCompetitionFinished()){
                        JOptionPane.showMessageDialog(arenaPanel, "ICompetition started! Please wait.");
                        return;
                    }
                    
                    if (arenaPanel.noArena()){
                        JOptionPane.showMessageDialog(arenaPanel, "Please build arena first!");
                        return;
                    } 
					
                    try{
                        maxCompetitors = Integer.parseInt(tfMaxCompetitors.getText());
                        arenaPanel.setMaxCompetitors(maxCompetitors); 
                        if (maxCompetitors<=0 || maxCompetitors > 20) throw new Exception();
                    } catch (Exception ex){
                        JOptionPane.showMessageDialog(arenaPanel, "Invalid input values! Please try again.");
                        return;
                    }
                    
					try {
						arenaPanel.createCompetition((String)cmbCompetition.getSelectedItem(),(String)cmbDiscipline.getSelectedItem(),
								(String)cmbLeague.getSelectedItem(),(String)cmbGender.getSelectedItem());
					} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		                    
					break;
                    
               case "Add competitor":                 
                    if (arenaPanel.isCompetitionFinished()){
                        JOptionPane.showMessageDialog(arenaPanel, "ICompetition finished! Please create new competition.");
                        return;
                    }
                    if (arenaPanel.isCompetitionStarted()){
                        JOptionPane.showMessageDialog(arenaPanel, "ICompetition started! No competitors can be added.");
                        return;
                    }
                    if (arenaPanel.noArena()){
                        JOptionPane.showMessageDialog(arenaPanel, "Please build arena first!");
                        return;
                    }   
                    if (arenaPanel.getCompetition() == null) {
                        JOptionPane.showMessageDialog(arenaPanel, "Please create competition first!");
                        return;
                    }
                    if (arenaPanel.fullArena()){
                        JOptionPane.showMessageDialog(arenaPanel, "No more competitors can be added!");
                        return;
                    }
                    String name;
                    double age;
                    double maxSpeed;
                    double acceleration;

                    int racernumber=0;
                    String color;
                    int index=Integer.parseInt((String) cmRacerNumber.getSelectedItem());

                    try {
                        name = tfCompetitorName.getText();
                        age = Double.parseDouble(tfAge.getText());
                        maxSpeed = Double.parseDouble(tfMaxSpeed.getText());
                        acceleration = Double.parseDouble(tfAcceleration.getText());

                        racernumber=Integer.parseInt((String) cmRacerNumber.getSelectedItem());
                        color=(String) cmbColor.getSelectedItem();

                        if (name.isEmpty() || maxSpeed <=0 || acceleration <=0 || age<=0) throw new Exception();
                    }
                    catch(Exception ex){
                        JOptionPane.showMessageDialog(arenaPanel, "Invalid input values! Please try again.");
                        return;
                    }
					try {

						arenaPanel.addCompetitor(name, age, maxSpeed, acceleration,racernumber,color);
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (NoSuchMethodException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SecurityException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InstantiationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IllegalAccessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IllegalArgumentException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InvocationTargetException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}       
                    break;

               case "Start competition":
//                    System.out.println("1->" + arenaPanel.noArena()+ "2->"+arenaPanel.getCompetition()+"3->"+arenaPanel.noCompetitors());
                    if(flag){

                        arenaPanel.startRace();
                    }
                    else if (arenaPanel.noArena() || arenaPanel.getCompetition()==null || arenaPanel.noCompetitors()){
                        JOptionPane.showMessageDialog(arenaPanel, "Please build arena, create competition and add competitors!");
                        return;
                    }
                    else if (arenaPanel.isCompetitionFinished()){
                        JOptionPane.showMessageDialog(arenaPanel, "ICompetition finished! Please create a new competition and add competitors.");
                        return;
                    }
                    else if (arenaPanel.isCompetitionStarted()){
                        JOptionPane.showMessageDialog(arenaPanel, "ICompetition already started!");
                        return;
                    }
                    else
                      arenaPanel.startRace();
                    break;
                    
               case "Show info":
                    if(flag){
                        arenaPanel.showInfo();
                    }
                    else if (arenaPanel.noArena() || arenaPanel.getCompetition()==null || arenaPanel.noCompetitors()){
                        JOptionPane.showMessageDialog(arenaPanel, "Please build arena, create competition and add competitors!");
                        return;
                    } 
                    else
                       arenaPanel.showInfo();
                    break;

               case "Prototype":
                   try {
                       arenaPanel.PrototypeCopy((String)cmRacerNumber.getSelectedItem(),(String) cmbColor.getSelectedItem());
                   } catch (ClassNotFoundException e1) {
                       e1.printStackTrace();
                   } catch (NoSuchMethodException e1) {
                       e1.printStackTrace();
                   } catch (InstantiationException e1) {
                       e1.printStackTrace();
                   } catch (IllegalAccessException e1) {
                       e1.printStackTrace();
                   } catch (InvocationTargetException e1) {
                       e1.printStackTrace();
                   }



                   break;

               case "Decorator":
                   String s="Red";
                   s = (String) cmbColor.getSelectedItem();
                   double accBouns=0.0;
                   accBouns=Double.parseDouble(tfAcceleration.getText());
                   if(s==null) {
                       System.out.println("null");
                       s = "Red";
                   }
                   arenaPanel.Decoratoraddition(s,Double.parseDouble(tfAcceleration.getText()));
                   break;

        }
    }

}
