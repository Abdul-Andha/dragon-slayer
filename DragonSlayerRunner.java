// Abdul Andha

import java.util.Scanner;

public class DragonSlayerRunner {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    boolean playAgain = true;
    while (playAgain) {
      DragonSlayer game = new DragonSlayer();
      game.play();
      System.out.println("\nThe high score is: " + game.getTopScore());
      System.out.println();
      System.out.println("Play Again? (y/n)");
      if (scan.nextLine().equals("n")) {
        System.out.println("Goodbye!");
        playAgain = false;
      }
    }
  }
}
