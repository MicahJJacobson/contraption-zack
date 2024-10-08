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

public class TimedButton extends Button
{
   //this is probably bad practice
   protected static ArrayList<ArrayList<Spike>> spikes = new ArrayList<ArrayList<Spike>>();
   //I know, bad practice
   private static int currentRoom;
   private int timer;

   public TimedButton(int x, int y, int width, int height, boolean hasCollisions, Color color)
   {
      super(x, y, width, height, hasCollisions, color);
      for(int i = 0; i < 10; i++)
      {
         spikes.add(new ArrayList<Spike>());
      }
      timer = 0;
   }
   
   public TimedButton(int x, int y, Color color)
   {
      super(x, y, 50, 50, true, color);
      for(int i = 0; i < 10; i++)
      {
         spikes.add(new ArrayList<Spike>());
      }
      timer = 0;
   }
   
   public TimedButton(TimedButton other)
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
   
   public TimedButton clone()
   {
      return new TimedButton(this);
   }
   
   public static TimedButton clone(TimedButton other)
   {
      return new TimedButton(other);
   }
   
   /*
   public static ArrayList<ArrayList<Spike>> getSpikes()
   {
      return spikes;
   }
   */
      
   public void drawMe(GraphicsContext gc)
   {
      if(hasCollisions)
      {
         int borderWidth = 5;
         gc.setFill(color);
         gc.fillOval(x + borderWidth, y + borderWidth, width - (borderWidth * 2), height - (borderWidth * 2));
      }
      else
      {
         int borderWidth = 7;
         gc.setFill(color);
         gc.fillOval(x + borderWidth, y + borderWidth, width - (borderWidth * 2), height - (borderWidth * 2));
         timer++;
         if(timer >= 2000)
         {
            timer = 0;
            swapCollisions();
         }
      }
      
   }
   
   public Color getColor()
   {
      return color;
   }
}