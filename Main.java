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

import java.util.*;
import java.text.*;
import java.io.*;
import java.lang.*;
import javafx.application.*;
import javafx.event.*;
import javafx.stage.*;
import javafx.scene.canvas.*;
import javafx.scene.paint.*;
import javafx.scene.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.animation.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import java.net.*;
import javafx.geometry.*;

public class Main extends Application
{

   AnimationHandler ta = new AnimationHandler();
   ComboBox menu = new ComboBox();
   StackPane sp = new StackPane();
   GridPane gp = new GridPane();
   Canvas theCanvas = new Canvas(1368,768);
   GraphicsContext gc = theCanvas.getGraphicsContext2D(); 
   String garbage;
   int dooramountbase = 0;
   int rotation = 0;
   ArrayList<AbstractMech> mechs = new ArrayList<AbstractMech>();
   ArrayList<AbstractMech> doormechs = new ArrayList<AbstractMech>();
   int boundariesU, boundariesD, boundariesL, boundariesR;
   boolean hasDoors = false;
   int doorX;
   int doorY;
   int doorcounter = 0;
   int dooramount;
   levelSwitch nextULevel = new levelSwitch("up");
   levelSwitch nextULevel2 = new levelSwitch("up");
   levelSwitch nextLLevel = new levelSwitch("left");
   levelSwitch prevDLevel = new levelSwitch("down"); 
   
   
   //
   Spike newSpike = new Spike(684, 134, true, Color.GREEN);
   Button newButton = new Button(684, 184, 50, 50, true, Color.GREEN);
   Button secondButton = new Button(684, 284, 50, 50, true, Color.GREEN);
   //
   Spring newSpring = new Spring(795, 384, true, Color.LIGHTGREY);

   

   Player player = new Player(684,384);
   boolean up, down, left, right = false;
   boolean nextlevel = false;

   //intially 1st level
   String levelFile = "1stLevel.txt";

