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

public class Button extends AbstractMech
{
   private static ArrayList<Spike> spikes = new ArrayList<Spike>();

   public Button(int x, int y, int width, int height, boolean hasCollisions, Color color)
   {
      super(x, y, width, height, hasCollisions, color);
   }
   
   public Button(int x, int y, Color color)
   {
      super(x, y, 50, 50, true, color);
   }
   
   public void swapCollisions()
   {
      //will swap the value of hasCollisions
      //if it is true, it will change it to false
      //if it is false, it will change it to true
      hasCollisions = !hasCollisions;
      for(int i = 0; i < spikes.size(); i++)
      {
         if(spikes.get(i).getColor().equals(color))
         {
            spikes.get(i).swapCollisions();
         }
      }
   }
   
   public boolean getCollisions()
   {
      return hasCollisions;
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
      spikes.add(spike);
   }
   
   public Color getColor()
   {
      return color;
   }
   
   
}