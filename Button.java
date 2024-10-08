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

public class Button extends AbstractMech
{
   //this is probably bad practice
   protected static ArrayList<ArrayList<Spike>> spikes = new ArrayList<ArrayList<Spike>>();
   //I know, bad practice
   protected static int currentRoom = 0;

   public Button(int x, int y, int width, int height, boolean hasCollisions, Color color)
   {
      super(x, y, width, height, hasCollisions, color);
   }
   
   public Button(int x, int y, Color color)
   {
      super(x, y, 50, 50, true, color);
   }
   
   public Button(Button other)
   {
      super(other.x, other.y, other.width, other.height, other.hasCollisions, other.color);
      /*
      ArrayList<ArrayList<Spike>> tempSpikes = new ArrayList<ArrayList<Spike>>();
      for(int i = 0; i < spikes.size(); i++)
      {
         tempSpikes.add(new ArrayList<Spike>());
         for(int j = 0; j < spikes.get(i).size(); j++)
         {
            tempSpikes.get(i).add(new Spike(spikes.get(i).get(j)));
         }
      }
      */
      
   }
   
   public static void reassignSpikes(ArrayList<ArrayList<AbstractMech>> mechs)
   {
      spikes = new ArrayList<ArrayList<Spike>>();
      for(int i = 0; i < 10; i++)
      {
         spikes.add(new ArrayList<Spike>());
      }
      /*
      for(int i = 0; i < spikes.size(); i++)
      {
         for(int j = 0; j < mechs.get(i).size(); j++)
         {
            AbstractMech current = mechs.get(i).get(j);
            if(current instanceof Spike)
            {
               
            }
         }
      }
      */
      for(int i = 0; i < mechs.size(); i++)
      {
         for(int j = 0; j < mechs.get(i).size(); j++)
         {
            AbstractMech current = mechs.get(i).get(j);
            if(current instanceof Spike)
            {
               spikes.get(i).add((Spike)current);
            }
         }
      }
      
      
   }
   
   public Button clone()
   {
      return new Button(this);
   }
   
   public static Button clone(Button other)
   {
      return new Button(other);
   }
   
   /*
   public static ArrayList<ArrayList<Spike>> getSpikes()
   {
      return spikes;
   }
   */
   
   public void swapCollisions()
   {
      //will swap the value of hasCollisions
      //if it is true, it will change it to false
      //if it is false, it will change it to true
      hasCollisions = !hasCollisions;
      for(int i = 0; i < spikes.get(currentRoom).size(); i++)
      {
         if(spikes.get(currentRoom).get(i).getColor().equals(color))
            {
               spikes.get(currentRoom).get(i).swapCollisions();
            }
      }
   }
   
   public boolean getCollisions()
   {
      return hasCollisions;
   }
   
   public static int getCurrrentRoom()
   {
      return currentRoom;
   }
   
   public static void setCurrentRoom(int currentRoomIn)
   {
      currentRoom = currentRoomIn;
   }
      
   public void drawMe(GraphicsContext gc)
   {
      if(hasCollisions)
      {
         int borderWidth = 5;
         gc.setFill(color);
         gc.fillRect(x + borderWidth, y + borderWidth, width - (borderWidth * 2), height - (borderWidth * 2));
      }
      else
      {
         int borderWidth = 7;
         gc.setFill(color);
         gc.fillRect(x + borderWidth, y + borderWidth, width - (borderWidth * 2), height - (borderWidth * 2));
      }
      
   }
   
   public static void addSpike(Spike spike)
   {
      while(spikes.size() <= currentRoom)
      {
         spikes.add(new ArrayList<Spike>());
      }
      spikes.get(currentRoom).add(spike);
   }
   
   public Color getColor()
   {
      return color;
   }
   
   
}