   public void start(Stage stage)
   {
      //
      mechs.add(newSpike);
      mechs.add(newButton);
      mechs.add(secondButton);
      newButton.addSpike(newSpike);
      //
      
      //Establishing the flowpane of the project
      //Key lsiteners for moving the player
      gp.setAlignment(Pos.TOP_LEFT);
      sp.setOnKeyPressed(new KeyListenerDown());
      sp.setOnKeyReleased(new KeyListenerUp());
      sp.getChildren().add(theCanvas);
      sp.getChildren().add(gp);
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
      
   
   public void drawItems()
   {
   //Here I read through a file so we can track the highscore through every game
      try
      {
         Scanner scan = new Scanner(new File(levelFile));
         
         
         
         
         
         while(scan.hasNext())
         {
            String item = scan.next();
             //Tile  
            if(item.equals("T"))
            {
               String colorString = scan.next();
               Color color = parseColor(colorString);
               gc.setFill(color);
               int X = scan.nextInt();
               int Y = scan.nextInt();
               gc.fillRect(X,Y,50,50); 
            }       
            //Doors    
            else if(item.equals("D"))
               {
                if(dooramountbase < 4)
                {
                  int X = scan.nextInt();
                  int Y = scan.nextInt();
                  Door door = new Door(X,Y,25,50, true);
                  doormechs.add(door);  
                  hasDoors = true;
                  dooramountbase++;    
                }
               }
             
            //level block
            else if (item.equals("TW"))
            {
               String colorString = scan.next();
               Color color = parseColor(colorString);
               gc.setFill(color);
               int X = scan.nextInt();
               int Y = scan.nextInt();
               gc.fillRect(X,Y,50,50);               
            }
            //Wall
            else if (item.equals("W"))
            {
               String colorString = scan.next();
               Color color = parseColor(colorString);
               int height = scan.nextInt();
               int X = scan.nextInt();
               int Y = scan.nextInt();
               Wall wall = new Wall(X,Y,50,height, true, color);
               wall.drawMe(gc);
               mechs.add(wall);             
            } 
            //Horizontal Lines
            else if (item.equals("LH"))
            {
               int X = scan.nextInt();
               int Y = scan.nextInt();
               gc.setFill(Color.BLACK);
               gc.fillRect(X,Y,500,1);          
            }
            //Vertical Lines
            else if (item.equals("LV"))
            {
               int X = scan.nextInt();
               int Y = scan.nextInt();
               gc.setFill(Color.BLACK);
               gc.fillRect(X,Y,1,385);          
            }  
            //Boundaries of the level            
            else if(item.equals("Boundaries"))
            {
               boundariesD = scan.nextInt();
               boundariesU = scan.nextInt();  
               boundariesL = scan.nextInt();
               boundariesR = scan.nextInt(); 
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
            else if (item.equals("levelDFile"))
            {
               prevDLevel.staging(scan.next());
            }
            //spikes
            else if(item.equals("S")) 
            {
               Color newColor = parseColor(scan.next());
               int x = scan.nextInt();
               int y = scan.nextInt();
               boolean spikesAreUp = scan.nextBoolean();
               Spike newSpike = new Spike(x, y, spikesAreUp, newColor);
               mechs.add(newSpike);
               Button.addSpike(newSpike);
            } 
            else if(item.equals("B"))
            {
               Color newColor = parseColor(scan.next());
               int x = scan.nextInt();
               int y = scan.nextInt();
               mechs.add(new Button(x, y, newColor));
            }
            
            
            //
            newSpike.drawMe(gc);
            newButton.drawMe(gc); 
            secondButton.drawMe(gc);
            //
            newSpring.drawMe(gc);
            
            System.out.println(mechs.size());
                     
          
         }
      }
      catch(FileNotFoundException fnfe)
      {
         System.out.println("No next level file");
      }
   }
   public class AnimationHandler extends AnimationTimer
   {
      public void handle(long currentTimeInNanoSeconds) 
      {
            gc.clearRect(0,0,1368,768);
            drawBackground();
            /*
            for(int i = 0; i<mechs.size(); i++)
            {
               mechs.remove(i);
            }           
            */ 
            drawItems();
            for(int i = 0; i<mechs.size(); i++)
            {
               mechs.get(i).checkBoundaries(player);
            }
            if(hasDoors == true)
            {
              for(int i = 0; i<doormechs.size(); i++)
                  {
                     doormechs.get(i).drawMe(gc);
                     doormechs.get(i).checkBoundaries(player);
                  }  
               int dooramount = dooramountbase; 
               if(doorcounter == 150)
               {
                  doorcounter = 0;
                  if(rotation > 0)
                  {
                  Door closeddoor = new Door(doorX,doorY,25,50,true);
                  closeddoor.drawMe(gc);
                  doormechs.add(closeddoor);
                  }
                  doorX = doormechs.get(0).getX();
                  doorY = doormechs.get(0).getY();
                  doormechs.remove(0);
                  gc.clearRect(doorX,doorY,25,50);
                  rotation++;
                  if(dooramountbase > 1)
                  {
                  dooramount--;;
                  }
               }
               
                
            }           
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
                        drawItems();
                     }
                     //make sure player fully leaves boundaries
                     else if(nextULevel.isOut(player.getX(),player.getY()))
                     {
                        //prevDLevel.staging(levelFile);
                        levelFile = nextULevel.getStaging();
                        drawItems();   
                     }
                  }
                  else if(nextULevel2.canGo(player.getX(),player.getY()))
                  {
                     if(nextULevel2.isInbound()) 
                     {
                        //prevDLevel.staging(levelFile);
                        levelFile = nextULevel2.getStaging();
                        drawItems();
                     }
                     //make sure player fully leaves boundaries
                     else if(nextULevel2.isOut(player.getX(),player.getY()))
                     {
                        //prevDLevel.staging(levelFile);
                        levelFile = nextULevel2.getStaging();
                        drawItems();   
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
                        drawItems();
                        
                     }
                     //make sure player fully leaves boundaries
                     else if(prevDLevel.isOut(player.getX(),player.getY())) 
                     {
                        //need to fix with previous previous level somehow (if applicable)
                        //prevDLevel.staging(levelFile);
                        levelFile = prevDLevel.getStaging();
                        drawItems();   
                     }
                  }
               }
            }
            if(player.getX() > boundariesL || nextLLevel.canGo(player.getX(),player.getY()))
            {            
               if(left)
               {
                  player.setX(player.getX() - 1);
                  
                  //check if player is going to next level
                  if(nextLLevel.canGo(player.getX(),player.getY()))
                  {
                     if(nextLLevel.isInbound()) 
                     {
                        prevDLevel.staging(levelFile);
                        levelFile = nextLLevel.getStaging();
                        drawItems();
                     }
                     //make sure player fully leaves boundaries
                     else if(nextLLevel.isOut(player.getX(),player.getY())) 
                     {
                        prevDLevel.staging(levelFile);
                        levelFile = nextLLevel.getStaging();
                        drawItems();   
                     }
                  }
                  
               }
            }
            if(player.getX() < boundariesR)
            {
               if(right)
               {
                  player.setX(player.getX() + 1);
               }  
            }
            doorcounter++;
                
            
      }
      
   }
   public class KeyListenerDown implements EventHandler<KeyEvent>  
   {
      public void handle(KeyEvent event) 
      { 
      //Key listener for moving
         if (event.getCode() == KeyCode.ESCAPE) 
         {
            menu.setValue("Menu");
            menu.getItems().add("Save");
            menu.getItems().add("Load");
            gp.getChildren().add(menu);
            if(menu.getValue() == "Save")
            {
               saveData();
            }
            else if (menu.getValue() == "Load")
            {
               loadReader();
            }
            
            
            
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
}