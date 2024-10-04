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

public class Wall extends AbstractMech
{
   public Wall(int x, int y, int width, int height, Color color)
   {
      super(x, y, width, height, true, color);
   }
   
   public Wall(int x, int y, int width, int height, boolean hasCollisions, Color color)
   {
      super(x, y, width, height, hasCollisions, color);
   }
   
   public void drawMe(GraphicsContext gc)
   {
      gc.setFill(Color.BLACK);
      gc.fillRect(x, y, width, height);
      gc.setFill(color);
      gc.fillRect(x + 1, y + 1, width - 2, height - 2);
      
   }
   
}