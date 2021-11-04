/** This class represents a Player object
 *  @author Abdul Andha
 */

public class Player {

  /** The name of the player */
  private String name;
  
  /** The health of the player */
  private int health;
  
  /** The amount of gold the player has */
  private int gold;
  
  /** Whether or not the player has a health potion */
  private boolean hasHealthPot;
  
  /** The player's weapon */
  private Sword weapon;
  
  /** Instantiates a Player object.
   *  @param name  The name of the player. Provided by the user.
   */
  public Player(String name) {
    this.name = name;
    health = 100;
    gold = 0;
    hasHealthPot = false;
    weapon = new Sword();
  }
  
  /** Returns the name of the player.
   *  @return The name of the player 
   */
  public String getName() {
    return name;
  }
  
  /** Returns the player's weapon.
   *  @return The player's weapon 
   */
  public Sword getWeapon() {
    return weapon;
  }
  
  /** Returns whether or not the player has a health potion.
   *  @return Whether or not the player has a health potion
   */
  public boolean hasHealthPot() {
    return hasHealthPot;
  }
  
  /** Returns the amount of gold the player has.
   *  @return The amount of gold the player has 
   */  
  public int getGold() {
    return gold;
  }

  /** Returns the current health of the player.
   *  @return The current health of the player 
   */ 
  public int getHealth() {
    return health;
  }
  
  /** Sets hasHealthPot to true */
  public void giveHealthPot() {
    hasHealthPot = true;
  }
  
  /** Adds a specified amount to gold.
   *  @param amt  The amount to add to gold
   */
  public void addGold(int amt) {
    gold += amt;
  }
  
  /** Adds a specified amount to health.
   *  @param amt  The amount to add to health.
   */
  public void addHealth(int amt) {
    health += amt;
  }
  
  /** Calculates and returns the attack value of the player.
   *  <p>
   *  The player's attack equal to the weapon's attack.
   *  There is a certain chance of the attack to be tripled.
   *  This chance is represented by getCritRate() which is called on the weapon object
   *
   *  @return The attack value of the player
   */
  public int calcAttack() {
    int r = (int) (Math.random() * 100) + 1;
    if (r <= weapon.getCritRate()) {
      return weapon.getAttack() * 3;
    } else {
      return weapon.getAttack(); 
    }
  }
  
  /** Calculates the score of the player, after they win.
   *  @return The score the player got, calculated by adding gold and health.
   */
  public int calcScore() {
    return gold + health;
  }

  /** Subtracts damage from health and prints appropriate message.
   *  @param dmg  The amount to subtract from health
   */
  public void takeDamage(int dmg) {
    health -= dmg;
    if (health <= 0) {
      health = 0;
    }
    System.out.println("The dragon hits you for " + dmg + " HP and now you have " + health + " health.");
  }
  
  /** Adds 25 to health and sets hasHealthPot to false */
  public void useHealthPot() {
    health += 25;
    hasHealthPot = false;
  }

  /** Returns a String that includes values
   *  of the four instance variables (the object state)
   *
   *  @return  String representation of the object's state
   */
  public String toString() {
    String str = "";
    str += name + ":\n";
    str += "Health = " + health + "\n";
    str += "Health Pot = " + hasHealthPot + "\n";
    str += "Gold = " + gold;
    return str;
  }
}