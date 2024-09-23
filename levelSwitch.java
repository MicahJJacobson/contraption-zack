

public class levelSwitch 
{
    int winningSide, boundary1, boundary2;
    boolean inorout;
    
    public levelSwitch() 
    {
        //this.currentLevel = 1; // Default starting level
    }
    
    public void levelInput(int winning, int bound, int bound2) 
    {
    
      winningSide = winning;
      boundary1 = bound;
      boundary2 = bound2;
    
    }
    
    public void Inbound(boolean in)
    {
      inorout=in; 
    }
    
    public boolean isInbound()
    {
      return inorout;
    }
    
    public boolean isOut(int posx, int posy, String winning)
    {
      //player.getX() > nextLevelL && player.getY()+25 < nextLevelU && player.getX()+50 <= nextLevelR
      if(winning.equals("up"))
      {
         if (posx > boundary1 && posy+25 <= winningSide && posx+50 <= boundary2)
         {
            return true;
         }
         else 
         {
            return false;
         }
      }
      else if(winning.equals("left"))
      {
         if (posy > boundary1 && posx+25 <= winningSide && posy+50 <= boundary2)
         {
            return true;
         }
         else 
         {
            return false;
         }
      }
    return false;

    }
    
    public boolean canGo(int posx, int posy, String winning)
    {
      if(winning.equals("up"))
      {
         if (posx > boundary1 && posy <= winningSide && posx+50 <= boundary2)
         {
            return true;
         }
         else 
         {
            return false;
         }
      }
      else if(winning.equals("left"))
      {
         if (posy > boundary1 && posx <= winningSide && posy+50 <= boundary2)
         {
            return true;
         }
         else 
         {
            return false;
         }
      }
    return false;
    }

}