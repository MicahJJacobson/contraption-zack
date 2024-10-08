import java.net.*;
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.text.*;
import javafx.stage.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.util.*;
import javafx.scene.paint.*;
import javafx.geometry.*;
import javafx.scene.image.*;
import java.io.*;
import java.text.*;
import java.lang.*;
import javafx.event.*;
import javafx.scene.canvas.*;
import javafx.scene.input.*;
import javafx.animation.*;

public class Main extends Application
{

   AnimationHandler ta = new AnimationHandler();
   ComboBox menu = new ComboBox();
   StackPane sp = new StackPane();
   GridPane gp = new GridPane(); 
   Canvas theCanvas = new Canvas(1368,768);
   GraphicsContext gc = theCanvas.getGraphicsContext2D(); 
   ArrayList<Object> saveList = new ArrayList<Object>();
   ArrayList<ArrayList<AbstractMech>> mechs = new ArrayList<ArrayList<AbstractMech>>();
   //this doesn't need to be saved because it is only on one level
   //also, the save files only have to stay until the program is shut down
   ArrayList<Door> doormechs = new ArrayList<Door>();
   //index 0 is nextULevel
   //index 1 is nextULevel2
   //index 2 is nextLLevel
   //index 3 is prevDLevel
   //index 4 is prevRLevel
   //index 5 is nextRLevel
   ArrayList<ArrayList<levelSwitch>> levelSwitches = new ArrayList<ArrayList<levelSwitch>>();
   //index 0 is Down
   //index 1 is Up
   //index 2 is Left
   //index 3 is Right
   ArrayList<ArrayList<Integer>> boundaries = new ArrayList<ArrayList<Integer>>();
   ArrayList<ArrayList<Integer>> Position = new ArrayList<ArrayList<Integer>>();
   //index 0 is starposx
   //index 1 is starposy
   //index 2 is returnposx
   //index 3 is returnposy
   
   int boundariesU, boundariesD, boundariesL, boundariesR;
   int playerX;
   int playerY;
   int doorX;
   int doorY;
   int doorcounter = -1;
   int doorcount = 0;
   boolean start = false;
   String item = "";
   String mech = "";
   levelSwitch nextULevel = new levelSwitch("up");
   levelSwitch nextULevel2 = new levelSwitch("up");
   levelSwitch nextLLevel = new levelSwitch("left");
   levelSwitch prevLLevel = new levelSwitch("left");
   levelSwitch prevDLevel = new levelSwitch("down"); 
   levelSwitch prevRLevel = new levelSwitch("right");
   levelSwitch nextRLevel = new levelSwitch("right");
   
   //index of the current level
   //index 0 is room 1
   //index 1 is room 2
   //index 2 is room 3
   //index 3 is room 4
   //index 4 is room 5
   //index 5 is room 6
   //index 6 is room 7
   //index 7 is room 8
   //index 8 is room 9
   //index 9 is room 10
   
   /*
             /\            
            /  \           
           /    \         
      /\  /   5  \          
     /  \ \      /        
    /    \ \    /\/\       
   /  10  \ \  / /  \      
   \      /  \/\/ 4 /      
    \    /\/\  /   / /\     
     \  / /  \ \  / /  \    
      \/ /    \ \/\/    \   
        /   9  \  /   3  \  
        \      /  \      / 
      /\/\    / /\/\    / /\
     /  \ \  / /  \ \  /\/  \  
    /    \/\/ /    \ \/ /    \
   /   8  \  /   6  \  /   2  \
   \      /  \      /  \      /
    \    /\/\/\    /    \    / /\
     \  / /  \ \  /      \  /\/  \
      \/\/    \/\/        \/ /    \
        /   7  \            /   1  \
        \      /            \      /
         \    /              \    /
          \  /                \  /
           \/                  \/
   
   */
   int currentRoom = 0;
   int currentRoomPrev = -1; //for level switching
   boolean twoStart = false; // checks if two starting positions
   //String direction = "left";
   /*
   Spike newSpike = new Spike(684, 134, true, Color.GREEN);
   Button newButton = new Button(684, 184, 50, 50, true, Color.GREEN);
   Button secondButton = new Button(684, 284, 50, 50, true, Color.GREEN);
   */
   //Spring newSpring = new Spring(795, 384, true, Color.LIGHTGREY,);

   

   Player player = new Player(684,384);
   boolean up, down, left, right = false;
   boolean nextlevel = false;


   //intially 1st level

   String levelFile = "1stLevel.txt";

