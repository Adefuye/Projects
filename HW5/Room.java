public class Room
{

  private String name;
  private String desc;
  private Room north;
  private Room south;
  private Room east;
  private Room west = null;

  public Room(String name, String desc)
  {
    this.name = name;
    this.desc = desc;
  }

  public void setExits(Room north, Room south, Room east,Room west)
  {
    this.north = north;
    this.south = south;
    this.east = east;
    this.west = west;
  }

//Getter and setters plus the setExits Shyt
}
