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
   FlowPane fp = new FlowPane();
   GridPane gp = new GridPane();
   Canvas theCanvas = new Canvas(1368,768);
   GraphicsContext gc = theCanvas.getGraphicsContext2D(); 
   String garbage;
<<<<<<< HEAD
   int boundariesU, boundariesD, boundariesL, boundariesR;
=======
>>>>>>> 1997a02d0ecdb7dc8e11221fac688f50e25949a9
   Player player = new Player(684,384);
   int playerx = 768;
   int playery = 348;
   boolean up, down, left, right = false;
   boolean nextlevel = false;
   public void start(Stage stage)
   {
      drawBackground();
      drawItems();
      //Establishing the flowpane of the project
      //Key lsiteners for moving the player
      gp.setAlignment(Pos.TOP_LEFT);
      fp.setOnKeyPressed(new KeyListenerDown());
      fp.setOnKeyReleased(new KeyListenerUp());
      fp.getChildren().add(theCanvas);
      fp.getChildren().add(gp);
      Scene scene = new Scene(fp, 1368, 768);
      stage.setScene(scene);
      stage.setTitle("Contraption Zac");
      fp.requestFocus();
      stage.show();
      ta.start();
      
      
   } 
   public void drawBackground()
   {
      gc.setFill(Color.BLACK);
      gc.fillRect(0,0,theCanvas.getWidth(),theCanvas.getHeight());   
   }
   public void drawItems()
   {
   //Here I read through a file so we can track the highscore through every game
      try
      {
         Scanner scan = new Scanner(new File("1stLevel.txt"));
         
         
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
         }
      }
      catch(FileNotFoundException fnfe)
      {
      
      }
   }
   public class AnimationHandler extends AnimationTimer
   {
      public void handle(long currentTimeInNanoSeconds) 
      {
         if(!nextlevel)
         {
            gc.clearRect(0,0,1368,768);
            drawBackground();
            drawItems();
            player.draw(playerx,playery,gc,true);
<<<<<<< HEAD
            if(playery > boundariesU)
            {
               if(up)
               {
                  playery--;
               }
            }
            if(playery < boundariesD)
            {
               if(down)
               {
                  playery++;
               }
            }
            if(playerx > boundariesL)
            {            
               if(left)
               {
                  playerx--;
               }
            }
            if(playerx < boundariesR)
            {
               if(right)
               {
                  playerx++;
               }  
            }
=======
            if(up)
            {
               playery--;
            }
            if(down)
            {
               playery++;
            }
            if(left)
            {
               playerx--;
            }
            if(right)
            {
               playerx++;
            }  
>>>>>>> 1997a02d0ecdb7dc8e11221fac688f50e25949a9
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