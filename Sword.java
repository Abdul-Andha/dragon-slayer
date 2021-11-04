/** This class represents a Sword object
 *  @author Abdul Andha
 */
 
public class Sword {
  
  /** The sword's attack value */
  private int attack;
  
  /** The swprd's dodge chance */
  private int dodgeRate;
  
  /** The sword's critical hit rate */
  private int critRate;
  
  /** Instantiates a Sword object. */
  public Sword() {
    attack = 10;
    dodgeRate = 20;
    critRate = 15;
  }
  
  /** Returns the attack of the sword.
   *  @return The attack of the sword 
   */
  public int getAttack() {
    return attack;    
  }
  
  /** Returns the critical hit rate of the sword.
   *  @return The critical hit rate of the sword
   */
  public int getCritRate() {
    return critRate;
  }

  /** Returns the dodge chance of the sword.
   *  @return The cdodge chance of the sword
   */
  public int getDodgeRate() {
    return dodgeRate;
  }
  
  /** Upgrades the sword
   *  <p>
   *  The attack is increased by 10.
   *  The dodgeRate is increated by 5.
   *  The critRate is increased by 7.
   */
  public void upgrade() {
    attack += 10;
    dodgeRate += 5;
    critRate += 7;
  }

  /** Returns a String that includes values
   *  of the three instance variables (the object state)
   *
   *  @return  String representation of the object's state
   */
  public String toString() {
    String str = "";
    str += "Sword:\n";
    str += "Attack = " + attack + "\n";
    str += "Crit Rate = " + critRate + "\n";
    str += "Dodge Rate = " + dodgeRate;
    return str;
  }
}