   public void start(Stage stage)
   {
      for(int i = 0; i < 10; i++)
      {
         levelSwitches.add(new ArrayList<levelSwitch>());
         boundaries.add(new ArrayList<Integer>());
         mechs.add(new ArrayList<AbstractMech>());
      }
      
      for(int i = 0; i < 20; i++)
      {
         Position.add(new ArrayList<Integer>());
      }
      
      for(int i = 0; i < levelSwitches.size(); i++)
      {
         for(int j = 0; j < 7; j++)
         {
            levelSwitches.get(i).add(null);
         }
      }

      initializeArrayLists();
      
      /*
      levelSwitches.get(0).set(0, nextULevel);
      levelSwitches.get(0).set(1, nextULevel2);
      levelSwitches.get(0).set(2, nextLLevel);
      levelSwitches.get(0).set(3, prevDLevel);
      levelSwitches.get(0).set(4, prevRLevel);
      */
      
      
      
      
      

      menu.setValue("Menu");
      menu.getItems().add("Save");
      menu.getItems().add("Load");
      menu.getItems().add("Reset");
      menu.getItems().add("Exit");
      
      initializeItems();
      //Uncomment this
      /*
      mechs.get(currentRoom).add(newSpike);
      mechs.get(currentRoom).add(newButton);
      mechs.get(currentRoom).add(secondButton);
      newButton.addSpike(newSpike);
      */
      
      //Establishing the flowpane of the project
      //Key lsiteners for moving the player
      gp.setAlignment(Pos.TOP_LEFT);
      sp.setOnKeyPressed(new KeyListenerDown());
      sp.setOnKeyReleased(new KeyListenerUp());
      menu.setOnAction(new ComboBoxListener());
      sp.setBackground(new Background(new BackgroundFill(Color.CYAN, CornerRadii.EMPTY, Insets.EMPTY)));
         sp.setPrefSize(100,30);
         Label label2 = new Label("Welcome to MoodTraption Zac         Please press H to Start          START             LOAD");
          sp.getChildren().add(label2);
      sp.getChildren().add(theCanvas);
      sp.getChildren().add(gp);
      gp.getChildren().add(menu);
      menu.setVisible(false);
      Scene scene = new Scene(sp, 1368, 768);
      stage.setScene(scene);
      stage.setTitle("Contraption Zac");
      sp.requestFocus();
      stage.show();
      ta.start();
      
      
      
   } 
   public void drawBackground()
   {
      gc.setFill(Color.BLACK);
      gc.fillRect(0,0,theCanvas.getWidth(),theCanvas.getHeight());   
   }
   public void loadReader()
   {
      try
      {
         Scanner savescan = new Scanner(new File("SaveData.txt"));
         while(savescan.hasNext())
         {
            String objectscan = savescan.next();
            if(objectscan.equals("playercords"))
            {
               player.setX(savescan.nextInt());
               player.setY(savescan.nextInt());
            }
         }
      }
      
      catch(FileNotFoundException fnfe)
      {
      
      }
      
      
   }
   public void saveData()
   {
      try
      {
         FileOutputStream fos = new FileOutputStream("SaveData.txt", true); //false means new file; true means append
                     
         PrintWriter pw = new PrintWriter(fos);
                     
         pw.println("playercords" + player.getX() + player.getY());
                     
         pw.close();      
      }
      
      catch(FileNotFoundException fnfe)
      {
      
      }
   }
      
      
   public void initializeItems()
   {
      try
      {
         playerX=player.getX();
         playerY=player.getY();
         Scanner scan = new Scanner(new File(levelFile));
         Scanner mechscan = new Scanner(new File(levelFile));
         //this will check which level it is on and set the currentRoom variable to the level - 1
         currentRoom = Integer.parseInt(levelFile.substring(0, 1)) - 1;
         //Button needs this information, so this gives button which room we are in
         Button.setCurrentRoom(currentRoom);
         //temporary just so the level is playable
         //checks if the room has already been initalized
         if(mechs.get(currentRoom).isEmpty() || mechs.get(currentRoom).get(0) == null)
         {
            item = "";
            while(!(item.equals("end")))
            {
               item = scan.next();         
               if(item.equals("Boundaries"))
               {
                  boundariesD = scan.nextInt();
                  boundariesU = scan.nextInt();  
                  boundariesL = scan.nextInt();
                  boundariesR = scan.nextInt(); 
                  boundaries.get(currentRoom).add(boundariesD);
                  boundaries.get(currentRoom).add(boundariesU);
                  boundaries.get(currentRoom).add(boundariesL);
                  boundaries.get(currentRoom).add(boundariesR);
                  
               }
               //area where player can pass to next level(s) 
               else if(item.equals("NextU"))
               {
                  nextULevel.levelInput(scan.nextInt(), scan.nextInt(), scan.nextInt());
               }
               else if(item.equals("NextU2"))
               {
                  nextULevel2.levelInput(scan.nextInt(), scan.nextInt(), scan.nextInt());
               }
               else if(item.equals("NextL"))
               {
                  nextLLevel.levelInput(scan.nextInt(), scan.nextInt(), scan.nextInt()); 
               }
               else if(item.equals("NextR"))
               {
                  nextRLevel.levelInput(scan.nextInt(), scan.nextInt(), scan.nextInt()); 
               }
               //tells if next level block will be in boundaries or out 
               else if(item.equals("In?U"))
               {
                  String In = scan.next();
                  nextULevel.Inbound(Boolean.parseBoolean(In));
                  
               }
               else if(item.equals("In?U2"))
               {
                  String In = scan.next();
                  nextULevel2.Inbound(Boolean.parseBoolean(In));
                  
               }
               else if(item.equals("In?L"))
               {
                  String In = scan.next();
                  nextLLevel.Inbound(Boolean.parseBoolean(In));
                  
               }
               else if(item.equals("In?R"))
               {
                  String In = scan.next();
                  nextRLevel.Inbound(Boolean.parseBoolean(In));
                  
               }
               else if(item.equals("PreviousD"))
               {
                  prevDLevel.levelInput(scan.nextInt(), scan.nextInt(), scan.nextInt());
                  
               }
               //tells if previous level block will be in boundaries or out
               else if(item.equals("PIn?D"))
               {
                  String In = scan.next();
                  prevDLevel.Inbound(Boolean.parseBoolean(In));
                  
               }
               else if(item.equals("PreviousR"))
               {
                  prevRLevel.levelInput(scan.nextInt(), scan.nextInt(), scan.nextInt());
                  
               }
               else if(item.equals("PreviousL"))
               {
                  prevLLevel.levelInput(scan.nextInt(), scan.nextInt(), scan.nextInt());
                  
               }
               //tells if previous level block will be in boundaries or out
               else if(item.equals("PIn?R"))
               {
                  String In = scan.next();
                  prevRLevel.Inbound(Boolean.parseBoolean(In));
                  
               }
               else if(item.equals("PIn?L"))
               {
                  String In = scan.next();
                  prevLLevel.Inbound(Boolean.parseBoolean(In));
                  
               }
               //name of next level
               else if(item.equals("levelUFile"))
               {
                  nextULevel.staging(scan.next());
               } 
               else if(item.equals("levelUFile2"))
               {
                  nextULevel2.staging(scan.next());
               } 
               else if(item.equals("levelLFile"))
               {
                  nextLLevel.staging(scan.next());
               }
               else if(item.equals("levelRFile1"))
               {
                  nextRLevel.staging(scan.next());
               }
               else if (item.equals("levelDFile"))
               {
                  prevDLevel.staging(scan.next());
               }
               else if (item.equals("levelRFile"))
               {
                  prevRLevel.staging(scan.next());
               }
               else if (item.equals("levelLFile1"))
               {
                  prevLLevel.staging(scan.next());
               }
               else if (item.equals("StartPos"))
               {
                  playerX = scan.nextInt();
                  playerY = scan.nextInt();
                  Position.get(currentRoom).add(playerX);
                  Position.get(currentRoom).add(playerY);
                  player.setX(playerX);
                  player.setY(playerY);
               }
               else if (item.equals("ReturnPosU"))
               {
                  int replayerX = scan.nextInt();
                  int replayerY = scan.nextInt();
                  Position.get(currentRoom).add(replayerX);
                  Position.get(currentRoom).add(replayerY);
                  //player.setX(playerX);
                  //player.setY(playerY);
               }
               
               
            }
            while(mechscan.hasNext())
            {
            //Doors
               mech = mechscan.next();
               //Tile  
               if(mech.equals("T"))
               {
                  String colorString = mechscan.next();
                  Color color = parseColor(colorString);
                  int X = mechscan.nextInt();
                  int Y = mechscan.nextInt();
                  mechs.get(currentRoom).add(new Tile(X, Y, color));
               }       
                
               //level block
               else if (mech.equals("TW"))
               {
                  String colorString = mechscan.next();
                  Color color = parseColor(colorString);
                  int X = mechscan.nextInt();
                  int Y = mechscan.nextInt();
                  mechs.get(currentRoom).add(new Wall(X, Y, 50, 50, false, color));              
               }
               //Additional level block indication
               else if(mech.equals("LB"))
               {
                  String colorString = mechscan.next();
                  Color color = parseColor(colorString);
                  int X = mechscan.nextInt();
                  int Y = mechscan.nextInt();               
                  gc.setFill(color);
                  mechs.get(currentRoom).add(new Wall(X, Y, 40, 40, false, color));
               }    
               //Wall
               else if (mech.equals("W"))
               {
                  String colorString = mechscan.next();
                  Color color = parseColor(colorString);
                  int height = mechscan.nextInt();
                  int width = mechscan.nextInt();
                  int X = mechscan.nextInt();
                  int Y = mechscan.nextInt();
                  mechs.get(currentRoom).add(new Wall(X,Y,width,height, true, color));            
               }  
               if(mech.equals("D"))
               {
                  int X = mechscan.nextInt();
                  int Y = mechscan.nextInt();
                  doormechs.add(new Door(X,Y,25,50, true)); 
                  doorcounter = 0;
               }
               
               //Spikes Horizontal
               else if(mech.equals("SH")) 
               {
                  Color newColor = parseColor(mechscan.next());
                  int x = mechscan.nextInt();
                  int y = mechscan.nextInt();
                  boolean spikesAreUp = mechscan.nextBoolean();
                  Spike newSpike = new Spike(x, y, spikesAreUp, newColor);
                  mechs.get(currentRoom).add(newSpike);
                  Button.addSpike(newSpike);
               } 
               else if(mech.equals("SV")) 
               {
                  Color newColor = parseColor(mechscan.next());
                  int x = mechscan.nextInt();
                  int y = mechscan.nextInt();
                  int width = mechscan.nextInt();
                  int height = mechscan.nextInt();
                  boolean spikesAreUp = mechscan.nextBoolean();
                  Spike newSpike = new Spike(x, y, width, height, spikesAreUp, newColor);
                  mechs.get(currentRoom).add(newSpike);
                  Button.addSpike(newSpike);
               }                
               //Buttons 
               else if(mech.equals("B"))
               {
                  Color newColor = parseColor(mechscan.next());
                  int x = mechscan.nextInt();
                  int y = mechscan.nextInt();
                  mechs.get(currentRoom).add(new Button(x, y, newColor));
               }
               //timed button
               else if(mech.equals("TB"))
               {
                  Color newColor = parseColor(mechscan.next());
                  int x = mechscan.nextInt();
                  int y = mechscan.nextInt();
                  mechs.get(currentRoom).add(new TimedButton(x, y, newColor));
               }
               //Springs
               else if(mech.equals("SP")) 
               {
                  Color newColor = parseColor(mechscan.next());
                  int x = mechscan.nextInt();
                  int y = mechscan.nextInt();
                  boolean springsAreUp = mechscan.nextBoolean();
                  String direction = mechscan.next();
                  Spring newSpring = new Spring(x, y, springsAreUp, newColor, direction);
                  mechs.get(currentRoom).add(newSpring);
                  
               } 
               //Switches
               else if(mech.equals("SW"))
               {
                  Color newColor = parseColor(mechscan.next());
                  int x = mechscan.nextInt();
                  int y = mechscan.nextInt();
                  mechs.get(currentRoom).add(new Switches(x,y,25,25, true, newColor, false));
                  
               
               }
               //Electric Wall
               else if(mech.equals("EW"))
               {
                  Color newColor = parseColor(mechscan.next());
                  int x = mechscan.nextInt();
                  int y = mechscan.nextInt();
                  ElectricWall newElectricWall = new ElectricWall(x, y, 10, 30, true, newColor);
                  mechs.get(currentRoom).add(newElectricWall);
                  Button.addSpike(newElectricWall);               
               }
               
               addLevelSwitchesToArrayList();
            }
            
         }
         else
         {
            //loading in the data from the level that is going back to
            nextULevel = levelSwitches.get(currentRoom).get(0);
            nextULevel2 = levelSwitches.get(currentRoom).get(1);
            nextLLevel = levelSwitches.get(currentRoom).get(2);
            prevDLevel = levelSwitches.get(currentRoom).get(3);
            prevRLevel = levelSwitches.get(currentRoom).get(4);
            nextRLevel = levelSwitches.get(currentRoom).get(5);
            prevLLevel = levelSwitches.get(currentRoom).get(6);
            boundariesD = boundaries.get(currentRoom).get(0);
            boundariesU = boundaries.get(currentRoom).get(1);
            boundariesL = boundaries.get(currentRoom).get(2);
            boundariesR = boundaries.get(currentRoom).get(3);
            if(currentRoom>currentRoomPrev) 
            {
               player.setX(Position.get(currentRoom).get(0)); //start x
               player.setY(Position.get(currentRoom).get(1)); //start y
            }
            else if(currentRoom<currentRoomPrev)
            {
               player.setX(Position.get(currentRoom).get(2)); //return x
               player.setY(Position.get(currentRoom).get(3)); //return y
            }
            
         }
         currentRoomPrev=currentRoom;
      }
      catch(FileNotFoundException fnfe)
      {
         System.out.println("No next level file");
      }
   }
   
