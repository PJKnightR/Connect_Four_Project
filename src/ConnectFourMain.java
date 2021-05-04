import java.util.Scanner;

public class ConnectFourMain {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean isPlaying = true;

        while (isPlaying) {
            ConnectFourGame game = new ConnectFourGame(1, 4, 2, 9);
            game.StartGame();

            System.out.println("Enter 'R' to play again, or anything else to Quit.");
            if(!input.nextLine().equalsIgnoreCase("R")) {
                isPlaying = false;
            }
        }
    }
}
