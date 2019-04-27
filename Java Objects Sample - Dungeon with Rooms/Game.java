/*
  Emmanuel Adefuye [EMAX]
  26/4/2019
*/
import java.util.Scanner;

public class Game
{
  private static Dungeon house = new Dungeon();//create Dungeon containing every room in Dungeon.java
  private static Room currentRoom = house.getCurrentRoom();
//-----------------------------------------M A I N---------------------------------------
  public static void main(String[] args)
  {
    prompt();
  }
//------------------------------------------M O V E--------------------------------------
  public static void move()
  {
    Scanner input = new Scanner(System.in);
    String response = input.nextLine();

    if(response.matches("S|s") && currentRoom.getSouth()!=null)
      {currentRoom = currentRoom.getSouth(); prompt();}
    else if(response.matches("N|n") && currentRoom.getNorth()!=null)
      {currentRoom = currentRoom.getNorth(); prompt();}
    else if(response.matches("E|e") && currentRoom.getEast()!=null)
      {currentRoom = currentRoom.getEast(); prompt();}
    else if(response.matches("W|w") && currentRoom.getWest()!=null)
      {currentRoom = currentRoom.getWest(); prompt();}
    else if(response.matches("Q|q"))
      {System.out.println("Shutting Down..."); System.exit(0);}
    else{System.out.println("\nThere's no exit this way"); prompt();}
  }
//--------------------------------------P R O M P T-------------------------------------
  public static void prompt()
  {System.out.println("\nYou're currently in the "+currentRoom+"\n[Q] to BoomTube outta here\nWhere would you like to go?"); move();}
}