   public void drawItems()
   {
      gc.clearRect(0,0,1368,768);
      drawBackground();
      for (int i = 0; i < mechs.get(currentRoom).size(); i++)
      {
         mechs.get(currentRoom).get(i).drawMe(gc);
      }
      
      for(int i = 0; i < doormechs.size(); i++)
      {
         doormechs.get(i).drawMe(gc);
      }
   }
   public class AnimationHandler extends AnimationTimer
   {
      public void handle(long currentTimeInNanoSeconds) 
      {
         if(start == true)
         {
            drawItems();
            for(int i = 0; i<mechs.get(currentRoom).size(); i++)
            {
               mechs.get(currentRoom).get(i).checkBoundaries(player);
            }            
         
         if(menu.getValue().equals("Save") || menu.getValue().equals("Load") || menu.getValue().equals("Reset") || menu.getValue().equals("Exit"))
         {
            menu.setValue("Menu");
         }
            
         /*
         if(Door.getDoorCounter() >= 150)
         {
            doormechs.get(currentDoor).switchVisibility();
            currentDoor++;
            if(currentDoor > 3)
            {
               currentDoor = 0;
            }
               doormechs.get(currentDoor).switchVisibility();
            }
            
            
         }
         */
         
            for(int i = 0; i<doormechs.size(); i++)
            {
               doormechs.get(i).checkBoundaries(player);
            }
            if(doorcounter == 150)
            {
               doorcounter = 0;
               if(doorcount > 0)
               {
                  Door closeddoor = new Door(doorX,doorY,25,50,true);
                  doormechs.add(closeddoor);
               }
               doorX = doormechs.get(0).getX();
               doorY = doormechs.get(0).getY();
               doormechs.remove(0);
               doorcount++;
               
            }
         
            if(currentRoom == 2)
            {
               for (int i = 0; i < doormechs.size(); i++)
               {
                  doormechs.get(i).setShouldBeDrawn(true);
               }
            }
            else
            {
               for (int i = 0; i < doormechs.size(); i++)
               {
                  doormechs.get(i).setShouldBeDrawn(false);
               }
            }
         
            // 
            //      
            player.drawMe(player.getX(),player.getY(),gc);
            if(player.getY() > boundariesU || nextULevel.canGo(player.getX(),player.getY()) || nextULevel2.canGo(player.getX(),player.getY()))
            {
               if(up)
               {
                  player.setY(player.getY() - 1);
                  //check if player is going to next level
                  if(nextULevel.canGo(player.getX(),player.getY()))
                  {
                     if(nextULevel.isInbound()) 
                     {
                        //prevDLevel.staging(levelFile);
                        levelFile = nextULevel.getStaging();
                        addLevelSwitchesToArrayList();
                        initializeItems(); 
                     
                     }
                     //make sure player fully leaves boundaries
                     else if(nextULevel.isOut(player.getX(),player.getY()))
                     {
                        //prevDLevel.staging(levelFile);
                        levelFile = nextULevel.getStaging();
                        addLevelSwitchesToArrayList();
                        initializeItems();    
                     }
                  }
                  else if(nextULevel2.canGo(player.getX(),player.getY()))
                  {
                     if(nextULevel2.isInbound()) 
                     {
                        //prevDLevel.staging(levelFile);
                        levelFile = nextULevel2.getStaging();
                        addLevelSwitchesToArrayList();
                        initializeItems(); 
                     }
                     //make sure player fully leaves boundaries
                     else if(nextULevel2.isOut(player.getX(),player.getY()))
                     {
                        //prevDLevel.staging(levelFile);
                        levelFile = nextULevel2.getStaging();
                        addLevelSwitchesToArrayList();
                        initializeItems();   
                     }
                  }
               
               }
            }
            if(player.getY() < boundariesD || prevDLevel.canGo(player.getX(),player.getY()))
            {
               if(down)
               {
                  player.setY(player.getY() + 1);
                  
                  //check if player is going to next level
                  if(prevDLevel.canGo(player.getX(),player.getY()))
                  {
                     if(prevDLevel.isInbound()) 
                     {
                        //need to fix with previous previous level somehow (if applicable)
                        //prevDLevel.staging(levelFile);
                        levelFile = prevDLevel.getStaging();
                        addLevelSwitchesToArrayList();
                        initializeItems(); 
                        
                     }
                     //make sure player fully leaves boundaries
                     else if(prevDLevel.isOut(player.getX(),player.getY())) 
                     {
                        //need to fix with previous previous level somehow (if applicable)
                        //prevDLevel.staging(levelFile);
                        addLevelSwitchesToArrayList();
                        initializeItems();    
                     }
                  }
               }
            }
            if(player.getX() > boundariesL || nextLLevel.canGo(player.getX(),player.getY()) || prevLLevel.canGo(player.getX(),player.getY()))
            {            
               if(left)
               {
                  player.setX(player.getX() - 1);
                  
                  //check if player is going to next level
                  if(nextLLevel.canGo(player.getX(),player.getY()))
                  {

                     //prevDLevel.staging(levelFile);
                     levelFile = nextLLevel.getStaging();
                     addLevelSwitchesToArrayList();
                     initializeItems(); 
                  }
                     //make sure player fully leaves boundaries
                  else if(nextLLevel.isOut(player.getX(),player.getY())) 
                  {
                     //prevDLevel.staging(levelFile);
                     levelFile = nextLLevel.getStaging();
                     addLevelSwitchesToArrayList();
                     initializeItems();    

                     if(nextLLevel.isInbound()) 
                     {
                        prevDLevel.staging(levelFile);
                        levelFile = nextLLevel.getStaging();
                        addLevelSwitchesToArrayList();
                        initializeItems(); 
                     }
                     //make sure player fully leaves boundaries
                     else if(nextLLevel.isOut(player.getX(),player.getY())) 
                     {
                        prevDLevel.staging(levelFile);
                        levelFile = nextLLevel.getStaging();
                        addLevelSwitchesToArrayList();
                        initializeItems();    
                     }
                  }
                  else if(prevLLevel.canGo(player.getX(),player.getY()))
                  {
                     if(prevLLevel.isInbound()) 
                     {
                        levelFile = prevLLevel.getStaging();
                        addLevelSwitchesToArrayList();
                        initializeItems(); 
                     }
                     //make sure player fully leaves boundaries
                     else if(prevLLevel.isOut(player.getX(),player.getY())) 
                     {
                        addLevelSwitchesToArrayList();
                        initializeItems();     
                     }
                  }
                  
               }
            }
            if(player.getX() < boundariesR || prevRLevel.canGo(player.getX(),player.getY()) || nextRLevel.canGo(player.getX(),player.getY()))
            {
               if(right)
               {
                  player.setX(player.getX() + 1);
               
                  //check if player is going to next level
                  if(prevRLevel.canGo(player.getX(),player.getY()))
                  {
                     if(prevRLevel.isInbound()) 
                     {
                        levelFile = prevRLevel.getStaging();
                        addLevelSwitchesToArrayList();
                        initializeItems(); 
                     }
                     //make sure player fully leaves boundaries
                     else if(prevRLevel.isOut(player.getX(),player.getY())) 
                     {
                        addLevelSwitchesToArrayList();
                        initializeItems();     
                     }
                  }
                  else if(nextRLevel.canGo(player.getX(),player.getY()))
                  {

                     //prevDLevel.staging(levelFile);
                     levelFile = nextRLevel.getStaging();
                     addLevelSwitchesToArrayList();
                     initializeItems(); 
                  }
                     //make sure player fully leaves boundaries
                  else if(nextRLevel.isOut(player.getX(),player.getY())) 
                  {
                     //prevDLevel.staging(levelFile);
                     levelFile = nextRLevel.getStaging();
                     addLevelSwitchesToArrayList();
                     initializeItems();    

                     if(nextRLevel.isInbound()) 
                     {
                        prevDLevel.staging(levelFile);
                        levelFile = nextRLevel.getStaging();
                        addLevelSwitchesToArrayList();
                        initializeItems(); 
                     }
                     //make sure player fully leaves boundaries
                     else if(nextRLevel.isOut(player.getX(),player.getY())) 
                     {
                        prevDLevel.staging(levelFile);
                        levelFile = nextRLevel.getStaging();
                        addLevelSwitchesToArrayList();
                        initializeItems();    
                     }
                  }
               }  
            }
            if(doorcounter != -1)
            {
               doorcounter++;
            }
                
            
         }
      }
   }
   public class KeyListenerDown implements EventHandler<KeyEvent>  
   {
      public void handle(KeyEvent event) 
      { 
      //Key listener for moving
         if (event.getCode() == KeyCode.ESCAPE) 
         {
            // Toggle ComboBox visibility instead of removing/adding
            if (menu.isVisible()) 
            {
               menu.setVisible(false); // Hide ComboBox
               sp.requestFocus(); // Return focus to the main pane (so it will listen for ESC again)
            } 
            else 
            {
               menu.setVisible(true); // Show ComboBox
               menu.requestFocus(); // Focus on ComboBox when visible
            }
         }
         if (event.getCode() == KeyCode.H) 
         {
            start = true;
         }
         
         if (event.getCode() == KeyCode.W)  
         {
            up = true;
         }
         if (event.getCode() == KeyCode.A)  
         {
            left = true;
         }         
         if (event.getCode() == KeyCode.S)  
         {
            down = true;
         }
         if (event.getCode() == KeyCode.D)  
         {
            right = true;
         }
         
      
      }
   }
   
