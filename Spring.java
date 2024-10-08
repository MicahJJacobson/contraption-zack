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
   private  String direction;
   private boolean isActivated;
   //Color color;
   //all spikes are the same size so we don't need width and height
   public Spring(int x, int y, boolean hasCollisions, Color color, String direction)
   {
      
      super(x , y , 25, 25, hasCollisions, color);
      this.direction = direction;
      isActivated = false;
   }
   
   public Spring(Spring other)
   {
      super(other.x, other.y, other.width, other.height, other.hasCollisions, other.color);
      isActivated = other.isActivated;
      direction = other.direction;
   }
   
   public Spring clone()
   {
      return new Spring(this);
   }
   
   public static Spring clone(Spring other)
   {
      return new Spring(other);
   }
   
   public String getDirection() 
   {
      return direction;
   }
   public int getX() 
   {
      return x;
   }
   public int getY() 
   {
      return y;
   }
   public void activate()
   {
      isActivated = true;
   }
   public boolean getIsActivated()
   {
      return isActivated;
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
   
   public void springMove(Player player) 
   {
      player.setX(this.x);
      player.setY(this.y);
      if(direction.equals("right")) 
      {
         player.setX(player.getX()+50);
      }
      else if(direction.equals("left")) 
      {
         player.setX(player.getX()-50);
      }
      else if(direction.equals("up")) 
      {
         player.setY(player.getY()-50);
      }
      else if(direction.equals("down")) 
      {
         player.setY(player.getY()+50);
      }
   } 
   
   public void drawMe(GraphicsContext gc)
   {
     
      //spring has sprung
      if (isActivated == false)
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