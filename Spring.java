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

public class Spring extends AbstractMech
{
   /*
   public Spike(int x, int y, int width, int height, boolean hasCollisions, Color color)
   {
      super(x, y, width, height, hasCollisions);
      this.color = color;
   }
   */
   String directionn;
   Color colorr;
   //all spikes are the same size so we don't need width and height
   public Spring(int x, int y, boolean hasCollisions, Color color, String direction)
   {
      
      super(x, y, 50, 12, hasCollisions, color);
      directionn = direction;
      colorr = color;
   }
   
   public String getDirection() 
   {
      return directionn;
   }
   public int getX() 
   {
      return x;
   }
   public int getY() 
   {
      return y;
   }
   
   public void swapCollisions()
   {
      //will swap the value of hasCollisions
      //if it is true, it will change it to false
      //if it is false, it will change it to true
      hasCollisions = !hasCollisions;
   }
   
   public boolean getCollisions()
   {
      return hasCollisions;
   }
   
   public void drawMe(GraphicsContext gc)
   {
     
      //spring has sprung
      if (hasCollisions)
      {

         
         
         //gc.setFill(Color.BLACK);
         //show circle that spring is available
         gc.setFill(color);
         gc.fillOval(x,y,25,25); // Second circle
         
      }
      else
      {
         gc.setFill(Color.BLACK);

         gc.fillRect(x,y,25,25); // Second circle
         
      }
   }
   
   
   public Color getColor()
   {
      return color;
   }
}