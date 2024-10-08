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

public class Spike extends AbstractMech
{
   
   public Spike(int x, int y, int width, int height, boolean hasCollisions, Color color)
   {
      super(x, y, width, height, hasCollisions, color);
      
   }
   
   
   //all spikes are the same size so we don't need width and height
   public Spike(int x, int y, boolean hasCollisions, Color color)
   {
      super(x, y, 50, 12, hasCollisions, color);
   }
   
   public Spike(Spike other)
   {
      super(other.x, other.y, other.width, other.height, other.hasCollisions, other.color);
   }
   
   public Spike clone()
   {
      return new Spike(this);
   }
   
   public static Spike clone(Spike other)
   {
      return new Spike(other);
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
      if(width == 50)
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
      
         if (hasCollisions)
         {
            gc.setFill(color);
         // Draw the three circles with padding and spacing
            gc.fillOval(x + leftRightPadding, y, circleDiameter, circleDiameter); // First circle
            gc.fillOval(x + leftRightPadding + circleDiameter + spaceBetweenCircles, y, circleDiameter, circleDiameter); // Second circle
            gc.fillOval(x + leftRightPadding + 2 * (circleDiameter + spaceBetweenCircles), y, circleDiameter, circleDiameter); // Third circle
         }
         else
         {
            double smallerDiameter = circleDiameter - 8; // New diameter for smaller circles
            double offset = (circleDiameter - smallerDiameter) / 2; // Offset to keep the circles centered
         
            gc.setFill(Color.BLACK);
         
         //drawing black circles to show that the spikes have retracted
            gc.fillOval(x + leftRightPadding, y, circleDiameter, circleDiameter); // First circle
            gc.fillOval(x + leftRightPadding + circleDiameter + spaceBetweenCircles, y, circleDiameter, circleDiameter); // Second circle
            gc.fillOval(x + leftRightPadding + 2 * (circleDiameter + spaceBetweenCircles), y, circleDiameter, circleDiameter); // Third circle
         
            gc.setFill(color);
         
         // Draw the three smaller circles, ensuring they stay centered relative to the original positions
            gc.fillOval(x + leftRightPadding + offset, y + offset, smallerDiameter, smallerDiameter); // First circle
            gc.fillOval(x + leftRightPadding + circleDiameter + spaceBetweenCircles + offset, y + offset, smallerDiameter, smallerDiameter); // Second circle
            gc.fillOval(x + leftRightPadding + 2 * (circleDiameter + spaceBetweenCircles) + offset, y + offset, smallerDiameter, smallerDiameter); // Third circle
         }
      }
      else
      {
         double totalHeight = 50; // Total available height
         double circleDiameter = 12; // Diameter of each circle
         int numberOfCircles = 3; // Number of circles
      
      // Calculate the total space occupied by all circles combined
         double totalCirclesHeight = numberOfCircles * circleDiameter;
      
      // Calculate the remaining space after placing the circles
         double remainingSpace = totalHeight - totalCirclesHeight;
      
      // Calculate the padding on the top and bottom sides, as well as space between the circles
         double topBottomPadding = remainingSpace / 4; // Dividing by 4 for top/bottom padding and space between circles
         double spaceBetweenCircles = topBottomPadding; // Space between circles is the same as top/bottom padding
      
         if (hasCollisions)
         {
            gc.setFill(color);
         // Draw the three circles with padding and spacing vertically
            gc.fillOval(x, y + topBottomPadding, circleDiameter, circleDiameter); // First circle
            gc.fillOval(x, y + topBottomPadding + circleDiameter + spaceBetweenCircles, circleDiameter, circleDiameter); // Second circle
            gc.fillOval(x, y + topBottomPadding + 2 * (circleDiameter + spaceBetweenCircles), circleDiameter, circleDiameter); // Third circle
         }
         else
         {
            double smallerDiameter = circleDiameter - 8; // New diameter for smaller circles
            double offset = (circleDiameter - smallerDiameter) / 2; // Offset to keep the circles centered
         
            gc.setFill(Color.BLACK);
         
         // Drawing black circles to show that the spikes have retracted
            gc.fillOval(x, y + topBottomPadding, circleDiameter, circleDiameter); // First circle
            gc.fillOval(x, y + topBottomPadding + circleDiameter + spaceBetweenCircles, circleDiameter, circleDiameter); // Second circle
            gc.fillOval(x, y + topBottomPadding + 2 * (circleDiameter + spaceBetweenCircles), circleDiameter, circleDiameter); // Third circle
         
            gc.setFill(color);
         
         /*
         // Draw the three smaller circles, ensuring they stay centered relative to the original positions
//
         gc.fillOval(x + leftRightPadding + offset, y + offset, smallerDiameter, smallerDiameter); // First circle
         gc.fillOval(x + leftRightPadding + circleDiameter + spaceBetweenCircles + offset, y + offset, smallerDiameter, smallerDiameter); // Second circle
         gc.fillOval(x + leftRightPadding + 2 * (circleDiameter + spaceBetweenCircles) + offset, y + offset, smallerDiameter, smallerDiameter); // Third circle
         */
      //}
      //
            /*
            gc.fillOval(x + offset, y + topBottomPadding + offset, smallerDiameter, smallerDiameter); // First circle
            gc.fillOval(x + offset, y + topBottomPadding + circleDiameter + spaceBetweenCircles + offset, smallerDiameter, smallerDiameter); // Second circle
            gc.fillOval(x + offset, y + topBottomPadding + 2 * (circleDiameter + spaceBetweenCircles) + offset, smallerDiameter, smallerDiameter); // Third circle
            */
         }
         
      } 
   }   
   
   public Color getColor()
   {
      return color;
   }
}