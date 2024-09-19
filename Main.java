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
   int boundariesU, boundariesD, boundariesL, boundariesR;

   //window where player can access next level
   int nextLevelU, nextLevelL, nextLevelR;
   int prevLevelD, prevLevelL, prevLevelR;

   Player player = new Player(684,384);
   boolean up, down, left, right = false;
   boolean nextlevel = false;
   //checks if next level block is in boundaries
   boolean Inboundaries;
   //Previous level boundaries
   boolean PInboundaries;
   //intially 1st level
   String levelFile = "1stLevel.txt";
   //put next level here temporarily 
   String stagingFile;
   //for going back in file 
   String previousFile;

   public void start(Stage stage)
   {
      drawBackground();
      drawItems();
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
            if (item.equals("T"))
            {
               String colorString = scan.next();
               Color color = parseColor(colorString);
               gc.setFill(color);
               int X = scan.nextInt();
               int Y = scan.nextInt();
               gc.fillRect(X,Y,50,50); 
            }
            else if (item.equals("W"))
            {
               String colorString = scan.next();
               Color color = parseColor(colorString);
               gc.setFill(color);
               int X = scan.nextInt();
               int Y = scan.nextInt();
               gc.fillRect(X,Y,50,50);               
            }  
            else if (item.equals("LH"))
            {
               int X = scan.nextInt();
               int Y = scan.nextInt();
               gc.setFill(Color.BLACK);
               gc.fillRect(X,Y,300,1);          
            }
            else if (item.equals("LV"))
            {
               int X = scan.nextInt();
               int Y = scan.nextInt();
               gc.setFill(Color.BLACK);
               gc.fillRect(X,Y,1,1000);          
            }              
            else if (item.equals("JK"))
            {
               int X = scan.nextInt();
               int Y = scan.nextInt();
               gc.setFill(Color.GREEN);
               gc.fillRect(X,Y,50,50);          
            } 
            else if(item.equals("Boundaries"))
            {
               boundariesD = scan.nextInt();
               boundariesU = scan.nextInt();  
               boundariesL = scan.nextInt();
               boundariesR = scan.nextInt(); 
            }
            //area where player can pass to next level 
            else if(item.equals("Next"))
            {
               nextLevelU = scan.nextInt();  
               nextLevelL = scan.nextInt();
               nextLevelR = scan.nextInt(); 
            }
            //tells if next level block will be in boundaries or out 
            else if(item.equals("In?"))
            {
               String In = scan.next();
               Inboundaries = Boolean.parseBoolean(In);
               
            }
            else if(item.equals("Previous"))
            {
               prevLevelD = scan.nextInt();  
               prevLevelL = scan.nextInt();
               prevLevelR = scan.nextInt();
               
            }
            else if(item.equals("PIn?"))
            {
               String In = scan.next();
               PInboundaries = Boolean.parseBoolean(In);
               
            }
            //name of next level
            else if(item.equals("levelFile"))
            {
               stagingFile = scan.next();
            }              
          //}
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
            drawItems();
            player.draw(player.getX(),player.getY(),gc);
            if(player.getY() > boundariesU || (player.getX() > nextLevelL && player.getY() <= nextLevelU && player.getX()+50 <= nextLevelR))
            {
               if(up)
               {
                  player.setY(player.getY() - 1);
                  //check if player is going to next level
                  if(player.getX() > nextLevelL && player.getY() <= nextLevelU && player.getX()+50 <= nextLevelR)
                  {
                     if(Inboundaries) 
                     {
                        previousFile = levelFile;
                        levelFile = stagingFile;
                        drawItems();
                     }
                     //make sure player fully leaves boundaries
                     else if(player.getX() > nextLevelL && player.getY()+25 < nextLevelU && player.getX()+50 <= nextLevelR) 
                     {
                        previousFile = levelFile;
                        levelFile = stagingFile;
                        drawItems();   
                     }
                  }
           
               }
            }
            if(player.getY() < boundariesD)
            {
               if(down)
               {
                  player.setY(player.getY() + 1);;
               }
            }
            if(player.getX() > boundariesL)
            {            
               if(left)
               {
                  player.setX(player.getX() - 1);
               }
            }
            if(player.getX() < boundariesR)
            {
               if(right)
               {
                  player.setX(player.getX() + 1);
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