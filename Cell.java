package connect4package;
import javax.swing.*;
import java.awt.*;

/**
 * <h1>Cell</h1>
 * This class implements the cell of the game board as well as the "checker" by
 * implementing both concepts as a single {@code JButton} that is either white when
 * empty or filled with the appropriate player's color when occupied.
 */
public class Cell extends JButton {
    private Boolean isFilled = false;
    private int currentRow;
    private int currentColumn;
    private int ID;
    private Color cellColor;

    /**
     * <h1>Constructor Method</h1>
     * Initializes the cell with its row and column coordinates in respect to the game board.
     * Sets color to white by default to indicate emptiness.
     * @param x The cell's row position
     * @param y The cell's column position
     */
    public Cell(int x, int y){
        setRow(x);
        setColumn(y);
        setBackground(Color.white);
        setFocusable(false);

        // Enlarge the button so that it becomes a circle rather than an oval:
        Dimension size = getPreferredSize();
        size.width = size.height = Math.max(size.width, size.height);
        setPreferredSize(size);

        // Overrides JButton background and allows for us to paint a round background:
        setContentAreaFilled(false);
    }

    /**
     * Setter method for {@code currentRow}
     * @param x Row location input for the cell
     */
    public void setRow(int x) {
        currentRow = x;
    }

    /**
     * Setter method for {@code currentColumn}
     * @param y Column location input for the cell
     */
    public void setColumn(int y){
        currentColumn = y;
    }

    /**
     * Getter method for {@code currentRow}
     * @return Row location of the cell
     */
    public int getRow(){
        return currentRow;
    }

    /**
     * Getter method for {@code currentColumn}
     * @return Columnt location of the cell
     */
    public int getColumn(){
        return currentColumn;
    }

    /**
     * Setter method for {@code isFilled}, no need for param
     * since checkers are never removed during a game
     */
    public void setIsFilled() {
        isFilled = true;
    }

    /**
     * Getter method for {@code isFilled}
     * @return Boolean value if cell is occupied by a checker
     */
    public Boolean getIsFilled() {
        return isFilled;
    }

    /**
     * Generates the background color of the button
     * @param g Graphics object
     */
    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(Color.gray);
        } else {
            g.setColor(getBackground());
        }
        g.fillOval(0, 0, getSize().width - 1, getSize().height - 1);

        super.paintComponent(g);
    }

    /**
     * Generates the border of button
     * @param g Graphics object
     */
    protected void paintBorder(Graphics g) {
        g.setColor(Color.black);
        g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
    }

    /**
     * Setter method for cell's {@code ID}
     * @param newID New ID to assign to cell
     */
    public void setID(int newID){
        ID = newID;
    }

    /**
     * Getter method for {@code ID}
     * @return Unique identifier for locating cell in the grid
     */
    public int getID(){
        return ID;
    }
    /**
     * Setter method for cell's {@code cellColor}
     * @param color New cell color to assign to cell
     */
    public void setColor(Color color){
        cellColor = color;
    }
    /**
     * Getter method for {@code cellColor}
     * @return Unique identifier for identifying cell ownership in the grid
     */
    public Color getColor(){
        return cellColor;
    }
}