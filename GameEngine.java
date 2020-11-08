package connect4package;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <h1>GameEngine</h1>
 * This class handles drawing the GUI elements of the game board, based on the
 * global board size variables. Handles all game logic such as enforces the
 * rules of the Connect 4 game.
 */
public class GameEngine {
    private static int score1 = 0;
    private static int score2 = 0;
    public static String gameMode;
    public static int currentPlayer = 1;
    static JFrame frame = MainView.frame; // Primary GUI container used by all classes
    static JLabel turnLabel = new JLabel("* Currently Player " +currentPlayer+ "'s turn *");

    /**
     * Generates GUI elements for game mode view
     */
    public static void drawGameGUI() {
        MainView.clear();               // Reset main window
        JPanel board = Grid.drawGrid(); // Generate game mode view

        // Change window title, depending on game mode:
        switch (gameMode) {
            case "PVE":
                frame.setTitle("Single-Player Mode");
                break;
            case "PVP":
                frame.setTitle("Multi-Player Mode");
                break;
            case "EVE":
                frame.setTitle("Spectator Mode");
                break;
        }

        //Make player 1 score header:
        JLabel p1ScoreLabel = new JLabel(" Player 1's Score: [ " + getScore1() + " ]");
        p1ScoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Make player 2 score header:
        JLabel p2ScoreLabel = new JLabel(" Player 2's Score: [ " + getScore2() + " ]");
        p2ScoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Make current player header
        turnLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Make panel:
        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.Y_AXIS));

        //Return to menu button:
        JButton returnButton = new JButton("Return to Main Menu");
        returnButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        returnButton.setActionCommand("return");
        returnButton.addActionListener(new ClickListener());

        //Add components to menu:
        gamePanel.add(p1ScoreLabel);
        gamePanel.add(p2ScoreLabel);
        gamePanel.add(Box.createVerticalGlue());    //Spacing
        gamePanel.add(turnLabel);
        gamePanel.add(Box.createVerticalGlue());    //Spacing
        gamePanel.add(returnButton);
        gamePanel.add(Box.createVerticalGlue());    //Spacing
        gamePanel.add(board);
        gamePanel.add(Box.createVerticalGlue());    //Spacing

        frame.add(gamePanel); //Add menu to window frame
        frame.setVisible(true);
    }

    /**
     * Increments score for player 1
     */
    private static void setScore1() {
        score1 = score1 + 1;
    }

    /**
     * Increments score for player 2
     */
    private static void setScore2() {
        score2 = score2 + 1;
    }

    /**
     * Getter method for {@code score1}
     * @return Player 1's score
     */
    public static int getScore1() {
        return score1;
    }

    /**
     * Getter method for {@code score2}
     * @return Player 2's score
     */
    public static int getScore2() {
        return score2;
    }

    /**
     * Setter method for {@code gameMode}
     * @param newMode Current game mode
     */
    public static void setMode(String newMode) {
        gameMode = newMode;
    }

    /**
     * Getter method for {@code gameMode}
     * @return Current game mode
     */
    public static String getMode() {
        return gameMode;
    }

    /**
     * Event handler for return to main menu button
     */
    private static class ClickListener implements ActionListener {
        public void actionPerformed(ActionEvent click) {
            String command = click.getActionCommand();
            if (command.equals("return")) {
                returnToMain();
            }
        }
    }

    /**
     * Method to remove all GUI elements and return to main menu GUI
     */
    private static void returnToMain(){
        MainView.clear();
        MainView.drawGUI();
        frame.revalidate();
    }

    /**
     * Setter method for {@code currentPlayer}
     * Sets currentPlayer equal to 1 (Player 1 will always go first by default)
     */
    public static void setCurrentPlayer() {
        currentPlayer = 1;
    }

    /**
     * Returns player number opposite of current player,
     * in order to switch turns
     */
    public static void switchPlayer() {
        if (currentPlayer == 1) {
            currentPlayer = 2;
            turnLabel.setText("* Currently Player " + 2 + "'s turn *");
        } else {
            currentPlayer = 1;
            turnLabel.setText("* Currently Player " + 1 + "'s turn *");
        }
        frame.revalidate();    // Update score counters
    }

    /**
     * Checks if button clicked is valid move
     * @param ID Identifier of button clicked
     * @return Boolean value if move is legal
     */
    public static Boolean isValidMove(int ID) {
        //check if cell is already taken:
        if (Grid.cells[ID].getIsFilled() == true) {
            System.out.println("Cell is taken!"); //debug
            JOptionPane.showMessageDialog(MainView.frame, "Invalid move! (Cell is filled)");  //Warning pop-up
            return false;
        }
        //Check if cell below is empty:
        if (!(ID >= (MainView.getCellCount() - MainView.columns)) &&             //Checks that cell isn't on bottom row
                Grid.cells[(MainView.columns + ID)].getIsFilled() == false) {    //Checks if cell under is filled
            JOptionPane.showMessageDialog(MainView.frame, "Invalid move! (Cell below is empty)");  //Warning pop-up
            //System.out.println("Cell below is empty!"); //debug
            return false;
        }
        //Check if cell is on the bottom row:
        if (ID >= (MainView.getCellCount() - MainView.columns)) {
            //System.out.println("Valid move: bottom row & empty"); //debug
            return true;
        } else {
            //System.out.println("Default 'true' returned"); //debug
            return true;
        }
    }

    /**
     * Checks if given ID is within range of actual ID array
     * @param ID Cell ID to be checked
     * @return Boolean value if ID lies within array
     */
    public static Boolean IDInBounds(int ID){
        if ((ID + 1) >= MainView.getCellCount()) {
            //System.out.println("Index is out of bounds (too high)");
            return false; // Index out of bounds
        }
        else if (ID < 0) {
            //System.out.println("Index is out of bounds (too low)");
            return false; // Index out of bounds
        }
        else
            return true;  // Index within bounds
    }

    /**
     * Checks if given row is within range of grid
     * @param row Row location to be checked
     * @return Boolean value if row location exists on grid
     */
    public static Boolean rowInBounds(int row){
        if (row > MainView.rows){
            //System.out.println("Index is out of bounds (too high)");
            return false; // Index out of bounds
        }
        else if (row - 1 <= 0){
            //System.out.println("Index is out of bounds (too low)");
            return false; // Index out of bounds
        }
        else
            return true;  // Index within bounds
    }

    /**
     * Checks if given column is within range of grid
     * @param col Column location to be checked
     * @return Boolean value if column location exists on grid
     */
    public static Boolean colInBounds(int col){
        if (col > MainView.columns){
            //System.out.println("Index is out of bounds (too high)");
            return false; // Index out of bounds
        }
        else if (col - 1 <= 0){
            //System.out.println("Index is out of bounds (too low)");
            return false; // Index out of bounds
        }
        else
            return true;  // Index within bounds
    }

    /**
     * Checks if move will result in a win for player making it
     * @param ID The ID of the cell where checker was placed
     * @return Boolean value if move will create win state
     */
    public static Boolean isWinningMove(int ID) {
        Boolean winning = false;
        int consecutiveVert = 1;
        int consecutiveHoriz = 1;
        int consecutiveDiagUpperLeft = 1;
        int consecutiveDiagLowerLeft = 1;
        int consecutiveDiagUpperRight = 1;
        int consecutiveDiagLowerRight = 1;
        int nextRowID = ID + MainView.columns;
        int leftColID = ID - 1;
        int rightColID = ID + 1;

        Color color = Grid.cells[ID].getColor();

        //Vertical check:
        if (!(ID >= (MainView.getCellCount() - MainView.columns))) {
            while ((Grid.cells[nextRowID].getIsFilled()) && (color == (Grid.cells[nextRowID].getColor()))) {
                if ((nextRowID >= (MainView.getCellCount() - MainView.columns))) {
                    consecutiveVert += 1;
                    break;
                }
                consecutiveVert += 1;
                nextRowID += MainView.columns;
            }
        }

        //Horizontal check:
        while (IDInBounds(ID) && (Grid.cells[leftColID].getIsFilled()) && (color == (Grid.cells[leftColID].getColor()))) {
            if (Grid.cells[leftColID].getColumn() == 0) {
                consecutiveHoriz += 1;
                break;
            }
            consecutiveHoriz += 1;
            leftColID -= 1;
            ID -= 1;
            }
         while (IDInBounds(ID) && (Grid.cells[rightColID].getIsFilled()) && (color == (Grid.cells[rightColID].getColor()))) {
            if (Grid.cells[rightColID].getColumn() == MainView.columns) {
                consecutiveHoriz += 1;
                break;
            }
            consecutiveHoriz += 1;
            rightColID += 1;
            ID += 1;
            if (consecutiveHoriz == 4)
                break;
            }

        //Diagonal check:
        int[] upperLeftRowAndCol  = {Grid.cells[ID].getRow()-1, Grid.cells[ID].getColumn()-1};
        int[] lowerLeftRowAndCol  = {Grid.cells[ID].getRow()+1, Grid.cells[ID].getColumn()-1};
        int[] upperRightRowAndCol = {Grid.cells[ID].getRow()-1, Grid.cells[ID].getColumn()+1};
        int[] lowerRightRowAndCol = {Grid.cells[ID].getRow()+1, Grid.cells[ID].getColumn()+1};

        //Check diagonally upwards to the left:
        int upperLeftID = ID - (MainView.columns + 1);
        if (((findCellID(upperLeftRowAndCol[0], upperLeftRowAndCol[1])) == -1) ||
            (IDInBounds(upperLeftID) == false)){ //Avoid out of bounds exception
            //System.out.println("Upper left cell is out of bounds."); //debug
        }
        else{
            while (IDInBounds(upperLeftID) && Grid.cells[upperLeftID].getIsFilled() &&
                    Grid.cells[upperLeftID].getColor() == color) {

                if (Grid.cells[upperLeftID].getColumn() == 0){
                    consecutiveDiagUpperLeft += 1;
                    break;
                }
                System.out.println("Consecutive Diagonal Upper-Left for Player " + currentPlayer + ": "
                        + consecutiveDiagUpperLeft + " checkers"); //debug
                consecutiveDiagUpperLeft += 1;
                upperLeftID -= (MainView.columns + 1);
                if (consecutiveDiagUpperLeft == 4)
                    break;
            }
        }

        //Check diagonally downwards to the left:
        int lowerLeftID = ID + (MainView.columns - 1);
        if ((findCellID(lowerLeftRowAndCol[0], lowerLeftRowAndCol[1])) == -1){ //Avoid out of bounds exception
            //System.out.println("Lower left cell is out of bounds."); //debug
        }
        else{
            while (IDInBounds(lowerLeftID) && Grid.cells[lowerLeftID].getIsFilled() &&
                    Grid.cells[lowerLeftID].getColor() == color) {

                if (Grid.cells[lowerLeftID].getColumn() == 0){
                    consecutiveDiagLowerLeft += 1;
                    break;
                }
                System.out.println("Consecutive Diagonal Lower-Left for Player " + currentPlayer + ": "
                        + consecutiveDiagLowerLeft + " checkers"); //debug
                consecutiveDiagLowerLeft += 1;
                lowerLeftID += (MainView.columns - 1);
                if (consecutiveDiagLowerLeft == 4)
                    break;
            }
        }

        //Check diagonally upwards to the right:
        int upperRightID = ID - (MainView.columns - 1);
        if ((findCellID(upperRightRowAndCol[0], upperRightRowAndCol[1])) == -1){ //Avoid out of bounds exception
            //System.out.println("Upper right cell is out of bounds."); //debug
        }
        else{
            while (IDInBounds(upperRightID) && Grid.cells[upperRightID].getIsFilled() &&
                    Grid.cells[upperRightID].getColor() == color) {

                if (Grid.cells[upperRightID].getColumn() == MainView.columns){
                    consecutiveDiagUpperRight += 1;
                    break;
                }
                System.out.println("Consecutive Diagonal Upper-Right for Player " + currentPlayer + ": "
                        + consecutiveDiagUpperRight + " checkers"); //debug
                consecutiveDiagUpperRight += 1;
                upperRightID -= (MainView.columns - 1);
                if (consecutiveDiagUpperRight == 4)
                    break;
            }
        }

        //Check diagonally downwards to the right:
        int lowerRightID = ID + (MainView.columns + 1);
        if (!(ID >= (MainView.getCellCount() - MainView.columns))) { //Avoid out of bounds exception
            while (IDInBounds(lowerRightID) && Grid.cells[lowerRightID].getIsFilled() && Grid.cells[lowerRightID].getColor() == color) {
                if (Grid.cells[lowerRightID].getColumn() == (MainView.columns)){
                    consecutiveDiagLowerRight += 1;
                    break;
                }

                System.out.println("Consecutive Diagonal Lower-Right for Player " + currentPlayer + ": "
                        + consecutiveDiagLowerRight + " checkers"); //debug
                consecutiveDiagLowerRight += 1;
                lowerRightID += (MainView.columns + 1);
                if (consecutiveDiagLowerRight == 4)
                    break;
            }
            if ((lowerRightID == (MainView.getCellCount() - 1))) {
                consecutiveDiagLowerRight += 1;
            }
        }

        //Determine if full Connect-4 pattern was found:
        if ((consecutiveVert >= 4) || (consecutiveHoriz >= 4) || (consecutiveDiagUpperLeft >= 4) || (consecutiveDiagLowerLeft >= 4) ||
            (consecutiveDiagUpperRight >= 4) || (consecutiveDiagLowerRight >= 4)) {
            winning = true;
        }
        else {
            winning = false;
            System.out.println("Consecutive Vertical for Player " + currentPlayer +  ": " + consecutiveVert + " checkers"); //debug
            System.out.println("Consecutive Horiz for Player " + currentPlayer +  ": " + consecutiveHoriz + " checkers");   //debug
            System.out.println("Consecutive Diagonal Upper-Left for Player " + currentPlayer + ": "
                    + consecutiveDiagUpperLeft + " checkers"); //debug
            System.out.println("Consecutive Diagonal Lower-Left for Player " + currentPlayer + ": "
                    + consecutiveDiagLowerLeft + " checkers"); //debug
            System.out.println("Consecutive Diagonal Upper-Right for Player " + currentPlayer + ": "
                    + consecutiveDiagUpperRight + " checkers"); //debug
            System.out.println("Consecutive Diagonal Lower-Right for Player " + currentPlayer + ": "
                    + consecutiveDiagLowerRight + " checkers"); //debug
        }
        return winning;
    }

    /**
     * Displays GUI popup announcing that a player has won
     */
    public static void declareWinner(){
        System.out.println("Congratulations, Player " + currentPlayer + "!" + " You have won!"); // debug
        if(currentPlayer == 1)  // Increase player 1's score, if winner
            setScore1();
        if(currentPlayer == 2)   // Increase player 2's score, if winner
            setScore2();

        // Display who won:
        JOptionPane.showMessageDialog(MainView.frame, "Congratulations, Player " + currentPlayer + "!" + " You have won!");
        returnToMain();
    }

    /**
     * Finds the ID of a cell based on its row and column coordinates
     * @param rowInput The row position
     * @param colInput The column position
     * @return The ID of cell found, or -1 if not found
     */
    private static int findCellID (int rowInput, int colInput) {
        for (int ID = 0; ID < MainView.getCellCount(); ID++) {
            if (Grid.cells[ID].getRow() == rowInput && Grid.cells[ID].getColumn() == colInput) {
                return ID;
            }
        }
        return -1;
    }
}


