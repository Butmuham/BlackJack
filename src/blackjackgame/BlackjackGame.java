import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BlackjackGame {
    public static void main(String[] args) {
        System.out.println("Welcome to Blackjack!");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            Game game = new Game();
            game.play();
            System.out.print("Do you want to play again? (yes/no): ");
            String replay = scanner.next().toLowerCase();
            if (!"yes".equals(replay)) {
                break;
            }
        }
    }
}