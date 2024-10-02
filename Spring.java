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

public class Spring extends AbstractMech
{
   private Color color;
   /*
   public Spike(int x, int y, int width, int height, boolean hasCollisions, Color color)
   {
      super(x, y, width, height, hasCollisions);
      this.color = color;
   }
   */
   
   //all spikes are the same size so we don't need width and height
   public Spring(int x, int y, boolean hasCollisions, Color color)
   {
      super(x, y, 50, 12, hasCollisions);
      this.color = color;
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
   
   public void drawMe(GraphicsContext gc, Color color)
   {
      
   }
   
   public void drawMe(GraphicsContext gc)
   {
      double totalWidth = 50; // Total available width
      double circleDiameter = 12; // Diameter of each circle
      int numberOfCircles = 3; // Number of circles

      // Calculate the total space occupied by all circles combined
      double totalCirclesWidth = numberOfCircles * circleDiameter;

      // Calculate the remaining space after placing the circles
      double remainingSpace = totalWidth - totalCirclesWidth;

      // Calculate the padding on the left and right sides, as well as space between the circles
      double leftRightPadding = remainingSpace / 4; // Dividing by 4 as there will be padding on both sides and space between circles
      double spaceBetweenCircles = leftRightPadding; // Space between circles is the same as left-right padding
      //spring has sprung
      if (hasCollisions)
      {
         double smallerDiameter = circleDiameter - 8; // New diameter for smaller circles
         double offset = (circleDiameter - smallerDiameter) / 2; // Offset to keep the circles centered

         gc.setFill(Color.BLACK);
         
         gc.setFill(color);
         gc.fillOval(x-10,y,50,50); // Second circle
         
      }
      else
      {
         double smallerDiameter = circleDiameter - 8; // New diameter for smaller circles
         double offset = (circleDiameter - smallerDiameter) / 2; // Offset to keep the circles centered

         gc.setFill(Color.BLACK);
         //show circle that spring is available
         gc.setFill(color);
         gc.fillOval(x,y,50,50); // Second circle
      }
   }   
   
   public Color getColor()
   {
      return color;
   }
   
   public void doThing()
   {
      return;
   }
}