import java.util.Scanner;

public class ConnectFourMain {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean isPlaying = true;

        while (isPlaying) {
            //Types: 0 (Human), 1 (Random Select), 2 (Random Select w/ Blocking), 3 (Random Select w/ Blocking
            //and Prioritization), 4 (Alpha-Beta Prune)
            
            //for running a large number of games for data comparison
            ConnectFourGame game = new ConnectFourGame(0, 4, 2, 0);
            game.StartGame();

            //for being able to customize a single game at runtime
            /*ConnectFourGame game = new ConnectFourGame();
            game.StartGame();*/

            System.out.println("Enter 'R' to play again, or anything else to Quit.");
            if(!input.nextLine().equalsIgnoreCase("R")) {
                isPlaying = false;
            }
        }
    }
}
