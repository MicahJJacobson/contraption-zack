import javafx.scene.paint.*;
import javafx.scene.canvas.*;

//this is an example object
public class Player extends Draw
{
	//takes in its position
   public Player(float x, float y)
   {
      super(x,y);
   }
   //draws itself at the passed in x and y.
   public void drawMe(float x, float y, GraphicsContext gc)
   {
   //Creating the ovals with black outline around them
      gc.setFill(Color.RED);
      gc.fillOval(x,y,27,27);
   }
}
