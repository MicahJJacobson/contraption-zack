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
public class Door extends AbstractMech
{
   public Door(int x, int y, int width, int height)
      {
         super(x, y, width, height);
      }
   
   public Door(int x, int y, int width, int height, boolean hasCollisions)
      {
         super(x, y, width, height, hasCollisions);
      }

   public void drawMe(GraphicsContext gc, Color color)
      {
         gc.setFill(color);
         gc.fillRect(x, y, width, height);
      }
   public void doThing()
      {
         
      }



















}