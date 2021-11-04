/** This class represents a Room object
 *  @author Abdul Andha
 */

public class Room {

  /** The name of the room */
  private String name;
  
  /** Whether or not the room has been cleared of dragons */
  private boolean isClear;
  
  /** Whether or not the room has been searched */
  private boolean searched;
  
  /** An array of dragons in the room */
  private Dragon[] dragons;
  
  /** The dragon that the player is currently facing */
  private Dragon currentDragon;
  
  /** The player in the room */
  private Player player;
  
  /** The number of rooms that have been created in one game */
  private static int roomCount = 0;
  
  /** A DragonSlayer object*/
  private DragonSlayer dragonSlayer;
  
  /** An array of possible room names */
  private String[] names = {"The Den", "The Lair", "The Cavern", "The Grotto", "The Pit"};
  
  /** Instantiates a Room object.
   *
   *  @param player  The player entering the room
   *  @param dragonSlayer  The DragonSlayer object of the room
   */
  public Room(Player player, DragonSlayer dragonSlayer) {
    this.name = names[roomCount];
    roomCount++;
    isClear = false;
    searched = false;
    this.player = player;
    this.dragonSlayer = dragonSlayer;
    
    int numDragons = (int) (Math.random() * 3) + 1;
    dragons = new Dragon[numDragons];
    
    for (int i = 0; i < dragons.length; i++) {
      dragons[i] = new Dragon();
    }
    currentDragon = dragons[0];
  }
  
  /** Returns the name of the room.
   *  @return The name of the room 
   */
  public String getName() {
    return name;
  }
  
  /** Returns the number of dragons in the room.
   *  @return The number of dragons in the room. 
   */
  public int getDragonNum() {
    return dragons.length;
  }
  
  /** Returns whether or not the room has been searched.
   *  @return Whether or not the room has been searched.
   */
  public boolean isSearched() {
    return searched;
  }
  
  /** Returns the number of rooms created.
   *  @return The number of rooms created.
   */
  public static int getRoomCount() {
    return roomCount;
  }
  
  /** Returns the number of rooms created.
   *  @param num  The new value of roomCount.
   *
   *  @return The number of rooms created.
   */
  public static void setRoomCount(int num) {
    roomCount = num;
  }
  
  /** Searches the room for a health potion. There is a 50% chance to find one.
   *  @return Whether or not a health potion was found
   */
  public boolean search() {
    searched = true;
    if (Math.random() > 0.5) {
      player.giveHealthPot();
      return true;
    }
    return false;
  }
  
  /** Performs one attack sequence.
   *  <p>
   *  First, the player attacks the dragon.
   *  If the dragon survives, it attacks the player and there is a chance that the player dodges.
   *  If the dragon dies, a random event occurs.
   *  If all dragons die, a message is printed and enterNewRoom() is called on the dragonSlayer object.
   */
  public void attackSequence() {
    currentDragon.takeDamage(player.calcAttack());
    if (!currentDragon.isDead()) {
    
      int r = (int) (Math.random() * 100) + 1;
      if (r <= player.getWeapon().getDodgeRate()) {
        System.out.println("The dragon swipes but you dodge!");
      } else {
        player.takeDamage(currentDragon.calcAttack());
      }
      
    } else {
      randomDrop();
      updateCurrentDragon();
      if (currentDragon != null) {
        System.out.println("The next dragon steps forward.");
      } else {
        System.out.println("All dragons have been slain!");
        dragonSlayer.enterNewRoom();
      }
    }
  }
  
  /** Prints information about the current dragon */
  public void printDragonInfo() {
    updateCurrentDragon();
    System.out.println(currentDragon);
  }
   /** Private helper method that performs one of four random events.
   *  <p>
   *  The random events include:
   *  The player earns 50 gold.
   *  The player's weapon is upgraded.
   *  The player's health is increased by 35.
   *  The player find nothing.
   */
  private void randomDrop() {
    int r = (int) (Math.random() * 4) + 1;
    
    if (r == 1) {
      player.addGold(50);
      System.out.println("You pick up 50 gold from the dragon's corpse!");
    }
    if (r == 2) {
      player.getWeapon().upgrade();
      System.out.println("Your sword got stronger from the fight!");
    }
    if (r == 3) {
      player.addHealth(35);
      System.out.println("A healing effect radiates from the corpse! +35 HP");
    }
    if (r == 4) {
      System.out.println("You investigate the corpse but find nothing.");
    }
  }
  
  /** Private helper method that updates currentDragon to the first alive dragon in dragons[].
   *  If all dragons are dead, sets currentDragon to null.
   */
  private void updateCurrentDragon() { 
    for (int i = 0; i < dragons.length; i++) {
      if (!dragons[i].isDead()) {
        currentDragon = dragons[i];
        break;
      }
      currentDragon = null;
    }  
  }
}