   public class KeyListenerUp implements EventHandler<KeyEvent>  
   {
      public void handle(KeyEvent event) 
      { 
      //Key listener to counter act when the action is released
         if (event.getCode() == KeyCode.W) 
         {
            up = false;
         }
         if (event.getCode() == KeyCode.A)  
         {
            left = false;
         }
         if (event.getCode() == KeyCode.S)  
         {
            down = false;
         }
         if (event.getCode() == KeyCode.D)  
         {
            right = false;
         }
      }
   }
   public Color parseColor(String colorString) {
      Color color;
      try {
         color = Color.web(colorString);
      } catch (IllegalArgumentException e) {
         color = Color.BLACK;
      }
      return color;
   }
   
   //this will add 
   public void addLevelSwitchesToArrayList()
   {
   //
      levelSwitches.get(currentRoom).set(0, new levelSwitch(nextULevel));
      levelSwitches.get(currentRoom).set(1, new levelSwitch(nextULevel2));
      levelSwitches.get(currentRoom).set(2, new levelSwitch(nextLLevel));
      levelSwitches.get(currentRoom).set(3, new levelSwitch(prevDLevel));
      levelSwitches.get(currentRoom).set(4, new levelSwitch(prevRLevel));
      levelSwitches.get(currentRoom).set(5, new levelSwitch(nextRLevel));
      levelSwitches.get(currentRoom).set(6, new levelSwitch(prevLLevel));
   //
   }
   
