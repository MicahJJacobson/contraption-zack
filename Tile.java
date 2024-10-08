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

public class Tile extends AbstractMech
{
   public Tile(int x, int y, Color color)
   {
      super(x, y, 50, 50, false, color);
   }
   
   public Tile(Tile other)
   {
      super(other.x, other.y, other.width, other.height, other.hasCollisions, other.color);
   }
   
   public Tile clone()
   {
      return new Tile(this);
   }
   
   public static Tile clone(Tile other)
   {
      return new Tile(other);
   }
   
   public void drawMe(GraphicsContext gc)
   {
      gc.setFill(Color.BLACK);
      gc.fillRect(x, y, width, height);
      gc.setFill(color);
      gc.fillRect(x + 1, y + 1, width - 2, height - 2);
   }
}