package connect4package;

/**
 * <h1>AIPlayer</h1>
 * This is a subclass of the abstract {@code Player} class, specifically for
 * a player object controlled by AI logic defined within.
 */
public class AIPlayer extends Player {

    /**
     * Analyzes checker pieces on board and finds any potential winning moves
     * from opposing player in their next turn, and places AI's checker there
     * instead to block the other player from winning
     * @return Boolean value if blocking move is found
     */
    private boolean findBlockingMove(){
        // TODO implement findBlockingMove
        return false;
    }

    /**
     * Analyzes checker pieces on board and finds any potential moves that will
     * make the AI win. Scans the board horizontally, vertically, and diagonally
     * for Connect-4 patterns that are 1 valid move away from completion.
     * @return Boolean value if winning move is found
     */
    private boolean findWinningMove(){
        // TODO implement findWinningMove
        return false;
    }

    /**
     * When no winning or blocking moves are found, the AI will find all possible
     * valid moves it can make and will select one at random in attempt to build a
     * new Connect-4 pattern
     */
    private void findRandomMove(){
        // TODO implement findRandomMove
    }
}