   //this will create empty arraylists that have their inner arraylists initialized
   public void initializeArrayLists()
   {
      for(int i = 0; i < 10; i++)
      {
         levelSwitches.add(new ArrayList<levelSwitch>());
         boundaries.add(new ArrayList<Integer>());
         mechs.add(new ArrayList<AbstractMech>());
      }
      
      for(int i = 0; i < 20; i++)
      {
         Position.add(new ArrayList<Integer>());
      }
      
      for(int i = 0; i < 10; i++)
      {
         for(int j = 0; j < 10; j++)
         {
            levelSwitches.get(i).add(null);
         }
      }
      
      for(int i= 0; i < 6; i++)
      {
         saveList.add(null);
      }
   }
   
   ArrayList<String> files = new ArrayList<String>();
   int currentFileBadCode = 0;
   
   /*
   public void initializeEveryLevel()
   {
      files.add(new String("1stlevel.txt"));
      files.add(new String("2ndlevel.txt"));
      files.add(new String("3rdlevel.txt"));
      files.add(new String("4thlevel.txt"));
      files.add(new String("5thlevel.txt"));
      files.add(new String("6thlevel.txt"));
      files.add(new String("7thlevel.txt"));
      files.add(new String("8thlevel.txt"));
      files.add(new String("9thlevel.txt"));
      files.add(new String("10thlevel.txt"));
      
      levelFile = files.get(currentFileBadCode);
      initializeItems();
      currentFileBadCode++;
      
      
   }
   */
   
