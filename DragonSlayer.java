import java.util.Scanner;

/** This class represents a DragonSlayer object
 *  @author Abdul Andha
 */
 
public class DragonSlayer {

  /** Utility scanner */
  private Scanner scan;
  
  /** Whether or not the player won. */
  private boolean winStatus;
  
  /** Whether or not the game is over */
  private boolean gameOver;
  
  /** The player in the game */
  private Player player;
  
  /** The room the player is in */
  private Room currentRoom;
  
  /** The high score across multiple games */
  private static int topScore = 0;
  
  /** Instantiates a DragonSlayer object */
  public DragonSlayer() {
    Room.setRoomCount(0);
    scan = new Scanner(System.in);
    winStatus = false;
    gameOver = false;
  }
  
  /** Returns the current top score.
   *  @return The top score
   */
  public int getTopScore() {
    return topScore;
  }
  
  /** Starts and handles the game. Calls on other methods and prints the final message when the game is over. */
  public void play() {
    welcome();
    enterNewRoom();
    presentOptions();
    if (winStatus) {
      int score = player.calcScore();
      if (score > topScore) {
        topScore = score;
      }
      System.out.println("Congratulations! You managed to clear all of the rooms. You win with a score of " + score + "!");
    } else {
      System.out.println("You died. Better luck next time!");
    }
  }
  
  /** Greets the player and explains the game. */
  private void welcome() {
    System.out.println("Welcome to Dragon Slayer! What is your name, adventurer?");
    player = new Player(scan.nextLine());
    System.out.println("In a moment, you will enter the cave.");
    System.out.println("Each room of the cave will have up to 3 dragons.");
    System.out.println("You must kill every dragon in a room to advance to the next room");
    System.out.println("If you manage to clear 5 rooms, you win!");
    System.out.println("TIP: Search for hidden treasures in each room.");
    System.out.println("If you don't make it out alive, then this is goodbye.");
    
    while (true) {
      System.out.println("Are you ready?");
      String choice = scan.nextLine();
      if (choice.equals("Yes") || choice.equals("yes")) {
        break;
      }
    } 
  }
  
  /** Sets currentRoom to a new room. When all 5 rooms have been cleared, sets winStatus and gameOver to true. */
  public void enterNewRoom() {
    if (Room.getRoomCount() == 5) {
      winStatus = true;
      gameOver = true;
      return;
    }
    currentRoom = new Room(player, this);
    System.out.println("You enter " + currentRoom.getName() + ". To go to the next room, you must clear all the dragons here.");
    System.out.println(currentRoom.getDragonNum() + " dragons block your path. A dragon steps forward.");
  }
  
  /** Presents the actions available to the player at a given time */
  private void presentOptions() {
    System.out.println("----------");
    System.out.println("What will you do now?");
    System.out.println("(C)heck the dragon's health and level");
    System.out.println("(A)ttack the dragon");
    System.out.println("(S)earch the room");
    System.out.println("(V)iew your current health and inventory");
    System.out.println("Inspect your (W)eapon");
    
    if (player.hasHealthPot()) {
    System.out.println("(U)se your health potion");
    }
    System.out.println("E(x)it the game.");
    String choice = scan.nextLine();
    processChoice(choice);
  }
  
  /** Does a certain action based on the user's choice. 
   *  <p>
   *  After the given action is performed, it checks if the game is over.
   *  If it is not, it calls PresentOptions() again.
   *  @param choice  The user's choice
   */
  private void processChoice(String choice) {
    if (choice.equals("C") || choice.equals("c")) {
      currentRoom.printDragonInfo();
    } else if (choice.equals("A") || choice.equals("a")) {
      currentRoom.attackSequence();
    } else if (choice.equals("S") || choice.equals("s")) {
    
      if (!currentRoom.isSearched()) {
      
        if (currentRoom.search()) {
          System.out.println("You found a health potion!");
        } else {
          System.out.println("You found nothing.");
        }
        
      } else {
        System.out.println("This room has already been searched. Quit stalling and fight those dragons!");
      }
      
    } else if (choice.equals("V") || choice.equals("v")) {
      System.out.println(player);
    } else if (choice.equals("W") || choice.equals("w")) {
      System.out.println(player.getWeapon());
    } else if ((choice.equals("U") || choice.equals("u")) && player.hasHealthPot()) {
      player.useHealthPot();
      System.out.println("You feel refreshed! +25 HP");
    }  else if (choice.equals("X") || choice.equals("x")) {
      System.out.println("Goodbye!");
      System.exit(0);
    } else {
      System.out.println("That's not a valid choice!");
    } 
    if (player.getHealth() == 0) {
      gameOver = true;
    }
    if (!gameOver) {
      presentOptions();
    }
  }
}