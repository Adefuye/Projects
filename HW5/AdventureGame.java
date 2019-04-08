import java.util.Scanner;

public class AdventureGame
{
  public static void main(String[] args)
  {
    getDesc();
    prompt();
  }//end main
  
 private static String[] roomDesc =
  {
    "You are now in Bedroom2, exits are north and east",
    "You are now in South Hall, exits are north, east and west",
    "You are now in the Dining Room, exits are north and west",
    "You are now in Bedroom1, exits are east and south",
    "You are now in North Hall, exits are north, east, west and south",
    "You are now in the Kitchen, exits are west and south",
    "You are now in the Balcony, exits are soouth"
  };

  private static int[][] exits = {{3,1,-1,-1},{4,2,0,-1},{5,-1,1,-1},{-1,4,-1,0},{6,5,3,1},{-1,-1,4,2},{-1,-1,-1,4}};

  private static int currentRoom = 0;
  private static int north = 0;
  private static int south = 1;
  private static int east = 2;
  private static int west = 3;

  public static void move()
  {
    Scanner input = new Scanner(System.in);
      String interaction = input.nextLine();
      if(interaction.matches("N|n"))
      {
        currentRoom = exits[currentRoom][north];
        if(exits[currentRoom][north] <= -1){System.out.println("Can't go this way");prompt();}
        getDesc();
      }
      else if(interaction.matches("S|s"))
      {
        currentRoom = exits[currentRoom][south];
        if(exits[currentRoom][south] <= -1){System.out.println("Can't go this way");prompt();}
        getDesc();
      }
      else if(interaction.matches("E|e"))
      {
        currentRoom = exits[currentRoom][east];
        if(exits[currentRoom][east] <= -1){System.out.println("Can't go this way");prompt();}
        getDesc();
      }
      else if(interaction.matches("W|w"))
      {
        currentRoom = exits[currentRoom][west];
        if(exits[currentRoom][west] <= -1){System.out.println("Can't go this way");prompt();}
        getDesc();
      }
      else{System.out.println("Can't go this way");prompt();}
  }

  public static void prompt()
  {
    System.out.println("Where would you like to go? \n N for North \n S for South \n W for West \n E for east" );
    move();
  }

  public static void getDesc()
  {
    System.out.println(roomDesc[currentRoom]);
    prompt();
  }

}//end class
