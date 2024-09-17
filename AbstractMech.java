public abstract class AbstractMech
{
   int x;
   int y;
   int height;
   int width;
   
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
   public int getHeight()
   {
      return height;
   }
   public int getWidth()
   {
      return width;
   }   
}   

      
      
      
      
      
      
      
      

   