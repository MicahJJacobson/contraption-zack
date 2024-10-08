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
import java.awt.Point;

public abstract class AbstractMech implements Cloneable
{
   protected int x;
   protected int y;
   protected int width;
   protected int height;
   protected boolean hasCollisions;
   protected Color color;
   
   //ArrayList<AbstractMech> mechs;
   
   /*
   public AbstractMech(int x, int y, int width, int height, Color color)
   {
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
      hasCollisions = false;
      this.color = color;
   }
   */
   
   public AbstractMech(int x, int y, int width, int height, boolean hasCollisions, Color color)
   {
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
      this.hasCollisions = hasCollisions;
      this.color = color;
   }
   
   public AbstractMech clone() 
   {
        try 
        {
            return (AbstractMech) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); // This shouldn't happen since we are Cloneable
        }
    }

   public abstract void drawMe(GraphicsContext gc);
   public int getHeight()
   {
      return height;
   }
   public int getWidth()
   {
      return width;
   }  
   public int getX()
   {
      return x;
   }
   public int getY()
   {
      return y;
   }
   public boolean checkBoundaries(Player player) 
   {
      if(!hasCollisions)
      {
         return false;
      }
      
      // Define the top-left and bottom-right corners of the player's rectangle.
      Point playerTopLeft = new Point(player.getX(), player.getY());
      Point playerBottomRight = new Point(player.getX() + player.getSize(), player.getY() + player.getSize());
   
      // Define the top-left and bottom-right corners of 'this' rectangle.
      Point thisTopLeft = new Point(x, y);
      Point thisBottomRight = new Point(x + width, y + height);
   
      // Check if 'this' rectangle is completely to the right of the player's rectangle.
      if (thisTopLeft.getX() > playerBottomRight.getX() || 
          thisBottomRight.getX() < playerTopLeft.getX() || 
          thisTopLeft.getY() > playerBottomRight.getY() || 
          thisBottomRight.getY() < playerTopLeft.getY()) 
      {
          return false; // No overlap
      }

      // Calculate the overlap on each side
      int overlapLeft = (int)playerBottomRight.getX() - (int)thisTopLeft.getX();
      int overlapRight = (int)thisBottomRight.getX() - (int)playerTopLeft.getX();
      int overlapTop = (int)playerBottomRight.getY() - (int)thisTopLeft.getY();
      int overlapBottom = (int)thisBottomRight.getY() - (int)playerTopLeft.getY();

      // Resolve the collision by moving the player out of the smallest overlap
      if (overlapLeft < overlapRight && overlapLeft < overlapTop && overlapLeft < overlapBottom) 
      {
          player.setX(player.getX() - overlapLeft); // Move player left
      } 
      else if (overlapRight < overlapLeft && overlapRight < overlapTop && overlapRight < overlapBottom) {
          player.setX(player.getX() + overlapRight); // Move player right
      } 
      else if (overlapTop < overlapLeft && overlapTop < overlapRight && overlapTop < overlapBottom) 
      {
          player.setY(player.getY() - overlapTop); // Move player up
      } 
      else if (overlapBottom < overlapLeft && overlapBottom < overlapRight && overlapBottom < overlapTop) 
      {
         player.setY(player.getY() + overlapBottom); // Move player down
      }
      
      if(this instanceof Button)
      {
         Button button = (Button)this;
         if(button.getCollisions() == true)
         {
            button.swapCollisions();
         }
      }
      
      if(this instanceof TimedButton)
      {
         TimedButton timedButton = (TimedButton)this;
         if(timedButton.getCollisions() == true)
         {
            timedButton.swapCollisions();
         }
      }
      
      if(this instanceof Spring)
      {
         Spring spring = (Spring)this;
         //We want spring to have collisions
         if(spring.getIsActivated() == false)
         {
            //spring.swapCollisions();
            spring.activate();
            spring.springMove(player);
         }
      }
      if(this instanceof Switches)
      {
         Switches switches = (Switches)this;
         switches.switchIsActivated();
      }      
      //if the rectangles overlap, then return true
      return true; 
   }
     

}   

      
      
      
      
      
      
      
      

   