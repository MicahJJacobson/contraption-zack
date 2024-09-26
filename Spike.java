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

public class Spike extends AbstractMech
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
   public Spike(int x, int y, boolean hasCollisions, Color color)
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
   
   public void drawMe(GraphicsContext gc, Color color)
   {
      
   }
   
   public void drawMe(GraphicsContext gc)
   {
   /*
      gc.setFill(color);
      gc.fillOval(x, y, 12, 12);
      gc.fillOval(x + 12 + (12/2), y, 12, 12);
      gc.fillOval(x + 24 + (12/2), y, 12, 12);
      
   */
   
   /*
      double totalWidth = 50; // Total available width
      double circleDiameter = 12; // Diameter of each circle
      double spaceBetweenCircles = (totalWidth - (3 * circleDiameter)) / 2; // Calculate the space between the circles

      gc.setFill(color);
      // Draw the three circles with even spacing
      gc.fillOval(x, y, circleDiameter, circleDiameter); // First circle
      gc.fillOval(x + circleDiameter + spaceBetweenCircles, y, circleDiameter, circleDiameter); // Second circle
      gc.fillOval(x + 2 * (circleDiameter + spaceBetweenCircles), y, circleDiameter, circleDiameter); // Third circle
   */
   
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

    gc.setFill(color);
    // Draw the three circles with padding and spacing
    gc.fillOval(x + leftRightPadding, y, circleDiameter, circleDiameter); // First circle
    gc.fillOval(x + leftRightPadding + circleDiameter + spaceBetweenCircles, y, circleDiameter, circleDiameter); // Second circle
    gc.fillOval(x + leftRightPadding + 2 * (circleDiameter + spaceBetweenCircles), y, circleDiameter, circleDiameter); // Third circle
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