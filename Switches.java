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

public class Switches extends Button
{
   //this is probably bad practice
   protected static ArrayList<ArrayList<Spike>> spikes = new ArrayList<ArrayList<Spike>>();
   //I know, bad practice
   private static int currentRoom;
   private int timer;
   private boolean isActivated;

   public Switches(int x, int y, int width, int height, boolean hasCollisions, Color color, boolean isActivated)
   {
      super(x, y, width, height, true, color);
      this.isActivated = isActivated;
      for(int i = 0; i < 10; i++)
      {
         spikes.add(new ArrayList<Spike>());
      }
      timer = 0;
   }
   
   public Switches(int x, int y, Color color)
   {
      super(x, y, 25, 25, true, color);
      for(int i = 0; i < 10; i++)
      {
         spikes.add(new ArrayList<Spike>());
      }
      timer = 0;
   }
   
   public Switches(Switches other)
   {
      super(other.x, other.y, other.width, other.height, other.hasCollisions, other.color);
      ArrayList<ArrayList<Spike>> tempSpikes = new ArrayList<ArrayList<Spike>>();
      for(int i = 0; i < spikes.size(); i++)
      {
         tempSpikes.add(new ArrayList<Spike>());
         for(int j = 0; j < spikes.get(i).size(); j++)
         {
            tempSpikes.get(i).add(new Spike(spikes.get(i).get(j)));
         }
      }
      timer = 0;
   }
   
   public Switches clone()
   {
      return new Switches(this);
   }
   
   
      
   public void drawMe(GraphicsContext gc)
   {

            if(isActivated == true)
            {
            gc.setFill(color);
            gc.fillRect(x, y+2, 10, 10);
            }
      

         if(isActivated == false)
         {
            gc.setFill(color);
            gc.fillRect(x, y, 10, 10);
               
         }
         
      
      
   }
   public void switchIsActivated()
   {
      isActivated = !isActivated;
      for(int i = 0; i < spikes.get(currentRoom).size(); i++)
      {
         if(spikes.get(currentRoom).get(i).getColor().equals(color))
         {
            spikes.get(currentRoom).get(i).swapCollisions();
         }   
      }
   }
   
   public Color getColor()
   {
      return color;
   }
















}
