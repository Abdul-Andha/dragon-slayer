/** This class represents a Dragon object
 *  @author Abdul Andha
 */
 
public class Dragon {

  /** The dragon's health */
  private int health;
  
  /** The dragon's level */
  private int level;
  
  /** Whether or not the dragon is dead */
  private boolean dead;
  
  /** Instantiates a Dragon object */
  public Dragon() {
    health = 100;
    level = (int) (Math.random() * 3) + 1;
    dead = false;
  }
  
  /** Returns the health of the dragon.
   *  @return The health of the dragon
   */
  public int getHealth() {
    return health;
  }
  
  /** Returns the level of the dragon.
   *  @return The level of the dragon
   */
  public int getLevel() {
    return level;
  }
  
  /** Returns whether or not the dragon is dead.
   *  @return Whether or not the dragon is dead.
   */
  public boolean isDead() {
    return dead;
  }
  
  /** Subtracts damage from health and prints appropriate message.
   *  @param damage  The amount to subtract from health
   */
  public void takeDamage(int damage) {
    if (dead) {
      System.out.println("The dragon is already dead.");
    } else {
      health -= damage;
      if (health <= 0) {
        health = 0;
        System.out.println("The dragon takes " + damage + " damage and now has " + health + " health.");
        System.out.println("The dragon has been slain!");
        dead = true;
      } else {
        System.out.println("The dragon takes " + damage + " damage and now has " + health + " health.");
      }
    }
  }
  
  /** Calculates and returns the attack value of the dragon.
   *  <p>
   *  The dragon's attack is calculated by multiplying its level
   *  by a random integer from 1 to 10.
   *
   *  @return The attack value of the dragon
   */
  public int calcAttack() {
    int attack = ((int) (Math.random() * 10) + 1) * level;
    return attack;
  }
  
  /** Returns a String that includes values
   *  of the two instance variables (the object state)
   *
   *  @return  String representation of the object's state
   */
  public String toString() {
    String str = "";
    str += "Dragon:\n";
    str += "Health = " + health + "\n";
    str += "Level = " + level + "\n";
    return str;
  }
}