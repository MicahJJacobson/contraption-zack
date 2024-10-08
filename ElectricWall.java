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

public class ElectricWall extends Spike
{
   
   public ElectricWall(int x, int y, int width, int height, boolean hasCollisions, Color color)
   {
      super(x, y, width, height, hasCollisions, color);
     
   }
   
   
   public ElectricWall(ElectricWall other)
   {
      super(other.x, other.y, other.width, other.height, other.hasCollisions, other.color);
   }
   
   public ElectricWall clone()
   {
      return new ElectricWall(this);
   }
   
   public static ElectricWall clone(ElectricWall other)
   {
      return new ElectricWall(other);
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

      if (hasCollisions)
        {
            gc.setFill(Color.BLUE);
            gc.fillRect(x,y,width,height);
        }

     
   }
           
     
     
   
   public Color getColor()
   {
      return color;
   }
}