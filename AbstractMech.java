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
   public abstract void activate(AbstractMech mech);
}   

      
      
      
      
      
      
      
      

   