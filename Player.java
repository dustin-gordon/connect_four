package connect4package;
import java.awt.*;

/**
 * <h1>Player</h1>
 * This abstract class instantiates the 4 player types which subclass {@code Player}:
 * Human Player 1, Human Player 2, AI Player 1, and AI Player 2; and defines methods
 * shared by all player types.
 */
abstract public class Player {
    private Color checkerColor;
    public static HumanPlayer HP1 = new HumanPlayer();
    public static HumanPlayer HP2 = new HumanPlayer();
    public static AIPlayer AI1 = new AIPlayer();
    public static AIPlayer AI2 = new AIPlayer();

    /**
     * Setter method for {@code checkerColor}
     * @param newColor Color to be assigned to the player
     */
    public void setCheckerColor(Color newColor){
        checkerColor = newColor;
    }

    /**
     * Getter method for {@code checkerColor}
     * @return The player's current checker piece color
     */
    public Color getCheckerColor() {
        return checkerColor;
    }
}
