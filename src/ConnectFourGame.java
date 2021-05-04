import java.util.Scanner;

public class ConnectFourGame {

    public int NUM_ROWS = 6;
    public int NUM_COLUMNS = 7;
    public int MAX_TURNS = 42;
    private Scanner input;
    public char[][] board;
    public ConnectFourAI computerAI;

    /**
     * Initializes the Connect Four Board
     */
    ConnectFourGame() {
        input = new Scanner(System.in);
        board = new char[NUM_ROWS][NUM_COLUMNS];
        computerAI = new ConnectFourAI();

        //initialize board
        for (int row = 0; row < board.length; row++){
            for (int col = 0; col < board[0].length; col++){
                board[row][col] = ' ';
            }
        }
    }

    /**
     * Manages the current Connect Four Game
     */
    public void StartGame() {
        int turn = 1;
        char player = 'R';
        boolean winner = false;

        //play a turn
        while (!winner && turn <= MAX_TURNS){
            int play;

            do {
                displayBoard();

                System.out.print("Player " + player + ", choose a column: ");
                play = input.nextInt() - 1;

            } while (!validateMove(play));

            //drop the checker
            for (int row = board.length-1; row >= 0; row--){
                if(board[row][play] == ' '){
                    board[row][play] = player;
                    break;
                }
            }

            //determine if there is a winner
            winner = isWinner(player);

            //switch players
            if (player == 'R'){
                player = 'B';
            }else{
                player = 'R';
            }

            play = computerAI.AlphaBetaSearch(board, 1, player);

            //drop the checker
            for (int row = board.length-1; row >= 0; row--){
                if(board[row][play] == ' '){
                    board[row][play] = player;
                    break;
                }
            }

            winner = isWinner(player);

            //switch players
            if (player == 'R'){
                player = 'B';
            }else{
                player = 'R';
            }

            turn++;
        }
        displayBoard();

        if (winner){
            if (player == 'R'){
                System.out.println("Black won");
            } else {
                System.out.println("Red won");
            }
        } else {
            System.out.println("Tie game");
        }
    }

    /**
     * Displays the current game board
     */
    public void displayBoard(){
        System.out.println("   1   2   3   4   5   6   7");
        System.out.println(" - - - - - - - - - - - - - - -");
        for (int row = 0; row < board.length; row++){
            System.out.print(" | ");
            for (int col = 0; col < board[0].length; col++){
                System.out.print(board[row][col]);
                System.out.print(" | ");
            }
            System.out.println("\n - - - - - - - - - - - - - - -");
        }
        System.out.println("   1   2   3   4   5   6   7\n");
    }

    /**
     * Checks if the current move is valid (Within the board and the column isn't full)
     * @param column the selected column a piece is dropped in
     * @return Whether the current move is valid or not
     */
    public boolean validateMove(int column){
        return !(column < 0 || column >= NUM_COLUMNS || board[0][column] != ' ');
    }

    /**
     * Checks if a player has won the game
     * @param player the current player
     * @return Whether the current player has won or not
     */
    public boolean isWinner(char player) {
        //check for 4 across
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length - 3; col++) {
                if (board[row][col] == player &&
                        board[row][col + 1] == player &&
                        board[row][col + 2] == player &&
                        board[row][col + 3] == player) {
                    return true;
                }
            }
        }
        //check for 4 up and down
        for (int row = 0; row < board.length - 3; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col] == player &&
                        board[row + 1][col] == player &&
                        board[row + 2][col] == player &&
                        board[row + 3][col] == player) {
                    return true;
                }
            }
        }
        //check upward diagonal
        for (int row = 3; row < board.length; row++) {
            for (int col = 0; col < board[0].length - 3; col++) {
                if (board[row][col] == player &&
                        board[row - 1][col + 1] == player &&
                        board[row - 2][col + 2] == player &&
                        board[row - 3][col + 3] == player) {
                    return true;
                }
            }
        }
        //check downward diagonal
        for (int row = 0; row < board.length - 3; row++) {
            for (int col = 0; col < board[0].length - 3; col++) {
                if (board[row][col] == player &&
                        board[row + 1][col + 1] == player &&
                        board[row + 2][col + 2] == player &&
                        board[row + 3][col + 3] == player) {
                    return true;
                }
            }
        }
        return false;
    }
}
