//TO-DO: Integrate AI functionality into game

import java.util.ArrayList;

public class ConnectFourAI {

    public int NUM_ROWS = 6;
    public int NUM_COLUMNS = 7;
    int LOW_SCORE = 1, MEDIUM_SCORE = 4, HIGH_SCORE = 16, MAX_SCORE = 10000;

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
     * @param curColor the char representation of the current color being placed
     * @param opponentColor the char representation of the opponent color
     */
    private int FindLongestSequence(char[][] state, char curColor, char opponentColor) {
        int longestSequenceScore = 0;

        //by row
        for (int row = 0; row < state.length; row++) {
            for (int col = 0; col < state[0].length; col++) {
                //if we find one piece, check for another or empty. Continue til we would get to 4

                //found first piece
                if (state[row][col] == curColor) {

                    //found second piece
                    if (state[row][col + 1] == curColor) {

                        //found third piece
                        if (state[row][col + 2] == curColor) {

                            //found fourth piece
                            if (state[row][col + 3] == curColor) {
                                return MAX_SCORE;

                                //check if a fourth piece can be used here
                            } else if (col < 4) {
                                if (state[row][col + 3] != opponentColor) {
                                    longestSequenceScore = updateScore(longestSequenceScore, HIGH_SCORE);
                                }
                            } else {
                                if (state[row][col - 3] != opponentColor) {
                                    longestSequenceScore = updateScore(longestSequenceScore, HIGH_SCORE);
                                }
                            }

                            //check if a third and fourth piece can be used here
                        } else if (col < 4) {
                            if (state[row][col - 2] != opponentColor && state[row][col - 3] != opponentColor) {
                                longestSequenceScore = updateScore(longestSequenceScore, MEDIUM_SCORE);
                            }
                        } else {
                            if (state[row][col - 2] != opponentColor && state[row][col - 3] != opponentColor) {
                                longestSequenceScore = updateScore(longestSequenceScore, MEDIUM_SCORE);
                            }
                        }

                        //check if a second, third, and fourth piece can be used here
                    } else if (col < 4) {
                        if (state[row][col + 1] != opponentColor && state[row][col + 2] != opponentColor && state[row][col + 3] != opponentColor) {
                            longestSequenceScore = updateScore(longestSequenceScore, LOW_SCORE);
                        }
                    } else {
                        if (state[row][col - 1] != opponentColor && state[row][col - 2] != opponentColor && state[row][col - 3] != opponentColor) {
                            longestSequenceScore = updateScore(longestSequenceScore, LOW_SCORE);
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
                if (state[row][col] == curColor) {

                    //found second piece
                    if (state[row + 1][col] == curColor) {

                        //found third piece
                        if (state[row + 2][col] == curColor) {

                            //found fourth piece
                            if (state[row + 3][col] == curColor) {
                                return MAX_SCORE;

                                //check if a fourth piece can be used here
                            } else if (row < 3) {
                                if (state[row + 3][col] != opponentColor) {
                                    longestSequenceScore = updateScore(longestSequenceScore, HIGH_SCORE);
                                }
                            } else {
                                if (state[row - 3][col] != opponentColor) {
                                    longestSequenceScore = updateScore(longestSequenceScore, HIGH_SCORE);
                                }
                            }
                            //check if a third and fourth piece can be used here
                        } else if (row < 3) {
                            if (state[row + 2][col] != opponentColor && state[row + 3][col] != opponentColor) {
                                longestSequenceScore = updateScore(longestSequenceScore, MEDIUM_SCORE);
                            }
                        } else {
                            if (state[row - 2][col] != opponentColor && state[row - 3][col] != opponentColor) {
                                longestSequenceScore = updateScore(longestSequenceScore, MEDIUM_SCORE);
                            }
                        }
                        //check if a second, third, and fourth piece can be used here
                    } else if (row < 3) {
                        if (state[row + 1][col] != opponentColor && state[row + 2][col] != opponentColor && state[row + 3][col] != opponentColor) {
                            longestSequenceScore = updateScore(longestSequenceScore, LOW_SCORE);
                        }
                    } else {
                        if (state[row - 1][col] != opponentColor && state[row - 2][col] != opponentColor && state[row - 3][col] != opponentColor) {
                            longestSequenceScore = updateScore(longestSequenceScore, LOW_SCORE);
                        }
                    }
                }
            }
        }

        //upward diagonal
        for (int row = 3; row < state.length; row++) {
            for (int col = 0; col < state[0].length - 3; col++) {

                //found first piece
                if (state[row][col] == curColor) {

                    //found second piece
                    if (state[row - 1][col + 1] == curColor) {

                        //found third piece
                        if (state[row - 2][col + 2] == curColor) {

                            //found fourth piece
                            if (state[row - 3][col + 3] == curColor) {
                                return MAX_SCORE;

                                //check if a fourth piece can be used here
                            }  else if (state[row - 3][col + 3] != opponentColor) {
                                longestSequenceScore = updateScore(longestSequenceScore, HIGH_SCORE);
                            }
                            //check if a third and fourth piece can be used here
                        }  else if (state[row - 2][col + 2] != opponentColor && state[row - 3][col + 3] != opponentColor) {
                            longestSequenceScore = updateScore(longestSequenceScore, MEDIUM_SCORE);
                        }
                        //check if a second, third, and fourth piece can be used here
                    } else if (state[row - 1][col + 1] != opponentColor && state[row - 2][col + 2] != opponentColor && state[row - 3][col + 3] != opponentColor) {
                        longestSequenceScore = updateScore(longestSequenceScore, LOW_SCORE);
                    }
                }
            }
        }

        //downward diagonal
        for (int row = 0; row < state.length - 3; row++) {
            for (int col = 0; col < state[0].length - 3; col++) {
                //found first piece
                if (state[row][col] == curColor) {

                    //found second piece
                    if (state[row + 1][col + 1] == curColor) {

                        //found third piece
                        if (state[row + 2][col + 2] == curColor) {

                            //found fourth piece
                            if (state[row + 3][col + 3] == curColor) {
                                return MAX_SCORE;

                                //check if a fourth piece can be used here
                            }  else if (state[row + 3][col + 3] != opponentColor) {
                                longestSequenceScore = updateScore(longestSequenceScore, HIGH_SCORE);
                            }
                            //check if a third and fourth piece can be used here
                        }  else if (state[row + 2][col + 2] != opponentColor && state[row + 3][col + 3] != opponentColor) {
                            longestSequenceScore = updateScore(longestSequenceScore, MEDIUM_SCORE);
                        }
                        //check if a second, third, and fourth piece can be used here
                    } else if (state[row + 1][col + 1] != opponentColor && state[row + 2][col + 2] != opponentColor && state[row + 3][col + 3] != opponentColor) {
                        longestSequenceScore = updateScore(longestSequenceScore, LOW_SCORE);
                    }
                }
            }
        }

        return longestSequenceScore;
    }

    /**
     * Updates the score value if it's higher than the previous
     * @param oldScore the old score value
     * @param newScore the new score value
     * @return the higher of old or new score
     */
    private int updateScore(int oldScore, int newScore) {
        if (newScore > oldScore) {
            return newScore;
        } else {
            return oldScore;
        }
    }

    /**
     * An AI the chooses a purely random column to drop pieces in
     * @return the index of the column to place a piece in
     */
    public int randomSelection() {
        return (int) (Math.random() * 7);
    }

    /**
     * Checks if the opponent is about to win and blocks the victory
     * @param state the board
     * @param opponentColor the color of the opponents pieces
     * @return the position to place a piece to block the opponent
     */
    public int blockOpponentPiece(char[][] state, char opponentColor) {
        //by row
        for (int row = 0; row < state.length; row++) {
            for (int col = 0; col < state[0].length; col++) {
                if (col < 4) {
                    if (state[row][col] == opponentColor && state[row][col + 1] == opponentColor && state[row][col + 2] == opponentColor && state[row][col + 3] == ' ') {
                        return col + 3;
                    }
                } else {
                    if (state[row][col] == opponentColor && state[row][col - 1] == opponentColor && state[row][col - 2] == opponentColor && state[row][col - 3] == ' ') {
                        return col - 3;
                    }
                }
            }
        }

        //by column
        for (int row = 0; row < state.length; row++) {
            for (int col = 0; col < state[0].length; col++) {
                if (row < 3) {
                    if (state[row][col] == opponentColor && state[row + 1][col] == opponentColor && state[row + 2][col] == opponentColor && state[row + 3][col] == ' ') {
                        return col;
                    }
                } else {
                    if (state[row][col] == opponentColor && state[row - 1][col] == opponentColor && state[row - 2][col] == opponentColor && state[row - 3][col] == ' ') {
                        return col;
                    }
                }
            }
        }

        //upward diagonal
        for (int row = 3; row < state.length; row++) {
            for (int col = 0; col < state[0].length - 3; col++) {
                if (state[row][col] == opponentColor && state[row - 1][col + 1] == opponentColor && state[row - 2][col + 2] == opponentColor && state[row - 3][col + 3] == ' ') {
                    return col + 3;
                }
            }
        }

        //downward diagonal
        for (int row = 0; row < state.length - 3; row++) {
            for (int col = 0; col < state[0].length - 3; col++) {
                if (state[row][col] == opponentColor && state[row + 1][col + 1] == opponentColor && state[row + 2][col + 2] == opponentColor && state[row + 3][col + 3] == ' ') {
                    return col + 3;
                }
            }
        }

        return -1;
    }

    /**
     * An AI that chooses purely random places to drop pieces, until it can block an opponents victory condition
     * @param state the board
     * @param opponentColor the opponents piece color
     * @return the position to place the piece
     */
    public int randomSelectionBlocking(char[][] state, char opponentColor) {
        int play = blockOpponentPiece(state, opponentColor);
        if (play != -1) {
            return play;
        } else {
            return randomSelection();
        }
    }

    /**
     * An AI that chooses purely random places to drop pieces until it can block the opponents victory condition,
     * or until it is one piece placement away from victory
     * @param state the board
     * @param curColor the current players piece color
     * @param opponentColor the opponents piece color
     * @return the position to place the piece
     */
    public int randomSelectionBlockingPrioritization(char[][] state, char curColor, char opponentColor) {
        int play = blockOpponentPiece(state, opponentColor);

        if (play != -1) {
            return play;
        } else {
            //by row
            for (int row = 0; row < state.length; row++) {
                for (int col = 0; col < state[0].length; col++) {
                    if (col < 4) {
                        if (state[row][col] == curColor && state[row][col + 1] == curColor && state[row][col + 2] == curColor && state[row][col + 3] == ' ') {
                            return col + 3;
                        }
                    } else {
                        if (state[row][col] == curColor && state[row][col - 1] == curColor && state[row][col - 2] == curColor && state[row][col - 3] == ' ') {
                            return col - 3;
                        }
                    }
                }
            }

            //by column
            for (int row = 0; row < state.length; row++) {
                for (int col = 0; col < state[0].length; col++) {
                    if (row < 3) {
                        if (state[row][col] == curColor && state[row + 1][col] == curColor && state[row + 2][col] == curColor && state[row + 3][col] == ' ') {
                            return col;
                        }
                    } else {
                        if (state[row][col] == curColor && state[row - 1][col] == curColor && state[row - 2][col] == curColor && state[row - 3][col] == ' ') {
                            return col;
                        }
                    }
                }
            }

            //upward diagonal
            for (int row = 3; row < state.length; row++) {
                for (int col = 0; col < state[0].length - 3; col++) {
                    if (state[row][col] == curColor && state[row - 1][col + 1] == curColor && state[row - 2][col + 2] == curColor && state[row - 3][col + 3] == ' ') {
                        return col + 3;
                    }
                }
            }

            //downward diagonal
            for (int row = 0; row < state.length - 3; row++) {
                for (int col = 0; col < state[0].length - 3; col++) {
                    if (state[row][col] == curColor && state[row + 1][col + 1] == curColor && state[row + 2][col + 2] == curColor && state[row + 3][col + 3] == ' ') {
                        return col + 3;
                    }
                }
            }
        }

        return randomSelection();
    }
}