   public class ComboBoxListener implements EventHandler<ActionEvent>
   {
      public void handle(ActionEvent e)
      {
         if(menu.getValue()==null)
         {
            System.out.println(">:( null");
            menu.setValue("Menu");
         }
         else
         {
            switch((String)menu.getValue())
            {
               case "Save":
                  
                  //index 0 mechs
                  //index 1 boundaries
                  //index 2 levelSwitches
                  //index 3 levelFile
                  //index 4 X position of player
                  //index 5 Y position of player
                  ArrayList<ArrayList<AbstractMech>> tempMechs = new ArrayList<ArrayList<AbstractMech>>();
                  for(int i = 0; i < mechs.size(); i++)
                  {
                     tempMechs.add(new ArrayList<AbstractMech>());
                     for(int j = 0; j < mechs.get(i).size(); j++)
                     {
                        //this creates a new instance of the mech at the current index
                        tempMechs.get(i).add(mechs.get(i).get(j).clone());
                     }
                  }
                  saveList.set(0, tempMechs);
                  //this will create a shallow copy of the mechs arraylist
                  saveList.set(1, new ArrayList<ArrayList<Integer>>(boundaries));
                  ArrayList<ArrayList<levelSwitch>> tempSwitches = new ArrayList<ArrayList<levelSwitch>>();
                  for(int i = 0; i < levelSwitches.size(); i++)
                  {
                     tempSwitches.add(new ArrayList<levelSwitch>());
                     for(int j = 0; j < levelSwitches.get(i).size(); j++)
                     {
                        if(levelSwitches.get(i).get(j) == null)
                        {
                           tempSwitches.get(i).add(null);
                        }
                        else
                        {
                           tempSwitches.get(i).add(new levelSwitch(levelSwitches.get(i).get(j)));
                        }
                     }
                  }
                  saveList.set(2, tempSwitches);
                  saveList.set(3, new String(levelFile));
                  saveList.set(4, player.getX());
                  saveList.set(5, player.getY());
                  /*
                  ArrayList<Door> tempDoors = new ArrayList<Door>();
                  for(int i = 0; i < doors.size(); i++)
                  {
                     tempDoors.add(new Door(doors.get(i)));
                  }
                  saveList.set(6, tempDoors);
                  saveList.set(7, doorcounter);
                  System.out.println("Success");
                  */
                  break;
               case "Load":
                  if(saveList.get(0) == null)
                  {
                     break;
                  }
                  //typecasting all of thhe ArrayLists back to their original types and then setting them to their respective references
                  mechs = (ArrayList<ArrayList<AbstractMech>>)saveList.get(0);
                  boundaries = (ArrayList<ArrayList<Integer>>)saveList.get(1);
                  levelSwitches = (ArrayList<ArrayList<levelSwitch>>)saveList.get(2);
                  levelFile = (String)saveList.get(3);
                  Button.reassignSpikes(mechs);
                  initializeItems();
                  player.setX((int)saveList.get(4));
                  player.setY((int)saveList.get(5)); 
                                   
                  break;
               case "Reset":
                  saveList.clear();
                  doormechs.clear();
                  /*
                  for(int i = 0; i < mechs.size(); i++)
                  {
                     for(int j = 0; j < mechs.get(i).size(); j++)
                     {
                        mechs.get(i).set(j, null);
                     }
                  }
                  for(int i = 0; i < levelSwitches.size(); i++)
                  {
                     for(int j = 0; j < levelSwitches.get(i).size(); j++)
                     {
                        levelSwitches.get(i).set(j, null);
                     }
                  }
                  for(int i = 0; i < boundaries.size(); i++)
                  {
                     for(int j = 0; j < boundaries.get(i).size(); j++)
                     {
                        boundaries.get(i).set(j, null);
                     }
                  }
                  for(int i = 0; i < Position.size(); i++)
                  {
                     for(int j = 0; j < Position.get(i).size(); j++)
                     {
                        Position.get(i).set(j, null);
                     }
                  }
                  */
                  for(int i = 0; i < mechs.size(); i++)
                  {
                     mechs.get(i).clear();
                  }
                  for(int i = 0; i < levelSwitches.size(); i++)
                  {
                     levelSwitches.get(i).clear();
                  }
                  for(int i = 0; i < boundaries.size(); i++)
                  {
                     boundaries.get(i).clear();
                  }
                  for(int i = 0; i < Position.size(); i++)
                  {
                     Position.get(i).clear();
                  }
                  for(int i = 0; i < doormechs.size(); i++)
                  {
                     doormechs.remove(0);
                  }
                  initializeArrayLists();
                  levelFile = "1stLevel.txt";
                  //initializeEveryLevel();
                  initializeItems();
                  break;
               case "Exit":
                  System.exit(0);
                  break;
                  
            }
         }
      }
   }
}