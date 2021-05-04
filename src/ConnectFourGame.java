import java.util.Scanner;

public class ConnectFourGame {

    public int NUM_ROWS = 6;
    public int NUM_COLUMNS = 7;
    public int MAX_TURNS = 42;
    private Scanner input;
    public char[][] board;
    public ConnectFourAI computerAI;
    public int player1AI, player2AI;
    public int alphaBetaSearchDepth;

    /**
     * Initializes the Connect Four Board
     */
    ConnectFourGame() {
        input = new Scanner(System.in);
        board = new char[NUM_ROWS][NUM_COLUMNS];
        computerAI = new ConnectFourAI();
        player1AI = 0;
        player2AI = 0;

        //initialize board
        for (int row = 0; row < board.length; row++){
            for (int col = 0; col < board[0].length; col++){
                board[row][col] = ' ';
            }
        }

        player1AI = initializePlayer(1);
        player2AI = initializePlayer(2);
    }

    /**
     * Initializes what type of player each is
     * @param playerNum the numerical id of the player type (0 being human, 1 being random AI)
     * @return the numerical player type id
     */
    public int initializePlayer(int playerNum) {
        System.out.println("Is player " + playerNum + " a human or an AI? Enter the corresponding number below:\n1.\tHuman\n2.\tAI");
        int choice = input.nextInt();
        while (choice <= 0 || choice > 2) {
            System.out.println("Please enter a valid option number.");
            choice = input.nextInt();
        }

        if (choice == 1) {
            return 0;
        } else {
            System.out.println("Choose an AI for Player " + playerNum + ":\n1.\tRandom Selection AI\n2.\tRandom Selection with Blocking AI\n3.\tRandom Selection with Blocking and Prioritization AI\n4.\tAlpha-Beta Pruning AI\n-1.\tCancel");

            choice = input.nextInt();
            while (choice > 4 || choice < -1 || choice == 0) {
                System.out.println("Please enter a valid option number.");
                choice = input.nextInt();
            }

            if (choice == -1) {
                return initializePlayer(playerNum);
            } else if (choice == 4) {
                System.out.println("Enter the depth for alpha-beta to search. (No more then 9 recommended) (Caps off at 10 due to poor performance)");

                int depth = input.nextInt();
                while (depth < 1 || depth > 10) {
                    depth = input.nextInt();
                }
                alphaBetaSearchDepth = depth;
            }

            return choice;
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
            int play = 0;
            player = 'R';

            play = playerTurn(player1AI, 1);

            //drop the checker
            for (int row = board.length-1; row >= 0; row--){
                if(board[row][play] == ' '){
                    board[row][play] = player;
                    break;
                }
            }

            displayBoard();

            //determine if there is a winner
            winner = isWinner(player);

            if (!winner) {
                player = 'B';
                play = playerTurn(player2AI, 2);

                //drop the checker
                for (int row = board.length-1; row >= 0; row--){
                    if(board[row][play] == ' '){
                        board[row][play] = player;
                        break;
                    }
                }

                displayBoard();

                //determine if there is a winner
                winner = isWinner(player);
            }

            turn = turn + 2;
        }

        if (winner){
            if (player == 'B'){
                System.out.println("Black won");
            } else {
                System.out.println("Red won");
            }
        } else {
            System.out.println("Tie game");
        }
    }

    /**
     * Returns the column that a player plays a piece in
     * @param playerTypeID the kind of player performing the action
     * @param playerID the number of the current player
     * @return the column that a player plays a piece in
     */
    public int playerTurn(int playerTypeID, int playerID) {
        int play;

        if (playerTypeID == 1) {
            do {
                play = computerAI.randomSelection();
            } while (!validateMove(play));
        } else if (playerTypeID == 2) {
            do {
                if (playerID == 1) {
                    play = computerAI.randomSelectionBlocking(board, 'B');
                } else {
                    play = computerAI.randomSelectionBlocking(board, 'R');
                }
            } while (!validateMove(play));
        } else if (playerTypeID == 3) {
            do {
                if (playerID == 1) {
                    play = computerAI.randomSelectionBlockingPrioritization(board, 'R', 'B');
                } else {
                    play = computerAI.randomSelectionBlockingPrioritization(board, 'B', 'R');
                }
            } while (!validateMove(play));
        } else if (playerTypeID == 4) {
            do {
                char p = 'x';
                if (playerID == 1){
                    p = 'B';
                }else{
                    p = 'R';
                }
                play = computerAI.AlphaBetaSearch(board, alphaBetaSearchDepth, p);
            } while (!validateMove(play));
        } else {
            do {
                displayBoard();

                System.out.print("Player " + playerID + ", choose a column: ");
                play = input.nextInt() - 1;

            } while (!validateMove(play));
        }

        return play;
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
            for (int col = 0; col < board[0].length; col++) {
                if (col < 4) {
                    if (board[row][col] == player &&
                            board[row][col + 1] == player &&
                            board[row][col + 2] == player &&
                            board[row][col + 3] == player) {
                        return true;
                    }
                } else {
                    if (board[row][col] == player &&
                            board[row][col - 1] == player &&
                            board[row][col - 2] == player &&
                            board[row][col - 3] == player) {
                        return true;
                    }
                }
            }
        }
        //check for 4 up and down
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (row < 3) {
                    if (board[row][col] == player &&
                            board[row + 1][col] == player &&
                            board[row + 2][col] == player &&
                            board[row + 3][col] == player) {
                        return true;
                    }
                } else {
                    if (board[row][col] == player &&
                            board[row - 1][col] == player &&
                            board[row - 2][col] == player &&
                            board[row - 3][col] == player) {
                        return true;
                    }
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
