import javafx.scene.paint.*;
import javafx.scene.canvas.*;

//this is an example object
public class Player
{
   private int x;
   private int y;
   private int size;
	//takes in its position
   public Player(int x, int y)
   {
      this.x = x;
      this.y = y;
      size = 25;
   }
   
   public Player(int x, int y, int size)
   {
      this.x = x;
      this.y = y;
      this.size = size;
   }
   //draws itself at the passed in x and y.
   public void drawMe(int x, int y, GraphicsContext gc)
   {
   //Creating the ovals with black outline around them
      gc.setFill(Color.RED);
      gc.fillRect(x,y,size,size);
   }
   
   public int getSize()
   {
      return size;
   }
   
   public int getX()
   {
      return x;
   }
   
   public int getY()
   {
      return y;
   }
   
   public void setX(int x)
   {
      this.x = x;
   }
   
   public void setY(int y)
   {
      this.y = y;
   }
}
