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
<<<<<<< HEAD
      gc.fillOval(x,y,50,50);
=======
      gc.fillOval(x,y,27,27);
>>>>>>> 1997a02d0ecdb7dc8e11221fac688f50e25949a9
   }
}
