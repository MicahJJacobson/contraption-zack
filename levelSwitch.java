

public class levelSwitch 
{
    //side winning block is on (in pixels), window for player to meet
    int winningSide, boundary1, boundary2;
    //shows winning direction as string
    String winningDirection;
    String nextLevel;
    //is winning block inside or outside boundaries
    boolean inorout;
    
    
    //direction of next level
    public levelSwitch(String winningDirect) 
    {
        winningDirection = winningDirect;
    }
    
    //copy constructor
    public levelSwitch(levelSwitch other)
    {
      this.winningDirection = new String(other.winningDirection);
      if(other.nextLevel != null)
      {
         this.nextLevel = new String(other.nextLevel);
      }
      this.inorout = other.inorout;
      this.winningSide = other.winningSide;
      this.boundary1 = other.boundary1;
      this.boundary2 = other.boundary2;
    }
    
    //winning conditions 
    public void levelInput(int winning, int bound, int bound2) 
    {
    
      winningSide = winning;
      boundary1 = bound;
      boundary2 = bound2;
    
    }
    //from text file get if player is going to leave boundaries or not
    public void Inbound(boolean in)
    {
      inorout=in; 
    }
    //stores next level
    public void staging(String in)
    {
      nextLevel=in;  
    }
    //gets next level
    public String getStaging()
    {
      return nextLevel;  
    }
    //checks if player needs to leave boundaries to go to next level
    public boolean isInbound()
    {
      return inorout;
    }
    //Check if player is out of boundaries to o to next (if specified)
    public boolean isOut(int posx, int posy)
    {
      //player.getX() > nextLevelL && player.getY()+25 < nextLevelU && player.getX()+50 <= nextLevelR
      if(winningDirection.equals("up"))
      {
         if (posx > boundary1 && posy+25 <= winningSide && posx+25 <= boundary2)
         {
            return true;
         }
         else 
         {
            return false;
         }
      }
      else if(winningDirection.equals("left"))
      {
         if (posy > boundary1 && posx+25 <= winningSide && posy+25 <= boundary2)
         {
            return true;
         }
         else 
         {
            return false;
         }
      }
      else if(winningDirection.equals("right"))
      {
         if (posy > boundary1 && posx-25 >= winningSide && posy+25 <= boundary2)
         {
            return true;
         }
         else 
         {
            return false;
         }
      }
      else if(winningDirection.equals("down"))
      {
         if (posx > boundary1 && posy-25 >= winningSide && posx+25 <= boundary2)
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
    //check if player can go to the next level block
    public boolean canGo(int posx, int posy)
    {
      if(winningDirection.equals("up"))
      {
         if (posx > boundary1 && posy <= winningSide && posx+25 <= boundary2)
         {
            return true;
         }
         else 
         {
            return false;
         }
      }
      else if(winningDirection.equals("left"))
      {
         if (posy > boundary1 && posx <= winningSide && posy+25 <= boundary2)
         {
            return true;
         }
         else 
         {
            return false;
         }
      }
      else if(winningDirection.equals("right"))
      {
         if (posy > boundary1 && posx >= winningSide && posy+25 <= boundary2)
         {
            return true;
         }
         else 
         {
            return false;
         }
      }
      else if(winningDirection.equals("down"))
      {
         if (posx > boundary1 && posy >= winningSide && posx+25 <= boundary2)
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