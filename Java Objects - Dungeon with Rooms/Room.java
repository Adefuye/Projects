/*
  Emmanuel Adefuye [EMAX]
  26/4/2019
*/
public class Room
{
//------------------------I N S T A N C E  V A R I A B L E S------------------------
  private String description;
  private String name;
  private Room north;
  private Room east;
  private Room south;
  private Room west;

  public Room(String name, String description)
  {
    this.name = name;
    this.description = description;
  }//end of constructor

  public void setEast(Room east){this.east = east;}
  public void setWest(Room west){this.west = west;}
  public void setNorth(Room north){this.north = north;}
  public void setSouth(Room south){this.south = south;}

//------------------------------E X I T S--------------------------------

  public void setExits(Room n, Room e, Room w, Room s)
  {
    this.north = n;
    this.south = s;
    this.east = e;
    this.west = w;
  }
//------------------G E T  E X I T S  F O R  R O O M S--------------------
  public Room getEast(){return this.east;}
  public Room getWest(){return this.west;}
  public Room getNorth(){return this.north;}
  public Room getSouth(){return this.south;}

  public String getDescription(){return description;}
  public String getName(){return name;}//returns the created Room's name

  public String getExits()
  {
    String northName = (north==null)?"None":north.getName();
    String southName = (south==null)?"None":south.getName();
    String eastName = (east==null)?"None":east.getName();
    String westName = (west==null)?"None":west.getName();
    return String.format("[N]orth: %s\n[E]ast: %s\n[W]est: %s\n[S]outh: %s", northName, eastName, westName, southName);
  }
  public String toString()
  {
    return String.format("%s [%s]\n%s", name, description, getExits());
  }
}
