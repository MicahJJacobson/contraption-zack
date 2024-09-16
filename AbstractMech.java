public abstract class AbstractMech
{
   int x;
   int y;
   
   ArrayList<AbstractMech> mechs;
   
   public abstract void doThing();
   public void engage()
   {
      doThing();
      for(int i = 0; i<mechs.size(); i++)
      {
         mechs.engage();
      }
   }
   public abstract void drawMe(Graphicscontext gc);
}   

public class Button extends AbstractMech
   {
      public void doThing()
      {
         
      
      }
      
      
      public void drawMe(Graphicscontext gc)
      {
      
      
      
      }
      
      
      
      
      
      
      
      
      
      
   }

   