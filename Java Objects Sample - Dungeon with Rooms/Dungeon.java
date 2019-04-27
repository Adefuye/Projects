/*
  Emmanuel Adefuye [EMAX]
  26/4/2019
*/
public class Dungeon
{
  private Room balcony;
  private Room bedroom1;
  private Room bedroom2;
  private Room kitchen;
  private Room dining;
  private Room northHall;
  private Room southHall;

//-----------------------------C R E A T E  R O O M S---------------------------
  public Dungeon()
  {
    balcony = new Room("Balcony","It's vacant out here");
    bedroom1 = new Room("Master Bedroom","The bed is a King Size");
    bedroom2 = new Room("Guest Bedroom","The bed is a Queen Size");
    kitchen = new Room("Kitchen","Food smells good");
    dining = new Room("Dining Room","The table is big");
    southHall = new Room("Southern Hall","It's clean in here");
    northHall = new Room("Northern Hall","It's clean in here");

    balcony.setExits(null, null, null, northHall);
    northHall.setExits(balcony, kitchen, bedroom1, southHall);
    southHall.setExits(northHall, dining, bedroom2, null);
    bedroom1.setExits(null, northHall, null, bedroom2);
    bedroom2.setExits(bedroom1, southHall, null, null);
    kitchen.setExits(null, null, northHall, dining);
    dining.setExits(kitchen, southHall, null, null);
  }
//------------------------------C U R R E N T  R O O M--------------------------
  public Room getCurrentRoom()
  {
    return balcony;
  }


}//end class
