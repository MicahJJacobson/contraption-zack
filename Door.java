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

public class Door extends AbstractMech
{
   //private static int doorCounter;
   //private boolean isVisible;
   
   //determines whether or not the doors should be drawn and have collisions
   private boolean shouldBeDrawn;

   /*
   public Door(int x, int y, int width, int height)
   {
       super(x, y, width, height);
   }
   */
   
   public Door(int x, int y, int width, int height, boolean hasCollisions)
   {
      super(x, y, width, height, hasCollisions, Color.GREY);
      shouldBeDrawn = true;
   }
   
   public void setShouldBeDrawn(boolean shouldBeDrawn)
   {
      this.shouldBeDrawn = shouldBeDrawn;
   }
   
   public Door(Door other)
   {
      super(other.x, other.y, other.width, other.height, other.hasCollisions, other.color);
      shouldBeDrawn = other.shouldBeDrawn;
   }
   
   /*
   public static int getDoorCounter()
   {
      return doorCounter;
   }
   
   public boolean getIsVisible()
   {
      return isVisible;
   }
   
   public void switchVisibility()
   {
      isVisible = !isVisible;
   }
   
   public static void setDoorCounter(int doorCounterIn)
   {
      doorCounter = doorCounterIn;
   }
   */

   public void drawMe(GraphicsContext gc)
   {
      /*
      if(isVisible)
      {
         gc.setFill(color);
         gc.fillRect(x, y, width, height);
         gc.setFill(Color.BLACK);
         gc.fillRect(x,y+25,25,1);
      }
      */
      if(shouldBeDrawn)
      {
         gc.setFill(color);
         gc.fillRect(x, y, width, height);
         gc.setFill(Color.BLACK);
         gc.fillRect(x,y+25,25,1);
      }
   }



















}