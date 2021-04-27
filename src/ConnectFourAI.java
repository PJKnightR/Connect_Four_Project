//TO-DO: Integrate AI functionality into game

public class ConnectFourAI {

    /**
     * Returns the index of the column to place a piece within
     * @param state the starting board
     * @param depth the number of moves to look ahead
     * @return the index of the column to place a piece within
     */
    public int AlphaBetaSearch(char[][] state, int depth) {
        return MaxValue(state, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private int MaxValue(char[][] state, int alpha, int beta) {
        //TO-DO: Implement Max Value Method

        return -1;
    }

    private int MinValue(char[][] state, int alpha, int beta) {
        //TO-DO: Implement Min Value Method

        return -1;
    }
}
