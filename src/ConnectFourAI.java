//TO-DO: Integrate AI functionality into game

public class ConnectFourAI {

    /**
     * Returns the index of the column to place a piece within
     * @param state the starting board
     * @param depth the number of moves to look ahead
     * @return the index of the column to place a piece within
     */
    public int AlphaBetaSearch(char[][] state, int depth) {
        return MaxValue(state, Integer.MIN_VALUE, Integer.MAX_VALUE, depth);
    }

    private int MaxValue(char[][] state, int alpha, int beta, int depth) {
        //TO-DO: Implement Max Value Method

        return -1;
    }

    private int MinValue(char[][] state, int alpha, int beta, int depth) {
        //TO-DO: Implement Min Value Method

        return -1;
    }

    /**
     * Heuristic Function, finds the longest sequence on the board given a new piece placement and returns
     * a score value based on the length of the longest sequence that isn't blocked by an opponents piece.
     * This is used by max and min to find the best spots to place or block placement.
     * @param state the board the new piece is being placed on
     * @param placedPieceColumnIndex the index of the column the piece is being placed within
     */
    private int FindLongestSequence(char[][] state, int placedPieceColumnIndex) {
        int longestSequenceScore = 0;

        //TO-DO: Implement Find Longest Sequence Method

        //by row
        for (int row = 0; row < state.length; row++) {
            for (int col = 0; col < state[0].length - 3; col++) {
                //if we find one piece, check for another or empty. Continue til we would get to 4

                //found first piece
                if (state[row][col] == 'B') {

                    //found second piece
                    if (state[row][col + 1] == 'B') {

                        //found third piece
                        if (state[row][col + 2] == 'B') {

                            //found fourth piece
                            if (state[row][col + 3] == 'B') {
                                return 10000;

                                //check if a fourth piece can be used here
                            }  else if (state[row][col + 3] != 'R') {
                                if (longestSequenceScore < 16) {
                                    longestSequenceScore = 16;
                                }
                            }
                            //check if a third and fourth piece can be used here
                        }  else if (state[row][col + 2] != 'R' && state[row][col + 3] != 'R') {
                            if (longestSequenceScore < 4) {
                                longestSequenceScore = 4;
                            }
                        }
                        //check if a second, third, and fourth piece can be used here
                    } else if (state[row][col + 1] != 'R' && state[row][col + 2] != 'R' && state[row][col + 3] != 'R') {
                        if (longestSequenceScore < 1) {
                            longestSequenceScore = 1;
                        }
                    }
                }
            }
        }

        //by column
        for (int row = 0; row < state.length - 3; row++) {
            for (int col = 0; col < state[0].length; col++) {
                //if we find one piece, check for another or empty. Continue til we would get to 4

                //found first piece
                if (state[row][col] == 'B') {

                    //found second piece
                    if (state[row + 1][col] == 'B') {

                        //found third piece
                        if (state[row + 2][col] == 'B') {

                            //found fourth piece
                            if (state[row + 3][col] == 'B') {
                                return 10000;

                                //check if a fourth piece can be used here
                            }  else if (state[row + 3][col] != 'R') {
                                if (longestSequenceScore < 16) {
                                    longestSequenceScore = 16;
                                }
                            }
                            //check if a third and fourth piece can be used here
                        }  else if (state[row + 2][col] != 'R' && state[row + 3][col] != 'R') {
                            if (longestSequenceScore < 4) {
                                longestSequenceScore = 4;
                            }
                        }
                        //check if a second, third, and fourth piece can be used here
                    } else if (state[row + 1][col] != 'R' && state[row + 2][col] != 'R' && state[row + 3][col] != 'R') {
                        if (longestSequenceScore < 1) {
                            longestSequenceScore = 1;
                        }
                    }
                }
            }
        }

        //upward diagonal
        for (int row = 3; row < state.length; row++) {
            for (int col = 0; col < state[0].length - 3; col++) {

                //found first piece
                if (state[row][col] == 'B') {

                    //found second piece
                    if (state[row - 1][col + 1] == 'B') {

                        //found third piece
                        if (state[row - 2][col + 2] == 'B') {

                            //found fourth piece
                            if (state[row - 3][col + 3] == 'B') {
                                return 10000;

                                //check if a fourth piece can be used here
                            }  else if (state[row - 3][col + 3] != 'R') {
                                if (longestSequenceScore < 16) {
                                    longestSequenceScore = 16;
                                }
                            }
                            //check if a third and fourth piece can be used here
                        }  else if (state[row - 2][col + 2] != 'R' && state[row - 3][col + 3] != 'R') {
                            if (longestSequenceScore < 4) {
                                longestSequenceScore = 4;
                            }
                        }
                        //check if a second, third, and fourth piece can be used here
                    } else if (state[row - 1][col + 1] != 'R' && state[row - 2][col + 2] != 'R' && state[row - 3][col + 3] != 'R') {
                        if (longestSequenceScore < 1) {
                            longestSequenceScore = 1;
                        }
                    }
                }
            }
        }

        //downward diagonal
        for (int row = 0; row < state.length - 3; row++) {
            for (int col = 0; col < state[0].length - 3; col++) {
                //found first piece
                if (state[row][col] == 'B') {

                    //found second piece
                    if (state[row + 1][col + 1] == 'B') {

                        //found third piece
                        if (state[row + 2][col + 2] == 'B') {

                            //found fourth piece
                            if (state[row + 3][col + 3] == 'B') {
                                return 10000;

                                //check if a fourth piece can be used here
                            }  else if (state[row + 3][col + 3] != 'R') {
                                if (longestSequenceScore < 16) {
                                    longestSequenceScore = 16;
                                }
                            }
                            //check if a third and fourth piece can be used here
                        }  else if (state[row + 2][col + 2] != 'R' && state[row + 3][col + 3] != 'R') {
                            if (longestSequenceScore < 4) {
                                longestSequenceScore = 4;
                            }
                        }
                        //check if a second, third, and fourth piece can be used here
                    } else if (state[row + 1][col + 1] != 'R' && state[row + 2][col + 2] != 'R' && state[row + 3][col + 3] != 'R') {
                        if (longestSequenceScore < 1) {
                            longestSequenceScore = 1;
                        }
                    }
                }
            }
        }

        return longestSequenceScore;
    }
}
